package com.ruoyi.ruoyi.mapper;

import com.ruoyi.ruoyi.domain.Classes;

import java.util.List;

/**
 * 班级信息Mapper接口
 * 
 * @author Aaron
 * @date 2023-11-17
 */
public interface ClassesMapper 
{
    /**
     * 查询班级信息
     * 
     * @param id 班级信息主键
     * @return 班级信息
     */
    public Classes selectClassesById(Long id);

    /**
     * 查询班级信息列表
     * 
     * @param classes 班级信息
     * @return 班级信息集合
     */
    public List<Classes> selectClassesList(Classes classes);

    /**
     * 查询班级信息列表
     *
     * @param classes 班级信息
     * @return 班级信息集合
     */
    public List<Classes> selectClassesList1(Classes classes);

    /**
     * 新增班级信息
     * 
     * @param classes 班级信息
     * @return 结果
     */
    public int insertClasses(Classes classes);

    /**
     * 修改班级信息
     * 
     * @param classes 班级信息
     * @return 结果
     */
    public int updateClasses(Classes classes);

    /**
     * 删除班级信息
     * 
     * @param id 班级信息主键
     * @return 结果
     */
    public int deleteClassesById(Long id);

    /**
     * 批量删除班级信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClassesByIds(Long[] ids);
}
