package com.ruoyi.ruoyi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ruoyi.mapper.TeachDetailMapper;
import com.ruoyi.ruoyi.domain.TeachDetail;
import com.ruoyi.ruoyi.service.ITeachDetailService;

/**
 * 任课信息Service业务层处理
 * 
 * @author Aaron
 * @date 2023-11-22
 */
@Service
public class TeachDetailServiceImpl implements ITeachDetailService 
{
    @Autowired
    private TeachDetailMapper teachDetailMapper;

    /**
     * 查询任课信息
     * 
     * @param id 任课信息主键
     * @return 任课信息
     */
    @Override
    public TeachDetail selectTeachDetailById(Long id)
    {
        return teachDetailMapper.selectTeachDetailById(id);
    }

    /**
     * 查询任课信息列表
     * 
     * @param teachDetail 任课信息
     * @return 任课信息
     */
    @Override
    public List<TeachDetail> selectTeachDetailList(TeachDetail teachDetail)
    {
        return teachDetailMapper.selectTeachDetailList(teachDetail);
    }

    /**
     * 新增任课信息
     * 
     * @param teachDetail 任课信息
     * @return 结果
     */
    @Override
    public int insertTeachDetail(TeachDetail teachDetail)
    {
        teachDetail.setCreateTime(DateUtils.getNowDate());
        return teachDetailMapper.insertTeachDetail(teachDetail);
    }

    /**
     * 修改任课信息
     * 
     * @param teachDetail 任课信息
     * @return 结果
     */
    @Override
    public int updateTeachDetail(TeachDetail teachDetail)
    {
        teachDetail.setUpdateTime(DateUtils.getNowDate());
        return teachDetailMapper.updateTeachDetail(teachDetail);
    }

    /**
     * 批量删除任课信息
     * 
     * @param ids 需要删除的任课信息主键
     * @return 结果
     */
    @Override
    public int deleteTeachDetailByIds(Long[] ids)
    {
        return teachDetailMapper.deleteTeachDetailByIds(ids);
    }

    /**
     * 删除任课信息信息
     * 
     * @param id 任课信息主键
     * @return 结果
     */
    @Override
    public int deleteTeachDetailById(Long id)
    {
        return teachDetailMapper.deleteTeachDetailById(id);
    }
}
