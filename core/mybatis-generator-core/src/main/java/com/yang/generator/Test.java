package com.yang.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Test {
	private static final String CONSTANT = "constant";
	private static final String BEAN = "bean";
	private static final String CLASSNAME = "className";
	private static final String PROPERTITY = "propertity";
	private static final String VALUE = "value";

	public static void parseBean(List<Constant> listC, String fileName)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document document = builder.parse(Test.class.getClassLoader()
				.getResourceAsStream(fileName));
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
				String pack = string.substring(0, string.lastIndexOf('.'));
				String className = string
						.substring(string.lastIndexOf('.') + 1);
				con.setClassName(className);
				con.setPack(pack);
			}
			List<Propertity> listP = new ArrayList<Propertity>();
			NodeList list = item.getChildNodes();
			for (int j = 0; j < list.getLength(); j++) {
				Node node = list.item(j);
				Propertity p = new Propertity();
				if (node != null) {
					NamedNodeMap attributes = node.getAttributes();
					if (attributes != null) {
						p.setClassName(attributes.getNamedItem(CLASSNAME)
								.getTextContent());
						p.setPropertity(attributes.getNamedItem(PROPERTITY)
								.getTextContent());
						if (fileName.contains(CONSTANT)) {
							p.setValue(attributes.getNamedItem(VALUE)
									.getTextContent());
						}
						listP.add(p);
					}
				}
			}
			con.setList(listP);
			listC.add(con);
		}
	}

	public static Template getTemplate(String fileName) throws IOException {
		String file = Test.class.getClassLoader().getResource(fileName)
				.getFile().substring(1);
		String dir = file.substring(0, file.lastIndexOf("/"));
		Configuration conf = new Configuration();
		conf.setDirectoryForTemplateLoading(new File(dir));
		conf.setObjectWrapper(new DefaultObjectWrapper());
		return conf.getTemplate(fileName);
	}

	public static void genBean(String fileName, String tmpName)
			throws ParserConfigurationException, SAXException, IOException,
			TemplateException {
		List<Constant> listC = new ArrayList<Constant>();
		if (fileName.contains(CONSTANT)||fileName.contains(BEAN)) {
			parseBean(listC, fileName);
			for (Constant constant : listC) {
				String dir = constant.getPack().replaceAll(File.separator + ".",
						File.separator + File.separator);
				File dirs = new File(dir);
				if (!dirs.exists()) {
					dirs.mkdirs();
				}
				String filePath = dir + File.separator + constant.getClassName()
						+ ".java";
				File f = new File(filePath);
				Template template = getTemplate(tmpName);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("package", constant.getPack());
				map.put("className", constant.getClassName());
				map.put("propertites", constant.getList());
				FileWriter writer = new FileWriter(f);
				template.process(map, writer);
				writer.flush();
				writer.close();
			}
		}
	}

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException, TemplateException {
		genBean("constant.xml", "constant.ftl");
		genBean("bean.xml", "bean.ftl");
	}
}
