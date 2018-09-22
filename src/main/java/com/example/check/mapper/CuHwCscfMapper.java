package com.example.check.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import com.example.check.domain.CuHwCscf;
import com.example.check.domain.CuHwCscfExample;
import com.example.check.domain.StdCscf;

public interface CuHwCscfMapper {
	
	/**
	 * 判断表是否存在
	 * @param tableName 表名
	 * @return
	 */
	long isTableExist(@Param("tableName")String tableName);
	
	/**
	 * 创建表结构
	 * @param tableName 旧表名
	 * @param newTableName 新表名
	 * @return
	 */
	int createTable(@Param("tableName")String tableName,@Param("newTableName")String newTableName);
	
	/**
	 * 向新表名插入数据
	 * @param tableName 旧表名
	 * @param newTableName 新表名
	 * @return
	 */
	int copyTable(@Param("tableName")String tableName,@Param("newTableName")String newTableName);
	
	/**
	 * 删除表
	 * @param tableName 表名
	 * @return
	 */
	int dropTable(@Param("tableName")String tableName);
	
	/**
	 * 批量插入
	 * @param example
	 * @return
	 */
	int batchImport(List<T> list);
	
	/**
	 * 批量更新省份
	 * @param example
	 * @return
	 */
	int batchUpdateProvince(List<StdCscf> list);
	
	
    long countByExample(CuHwCscfExample example);

    int deleteByExample(CuHwCscfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CuHwCscf record);

    int insertSelective(CuHwCscf record);

    List<CuHwCscf> selectByExample(CuHwCscfExample example);

    CuHwCscf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CuHwCscf record,
			@Param("example") CuHwCscfExample example);

    int updateByExample(@Param("record") CuHwCscf record, @Param("example") CuHwCscfExample example);

    int updateByPrimaryKeySelective(CuHwCscf record);

    int updateByPrimaryKey(CuHwCscf record);
}