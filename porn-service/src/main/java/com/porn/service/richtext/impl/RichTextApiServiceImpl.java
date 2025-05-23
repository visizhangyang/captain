package com.porn.service.richtext.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.entity.Pair;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.config.api.ConfigApiService;
import com.porn.client.config.dto.ConfigQueryDTO;
import com.porn.client.config.vo.ConfigVo;
import com.porn.client.richtext.api.RichTextApiService;
import com.porn.client.richtext.dto.RichTextQueryDTO;
import com.porn.client.richtext.dto.RichTextSaveOrUpdateDTO;
import com.porn.client.richtext.vo.RichTextVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.richtext.converter.RichTextConverter;
import com.porn.service.richtext.dao.entity.RichTextDO;
import com.porn.service.richtext.dao.mapper.RichTextMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service

@Transactional(rollbackFor = {Exception.class})
public class RichTextApiServiceImpl implements RichTextApiService {
    private static final Logger log = LoggerFactory.getLogger(RichTextApiServiceImpl.class);


    @Autowired
    private RichTextConverter richTextConverter;


    @Autowired
    private RichTextMapper richTextMapper;


    @Autowired
    private ConfigApiService configApiService;

    public RichTextVo queryRichText(RichTextQueryDTO richTextQueryDTO) {

        List<RichTextVo> richTextVoList = queryRichTextList(richTextQueryDTO);

        return ObjectUtil.isEmpty(richTextVoList) ? null : richTextVoList.get(0);

    }


    public List<RichTextVo> queryRichTextList(RichTextQueryDTO richTextQueryDTO) {

        List<RichTextDO> richTextDOList = ChainWrappers.lambdaQueryChain(richTextMapper)
                .eq(ObjectUtil.isNotEmpty(richTextQueryDTO.getId()), BaseDO::getId, richTextQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(richTextQueryDTO.getType()), RichTextDO::getType, richTextQueryDTO.getType())
                .eq(ObjectUtil.isNotEmpty(richTextQueryDTO.getLangType()), RichTextDO::getLangType, richTextQueryDTO.getLangType())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();

        List<RichTextVo> richTextVoList = this.richTextConverter.toRichTextVoList(richTextDOList);

        return richTextVoList;

    }


    public RichTextVo saveOrUpdate(RichTextSaveOrUpdateDTO richTextSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(richTextSaveOrUpdateDTO.getId())) {

            RichTextDO richTextDO = this.richTextConverter.toRichTextDO(richTextSaveOrUpdateDTO);

            if (this.richTextMapper.insert(richTextDO) <= 0) {

                throw new BusinessException("保存富文本信息失败.");

            }

            return queryRichText(((RichTextQueryDTO.RichTextQueryDTOBuilder) RichTextQueryDTO.builder().id(richTextDO.getId())).build());

        }

        boolean rs = ChainWrappers.lambdaUpdateChain(richTextMapper)
                .set(ObjectUtil.isNotEmpty(richTextSaveOrUpdateDTO.getType()), RichTextDO::getType, richTextSaveOrUpdateDTO.getType())
                .set(ObjectUtil.isNotEmpty(richTextSaveOrUpdateDTO.getLangType()), RichTextDO::getLangType, richTextSaveOrUpdateDTO.getLangType())
                .set(RichTextDO::getRichText, richTextSaveOrUpdateDTO.getRichText())
                .eq(BaseDO::getId, richTextSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新富文本信息失败.");

        }

        return queryRichText(((RichTextQueryDTO.RichTextQueryDTOBuilder) RichTextQueryDTO.builder().id(richTextSaveOrUpdateDTO.getId())).build());

    }


    public List<Pair> queryTypes() {

        ConfigQueryDTO configQueryDTO = ConfigQueryDTO.builder().configGroup("richtext").accountId(CommonConst.LZERO).build();

        List<ConfigVo> configVoList = this.configApiService.queryConfigList(configQueryDTO);

        if (ObjectUtil.isEmpty(configVoList)) {

            return Collections.emptyList();

        }

        return (List<Pair>) configVoList.stream().map(config -> Pair.of(config.getId(), config.getConfigCode())).collect(Collectors.toList());

    }

}
