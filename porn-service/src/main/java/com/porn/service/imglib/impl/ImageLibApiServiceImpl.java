package com.porn.service.imglib.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.imglib.api.ImageLibApiService;
import com.porn.client.imglib.dto.*;
import com.porn.client.imglib.vo.ImageLibVo;
import com.porn.client.minio.api.MinioApiService;
import com.porn.client.minio.dto.PrevFileDTO;
import com.porn.client.minio.vo.PrevFileVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.imglib.converter.ImageLibConverter;
import com.porn.service.imglib.dao.entity.ImageLibDO;
import com.porn.service.imglib.dao.mapper.ImageLibMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class})
public class ImageLibApiServiceImpl implements ImageLibApiService {
    private static final Logger log = LoggerFactory.getLogger(ImageLibApiServiceImpl.class);

    @Autowired
    private ImageLibMapper imageLibMapper;

    @Autowired
    private ImageLibConverter imageLibConverter;

    @Autowired
    private MinioApiService minioApiService;

    public ImageLibVo queryImageLib(ImageLibQueryDTO imageLibQueryDTO) {

        List<ImageLibVo> imageLibVoList = queryImageLibList(imageLibQueryDTO);

        return ObjectUtil.isEmpty(imageLibVoList) ? null : imageLibVoList.get(0);

    }

    public List<ImageLibVo> queryImageLibList(ImageLibQueryDTO imageLibQueryDTO) {

        List<ImageLibDO> imageLibDOList = ChainWrappers.lambdaQueryChain(imageLibMapper).eq(ObjectUtil.isNotEmpty(imageLibQueryDTO.getImageType()), ImageLibDO::getImageType, imageLibQueryDTO.getImageType()).eq(ObjectUtil.isNotEmpty(imageLibQueryDTO.getStatus()), ImageLibDO::getStatus, imageLibQueryDTO.getStatus()).eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag()).list();

        if (ObjectUtil.isEmpty(imageLibDOList)) {

            return Collections.emptyList();

        }

        List<ImageLibVo> imageLibVoList = this.imageLibConverter.toImageLibVoList(imageLibDOList);

        if (ObjectUtil.isNotEmpty(imageLibVoList)) {

            imageLibVoList.forEach(imageLibVo -> {

                PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(imageLibVo.getImgPath()).build());

                imageLibVo.setImgPathUrl(ObjectUtil.isEmpty(imageLibVo) ? "" : imageLibVo.getImgPathUrl());

            });

        }

        return imageLibVoList;

    }

    public PageVo<ImageLibVo> queryPage(ImageLibQueryPageDTO imageLibQueryPageDTO) {

        Page page = new Page(imageLibQueryPageDTO.getPageStart().intValue(), imageLibQueryPageDTO.getPageSize().intValue(), true);

        LambdaQueryWrapper<ImageLibDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(imageLibQueryPageDTO.getImageType()), ImageLibDO::getImageType, imageLibQueryPageDTO.getImageType());
        wrapper.eq(ObjectUtil.isNotEmpty(imageLibQueryPageDTO.getStatus()), ImageLibDO::getStatus, imageLibQueryPageDTO.getStatus());
        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        wrapper.orderByDesc(BaseDO::getCreateTime);

        IPage<ImageLibDO> imageLibPage = this.imageLibMapper.selectPage((IPage) page, wrapper);

        List<ImageLibVo> imageLibVoList = this.imageLibConverter.toImageLibVoList(imageLibPage.getRecords());

        if (ObjectUtil.isNotEmpty(imageLibVoList)) {

            imageLibVoList.forEach(imageLibVo -> {

                PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(imageLibVo.getImgPath()).build());

                imageLibVo.setImgPathUrl(ObjectUtil.isEmpty(prevFileVo) ? "" : prevFileVo.getFileUrl());

            });

        }

        return PageVo.<ImageLibVo>builder().pageStart(imageLibQueryPageDTO.getPageStart()).pageSize(imageLibQueryPageDTO.getPageSize()).total(Long.valueOf(imageLibPage.getTotal())).data(imageLibVoList).build();

    }

    public Boolean batchSaveImageLib(List<ImageLibSaveOrUpdateDTO> imageLibSaveOrUpdateDTOList) {

        if (ObjectUtil.isEmpty(imageLibSaveOrUpdateDTOList)) {

            return Boolean.TRUE;

        }

        for (ImageLibSaveOrUpdateDTO imageLibSaveOrUpdateDTO : imageLibSaveOrUpdateDTOList) {

            ImageLibDO imageLibDO = ImageLibDO.builder().imageType(imageLibSaveOrUpdateDTO.getImageType()).imgPath(imageLibSaveOrUpdateDTO.getImgPath()).status(EnableStatusEnum.DISABLED.getStatus()).build();

            if (this.imageLibMapper.insert(imageLibDO) <= 0) {

                throw new BusinessException("保存图片信息失败.");

            }

        }

        return Boolean.TRUE;

    }

    public Boolean batchDelete(ImageLibBatchDeleteDTO imageLibBatchDeleteDTO) {
        return ChainWrappers.lambdaUpdateChain(imageLibMapper).set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag()).eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag()).eq(ObjectUtil.isNotEmpty(imageLibBatchDeleteDTO.getId()), BaseDO::getId, imageLibBatchDeleteDTO.getId()).in(ObjectUtil.isNotEmpty(imageLibBatchDeleteDTO.getIdList()), BaseDO::getId, imageLibBatchDeleteDTO.getIdList()).update();
    }

    public Boolean updateStatus(ImageLibUpdateStatusDTO imageLibUpdateStatusDTO) {

        return ChainWrappers.lambdaUpdateChain(imageLibMapper).set(ImageLibDO::getStatus, imageLibUpdateStatusDTO.getStatus()).eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag()).eq(ObjectUtil.isNotEmpty(imageLibUpdateStatusDTO.getId()), BaseDO::getId, imageLibUpdateStatusDTO.getId()).in(ObjectUtil.isNotEmpty(imageLibUpdateStatusDTO.getIdList()), BaseDO::getId, imageLibUpdateStatusDTO.getIdList()).update();
    }

}
