package com.ruoyi.web.controller.ruoyi;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.ruoyi.domain.Term;
import com.ruoyi.ruoyi.service.ITermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 学期信息Controller
 * 
 * @author Aaron
 * @date 2023-11-22
 */
@RestController
@RequestMapping("/ruoyi/term")
public class TermController extends BaseController
{
    @Autowired
    private ITermService termService;

    /**
     * 查询学期信息列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:term:list')")
    @GetMapping("/list")
    public TableDataInfo list(Term term)
    {
        startPage();
        List<Term> list = termService.selectTermList(term);
        return getDataTable(list);
    }

    /**
     * 导出学期信息列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:term:export')")
    @Log(title = "学期信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Term term)
    {
        List<Term> list = termService.selectTermList(term);
        ExcelUtil<Term> util = new ExcelUtil<Term>(Term.class);
        util.exportExcel(response, list, "学期信息数据");
    }

    /**
     * 获取学期信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:term:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(termService.selectTermById(id));
    }

    /**
     * 新增学期信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:term:add')")
    @Log(title = "学期信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Term term)
    {
        term.setTermId(term.getYearBegin()+"-"+term.getYearEnd()+"-"+term.getYearIndex());
        term.setTermDesc(term.getYearBegin()+"至"+term.getYearEnd()+"学年度第"+term.getYearIndex()+"学期");

        Term t = new Term();
        t.setTermId(term.getTermId());
        if(termService.selectTermList(t).size()>0){
            return AjaxResult.error("该学期信息已经存在，不能重复添加。");
       };
        return toAjax(termService.insertTerm(term));
    }

    /**
     * 修改学期信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:term:edit')")
    @Log(title = "学期信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Term term)
    {
        return toAjax(termService.updateTerm(term));
    }

    /**
     * 删除学期信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi:term:remove')")
    @Log(title = "学期信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(termService.deleteTermByIds(ids));
    }
}
