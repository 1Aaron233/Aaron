package com.ruoyi.web.controller.ruoyi;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.ruoyi.domain.Department;
import com.ruoyi.ruoyi.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系部Controller
 * 
 * @author Aaron
 * @date 2023-11-07
 */
@RestController
@RequestMapping("/ruoyi/department")
public class DepartmentController extends BaseController
{
    @Autowired
    private IDepartmentService departmentService;

    /**
     * 查询系部列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:department:list')")
    @GetMapping("/list")
    public TableDataInfo list(Department department)
    {
        startPage();
        List<Department> list = departmentService.selectDepartmentList(department);
//       封装成指定的数据
        return getDataTable(list);
    }

    /**
     * 查询全部系部列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:department:list')")
    @GetMapping("/listAll")
    public TableDataInfo listAll()
    {
        //传入一个空对象
        Department temp = new Department();
        List<Department> list = departmentService.selectDepartmentList(temp);
//       封装成指定的数据
        return getDataTable(list);
    }

    /**
     * 导出系部列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:department:export')")
    @Log(title = "系部", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Department department)
    {
        List<Department> list = departmentService.selectDepartmentList(department);
        ExcelUtil<Department> util = new ExcelUtil<Department>(Department.class);
        util.exportExcel(response, list, "系部数据");
    }

    /**
     * 获取系部详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:department:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(departmentService.selectDepartmentById(id));
    }

    /**
     * 新增系部
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:department:add')")
//     @Log日志注解
    @Log(title = "系部", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Department department)
    {
        //构建一个新的对象，然后去查询
        Department temp = new Department();
        temp.setDepartmentId(department.getDepartmentId());
        List<Department> result = departmentService.selectDepartmentList(temp);
        if (result.size()>0){
            return AjaxResult.error("该部门编号已经存在，不能重复添加");
        }
        //        设置getUsername（），createby
        department.setCreateBy(getUsername());
        return toAjax(departmentService.insertDepartment(department));
    }

    /**
     * 修改系部
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:department:edit')")
    @Log(title = "系部", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Department department)
    {
        department.setUpdateBy(getUsername());
        return toAjax(departmentService.updateDepartment(department));
    }

    /**
     * 删除系部
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:department:remove')")
    @Log(title = "系部", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(departmentService.deleteDepartmentByIds(ids));
    }
}
