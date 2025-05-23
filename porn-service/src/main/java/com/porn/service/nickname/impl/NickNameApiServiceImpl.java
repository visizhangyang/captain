package com.porn.service.nickname.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.nickname.api.NickNameApiService;
import com.porn.client.nickname.dto.*;
import com.porn.client.nickname.enums.NickNameTypeEnum;
import com.porn.client.nickname.vo.NickNameVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.nickname.converter.NickNameConverter;
import com.porn.service.nickname.dao.entity.NickNameDO;
import com.porn.service.nickname.dao.mapper.NickNameMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service

@Transactional(rollbackFor = {Exception.class})
public class NickNameApiServiceImpl implements NickNameApiService {
    private static final Logger log = LoggerFactory.getLogger(NickNameApiServiceImpl.class);

    @Autowired
    private NickNameMapper nickNameMapper;

    @Autowired
    private NickNameConverter nickNameConverter;


    public NickNameVo queryNickName(NickNameQueryDTO nickNameQueryDTO) {

        List<NickNameVo> nickNameVoList = queryNickNameList(nickNameQueryDTO);

        return ObjectUtil.isEmpty(nickNameVoList) ? null : nickNameVoList.get(0);

    }

    public List<NickNameVo> queryNickNameList(NickNameQueryDTO nickNameQueryDTO) {

        List<NickNameDO> nickNameList = ChainWrappers.lambdaQueryChain(nickNameMapper)
                .like(ObjectUtil.isNotEmpty(nickNameQueryDTO.getLkNickName()), NickNameDO::getNickName, nickNameQueryDTO.getLkNickName())
                .eq(ObjectUtil.isNotEmpty(nickNameQueryDTO.getNickNameType()), NickNameDO::getNickNameType, nickNameQueryDTO.getNickNameType())
                .eq(ObjectUtil.isNotEmpty(nickNameQueryDTO.getStatus()), NickNameDO::getStatus, nickNameQueryDTO.getStatus())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();

        if (ObjectUtil.isEmpty(nickNameList)) {

            return Collections.emptyList();

        }

        List<NickNameVo> nickNameVoList = this.nickNameConverter.toNickNameVoList(nickNameList);

        return nickNameVoList;

    }


    public PageVo<NickNameVo> queryPage(NickNameQueryPageDTO nickNameQueryPageDTO) {

        Page page = new Page(nickNameQueryPageDTO.getPageStart().intValue(), nickNameQueryPageDTO.getPageSize().intValue(), true);

        LambdaQueryWrapper<NickNameDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ObjectUtil.isNotEmpty(nickNameQueryPageDTO.getLkNickName()), NickNameDO::getNickName, nickNameQueryPageDTO.getLkNickName());
        wrapper.eq(ObjectUtil.isNotEmpty(nickNameQueryPageDTO.getNickNameType()), NickNameDO::getNickNameType, nickNameQueryPageDTO.getNickNameType());
        wrapper.eq(ObjectUtil.isNotEmpty(nickNameQueryPageDTO.getStatus()), NickNameDO::getStatus, nickNameQueryPageDTO.getStatus());
        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        wrapper.orderByDesc(BaseDO::getCreateTime);
        IPage<NickNameDO> nickNamePage = this.nickNameMapper.selectPage((IPage) page, wrapper);

        List<NickNameVo> nickNameVoList = this.nickNameConverter.toNickNameVoList(nickNamePage.getRecords());

        return PageVo.<NickNameVo>builder()
                .pageStart(nickNameQueryPageDTO.getPageStart())
                .pageSize(nickNameQueryPageDTO.getPageSize())
                .total(Long.valueOf(nickNamePage.getTotal()))
                .data(nickNameVoList)
                .build();

    }


    public Boolean batchSaveNickName(List<NickNameSaveOrUpdateDTO> nickNameSaveOrUpdateDTOList) {

        if (ObjectUtil.isEmpty(nickNameSaveOrUpdateDTOList)) {

            return Boolean.TRUE;

        }

        for (NickNameSaveOrUpdateDTO nickNameSaveOrUpdateDTO : nickNameSaveOrUpdateDTOList) {


            NickNameDO nickNameDO = NickNameDO.builder().nickName(nickNameSaveOrUpdateDTO.getNickName()).nickNameType(nickNameSaveOrUpdateDTO.getNickNameType()).status(EnableStatusEnum.DISABLED.getStatus()).build();

            if (this.nickNameMapper.insert(nickNameDO) <= 0) {

                throw new BusinessException("保存昵称信息失败.");

            }

        }

        return Boolean.TRUE;

    }


    public Boolean batchDelete(NickNameBatchDeleteDTO nickNameBatchDeleteDTO) {

        return ChainWrappers.lambdaUpdateChain(nickNameMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .eq(ObjectUtil.isNotEmpty(nickNameBatchDeleteDTO.getId()), BaseDO::getId, nickNameBatchDeleteDTO.getId())
                .in(ObjectUtil.isNotEmpty(nickNameBatchDeleteDTO.getIdList()), BaseDO::getId, nickNameBatchDeleteDTO.getIdList())
                .update();

    }


    public Boolean updateStatus(NickNameUpdateStatusDTO nickNameUpdateStatusDTO) {

        return ChainWrappers.lambdaUpdateChain(nickNameMapper)
                .set(NickNameDO::getStatus, nickNameUpdateStatusDTO.getStatus())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .eq(ObjectUtil.isNotEmpty(nickNameUpdateStatusDTO.getId()), BaseDO::getId, nickNameUpdateStatusDTO.getId())
                .in(ObjectUtil.isNotEmpty(nickNameUpdateStatusDTO.getIdList()), BaseDO::getId, nickNameUpdateStatusDTO.getIdList())
                .update();

    }


    public Boolean upload(NickNameUploadDTO nickNameUploadDTO) {

        if (ObjectUtil.isEmpty(nickNameUploadDTO) ||
                ObjectUtil.isEmpty(nickNameUploadDTO.getLines())) {

            return Boolean.FALSE;

        }

        List<NickNameSaveOrUpdateDTO> nickNameSaveOrUpdateDTOList = new ArrayList<>();

        for (String line : nickNameUploadDTO.getLines()) {

            if (ObjectUtil.isEmpty(line)) {

                continue;

            }


            NickNameSaveOrUpdateDTO nickNameSaveOrUpdateDTO = NickNameSaveOrUpdateDTO.builder().nickName(line.trim()).nickNameType(NickNameTypeEnum.CUSTOM.getType()).status(EnableStatusEnum.DISABLED.getStatus()).build();

            nickNameSaveOrUpdateDTOList.add(nickNameSaveOrUpdateDTO);

        }

        return batchSaveNickName(nickNameSaveOrUpdateDTOList);

    }

}

