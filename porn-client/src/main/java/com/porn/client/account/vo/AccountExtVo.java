package com.porn.client.account.vo;

import com.porn.client.common.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountExtVo extends BaseVo {
    @ApiModelProperty("账户id")
    private Long accountId;

    @ApiModelProperty("扩展类型, AccountExtTypeEnum")
    private Integer extType;

    @ApiModelProperty("扩展key")
    private String extKey;

    @ApiModelProperty("扩展值")
    private String extValue;

    @ApiModelProperty("冗余字段1")
    private String attr1;

    @ApiModelProperty("冗余字段2")
    private String attr2;

    @ApiModelProperty("冗余字段3")
    private String attr3;

}

