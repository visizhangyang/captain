/*     */
package com.porn.web.common.msg;
/*     */
/*     */

import cn.hutool.core.util.ObjectUtil;
import com.porn.client.common.dto.AbstractDTO;
import com.porn.client.common.enums.ResultFlagEnum;
import com.porn.client.common.exceptions.AuthException;
import com.porn.client.common.holder.UserHolder;
import com.porn.client.common.req.ServiceRequest;
import com.porn.client.user.vo.UserLoginVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.function.Function;
import java.util.function.Supplier;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Msg<T>  implements Serializable
{
    private static final Logger log = LoggerFactory.getLogger(Msg.class);
    public static final String MESSAGE_ERROR = ResultFlagEnum.CODE_FAIL.getDescription();
    @ApiModelProperty("响应代码, 200标识正确")
    private int code;
    @ApiModelProperty("结果集")
    private T body;
    @ApiModelProperty("错误信息.")
    private String message;


    public static final int CODE_SUCCESS = ResultFlagEnum.CODE_SUCCESS.getFlag().intValue();

      public static final int CODE_ERROR = ResultFlagEnum.CODE_FAIL.getFlag().intValue();
      public static final String MESSAGE_SUCCESS = ResultFlagEnum.CODE_SUCCESS.getDescription();



    public static <T> Msg<T> success() {
        return Msg.<T>builder()
        .code(CODE_SUCCESS)
        .message(MESSAGE_SUCCESS)
        .build();
    }

    public static <T> Msg<T> success(T body) {
        /*  62 */
        return Msg.<T>builder()
/*  63 */.code(CODE_SUCCESS)
/*  64 */.message(MESSAGE_SUCCESS)
/*  65 */.body(body)
/*  66 */.build();
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    public static <T> Msg<T> fail() {
        /*  75 */
        return Msg.<T>builder()
/*  76 */.message(MESSAGE_ERROR)
/*  77 */.code(CODE_ERROR)
/*  78 */.build();
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    public static <T> Msg<T> authFail() {
        /*  87 */
        return Msg.<T>builder()
/*  88 */.message(ResultFlagEnum.CODE_AUTHFAIL.getDescription())
/*  89 */.code(ResultFlagEnum.CODE_AUTHFAIL.getFlag().intValue())
/*  90 */.build();
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    public static <T> Msg<T> error(Exception e) {
        /* 100 */
        return Msg.<T>builder()
/* 101 */.message(e.getMessage())
/* 102 */.code(CODE_ERROR)
/* 103 */.build();
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    public static <R, T> Msg<T> execute(R request, Function<R, T> func) {
        /*     */
        try {
            /* 116 */
            T body = func.apply(request);
            /* 117 */
            return success(body);
            /* 118 */
        } catch (Exception e) {
            /* 119 */
            log.error(e.getMessage(), e);
            /* 120 */
            return error(e);
            /*     */
        }
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    public static <R extends ServiceRequest, T> Msg<T> executeService(R request, Function<R, T> func) {
        try {
            if (request instanceof AbstractDTO) {
                AbstractDTO baseDTO = (AbstractDTO) request;
                UserLoginVo userLoginVo = UserHolder.getUser();
                if (ObjectUtil.isNotEmpty(userLoginVo)) {
                    baseDTO.setCurrentUserId(userLoginVo.getId());
                    baseDTO.setCurrentUserName(userLoginVo.getName());
                }
            }
            return success(func.apply(request));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return error(e);
        }
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    public static <R, T> Msg<T> executeSimpleService(R request, Function<R, T> func) {
        /*     */
        try {
            /* 155 */
            if (request instanceof AbstractDTO) {
                /* 156 */
                AbstractDTO baseDTO = (AbstractDTO) request;
                /* 157 */
                UserLoginVo userLoginVo = UserHolder.getUser();
                /* 158 */
                if (ObjectUtil.isNotEmpty(userLoginVo)) {
                    /* 159 */
                    baseDTO.setCurrentUserId(userLoginVo.getId());
                    /* 160 */
                    baseDTO.setCurrentUserName(userLoginVo.getName());
                    /*     */
                }
                /*     */
            }
            /* 163 */
            return success(func.apply(request));
            /* 164 */
        } catch (AuthException authException) {
            /* 165 */
            log.error(authException.getMessage(), (Throwable) authException);
            /* 166 */
            return authFail();
            /* 167 */
        } catch (Exception e) {
            /* 168 */
            log.error(e.getMessage(), e);
            /* 169 */
            return error(e);
            /*     */
        }
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    public static <T> Msg<T> executeService(Supplier<T> func) {
        /*     */
        try {
            /* 180 */
            return success(func.get());
            /* 181 */
        } catch (Exception e) {
            /* 182 */
            log.error(e.getMessage(), e);
            /* 183 */
            return error(e);
            /*     */
        }
        /*     */
    }
    /*     */
}

