package com.example.check.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/")
	public String login(){
		return "index";
	}
	
	@RequestMapping("/table/{type}")
	public String goStandardtable(@PathVariable("type")String type , HttpServletRequest request){
		return "standardtable/index";
	}
	
	@RequestMapping("/datacheck/{tableName}")
	public String goDataCheck(@PathVariable("tableName")String tableName , HttpServletRequest request){
		logger.info("tableName="+tableName);
		return "datacheck/index";
	}
	
	@RequestMapping("/upload")
	public String upload(){
		return "fileupload";
	}
	
	@RequestMapping("/sdetail/{tableName}")
	public String stdTable(@PathVariable("tableName")String tableName , HttpServletRequest request){
		if(StringUtils.isNotBlank(tableName) && "std_cscf".equals(tableName)){
			return "standardtable/stdcscf";
		}
		return null;
	}
	
	@RequestMapping("/adetail/{tableName}")
	public String cuTable(@PathVariable("tableName")String tableName , HttpServletRequest request){
		if(StringUtils.isNotBlank(tableName) && "cu_hw_cscf".equals(tableName)){
			return "actualtable/cuhwcscf";
		}else if(StringUtils.isNotBlank(tableName) && "cu_zte_cscf".equals(tableName)){
			return "actualtable/cuztecscf";
		}
		return null;
	}
	
	@RequestMapping("/chdetail/{tableName}")
	public String checkTable(@PathVariable("tableName")String tableName , HttpServletRequest request){
		if(StringUtils.isNotBlank(tableName) && "cu_hw_cscf".equals(tableName)){
			return "actualtable/cuhwcscf";
		}
		return null;
	}
	
	@RequestMapping("/cdetail/{type}/{id}")
	public String checkDetail(@PathVariable("type")String type,
			@PathVariable("id")String id,HttpServletRequest request){
		return "datacheck/detail";
	}
}
