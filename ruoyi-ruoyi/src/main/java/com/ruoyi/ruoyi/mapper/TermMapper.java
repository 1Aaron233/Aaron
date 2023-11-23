package com.ruoyi.ruoyi.mapper;

import java.util.List;
import com.ruoyi.ruoyi.domain.Term;

/**
 * 学期信息Mapper接口
 * 
 * @author Aaron
 * @date 2023-11-22
 */
public interface TermMapper 
{
    /**
     * 查询学期信息
     * 
     * @param id 学期信息主键
     * @return 学期信息
     */
    public Term selectTermById(Long id);

    /**
     * 查询学期信息列表
     * 
     * @param term 学期信息
     * @return 学期信息集合
     */
    public List<Term> selectTermList(Term term);

    /**
     * 新增学期信息
     * 
     * @param term 学期信息
     * @return 结果
     */
    public int insertTerm(Term term);

    /**
     * 修改学期信息
     * 
     * @param term 学期信息
     * @return 结果
     */
    public int updateTerm(Term term);

    /**
     * 删除学期信息
     * 
     * @param id 学期信息主键
     * @return 结果
     */
    public int deleteTermById(Long id);

    /**
     * 批量删除学期信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTermByIds(Long[] ids);
}
