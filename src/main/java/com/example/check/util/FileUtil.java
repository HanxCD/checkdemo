package com.example.check.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.check.domain.CuZteCscf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.example.check.constants.CommonContants;
import com.example.check.domain.CuHwCscf;

public class FileUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	@SuppressWarnings({ "resource", "rawtypes", "unchecked" })
	public static <T> List<T> parseFile(String type ,Map<String,String> map,File file, Map<String,String> resultMap){
		List list = new ArrayList();
		
		if(!file.exists()){
			logger.info("该文件"+file.getPath()+"不存在！");
			return null;
		}
		
		//文件名
		String fileName = file.getName();
		logger.info("=============文件名:"+fileName);
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(fis,"GBK"); // 建立一个输入流对象reader 
			BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
			String line = "";  
	        while ((line = br.readLine().trim()) != null) {
	        	 if(line.matches("^共有(\\d+)个报告$") || line.startsWith("命令执行成功")){
	        		 break;
	        	 }
	        	 if(StringUtils.isEmpty(line)) continue;
	        	 if("cu_hw_cscf".equals(type)){
	        		 String afterLine = line.replaceAll("\\s+", " ");
		        	 String[] array = afterLine.split(" ");
	        		 if(array.length == CommonContants.hwCscfCount){
		        		 parseLine(array,map, resultMap);
		        		 String json = JSON.toJSONString(resultMap);
		        		 logger.info("json:"+json);
		        		 CuHwCscf cuHwCscf = JSON.parseObject(json, CuHwCscf.class);
		        		 list.add(cuHwCscf);
		        	 }
	        	 }
	        	 if("cu_zte_cscf".equals(type)){
	        		 String afterLine = line.replaceAll("\\s+", " ");
		        	 String[] array = afterLine.split(" ");
		        	 if(fileName.contains("ADJHOST")){
						 if(array.length == CommonContants.zteAdjhostCount){
							 String classify = array[7];
				        	 if(!"S-CSCF".equals(classify)){
				        		 continue;
				        	 }
							 parseLine(array,map, resultMap);
							 String json = JSON.toJSONString(resultMap);
							 logger.info("json:"+json);
							 CuZteCscf cuZteCscf = JSON.parseObject(json, CuZteCscf.class);
							 list.add(cuZteCscf);
						 }
		        	 }else{
		        		 if(array.length == CommonContants.ztePoolCount){
							 parseLine(array,map, resultMap);
							 String json = JSON.toJSONString(resultMap);
							 logger.info("json:"+json);
							 CuZteCscf cuZteCscf = JSON.parseObject(json, CuZteCscf.class);
							 list.add(cuZteCscf);
						 } 
		        	 }
					 
				 }
	        }
		} catch (FileNotFoundException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		logger.info("parseFile end:"+list.size());
		return list;
	}

	/**
	 * 处理每行的信息，获取最新的数据
	 * @param array
	 * @param resultMap 
	 * @param resultMap
	 * @param resultMap2 
	 */
	private static Map<String,String> parseLine(String[] array, Map<String, String> map, Map<String, String> resultMap) {
		for(String key : map.keySet()){
			String sindex = map.get(key);
			int index = Integer.parseInt(sindex);
			String value = array[index];
			resultMap.put(key, value);
		}
		return resultMap;
	}

	/**
     * 复制文件
     * @param fromFile
     * @param toFile
     * <br/>
     * 2016年12月19日  下午3:31:50
     * @throws IOException 
     */
    public static void copyFile(File fromFile,File toFile) throws IOException{
        FileInputStream ins = new FileInputStream(fromFile);
        FileOutputStream out = new FileOutputStream(toFile);
        byte[] b = new byte[1024];
        int n=0;
        while((n=ins.read(b))!=-1){
            out.write(b, 0, n);
        }
        
        ins.close();
        out.close();
    }
}
