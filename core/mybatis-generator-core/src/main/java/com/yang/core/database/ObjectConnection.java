package com.yang.core.database;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.yang.stock.bean.Stocks;

public class ObjectConnection implements ConnectionInterface {

	private String url;// "http://stockpage.10jqka.com.cn/spService/601818/Header/realPlate"

	public ObjectConnection() {
		super();
		url="http://stockpage.10jqka.com.cn/spService/601818/Header/realPlate";
	}

	public ObjectConnection(String url, String code) {
		super();
		this.url = url;
	}

	public ObjectConnection(String code) {
		super();
		this.url = "http://stockpage.10jqka.com.cn/spService/" + code
				+ "/Header/realPlate";
	}

	public Connection getConnection() {
		return null;
	}

	public Object getFromURL() {
		if (url == null || "".equals(url.trim())) {
			return null;
		} else {
			try {
				URL urls = new URL(url);
				URLConnection connection = urls.openConnection();
				InputStream is = connection.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, "utf-8"));
				String line = "";
				StringBuffer sb = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
				is.close();
				String[] split = sb.toString().split("\\|");
				String table = split[0];
				table = "<table>" + table + "</table>";
				System.err.println(table);
				DocumentBuilderFactory fac = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder = fac.newDocumentBuilder();
				ByteArrayInputStream bis = new ByteArrayInputStream(
						table.getBytes("utf-8"));
				Document doc = builder.parse(bis);
				NodeList node = doc.getElementsByTagName("tr");
				List<Stocks> list=new ArrayList<Stocks>();
				for (int i = 0; i < node.getLength(); i++) {
					Node item = node.item(i);
					NodeList childNodes = item.getChildNodes();
					Stocks st = new Stocks();
					Date date = new Date();
					SimpleDateFormat format = new SimpleDateFormat(
							"yyyy-MM-dd ");
					st.setDate(format.format(date)
							+ childNodes.item(0).getTextContent());
					st.setName(childNodes.item(1).getTextContent());
					st.setOperation(childNodes.item(2).getTextContent());
					st.setCode(childNodes.item(1).getFirstChild()
							.getAttributes().item(0).getTextContent()
							.replaceAll("/", ""));
					System.err.println(st);
					list.add(st);
				}
				return list;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (DOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
