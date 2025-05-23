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
        implements ApiService<TurntableVo> {

    @Autowired
    private RewardRuleApiService rewardRuleApiService;


    public TurntableVo cmd(CmdRequestDTO cmdRequestDTO) {

        TurntableVo turntableVo = this.rewardRuleApiService.queryTurntable(TurntableQueryDTO.builder().build());

        if (ObjectUtil.isNotEmpty(turntableVo.getNum0())) {

            turntableVo.getNum0().setScale(0);

        }

        if (ObjectUtil.isNotEmpty(turntableVo.getNum1())) {

            turntableVo.getNum1().setScale(0);

        }

        if (ObjectUtil.isNotEmpty(turntableVo.getNum2())) {

            turntableVo.getNum2().setScale(0);

        }

        if (ObjectUtil.isNotEmpty(turntableVo.getNum3())) {

            turntableVo.getNum3().setScale(0);

        }

        if (ObjectUtil.isNotEmpty(turntableVo.getNum4())) {

            turntableVo.getNum4().setScale(0);

        }

        if (ObjectUtil.isNotEmpty(turntableVo.getNum5())) {

            turntableVo.getNum5().setScale(0);

        }

        if (ObjectUtil.isNotEmpty(turntableVo.getNum6())) {

            turntableVo.getNum6().setScale(0);

        }

        if (ObjectUtil.isNotEmpty(turntableVo.getNum7())) {

            turntableVo.getNum7().setScale(0);

        }

        return turntableVo;

    }


    public String getApi() {

        return "api_queryturntable";

    }

}

