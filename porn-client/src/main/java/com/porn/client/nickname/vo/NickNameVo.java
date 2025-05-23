package com.porn.client.nickname.vo;

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
public class NickNameVo extends BaseVo {

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("昵称类型, NickNameTypeEnum, 0-机器人, 1-手工导入")
    private Integer nickNameType;

    @ApiModelProperty("使用状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer status;

}

