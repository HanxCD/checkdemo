package com.example.check.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import com.example.check.domain.StdCscf;
import com.example.check.domain.StdCscfExample;

public interface StdCscfMapper {
    long countByExample(StdCscfExample example);

    int deleteByExample(StdCscfExample example);

    int deleteByPrimaryKey(Integer id);
    
    int deleteAll();

    int insert(StdCscf record);

    int insertSelective(StdCscf record);
    
    int batchImport(List<StdCscf> list);

    List<StdCscf> selectByExample(StdCscfExample example);

    StdCscf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StdCscf record,
			@Param("example") StdCscfExample example);

    int updateByExample(@Param("record") StdCscf record, @Param("example") StdCscfExample example);

    int updateByPrimaryKeySelective(StdCscf record);

    int updateByPrimaryKey(StdCscf record);
}