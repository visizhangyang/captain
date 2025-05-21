/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.common.vo.PageVo;
/*    */ import com.porn.client.recommendapp.api.RecommendAppService;
/*    */ import com.porn.client.recommendapp.dto.RecommendAppDeleteDTO;
/*    */ import com.porn.client.recommendapp.dto.RecommendAppQueryPageDTO;
/*    */ import com.porn.client.recommendapp.dto.RecommendAppSaveOrUpdateDTO;
/*    */ import com.porn.client.recommendapp.vo.RecommendAppVo;
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
/*    */ 
/*    */ 
/*    */ @Api(tags = {"后台管理-业务管理-app推荐"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/recommendapp"})
/*    */ public class RecommendAppController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private RecommendAppService recommendAppService;
/*    */   
/*    */   @ApiOperation("分页查询")
/*    */   @PostMapping({"/queryPage"})
/*    */   public Msg<PageVo<RecommendAppVo>> queryPage(@RequestBody RecommendAppQueryPageDTO recommendAppQueryPageDTO) {
/* 35 */     Objects.requireNonNull(this.recommendAppService); return Msg.executeService(recommendAppQueryPageDTO, this.recommendAppService::queryPage);
/*    */   }
/*    */   
/*    */   @ApiOperation("新增或更新")
/*    */   @PostMapping({"/saveOrUpdate"})
/*    */   public Msg<RecommendAppVo> saveOrUpdate(@RequestBody RecommendAppSaveOrUpdateDTO recommendAppSaveOrUpdateDTO) {
/* 41 */     Objects.requireNonNull(this.recommendAppService); return Msg.executeService(recommendAppSaveOrUpdateDTO, this.recommendAppService::saveOrUpdate);
/*    */   }
/*    */   
/*    */   @ApiOperation("删除")
/*    */   @PostMapping({"/delete"})
/*    */   public Msg<Boolean> delete(@RequestBody RecommendAppDeleteDTO recommendAppDeleteDTO) {
/* 47 */     Objects.requireNonNull(this.recommendAppService); return Msg.executeService(recommendAppDeleteDTO, this.recommendAppService::delete);
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/mng/business/RecommendAppController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */