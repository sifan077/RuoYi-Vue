package com.ruoyi.biz.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.ruoyi.biz.domain.DemoSample;
import com.ruoyi.biz.mapper.DemoSampleMapper;
import com.ruoyi.biz.service.IDemoSampleService;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.mp.MpPageUtils;

/**
 * 示例业务 Service 实现
 *
 * @author ruoyi
 */
@Service
public class DemoSampleServiceImpl extends ServiceImpl<DemoSampleMapper, DemoSample> implements IDemoSampleService
{
    /**
     * 分页查询。
     * <p>pageNum/pageSize 不放在实体上，与若依一致：由 {@link MpPageUtils#buildPage()} 从当前请求参数读取。</p>
     */
    @Override
    public IPage<DemoSample> selectDemoSamplePage(DemoSample query)
    {
        Page<DemoSample> page = MpPageUtils.buildPage();
        LambdaQueryWrapper<DemoSample> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(query.getSampleName()), DemoSample::getSampleName, query.getSampleName())
            .eq(StringUtils.isNotEmpty(query.getStatus()), DemoSample::getStatus, query.getStatus())
            .orderByDesc(DemoSample::getSampleId);
        return page(page, wrapper);
    }
}
