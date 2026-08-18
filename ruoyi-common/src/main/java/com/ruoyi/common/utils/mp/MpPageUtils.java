package com.ruoyi.common.utils.mp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;

/**
 * MyBatis-Plus 分页工具（与若依前端 pageNum/pageSize、TableDataInfo 对齐）
 * <p>老业务继续用 {@link com.ruoyi.common.utils.PageUtils} + PageHelper；新业务用本工具 + MP 分页插件。</p>
 *
 * @author ruoyi
 */
public final class MpPageUtils
{
    private MpPageUtils()
    {
    }

    /**
     * 从当前 HTTP 请求构建 MP 分页对象。
     * <p>读取参数名与 PageHelper/前端约定一致：{@code pageNum}、{@code pageSize}（见 {@link TableSupport}），
     * 无需写在业务实体字段上，也无需在 Service 方法签名里再声明一遍。</p>
     *
     * @param <T> 实体类型
     * @return Page（已带上请求中的页码与每页条数，默认 1/10）
     */
    public static <T> Page<T> buildPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        return buildPage(pageDomain.getPageNum(), pageDomain.getPageSize());
    }

    /**
     * 构建 MP 分页对象
     *
     * @param pageNum  页码（空则 1）
     * @param pageSize 每页条数（空则 10）
     * @param <T>      实体类型
     * @return Page
     */
    public static <T> Page<T> buildPage(Integer pageNum, Integer pageSize)
    {
        long num = pageNum == null || pageNum < 1 ? 1L : pageNum.longValue();
        long size = pageSize == null || pageSize < 1 ? 10L : pageSize.longValue();
        return new Page<>(num, size);
    }

    /**
     * 将 MP 分页结果转为若依表格数据
     *
     * @param page MP 分页结果
     * @return TableDataInfo
     */
    public static TableDataInfo getDataTable(IPage<?> page)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        if (page != null)
        {
            rspData.setRows(page.getRecords());
            rspData.setTotal(page.getTotal());
        }
        return rspData;
    }
}
