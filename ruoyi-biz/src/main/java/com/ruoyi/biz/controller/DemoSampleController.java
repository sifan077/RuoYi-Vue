package com.ruoyi.biz.controller;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.biz.domain.DemoSample;
import com.ruoyi.biz.service.IDemoSampleService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;

/**
 * 示例业务（MyBatis-Plus）
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/biz/sample")
public class DemoSampleController extends BaseController
{
    @Autowired
    private IDemoSampleService demoSampleService;

    /**
     * 分页列表
     */
    @PreAuthorize("@ss.hasPermi('biz:sample:list')")
    @GetMapping("/list")
    public TableDataInfo list(DemoSample demoSample)
    {
        return getDataTable(demoSampleService.selectDemoSamplePage(demoSample));
    }

    /**
     * 获取详情
     */
    @PreAuthorize("@ss.hasPermi('biz:sample:query')")
    @GetMapping(value = "/{sampleId}")
    public AjaxResult getInfo(@PathVariable Long sampleId)
    {
        return success(demoSampleService.getById(sampleId));
    }

    /**
     * 新增
     */
    @PreAuthorize("@ss.hasPermi('biz:sample:add')")
    @Log(title = "示例业务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody DemoSample demoSample)
    {
        return toAjax(demoSampleService.save(demoSample));
    }

    /**
     * 修改
     */
    @PreAuthorize("@ss.hasPermi('biz:sample:edit')")
    @Log(title = "示例业务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody DemoSample demoSample)
    {
        return toAjax(demoSampleService.updateById(demoSample));
    }

    /**
     * 删除
     */
    @PreAuthorize("@ss.hasPermi('biz:sample:remove')")
    @Log(title = "示例业务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sampleIds}")
    public AjaxResult remove(@PathVariable Long[] sampleIds)
    {
        return toAjax(demoSampleService.removeByIds(Arrays.asList(sampleIds)));
    }
}
