package com.example.check.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.check.domain.SysBusiTableDef;
import com.example.check.domain.SysBusiTableDefExample;

public interface SysBusiTableDefMapper {
    long countByExample(SysBusiTableDefExample example);

    int deleteByExample(SysBusiTableDefExample example);

    int deleteByPrimaryKey(Integer id);
    
    int deleteByPrimaryKeyList(List list);

    int insert(SysBusiTableDef record);

    int insertSelective(SysBusiTableDef record);

    List<SysBusiTableDef> selectByExample(SysBusiTableDefExample example);

    SysBusiTableDef selectByPrimaryKey(Integer id);
    
    SysBusiTableDef selectByTableName(String tableName);

    int updateByExampleSelective(@Param("record") SysBusiTableDef record,
			@Param("example") SysBusiTableDefExample example);

    int updateByExample(@Param("record") SysBusiTableDef record, @Param("example") SysBusiTableDefExample example);

    int updateByPrimaryKeySelective(SysBusiTableDef record);

    int updateByPrimaryKey(SysBusiTableDef record);
}