/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.common.vo.PageVo;
/*    */ import com.porn.client.plan.api.PlanApiService;
/*    */ import com.porn.client.plan.dto.PlanDeleteDTO;
/*    */ import com.porn.client.plan.dto.PlanQueryPageDTO;
/*    */ import com.porn.client.plan.dto.PlanSaveOrUpdateDTO;
/*    */ import com.porn.client.plan.vo.PlanVo;
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
/*    */ @Api(tags = {"后台管理-业务管理-计划管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/plan"})
/*    */ public class PlanController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private PlanApiService planApiService;
/*    */   
/*    */   @ApiOperation("分页查询")
/*    */   @PostMapping({"/queryPage"})
/*    */   public Msg<PageVo<PlanVo>> queryPage(@RequestBody PlanQueryPageDTO planQueryPageDTO) {
/* 35 */     Objects.requireNonNull(this.planApiService); return Msg.executeService(planQueryPageDTO, this.planApiService::queryPage);
/*    */   }
/*    */   
/*    */   @ApiOperation("新增或更新")
/*    */   @PostMapping({"/saveOrUpdate"})
/*    */   public Msg<PlanVo> saveOrUpdate(@RequestBody PlanSaveOrUpdateDTO planSaveOrUpdateDTO) {
/* 41 */     Objects.requireNonNull(this.planApiService); return Msg.executeService(planSaveOrUpdateDTO, this.planApiService::saveOrUpdate);
/*    */   }
/*    */   
/*    */   @ApiOperation("删除")
/*    */   @PostMapping({"/delete"})
/*    */   public Msg<Boolean> delete(@RequestBody PlanDeleteDTO planDeleteDTO) {
/* 47 */     Objects.requireNonNull(this.planApiService); return Msg.executeService(planDeleteDTO, this.planApiService::delete);
/*    */   }
/*    */ }


