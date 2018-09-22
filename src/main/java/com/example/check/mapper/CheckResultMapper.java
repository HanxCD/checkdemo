package com.example.check.mapper;

import com.example.check.domain.CheckResult;
import com.example.check.domain.CheckResultExample;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckResultMapper {
	
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
	
    long countByExample(CheckResultExample example);

    int deleteByExample(CheckResultExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CheckResult record);

    int insertSelective(CheckResult record);

    List<CheckResult> selectByExample(CheckResultExample example);

    CheckResult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CheckResult record, @Param("example") CheckResultExample example);

    int updateByExample(@Param("record") CheckResult record, @Param("example") CheckResultExample example);

    int updateByPrimaryKeySelective(CheckResult record);

    int updateByPrimaryKey(CheckResult record);
}