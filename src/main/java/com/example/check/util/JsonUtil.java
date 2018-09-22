package com.example.check.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

	public static Map jsonArrayToMap(JSONArray array){
		Map map = new HashMap();
		for(int i =0;i<array.size();i++){
			JSONObject o = array.getJSONObject(i);
			String key = o.getString("name");
			String value = o.getString("value");
			map.put(key, value);
		}
		return map;
	}
	
	public static void main(String[] args){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()));
	}
}