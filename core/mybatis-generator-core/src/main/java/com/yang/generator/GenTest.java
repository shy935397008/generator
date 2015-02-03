package com.yang.generator;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.yang.core.AbstractBean;
import com.yang.core.Constant;
import com.yang.core.Property;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class GenTest {
	private static final String CONSTANT = "constant";
	private static final String BEAN = "bean";

	private static final String CLASSNAME = "className";
	private static final String PROPERTY = "property";
	private static final String PROPERTITES = "properties";
	private static final String VALUE = "value";

	private static final String PACKAGE = "package";
	private static final String IMPORT = "import";
	private static final String JAVA = "java";

	private static final String DOT = ".";
	private static final String UNBIAS = "\\";

	private static final String CONSTANTTMP = "constant.ftl";
	private static final String BEANTMP = "bean.ftl";
	private static final String DAOTMP = "dao.ftl";
	private static final String SERVICETMP = "service.ftl";
	private static final String CONTROLLERTMP = "controller.ftl";

	private static final String DAO = "dao";
	private static final String SERVICE = "service";
	private static final String CONTROLLER = "controller";

	/**
	 * 解析配置文件
	 * 
	 * @param listC
	 * @param fileName
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void parseBean(List<Constant> listC, String fileName)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document document = builder.parse(fileName);
		Element element = document.getDocumentElement();
		NodeList nodeList = null;
		if (fileName.contains(CONSTANT)) {
			nodeList = element.getElementsByTagName(CONSTANT);

		} else if (fileName.contains(BEAN)) {
			nodeList = element.getElementsByTagName(BEAN);
		}
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node item = nodeList.item(i);
			if (item == null) {
				continue;
			}
			Constant con = new Constant();
			String string = item.getAttributes().getNamedItem(CLASSNAME)
					.getTextContent();
			if (string != null && string.trim().length() > 0) {
				String pack = string.substring(0, string.lastIndexOf(DOT));
				String className = string
						.substring(string.lastIndexOf(DOT) + 1);
				con.setClassName(className);
				con.setPack(pack);
			}
			List<Property> listP = new ArrayList<Property>();
			Set<String> listImp = new HashSet<String>();
			NodeList list = item.getChildNodes();
			for (int j = 0; j < list.getLength(); j++) {
				Node node = list.item(j);
				Property p = new Property();
				if (node != null) {
					NamedNodeMap attributes = node.getAttributes();
					if (attributes != null) {
						String clazz = attributes.getNamedItem(CLASSNAME)
								.getTextContent();
						if (clazz.contains(DOT)) {
							p.setClassName(clazz.substring(clazz
									.lastIndexOf(DOT) + 1));
							listImp.add(clazz);
						} else {
							p.setClassName(clazz);
						}
						p.setProperty(attributes.getNamedItem(PROPERTY)
								.getTextContent());
						if (fileName.contains(CONSTANT)) {
							p.setValue(attributes.getNamedItem(VALUE)
									.getTextContent());
						}
						listP.add(p);
					}
				}
			}
			con.setImports(listImp);
			con.setList(listP);
			listC.add(con);
		}
	}

	/**
	 * 获取模板
	 * 
	 * @param fileName
	 *            模板名
	 * @return
	 * @throws IOException
	 */
	public static Template getTemplate(String fileName) throws IOException {
		Configuration conf = new Configuration();
		conf.setClassForTemplateLoading(GenTest.class, "");
		conf.setObjectWrapper(new DefaultObjectWrapper());
		return conf.getTemplate(fileName);
	}

	public static void genBean(String url) throws ParserConfigurationException,
			SAXException, IOException, TemplateException {
		List<Constant> listC = new ArrayList<Constant>();
		if (url.contains(CONSTANT) || url.contains(BEAN)) {
			parseBean(listC, url);
			for (Constant constant : listC) {
				// ----------------------1.bean--------------------------
				String dir = constant.getPack().replaceAll(UNBIAS + DOT,
						File.separator + File.separator);
				String[] split = constant.getPack().split("\\.");
				String baseDir = dir.substring(0,
						dir.lastIndexOf(File.separator));
				String beanDir = null;
				if (url.contains(BEAN)) {
					beanDir = baseDir + File.separator
							+ constant.getPack().split("\\.")[split.length - 1];
				} else if (url.contains(CONSTANT)) {
					beanDir = baseDir + File.separator + CONSTANT;
				}
				genDir(beanDir);
				String filePath = beanDir + File.separator
						+ constant.getClassName() + DOT + JAVA;
				File f = new File(filePath);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(PACKAGE, constant.getPack());
				map.put(CLASSNAME, constant.getClassName());
				map.put(PROPERTITES, constant.getList());
				map.put(IMPORT, constant.getImports());
				if (url.contains(CONSTANT)) {
					genSource(map, CONSTANTTMP, f);
				} else if (url.contains(BEAN)) {
					genSource(map, BEANTMP, f);
				}
				if (url.contains(BEAN)) {
					// --------------------------2. dao-------------------

					List<String> classList = new ArrayList<String>();
					String beanClass = constant.getPack() + DOT
							+ constant.getClassName();
					classList.add(beanClass);
					String daoClass = genCode(baseDir, map, constant, DAO,
							DAOTMP, classList);

					// --------------------------3. service--------------------
					classList.add(beanClass);
					classList.add(daoClass);
					String ServiceClass = genCode(baseDir, map, constant,
							SERVICE, SERVICETMP, classList);
					// -----------------------------4
					// controller----------------------------------------------
					classList.add(beanClass);
					classList.add(ServiceClass);
					genCode(baseDir, map, constant, CONTROLLER, CONTROLLERTMP,
							classList);

					// --------------------------------------------5.VIEW----------------------------------------------------------------
					// TODO GEN HTML
				} else if (url.contains(CONSTANT)) {
					// properties 常量
					String cons = baseDir + File.separator + CONSTANT;
					genDir(cons);
					@SuppressWarnings("unchecked")
					List<Property> list = (List<Property>) constant.getList();
					Properties p = new Properties();
					for (Property pro : list) {
						p.put(constant.getPack() + DOT
								+ constant.getClassName() + DOT
								+ pro.getProperty(), pro.getValue());
					}
					File constantFile = new File(cons + File.separator
							+ constant.getClassName() + DOT + PROPERTITES);
					OutputStream os = new FileOutputStream(constantFile);
					p.store(os, constant.getClassName() + " property");
					os.flush();
					os.close();
				}
			}
		}
	}

	public static void genFromFile(String file)
			throws ParserConfigurationException, SAXException, IOException,
			TemplateException {
		File f = new File(file);
		genBean(f.toURI().toString());
	}

	/**@deprecated
	 * 首字母大写
	 * StringUtil.firstUpper(String str);
	 * @param str
	 * @return
	 */
	public static String firstUpper(String str) {
		char[] cs = str.toCharArray();
		if (Character.isLowerCase(cs[0])) {
			cs[0] -= 32;
		}
		return new String(cs);
	}

	/**@deprecated
	 * 首字母大写
	 * @see StringUtil.firstlower(String str);
	 * @param str
	 * @return
	 */
	public static String firstlower(String str) {
		char[] cs = str.toCharArray();
		if (Character.isUpperCase(cs[0])) {
			cs[0] += 32;
		}
		return new String(cs);
	}

	/**
	 * 文件夹生成
	 * 
	 * @param dir
	 *            文件夹
	 */
	private static void genDir(String dir) {
		if (dir != null && !"".equals(dir.trim())) {
			File f = new File(dir);
			if (!f.exists()) {
				f.mkdirs();
			}
		}
	}

	/**
	 * 根据模板生成文件
	 * 
	 * @param map
	 *            数据 model
	 * @param tmpName
	 *            模板名称
	 * @param f
	 *            要生成的文件
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void genSource(Map<String, Object> map, String tmpName, File f)
			throws IOException, TemplateException {
		Template template = getTemplate(tmpName);
		FileWriter writer = new FileWriter(f);
		template.process(map, writer);
		writer.flush();
		writer.close();
	}

	/**
	 * 文件拷贝
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public static void copy(String fileName) throws IOException {
		String dir = System.getProperty("user.dir");
		InputStream stream = GenTest.class.getClassLoader()
				.getResourceAsStream(fileName);
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(dir + File.separator + fileName));
		BufferedInputStream bis = new BufferedInputStream(stream);
		int len = 0;
		byte[] buf = new byte[2048];
		while ((len = bis.read(buf)) != -1) {
			bos.write(buf, 0, len);
		}
		bos.flush();
		bos.close();
		bis.close();
		stream.close();
	}

	/**
	 * 用来生产dao或 service或 constroller
	 * 
	 * @param baseDir
	 * @param map
	 * @param constant
	 * @param layer
	 *            DAO/SERVICE/CONTROLLER
	 * @param tmp
	 *            模板
	 * @param list
	 *            importList
	 * @return 生成的类完全名
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String genCode(String baseDir, Map<String, Object> map,
			AbstractBean constant, String layer, String tmp, List<String> list)
			throws IOException, TemplateException {
		String dir = baseDir + File.separator + layer;
		genDir(dir);
		File file = new File(dir + File.separator + constant.getClassName()
				+ firstUpper(layer) + DOT + JAVA);
		map.put(PACKAGE,
				dir.replaceAll(File.separator + File.separator, UNBIAS + DOT));
		map.put(IMPORT, list);
		genSource(map, tmp, file);
		list.clear();
		return dir.replaceAll(File.separator + File.separator, UNBIAS + DOT)
				+ DOT + constant.getClassName() + firstUpper(layer);
	}

	/**
	 * 驼峰书写
	 * @see StringUtil.camel(String str)
	 * @deprecated
	 * @param str
	 *            abc_abc→AbcAbc
	 * @return
	 */
	public static String camel(String str) {
		str = str.toLowerCase();
		if (str.contains("_")) {
			String[] split = str.split("_");
			StringBuilder sb = new StringBuilder();
			for (String string : split) {
				sb.append(firstUpper(string));
			}
			return sb.toString();
		}
		return firstUpper(str);
	}

	public static void getByBean(AbstractBean constant) throws IOException,
			TemplateException {
		// ----------------------1.bean--------------------------
		String dir = constant.getPack().replaceAll(UNBIAS + DOT,
				File.separator + File.separator);
		String[] split = constant.getPack().split("\\.");
		String baseDir = dir.substring(0, dir.lastIndexOf(File.separator));
		String beanDir = null;
		beanDir = baseDir + File.separator
				+ constant.getPack().split("\\.")[split.length - 1];

		genDir(beanDir);
		String filePath = beanDir + File.separator + constant.getClassName()
				+ DOT + JAVA;
		File f = new File(filePath);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PACKAGE, constant.getPack());
		map.put(CLASSNAME, constant.getClassName());
		map.put(PROPERTITES, constant.getList());
		map.put(IMPORT, constant.getImports());
		genSource(map, BEANTMP, f);
		// --------------------------2. dao-------------------

		List<String> classList = new ArrayList<String>();
		String beanClass = constant.getPack() + DOT + constant.getClassName();
		classList.add(beanClass);
		String daoClass = genCode(baseDir, map, constant, DAO, DAOTMP,
				classList);

		// --------------------------3. service--------------------
		classList.add(beanClass);
		classList.add(daoClass);
		String ServiceClass = genCode(baseDir, map, constant, SERVICE,
				SERVICETMP, classList);
		// -----------------------------4
		// controller----------------------------------------------
		classList.add(beanClass);
		classList.add(ServiceClass);
		genCode(baseDir, map, constant, CONTROLLER, CONTROLLERTMP, classList);

		// --------------------------------------------5.VIEW----------------------------------------------------------------
		// TODO GEN HTML
	}
	public static  void genWithConstant(AbstractBean constant) throws IOException, TemplateException{
		String dir = constant.getPack().replaceAll(UNBIAS + DOT,
				File.separator + File.separator);
//		String[] split = constant.getPack().split("\\.");
		String baseDir = dir.substring(0,
				dir.lastIndexOf(File.separator));
		String beanDir = null;
		beanDir = baseDir + File.separator + CONSTANT;
		
		genDir(beanDir);
		String filePath = beanDir + File.separator
				+ constant.getClassName() + DOT + JAVA;
		File f = new File(filePath);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PACKAGE, constant.getPack());
		map.put(CLASSNAME, constant.getClassName());
		map.put(PROPERTITES, constant.getList());
		map.put(IMPORT, constant.getImports());
		genSource(map, CONSTANTTMP, f);
			// properties 常量
		String cons = baseDir + File.separator + CONSTANT;
		genDir(cons);
		@SuppressWarnings("unchecked")
		List<Property> list = (List<Property>) constant.getList();
		Properties p = new Properties();
		for (Property pro : list) {
			p.put(constant.getPack() + DOT
					+ constant.getClassName() + DOT
					+ pro.getProperty(), pro.getValue());
		}
		File constantFile = new File(cons + File.separator
				+ constant.getClassName() + DOT + PROPERTITES);
		OutputStream os = new FileOutputStream(constantFile);
		p.store(os, constant.getComment() + " property");
		os.flush();
		os.close();
	}

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException, TemplateException {
		if (args != null && args.length > 0) {
			for (String str : args) {
				if (str.endsWith(".xml")) {
					genFromFile(str);
				}
			}
		} else {
			genBean(GenTest.class.getClassLoader().getResource("bean.xml")
					.toString());
			genBean(GenTest.class.getClassLoader().getResource("constant.xml")
					.toString());
			copy("bean.xml");
			copy("constant.xml");
		}

	}
}