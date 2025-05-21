/*    */ package com.porn.web.mng.business;
/*    */ 
/*    */ import com.porn.client.common.entity.PairPlus;
/*    */ import com.porn.client.common.req.ServiceRequest;
/*    */ import com.porn.client.reward.api.RewardRuleApiService;
/*    */ import com.porn.client.reward.dto.RewardRuleQueryDTO;
/*    */ import com.porn.client.reward.dto.RewardRuleSaveOrUpdateDTO;
/*    */ import com.porn.client.reward.dto.TurntableQueryDTO;
/*    */ import com.porn.client.reward.dto.TurntableSaveOrUpdateDTO;
/*    */ import com.porn.client.reward.enums.RuleTypeEnum;
/*    */ import com.porn.client.reward.vo.RewardRuleVo;
/*    */ import com.porn.client.reward.vo.TurntableVo;
/*    */ import com.porn.web.common.controller.BaseController;
/*    */ import com.porn.web.common.msg.Msg;
/*    */ import io.swagger.annotations.Api;
/*    */ import io.swagger.annotations.ApiOperation;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import java.util.stream.Collectors;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestBody;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Api(tags = {"后台管理-业务管理-描述管理"})
/*    */ @RestController
/*    */ @RequestMapping({"/mng/business/rewardrule"})
/*    */ public class RewardRuleController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private RewardRuleApiService rewardRuleApiService;
/*    */   
/*    */   @ApiOperation("查询")
/*    */   @PostMapping({"/queryRewardRuleList"})
/*    */   public Msg<List<RewardRuleVo>> queryRewardRuleList(@RequestBody RewardRuleQueryDTO rewardRuleQueryDTO) {
/* 42 */     Objects.requireNonNull(this.rewardRuleApiService); return Msg.executeService(rewardRuleQueryDTO, this.rewardRuleApiService::queryRewardRuleList);
/*    */   }
/*    */   
/*    */   @ApiOperation("查询")
/*    */   @PostMapping({"/queryRewardRule"})
/*    */   public Msg<RewardRuleVo> queryRewardRule(@RequestBody RewardRuleQueryDTO rewardRuleQueryDTO) {
/* 48 */     Objects.requireNonNull(this.rewardRuleApiService); return Msg.executeService(rewardRuleQueryDTO, this.rewardRuleApiService::queryRewardRule);
/*    */   }
/*    */   
/*    */   @ApiOperation("查询规则类型")
/*    */   @PostMapping({"/queryRewardTypeList"})
/*    */   public Msg<List<PairPlus>> queryRewardTypeList() {
/* 54 */     return Msg.success(Arrays.<RuleTypeEnum>stream(RuleTypeEnum.values()).filter(rt -> (rt.getType().intValue() >= 0)).map(rt -> PairPlus.of(rt.getType(), rt.getName(), rt.getDescription())).collect(Collectors.toList()));
/*    */   }
/*    */   
/*    */   @ApiOperation("保存或更新")
/*    */   @PostMapping({"/saveOrUpdate"})
/*    */   public Msg<RewardRuleVo> saveOrUpdate(@RequestBody RewardRuleSaveOrUpdateDTO rewardRuleSaveOrUpdateDTO) {
/* 60 */     Objects.requireNonNull(this.rewardRuleApiService); return Msg.executeService(rewardRuleSaveOrUpdateDTO, this.rewardRuleApiService::saveOrUpdate);
/*    */   }
/*    */   
/*    */   @ApiOperation("查询")
/*    */   @PostMapping({"/queryTurntable"})
/*    */   public Msg<TurntableVo> queryTurntable(@RequestBody TurntableQueryDTO turntableQueryDTO) {
/* 66 */     Objects.requireNonNull(this.rewardRuleApiService); return Msg.executeService(turntableQueryDTO, this.rewardRuleApiService::queryTurntable);
/*    */   }
/*    */   
/*    */   @ApiOperation("保存或更新")
/*    */   @PostMapping({"/saveOrUpdateTurntable"})
/*    */   public Msg<TurntableVo> saveOrUpdateTurntable(@RequestBody TurntableSaveOrUpdateDTO turntableSaveOrUpdateDTO) {
/* 72 */     Objects.requireNonNull(this.rewardRuleApiService); return Msg.executeService(turntableSaveOrUpdateDTO, this.rewardRuleApiService::saveOrUpdateTurntable);
/*    */   }
/*    */ }


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-web-3.3.0.jar!/com/porn/web/mng/business/RewardRuleController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */