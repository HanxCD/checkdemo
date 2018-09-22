package com.example.check.domain;

import lombok.Getter;
import lombok.Setter;
import cn.afterturn.easypoi.excel.annotation.Excel;

@Getter
@Setter
public class SysBusiTableDef {

	private String id; // uuid
	
	private int type;  // 0--标准数据表；1--现网数据表
	
	@Excel(name = "表英文名字", orderNum = "1", width = 20)
	private String tableName; //英文名
	
	@Excel(name = "表中文名字", orderNum = "2", width = 20)
	private String displayName; //中文名
	
	private int status; //状态
	
	@Excel(name = "分类", orderNum = "3")
	private String classify;//分类
	
	@Excel(name = "类的名字", orderNum = "4", width = 20)
	private String className;//类的名字
	
	@Excel(name = "描述", orderNum = "5", width = 30)
	private String tableDesc;//描述
	
	@Excel(name = "创建时间", exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "6", width = 20)
	private String createTime; //创建时间
	
	@Excel(name = "更新时间", exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "7", width = 20)
	private String updateTime; //更新时间
	
	public SysBusiTableDef(){
		
	}
	
	public SysBusiTableDef(String tableName,String displayName,String classify, String className, String tableDesc,String createTime,String updateTime){
		this.tableName = tableName;
		this.displayName = displayName;
		this.classify = classify;
		this.className = className;
		this.tableDesc = tableDesc;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
}
