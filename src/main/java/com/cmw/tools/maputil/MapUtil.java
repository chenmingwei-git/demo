package com.cmw.tools.maputil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class MapUtil {
	//Map key 大写转小写
	public static Map<String, Object> transformMapUpperCase(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<>();
 
		if (map == null || map.isEmpty()) {
			return resultMap;
		}
 
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			String newKey = key.toLowerCase();
//			newKey = newKey.replace("_", "");
 
			resultMap.put(newKey, map.get(key));
		}
 
		return resultMap;
	}
	
	//List<Map> key 大写转小写
	public static List<Map<String, Object>> transformListUpperCase(List<Map<String, Object>> list) {
		List<Map<String, Object>> resultList=new ArrayList<Map<String,Object>>();
		
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> resultMap = MapUtil.transformMapUpperCase(list.get(i));
			resultList.add(resultMap);
		}
 
		return resultList;
	}

   public static Map<String,Object> getModelAttriButeType(Object model) throws Exception{
        Field[] field = model.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
        Map<String,Object> map = new HashMap<String, Object>();
        for(int j=0 ; j<field.length ; j++){     //遍历所有属性
            String name = field[j].getName();    //获取属性的名字

            name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
            Method m = model.getClass().getMethod("get"+name);
            Object value =  m.invoke(model);    //调用getter方法获取属性值
            map.put(HumpToUnderline(name), value);
            if(value != null){

            }


        }
        return map;
    }

    /***
     * 驼峰命名转为下划线命名
     *
     * @param para
     *        驼峰命名的字符串
     */

    public static String HumpToUnderline(String para){
        para = para.substring(0,1).toLowerCase()+para.substring(1);
        StringBuilder sb=new StringBuilder(para);
        int temp=0;//定位
        for(int i=0;i<para.length();i++){
            if(Character.isUpperCase(para.charAt(i))){
                sb.insert(i+temp, "_");
                temp+=1;
            }
        }
        return sb.toString().toUpperCase();
    }
	
}
