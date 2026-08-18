package com.ruoyi.framework.config;

import java.util.Date;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;

/**
 * MyBatis-Plus 配置（分页、自动填充）。
 * 仅新业务使用 MP 分页 / 填充；老业务继续使用 PageHelper，互不影响。
 *
 * @author ruoyi
 */
@Configuration
public class MybatisPlusConfig
{
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor()
    {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 自动填充 createBy/createTime/updateBy/updateTime（字段存在时才填充）
     */
    @Bean
    public MetaObjectHandler metaObjectHandler()
    {
        return new MetaObjectHandler()
        {
            @Override
            public void insertFill(MetaObject metaObject)
            {
                Date now = new Date();
                strictInsertFill(metaObject, "createTime", Date.class, now);
                strictInsertFill(metaObject, "updateTime", Date.class, now);
                String username = getUsernameQuietly();
                if (StringUtils.isNotEmpty(username))
                {
                    strictInsertFill(metaObject, "createBy", String.class, username);
                    strictInsertFill(metaObject, "updateBy", String.class, username);
                }
            }

            @Override
            public void updateFill(MetaObject metaObject)
            {
                strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
                String username = getUsernameQuietly();
                if (StringUtils.isNotEmpty(username))
                {
                    strictUpdateFill(metaObject, "updateBy", String.class, username);
                }
            }

            private String getUsernameQuietly()
            {
                try
                {
                    return SecurityUtils.getUsername();
                }
                catch (Exception e)
                {
                    return null;
                }
            }
        };
    }
}
