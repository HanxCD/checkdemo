package com.example.check.mapper;

import com.example.check.domain.StdPsLacandtac;
import com.example.check.domain.StdPsLacandtacExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StdPsLacandtacMapper {
    long countByExample(StdPsLacandtacExample example);

    int deleteByExample(StdPsLacandtacExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StdPsLacandtac record);

    int insertSelective(StdPsLacandtac record);

    List<StdPsLacandtac> selectByExample(StdPsLacandtacExample example);

    StdPsLacandtac selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StdPsLacandtac record,
			@Param("example") StdPsLacandtacExample example);

    int updateByExample(@Param("record") StdPsLacandtac record, @Param("example") StdPsLacandtacExample example);

    int updateByPrimaryKeySelective(StdPsLacandtac record);

    int updateByPrimaryKey(StdPsLacandtac record);
}