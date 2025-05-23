package com.porn.client.notice.vo;

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
public class NoticeAccountVo
        extends BaseVo {

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("公告ID")
    private Long noticeId;

}

