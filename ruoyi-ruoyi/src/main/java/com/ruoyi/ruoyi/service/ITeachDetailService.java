package com.ruoyi.ruoyi.service;

import java.util.List;
import com.ruoyi.ruoyi.domain.TeachDetail;

/**
 * 任课信息Service接口
 * 
 * @author Aaron
 * @date 2023-11-22
 */
public interface ITeachDetailService 
{
    /**
     * 查询任课信息
     * 
     * @param id 任课信息主键
     * @return 任课信息
     */
    public TeachDetail selectTeachDetailById(Long id);

    /**
     * 查询任课信息列表
     * 
     * @param teachDetail 任课信息
     * @return 任课信息集合
     */
    public List<TeachDetail> selectTeachDetailList(TeachDetail teachDetail);

    /**
     * 新增任课信息
     * 
     * @param teachDetail 任课信息
     * @return 结果
     */
    public int insertTeachDetail(TeachDetail teachDetail);

    /**
     * 修改任课信息
     * 
     * @param teachDetail 任课信息
     * @return 结果
     */
    public int updateTeachDetail(TeachDetail teachDetail);

    /**
     * 批量删除任课信息
     * 
     * @param ids 需要删除的任课信息主键集合
     * @return 结果
     */
    public int deleteTeachDetailByIds(Long[] ids);

    /**
     * 删除任课信息信息
     * 
     * @param id 任课信息主键
     * @return 结果
     */
    public int deleteTeachDetailById(Long id);
}
