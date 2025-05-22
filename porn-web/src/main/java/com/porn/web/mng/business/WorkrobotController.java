/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.workrobot.api.WorkrobotApiService;
/*    */ import com.porn.client.workrobot.dto.WorkrobotQueryDTO;
/*    */ import com.porn.client.workrobot.dto.WorkrobotSaveOrUpdateDTO;
/*    */ import com.porn.client.workrobot.vo.WorkrobotVo;
/*    */ import com.porn.service.workrobot.cron.WorkrobotCron;
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
/*    */ @Api(tags = {"后台管理-业务管理-搬砖机器人"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/workrobot"})
/*    */ public class WorkrobotController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private WorkrobotApiService workrobotApiService;
/*    */   @Autowired
/*    */   private WorkrobotCron workrobotCron;
/*    */   
/*    */   @ApiOperation("查询")
/*    */   @PostMapping({"/queryWorkrobotList"})
/*    */   public Msg<List<WorkrobotVo>> queryWorkrobotList(@RequestBody WorkrobotQueryDTO workrobotQueryDTO) {
/* 38 */     Objects.requireNonNull(this.workrobotApiService); return Msg.executeService(workrobotQueryDTO, this.workrobotApiService::queryWorkrobotList);
/*    */   }
/*    */   
/*    */   @ApiOperation("新增或更新")
/*    */   @PostMapping({"/batchCreate"})
/*    */   public Msg<Boolean> saveOrUpdate(@RequestBody List<WorkrobotSaveOrUpdateDTO> workrobotSaveOrUpdateDTOList) {
/* 44 */     Objects.requireNonNull(this.workrobotApiService); return Msg.executeSimpleService(workrobotSaveOrUpdateDTOList, this.workrobotApiService::batchCreate);
/*    */   }
/*    */   
/*    */   @ApiOperation("刷新缓存")
/*    */   @PostMapping({"/refreshCache"})
/*    */   public Msg<Boolean> refreshCache() {
/* 50 */     this.workrobotCron.refreshCache();
/* 51 */     return Msg.success(Boolean.TRUE);
/*    */   }
/*    */ }


