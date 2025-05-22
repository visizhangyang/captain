
package com.porn.client.notice.vo;
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
 public class NoticeAccountVo
         extends BaseVo
         {

    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("公告ID")
     private Long noticeId;




}


