package com.example.check.mapper;

import com.example.check.domain.CheckResult;
import com.example.check.domain.CheckResultDetail;
import com.example.check.domain.CheckResultDetailExample;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckResultDetailMapper {
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
	
    long countByExample(CheckResultDetailExample example);

    int deleteByExample(CheckResultDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CheckResultDetail record);

    int insertSelective(CheckResultDetail record);
    
    /**
     * 批量插入
     * @param checkList
     * @return
     */
	int batchInsert(List<CheckResultDetail> detialList);

    List<CheckResultDetail> selectByExample(CheckResultDetailExample example);

    CheckResultDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CheckResultDetail record,
			@Param("example") CheckResultDetailExample example);

    int updateByExample(@Param("record") CheckResultDetail record, @Param("example") CheckResultDetailExample example);

    int updateByPrimaryKeySelective(CheckResultDetail record);

    int updateByPrimaryKey(CheckResultDetail record);

    /**
     * 批量删除
     * @param checkList
     * @return
     */
	int batchDelete(List<CheckResult> checkList);
}