package com.yang.core;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;

import com.yang.generator.GenTest;
/**
 *javaBean ∑¥…‰π§æﬂ¿∏ 
 *
 */
public class BeanUtils {

	public static void map2Bean(Object obj,Map<String,Object> map){
		if(map!=null&&!map.isEmpty()){
			for (Entry<String, Object> ent : map.entrySet()) {
				if(obj!=null){
					try {
						if(ent.getValue()!=null){
							obj.getClass().getMethod("set"+GenTest.firstUpper(ent.getKey()),ent.getValue().getClass()).invoke(obj, ent.getValue());							
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
				}
			}
		}
	} 
}
