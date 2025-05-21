
package com.porn.service.reward.impl;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.config.api.ConfigApiService;
import com.porn.client.config.dto.ConfigQueryDTO;
import com.porn.client.config.dto.ConfigSaveOrUpdateDTO;
import com.porn.client.config.vo.ConfigVo;
import com.porn.client.minio.api.MinioApiService;
import com.porn.client.minio.dto.PrevFileDTO;
import com.porn.client.minio.vo.PrevFileVo;
import com.porn.client.reward.api.RewardRuleApiService;
import com.porn.client.reward.dto.RewardRuleQueryDTO;
import com.porn.client.reward.dto.RewardRuleSaveOrUpdateDTO;
import com.porn.client.reward.dto.TurntableQueryDTO;
import com.porn.client.reward.dto.TurntableSaveOrUpdateDTO;
import com.porn.client.reward.vo.RewardRuleVo;
import com.porn.client.reward.vo.TurntableVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.reward.converter.RewardRuleConverter;
import com.porn.service.reward.dao.entity.RewardRuleDO;
import com.porn.service.reward.dao.mapper.RewardRuleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Service

@Transactional(rollbackFor = {Exception.class})
 public class RewardRuleApiServiceImpl implements RewardRuleApiService {
    /*  40 */   private static final Logger log = LoggerFactory.getLogger(RewardRuleApiServiceImpl.class);



    @Autowired
     private RewardRuleMapper rewardRuleMapper;



    @Autowired
     private RewardRuleConverter rewardRuleConverter;



    @Autowired
     private MinioApiService minioApiService;


    @Autowired
     private ConfigApiService configApiService;




    public RewardRuleVo queryRewardRule(RewardRuleQueryDTO rewardRuleQueryDTO) {
        /*  59 */
        List<RewardRuleVo> rewardRuleVoList = queryRewardRuleList(rewardRuleQueryDTO);
        /*  60 */
        return ObjectUtil.isEmpty(rewardRuleVoList) ? null : rewardRuleVoList.get(0);

    }










    public List<RewardRuleVo> queryRewardRuleList(RewardRuleQueryDTO rewardRuleQueryDTO) {
        /*  71 */
        List<RewardRuleDO> rewardRuleList =   ChainWrappers.lambdaQueryChain(rewardRuleMapper)
                .eq(ObjectUtil.isNotEmpty(rewardRuleQueryDTO.getId()), BaseDO::getId, rewardRuleQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(rewardRuleQueryDTO.getRuleType()), RewardRuleDO::getRuleType, rewardRuleQueryDTO.getRuleType())
                .eq(ObjectUtil.isNotEmpty(rewardRuleQueryDTO.getLangType()), RewardRuleDO::getLangType, rewardRuleQueryDTO.getLangType())
                .in(ObjectUtil.isNotEmpty(rewardRuleQueryDTO.getRuleTypeList()), RewardRuleDO::getRuleType, rewardRuleQueryDTO.getRuleTypeList())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByAsc(RewardRuleDO::getRuleType)
                .list();
        /*  72 */
        if (ObjectUtil.isEmpty(rewardRuleList)) {
            /*  73 */
            return Collections.emptyList();

        }
        /*  75 */
        List<RewardRuleVo> rewardRuleVoList = this.rewardRuleConverter.toRewardRuleVoList(rewardRuleList);
        /*  76 */
        if (ObjectUtil.isNotEmpty(rewardRuleVoList)) {
            /*  77 */
            for (RewardRuleVo rewardRuleVo : rewardRuleVoList) {
                /*  78 */
                if (ObjectUtil.isNotEmpty(rewardRuleVo.getRuleImg())) {
                    /*  79 */
                    PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(rewardRuleVo.getRuleImg()).build());
                    /*  80 */
                    if (ObjectUtil.isNotEmpty(prevFileVo)) {
                        /*  81 */
                        rewardRuleVo.setRuleImgUrl(prevFileVo.getFileUrl());

                    }

                }

            }

        }
        /*  86 */
        return rewardRuleVoList;

    }



    public RewardRuleVo saveOrUpdate(RewardRuleSaveOrUpdateDTO rewardRuleSaveOrUpdateDTO) {
        /*  90 */
        if (ObjectUtil.isEmpty(rewardRuleSaveOrUpdateDTO.getId())) {
            /*  91 */
            RewardRuleDO rewardRuleDO = this.rewardRuleConverter.toRewardRuleDO(rewardRuleSaveOrUpdateDTO);
            /*  92 */
            if (this.rewardRuleMapper.insert(rewardRuleDO) <= 0) {
                /*  93 */
                throw new BusinessException("保存抽奖信息失败.");

            }
            /*  95 */
            return queryRewardRule(((RewardRuleQueryDTO.RewardRuleQueryDTOBuilder) RewardRuleQueryDTO.builder().id(rewardRuleDO.getId())).build());

        }










        /* 107 */
        boolean rs =  ChainWrappers.lambdaUpdateChain(rewardRuleMapper)
                .set(RewardRuleDO::getName, rewardRuleSaveOrUpdateDTO.getName())
                .set(RewardRuleDO::getSubName, rewardRuleSaveOrUpdateDTO.getSubName())
                .set(RewardRuleDO::getRuleType, rewardRuleSaveOrUpdateDTO.getRuleType())
                .set(RewardRuleDO::getLangType, rewardRuleSaveOrUpdateDTO.getLangType())
                .set(RewardRuleDO::getRuleImg, rewardRuleSaveOrUpdateDTO.getRuleImg())
                .set(RewardRuleDO::getTotalAmount, rewardRuleSaveOrUpdateDTO.getTotalAmount())
                .set(RewardRuleDO::getRewardNum, rewardRuleSaveOrUpdateDTO.getRewardNum())
                .eq(BaseDO::getId, rewardRuleSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
        /* 108 */
        if (!rs) {
            /* 109 */
            throw new BusinessException("更新抽奖信息失败.");

        }
        /* 111 */
        return queryRewardRule(((RewardRuleQueryDTO.RewardRuleQueryDTOBuilder) RewardRuleQueryDTO.builder().id(rewardRuleSaveOrUpdateDTO.getId())).build());

    }



    public TurntableVo queryTurntable(TurntableQueryDTO turntableQueryDTO) {
        /* 120 */
        ConfigQueryDTO configQueryDTO = ConfigQueryDTO.builder().configCode("turntable_config").configGroup("turntable_config_group").accountId(CommonConst.LZERO).build();
        /* 121 */
        ConfigVo configVo = this.configApiService.queryConfig(configQueryDTO);
        /* 122 */
        if (ObjectUtil.isEmpty(configVo)) {
            /* 123 */
            return TurntableVo.builder().build();

        }
        /* 125 */
        TurntableVo turntableVo = (TurntableVo) JSON.parseObject(configVo.getConfigValue(), TurntableVo.class);
        /* 126 */
        turntableVo.setId(configVo.getId());
        /* 127 */
        return turntableVo;

    }



    public TurntableVo saveOrUpdateTurntable(TurntableSaveOrUpdateDTO turntableSaveOrUpdateDTO) {
        /* 131 */
        TurntableVo turntableVo = this.rewardRuleConverter.toTurntableVo(turntableSaveOrUpdateDTO);








        /* 140 */
        ConfigSaveOrUpdateDTO configSaveOrUpdateDTO = ((ConfigSaveOrUpdateDTO.ConfigSaveOrUpdateDTOBuilder) ConfigSaveOrUpdateDTO.builder().id(turntableVo.getId())).configCode("turntable_config").configGroup("turntable_config_group").configValue(JSON.toJSONString(turntableVo)).configDesc("").status(EnableStatusEnum.ENABLE.getStatus()).status(Integer.valueOf(1)).build();
        /* 141 */
        this.configApiService.saveOrUpdate(configSaveOrUpdateDTO);
        /* 142 */
        return turntableVo;

    }

}

