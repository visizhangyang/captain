package com.porn.client.message.vo;

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
public class MessageVo
        extends BaseVo {

    @ApiModelProperty("消息体")
    private String msg;

    @ApiModelProperty("账户id")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String accountName;

}

