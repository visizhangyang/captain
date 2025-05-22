
package com.porn.service.nickname.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
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
    /*  33 */   private static final Logger log = LoggerFactory.getLogger(NickNameApiServiceImpl.class);




    @Autowired
     private NickNameMapper nickNameMapper;




    @Autowired
     private NickNameConverter nickNameConverter;





    public NickNameVo queryNickName(NickNameQueryDTO nickNameQueryDTO) {
        /*  48 */
        List<NickNameVo> nickNameVoList = queryNickNameList(nickNameQueryDTO);
        /*  49 */
        return ObjectUtil.isEmpty(nickNameVoList) ? null : nickNameVoList.get(0);

    }








    public List<NickNameVo> queryNickNameList(NickNameQueryDTO nickNameQueryDTO) {
        /*  58 */
        List<NickNameDO> nickNameList =  ChainWrappers.lambdaQueryChain(nickNameMapper)
                .like(ObjectUtil.isNotEmpty(nickNameQueryDTO.getLkNickName()), NickNameDO::getNickName, nickNameQueryDTO.getLkNickName())
                .eq(ObjectUtil.isNotEmpty(nickNameQueryDTO.getNickNameType()), NickNameDO::getNickNameType, nickNameQueryDTO.getNickNameType())
                .eq(ObjectUtil.isNotEmpty(nickNameQueryDTO.getStatus()), NickNameDO::getStatus, nickNameQueryDTO.getStatus())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();
        /*  59 */
        if (ObjectUtil.isEmpty(nickNameList)) {
            /*  60 */
            return Collections.emptyList();

        }
        /*  62 */
        List<NickNameVo> nickNameVoList = this.nickNameConverter.toNickNameVoList(nickNameList);
        /*  63 */
        return nickNameVoList;

    }



    public PageVo<NickNameVo> queryPage(NickNameQueryPageDTO nickNameQueryPageDTO) {
        /*  67 */
        Page page = new Page(nickNameQueryPageDTO.getPageStart().intValue(), nickNameQueryPageDTO.getPageSize().intValue(), true);

        LambdaQueryWrapper<NickNameDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ObjectUtil.isNotEmpty(nickNameQueryPageDTO.getLkNickName()), NickNameDO::getNickName, nickNameQueryPageDTO.getLkNickName());
        wrapper.eq(ObjectUtil.isNotEmpty(nickNameQueryPageDTO.getNickNameType()), NickNameDO::getNickNameType, nickNameQueryPageDTO.getNickNameType());
        wrapper.eq(ObjectUtil.isNotEmpty(nickNameQueryPageDTO.getStatus()), NickNameDO::getStatus, nickNameQueryPageDTO.getStatus());
        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        wrapper.orderByDesc(BaseDO::getCreateTime);
        IPage<NickNameDO> nickNamePage = this.nickNameMapper.selectPage((IPage) page, wrapper);
        /*  76 */
        List<NickNameVo> nickNameVoList = this.nickNameConverter.toNickNameVoList(nickNamePage.getRecords());
        /*  77 */
        return PageVo.<NickNameVo>builder()
/*  78 */.pageStart(nickNameQueryPageDTO.getPageStart())
/*  79 */.pageSize(nickNameQueryPageDTO.getPageSize())
/*  80 */.total(Long.valueOf(nickNamePage.getTotal()))
/*  81 */.data(nickNameVoList)
/*  82 */.build();

    }



    public Boolean batchSaveNickName(List<NickNameSaveOrUpdateDTO> nickNameSaveOrUpdateDTOList) {
        /*  86 */
        if (ObjectUtil.isEmpty(nickNameSaveOrUpdateDTOList)) {
            /*  87 */
            return Boolean.TRUE;

        }
        /*  89 */
        for (NickNameSaveOrUpdateDTO nickNameSaveOrUpdateDTO : nickNameSaveOrUpdateDTOList) {




            /*  94 */
            NickNameDO nickNameDO = NickNameDO.builder().nickName(nickNameSaveOrUpdateDTO.getNickName()).nickNameType(nickNameSaveOrUpdateDTO.getNickNameType()).status(EnableStatusEnum.DISABLED.getStatus()).build();
            /*  95 */
            if (this.nickNameMapper.insert(nickNameDO) <= 0) {
                /*  96 */
                throw new BusinessException("保存昵称信息失败.");

            }

        }
        /*  99 */
        return Boolean.TRUE;

    }



    public Boolean batchDelete(NickNameBatchDeleteDTO nickNameBatchDeleteDTO) {
        /* 103 */
        return ChainWrappers.lambdaUpdateChain(nickNameMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .eq(ObjectUtil.isNotEmpty(nickNameBatchDeleteDTO.getId()), BaseDO::getId, nickNameBatchDeleteDTO.getId())
                .in(ObjectUtil.isNotEmpty(nickNameBatchDeleteDTO.getIdList()), BaseDO::getId, nickNameBatchDeleteDTO.getIdList())
/* 108 */.update();

    }



    public Boolean updateStatus(NickNameUpdateStatusDTO nickNameUpdateStatusDTO) {
        /* 112 */
        return  ChainWrappers.lambdaUpdateChain(nickNameMapper)
                .set(NickNameDO::getStatus, nickNameUpdateStatusDTO.getStatus())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .eq(ObjectUtil.isNotEmpty(nickNameUpdateStatusDTO.getId()), BaseDO::getId, nickNameUpdateStatusDTO.getId())
                .in(ObjectUtil.isNotEmpty(nickNameUpdateStatusDTO.getIdList()), BaseDO::getId, nickNameUpdateStatusDTO.getIdList())
/* 117 */.update();

    }



    public Boolean upload(NickNameUploadDTO nickNameUploadDTO) {
        /* 121 */
        if (ObjectUtil.isEmpty(nickNameUploadDTO) ||
                /* 122 */       ObjectUtil.isEmpty(nickNameUploadDTO.getLines())) {
            /* 123 */
            return Boolean.FALSE;

        }
        /* 125 */
        List<NickNameSaveOrUpdateDTO> nickNameSaveOrUpdateDTOList = new ArrayList<>();
        /* 126 */
        for (String line : nickNameUploadDTO.getLines()) {
            /* 127 */
            if (ObjectUtil.isEmpty(line)) {

                continue;

            }




            /* 134 */
            NickNameSaveOrUpdateDTO nickNameSaveOrUpdateDTO = NickNameSaveOrUpdateDTO.builder().nickName(line.trim()).nickNameType(NickNameTypeEnum.CUSTOM.getType()).status(EnableStatusEnum.DISABLED.getStatus()).build();
            /* 135 */
            nickNameSaveOrUpdateDTOList.add(nickNameSaveOrUpdateDTO);

        }
        /* 137 */
        return batchSaveNickName(nickNameSaveOrUpdateDTOList);

    }

}

