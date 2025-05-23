package com.porn.client.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PageVo<V> implements Serializable {

    @ApiModelProperty("当前页码值")
    private Integer pageStart;

    @ApiModelProperty("每页数量")
    private Integer pageSize;

    @ApiModelProperty("数据条数")
    private Long total;

    @ApiModelProperty("一页数据")
    private List<V> data;

}

