package com.porn.service.common.config;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.holder.UserHolder;
import com.porn.client.user.vo.UserLoginVo;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class MyBatisPlusConfig
        extends DefaultSqlInjector {

    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        interceptor.addInnerInterceptor((InnerInterceptor) new PaginationInnerInterceptor(DbType.MYSQL));

        return interceptor;

    }


    @Bean
    public FillObjectHandler fillObjectHandler() {

        return new FillObjectHandler();

    }


    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {

        List<AbstractMethod> methodList = super.getMethodList(mapperClass);

        methodList.add(new InsertBatchSomeColumn());

        return methodList;

    }

    public static class FillObjectHandler
            implements MetaObjectHandler {

        public void insertFill(MetaObject metaObject) {

            UserLoginVo userLoginVo = UserHolder.getUser();

            LocalDateTime now = LocalDateTime.now();

            setFieldValByName("createTime", now, metaObject);

            setFieldValByName("modifyTime", now, metaObject);

            setFieldValByName("createBy", Long.valueOf(ObjectUtil.isEmpty(userLoginVo) ? -1L : userLoginVo.getId().longValue()), metaObject);

            setFieldValByName("modifyBy", Long.valueOf(ObjectUtil.isEmpty(userLoginVo) ? -1L : userLoginVo.getId().longValue()), metaObject);

            setFieldValByName("delFlag", DelFlagEnum.NORMAL.getFlag(), metaObject);

        }


        public void updateFill(MetaObject metaObject) {

            UserLoginVo userLoginVo = UserHolder.getUser();

            LocalDateTime now = LocalDateTime.now();

            setFieldValByName("modifyTime", now, metaObject);

            setFieldValByName("modifyBy", Long.valueOf(ObjectUtil.isEmpty(userLoginVo) ? -1L : userLoginVo.getId().longValue()), metaObject);

        }

    }

}

