package com.porn.web.common.interceptors;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.common.holder.UserHolder;
import com.porn.client.log.api.OperateLogApiService;
import com.porn.client.log.dto.OperateLogSaveDTO;
import com.porn.client.user.vo.UserLoginVo;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;

public class OpLogInterceptor
        implements HandlerInterceptor {
    public static final String OPLOG_OBJ = "oplog_obj";
    private OperateLogApiService operateLogApiService;

    public OpLogInterceptor(OperateLogApiService operateLogApiService) {
        this.operateLogApiService = operateLogApiService;
    }

    public static OpLogInterceptorBuilder builder() {
        return new OpLogInterceptorBuilder();
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserLoginVo userLoginVo = UserHolder.getUser();
        String params = "";
        if (HttpMethod.GET.toString().equalsIgnoreCase(request.getMethod())) {
            params = JSON.toJSONString(request.getParameterMap());
        } else {
            params = IoUtil.read((InputStream) request.getInputStream(), Charset.forName("UTF-8"));
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;


        OperateLogSaveDTO operateLogSaveDTO = OperateLogSaveDTO.builder().userId(Long.valueOf(ObjectUtil.isEmpty(userLoginVo) ? -1L : userLoginVo.getId().longValue())).name(ObjectUtil.isEmpty(userLoginVo) ? "" : userLoginVo.getName()).method(request.getMethod()).action(StrUtil.join(".", new Object[]{handlerMethod.getBean().getClass().getSimpleName(), handlerMethod.getMethod().getName()})).params(params).startTime(Long.valueOf(System.currentTimeMillis())).build();
        request.setAttribute("oplog_obj", operateLogSaveDTO);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        OperateLogSaveDTO operateLogSaveDTO = (OperateLogSaveDTO) request.getAttribute("oplog_obj");
        operateLogSaveDTO.setEndTime(Long.valueOf(System.currentTimeMillis()));
        CompletableFuture.runAsync(() -> {

        });
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    public static class OpLogInterceptorBuilder {
        private OperateLogApiService operateLogApiService;

        public OpLogInterceptorBuilder operateLogApiService(OperateLogApiService operateLogApiService) {
            this.operateLogApiService = operateLogApiService;
            return this;
        }

        public OpLogInterceptor build() {
            return new OpLogInterceptor(this.operateLogApiService);
        }

        public String toString() {
            return "OpLogInterceptor.OpLogInterceptorBuilder(operateLogApiService=" + this.operateLogApiService + ")";
        }
    }
}

