
package com.porn.client.account.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;




@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class AccountStatisticsVo
         implements Serializable
         {

    @ApiModelProperty("总账户数量")
     private Long totalAccountCount;

    @ApiModelProperty("今天新增数量")
     private Long todayAccountCount;



}


