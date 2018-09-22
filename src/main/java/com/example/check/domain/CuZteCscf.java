package com.example.check.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuZteCscf {

	private Integer id;

	/**
	 * 邻接主机编号
	 */
	@Excel(name = "邻接主机编号", orderNum = "1", width = 20)
    private String hostGroupId;

    /**
	 * 邻接主机名称
	 */
	@Excel(name = "邻接主机名称", orderNum = "3", width = 20)
    private String hostGroupName;

    /**
	 * 池编号
	 */
	@Excel(name = "池编号", orderNum = "4", width = 30)
    private String poolId;

    /**
	 * 心跳模式
	 */
	@Excel(name = "心跳模式", orderNum = "5", width = 20)
    private String heartBeat;

    /**
	 * "不可用"状态下心跳间隔(秒)
	 */
	@Excel(name = "\"不可用\"状态下心跳间隔(秒)", orderNum = "6", width = 20)
    private String unusable;

    /**
	 * "可用"与"未检测"状态下心跳间隔(秒)
	 */
	@Excel(name = "\"可用\"与\"未检测\"状态下心跳间隔(秒)", orderNum = "7", width = 30)
    private String usable;

	/**
	 * 不可用的失败检测次数
	 */
	@Excel(name ="不可用的失败检测次数", orderNum = "8", width = 30)
	private String unusableFailed;

	/**
	 * 可用的成功检测次数
	 */
	@Excel(name = "可用的成功检测次数", orderNum = "9", width = 30)
	private String usableSuccess;

	/**
	 * 能力集
	 */
	@Excel(name = "能力集", orderNum = "10", width = 30)
	private String csValue;

	/**
	 * S-CSCF 1
	 */
	@Excel(name ="S-CSCF 1", orderNum = "11", width = 30)
	private String scscf1;

	/**
	 * 备注
	 */
	@Excel(name ="备注", orderNum = "11", width = 30)
	private String remark;
	
    /**
	 * 网元名称
	 */
	@Excel(name = "设备", orderNum = "12", width = 20)
	private String deviceName;
	
	/**
	 * 省份
	 */
	@Excel(name = "省份", orderNum = "13", width = 20)
	private String province;
	
	
	/**
	 * 厂家
	 */
	@Excel(name = "厂家", orderNum = "14", width = 20)
    private String vendor;

    /**
     * 【自定义属性】成功匹配标志，true:和现网库存在匹配数据，false:和现网库没有匹配数据
     */
    private Boolean successMatch;

    public CuZteCscf(){

	}

	public CuZteCscf(String hostGroupId, String hostGroupName, String poolId, String heartBeat, String unusable, String usable, String unusableFailed, String usableSuccess, String csValue, String scscf1, String remark) {
		this.hostGroupId = hostGroupId;
		this.hostGroupName = hostGroupName;
		this.poolId = poolId;
		this.heartBeat = heartBeat;
		this.unusable = unusable;
		this.usable = usable;
		this.unusableFailed = unusableFailed;
		this.usableSuccess = usableSuccess;
		this.csValue = csValue;
		this.scscf1 = scscf1;
		this.remark = remark;
	}
}