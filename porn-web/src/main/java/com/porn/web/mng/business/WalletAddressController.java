/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.common.vo.PageVo;
/*    */ import com.porn.client.wallet.api.WalletAddressApiService;
/*    */ import com.porn.client.wallet.dto.WalletAddressDeleteDTO;
/*    */ import com.porn.client.wallet.dto.WalletAddressEnableOrDisableDTO;
/*    */ import com.porn.client.wallet.dto.WalletAddressQueryPageDTO;
/*    */ import com.porn.client.wallet.dto.WalletAddressSaveOrUpdateDTO;
/*    */ import com.porn.client.wallet.dto.WalletAddressUpdateRemarkDTO;
/*    */ import com.porn.client.wallet.vo.WalletAddressVo;
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
/*    */ @Api(tags = {"后台管理-业务管理-收款地址管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/walletaddress"})
/*    */ public class WalletAddressController extends BaseController {
/*    */   @Autowired
/*    */   private WalletAddressApiService walletAddressApiService;
/*    */   
/*    */   @ApiOperation("查询钱包")
/*    */   @PostMapping({"/queryPage"})
/*    */   public Msg<PageVo<WalletAddressVo>> queryPage(@RequestBody WalletAddressQueryPageDTO walletAddressQueryPageDTO) {
/* 33 */     Objects.requireNonNull(this.walletAddressApiService);
              return Msg.executeService(walletAddressQueryPageDTO, this.walletAddressApiService::queryPage);
/*    */   }
/*    */   
/*    */   @ApiOperation("启用或禁用")
/*    */   @PostMapping({"/enableOrDisable"})
/*    */   public Msg<Boolean> enableOrDisable(@RequestBody WalletAddressEnableOrDisableDTO walletAddressEnableOrDisableDTO) {
/* 39 */     Objects.requireNonNull(this.walletAddressApiService); return Msg.executeService(walletAddressEnableOrDisableDTO, this.walletAddressApiService::enableOrDisable);
/*    */   }
/*    */   
/*    */   @ApiOperation("新增或更新地址")
/*    */   @PostMapping({"/saveOrUpdate"})
/*    */   public Msg<WalletAddressVo> saveOrUpdate(@RequestBody WalletAddressSaveOrUpdateDTO walletAddressSaveOrUpdateDTO) {
/* 45 */     Objects.requireNonNull(this.walletAddressApiService); return Msg.executeService(walletAddressSaveOrUpdateDTO, this.walletAddressApiService::saveOrUpdate);
/*    */   }
/*    */   
/*    */   @ApiOperation("删除")
/*    */   @PostMapping({"/delete"})
/*    */   public Msg<Boolean> delete(@RequestBody WalletAddressDeleteDTO walletAddressDeleteDTO) {
/* 51 */     Objects.requireNonNull(this.walletAddressApiService); return Msg.executeService(walletAddressDeleteDTO, this.walletAddressApiService::delete);
/*    */   }
/*    */   
/*    */   @ApiOperation("更新备注")
/*    */   @PostMapping({"/updateRemark"})
/*    */   public Msg<Boolean> updateRemark(@RequestBody WalletAddressUpdateRemarkDTO walletAddressUpdateRemarkDTO) {
/* 57 */     Objects.requireNonNull(this.walletAddressApiService); return Msg.executeService(walletAddressUpdateRemarkDTO, this.walletAddressApiService::updateRemark);
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/mng/business/WalletAddressController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */