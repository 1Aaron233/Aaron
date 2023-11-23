package com.ruoyi.ruoyi.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.ruoyi.domain.Department;
import com.ruoyi.ruoyi.mapper.DepartmentMapper;
import com.ruoyi.ruoyi.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系部Service业务层处理
 * 
 * @author Aaron
 * @date 2023-11-07
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService 
{
    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 查询系部
     * 
     * @param id 系部主键
     * @return 系部
     */
    @Override
    public Department selectDepartmentById(Long id)
    {
        return departmentMapper.selectDepartmentById(id);
    }

    /**
     * 查询系部列表
     * 
     * @param department 系部
     * @return 系部
     */
    @Override
    public List<Department> selectDepartmentList(Department department)
    {
        return departmentMapper.selectDepartmentList(department);
    }

    /**
     * 新增系部
     * 
     * @param department 系部
     * @return 结果
     */
    @Override
    public int insertDepartment(Department department)
    {
//        设置创建时间 getNowDate()把时间设置为当前的时间，把时间设计进去
        department.setCreateTime(DateUtils.getNowDate());
        return departmentMapper.insertDepartment(department);
    }

    /**
     * 修改系部
     * 
     * @param department 系部
     * @return 结果
     */
    @Override
    public int updateDepartment(Department department)
    {
        department.setUpdateTime(DateUtils.getNowDate());
        return departmentMapper.updateDepartment(department);
    }

    /**
     * 批量删除系部
     * 
     * @param ids 需要删除的系部主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentByIds(Long[] ids)
    {
        return departmentMapper.deleteDepartmentByIds(ids);
    }

    /**
     * 删除系部信息
     * 
     * @param id 系部主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentById(Long id)
    {
        return departmentMapper.deleteDepartmentById(id);
    }
}
