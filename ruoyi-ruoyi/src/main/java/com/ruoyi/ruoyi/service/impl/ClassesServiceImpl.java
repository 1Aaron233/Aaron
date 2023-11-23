package com.ruoyi.ruoyi.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.ruoyi.domain.Classes;
import com.ruoyi.ruoyi.mapper.ClassesMapper;
import com.ruoyi.ruoyi.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 班级信息Service业务层处理
 * 
 * @author Aaron
 * @date 2023-11-17
 */
@Service
public class ClassesServiceImpl implements IClassesService 
{
    @Autowired
    private ClassesMapper classesMapper;

    /**
     * 查询班级信息
     * 
     * @param id 班级信息主键
     * @return 班级信息
     */
    @Override
    public Classes selectClassesById(Long id)
    {
        return classesMapper.selectClassesById(id);
    }

    /**
     * 查询班级信息列表
     * 
     * @param classes 班级信息
     * @return 班级信息
     */
    @Override
    public List<Classes> selectClassesList(Classes classes)
    {
        return classesMapper.selectClassesList(classes);
    }

    /**
     * 查询班级信息列表
     *
     * @param classes 班级信息
     * @return 班级信息
     */
    @Override
    public List<Classes> selectClassesList1(Classes classes)
    {
        return classesMapper.selectClassesList1(classes);
    }

    /**
     * 新增班级信息
     * 
     * @param classes 班级信息
     * @return 结果
     */
    @Override
    public int insertClasses(Classes classes)
    {
        classes.setCreateTime(DateUtils.getNowDate());
        return classesMapper.insertClasses(classes);
    }

    /**
     * 修改班级信息
     * 
     * @param classes 班级信息
     * @return 结果
     */
    @Override
    public int updateClasses(Classes classes)
    {
        classes.setUpdateTime(DateUtils.getNowDate());
        return classesMapper.updateClasses(classes);
    }

    /**
     * 批量删除班级信息
     * 
     * @param ids 需要删除的班级信息主键
     * @return 结果
     */
    @Override
    public int deleteClassesByIds(Long[] ids)
    {
        return classesMapper.deleteClassesByIds(ids);
    }

    /**
     * 删除班级信息信息
     * 
     * @param id 班级信息主键
     * @return 结果
     */
    @Override
    public int deleteClassesById(Long id)
    {
        return classesMapper.deleteClassesById(id);
    }
}
