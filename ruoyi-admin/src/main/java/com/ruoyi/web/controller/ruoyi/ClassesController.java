package com.ruoyi.web.controller.ruoyi;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.ruoyi.domain.Classes;
import com.ruoyi.ruoyi.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 班级信息Controller
 * 
 * @author Aaron
 * @date 2023-11-17
 */
@RestController
@RequestMapping("/ruoyi/classes")
public class ClassesController extends BaseController
{
    @Autowired
    private IClassesService classesService;

    /**
     * 查询班级信息列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:classes:list')")
    @GetMapping("/list")
    public TableDataInfo list(Classes classes)
    {
        startPage();
        List<Classes> list = classesService.selectClassesList(classes);
        return getDataTable(list);
    }

    /**
     * 导出班级信息列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:classes:export')")
    @Log(title = "班级信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Classes classes)
    {
        List<Classes> list = classesService.selectClassesList(classes);
        ExcelUtil<Classes> util = new ExcelUtil<Classes>(Classes.class);
        util.exportExcel(response, list, "班级信息数据");
    }

    /**
     * 获取班级信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:classes:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(classesService.selectClassesById(id));
    }

    /**
     * 新增班级信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:classes:add')")
    @Log(title = "班级信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Classes classes)
    {

        //构建一个新的对象，然后去查询
        Classes temp = new Classes();
        temp.setClassId(classes.getClassId());
        List<Classes> result = classesService.selectClassesList1(temp);
        if (result.size()>0){
            return AjaxResult.error("该班级编号已经存在，不能重复添加");
        }

        Classes temp1 = new Classes();
        temp1.setClassName(classes.getClassName());
        List<Classes> result1 = classesService.selectClassesList1(temp1);
        if (result1.size()>0){
            return AjaxResult.error("该班级名称已经存在，不能重复添加");
        }

        //        设置getUsername（），createby
        classes.setCreateBy(getUsername());
        return toAjax(classesService.insertClasses(classes));
    }

    /**
     * 修改班级信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:classes:edit')")
    @Log(title = "班级信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Classes classes)
    {
        classes.setUpdateBy(getUsername());
        return toAjax(classesService.updateClasses(classes));
    }

    /**
     * 删除班级信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:classes:remove')")
    @Log(title = "班级信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(classesService.deleteClassesByIds(ids));
    }
}
