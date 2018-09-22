package com.example.check.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.check.domain.CheckResult;
import com.example.check.domain.CheckResultDetail;
import com.example.check.domain.CheckResultDetailExample;
import com.example.check.domain.CheckResultExample;
import com.example.check.domain.CuHwCscf;
import com.example.check.domain.CuHwCscfExample;
import com.example.check.domain.CuZteCscf;
import com.example.check.domain.CuZteCscfExample;
import com.example.check.domain.DeviceInfo;
import com.example.check.domain.DeviceInfoExample;
import com.example.check.domain.StdCscf;
import com.example.check.domain.StdCscfExample;
import com.example.check.domain.SysBusiTableDef;
import com.example.check.domain.SysBusiTableDefExample;
import com.example.check.mapper.CheckResultDetailMapper;
import com.example.check.mapper.CheckResultMapper;
import com.example.check.mapper.CuHwCscfMapper;
import com.example.check.mapper.CuZteCscfMapper;
import com.example.check.mapper.DeviceInfoMapper;
import com.example.check.mapper.StdCscfMapper;
import com.example.check.mapper.SysBusiTableDefMapper;
import com.example.check.util.ExcelUtil;
import com.example.check.util.JsonUtil;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("/data")
public class DataHandlerController {
	
	private final static Logger logger = LoggerFactory.getLogger(DataHandlerController.class);

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Value("${uploadfile}")
	private String uploadfile;
	
	@Autowired
	private SysBusiTableDefMapper sysBusiTableDefMapper;
	
	@Autowired
	private CheckResultMapper checkResultMapper;
	
	@Autowired
	private CheckResultDetailMapper checkResultDetailMapper;
	
	@Autowired
	private StdCscfMapper stdCscfMapper;
	
	@Autowired
	private CuHwCscfMapper cuHwCscfMapper;

	@Autowired
	private CuZteCscfMapper cuZteCscfMapper;
	
	@Autowired
	private DeviceInfoMapper deviceInfoMapper;
	
	private final static String SUCCESS = "success";
	
	private final static String FAILURE = "failure";
	
	
	@RequestMapping("/pageList/{type}")
	@ResponseBody
	public String queryAllByPage(@PathVariable("type")String type,@RequestParam int pageNumber,int pageSize,HttpServletRequest request){
		logger.info("type="+type);
		JSONObject json = new JSONObject();
		List<SysBusiTableDef> list = new ArrayList<SysBusiTableDef>();
		try{
			//获取标准数据库
			SysBusiTableDefExample sysBusiTableDefExample = new SysBusiTableDefExample();
			sysBusiTableDefExample.createCriteria().andTypeEqualTo(type);
			list =sysBusiTableDefMapper.selectByExample(sysBusiTableDefExample);
			long total = list.size();
			
			PageHelper.startPage(pageNumber,pageSize);
			List<SysBusiTableDef> pageInfo = sysBusiTableDefMapper.selectByExample(sysBusiTableDefExample);
			logger.info(JSONObject.toJSONString(list));
			json.put("rows", pageInfo);
			json.put("total", total);
		}catch(Exception e){
			logger.error("queryAllList查询出错："+e.getMessage());
		}
		logger.info("结果："+json.toString());
		return JSON.toJSONString(list);
	}
	
	@RequestMapping("/list/{type}")
	@ResponseBody
	public String queryAllList(@PathVariable("type")String type){
		logger.info("type="+type);
		JSONObject json = new JSONObject();
		List<SysBusiTableDef> list = new ArrayList<SysBusiTableDef>();
		try{
			//获取标准数据库
			SysBusiTableDefExample sysBusiTableDefExample = new SysBusiTableDefExample();
			sysBusiTableDefExample.createCriteria().andTypeEqualTo(type);
			list =sysBusiTableDefMapper.selectByExample(sysBusiTableDefExample);
			
			long total = sysBusiTableDefMapper.countByExample(sysBusiTableDefExample);
			logger.info(JSONObject.toJSONString(list));
			json.put("rows", list);
			json.put("total", total);
		}catch(Exception e){
			logger.error("queryAllList查询出错："+e.getMessage());
		}
		logger.info("结果："+json.toString());
		return json.toString();
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/addTable/{type}")
	public String save(@PathVariable("type")String type,HttpServletRequest request){
		logger.info("type="+type);
		String dataStr = request.getParameter("param");
		JSONArray array = JSONArray.parseArray(dataStr);
		Map map = JsonUtil.jsonArrayToMap(array);
		String mapStr = JSONObject.toJSONString(map);
		logger.info(mapStr);
        SysBusiTableDef sysBusiTableDef=(SysBusiTableDef)JSONObject.toJavaObject(JSONObject.parseObject(mapStr), SysBusiTableDef.class);
        JSONObject result = new JSONObject();
        try{
        	String tableName = sysBusiTableDef.getTableName();
        	SysBusiTableDef old = sysBusiTableDefMapper.selectByTableName(tableName);
        	if(old != null){
        		result.put("state", FAILURE);
        		result.put("message", "表"+tableName+"已存在！");
        	}else{
            	sysBusiTableDef.setType(Integer.parseInt(type));
            	sysBusiTableDef.setStatus(0);
            	sysBusiTableDef.setCreateTime(sdf.format(new Date()));
            	sysBusiTableDefMapper.insert(sysBusiTableDef);
            	result.put("state", SUCCESS);
        	}
        }catch(Exception e){
        	logger.error("save出错："+e.getMessage());
        }
    	return result.toJSONString();
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/updateTable/{type}")
	public String update(@PathVariable("type")String type,HttpServletRequest request){
		JSONObject result = new JSONObject();
		String dataStr = request.getParameter("param");
		JSONArray array = JSONArray.parseArray(dataStr);
		Map<String,String> map = JsonUtil.jsonArrayToMap(array);
		String mapStr = JSONObject.toJSONString(map);
		logger.info(mapStr);
		try{
			String id = map.get("id");
			SysBusiTableDef oldTable = sysBusiTableDefMapper.selectByPrimaryKey(Integer.parseInt(id));
			String oldTableName = oldTable.getTableName();
			String tableName = map.get("tableName");
        	SysBusiTableDef old = sysBusiTableDefMapper.selectByTableName(tableName);
        	if(!oldTableName.equals(tableName) && old != null){
        		result.put("state", FAILURE);
        		result.put("message", "表"+tableName+"已存在！");
        	}else{
        		SysBusiTableDef newsbtd=(SysBusiTableDef)JSONObject.toJavaObject(JSONObject.parseObject(mapStr), SysBusiTableDef.class);
        		newsbtd.setType(Integer.parseInt(type));
        		newsbtd.setStatus(0);
        		newsbtd.setUpdateTime(sdf.format(new Date()));
        		sysBusiTableDefMapper.updateByPrimaryKey(newsbtd);
            	result.put("state", SUCCESS);
        	}
        }catch(Exception e){
        	logger.error("update出错："+e.getMessage());
        	result.put("state", FAILURE);
        	result.put("message", e.getMessage());
        }
		return result.toJSONString();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/deleteTable")
	public String delete(HttpServletRequest request){
		String ids = request.getParameter("ids");
		JSONArray array = JSONArray.parseArray(ids);
		logger.info("ids="+ids);
		List<String> list = new ArrayList();
		for(Object id :array.toArray()){
			list.add(id.toString());
		}
		JSONObject result = new JSONObject();
        try{
        	int num = sysBusiTableDefMapper.deleteByPrimaryKeyList(list);
        	logger.info("num="+num);
        	result.put("state", SUCCESS);
        }catch(Exception e){
        	logger.error("delete出错："+e.getMessage());
        	result.put("state", FAILURE);
        	result.put("message", e.getMessage());
        }
    	return result.toJSONString();
	}
	
	@RequestMapping("/download/{type}")
	public void downloadTable(@PathVariable("type")String type,HttpServletResponse response){
		SysBusiTableDefExample sysBusiTableDefExample = new SysBusiTableDefExample();
		sysBusiTableDefExample.createCriteria().andTypeEqualTo(type);
		List<SysBusiTableDef> list = sysBusiTableDefMapper.selectByExample(sysBusiTableDefExample);
		String fileName = "sys_busi_table_def.xlsx";
		try{
			String sheetName = StringUtils.isNotEmpty(type)&&type.equals("0")?"标准表":"现网表";
			ExcelUtil.exportExcel(list, sheetName, sheetName, SysBusiTableDef.class, fileName, response);
		}catch(Exception e){
			logger.error("downloadTable出错："+e.getMessage());
		}
	}
	
	@RequestMapping("/getDeviceInfo")
	public String getDeviceInfo(HttpServletRequest request ,HttpServletResponse response){
		DeviceInfoExample example = new DeviceInfoExample();
		List<DeviceInfo> list = deviceInfoMapper.selectByExample(example);
		logger.info("===========getDeviceInfo:list.size="+list.size());
		return JSONArray.toJSONString(list);
	}
	
	@RequestMapping("/detail/{tableName}")
	public String detailTable(@PathVariable("tableName")String tableName,HttpServletResponse response){
		logger.info("tableName="+tableName);
		JSONObject json = new JSONObject();
		try{
			if("std_cscf".equals(tableName.split(",")[0])){
				StdCscfExample stdCscfExample = new StdCscfExample();
				List<StdCscf> list =stdCscfMapper.selectByExample(stdCscfExample);
				long total = stdCscfMapper.countByExample(stdCscfExample);
				logger.info(JSONObject.toJSONString(list));
				json.put("rows", list);
				json.put("total", total);
			}else if("cu_hw_cscf".equals(tableName.split(",")[0])){
				CuHwCscfExample cuHwCscfExample = new CuHwCscfExample();
				List<CuHwCscf> list = cuHwCscfMapper.selectByExample(cuHwCscfExample);
				long total = cuHwCscfMapper.countByExample(cuHwCscfExample);
				logger.info(JSONObject.toJSONString(list));
				json.put("rows", list);
				json.put("total", total);
			}else if("cu_zte_cscf".equals(tableName.split(",")[0])){
				CuZteCscfExample cuZteCscfExample = new CuZteCscfExample();
				List<CuZteCscf> list = cuZteCscfMapper.selectByExample(cuZteCscfExample);
				long total = cuZteCscfMapper.countByExample(cuZteCscfExample);
				logger.info(JSONObject.toJSONString(list));
				json.put("rows", list);
				json.put("total", total);
			}else if("check_result".equals(tableName.split(",")[0]) || "check_result".equals(tableName)){
				CheckResultExample checkResultExample = new CheckResultExample();
				List<CheckResult> list = checkResultMapper.selectByExample(checkResultExample);
				long total = checkResultMapper.countByExample(checkResultExample);
				logger.info(JSONObject.toJSONString(list));
				json.put("rows", list);
				json.put("total", total);
			}
		}catch(Exception e){
			logger.error("detailTable出错："+e.getMessage());
		}
		logger.info("结果："+json.toString());
		return json.toString();
	}
	
	
	
	@RequestMapping("/cdetail/{type}/{id}")
	public String checkDetail(@PathVariable("type")String type, @PathVariable("id")String id,HttpServletResponse response){
		logger.info("id="+id);
		logger.info("type="+type);
		JSONObject json = new JSONObject();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		try{
			if("0".equals(type)){
				List<CheckResultDetail> detailList = getCheckResultDetail(type,id);
				list = getCheckDetail("正确",id, detailList);
			}else if("1".equals(type)){
				List<CheckResultDetail> detailList = getCheckResultDetail(type,id);
				list = getCheckDetail("错做",id, detailList);
			}else if("2".equals(type)){
				List<CheckResultDetail> detailList = getCheckResultDetail(type,id);
				list = getCheckDetail("少做",id, detailList);
			}else if("3".equals(type)){
				List<CheckResultDetail> detailList = getCheckResultDetail(type,id);
				list = getCheckDetail("多做",id, detailList);
			}else{
				//type为空
				List<CheckResultDetail> lossList = getCheckResultDetail("2",id);
				list.addAll(getCheckDetail("少做",id, lossList));
				
				List<CheckResultDetail> redunList = getCheckResultDetail("3",id);
				list.addAll(getCheckDetail("多做",id, redunList));
				
				List<CheckResultDetail> wrongList = getCheckResultDetail("1",id);
				list.addAll(getCheckDetail("错做",id, wrongList));
				
				List<CheckResultDetail> correctList = getCheckResultDetail("0",id);
				list.addAll(getCheckDetail("正确",id, correctList));
			}
			logger.info(JSONObject.toJSONString(list));
			json.put("rows", list);
			json.put("total", list.size());
		}catch(Exception e){
			logger.error("detailTable出错："+e.getMessage());
		}
		logger.info("结果："+json.toString());
		return json.toString();
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

	private List<Map<String, String>> getCheckDetail(String result, String id, List<CheckResultDetail> detailList) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		CheckResult checkResult =checkResultMapper.selectByPrimaryKey(Integer.parseInt(id));
		
		for(CheckResultDetail checkResultDetail : detailList){
			Map<String,String> map = new HashMap<String,String>();
			map.put("checkName", checkResult.getCategory());
			Integer stdId = checkResultDetail.getStdlibid();
			StdCscf stdCscf =stdCscfMapper.selectByPrimaryKey(stdId);
			if(stdCscf!=null){
				map.put("scsValue", stdCscf.getCsValue());
				map.put("poolValue", stdCscf.getPoolValue());
				map.put("province", stdCscf.getProvince());
			}
			
			Integer actId = checkResultDetail.getCulibid();
			CuHwCscf cuCscf =cuHwCscfMapper.selectByPrimaryKey(actId);
			if(cuCscf != null){
				map.put("acsValue", cuCscf.getCsValue());
				map.put("devinceName", cuCscf.getDeviceName());
				map.put("sipuri", cuCscf.getSipuri());
				map.put("vendor", cuCscf.getVendor());
				map.put("province", cuCscf.getProvince());
			}
			
			map.put("remark", checkResultDetail.getRemark());
			map.put("result", result);
			list.add(map);
		}
		return list;
	}
}
