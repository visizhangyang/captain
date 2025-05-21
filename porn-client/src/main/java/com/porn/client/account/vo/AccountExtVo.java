package com.porn.client.account.vo;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/vo/AccountExtVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */