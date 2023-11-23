package com.ruoyi.ruoyi.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 教师信息对象 ruoyi_teacher
 * 
 * @author Aaron
 * @date 2023-11-22
 */
public class Teacher extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 教师工号
     */
    @Excel(name = "教师工号")
    private String teacherId;

    /**
     * 教师姓名
     */
    @Excel(name = "教师姓名")
    private String teacherName;

    /**
     * 所属系部编号
     */
    @Excel(name = "所属系部编号")
    private String departmentId;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话")
    private String phone;

    /**
     * 性别（0女 1男）
     */
    @Excel(name = "性别", readConverterExp = "0=女,1=男")
    private String gender;

    /**
     * 状态（0正常 1暂停）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=暂停")
    private String status;

    @Excels({
            @Excel(name = "所属系部名称", targetAttr = "departmentName"),
            @Excel(name = "所属系部地址", targetAttr = "address"),
    })
    private Department department;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
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
        return "Teacher{" +
                "id=" + id +
                ", teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                ", department=" + department +
                '}';
    }
}
