package com.ruoyi.ruoyi.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 班级信息对象 ruoyi_classes
 * 
 * @author Aaron
 * @date 2023-11-17
 */
public class Classes extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 班级id */
    @Excel(name = "班级id")
    private String classId;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String className;

    /** 所属班级编号 */
    @Excel(name = "所属班级编号")
    private String departmentId;

    /** 班主任姓名 */
    @Excel(name = "班主任姓名")
    private String headMaster;

    /** 班主任联系电话 */
    @Excel(name = "班主任联系电话")
    private String headMasterPhone;

    /** 班级类型(0高中班 1中专3+3 2预科班) */
    @Excel(name = "班级类型",readConverterExp = "0=高中班,1=中专3+3，2=预科班")
    private String classType;

    /** 状态（0正常 1暂停） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=暂停")
    private String status;


    @Excels({
            @Excel(name = "所属系部名称",targetAttr = "departmentName"),
            @Excel(name = "所属系部地址",targetAttr = "address"),
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
    public void setClassId(String classId) 
    {
        this.classId = classId;
    }

    public String getClassId() 
    {
        return classId;
    }
    public void setClassName(String className) 
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
    }
    public void setDepartmentId(String departmentId) 
    {
        this.departmentId = departmentId;
    }

    public String getDepartmentId() 
    {
        return departmentId;
    }
    public void setHeadMaster(String headMaster) 
    {
        this.headMaster = headMaster;
    }

    public String getHeadMaster() 
    {
        return headMaster;
    }
    public void setHeadMasterPhone(String headMasterPhone) 
    {
        this.headMasterPhone = headMasterPhone;
    }

    public String getHeadMasterPhone() 
    {
        return headMasterPhone;
    }
    public void setClassType(String classType) 
    {
        this.classType = classType;
    }

    public String getClassType() 
    {
        return classType;
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
        return "Classes{" +
                "id=" + id +
                ", classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", headMaster='" + headMaster + '\'' +
                ", headMasterPhone='" + headMasterPhone + '\'' +
                ", classType='" + classType + '\'' +
                ", status='" + status + '\'' +
                ", department=" + department +
                '}';
    }
}
