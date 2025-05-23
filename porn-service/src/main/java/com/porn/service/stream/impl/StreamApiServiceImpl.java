package com.porn.service.stream.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.client.stream.dto.StreamQueryPageDTO;
import com.porn.client.stream.dto.StreamSaveOrUpdateDTO;
import com.porn.client.stream.vo.StreamVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.stream.converter.StreamConverter;
import com.porn.service.stream.dao.entity.StreamDO;
import com.porn.service.stream.dao.mapper.StreamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service

@Transactional(rollbackFor = {Exception.class})
public class StreamApiServiceImpl implements StreamApiService {
    private static final Logger log = LoggerFactory.getLogger(StreamApiServiceImpl.class);


    @Autowired
    private StreamMapper streamMapper;


    @Autowired
    private StreamConverter streamConverter;

    public StreamVo queryStream(StreamQueryDTO streamQueryDTO) {

        List<StreamVo> streamVoList = queryStreamList(streamQueryDTO);

        return ObjectUtil.isEmpty(streamVoList) ? null : streamVoList.get(0);

    }


    public List<StreamVo> queryStreamList(StreamQueryDTO streamQueryDTO) {

        List<StreamDO> streamList = ChainWrappers.lambdaQueryChain(streamMapper)
                .eq(ObjectUtil.isNotEmpty(streamQueryDTO.getId()), BaseDO::getId, streamQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(streamQueryDTO.getBizId()), StreamDO::getBizId, streamQueryDTO.getBizId())
                .eq(ObjectUtil.isNotEmpty(streamQueryDTO.getAccountId()), StreamDO::getAccountId, streamQueryDTO.getAccountId())
                .in(ObjectUtil.isNotEmpty(streamQueryDTO.getFlagList()), StreamDO::getFlag, streamQueryDTO.getFlagList())
                .in(ObjectUtil.isNotEmpty(streamQueryDTO.getAccountIdList()), StreamDO::getAccountId, streamQueryDTO.getAccountIdList())
                .eq(ObjectUtil.isNotEmpty(streamQueryDTO.getFlag()), StreamDO::getFlag, streamQueryDTO.getFlag())
                .eq(ObjectUtil.isNotEmpty(streamQueryDTO.getType()), StreamDO::getType, streamQueryDTO.getType())
                .in(ObjectUtil.isNotEmpty(streamQueryDTO.getTypeList()), StreamDO::getType, streamQueryDTO.getTypeList())
                .ge(ObjectUtil.isNotEmpty(streamQueryDTO.getStartTime()), BaseDO::getCreateTime, streamQueryDTO.getStartTime())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByDesc(BaseDO::getCreateTime)
                .list();

        List<StreamVo> streamVoList = this.streamConverter.toStreamVoList(streamList);

        return streamVoList;

    }


    public PageVo<StreamVo> queryPage(StreamQueryPageDTO streamQueryPageDTO) {

        Page page = new Page(streamQueryPageDTO.getPageStart().intValue(), streamQueryPageDTO.getPageSize().intValue(), true);

//        LambdaQueryChainWrapper lambdaQueryChainWrapper = ChainWrappers.lambdaQueryChain(streamMapper)
//                .eq(ObjectUtil.isNotEmpty(streamQueryPageDTO.getBizId()), StreamDO::getBizId, streamQueryPageDTO.getBizId())
//                .eq(ObjectUtil.isNotEmpty(streamQueryPageDTO.getAccountId()), StreamDO::getAccountId, streamQueryPageDTO.getAccountId())
//                .in(ObjectUtil.isNotEmpty(streamQueryPageDTO.getFlagList()), StreamDO::getFlag, streamQueryPageDTO.getFlagList())
//                .in(ObjectUtil.isNotEmpty(streamQueryPageDTO.getAccountIdList()), StreamDO::getAccountId, streamQueryPageDTO.getAccountIdList())
//                .eq(ObjectUtil.isNotEmpty(streamQueryPageDTO.getFlag()), StreamDO::getFlag, streamQueryPageDTO.getFlag())
//                .eq(ObjectUtil.isNotEmpty(streamQueryPageDTO.getType()), StreamDO::getType, streamQueryPageDTO.getType())
//                .ge(ObjectUtil.isNotEmpty(streamQueryPageDTO.getStartTime()), BaseDO::getCreateTime, streamQueryPageDTO.getStartTime())
//                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
//                .orderByDesc(BaseDO::getCreateTime);

        LambdaQueryWrapper<StreamDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(streamQueryPageDTO.getBizId()), StreamDO::getBizId, streamQueryPageDTO.getBizId());
        wrapper.eq(ObjectUtil.isNotEmpty(streamQueryPageDTO.getAccountId()), StreamDO::getAccountId, streamQueryPageDTO.getAccountId());
        wrapper.in(ObjectUtil.isNotEmpty(streamQueryPageDTO.getFlagList()), StreamDO::getFlag, streamQueryPageDTO.getFlagList());
        wrapper.in(ObjectUtil.isNotEmpty(streamQueryPageDTO.getAccountIdList()), StreamDO::getAccountId, streamQueryPageDTO.getAccountIdList());
        wrapper.eq(ObjectUtil.isNotEmpty(streamQueryPageDTO.getFlag()), StreamDO::getFlag, streamQueryPageDTO.getFlag());
        wrapper.eq(ObjectUtil.isNotEmpty(streamQueryPageDTO.getType()), StreamDO::getType, streamQueryPageDTO.getType());
        wrapper.ge(ObjectUtil.isNotEmpty(streamQueryPageDTO.getStartTime()), BaseDO::getCreateTime, streamQueryPageDTO.getStartTime());
        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        wrapper.orderByDesc(BaseDO::getCreateTime);


        IPage<StreamDO> streamPage = this.streamMapper.selectPage((IPage) page, wrapper);

        List<StreamVo> streamVoList = this.streamConverter.toStreamVoList(streamPage.getRecords());

        return PageVo.<StreamVo>builder()
                .pageStart(streamQueryPageDTO.getPageStart())
                .pageSize(streamQueryPageDTO.getPageSize())
                .total(Long.valueOf(streamPage.getTotal()))
                .data(streamVoList)
                .build();

    }


    public StreamVo saveOrUpdate(StreamSaveOrUpdateDTO streamSaveOrUpdateDTO) {

        StreamDO streamDO = this.streamConverter.toStreamDO(streamSaveOrUpdateDTO);

        if (this.streamMapper.insert(streamDO) > 0) {

            return queryStream(((StreamQueryDTO.StreamQueryDTOBuilder) StreamQueryDTO.builder().id(streamDO.getId())).bizId(streamDO.getBizId()).build());

        }

        throw new BusinessException("流水插入失败.");

    }


    public BigDecimal statisticsTotalProxyProfit(StreamQueryDTO streamQueryDTO) {

        return this.streamMapper.statisticsTotalProxyProfit(streamQueryDTO);

    }

}

