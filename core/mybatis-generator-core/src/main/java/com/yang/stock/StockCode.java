package com.yang.stock;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class StockCode {

	public List<A> getAllCode() throws JAXBException {
		List<A> list=new ArrayList<A>();
		StockCode stockCode = new StockCode();
		stockCode.getStockCode("aaa.xml", list);
		stockCode.getStockCode("bbb.xml", list);
		stockCode.getStockCode("ccc.xml", list);
		System.err.println(list.size());
		return list;
	}
	public void getStockCode(String fileName,List<A> list) throws JAXBException{
		JAXBContext ctx = JAXBContext.newInstance(UL.class);
		Unmarshaller unmarshaller = ctx.createUnmarshaller();
		URL url = StockCode.class.getClassLoader().getResource(fileName);
		Object object = unmarshaller.unmarshal(url);
		if(object instanceof UL){
			UL u=(UL) object;
			List<LI> liA = u.getList();
			for (LI li2 : liA) {
				list.add(li2.getA());
//				System.err.print(li2.getA().getValue().split(" ")[1]+"\t");
//				System.err.println(li2.getA().getValue().split(" ")[0]);
			}
		}
	}
}
