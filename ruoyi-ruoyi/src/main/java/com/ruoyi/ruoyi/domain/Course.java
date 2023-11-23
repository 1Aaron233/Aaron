package com.ruoyi.ruoyi.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程信息对象 ruoyi_course
 * 
 * @author Aaron
 * @date 2023-11-22
 */
public class Course extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 课程编号 */
    @Excel(name = "课程编号")
    private String courseId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 学分 */
    @Excel(name = "学分")
    private Long credit;

    /** 课时 */
    @Excel(name = "课时")
    private Long creditHours;

    /** 所属系部编号 */
    @Excel(name = "所属系部编号")
    private String departmentId;

    /** 课程类型（0公共基础课 1专业平台课 2专业模块课 3公共选修课 4专业拓展课 5毕业环节） */
    @Excel(name = "课程类型", readConverterExp = "0=公共基础课,1=专业平台课,2=专业模块课,3=公共选修课,4=专业拓展课,5=毕业环节")
    private String courseType;

    /** 状态（0正常 1暂停） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=暂停")
    private String status;

    @Excels({
            @Excel(name = "所属系部名称", targetAttr = "departmentName"),
            @Excel(name = "所属系部地址", targetAttr = "address"),
    })
    private Department department;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCourseId(String courseId) 
    {
        this.courseId = courseId;
    }

    public String getCourseId() 
    {
        return courseId;
    }
    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
    }
    public void setCredit(Long credit) 
    {
        this.credit = credit;
    }

    public Long getCredit() 
    {
        return credit;
    }
    public void setCreditHours(Long creditHours) 
    {
        this.creditHours = creditHours;
    }

    public Long getCreditHours() 
    {
        return creditHours;
    }
    public void setDepartmentId(String departmentId) 
    {
        this.departmentId = departmentId;
    }

    public String getDepartmentId() 
    {
        return departmentId;
    }
    public void setCourseType(String courseType) 
    {
        this.courseType = courseType;
    }

    public String getCourseType() 
    {
        return courseType;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credit=" + credit +
                ", creditHours=" + creditHours +
                ", departmentId='" + departmentId + '\'' +
                ", courseType='" + courseType + '\'' +
                ", status='" + status + '\'' +
                ", department=" + department +
                '}';
    }
}
