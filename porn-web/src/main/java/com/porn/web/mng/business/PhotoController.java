/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.common.vo.PageVo;
/*    */ import com.porn.client.photo.api.PhotoApiService;
/*    */ import com.porn.client.photo.dto.PhotoQueryPageDTO;
/*    */ import com.porn.client.photo.vo.PhotoVo;
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
/*    */ @Api(tags = {"后台管理-业务管理-相册管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/photo"})
/*    */ public class PhotoController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private PhotoApiService photoApiService;
/*    */   
/*    */   @ApiOperation("查询")
/*    */   @PostMapping({"/queryPage"})
/*    */   public Msg<PageVo<PhotoVo>> queryPage(@RequestBody PhotoQueryPageDTO photoQueryPageDTO) {
/* 33 */     Objects.requireNonNull(this.photoApiService); return Msg.executeService(photoQueryPageDTO, this.photoApiService::queryPage);
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/mng/business/PhotoController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */