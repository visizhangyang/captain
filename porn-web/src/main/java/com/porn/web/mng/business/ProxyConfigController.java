/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.common.vo.PageVo;
/*    */ import com.porn.client.config.api.ConfigApiService;
/*    */ import com.porn.client.config.dto.ProxyConfigDeleteDTO;
/*    */ import com.porn.client.config.dto.ProxyConfigQueryPageDTO;
/*    */ import com.porn.client.config.dto.ProxyConfigSaveOrUpdateDTO;
/*    */ import com.porn.client.config.vo.ConfigVo;
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
/*    */ @Api(tags = {"后台管理-系统管理-代理配置管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/proxyconfig"})
/*    */ public class ProxyConfigController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private ConfigApiService configApiService;
/*    */   
/*    */   @ApiOperation("分页查询")
/*    */   @PostMapping({"/queryPage"})
/*    */   public Msg<PageVo<ConfigVo>> queryPage(@RequestBody ProxyConfigQueryPageDTO proxyConfigQueryPageDTO) {
/* 33 */     Objects.requireNonNull(this.configApiService); return Msg.executeService(proxyConfigQueryPageDTO, this.configApiService::queryProxyPage);
/*    */   }
/*    */   
/*    */   @ApiOperation("新增或更新")
/*    */   @PostMapping({"/saveOrUpdate"})
/*    */   public Msg<ConfigVo> saveOrUpdate(@RequestBody ProxyConfigSaveOrUpdateDTO proxyConfigSaveOrUpdateDTO) {
/* 39 */     Objects.requireNonNull(this.configApiService); return Msg.executeService(proxyConfigSaveOrUpdateDTO, this.configApiService::proxySaveOrUpdate);
/*    */   }
/*    */   
/*    */   @ApiOperation("删除")
/*    */   @PostMapping({"/delete"})
/*    */   public Msg<Boolean> delete(@RequestBody ProxyConfigDeleteDTO proxyConfigDeleteDTO) {
/* 45 */     Objects.requireNonNull(this.configApiService); return Msg.executeService(proxyConfigDeleteDTO, this.configApiService::proxyDelete);
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/mng/business/ProxyConfigController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */