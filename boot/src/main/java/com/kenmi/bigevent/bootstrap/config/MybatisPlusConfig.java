package com.kenmi.bigevent.bootstrap.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.kenmi.bigevent.bootstrap.injector.EasySqlInjector;
import com.kenmi.bigevent.common.constants.CommonConstants;
import com.kenmi.bigevent.common.utils.UserInfoThreadHolder;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;
import java.util.Objects;

/**
 * MyBatis-Plus 配置
 *
 * @author andrew
 */
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

    /**
     * 分页配置
     *
     * @return 分页拦截器
     */
    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        return new PaginationInnerInterceptor();
    }

    /**
     * 批量插入配置
     *
     * @return 批量插入注入器
     */
    @Bean
    @Primary
    public EasySqlInjector easySqlInjector() {
        return new EasySqlInjector();
    }

    /**
     * 自动补充插入或更新时的值
     *
     * @return MetaObjectHandler
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                Date now = new Date();
                this.setFieldValByName("gmtCreate", now, metaObject);
                this.setFieldValByName("createdBy", Objects.isNull(UserInfoThreadHolder.getCurrentUser()) ?
                        CommonConstants.SYSTEM_PARAM_SYSTEM_ID : UserInfoThreadHolder.getCurrentUser().getUserId(), metaObject);
                this.setFieldValByName("gmtModify", now, metaObject);
                this.setFieldValByName("modifiedBy", Objects.isNull(UserInfoThreadHolder.getCurrentUser()) ?
                        CommonConstants.SYSTEM_PARAM_SYSTEM_ID : UserInfoThreadHolder.getCurrentUser().getUserId(), metaObject);
                this.setFieldValByName("deleted", 0, metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName("gmtModify", new Date(), metaObject);
                this.setFieldValByName("modifiedBy", Objects.isNull(UserInfoThreadHolder.getCurrentUser()) ?
                        CommonConstants.SYSTEM_PARAM_SYSTEM_ID : UserInfoThreadHolder.getCurrentUser().getUserId(), metaObject);
            }
        };
    }
}