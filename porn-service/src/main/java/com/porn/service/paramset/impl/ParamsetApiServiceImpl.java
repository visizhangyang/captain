package com.porn.service.paramset.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.minio.api.MinioApiService;
import com.porn.client.minio.dto.PrevFileDTO;
import com.porn.client.minio.vo.PrevFileVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.dto.ParamsetSaveOrUpdateDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.paramset.converter.ParamsetConverter;
import com.porn.service.paramset.dao.entity.ParamsetDO;
import com.porn.service.paramset.dao.mapper.ParamsetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

@Transactional(rollbackFor = {Exception.class})
public class ParamsetApiServiceImpl implements ParamsetApiService {
    private static final Logger log = LoggerFactory.getLogger(ParamsetApiServiceImpl.class);

    @Autowired
    private ParamsetMapper paramsetMapper;

    @Autowired
    private ParamsetConverter paramsetConverter;


    @Autowired
    private MinioApiService minioApiService;


    public ParamsetVo queryParamset(ParamsetQueryDTO paramsetQueryDTO) {

        ParamsetDO paramsetDO = ChainWrappers.lambdaQueryChain(paramsetMapper)
                .eq(ObjectUtil.isNotEmpty(paramsetQueryDTO.getId()), BaseDO::getId, paramsetQueryDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .one();

        ParamsetVo paramsetVo = this.paramsetConverter.toParamsetVo(paramsetDO);

        if (ObjectUtil.isNotEmpty(paramsetVo.getAppLogo())) {

            PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(paramsetVo.getAppLogo()).build());

            if (ObjectUtil.isNotEmpty(prevFileVo)) {

                paramsetVo.setAppLogoUrl(prevFileVo.getFileUrl());

            }

        }

        return paramsetVo;

    }


    public ParamsetVo saveOrUpdate(ParamsetSaveOrUpdateDTO paramsetSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(paramsetSaveOrUpdateDTO.getId())) {

            ParamsetDO paramsetDO = this.paramsetConverter.toParamsetDO(paramsetSaveOrUpdateDTO);

            if (this.paramsetMapper.insert(paramsetDO) <= 0) {

                throw new BusinessException("保存参数信息失败.");

            }

            return queryParamset(((ParamsetQueryDTO.ParamsetQueryDTOBuilder) ParamsetQueryDTO.builder().id(paramsetDO.getId())).build());

        }


        boolean rs = ChainWrappers.lambdaUpdateChain(paramsetMapper)
                .set(ParamsetDO::getLargeLevelRate, paramsetSaveOrUpdateDTO.getLargeLevelRate())
                .set(ParamsetDO::getNormalLevelRate, paramsetSaveOrUpdateDTO.getNormalLevelRate())
                .set(ParamsetDO::getWorkRate, paramsetSaveOrUpdateDTO.getWorkRate())
                .set(ParamsetDO::getRechargeAmount, paramsetSaveOrUpdateDTO.getRechargeAmount())
                .set(ParamsetDO::getNormalWithdrawAmount, paramsetSaveOrUpdateDTO.getNormalWithdrawAmount())
                .set(ParamsetDO::getLargeWithdrawAmount, paramsetSaveOrUpdateDTO.getLargeWithdrawAmount())
                .set(ParamsetDO::getDifficultyRate, paramsetSaveOrUpdateDTO.getDifficultyRate())
                .set(ParamsetDO::getFreeCommissionRate, paramsetSaveOrUpdateDTO.getFreeCommissionRate())
                .set(ParamsetDO::getMinWorkAmount, paramsetSaveOrUpdateDTO.getMinWorkAmount())
                .set(ParamsetDO::getMinRechargeAmount, paramsetSaveOrUpdateDTO.getMinRechargeAmount())
                .set(ParamsetDO::getMaxWithdrawAmount, paramsetSaveOrUpdateDTO.getMaxWithdrawAmount())
                .set(ParamsetDO::getMinWithdrawAmount, paramsetSaveOrUpdateDTO.getMinWithdrawAmount())
                .set(ParamsetDO::getRechargeMatchTime, paramsetSaveOrUpdateDTO.getRechargeMatchTime())
                .set(ParamsetDO::getWithdrawMatchTime, paramsetSaveOrUpdateDTO.getWithdrawMatchTime())
                .set(ParamsetDO::getOrderMatchTime, paramsetSaveOrUpdateDTO.getOrderMatchTime())
                .set(ParamsetDO::getLargeLevelWorkCount, paramsetSaveOrUpdateDTO.getLargeLevelWorkCount())
                .set(ParamsetDO::getNormalLevelWorkCount, paramsetSaveOrUpdateDTO.getNormalLevelWorkCount())
                .set(ParamsetDO::getLargeLevelWorkSpace, paramsetSaveOrUpdateDTO.getLargeLevelWorkSpace())
                .set(ParamsetDO::getNormalLevelWorkSpace, paramsetSaveOrUpdateDTO.getNormalLevelWorkSpace())
                .set(ParamsetDO::getLargeLevelWorkMinRange, paramsetSaveOrUpdateDTO.getLargeLevelWorkMinRange())
                .set(ParamsetDO::getLargeLevelWorkMaxRange, paramsetSaveOrUpdateDTO.getLargeLevelWorkMaxRange())
                .set(ParamsetDO::getNormalLevelWorkMinRange, paramsetSaveOrUpdateDTO.getNormalLevelWorkMinRange())
                .set(ParamsetDO::getNormalLevelWorkMaxRange, paramsetSaveOrUpdateDTO.getNormalLevelWorkMaxRange())
                .set(ParamsetDO::getLargeLevelIncAmount, paramsetSaveOrUpdateDTO.getLargeLevelIncAmount())
                .set(ParamsetDO::getMinWorkCount, paramsetSaveOrUpdateDTO.getMinWorkCount())
                .set(ParamsetDO::getMaxWorkCount, paramsetSaveOrUpdateDTO.getMaxWorkCount())
                .set(ParamsetDO::getNormalWithdrawMinRange, paramsetSaveOrUpdateDTO.getNormalWithdrawMinRange())
                .set(ParamsetDO::getNormalWithdrawMaxRange, paramsetSaveOrUpdateDTO.getNormalWithdrawMaxRange())
                .set(ParamsetDO::getLargeWithdrawMinRange, paramsetSaveOrUpdateDTO.getLargeWithdrawMinRange())
                .set(ParamsetDO::getLargeWithdrawMaxRange, paramsetSaveOrUpdateDTO.getLargeWithdrawMaxRange())
                .set(ParamsetDO::getNormalWithdrawDayCount, paramsetSaveOrUpdateDTO.getNormalWithdrawDayCount())
                .set(ParamsetDO::getLargeWithdrawDayCount, paramsetSaveOrUpdateDTO.getLargeWithdrawDayCount())
                .set(ParamsetDO::getNormalTransferMinRange, paramsetSaveOrUpdateDTO.getNormalTransferMinRange())
                .set(ParamsetDO::getNormalTransferMaxRange, paramsetSaveOrUpdateDTO.getNormalTransferMaxRange())
                .set(ParamsetDO::getLargeTransferMinRange, paramsetSaveOrUpdateDTO.getLargeTransferMinRange())
                .set(ParamsetDO::getLargeTransferMaxRange, paramsetSaveOrUpdateDTO.getLargeTransferMaxRange())
                .set(ParamsetDO::getWithdrawWorkCount, paramsetSaveOrUpdateDTO.getWithdrawWorkCount())
                .set(ParamsetDO::getProxyLevel1Rate, paramsetSaveOrUpdateDTO.getProxyLevel1Rate())
                .set(ParamsetDO::getProxyLevel2Rate, paramsetSaveOrUpdateDTO.getProxyLevel2Rate())
                .set(ParamsetDO::getProxyLevel3Rate, paramsetSaveOrUpdateDTO.getProxyLevel3Rate())
                .set(ParamsetDO::getNormalTreasureRate, paramsetSaveOrUpdateDTO.getNormalTreasureRate())
                .set(ParamsetDO::getLargeTreasureRate, paramsetSaveOrUpdateDTO.getLargeTreasureRate())
                .set(ParamsetDO::getWorkPageRefreshSpace, paramsetSaveOrUpdateDTO.getWorkPageRefreshSpace())
                .set(ParamsetDO::getAppLogo, paramsetSaveOrUpdateDTO.getAppLogo())
                .set(ParamsetDO::getNoviceTutorialUrl, paramsetSaveOrUpdateDTO.getNoviceTutorialUrl())
                .set(ParamsetDO::getCustomerServiceUrl, paramsetSaveOrUpdateDTO.getCustomerServiceUrl())
                .set(ParamsetDO::getPromotionUrl, paramsetSaveOrUpdateDTO.getPromotionUrl())
                .set(ParamsetDO::getInitRedPack, paramsetSaveOrUpdateDTO.getInitRedPack())
                .set(ParamsetDO::getRegisterCount, paramsetSaveOrUpdateDTO.getRegisterCount())
                .set(ParamsetDO::getMinTransferAmount, paramsetSaveOrUpdateDTO.getMinTransferAmount())
                .set(ParamsetDO::getPartnerLevelRate, paramsetSaveOrUpdateDTO.getPartnerLevelRate())
                .set(ParamsetDO::getPartnerLevelWorkCount, paramsetSaveOrUpdateDTO.getPartnerLevelWorkCount())
                .set(ParamsetDO::getPartnerLevelWorkSpace, paramsetSaveOrUpdateDTO.getPartnerLevelWorkSpace())
                .set(ParamsetDO::getPartnerLevelWorkMinRange, paramsetSaveOrUpdateDTO.getPartnerLevelWorkMinRange())
                .set(ParamsetDO::getPartnerLevelWorkMaxRange, paramsetSaveOrUpdateDTO.getPartnerLevelWorkMaxRange())
                .set(ParamsetDO::getPartnerWithdrawAmount, paramsetSaveOrUpdateDTO.getPartnerWithdrawAmount())
                .set(ParamsetDO::getPartnerLevelIncAmount, paramsetSaveOrUpdateDTO.getPartnerLevelIncAmount())
                .set(ParamsetDO::getPartnerWithdrawMinRange, paramsetSaveOrUpdateDTO.getPartnerWithdrawMinRange())
                .set(ParamsetDO::getPartnerWithdrawMaxRange, paramsetSaveOrUpdateDTO.getPartnerWithdrawMaxRange())
                .set(ParamsetDO::getPartnerWithdrawDayCount, paramsetSaveOrUpdateDTO.getPartnerWithdrawDayCount())
                .set(ParamsetDO::getPartnerTransferMinRange, paramsetSaveOrUpdateDTO.getPartnerTransferMinRange())
                .set(ParamsetDO::getPartnerTransferMaxRange, paramsetSaveOrUpdateDTO.getPartnerTransferMaxRange())
                .set(ParamsetDO::getPartnerTreasureRate, paramsetSaveOrUpdateDTO.getPartnerTreasureRate())
                .eq(BaseDO::getId, paramsetSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新参数信息失败.");

        }

        return queryParamset(((ParamsetQueryDTO.ParamsetQueryDTOBuilder) ParamsetQueryDTO.builder().id(paramsetSaveOrUpdateDTO.getId())).build());

    }

}

