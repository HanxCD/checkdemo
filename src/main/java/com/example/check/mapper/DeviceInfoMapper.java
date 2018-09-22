package com.example.check.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.check.domain.DeviceInfo;
import com.example.check.domain.DeviceInfoExample;

public interface DeviceInfoMapper {
    long countByExample(DeviceInfoExample example);

    int deleteByExample(DeviceInfoExample example);

    int deleteByPrimaryKey(Integer id);
    
    int deleteByDeviceName(String deviceName);

    int insert(DeviceInfo record);

    int insertSelective(DeviceInfo record);

    List<DeviceInfo> selectByExample(DeviceInfoExample example);

    DeviceInfo selectByPrimaryKey(Integer id);
    
    DeviceInfo selectByDeviceName(String deviceName);

    int updateByExampleSelective(@Param("record") DeviceInfo record,
			@Param("example") DeviceInfoExample example);

    int updateByExample(@Param("record") DeviceInfo record, @Param("example") DeviceInfoExample example);

    int updateByPrimaryKeySelective(DeviceInfo record);

    int updateByPrimaryKey(DeviceInfo record);
    
    int updateByDeviceName(DeviceInfo record);
}