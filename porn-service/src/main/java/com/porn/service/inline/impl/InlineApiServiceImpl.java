package com.porn.service.inline.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.BaseVo;
import com.porn.client.inline.api.InlineApiService;
import com.porn.client.inline.dto.InlineQueryDTO;
import com.porn.client.inline.dto.InlineSaveOrUpdateDTO;
import com.porn.client.inline.vo.InlineVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.inline.converter.InlineConverter;
import com.porn.service.inline.dao.entity.InlineDO;
import com.porn.service.inline.dao.mapper.InlineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service

@Transactional(rollbackFor = {Exception.class})
public class InlineApiServiceImpl implements InlineApiService {
    private static final Logger log = LoggerFactory.getLogger(InlineApiServiceImpl.class);

    @Autowired
    private InlineConverter inlineConverter;


    @Autowired
    private InlineMapper inlineMapper;


    public InlineVo queryInline(InlineQueryDTO inlineQueryDTO) {

        List<InlineVo> inlineVoList = queryInlineList(inlineQueryDTO);

        return ObjectUtil.isEmpty(inlineVoList) ? null : inlineVoList.get(0);

    }


    public List<InlineVo> queryInlineList(InlineQueryDTO inlineQueryDTO) {

        List<InlineDO> inlineList = ChainWrappers.lambdaQueryChain(inlineMapper)
                .eq(ObjectUtil.isNotEmpty(inlineQueryDTO.getId()), BaseDO::getId, inlineQueryDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByAsc(BaseDO::getId)
                .list();

        List<InlineVo> inlineVoList = this.inlineConverter.toInlineVoList(inlineList);

        return inlineVoList;

    }


    public InlineVo saveOrUpdate(InlineSaveOrUpdateDTO inlineSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(inlineSaveOrUpdateDTO.getId())) {

            InlineDO inlineDO = this.inlineConverter.toInlineDO(inlineSaveOrUpdateDTO);

            if (this.inlineMapper.insert(inlineDO) <= 0) {

                throw new BusinessException("保存在线信息失败.");

            }

            return queryInline(((InlineQueryDTO.InlineQueryDTOBuilder) InlineQueryDTO.builder().id(inlineDO.getId())).build());

        }


        boolean rs = ChainWrappers.lambdaUpdateChain(inlineMapper)
                .set(InlineDO::getMinInlineCount, inlineSaveOrUpdateDTO.getMinInlineCount())
                .set(InlineDO::getMaxInlineCount, inlineSaveOrUpdateDTO.getMaxInlineCount())
                .set(InlineDO::getMinInlineTime, inlineSaveOrUpdateDTO.getMinInlineTime())
                .set(InlineDO::getMaxInlineTime, inlineSaveOrUpdateDTO.getMaxInlineTime())
                .eq(BaseDO::getId, inlineSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新在线信息失败.");

        }

        return queryInline(((InlineQueryDTO.InlineQueryDTOBuilder) InlineQueryDTO.builder().id(inlineSaveOrUpdateDTO.getId())).build());

    }

    public Boolean batchCreate(List<InlineSaveOrUpdateDTO> inlineSaveOrUpdateDTOList) {

        if (ObjectUtil.isEmpty(inlineSaveOrUpdateDTOList)) {

            throw new BusinessException("数据为空.");

        }


        List<InlineVo> inlineVoList = queryInlineList(InlineQueryDTO.builder().build());


        List<Long> dbIdList = ObjectUtil.isEmpty(inlineVoList) ? ListUtil.list(false) : (List<Long>) inlineVoList.stream().map(BaseVo::getId).collect(Collectors.toList());

        for (InlineSaveOrUpdateDTO inlineSaveOrUpdateDTO : inlineSaveOrUpdateDTOList) {

            saveOrUpdate(inlineSaveOrUpdateDTO);

            if (ObjectUtil.isNotEmpty(inlineSaveOrUpdateDTO.getId())) {

                dbIdList.remove(inlineSaveOrUpdateDTO.getId());

            }

        }

        if (ObjectUtil.isNotEmpty(dbIdList)) {

            return ChainWrappers.lambdaUpdateChain(inlineMapper)
                    .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                    .in(BaseDO::getId, dbIdList)
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .update();

        }

        return Boolean.TRUE;

    }

}
