package com.porn.client.mobile.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CurTimeVo
        implements Serializable {

    @ApiModelProperty("当前时间")
    private String curDateTime;

}

