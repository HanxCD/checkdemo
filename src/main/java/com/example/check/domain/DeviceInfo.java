package com.example.check.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	/**
	 * 网元名称
	 */
    private String deviceName;
	
	/**
	 * 省份
	 */
    private String province;
    
    /**
	 * 地市
	 */
    private String city;
    
    /**
	 * 区号
	 */
    private String areaCode;
    
    /**
	 * 厂家
	 */
    private String vendor;
    
    /**
	 * 版本
	 */
    private String version;

    /**
	 * 覆盖地市
	 */
    private String coverCity;
}
