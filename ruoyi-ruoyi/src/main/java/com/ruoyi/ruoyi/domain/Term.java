package com.ruoyi.ruoyi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学期信息对象 ruoyi_term
 * 
 * @author Aaron
 * @date 2023-11-22
 */
public class Term extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 学期编号 */
    @Excel(name = "学期编号")
    private String termId;

    /** 开始学年 */
    @Excel(name = "开始学年")
    private Long yearBegin;

    /** 结束学年 */
    @Excel(name = "结束学年")
    private Long yearEnd;

    /** 第几学期 */
    @Excel(name = "第几学期")
    private Long yearIndex;

    /** 学期描述 */
    @Excel(name = "学期描述")
    private String termDesc;

    /** 状态（0正常 1暂停） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=暂停")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTermId(String termId) 
    {
        this.termId = termId;
    }

    public String getTermId() 
    {
        return termId;
    }
    public void setYearBegin(Long yearBegin) 
    {
        this.yearBegin = yearBegin;
    }

    public Long getYearBegin() 
    {
        return yearBegin;
    }
    public void setYearEnd(Long yearEnd) 
    {
        this.yearEnd = yearEnd;
    }

    public Long getYearEnd() 
    {
        return yearEnd;
    }
    public void setYearIndex(Long yearIndex) 
    {
        this.yearIndex = yearIndex;
    }

    public Long getYearIndex() 
    {
        return yearIndex;
    }
    public void setTermDesc(String termDesc) 
    {
        this.termDesc = termDesc;
    }

    public String getTermDesc() 
    {
        return termDesc;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("termId", getTermId())
            .append("yearBegin", getYearBegin())
            .append("yearEnd", getYearEnd())
            .append("yearIndex", getYearIndex())
            .append("termDesc", getTermDesc())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
