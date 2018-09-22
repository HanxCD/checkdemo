package com.example.check.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import cn.afterturn.easypoi.excel.annotation.Excel;

@Getter
@Setter
public class StdCscf implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	/**
	 * 省份
	 */
	@Excel(name = "省份", orderNum = "1", width = 20)
    private String province;
    
    /**
     * 能力集取值
     */
	@Excel(name = "能力集取值", orderNum = "2", width = 20)
    private String csValue;

    /**
     * S-CSCF Pool域名
     */
	@Excel(name = "S-CSCF Pool域名", orderNum = "3", width = 30)
    private String poolValue;

    /**
     * 备注
     */
    @Excel(name = "备注", orderNum = "4", width = 20)
    private String remark;
    
    //private String type;


    /**
     * 【自定义属性】成功匹配标志，true:和现网库存在匹配数据，false:和现网库没有匹配数据
     */
    private Boolean successMatch;
    
    public StdCscf(){
		
	}
	
	public StdCscf(Integer id, String province,String csValue, String poolValue, String remark){
		this.id = id;
		this.province = province;
		this.csValue = csValue;
		this.poolValue = poolValue;
		this.remark = remark;
	}
	
}