package com.porn.web.common.config;

import com.porn.client.log.api.OperateLogApiService;
import com.porn.client.user.api.UserApiService;
import com.porn.web.common.interceptors.AuthInterceptor;
import com.porn.web.common.interceptors.OpLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig
        implements WebMvcConfigurer {
    private static final String[] PATTERNS = new String[]{"/**"};
    private static final String[] EXCLUDEPATTERNS = new String[]{"/common/**", "/mng/business/photo/**", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html/**"};


    @Autowired
    private OperateLogApiService operateLogApiService;


    @Autowired
    private UserApiService userApiService;


    @Autowired
    private RedisTemplate redisTemplate;


    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
                        (HandlerInterceptor) AuthInterceptor.builder()
                                .userApiService(this.userApiService)
                                .redisTemplate(this.redisTemplate)
                                .build())
                .addPathPatterns(PATTERNS)
                .excludePathPatterns(EXCLUDEPATTERNS);


        registry.addInterceptor(
                        (HandlerInterceptor) OpLogInterceptor.builder()
                                .operateLogApiService(this.operateLogApiService)
                                .build())
                .addPathPatterns(PATTERNS)
                .excludePathPatterns(EXCLUDEPATTERNS);
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(new String[]{"*"
                }).allowedMethods(new String[]{"*"
                }).allowCredentials(false)
                .maxAge(3600L);
    }
}

