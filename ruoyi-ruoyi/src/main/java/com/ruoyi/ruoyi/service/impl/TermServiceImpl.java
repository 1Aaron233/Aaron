package com.ruoyi.ruoyi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ruoyi.mapper.TermMapper;
import com.ruoyi.ruoyi.domain.Term;
import com.ruoyi.ruoyi.service.ITermService;

/**
 * 学期信息Service业务层处理
 * 
 * @author Aaron
 * @date 2023-11-22
 */
@Service
public class TermServiceImpl implements ITermService 
{
    @Autowired
    private TermMapper termMapper;

    /**
     * 查询学期信息
     * 
     * @param id 学期信息主键
     * @return 学期信息
     */
    @Override
    public Term selectTermById(Long id)
    {
        return termMapper.selectTermById(id);
    }

    /**
     * 查询学期信息列表
     * 
     * @param term 学期信息
     * @return 学期信息
     */
    @Override
    public List<Term> selectTermList(Term term)
    {
        return termMapper.selectTermList(term);
    }

    /**
     * 新增学期信息
     * 
     * @param term 学期信息
     * @return 结果
     */
    @Override
    public int insertTerm(Term term)
    {
        term.setCreateTime(DateUtils.getNowDate());
        return termMapper.insertTerm(term);
    }

    /**
     * 修改学期信息
     * 
     * @param term 学期信息
     * @return 结果
     */
    @Override
    public int updateTerm(Term term)
    {
        term.setUpdateTime(DateUtils.getNowDate());
        return termMapper.updateTerm(term);
    }

    /**
     * 批量删除学期信息
     * 
     * @param ids 需要删除的学期信息主键
     * @return 结果
     */
    @Override
    public int deleteTermByIds(Long[] ids)
    {
        return termMapper.deleteTermByIds(ids);
    }

    /**
     * 删除学期信息信息
     * 
     * @param id 学期信息主键
     * @return 结果
     */
    @Override
    public int deleteTermById(Long id)
    {
        return termMapper.deleteTermById(id);
    }
}
