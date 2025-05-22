
package com.porn.service.mobile.api.impl;



import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.reward.api.RewardBalanceApiService;
import com.porn.client.reward.dto.RewardBalanceQueryDTO;
import com.porn.client.reward.vo.RewardBalanceLuckVo;
import com.porn.client.reward.vo.RewardBalanceVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
























@Service
 public class QueryRewardBalanceApiServiceImpl
         implements ApiService<RewardBalanceLuckVo>
         {
    
    @Autowired
     private RewardBalanceApiService rewardBalanceApiService;

    
    
    public RewardBalanceLuckVo cmd(CmdRequestDTO cmdRequestDTO) {
        /* 33 */
        RewardBalanceQueryDTO rewardBalanceQueryDTO = RewardBalanceQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).build();
        /* 34 */
        RewardBalanceVo rewardBalanceVo = this.rewardBalanceApiService.queryRewardBalance(rewardBalanceQueryDTO);
        /* 35 */
        if (ObjectUtil.isNotEmpty(rewardBalanceVo) &&
                /* 36 */       ObjectUtil.isNotEmpty(rewardBalanceVo.getAvailableCount())) {
            /* 37 */
            rewardBalanceVo.getAvailableCount().setScale(0);
            
        }
        
        
        
        
        
        /* 44 */
        RewardBalanceLuckVo rewardBalanceLuckVo = ((RewardBalanceLuckVo.RewardBalanceLuckVoBuilder) ((RewardBalanceLuckVo.RewardBalanceLuckVoBuilder) RewardBalanceLuckVo.builder().accountId(cmdRequestDTO.getAccountVo().getId())).availableCount(ObjectUtil.isEmpty(rewardBalanceVo) ? BigDecimal.ZERO : rewardBalanceVo.getAvailableCount())).luckIndex(Integer.valueOf(RandomUtil.randomInt(0, 3))).build();
        /* 45 */
        return rewardBalanceLuckVo;
        
    }

    
    
    public String getApi() {
        /* 49 */
        return "api_queryrewardbalance";
        
    }
    
}


