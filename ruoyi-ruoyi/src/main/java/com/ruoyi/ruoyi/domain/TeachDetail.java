package com.ruoyi.ruoyi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任课信息对象 ruoyi_teach_detail
 * 
 * @author Aaron
 * @date 2023-11-22
 */
public class TeachDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 学期编号 */
    @Excel(name = "学期编号")
    private String termId;

    /** 班级编号 */
    @Excel(name = "班级编号")
    private String classId;

    /** 课程编号 */
    @Excel(name = "课程编号")
    private String courseId;

    /** 教师工号 */
    @Excel(name = "教师工号")
    private String teacherId;

    /** 上课地点 */
    @Excel(name = "上课地点")
    private String courseLocation;

    /** 上课时间 */
    @Excel(name = "上课时间")
    private String courseTime;

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
    public void setClassId(String classId) 
    {
        this.classId = classId;
    }

    public String getClassId() 
    {
        return classId;
    }
    public void setCourseId(String courseId) 
    {
        this.courseId = courseId;
    }

    public String getCourseId() 
    {
        return courseId;
    }
    public void setTeacherId(String teacherId) 
    {
        this.teacherId = teacherId;
    }

    public String getTeacherId() 
    {
        return teacherId;
    }
    public void setCourseLocation(String courseLocation) 
    {
        this.courseLocation = courseLocation;
    }

    public String getCourseLocation() 
    {
        return courseLocation;
    }
    public void setCourseTime(String courseTime) 
    {
        this.courseTime = courseTime;
    }

    public String getCourseTime() 
    {
        return courseTime;
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
            .append("classId", getClassId())
            .append("courseId", getCourseId())
            .append("teacherId", getTeacherId())
            .append("courseLocation", getCourseLocation())
            .append("courseTime", getCourseTime())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
