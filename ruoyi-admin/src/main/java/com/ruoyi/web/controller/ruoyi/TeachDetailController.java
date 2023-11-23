package com.ruoyi.web.controller.ruoyi;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.ruoyi.domain.TeachDetail;
import com.ruoyi.ruoyi.service.ITeachDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 任课信息Controller
 * 
 * @author Aaron
 * @date 2023-11-22
 */
@RestController
@RequestMapping("/ruoyi/detail")
public class TeachDetailController extends BaseController
{
    @Autowired
    private ITeachDetailService teachDetailService;

    /**
     * 查询任课信息列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeachDetail teachDetail)
    {
        startPage();
        List<TeachDetail> list = teachDetailService.selectTeachDetailList(teachDetail);
        return getDataTable(list);
    }

    /**
     * 导出任课信息列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:detail:export')")
    @Log(title = "任课信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeachDetail teachDetail)
    {
        List<TeachDetail> list = teachDetailService.selectTeachDetailList(teachDetail);
        ExcelUtil<TeachDetail> util = new ExcelUtil<TeachDetail>(TeachDetail.class);
        util.exportExcel(response, list, "任课信息数据");
    }

    /**
     * 获取任课信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(teachDetailService.selectTeachDetailById(id));
    }

    /**
     * 新增任课信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:detail:add')")
    @Log(title = "任课信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeachDetail teachDetail)
    {
        return toAjax(teachDetailService.insertTeachDetail(teachDetail));
    }

    /**
     * 修改任课信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:detail:edit')")
    @Log(title = "任课信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeachDetail teachDetail)
    {
        return toAjax(teachDetailService.updateTeachDetail(teachDetail));
    }

    /**
     * 删除任课信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:detail:remove')")
    @Log(title = "任课信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teachDetailService.deleteTeachDetailByIds(ids));
    }
}
