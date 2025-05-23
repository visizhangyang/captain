package com.porn.client.server.vo;

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
public class MemoryInfoVo implements Serializable {
    @ApiModelProperty("内存总量")
    private double total;

    @ApiModelProperty("已用内存")
    private double used;

    @ApiModelProperty("剩余内存")
    private double free;

    @ApiModelProperty("使用率")
    private double usage;

}