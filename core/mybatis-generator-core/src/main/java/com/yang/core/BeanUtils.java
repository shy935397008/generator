package com.yang.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;

/**
 *javaBean 反射工具栏 
 *
 */
public class BeanUtils {

	public static void map2Bean(Object obj,Map<String,Object> map){
		if(map!=null&&!map.isEmpty()){
			for (Entry<String, Object> ent : map.entrySet()) {
				if(obj!=null){
					try {
						if(ent.getValue()!=null){
							obj.getClass().getMethod("set"+StringUtil.firstUpper(ent.getKey()),ent.getValue().getClass()).invoke(obj, ent.getValue());							
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

	
	/**大量（>8000）生成时速度慢,少量是用此方法<br/>
	 * JavaBean工厂  
	 * @param clazz
	 * @param objs
	 * @return
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public static  <T> T beanFactory(Class<T> clazz,Object...objs) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
		if(objs==null||objs.length==0){
			return clazz.newInstance();
		}
		Constructor<?>[] constructors = clazz.getConstructors();
		for (Constructor<?> constructor : constructors) {
			Class<?>[] types = constructor.getParameterTypes();
			if((types.length-1)==objs.length){
				for (Class<?> cla : types) {
					if(!cla.equals(objs.getClass())){
						return null;
					}else{
						continue;
					}
				}
			}
			
			return (T) constructor.newInstance(objs);
		}
		return null;
	}
	public static Object beanFactory(String clazzName) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return  Class.forName(clazzName).newInstance();
	}
	
}
