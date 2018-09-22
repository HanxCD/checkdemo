package com.example.check.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.check.domain.CallResult;
import com.example.check.domain.CheckResult;
import com.example.check.domain.CheckResultDetail;
import com.example.check.domain.CheckResultExample;
import com.example.check.domain.CuHwCscf;
import com.example.check.domain.CuHwCscfExample;
import com.example.check.domain.CuPpsLacandtac;
import com.example.check.domain.CuPpsLacandtacExample;
import com.example.check.domain.CuZteCscf;
import com.example.check.domain.CuZteCscfExample;
import com.example.check.domain.DeviceInfo;
import com.example.check.domain.StdCscf;
import com.example.check.domain.StdCscfExample;
import com.example.check.domain.StdPsLacandtac;
import com.example.check.domain.StdPsLacandtacExample;
import com.example.check.mapper.CheckResultDetailMapper;
import com.example.check.mapper.CheckResultMapper;
import com.example.check.mapper.CuHwCscfMapper;
import com.example.check.mapper.CuPpsLacandtacMapper;
import com.example.check.mapper.CuZteCscfMapper;
import com.example.check.mapper.DeviceInfoMapper;
import com.example.check.mapper.StdCscfMapper;
import com.example.check.mapper.StdPsLacandtacMapper;

/**
 * <b>System：</b>NCC<br/>
 * <b>Title：</b>CheckCompareController.java<br/>
 * <b>Description：</b> 描述 <br/>
 * <b>@author： </b>boco<br/>
 * <b>@date：</b>2018年08月2018/8/21日 15:09<br/>
 * <b>@version：</b> 1.0.0.0<br/>
 * <b>Copyright (c) 2018 ASPire Tech.</b>
 */
@RestController
@RequestMapping("/check")
public class CheckCompareController {
	
	private final static Logger logger = LoggerFactory.getLogger(CheckCompareController.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	@Autowired
	private StdPsLacandtacMapper stdPsLacandtacMapper;
	@Autowired
	private CuPpsLacandtacMapper cuPpsLacandtacMapper;
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
	
	/**
	 * 核查
	 * @throws Exception 
	 */
	@RequestMapping("/{classify}/{vendor}/{deviceId}")
	public CallResult checkCompare(@PathVariable("classify")String classify,@PathVariable("vendor")String vendor,@PathVariable("deviceId")String deviceId) throws Exception{
		logger.info("classify="+classify);
		logger.info("vendor="+vendor);
		logger.info("deviceId="+deviceId);
		CallResult callResult = new CallResult(true);
		//获取网元的基本信息
    	DeviceInfo deviceInfo = deviceInfoMapper.selectByPrimaryKey(Integer.parseInt(deviceId));
    	if(deviceInfo == null){
    		logger.error("没有找到相关网元("+deviceId+")的信息。");
    		throw new Exception("没有找到相关网元("+deviceId+")的信息。");
    	}
    	String deviceName = deviceInfo.getDeviceName();
    	String province = deviceInfo.getProvince();
    	if("cscf".equals(classify)){
    		if("hw".equals(vendor.toLowerCase())){
    			callResult = checkCompareHwCscf(vendor, deviceName,province);
    		}else if("zte".equals(vendor.toLowerCase())){
    			callResult = checkCompareZteCscf(vendor, deviceName,province);
    		}
    	}
		return callResult;
	}
	
	/**
	 * 核查华为CSCF数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/hw-cscf/")
	public CallResult checkCompareHwCscf(String classify, String deviceName,String province){
		CallResult callResult = new CallResult(true);
		//获取华为CSCF标准数据
		List<StdCscf> stdList = getStdList(province);
		logger.info("现网表 std_cscf 中"+stdList.size()+"条记录。");
		
		//获取华为CSCF现网数据
		List<CuHwCscf> cuList = new ArrayList<CuHwCscf>();
		getCuList(deviceName, "hw", cuList);
		logger.info("现网表 cu_hw_cscf 中"+cuList.size()+"条记录。");
		
		/**
		 * 1、先备份表
		 * 2、清空表
		 */
		backupTable("check_result");
		backupTable("check_result_detail");
		
		/**
		 * 1、删除历史记录
		 */
		logger.info("===============清空 check_result start========================");
		CheckResultExample checkResultExample = new CheckResultExample();
		checkResultExample.createCriteria().andCategoryEqualTo("CSCF").andDeviceNameEqualTo(deviceName);
		List<CheckResult> checkList = checkResultMapper.selectByExample(checkResultExample);
		/**
		 * 批量删除check_result_detail中相关记录
		 */
		logger.info("===============清空 check_result_detial start========================");
		if(CollectionUtils.isNotEmpty(checkList)){
			int detialCount = checkResultDetailMapper.batchDelete(checkList);
			logger.info("删除核查结果表 check_result_detial中"+detialCount+"条记录。");
		}
		logger.info("===============清空 check_result_detial end =========================");
		/**
		 * 批量删除check_result相关记录
		 */
		int checkCount = checkResultMapper.deleteByExample(checkResultExample);
		logger.info("===============清空 check_result end =========================");
		logger.info("删除核查结果表 check_result中相关网元(="+deviceName+")的"+checkCount+"条记录。");
		
		CheckResult checkResult = initialCheckResult("CSCF","HW", deviceName);
		checkResultMapper.insertSelective(checkResult);
		//定义集合用于存储核查详情数据
		List<CheckResultDetail> checkResultDetailList = new ArrayList<>();
		/**
		 * 循环现网库，并通过省份在标准库数据进行匹配，
		 * 1、现网csValue比对标准csValue是否完全一致：如果二者不一致，标准库多，则输出 “漏做”，否则输出“多做”；
		 * 2、如果二者一致，对比“现DS-CSCF SIP URI地址”(sipuri)与“标CS-CSCF Pool域名”(poolValue)，如果一致，则输出“正确”，不一致，则输出“错误”，结果备注为“主机名不一致”。
		 */
		for(CuHwCscf cuData:cuList){
			//现网库数据是否在标准库中匹配
			boolean matchInStdFlag = false;
			for(StdCscf stdData : stdList){
				if(cuData.getProvince().equalsIgnoreCase(stdData.getProvince())){
					matchInStdFlag = true;
					stdData.setSuccessMatch(true);
					//匹配到数据，则比对csValue
					String stdCsValue = stdData.getCsValue().trim();
					String cuCsValue = cuData.getCsValue().trim();
					String stdSipuri = stdData.getPoolValue().trim();
					String cuSipuri = cuData.getSipuri().trim();
					if(cuCsValue.equals(stdCsValue)){
						boolean isEqual = cuSipuri.contains(stdSipuri);
						logger.info("stdSipuri:cuSipuri:isEqual@"+stdSipuri+":"+cuSipuri+":"+isEqual);
						if(isEqual){
							CheckResultDetail checkResultDetail =
									buildCheckResultDetail(checkResult.getId(),stdData.getId(),cuData.getId(),true,false,false,false,"");
							checkResultDetailList.add(checkResultDetail);
						}else{
							//导出的时候备注为“主机名不一致”
							CheckResultDetail checkResultDetail =
									buildCheckResultDetail(checkResult.getId(),stdData.getId(),cuData.getId(),false,true,false,false,"主机名不一致");
							checkResultDetailList.add(checkResultDetail);
						}
					}
				}
			}
			if(!matchInStdFlag){
				//现网数据没有在标准库中匹配成功，则此数据为多做
				CheckResultDetail checkResultDetail =
						buildCheckResultDetail(checkResult.getId(),null,cuData.getId(),false,false,false,true,"");
				checkResultDetailList.add(checkResultDetail);
			}
		}
		//循环标准库数据，找出未匹配比对的数据，此数据为少做
		for(StdCscf stdData :stdList){
			if(stdData.getSuccessMatch() == null || stdData.getSuccessMatch() == false){
				CheckResultDetail checkResultDetail =
						buildCheckResultDetail(checkResult.getId(),stdData.getId(),null,false,false,true,false,"");
				checkResultDetailList.add(checkResultDetail);
			}
		}
		//统计核查结果，更新核查结果表
		int correctNum = 0;
		int wrongNum = 0;
		int lossNum = 0;
		int redundantNum = 0;
		for(CheckResultDetail checkResultDetail : checkResultDetailList){
			if(checkResultDetail.getIscorrect()){
				correctNum+=1;
				continue;
			}
			if(checkResultDetail.getIswrong()){
				wrongNum+=1;
				continue;
			}
			if(checkResultDetail.getIsloss()){
				lossNum+=1;
				continue;
			}
			if(checkResultDetail.getIsredundant()){
				redundantNum+=1;
				continue;
			}
		}
		checkResult.setTotalcounts(stdList.size());
		checkResult.setCorrectnum(correctNum);
		checkResult.setWrongnum(wrongNum);
		checkResult.setLossnum(lossNum);
		checkResult.setRedundantnum(redundantNum);
		checkResultMapper.updateByPrimaryKeySelective(checkResult);
		/**
		 * 将结果详情批量插入到check_result_detail中
		 */
		logger.info("=============== 批量插入 check_result_detial start========================");
		checkResultDetailMapper.batchInsert(checkResultDetailList);
		logger.info("=============== 批量插入 check_result_detial记录数:"+checkResultDetailList.size());
		logger.info("=============== 批量插入 check_result_detial end =========================");
		
		
		return callResult;
	}
	
	/**
	 * 核查中兴CSCF数据
	 * @return
	 */
	@RequestMapping("/zte-cscf/")
	public CallResult checkCompareZteCscf(String vendor, String deviceName, String province){
		CallResult callResult = new CallResult(true);

		//获取中兴 CSCF标准数据
		List<StdCscf> stdList = getStdList(province);

		//获取中兴CSCF现网数据
		List<CuZteCscf> cuList = new ArrayList<CuZteCscf>();
		getCuList(deviceName, province, cuList);

		/**
		 * 1、先备份表
		 * 2、清空表
		 */
		backupTable("check_result");
		backupTable("check_result_detail");

		/**
		 * 1、删除历史记录
		 */
		logger.info("===============清空 check_result start========================");
		CheckResultExample checkResultExample = new CheckResultExample();
		checkResultExample.createCriteria().andCategoryEqualTo("CSCF").andDeviceNameEqualTo(deviceName);
		List<CheckResult> checkList = checkResultMapper.selectByExample(checkResultExample);
		/**
		 * 批量删除check_result_detail中相关记录
		 */
		logger.info("===============清空 check_result_detial start========================");
		if(CollectionUtils.isNotEmpty(checkList)){
			int detialCount = checkResultDetailMapper.batchDelete(checkList);
			logger.info("删除核查结果表 check_result_detial中"+detialCount+"条记录。");
		}
		logger.info("===============清空 check_result_detial end =========================");
		/**
		 * 批量删除check_result相关记录
		 */
		int checkCount = checkResultMapper.deleteByExample(checkResultExample);
		logger.info("===============清空 check_result end =========================");
		logger.info("删除核查结果表 check_result中相关网元(="+deviceName+")的"+checkCount+"条记录。");
		
		//初始化核查结果表
		CheckResult checkResult = initialCheckResult("CSCF","ZTE", deviceName);
		checkResultMapper.insertSelective(checkResult);
		//定义集合用于存储核查详情数据
		List<CheckResultDetail> checkResultDetailList = new ArrayList<>();
		/**
		 * 循环现网库，并通过省份在标准库数据进行匹配，
		 * 1、现网csValue比对标准csValue是否完全一致：如果二者不一致，标准库多，则输出 “漏做”，否则输出“多做”；
		 * 2、如果二者一致，对比“现DS-CSCF SIP URI地址”(sipuri)与“标CS-CSCF Pool域名”(poolValue)，如果一致，则输出“正确”，不一致，则输出“错误”，结果备注为“主机名不一致”。
		 */
		for(CuZteCscf cuData:cuList){
			//现网库数据是否在标准库中匹配
			boolean matchInStdFlag = false;

			String cuCsValue = cuData.getCsValue();
			String cuHostGroupName = cuData.getHostGroupName();
			String cuPoolId = cuData.getPoolId();
			String cuHeartBeat = cuData.getHeartBeat();
			String cuUnusable = cuData.getUnusable();
			String cuUsable = cuData.getUsable();
			String cuUnusableFailed = cuData.getUnusableFailed();
			String cuUsableSuccess = cuData.getUsableSuccess();
			String str = (cuHeartBeat + "/" + cuUnusable + "/" + cuUsable + "/" + cuUnusableFailed + "/" + cuUsableSuccess);
			if (!"关闭/20/20/0/0".equals(str)){
				CheckResultDetail checkResultDetail =
					buildCheckResultDetail(checkResult.getId(),null,cuData.getId(),false,true,false,false,"参数设置异常");
				checkResultDetailList.add(checkResultDetail);
				matchInStdFlag = true;
				continue;
			}
			
			if (!"1".equals(cuPoolId)){
				//错做
				CheckResultDetail checkResultDetail =
					buildCheckResultDetail(checkResult.getId(),null,cuData.getId(),false,true,false,false,"池编号异常");
				checkResultDetailList.add(checkResultDetail);
				matchInStdFlag = true;
				continue;
			}
			for(StdCscf stdData : stdList){
				if(province.equalsIgnoreCase(stdData.getProvince())){
					matchInStdFlag = true;
					stdData.setSuccessMatch(true);
					//匹配到数据，则比对csValue
					String stdCsValue = stdData.getCsValue();
					String stdPoolValue = stdData.getPoolValue();
					if(cuCsValue.equals(stdCsValue)){
						if(cuHostGroupName.contains(stdPoolValue)){
							CheckResultDetail checkResultDetail =
									buildCheckResultDetail(checkResult.getId(),stdData.getId(),cuData.getId(),true,false,false,false,"");
							checkResultDetailList.add(checkResultDetail);
						}else{
							//导出的时候备注为“主机名不一致”
							CheckResultDetail checkResultDetail =
									buildCheckResultDetail(checkResult.getId(),stdData.getId(),cuData.getId(),false,true,false,false,"主机名不一致");
							checkResultDetailList.add(checkResultDetail);
						}
					}
				}
			}
			if(!matchInStdFlag){
				//现网数据没有在标准库中匹配成功，则此数据为多做
				CheckResultDetail checkResultDetail =
						buildCheckResultDetail(checkResult.getId(),null,cuData.getId(),false,false,false,true,"");
				checkResultDetailList.add(checkResultDetail);
			}
		}
		//循环标准库数据，找出未匹配比对的数据，此数据为少做
		for(StdCscf stdData :stdList){
			if(stdData.getSuccessMatch() == null || stdData.getSuccessMatch() == false){
				CheckResultDetail checkResultDetail =
						buildCheckResultDetail(checkResult.getId(),stdData.getId(),null,false,false,true,false,"");
				checkResultDetailList.add(checkResultDetail);
			}


		}
		//统计核查结果，更新核查结果表
		int correctNum = 0;
		int wrongNum = 0;
		int lossNum = 0;
		int redundantNum = 0;
		for(CheckResultDetail checkResultDetail : checkResultDetailList){
			if(checkResultDetail.getIscorrect()){
				correctNum+=1;
				continue;
			}
			if(checkResultDetail.getIswrong()){
				wrongNum+=1;
				continue;
			}
			if(checkResultDetail.getIsloss()){
				lossNum+=1;
				continue;
			}
			if(checkResultDetail.getIsredundant()){
				redundantNum+=1;
				continue;
			}
		}
		checkResult.setTotalcounts(stdList.size());
		checkResult.setCorrectnum(correctNum);
		checkResult.setWrongnum(wrongNum);
		checkResult.setLossnum(lossNum);
		checkResult.setRedundantnum(redundantNum);
		checkResultMapper.updateByPrimaryKeySelective(checkResult);
		
		/**
		 * 将结果详情批量插入到check_result_detail中
		 */
		checkResultDetailMapper.batchInsert(checkResultDetailList);
		return callResult;
	}
	
	/**
	 * 备份表
	 * 1、先判断newTableName是否存在
	 * 2、若存在，则先删除newTableName,否则先创建表结果，更新记录
	 * @param string
	 */
	private void backupTable(String tableName) {
		String date = sdf.format(new Date());
		String newTableName = tableName + "_" +date;
		if("check_result".equals(tableName)){
			if(checkResultMapper.isTableExist(newTableName) > 0){
				checkResultMapper.dropTable(newTableName);
			}
			int create = checkResultMapper.createTable(tableName, newTableName);
			logger.info("创建表"+newTableName+"是否成功："+create);
			int copy = checkResultMapper.copyTable(tableName, newTableName);
			logger.info("copy表"+newTableName+"记录是否成功："+copy);
		}else if("check_result_detial".equals(tableName)){
			if(checkResultDetailMapper.isTableExist(newTableName) > 0){
				checkResultDetailMapper.dropTable(newTableName);
			}
			int create = checkResultDetailMapper.createTable(tableName, newTableName);
			logger.info("创建表"+newTableName+"是否成功："+create);
			int copy = checkResultDetailMapper.copyTable(tableName, newTableName);
			logger.info("copy表"+newTableName+"记录是否成功："+copy);
		}
	}
	
	/**
	 * 获取标准数据库
	 */
	private List getStdList(String province){
		StdCscfExample stdCscfExample = new StdCscfExample();
		stdCscfExample.setOrderByClause("csValue");
		List stdList = stdCscfMapper.selectByExample(stdCscfExample);
		return stdList;
	}
	
	/**
	 * 获取现网数据
	 * @param deviceName
	 * @param vendor
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	private void getCuList(String deviceName, String vendor, List list){
		if("hw".equals(vendor.toLowerCase())){
			CuHwCscfExample cuHwCscfExample = new CuHwCscfExample();
			cuHwCscfExample.setOrderByClause("csValue");
			cuHwCscfExample.createCriteria().andDeviceNameEqualTo(deviceName).andProvinceIsNotNull();
			List<CuHwCscf> rstList= cuHwCscfMapper.selectByExample(cuHwCscfExample);
			list.addAll(rstList);
		}else {
			CuZteCscfExample cuZteCscfExample = new CuZteCscfExample();
			cuZteCscfExample.setOrderByClause("hostGroupId");
			cuZteCscfExample.createCriteria().andDeviceNameEqualTo(deviceName).andProvinceIsNotNull();
			List<CuZteCscf> rstList = cuZteCscfMapper.selectByExample(cuZteCscfExample);
			list.addAll(rstList);
		}
	}
	
	/**
	 * 初始化核查结果表
	 * @return
	 */
	private CheckResult initialCheckResult(String category,String vendor, String deviceName){
		CheckResult checkResult = new CheckResult();
		checkResult.setCategory(category);
		checkResult.setVendor(vendor);
		checkResult.setDeviceName(deviceName);
		checkResult.setTotalcounts(0);
		checkResult.setCorrectnum(0);
		checkResult.setWrongnum(0);
		checkResult.setLossnum(0);
		checkResult.setRedundantnum(0);
		return checkResult;
	}

	/**
	 * 创建核查结果详情对象
	 * @param checkRsultId 核查结果ID
	 * @param stdLibId 标准库数据ID
	 * @param cuLibId 现网库数据ID
	 * @param isCorrect 是否一致
	 * @param isWrong 是否错误
	 * @param isLoss 是否设备少
	 * @param isRedundant 是否设备多
	 * @param remark 
	 * @return
	 */
	private CheckResultDetail buildCheckResultDetail(Integer checkRsultId,Integer stdLibId,Integer cuLibId,
			Boolean isCorrect,Boolean isWrong,Boolean isLoss,Boolean isRedundant, String remark){
		CheckResultDetail checkResultDetail = new CheckResultDetail();
		checkResultDetail.setCheckresultid(checkRsultId);
		checkResultDetail.setStdlibid(stdLibId);
		checkResultDetail.setCulibid(cuLibId);
		checkResultDetail.setIscorrect(isCorrect);
		checkResultDetail.setIswrong(isWrong);
		checkResultDetail.setIsloss(isLoss);
		checkResultDetail.setIsredundant(isRedundant);
		checkResultDetail.setRemark(remark);
		return checkResultDetail;
	}
	
	/**
	 * 核查华为NB-TAC数据
	 * @return
	 */
	@RequestMapping("/hw-nb-tac")
	public CallResult checkCompareHwNbTac(){
		CallResult callResult = new CallResult(true);
		//获取华为TAC标准库数据
		StdPsLacandtacExample stdPsLacandtacExample = new StdPsLacandtacExample();
		stdPsLacandtacExample.createCriteria().andTypeEqualTo("TAC");
		List<StdPsLacandtac> stdList =stdPsLacandtacMapper.selectByExample(stdPsLacandtacExample);
		//获取华为TAC现网库数据
		CuPpsLacandtacExample cuPpsLacandtacExample = new CuPpsLacandtacExample();
		cuPpsLacandtacExample.createCriteria().andTypeEqualTo("TAC");
		List<CuPpsLacandtac> cuList = cuPpsLacandtacMapper.selectByExample(cuPpsLacandtacExample);
		//初始化核查结果表
		CheckResult checkResult = new CheckResult();
		checkResult.setCategory("华为NBTAC");
		checkResult.setTotalcounts(0);
		checkResult.setCorrectnum(0);
		checkResult.setWrongnum(0);
		checkResult.setLossnum(0);
		checkResult.setRedundantnum(0);
		checkResultMapper.insertSelective(checkResult);
		//定义集合用于存储核查详情数据
		List<CheckResultDetail> checkResultDetailList = new ArrayList<>();
		//循环现网库，并通过省份在标准库数据进行匹配，然后比对l1l2是否完全一致
		for(CuPpsLacandtac cuData : cuList){
			//现网库数据是否在标准库中匹配
			boolean matchInStdFlag = false;
			for(StdPsLacandtac stdData :stdList){
				if(cuData.getProvinceid().equalsIgnoreCase(stdData.getProvinceid())){
					matchInStdFlag = true;
					stdData.setSuccessMatch(true);
					//匹配到数据，则比对l1l2
					String stdl12 = stdData.getL1()+stdData.getL2();
					String cul12 = cuData.getL1()+cuData.getL2();
					if(stdl12.equalsIgnoreCase(cul12)){
						//比对一致，记录一致结果
						CheckResultDetail checkResultDetail =
								buildCheckResultDetail(checkResult.getId(),stdData.getId(),cuData.getId(),true,false,false,false,"");
						checkResultDetailList.add(checkResultDetail);
					}else {
						//比对l1l2不一致，记录不一致记录
						CheckResultDetail checkResultDetail =
								buildCheckResultDetail(checkResult.getId(),stdData.getId(),cuData.getId(),false,true,false,false,"");
						checkResultDetailList.add(checkResultDetail);
					}
				}
			}
			if(!matchInStdFlag){
				//现网数据没有在标准库中匹配成功，则此数据未多做
				CheckResultDetail checkResultDetail =
						buildCheckResultDetail(checkResult.getId(),null,cuData.getId(),false,false,false,true,"");
				checkResultDetailList.add(checkResultDetail);
			}
		}
		//循环标准库数据，找出未匹配比对的数据，此数据未设备少的数据
		for(StdPsLacandtac stdData :stdList){
			if(stdData.getSuccessMatch() == null || stdData.getSuccessMatch() == false){
				CheckResultDetail checkResultDetail =
						buildCheckResultDetail(checkResult.getId(),stdData.getId(),null,false,false,true,false,"");
				checkResultDetailList.add(checkResultDetail);
			}
		}
		//统计核查结果，更新核查结果表
		int correctNum = 0;
		int wrongNum = 0;
		int lossNum = 0;
		int redundantNum = 0;
		for(CheckResultDetail checkResultDetail : checkResultDetailList){
			if(checkResultDetail.getIscorrect()){
				correctNum+=1;
				continue;
			}
			if(checkResultDetail.getIswrong()){
				wrongNum+=1;
				continue;
			}
			if(checkResultDetail.getIsloss()){
				lossNum+=1;
				continue;
			}
			if(checkResultDetail.getIsredundant()){
				redundantNum+=1;
				continue;
			}
		}
		checkResult.setTotalcounts(stdList.size());
		checkResult.setCorrectnum(correctNum);
		checkResult.setWrongnum(wrongNum);
		checkResult.setLossnum(lossNum);
		checkResult.setRedundantnum(redundantNum);
		checkResultMapper.updateByPrimaryKeySelective(checkResult);
		//将结果详情查询结果详情表
		for(CheckResultDetail checkResultDetail : checkResultDetailList){
			checkResultDetailMapper.insertSelective(checkResultDetail);
		}
		return callResult;
	}

}
