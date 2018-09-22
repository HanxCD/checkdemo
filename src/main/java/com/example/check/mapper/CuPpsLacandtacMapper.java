package com.example.check.mapper;

import com.example.check.domain.CuPpsLacandtac;
import com.example.check.domain.CuPpsLacandtacExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CuPpsLacandtacMapper {
    long countByExample(CuPpsLacandtacExample example);

    int deleteByExample(CuPpsLacandtacExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CuPpsLacandtac record);

    int insertSelective(CuPpsLacandtac record);

    List<CuPpsLacandtac> selectByExample(CuPpsLacandtacExample example);

    CuPpsLacandtac selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CuPpsLacandtac record,
			@Param("example") CuPpsLacandtacExample example);

    int updateByExample(@Param("record") CuPpsLacandtac record, @Param("example") CuPpsLacandtacExample example);

    int updateByPrimaryKeySelective(CuPpsLacandtac record);

    int updateByPrimaryKey(CuPpsLacandtac record);
}