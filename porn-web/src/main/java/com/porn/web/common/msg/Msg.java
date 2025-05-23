package com.porn.web.common.msg;


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
public class Msg<T> implements Serializable {
    public static final String MESSAGE_ERROR = ResultFlagEnum.CODE_FAIL.getDescription();
    public static final int CODE_SUCCESS = ResultFlagEnum.CODE_SUCCESS.getFlag().intValue();
    public static final int CODE_ERROR = ResultFlagEnum.CODE_FAIL.getFlag().intValue();
    public static final String MESSAGE_SUCCESS = ResultFlagEnum.CODE_SUCCESS.getDescription();
    private static final Logger log = LoggerFactory.getLogger(Msg.class);
    @ApiModelProperty("响应代码, 200标识正确")
    private int code;
    @ApiModelProperty("结果集")
    private T body;
    @ApiModelProperty("错误信息.")
    private String message;

    public static <T> Msg<T> success() {
        return Msg.<T>builder()
                .code(CODE_SUCCESS)
                .message(MESSAGE_SUCCESS)
                .build();
    }

    public static <T> Msg<T> success(T body) {

        return Msg.<T>builder()
                .code(CODE_SUCCESS)
                .message(MESSAGE_SUCCESS)
                .body(body)
                .build();

    }


    public static <T> Msg<T> fail() {

        return Msg.<T>builder()
                .message(MESSAGE_ERROR)
                .code(CODE_ERROR)
                .build();

    }


    public static <T> Msg<T> authFail() {

        return Msg.<T>builder()
                .message(ResultFlagEnum.CODE_AUTHFAIL.getDescription())
                .code(ResultFlagEnum.CODE_AUTHFAIL.getFlag().intValue())
                .build();

    }


    public static <T> Msg<T> error(Exception e) {

        return Msg.<T>builder()
                .message(e.getMessage())
                .code(CODE_ERROR)
                .build();

    }


    public static <R, T> Msg<T> execute(R request, Function<R, T> func) {

        try {

            T body = func.apply(request);

            return success(body);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

            return error(e);

        }

    }


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


    public static <R, T> Msg<T> executeSimpleService(R request, Function<R, T> func) {

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

        } catch (AuthException authException) {

            log.error(authException.getMessage(), (Throwable) authException);

            return authFail();

        } catch (Exception e) {

            log.error(e.getMessage(), e);

            return error(e);

        }

    }


    public static <T> Msg<T> executeService(Supplier<T> func) {

        try {

            return success(func.get());

        } catch (Exception e) {

            log.error(e.getMessage(), e);

            return error(e);

        }

    }

}

