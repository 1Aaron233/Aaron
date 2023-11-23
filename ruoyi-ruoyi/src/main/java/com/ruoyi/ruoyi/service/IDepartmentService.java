package com.ruoyi.ruoyi.service;

import java.util.List;
import com.ruoyi.ruoyi.domain.Department;

/**
 * 系部Service接口
 * 
 * @author Aaron
 * @date 2023-11-07
 */
public interface IDepartmentService 
{
    /**
     * 查询系部
     * 
     * @param id 系部主键
     * @return 系部
     */
    public Department selectDepartmentById(Long id);

    /**
     * 查询系部列表
     * 
     * @param department 系部
     * @return 系部集合
     */
    public List<Department> selectDepartmentList(Department department);

    /**
     * 新增系部
     * 
     * @param department 系部
     * @return 结果
     */
    public int insertDepartment(Department department);

    /**
     * 修改系部
     * 
     * @param department 系部
     * @return 结果
     */
    public int updateDepartment(Department department);

    /**
     * 批量删除系部
     * 
     * @param ids 需要删除的系部主键集合
     * @return 结果
     */
    public int deleteDepartmentByIds(Long[] ids);

    /**
     * 删除系部信息
     * 
     * @param id 系部主键
     * @return 结果
     */
    public int deleteDepartmentById(Long id);
}
