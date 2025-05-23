package com.porn.web.common.interceptors;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.common.holder.UserHolder;
import com.porn.client.user.api.UserApiService;
import com.porn.client.user.vo.UserLoginVo;
import com.porn.web.common.msg.Msg;

import java.nio.charset.Charset;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class AuthInterceptor
        implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);
    private UserApiService userApiService;
    private RedisTemplate redisTemplate;

    public AuthInterceptor(UserApiService userApiService, RedisTemplate redisTemplate) {
        this.userApiService = userApiService;
        this.redisTemplate = redisTemplate;
    }

    public static AuthInterceptorBuilder builder() {
        return new AuthInterceptorBuilder();
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (ObjectUtil.isEmpty(token)) {


            response.setHeader("Content-Type", "application/json;charset=utf-8");
            response.getOutputStream().write(JSON.toJSONString(Msg.authFail()).getBytes(Charset.forName("UTF-8")));
            response.getOutputStream().flush();
            return false;
        }

        String userJson = (String) this.redisTemplate.opsForValue().get(String.format("login:%s", new Object[]{token}));
        if (ObjectUtil.isEmpty(userJson)) {

            response.setHeader("Content-Type", "application/json;charset=utf-8");
            response.getOutputStream().write(JSON.toJSONString(Msg.authFail()).getBytes(Charset.forName("UTF-8")));
            response.getOutputStream().flush();
            return false;
        }


        UserLoginVo userLoginVo = (UserLoginVo) JSON.parseObject(userJson, UserLoginVo.class);
        UserHolder.setUser(userLoginVo);

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserHolder.removeUser();
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }

    public static class AuthInterceptorBuilder {
        private UserApiService userApiService;
        private RedisTemplate redisTemplate;

        public AuthInterceptorBuilder userApiService(UserApiService userApiService) {
            this.userApiService = userApiService;
            return this;
        }

        public AuthInterceptorBuilder redisTemplate(RedisTemplate redisTemplate) {
            this.redisTemplate = redisTemplate;
            return this;
        }

        public AuthInterceptor build() {
            return new AuthInterceptor(this.userApiService, this.redisTemplate);
        }

        public String toString() {
            return "AuthInterceptor.AuthInterceptorBuilder(userApiService=" + this.userApiService + ", redisTemplate=" + this.redisTemplate + ")";
        }
    }
}

