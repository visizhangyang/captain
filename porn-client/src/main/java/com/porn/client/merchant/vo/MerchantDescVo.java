
package com.porn.client.merchant.vo;
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
 public class MerchantDescVo extends BaseVo {

    @ApiModelProperty("商户ID")
     private Long merchantId;

    @ApiModelProperty("语言类型, LangTypeEnum")
     private Integer langType;

    @ApiModelProperty("内容")
     private String content;



}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/merchant/vo/MerchantDescVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */