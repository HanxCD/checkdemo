package com.example.check.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuHwCscf{

	private Integer id;

	/**
	 * 省份
	 */
	@Excel(name = "归属省", orderNum = "1", width = 20)
    private String province;
    
    /**
	 * 能力集
	 */
	@Excel(name = "能力集", orderNum = "3", width = 20)
    private String csValue;

    /**
	 * SIP URI地址
	 */
	@Excel(name = "sipuri地址", orderNum = "4", width = 30)
    private String sipuri;
    
    /**
	 * 网元名称
	 */
	@Excel(name = "设备", orderNum = "5", width = 20)
    private String deviceName;
    
    /**
	 * 厂家
	 */
	@Excel(name = "厂家", orderNum = "6", width = 20)
    private String vendor;
	
	/**
	 * 备注
	 */
	@Excel(name = "地址类型", orderNum = "7", width = 30)
	private String addressType;

    /**
	 * 备注
	 */
	@Excel(name = "备注", orderNum = "8", width = 30)
    private String remark;
	
    /**
     * 【自定义属性】成功匹配标志，true:和现网库存在匹配数据，false:和现网库没有匹配数据
     */
    private Boolean successMatch;
    
    public CuHwCscf(){
		
	}
	
	public CuHwCscf(String province,String csValue, String sipuri,String deviceName,String vendor, String remark){
		this.csValue = csValue;
		this.sipuri = sipuri;
		this.deviceName = deviceName;
		this.vendor = vendor;
		this.remark = remark;
	}
}