
package com.porn.service.richtext.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
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
    /* 33 */   private static final Logger log = LoggerFactory.getLogger(RichTextApiServiceImpl.class);



    @Autowired
     private RichTextConverter richTextConverter;



    @Autowired
     private RichTextMapper richTextMapper;



    @Autowired
     private ConfigApiService configApiService;




    public RichTextVo queryRichText(RichTextQueryDTO richTextQueryDTO) {
        /* 49 */
        List<RichTextVo> richTextVoList = queryRichTextList(richTextQueryDTO);
        /* 50 */
        return ObjectUtil.isEmpty(richTextVoList) ? null : richTextVoList.get(0);

    }









    public List<RichTextVo> queryRichTextList(RichTextQueryDTO richTextQueryDTO) {
        /* 60 */
        List<RichTextDO> richTextDOList = ChainWrappers.lambdaQueryChain(richTextMapper)
                .eq(ObjectUtil.isNotEmpty(richTextQueryDTO.getId()), BaseDO::getId, richTextQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(richTextQueryDTO.getType()), RichTextDO::getType, richTextQueryDTO.getType())
                .eq(ObjectUtil.isNotEmpty(richTextQueryDTO.getLangType()), RichTextDO::getLangType, richTextQueryDTO.getLangType())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();
        /* 61 */
        List<RichTextVo> richTextVoList = this.richTextConverter.toRichTextVoList(richTextDOList);
        /* 62 */
        return richTextVoList;

    }



    public RichTextVo saveOrUpdate(RichTextSaveOrUpdateDTO richTextSaveOrUpdateDTO) {
        /* 66 */
        if (ObjectUtil.isEmpty(richTextSaveOrUpdateDTO.getId())) {

            /* 68 */
            RichTextDO richTextDO = this.richTextConverter.toRichTextDO(richTextSaveOrUpdateDTO);
            /* 69 */
            if (this.richTextMapper.insert(richTextDO) <= 0) {
                /* 70 */
                throw new BusinessException("保存富文本信息失败.");

            }
            /* 72 */
            return queryRichText(((RichTextQueryDTO.RichTextQueryDTOBuilder) RichTextQueryDTO.builder().id(richTextDO.getId())).build());

        }







        /* 81 */
        boolean rs = ChainWrappers.lambdaUpdateChain(richTextMapper)
                .set(ObjectUtil.isNotEmpty(richTextSaveOrUpdateDTO.getType()), RichTextDO::getType, richTextSaveOrUpdateDTO.getType())
                .set(ObjectUtil.isNotEmpty(richTextSaveOrUpdateDTO.getLangType()), RichTextDO::getLangType, richTextSaveOrUpdateDTO.getLangType())
                .set(RichTextDO::getRichText, richTextSaveOrUpdateDTO.getRichText())
                .eq(BaseDO::getId, richTextSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
        /* 82 */
        if (!rs) {
            /* 83 */
            throw new BusinessException("更新富文本信息失败.");

        }
        /* 85 */
        return queryRichText(((RichTextQueryDTO.RichTextQueryDTOBuilder) RichTextQueryDTO.builder().id(richTextSaveOrUpdateDTO.getId())).build());

    }







    public List<Pair> queryTypes() {
        /* 93 */
        ConfigQueryDTO configQueryDTO = ConfigQueryDTO.builder().configGroup("richtext").accountId(CommonConst.LZERO).build();
        /* 94 */
        List<ConfigVo> configVoList = this.configApiService.queryConfigList(configQueryDTO);
        /* 95 */
        if (ObjectUtil.isEmpty(configVoList)) {
            /* 96 */
            return Collections.emptyList();

        }
        /* 98 */
        return (List<Pair>) configVoList.stream().map(config -> Pair.of(config.getId(), config.getConfigCode())).collect(Collectors.toList());

    }

}
