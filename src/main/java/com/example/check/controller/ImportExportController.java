package com.example.check.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;

import com.alibaba.fastjson.JSONObject;
import com.example.check.constants.CommonContants;
import com.example.check.domain.CheckResult;
import com.example.check.domain.CheckResultDetail;
import com.example.check.domain.CheckResultDetailExample;
import com.example.check.domain.CuHwCscf;
import com.example.check.domain.CuHwCscfExample;
import com.example.check.domain.CuZteCscf;
import com.example.check.domain.CuZteCscfExample;
import com.example.check.domain.DeviceInfo;
import com.example.check.domain.StdCscf;
import com.example.check.domain.StdCscfExample;
import com.example.check.mapper.CheckResultDetailMapper;
import com.example.check.mapper.CheckResultMapper;
import com.example.check.mapper.CuHwCscfMapper;
import com.example.check.mapper.CuZteCscfMapper;
import com.example.check.mapper.DeviceInfoMapper;
import com.example.check.mapper.StdCscfMapper;
import com.example.check.util.ExcelUtil;
import com.example.check.util.FileUtil;

@RestController
@RequestMapping("/excel")
public class ImportExportController {
	
	private final static Logger logger = LoggerFactory.getLogger(DataHandlerController.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	@Autowired
	private StdCscfMapper stdCscfMapper;
	
	@Autowired
	private CuHwCscfMapper cuHwCscfMapper;

	@Autowired
	private CuZteCscfMapper cuZteCscfMapper;
	
	@Autowired
	private DeviceInfoMapper deviceInfoMapper;
	
	@Autowired
	private CheckResultMapper checkResultMapper;
	
	@Autowired
	private CheckResultDetailMapper checkResultDetailMapper;
	
	private final static String SUCCESS = "success";
	
	private final static String FAILURE = "failure";
	
	@Value("${uploadfile}")
	private String uploadfile;
	

	@SuppressWarnings("rawtypes")
	@RequestMapping("/import/{tableName}")
	public String importTable(@PathVariable("tableName")String tableName, HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("tableName="+tableName);
		JSONObject json = new JSONObject();
		json.put("state", SUCCESS);
		request.setCharacterEncoding("UTF-8");
		
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	    
	    /** 页面控件的文件流* */
        MultipartFile multipartFile = null;
        Map fileMap = multipartRequest.getFileMap();
        for (Iterator i = fileMap.keySet().iterator(); i.hasNext();) {
            Object obj = i.next();
            multipartFile=(MultipartFile) fileMap.get(obj);
        }
        
        String tempFilePath = "";
        if(multipartFile != null){
        	//获取文件名
	        String filename = multipartFile.getOriginalFilename();
	        logger.info("filename="+filename);
	        
	        //把文件存放到临时文件中
	        File uploadDir = new  File(uploadfile);
	        if(!uploadDir.exists()){
	        	uploadDir.mkdirs();
	        }
	        File dateUploadDir = new File(uploadfile+File.separator + new Date().getTime());
	        if(!dateUploadDir.exists()){
	        	dateUploadDir.mkdirs();
	        }
	        tempFilePath = dateUploadDir.getPath() +File.separator + filename;
	        logger.info("临时文件存放路径：tempFilePath="+tempFilePath);
	        File tempFile = new File(tempFilePath);
	        //将上传的文件写入新建的文件中
	        multipartFile.transferTo(tempFile);
	        
	        if("std_cscf".equals(tableName.split(",")[0])){
	        	//标准数据解析：解析excle表格的数据
	        	String message = stdExcelParse(tempFile,tableName);
	        	json.put("state", FAILURE);
    			json.put("message", message);
    			return json.toString();
	        }else if("cu_hw_cscf".equals(tableName.split(",")[0])){
	        	Map<String,String> map = CommonContants.hwCscf;
	        	//获取网元的基本信息
	        	String deviceName = filename.split("_")[0];
	        	logger.info("网元名称：deviceName="+deviceName);
	        	DeviceInfo deviceInfo = deviceInfoMapper.selectByDeviceName(deviceName);
	        	if(deviceInfo == null){
	        		logger.error("没有找到相关网元("+deviceName+")的信息。");
	        		throw new Exception("没有找到相关网元("+deviceName+")的信息。");
	        	}
	        	String province = deviceInfo.getProvince();
	        	String vendor = deviceInfo.getVendor();
	        	Map<String,String> resultMap = new HashMap<String,String>();
//	        	resultMap.put("province", province);
	        	resultMap.put("vendor", vendor);
	        	resultMap.put("DEVICENAME", deviceName);
	        	//先备份表
	        	logger.info("===============备份表 cu_hw_cscf start========================");
	        	backupTable("cu_hw_cscf");
	        	logger.info("===============备份表 cu_hw_cscf end =========================");
	        	
	        	//清空有关deviceName的信息
	        	logger.info("=========清空表cu_hw_cscf网元("+deviceName+") start============");
	        	CuHwCscfExample cuHwCscfExample = new CuHwCscfExample();
	        	cuHwCscfExample.createCriteria().andDeviceNameEqualTo(deviceName);
	    		int num = cuHwCscfMapper.deleteByExample(cuHwCscfExample);
	    		logger.info("=========清空表cu_hw_cscf网元("+deviceName+") end==============");
	    		logger.info("删除现网表cu_hw_cscf中的网元="+deviceName+"相关的"+num+"条记录。");
	    		
	    		//解析文件
	    		logger.info("==============="+filename+" parse start====================");
	    		List<T> rstList  = FileUtil.parseFile("cu_hw_cscf", map, tempFile, resultMap);
	        	logger.info("==============="+filename+" parse end======================");
	        	logger.info("解析文件"+filename+"中网元="+deviceName+"的信息，共"+rstList.size()+"条记录。");
	        	
	        	//批量插入
	        	logger.info("================batchImportList start======================");
	        	batchImportList("cu_hw_cscf",filename, rstList);
	        	logger.info("================batchImportList end========================");
	        	
	        	//批量更新省份
	        	batchUpdateProvince("cu_hw_cscf");
	        }else if("cu_zte_cscf".equals(tableName.split(",")[0])){
	        	//获取网元的基本信息
				String deviceName = filename.split("_")[0];
				logger.info("网元名称：deviceName="+deviceName);
				DeviceInfo deviceInfo = deviceInfoMapper.selectByDeviceName(deviceName);
				if(deviceInfo == null){
					logger.error("没有找到相关网元("+deviceName+")的信息。");
					throw new Exception("没有找到相关网元("+deviceName+")的信息。");
				}
				Map<String,String> map = new HashMap<String,String>();
	        	if(filename.contains("ADJHOST")){
	        		map = CommonContants.zteAdjhost;
	        	}else{
	        		map = CommonContants.ztePool;
	        	}
				
				String province = deviceInfo.getProvince();
				String vendor = deviceInfo.getVendor();
				Map<String,String> resultMap = new HashMap<String,String>();
//				resultMap.put("province", province);
				resultMap.put("vendor", vendor);
				resultMap.put("deviceName", deviceName);

				if(filename.contains("ADJHOST")){
					//先备份表
					logger.info("===============备份表 cu_zte_cscf start========================");
					backupTable("cu_zte_cscf");
					logger.info("===============备份表 cu_hw_cscf end =========================");
					
					//清空有关deviceName的信息
					logger.info("=========清空表cu_zte_cscf网元("+deviceName+") start============");
					CuZteCscfExample cuZteCscfExample = new CuZteCscfExample();
					cuZteCscfExample.createCriteria().andDeviceNameEqualTo(deviceName);
					int num = cuZteCscfMapper.deleteByExample(cuZteCscfExample);
					logger.info("=========清空表cu_zte_cscf网元("+deviceName+") end==============");
					logger.info("删除现网表cu_zte_cscf中的网元="+deviceName+"相关的"+num+"条记录。");
				}
				

				//解析文件
				logger.info("==============="+filename+" parse start====================");
				List<T> rstList = FileUtil.parseFile("cu_zte_cscf", map, tempFile, resultMap);
				logger.info("==============="+filename+" parse end======================");
				logger.info("解析文件"+filename+"中网元="+deviceName+"的信息，共"+rstList.size()+"条记录。");
				
				//批量插入
				if(filename.contains("ADJHOST")){
					logger.info("================batchImportList start======================");
					batchImportList("cu_zte_cscf",filename, rstList);
					logger.info("================batchImportList end========================");
	        	}else{
	        		logger.info("================batchUpdateList start======================");
	        		batchUpdateList("cu_zte_cscf",filename, rstList);
	        		logger.info("================batchUpdateList start======================");
	        	}
				//批量更新省份
	        	batchUpdateProvince("cu_zte_cscf");
			}
        }
		return json.toString();
	}
	
	private String stdExcelParse(File tempFile,String tableName){
		StringBuffer sb = new StringBuffer();
		String tempFilePath = tempFile.getPath();
		try{
			//根据file得到Workbook,主要是要根据这个对象获取,传过来的excel有几个sheet页  
	        Workbook hssfWorkbook = ExcelUtil.getWorkBook(tempFile); 
	        int countSheet = hssfWorkbook.getNumberOfSheets();
	        logger.info("共有="+countSheet+"张Sheet表单");
	        ImportParams params = new ImportParams();
	        //清空Std_cscf表中的信息
	        this.deleteAll(tableName.split(",")[0]);
	        for (int numSheet = 0; numSheet < countSheet; numSheet++) {  
	        	//表头在第几行 
	        	params.setTitleRows(0);
	    		params.setHeadRows(1); 
	            //第几个sheet页  
	            params.setStartSheetIndex(numSheet);  
	            //验证数据  
	            //params.setNeedVerfiy(true);  
	            
	            if("std_cscf".equals(tableName.split(",")[0])){
	            	//数据校验：提取时对标准数据进行校准。对于每一个表格，把“sheet名”与“省份”进行对比，保证二者一致，如果不一致，可以生成异常数据。
	            	//数据规整：“能力集取值”如果开头为0，去掉0。
	            	String sheetName = hssfWorkbook.getSheetName(numSheet);
	            	sb.append(this.resultVerfiy(tableName, tempFilePath, params, sheetName));
	            	if(StringUtils.isNotEmpty(sb.toString())){
	            		logger.info("验证结果"+sb.toString());
	            	}
	            	
	            }
	        }
		} catch (Exception e) {
			logger.info(e.getMessage());
		} 
        return sb.toString();
	}
	
	private String resultVerfiy(String tableName, String file, ImportParams params,String sheetName){
		StringBuffer sb = new StringBuffer();
		List<StdCscf> list = new ArrayList<StdCscf>();
		int i=0;
		if("std_cscf".equals(tableName.split(",")[0])){
			List<StdCscf> resultList = ExcelUtil.importExcel(file, params, StdCscf.class);
			for(StdCscf stdCscf:  resultList){
				i++;
				String province = stdCscf.getProvince();
				String csValue = stdCscf.getCsValue();
				if(!sheetName.equals(province)){
					sb.append("第"+i+"行，“sheet名("+sheetName+")”与“省份("+province+")”进行对比，不一致\n");
				}else{
					csValue = csValue.startsWith("0")?csValue.substring(1):csValue;
					stdCscf.setCsValue(csValue);
					list.add(stdCscf);
				}
			}
			if(StringUtils.isEmpty(sb.toString()) && CollectionUtils.isNotEmpty(list)){
				batchImprt(tableName.split(",")[0], sheetName ,list);
			}
		}
		return sb.toString();
	}
	
	private void deleteAll(String type) {
		int num = 0;
		if("std_cscf".equals(type)){
			num = stdCscfMapper.deleteAll();
			logger.info(type+"表，删除"+num+"记录成功。");
		}
	}
	
	private void batchImprt(String type, String sheetName, List<StdCscf> list) {
		int num = 0;
		if("std_cscf".equals(type)){
			num = stdCscfMapper.batchImport(list);
			logger.info(type+"表，sheet("+sheetName+")中的记录插入成功：num="+num+"条");
		}
	}
	
	/**
	 * 备份表
	 * 1、先判断newTableName是否存在
	 * 2、若存在，则先删除newTableName,否则先创建表结果，更新记录
	 * @param type
	 */
	private void backupTable(String type) {
		if("cu_hw_cscf".equals(type)){
			String date = sdf.format(new Date());
			String tableName = "cu_hw_cscf";
			String newTableName = tableName + "_" +date;
			if(cuHwCscfMapper.isTableExist(newTableName) > 0){
				cuHwCscfMapper.dropTable(newTableName);
			}
			int create = cuHwCscfMapper.createTable(tableName, newTableName);
			logger.info("创建表"+newTableName+"是否成功："+create);
			int copy = cuHwCscfMapper.copyTable(tableName, newTableName);
			logger.info("copy表"+newTableName+"记录是否成功："+copy);
		}else if ("cu_zte_cscf".equals(type)){
			String date = sdf.format(new Date());
			String tableName = "cu_zte_cscf";
			String newTableName = tableName + "_" +date;
			if(cuZteCscfMapper.isTableExist(newTableName) > 0){
				cuZteCscfMapper.dropTable(newTableName);
			}
			int create = cuZteCscfMapper.createTable(tableName, newTableName);
			logger.info("创建表"+newTableName+"是否成功："+create);
			int copy = cuZteCscfMapper.copyTable(tableName, newTableName);
			logger.info("copy表"+newTableName+"记录是否成功："+copy);
		}
	}
	
	/**
	 * 现网文件批量更新
	 * @param type
	 * @param fileName
	 * @param list
	 */
	private void batchUpdateList(String tableName, String fileName, List<T> list){
		int num = 0;
		if("cu_zte_cscf".equals(tableName)){
			num = cuZteCscfMapper.batchUpdate(list);
			logger.info(tableName+"表，文件("+fileName+")中的更新成功：num="+num+"条");
		}
	}

	/**
	 * 现网文件批量添加
	 * @param type
	 * @param fileName
	 * @param list
	 */
	private void batchImportList(String tableName, String fileName, List<T> list){
		int num = 0;
		if("cu_hw_cscf".equals(tableName)){
			num = cuHwCscfMapper.batchImport(list);
			logger.info(tableName+"表，文件("+fileName+")中的记录更新成功：num="+num+"条");
		}else if("cu_zte_cscf".equals(tableName)){
			num = cuZteCscfMapper.batchImport(list);
			logger.info(tableName+"表，文件("+fileName+")中的记录插入成功：num="+num+"条");
		}
	}
	
	/**
	 * 批量更新省份
	 * @param type
	 * @param fileName
	 * @param list
	 */
	private void batchUpdateProvince(String tableName){
		int num = 0;
		StdCscfExample stdCscfExample = new StdCscfExample();
		List<StdCscf> stdList = stdCscfMapper.selectByExample(stdCscfExample);
		if(CollectionUtils.isNotEmpty(stdList)){
			if("cu_hw_cscf".equals(tableName)){
				num = cuHwCscfMapper.batchUpdateProvince(stdList);
			}else if("cu_zte_cscf".equals(tableName)){
				num = cuZteCscfMapper.batchUpdateProvince(stdList);
			}
		}
		logger.info(tableName+"表记录更新成功：num="+num+"条");
	}
	
	@RequestMapping("/export/{vendor}/{tableName}/{id}")
	public void exportTable(@PathVariable("vendor")String vendor,@PathVariable("tableName")String tableName,
			@PathVariable("id")String id, String pageSize,String pageNumber,HttpServletResponse response){
		logger.info("tableName="+tableName);
		try{
			 List<ExcelExportEntity> colList = new ArrayList<ExcelExportEntity>();
			 
			 /**
			  * 表头
			  */
			 getColHeader(colList,vendor);
			 logger.info("headList:"+JSONObject.toJSONString(colList));
			 
			 /**
			  * 获取数据信息
			  */
			 List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			 list.addAll(this.checkDetail(id,vendor));
			 logger.info("list.size:"+list.size());
			 logger.info("list:"+JSONObject.toJSONString(list));
			 
			 /**
			  * 导出
			  */
			 String fileName = "IMS组自研团队业务-ICSCF能力集";
			 ExcelUtil.exportExcel(list, "", "结果输出", colList, fileName+".xlsx", response);
			 
			 
		}catch(Exception e){
			logger.error("exportTable出错："+e.getMessage());
		}
	}	
	
	private void getColHeader(List<ExcelExportEntity> colList,String vendor){
		ExcelExportEntity neColGroup = new ExcelExportEntity("网元信息", "ne");
        List<ExcelExportEntity> neColList = new ArrayList<ExcelExportEntity>();
        neColList.add(new ExcelExportEntity("省份", "province"));
        neColList.add(new ExcelExportEntity("网元", "deviceName"));
        neColList.add(new ExcelExportEntity("厂家", "vendor"));
        neColGroup.setList(neColList);
        colList.add(neColGroup);
        
        ExcelExportEntity checkColGroup = new ExcelExportEntity("核查结果", "check");
        List<ExcelExportEntity> checkColList = new ArrayList<ExcelExportEntity>();
        checkColList.add(new ExcelExportEntity("核查结果", "result"));
        checkColList.add(new ExcelExportEntity("结果备注", "remark"));
        checkColGroup.setList(checkColList);
        colList.add(checkColGroup);
        
        ExcelExportEntity stdColGroup = new ExcelExportEntity("标准数据", "std");
        List<ExcelExportEntity> stdColList = new ArrayList<ExcelExportEntity>();
        stdColList.add(new ExcelExportEntity("能力集取值", "scsValue"));
        stdColList.add(new ExcelExportEntity("S-CSCF Pool域名", "poolValue"));
        stdColList.add(new ExcelExportEntity("省份", "sprovince"));
        stdColList.add(new ExcelExportEntity("备注", "sremark"));
        stdColGroup.setList(stdColList);
        colList.add(stdColGroup);
        
        ExcelExportEntity cuColGroup = new ExcelExportEntity("现网数据", "cu");
        List<ExcelExportEntity> cuColList = new ArrayList<ExcelExportEntity>();
        if("hw".equals(vendor.toLowerCase())){
        	cuColList.add(new ExcelExportEntity("S-CSCF的能力1", "acsValue"));
            cuColList.add(new ExcelExportEntity("S-CSCF的能力1", "sipuri"));
        }else{
        	cuColList.add(new ExcelExportEntity("能力集", "acsValue"));
            cuColList.add(new ExcelExportEntity("邻接主机名称", "hostGroupName"));
            cuColList.add(new ExcelExportEntity("S-CSCF池编号", "poolId"));
            cuColList.add(new ExcelExportEntity("心跳模式", "heartBeat"));
            cuColList.add(new ExcelExportEntity("\"不可用\"状态下心跳间隔(秒)", "unusable"));
            cuColList.add(new ExcelExportEntity("\"可用\"与\"未检测\"状态下心跳间隔(秒)", "usable"));
            cuColList.add(new ExcelExportEntity("不可用的失败检测次数", "unusableFailed"));
            cuColList.add(new ExcelExportEntity("可用的成功检测次数", "usableSuccess"));
        }
        cuColGroup.setList(cuColList);
        colList.add(cuColGroup);
	}
	
	private List<Map<String,Object>> checkDetail(String id, String vendor){
		logger.info("id="+id);
		JSONObject json = new JSONObject();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			//type为空
			List<CheckResultDetail> lossList = getCheckResultDetail("2",id);
			list.addAll(getCheckDetail("少做",id, lossList,vendor));
			
			List<CheckResultDetail> redunList = getCheckResultDetail("3",id);
			list.addAll(getCheckDetail("多做",id, redunList,vendor));
			
			List<CheckResultDetail> wrongList = getCheckResultDetail("1",id);
			list.addAll(getCheckDetail("错做",id, wrongList,vendor));
			
			//List<CheckResultDetail> correctList = getCheckResultDetail("0",id);
			//list.addAll(getCheckDetail("正确",id, correctList,vendor));
		}catch(Exception e){
			logger.error("detailTable出错："+e.getMessage());
		}
		logger.info("结果："+json.toString());
		return list;
	}

	@RequestMapping("/export/{tableName}")
	public void exportTable(@PathVariable("tableName")String tableName,HttpServletResponse response){
		logger.info("tableName="+tableName);
		try{
			if("std_cscf".equals(tableName.split(",")[0])){
				String sheetName = "ICSCF标准表";
				StdCscfExample stdCscfExample = new StdCscfExample();
				List<StdCscf> list =stdCscfMapper.selectByExample(stdCscfExample);
				ExcelUtil.exportExcel(list, sheetName, sheetName, StdCscf.class, sheetName+".xlsx", response);
			}else if("cu_hw_cscf".equals(tableName.split(",")[0])){
				String sheetName = "ICSCF华为现网表";
				CuHwCscfExample cuHwCscfExample = new CuHwCscfExample();
				List<CuHwCscf> list = cuHwCscfMapper.selectByExample(cuHwCscfExample);
				ExcelUtil.exportExcel(list, sheetName, sheetName, CuHwCscf.class, sheetName+".xlsx", response);
			}else if("cu_zte_cscf".equals(tableName.split(",")[0])){
				String sheetName = "ICSCF中兴现网表";
				CuZteCscfExample cuZteCscfExample = new CuZteCscfExample();
				List<CuZteCscf> list = cuZteCscfMapper.selectByExample(cuZteCscfExample);
				ExcelUtil.exportExcel(list, sheetName, sheetName, CuZteCscf.class, sheetName+".xlsx", response);
			}
		}catch(Exception e){
			logger.error("exportTable出错："+e.getMessage());
		}
	}
	
	private List<CheckResultDetail> getCheckResultDetail(String type, String id) {
		List<CheckResultDetail> detailList = new ArrayList<CheckResultDetail>();
		CheckResultDetailExample  checkResultDetailExample = new CheckResultDetailExample();
		if("0".equals(type)){
			checkResultDetailExample.createCriteria().andCheckresultidEqualTo(Integer.parseInt(id)).andIscorrectEqualTo(true);
			detailList = checkResultDetailMapper.selectByExample(checkResultDetailExample);
		}else if("1".equals(type)){
			checkResultDetailExample.createCriteria().andCheckresultidEqualTo(Integer.parseInt(id)).andIswrongEqualTo(true);
			detailList = checkResultDetailMapper.selectByExample(checkResultDetailExample);
		}else if("2".equals(type)){
			checkResultDetailExample.createCriteria().andCheckresultidEqualTo(Integer.parseInt(id)).andIslossEqualTo(true);
			detailList = checkResultDetailMapper.selectByExample(checkResultDetailExample);
		}else if("3".equals(type)){
			checkResultDetailExample.createCriteria().andCheckresultidEqualTo(Integer.parseInt(id)).andIsredundantEqualTo(true);
			detailList = checkResultDetailMapper.selectByExample(checkResultDetailExample);
		}
		checkResultDetailExample.clear();
		return detailList;
	}
	
	private List<Map<String, Object>> getCheckDetail(String result, String id, List<CheckResultDetail> detailList,String vendor) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(CollectionUtils.isEmpty(detailList)){
			return list;
		}
		
		CheckResult checkResult =checkResultMapper.selectByPrimaryKey(Integer.parseInt(id));
		String deviceName = checkResult.getDeviceName();
		DeviceInfo deviceInfo = deviceInfoMapper.selectByDeviceName(deviceName);
		
		logger.info("getCheckDetail:detailList.size="+detailList.size());
		
		for(CheckResultDetail checkResultDetail : detailList){
			Map<String, Object> valMap = new HashMap<String, Object>();
			
			List<Map<String, String>> neList = new ArrayList<Map<String,String>>();
			Map<String,String> neMap = new HashMap<String,String>();
			neMap.put("deviceName", deviceInfo.getDeviceName());
			neMap.put("vendor", deviceInfo.getVendor());
			neMap.put("province", deviceInfo.getProvince());
			neList.add(neMap);
			valMap.put("ne", neList);
			
			List<Map<String, String>> checkList = new ArrayList<Map<String,String>>();
			Map<String,String> checkMap = new HashMap<String,String>();
			checkMap.put("result", result);
			checkMap.put("remark", checkResultDetail.getRemark()+"");
			checkList.add(checkMap);
			valMap.put("check", checkList);
			
			List<Map<String, String>> stdList = new ArrayList<Map<String,String>>();
			Integer stdId = checkResultDetail.getStdlibid();
			Map<String,String> stdMap = new HashMap<String,String>();
			StdCscf stdCscf =stdCscfMapper.selectByPrimaryKey(stdId);
			String scsValue = "";
			String poolValue = "";
			String province = "";
			String sremark = "";
			if(stdCscf!=null){
				scsValue = stdCscf.getCsValue();
				poolValue = stdCscf.getPoolValue();
				province = stdCscf.getProvince();
				sremark = stdCscf.getRemark();
			}
			stdMap.put("scsValue", scsValue);
			stdMap.put("poolValue", poolValue);
			stdMap.put("sprovince", province);
			stdMap.put("sremark", sremark);
			stdList.add(stdMap);
			valMap.put("std", stdList);
			
			List<Map<String, String>> cuList = new ArrayList<Map<String,String>>();
			Integer actId = checkResultDetail.getCulibid();
			Map<String,String> cuMap = new HashMap<String,String>();
			if("hw".equals(vendor.toLowerCase())){
				CuHwCscf cuCscf =cuHwCscfMapper.selectByPrimaryKey(actId);
				if(cuCscf != null){
					cuMap.put("acsValue", cuCscf.getCsValue());
					cuMap.put("sipuri", cuCscf.getSipuri());
				}
			}else{
				CuZteCscf zteCscf =cuZteCscfMapper.selectByPrimaryKey(actId);
				if(zteCscf != null){
					cuMap.put("acsValue", zteCscf.getCsValue());
					cuMap.put("hostGroupName", zteCscf.getHostGroupName());
					cuMap.put("poolId", zteCscf.getPoolId());
					cuMap.put("heartBeat", zteCscf.getHeartBeat());
					cuMap.put("unusable", zteCscf.getUnusable());
					cuMap.put("usable", zteCscf.getUsable());
					cuMap.put("unusableFailed", zteCscf.getUnusableFailed());
					cuMap.put("usableSuccess", zteCscf.getUsableSuccess());
				}
			}
			cuList.add(cuMap);
			valMap.put("cu", cuList);
			list.add(valMap);
		}
		logger.info("getCheckDetail：list.size= "+list.size());
		return list;
	}
}
