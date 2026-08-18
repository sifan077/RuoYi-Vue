package com.ruoyi.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.spring.service.IService;
import com.ruoyi.biz.domain.DemoSample;

/**
 * 示例业务 Service（MyBatis-Plus）
 *
 * @author ruoyi
 */
public interface IDemoSampleService extends IService<DemoSample>
{
    /**
     * 分页查询示例列表（查询条件与排序在 Service 内组装）
     *
     * @param query 查询条件
     * @return MP 分页结果
     */
    IPage<DemoSample> selectDemoSamplePage(DemoSample query);
}
