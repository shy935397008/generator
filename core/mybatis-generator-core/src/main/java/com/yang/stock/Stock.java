package com.yang.stock;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.yang.stock.bean.Stocks;

public class Stock {
	// http://stockpage.10jqka.com.cn/spService/601818/Header/realPlate
	// http://stockpage.10jqka.com.cn/spService/code/Header/realPlate
	@Test
	public void test() throws IOException, ParserConfigurationException,
			SAXException {
		URL url = new URL(
				"http://stockpage.10jqka.com.cn/spService/601818/Header/realPlate");
		URLConnection connection = url.openConnection();
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,
				"utf-8"));
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
		DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = fac.newDocumentBuilder();
		ByteArrayInputStream bis = new ByteArrayInputStream(
				table.getBytes("utf-8"));
		Document doc = builder.parse(bis);
		NodeList node = doc.getElementsByTagName("tr");
		for (int i = 0; i < node.getLength(); i++) {
			Node item = node.item(i);
			NodeList childNodes = item.getChildNodes();
			Stocks st = new Stocks();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
			st.setDate(format.format(date)
					+ childNodes.item(0).getTextContent());
			st.setName(childNodes.item(1).getTextContent());
			st.setOperation(childNodes.item(2).getTextContent());
			st.setCode(childNodes.item(1).getFirstChild().getAttributes()
					.item(0).getTextContent().replaceAll("/", ""));
//			System.err.println(st);
		}
	}
	static TimerTask task=new TimerTask() {
		@Override
		public void run() {
			System.err.println("==============");
		}
	};
//	@Test
	public static void startTask(StockTimer timer){
//		StockTimer timer=new StockTimer();
		if(timer==null){
			timer=new StockTimer();
		}
		if(!timer.isActive()){
			timer.schedule(task, 1, 5000);
		}else{
			timer.cancel();
			timer=new StockTimer();
			timer.schedule(task, 1, 5000);
		}
	}
	public static void stopTask(StockTimer timer){
		if(timer==null){
			timer=new StockTimer();
		}
		if(timer.isActive()){
			//timer.schedule(task, 1, 5000);
			timer.setActive(false);
			timer.cancel();
			timer=new StockTimer();
		}else{
			//timer.schedule(task, 1, 5000);
		}
	}
	
	public static void main(String[] args) {
		startTask(null);
	}

}
