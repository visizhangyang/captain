/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.common.vo.PageVo;
/*    */ import com.porn.client.imglib.api.ImageLibApiService;
/*    */ import com.porn.client.imglib.dto.ImageLibBatchDeleteDTO;
/*    */ import com.porn.client.imglib.dto.ImageLibQueryPageDTO;
/*    */ import com.porn.client.imglib.dto.ImageLibSaveOrUpdateDTO;
/*    */ import com.porn.client.imglib.vo.ImageLibVo;
/*    */ import com.porn.web.common.controller.BaseController;
/*    */ import com.porn.web.common.msg.Msg;
/*    */ import io.swagger.annotations.Api;
/*    */ import io.swagger.annotations.ApiOperation;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestBody;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Api(tags = {"后台管理-业务管理-图片管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/imagelib"})
/*    */ public class ImageLibController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private ImageLibApiService imageLibApiService;
/*    */   
/*    */   @ApiOperation("查询")
/*    */   @PostMapping({"/queryPage"})
/*    */   public Msg<PageVo<ImageLibVo>> queryPage(@RequestBody ImageLibQueryPageDTO imageLibQueryPageDTO) {
/* 37 */     Objects.requireNonNull(this.imageLibApiService); return Msg.executeService(imageLibQueryPageDTO, this.imageLibApiService::queryPage);
/*    */   }
/*    */   
/*    */   @ApiOperation("批量插入")
/*    */   @PostMapping({"/batchCreate"})
/*    */   public Msg<Boolean> batchCreate(@RequestBody List<ImageLibSaveOrUpdateDTO> imageLibSaveOrUpdateDTOList) {
/* 43 */     Objects.requireNonNull(this.imageLibApiService); return Msg.executeSimpleService(imageLibSaveOrUpdateDTOList, this.imageLibApiService::batchSaveImageLib);
/*    */   }
/*    */   
/*    */   @ApiOperation("批量删除")
/*    */   @PostMapping({"/batchDelete"})
/*    */   public Msg<Boolean> batchDelete(@RequestBody ImageLibBatchDeleteDTO imageLibBatchDeleteDTO) {
/* 49 */     Objects.requireNonNull(this.imageLibApiService); return Msg.executeService(imageLibBatchDeleteDTO, this.imageLibApiService::batchDelete);
/*    */   }
/*    */ }


