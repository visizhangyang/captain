package com.porn.service.merchant.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.merchant.api.MerchantDescApiService;
import com.porn.client.merchant.dto.MerchantDescQueryDTO;
import com.porn.client.merchant.dto.MerchantDescSaveOrUpdateDTO;
import com.porn.client.merchant.vo.MerchantDescVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.merchant.converter.MerchantDescConverter;
import com.porn.service.merchant.dao.entity.MerchantDescDO;
import com.porn.service.merchant.dao.mapper.MerchantDescMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service

@Transactional(rollbackFor = {Exception.class})
public class MerchantDescApiServiceImpl implements MerchantDescApiService {
    private static final Logger log = LoggerFactory.getLogger(MerchantDescApiServiceImpl.class);

    @Autowired
    private MerchantDescConverter merchantDescConverter;


    @Autowired
    private MerchantDescMapper merchantDescMapper;


    public MerchantDescVo queryMerchantDesc(MerchantDescQueryDTO merchantDescQueryDTO) {

        List<MerchantDescVo> merchantDescVoList = queryMerchantDescList(merchantDescQueryDTO);

        return ObjectUtil.isEmpty(merchantDescVoList) ? null : merchantDescVoList.get(0);

    }


    public List<MerchantDescVo> queryMerchantDescList(MerchantDescQueryDTO merchantDescQueryDTO) {

        List<MerchantDescDO> merchantDescList = ChainWrappers.lambdaQueryChain(merchantDescMapper)
                .eq(ObjectUtil.isNotEmpty(merchantDescQueryDTO.getId()), BaseDO::getId, merchantDescQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(merchantDescQueryDTO.getMerchantId()), MerchantDescDO::getMerchantId, merchantDescQueryDTO.getMerchantId())
                .eq(ObjectUtil.isNotEmpty(merchantDescQueryDTO.getLangType()), MerchantDescDO::getLangType, merchantDescQueryDTO.getLangType())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();

        if (ObjectUtil.isEmpty(merchantDescList)) {

            return Collections.emptyList();

        }

        List<MerchantDescVo> merchantDescVoList = this.merchantDescConverter.toMerchantDescVoList(merchantDescList);

        return merchantDescVoList;

    }

    public MerchantDescVo saveOrUpdate(MerchantDescSaveOrUpdateDTO merchantDescSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(merchantDescSaveOrUpdateDTO.getId())) {

            MerchantDescDO merchantDescDO = this.merchantDescConverter.toMerchantDescDO(merchantDescSaveOrUpdateDTO);

            if (this.merchantDescMapper.insert(merchantDescDO) <= 0) {

                throw new BusinessException("保存商户描述信息失败.");

            }

            return queryMerchantDesc(((MerchantDescQueryDTO.MerchantDescQueryDTOBuilder) MerchantDescQueryDTO.builder().id(merchantDescDO.getId())).build());

        }

        boolean rs = ChainWrappers.lambdaUpdateChain(merchantDescMapper)
                .set(ObjectUtil.isNotEmpty(merchantDescSaveOrUpdateDTO.getMerchantId()), MerchantDescDO::getMerchantId, merchantDescSaveOrUpdateDTO.getMerchantId())
                .set(ObjectUtil.isNotEmpty(merchantDescSaveOrUpdateDTO.getLangType()), MerchantDescDO::getLangType, merchantDescSaveOrUpdateDTO.getLangType())
                .set(ObjectUtil.isNotEmpty(merchantDescSaveOrUpdateDTO.getContent()), MerchantDescDO::getContent, merchantDescSaveOrUpdateDTO.getContent())
                .set(BaseDO::getModifyTime, LocalDateTimeUtil.now())
                .eq(BaseDO::getId, merchantDescSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新商户描述信息失败.");

        }

        return queryMerchantDesc(((MerchantDescQueryDTO.MerchantDescQueryDTOBuilder) MerchantDescQueryDTO.builder().id(merchantDescSaveOrUpdateDTO.getId())).build());

    }

}

