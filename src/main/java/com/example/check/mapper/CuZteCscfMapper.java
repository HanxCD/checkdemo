package com.example.check.mapper;

import com.example.check.domain.CuZteCscf;
import com.example.check.domain.CuZteCscfExample;
import com.example.check.domain.DeviceInfo;
import com.example.check.domain.DeviceInfoExample;
import com.example.check.domain.StdCscf;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface CuZteCscfMapper {
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
     * @param list
     * @return
     */
    int batchImport(List<T> list);
    
    /**
     * 批量更新
     * @param list
     * @return
     */
    int batchUpdate(List<T> list);
    
    /**
     * 批量更新省份
     * @param list
     * @return
     */
    int batchUpdateProvince(List<StdCscf> stdList);

    long countByExample(CuZteCscfExample example);

    int deleteByExample(CuZteCscfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CuZteCscf record);

    int insertSelective(CuZteCscf record);

    List<CuZteCscf> selectByExample(CuZteCscfExample example);

    CuZteCscf selectByPrimaryKey(Integer id);

    DeviceInfo  selectDeviceByPrimaryKey(String deviceName);

    int updateByExampleSelective(@Param("record") CuZteCscf record,
                                 @Param("example") CuZteCscfExample example);

    int updateByExample(@Param("record") CuZteCscf record, @Param("example") CuZteCscfExample example);

    int updateByPrimaryKeySelective(CuZteCscf record);

    int updateByPrimaryKey(CuZteCscf record);

}