
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
         extends DefaultSqlInjector
         {

    @Bean
     public MybatisPlusInterceptor paginationInterceptor() {
        /* 34 */
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        /* 36 */
        interceptor.addInnerInterceptor((InnerInterceptor) new PaginationInnerInterceptor(DbType.MYSQL));
        /* 37 */
        return interceptor;

    }







    @Bean
     public FillObjectHandler fillObjectHandler() {
        /* 46 */
        return new FillObjectHandler();

    }





    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        /* 52 */
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        /* 53 */
        methodList.add(new InsertBatchSomeColumn());
        /* 54 */
        return methodList;

    }




       public static class FillObjectHandler
             implements MetaObjectHandler
             {

        public void insertFill(MetaObject metaObject) {
            /* 63 */
            UserLoginVo userLoginVo = UserHolder.getUser();

            /* 65 */
            LocalDateTime now = LocalDateTime.now();
            /* 66 */
            setFieldValByName("createTime", now, metaObject);
            /* 67 */
            setFieldValByName("modifyTime", now, metaObject);
            /* 68 */
            setFieldValByName("createBy", Long.valueOf(ObjectUtil.isEmpty(userLoginVo) ? -1L : userLoginVo.getId().longValue()), metaObject);
            /* 69 */
            setFieldValByName("modifyBy", Long.valueOf(ObjectUtil.isEmpty(userLoginVo) ? -1L : userLoginVo.getId().longValue()), metaObject);
            /* 70 */
            setFieldValByName("delFlag", DelFlagEnum.NORMAL.getFlag(), metaObject);

        }



        public void updateFill(MetaObject metaObject) {
            /* 74 */
            UserLoginVo userLoginVo = UserHolder.getUser();

            /* 76 */
            LocalDateTime now = LocalDateTime.now();
            /* 77 */
            setFieldValByName("modifyTime", now, metaObject);
            /* 78 */
            setFieldValByName("modifyBy", Long.valueOf(ObjectUtil.isEmpty(userLoginVo) ? -1L : userLoginVo.getId().longValue()), metaObject);

        }

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/common/config/MyBatisPlusConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */