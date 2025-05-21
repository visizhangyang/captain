/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.common.vo.PageVo;
/*    */ import com.porn.client.merchant.api.MerchantApiService;
/*    */ import com.porn.client.merchant.api.MerchantDescApiService;
/*    */ import com.porn.client.merchant.dto.MerchantDeleteDTO;
/*    */ import com.porn.client.merchant.dto.MerchantDescQueryDTO;
/*    */ import com.porn.client.merchant.dto.MerchantDescSaveOrUpdateDTO;
/*    */ import com.porn.client.merchant.dto.MerchantEnableOrDisableDTO;
/*    */ import com.porn.client.merchant.dto.MerchantQueryPageDTO;
/*    */ import com.porn.client.merchant.dto.MerchantRobotCreateDTO;
/*    */ import com.porn.client.merchant.dto.MerchantSaveOrUpdateDTO;
/*    */ import com.porn.client.merchant.enums.MerchantTypeEnum;
/*    */ import com.porn.client.merchant.vo.MerchantDescVo;
/*    */ import com.porn.client.merchant.vo.MerchantVo;
/*    */ import com.porn.web.common.controller.BaseController;
/*    */ import com.porn.web.common.msg.Msg;
/*    */ import io.swagger.annotations.Api;
/*    */ import io.swagger.annotations.ApiOperation;
/*    */ import java.util.Objects;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestBody;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ @Api(tags = {"后台管理-业务管理-商户管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/merchant"})
/*    */ public class MerchantController
/*    */   extends BaseController {
/*    */   @Autowired
/*    */   private MerchantApiService merchantApiService;
/*    */   
/*    */   @ApiOperation("查询商户")
/*    */   @PostMapping({"/queryPage"})
/*    */   public Msg<PageVo<MerchantVo>> queryPage(@RequestBody MerchantQueryPageDTO merchantQueryPageDTO) {
/* 39 */     Objects.requireNonNull(this.merchantApiService); return Msg.executeService(merchantQueryPageDTO, this.merchantApiService::queryPage);
/*    */   } @Autowired
/*    */   private MerchantDescApiService merchantDescApiService;
/*    */   @ApiOperation("启用或禁用")
/*    */   @PostMapping({"/enableOrDisable"})
/*    */   public Msg<Boolean> enableOrDisable(@RequestBody MerchantEnableOrDisableDTO merchantEnableOrDisableDTO) {
/* 45 */     Objects.requireNonNull(this.merchantApiService); return Msg.executeService(merchantEnableOrDisableDTO, this.merchantApiService::enableOrDisable);
/*    */   }
/*    */   
/*    */   @ApiOperation("新增或更新")
/*    */   @PostMapping({"/saveOrUpdate"})
/*    */   public Msg<MerchantVo> saveOrUpdate(@RequestBody MerchantSaveOrUpdateDTO merchantSaveOrUpdateDTO) {
/* 51 */     merchantSaveOrUpdateDTO.setMerchantType(MerchantTypeEnum.NORMAL.getType());
/* 52 */     Objects.requireNonNull(this.merchantApiService); return Msg.executeService(merchantSaveOrUpdateDTO, this.merchantApiService::saveOrUpdate);
/*    */   }
/*    */   
/*    */   @ApiOperation("删除")
/*    */   @PostMapping({"/delete"})
/*    */   public Msg<Boolean> delete(@RequestBody MerchantDeleteDTO merchantDeleteDTO) {
/* 58 */     Objects.requireNonNull(this.merchantApiService); return Msg.executeService(merchantDeleteDTO, this.merchantApiService::delete);
/*    */   }
/*    */   
/*    */   @ApiOperation("创建机器人")
/*    */   @PostMapping({"/createRobot"})
/*    */   public Msg<Boolean> createRobot(@RequestBody MerchantRobotCreateDTO merchantRobotCreateDTO) {
/* 64 */     Objects.requireNonNull(this.merchantApiService); return Msg.executeService(merchantRobotCreateDTO, this.merchantApiService::createRobot);
/*    */   }
/*    */   
/*    */   @ApiOperation("新增或更新")
/*    */   @PostMapping({"/saveOrUpdateDesc"})
/*    */   public Msg<MerchantDescVo> saveOrUpdateDesc(@RequestBody MerchantDescSaveOrUpdateDTO merchantDescSaveOrUpdateDTO) {
/* 70 */     Objects.requireNonNull(this.merchantDescApiService); return Msg.executeService(merchantDescSaveOrUpdateDTO, this.merchantDescApiService::saveOrUpdate);
/*    */   }
/*    */   
/*    */   @ApiOperation("查询商户描述")
/*    */   @PostMapping({"/queryDesc"})
/*    */   public Msg<MerchantDescVo> queryDesc(@RequestBody MerchantDescQueryDTO merchantDescQueryDTO) {
/* 76 */     Objects.requireNonNull(this.merchantDescApiService); return Msg.executeService(merchantDescQueryDTO, this.merchantDescApiService::queryMerchantDesc);
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/mng/business/MerchantController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */