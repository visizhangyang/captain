
package com.porn.service.mobile.api.impl;



import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.plan.api.PlanApiService;
import com.porn.client.plan.api.PlanInsApiService;
import com.porn.client.plan.dto.PlanInsAppendDTO;
import com.porn.client.plan.dto.PlanInsQueryDTO;
import com.porn.client.plan.dto.PlanInsSaveOrUpdateDTO;
import com.porn.client.plan.vo.PlanInsVo;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.dingdingmsg.dto.ProxyMsgDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


































@Service
 public class AppendPlanApiServiceImpl
         implements ApiService<PlanInsVo>
         {

    @Autowired
     private PlanApiService planApiService;

    @Autowired
     private PlanInsApiService planInsApiService;

    @Autowired
     private AccountApiService accountApiService;

    @Autowired
     private DingdingMsgSender dingdingMsgSender;



    public PlanInsVo cmd(CmdRequestDTO cmdRequestDTO) {
        /*  49 */
        PlanInsAppendDTO planInsAppendDTO = (PlanInsAppendDTO) JSON.parseObject(cmdRequestDTO.getData(), PlanInsAppendDTO.class);
        /*  50 */
        PlanInsVo planInsVo = this.planInsApiService.queryPlanIns((
                /*  51 */         (PlanInsQueryDTO.PlanInsQueryDTOBuilder) PlanInsQueryDTO.builder()
/*  52 */.id(planInsAppendDTO.getId()))
/*  53 */.accountId(cmdRequestDTO.getAccountVo().getId())
/*  54 */.planId(planInsAppendDTO.getPlanId())
/*  55 */.build());

        /*  57 */
        if (ObjectUtil.isEmpty(planInsVo)) {
            /*  58 */
            throw new BusinessException("计划实例信息不存在.");

        }



        /*  63 */
        AccountQueryDTO accountQueryDTO = ((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build();
        /*  64 */
        AccountVo accountVo = this.accountApiService.queryAccount(accountQueryDTO);
        /*  65 */
        if (ObjectUtil.isEmpty(accountVo)) {
            /*  66 */
            throw new BusinessException("用户信息不存在.");

        }

        /*  69 */
        if (accountVo.getAvailableBalance().compareTo(planInsAppendDTO.getAppendInvest()) < 0)
             {
            /*  71 */
            throw new BusinessException("余额不足.");

        }








        /*  81 */
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).amountType(AmountTypeEnum.SUBTOTAL_SUBAVAILABLE.getType()).bizId(planInsVo.getId()).streamTypeEnum(StreamTypeEnum.PLAN_APPENDLOCK).operateAmount(planInsAppendDTO.getAppendInvest()).build();
        /*  82 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);


        /*  85 */
        PlanInsVo newPlanInsVo = this.planInsApiService.saveOrUpdate((
                /*  86 */         (PlanInsSaveOrUpdateDTO.PlanInsSaveOrUpdateDTOBuilder) PlanInsSaveOrUpdateDTO.builder()
/*  87 */.id(planInsVo.getId()))
/*  88 */.totalInvest(NumberUtil.add(planInsVo.getTotalInvest(), planInsAppendDTO.getAppendInvest()))
/*  89 */.build());



        /*  93 */
        this.dingdingMsgSender.sendMsg(
                /*  94 */         ProxyMsgDTO.builder()
/*  95 */.accountId(accountVo.getId())
/*  96 */.msg("账户[" + accountVo.getName() + "], 自动搬砖计划, 增加金额[" + planInsAppendDTO.getAppendInvest().stripTrailingZeros().toPlainString() + "], 总金额[" + newPlanInsVo.getTotalInvest().stripTrailingZeros().toPlainString() + "], 请注意观察.")
/*  97 */.build());


        /* 100 */
        return newPlanInsVo;

    }




    public String getApi() {
        /* 105 */
        return "api_appendplan";

    }

}


