
package com.porn.service.mobile.api.impl;



import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.LotteryApiRequestDTO;
import com.porn.client.mobile.vo.LotteryVo;
import com.porn.client.reward.api.RewardBalanceApiService;
import com.porn.client.reward.dto.OperateRewardBalanceDTO;
import com.porn.client.reward.dto.RewardBalanceQueryDTO;
import com.porn.client.reward.enums.RewardRecordTypeEnum;
import com.porn.client.reward.enums.RuleTypeEnum;
import com.porn.client.reward.vo.RewardBalanceVo;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

































@Service
 public class LotteryApiServiceImpl
         implements ApiService<LotteryVo>
         {
    
    @Autowired
     private RewardBalanceApiService rewardBalanceApiService;
    
    @Autowired
     private AccountApiService accountApiService;

    
    
    public LotteryVo cmd(CmdRequestDTO cmdRequestDTO) {
        /* 44 */
        RewardBalanceQueryDTO rewardBalanceQueryDTO = RewardBalanceQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).build();
        /* 45 */
        RewardBalanceVo rewardBalanceVo = this.rewardBalanceApiService.queryRewardBalance(rewardBalanceQueryDTO);
        /* 46 */
        if (ObjectUtil.isEmpty(rewardBalanceVo)) {
            /* 47 */
            throw new BusinessException("抽奖次数不足.");
            
        }
        /* 49 */
        if (rewardBalanceVo.getAvailableCount().compareTo(BigDecimal.ZERO) <= 0) {
            /* 50 */
            throw new BusinessException("抽奖次数不足.");
            
        }
        
        /* 53 */
        LotteryApiRequestDTO lotteryApiRequestDTO = (LotteryApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), LotteryApiRequestDTO.class);
        /* 54 */
        if (ObjectUtil.isEmpty(lotteryApiRequestDTO.getAmount()) || lotteryApiRequestDTO
/* 55 */.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            /* 56 */
            throw new BusinessException("数据异常.");
            
        }
        
        
        
        
        
        
        
        
        /* 66 */
        OperateRewardBalanceDTO operateRewardBalanceDTO = OperateRewardBalanceDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).operateCount(BigDecimal.ONE).type(RewardRecordTypeEnum.SUB.getType()).bizType(RuleTypeEnum.LOTTERY_RULE.getType()).bizId(CommonConst.IZERO.toString()).build();
        /* 67 */
        this.rewardBalanceApiService.operateRewardBalance(operateRewardBalanceDTO);
        
        
        
        
        
        
        
        
        /* 76 */
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).operateAmount(lotteryApiRequestDTO.getAmount()).bizId(rewardBalanceVo.getId()).streamTypeEnum(StreamTypeEnum.LOTTERY).build();
        /* 77 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);
        
        /* 79 */
        return LotteryVo.builder()
/* 80 */.result(Boolean.TRUE)
/* 81 */.build();
        
    }

    
    
    public String getApi() {
        /* 85 */
        return "api_lottery";
        
    }
    
}


