package com.porn.service.goods.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.goods.api.GoodsRuleApiService;
import com.porn.client.goods.dto.GoodsRuleQueryDTO;
import com.porn.client.goods.dto.GoodsRuleSaveOrUpdateDTO;
import com.porn.client.goods.vo.GoodsRuleVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.goods.converter.GoodsRuleConverter;
import com.porn.service.goods.dao.entity.GoodsRuleDO;
import com.porn.service.goods.dao.mapper.GoodsRuleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Service

@Transactional(rollbackFor = {Exception.class})
public class GoodsRuleApiServiceImpl implements GoodsRuleApiService {
    private static final Logger log = LoggerFactory.getLogger(GoodsRuleApiServiceImpl.class);


    @Autowired
    private GoodsRuleMapper goodsRuleMapper;


    @Autowired
    private GoodsRuleConverter goodsRuleConverter;


    public GoodsRuleVo queryGoodsRule(GoodsRuleQueryDTO goodsRuleQueryDTO) {

        List<GoodsRuleVo> goodsRuleVoList = queryGoodsRuleList(goodsRuleQueryDTO);

        return ObjectUtil.isEmpty(goodsRuleVoList) ? null : goodsRuleVoList.get(0);

    }


    public List<GoodsRuleVo> queryGoodsRuleList(GoodsRuleQueryDTO goodsRuleQueryDTO) {

        List<GoodsRuleDO> goodsRuleList = ChainWrappers.lambdaQueryChain(goodsRuleMapper)
                .eq(ObjectUtil.isNotEmpty(goodsRuleQueryDTO.getId()), BaseDO::getId, goodsRuleQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(goodsRuleQueryDTO.getStatus()), GoodsRuleDO::getStatus, goodsRuleQueryDTO.getStatus())
                .eq(ObjectUtil.isNotEmpty(goodsRuleQueryDTO.getMerchantId()), GoodsRuleDO::getMerchantId, goodsRuleQueryDTO.getMerchantId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByDesc(BaseDO::getCreateTime)
                .list();

        if (ObjectUtil.isEmpty(goodsRuleList)) {

            return Collections.emptyList();

        }

        List<GoodsRuleVo> goodsRuleVoList = this.goodsRuleConverter.toGoodsRuleVoList(goodsRuleList);

        return goodsRuleVoList;

    }


    public GoodsRuleVo saveOrUpdate(GoodsRuleSaveOrUpdateDTO goodsRuleSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(goodsRuleSaveOrUpdateDTO.getId())) {


            GoodsRuleDO goodsRuleDO = this.goodsRuleConverter.toGoodsRuleDO(goodsRuleSaveOrUpdateDTO);

            if (this.goodsRuleMapper.insert(goodsRuleDO) <= 0) {

                throw new BusinessException("保存商品规则信息失败.");

            }

            return queryGoodsRule(((GoodsRuleQueryDTO.GoodsRuleQueryDTOBuilder) GoodsRuleQueryDTO.builder().id(goodsRuleDO.getId())).build());

        }


        boolean rs = ChainWrappers.lambdaUpdateChain(goodsRuleMapper)
                .set(ObjectUtil.isNotEmpty(goodsRuleSaveOrUpdateDTO.getMinGoodsCount()), GoodsRuleDO::getMinGoodsCount, goodsRuleSaveOrUpdateDTO.getMinGoodsCount())
                .set(ObjectUtil.isNotEmpty(goodsRuleSaveOrUpdateDTO.getMaxGoodsCount()), GoodsRuleDO::getMaxGoodsCount, goodsRuleSaveOrUpdateDTO.getMaxGoodsCount())
                .set(ObjectUtil.isNotEmpty(goodsRuleSaveOrUpdateDTO.getMinAmount()), GoodsRuleDO::getMinAmount, goodsRuleSaveOrUpdateDTO.getMinAmount())
                .set(ObjectUtil.isNotEmpty(goodsRuleSaveOrUpdateDTO.getMaxAmount()), GoodsRuleDO::getMaxAmount, goodsRuleSaveOrUpdateDTO.getMaxAmount())
                .set(ObjectUtil.isNotEmpty(goodsRuleSaveOrUpdateDTO.getStatus()), GoodsRuleDO::getStatus, goodsRuleSaveOrUpdateDTO.getStatus())
                .eq(BaseDO::getId, goodsRuleSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新商品规则信息失败.");

        }

        return queryGoodsRule(((GoodsRuleQueryDTO.GoodsRuleQueryDTOBuilder) GoodsRuleQueryDTO.builder().id(goodsRuleSaveOrUpdateDTO.getId())).build());

    }

}
