package com.yang.stock;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.yang.stock.bean.Stocks;

public class Stock {
	// http://stockpage.10jqka.com.cn/spService/601818/Header/realPlate
	// http://stockpage.10jqka.com.cn/spService/code/Header/realPlate
	public  static void test() throws IOException, ParserConfigurationException,
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
//		TreeSet<Stocks> set =new TreeSet<Stocks>();
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
			System.err.println(st);
//			set.add(st);
//			if(set.size()>100){
//				Thread.yield();
//				}
			new Thread(new MyWriter(st,"601818",null)).start();
		}
	}
	static class MyWriter implements Runnable{

		private Stocks st;
		private String stockCode;
		private TreeSet<Stocks> set;
		
		public MyWriter(Stocks st,String stockCode, TreeSet<Stocks> set) {
			super();
			this.st = st;
			this.stockCode=stockCode;
		}

		public void run() {
			File file=new File("d:/"+stockCode+".txt");
			try {
				BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file,true));
//				Iterator<Stocks> iterator = set.iterator();
				bos.write(st.toString().getBytes("utf-8"));
				bos.write("\n".getBytes());
//				while(iterator.hasNext()){
//					st=iterator.next();
//				}
				bos.flush();
				bos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	static TimerTask task=new TimerTask() {
		public void run() {
			try {
				test();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
		}
	};
////	@TEST
	public static void startTask(StockTimer timer){
//		stocktimer timer=new stocktimer();
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
//	public static void stoptask(stocktimer timer){
//		if(timer==null){
//			timer=new stocktimer();
//		}
//		if(timer.isactive()){
//			//timer.schedule(task, 1, 5000);
//			timer.setactive(false);
//			timer.cancel();
//			timer=new stocktimer();
//		}else{
//			//timer.schedule(task, 1, 5000);
//		}
//	}
	
	public static void main(String[] args) {
		startTask(null);
	}

}
