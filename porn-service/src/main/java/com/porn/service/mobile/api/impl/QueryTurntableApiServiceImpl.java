
package com.porn.service.mobile.api.impl;



import cn.hutool.core.util.ObjectUtil;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.reward.api.RewardRuleApiService;
import com.porn.client.reward.dto.TurntableQueryDTO;
import com.porn.client.reward.vo.TurntableVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

















@Service
 public class QueryTurntableApiServiceImpl
         implements ApiService<TurntableVo>
         {
    
    @Autowired
     private RewardRuleApiService rewardRuleApiService;

    
    
    public TurntableVo cmd(CmdRequestDTO cmdRequestDTO) {
        /* 26 */
        TurntableVo turntableVo = this.rewardRuleApiService.queryTurntable(TurntableQueryDTO.builder().build());
        /* 27 */
        if (ObjectUtil.isNotEmpty(turntableVo.getNum0())) {
            /* 28 */
            turntableVo.getNum0().setScale(0);
            
        }
        /* 30 */
        if (ObjectUtil.isNotEmpty(turntableVo.getNum1())) {
            /* 31 */
            turntableVo.getNum1().setScale(0);
            
        }
        /* 33 */
        if (ObjectUtil.isNotEmpty(turntableVo.getNum2())) {
            /* 34 */
            turntableVo.getNum2().setScale(0);
            
        }
        /* 36 */
        if (ObjectUtil.isNotEmpty(turntableVo.getNum3())) {
            /* 37 */
            turntableVo.getNum3().setScale(0);
            
        }
        /* 39 */
        if (ObjectUtil.isNotEmpty(turntableVo.getNum4())) {
            /* 40 */
            turntableVo.getNum4().setScale(0);
            
        }
        /* 42 */
        if (ObjectUtil.isNotEmpty(turntableVo.getNum5())) {
            /* 43 */
            turntableVo.getNum5().setScale(0);
            
        }
        /* 45 */
        if (ObjectUtil.isNotEmpty(turntableVo.getNum6())) {
            /* 46 */
            turntableVo.getNum6().setScale(0);
            
        }
        /* 48 */
        if (ObjectUtil.isNotEmpty(turntableVo.getNum7())) {
            /* 49 */
            turntableVo.getNum7().setScale(0);
            
        }
        /* 51 */
        return turntableVo;
        
    }

    
    
    public String getApi() {
        /* 55 */
        return "api_queryturntable";
        
    }
    
}


