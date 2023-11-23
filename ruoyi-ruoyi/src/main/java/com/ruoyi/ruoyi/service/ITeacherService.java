package com.ruoyi.ruoyi.service;

import java.util.List;
import com.ruoyi.ruoyi.domain.Teacher;

/**
 * 教师信息Service接口
 * 
 * @author Aaron
 * @date 2023-11-22
 */
public interface ITeacherService 
{
    /**
     * 查询教师信息
     * 
     * @param id 教师信息主键
     * @return 教师信息
     */
    public Teacher selectTeacherById(Long id);

    /**
     * 查询教师信息列表
     * 
     * @param teacher 教师信息
     * @return 教师信息集合
     */
    public List<Teacher> selectTeacherList(Teacher teacher);

    /**
     * 新增教师信息
     * 
     * @param teacher 教师信息
     * @return 结果
     */
    public int insertTeacher(Teacher teacher);

    /**
     * 修改教师信息
     * 
     * @param teacher 教师信息
     * @return 结果
     */
    public int updateTeacher(Teacher teacher);

    /**
     * 批量删除教师信息
     * 
     * @param ids 需要删除的教师信息主键集合
     * @return 结果
     */
    public int deleteTeacherByIds(Long[] ids);

    /**
     * 删除教师信息信息
     * 
     * @param id 教师信息主键
     * @return 结果
     */
    public int deleteTeacherById(Long id);
}
