
package com.porn.service.imglib.impl;


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
    /*  34 */   private static final Logger log = LoggerFactory.getLogger(ImageLibApiServiceImpl.class);



    @Autowired
     private ImageLibMapper imageLibMapper;



    @Autowired
     private ImageLibConverter imageLibConverter;



    @Autowired
     private MinioApiService minioApiService;





    public ImageLibVo queryImageLib(ImageLibQueryDTO imageLibQueryDTO) {
        /*  51 */
        List<ImageLibVo> imageLibVoList = queryImageLibList(imageLibQueryDTO);
        /*  52 */
        return ObjectUtil.isEmpty(imageLibVoList) ? null : imageLibVoList.get(0);

    }







    public List<ImageLibVo> queryImageLibList(ImageLibQueryDTO imageLibQueryDTO) {
        /*  60 */
        List<ImageLibDO> imageLibDOList = ChainWrappers.lambdaQueryChain(imageLibMapper)
                .eq(ObjectUtil.isNotEmpty(imageLibQueryDTO.getImageType()), ImageLibDO::getImageType, imageLibQueryDTO.getImageType())
                .eq(ObjectUtil.isNotEmpty(imageLibQueryDTO.getStatus()), ImageLibDO::getStatus, imageLibQueryDTO.getStatus())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();
        /*  61 */
        if (ObjectUtil.isEmpty(imageLibDOList)) {
            /*  62 */
            return Collections.emptyList();

        }
        /*  64 */
        List<ImageLibVo> imageLibVoList = this.imageLibConverter.toImageLibVoList(imageLibDOList);
        /*  65 */
        if (ObjectUtil.isNotEmpty(imageLibVoList)) {
            /*  66 */
            imageLibVoList.forEach(imageLibVo -> {

                PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(imageLibVo.getImgPath()).build());

                imageLibVo.setImgPathUrl(ObjectUtil.isEmpty(imageLibVo) ? "" : imageLibVo.getImgPathUrl());

            });

        }
        /*  71 */
        return imageLibVoList;

    }



    public PageVo<ImageLibVo> queryPage(ImageLibQueryPageDTO imageLibQueryPageDTO) {
        /*  75 */
        Page page = new Page(imageLibQueryPageDTO.getPageStart().intValue(), imageLibQueryPageDTO.getPageSize().intValue(), true);

        LambdaQueryWrapper<ImageLibDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(imageLibQueryPageDTO.getImageType()), ImageLibDO::getImageType, imageLibQueryPageDTO.getImageType());
        wrapper.eq(ObjectUtil.isNotEmpty(imageLibQueryPageDTO.getStatus()), ImageLibDO::getStatus, imageLibQueryPageDTO.getStatus());
        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        wrapper.orderByDesc(BaseDO::getCreateTime);

        /*  82 */
        IPage<ImageLibDO> imageLibPage = this.imageLibMapper.selectPage((IPage) page, wrapper);
        /*  83 */
        List<ImageLibVo> imageLibVoList = this.imageLibConverter.toImageLibVoList(imageLibPage.getRecords());
        /*  84 */
        if (ObjectUtil.isNotEmpty(imageLibVoList)) {
            /*  85 */
            imageLibVoList.forEach(imageLibVo -> {

                PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(imageLibVo.getImgPath()).build());

                imageLibVo.setImgPathUrl(ObjectUtil.isEmpty(prevFileVo) ? "" : prevFileVo.getFileUrl());

            });

        }
        /*  90 */
        return PageVo.<ImageLibVo>builder()
/*  91 */.pageStart(imageLibQueryPageDTO.getPageStart())
/*  92 */.pageSize(imageLibQueryPageDTO.getPageSize())
/*  93 */.total(Long.valueOf(imageLibPage.getTotal()))
/*  94 */.data(imageLibVoList)
/*  95 */.build();

    }



    public Boolean batchSaveImageLib(List<ImageLibSaveOrUpdateDTO> imageLibSaveOrUpdateDTOList) {
        /*  99 */
        if (ObjectUtil.isEmpty(imageLibSaveOrUpdateDTOList)) {
            /* 100 */
            return Boolean.TRUE;

        }
        /* 102 */
        for (ImageLibSaveOrUpdateDTO imageLibSaveOrUpdateDTO : imageLibSaveOrUpdateDTOList) {




            /* 107 */
            ImageLibDO imageLibDO = ImageLibDO.builder().imageType(imageLibSaveOrUpdateDTO.getImageType()).imgPath(imageLibSaveOrUpdateDTO.getImgPath()).status(EnableStatusEnum.DISABLED.getStatus()).build();
            /* 108 */
            if (this.imageLibMapper.insert(imageLibDO) <= 0) {
                /* 109 */
                throw new BusinessException("保存图片信息失败.");

            }

        }
        /* 112 */
        return Boolean.TRUE;

    }



    public Boolean batchDelete(ImageLibBatchDeleteDTO imageLibBatchDeleteDTO) {
        return ChainWrappers.lambdaUpdateChain(imageLibMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .eq(ObjectUtil.isNotEmpty(imageLibBatchDeleteDTO.getId()), BaseDO::getId, imageLibBatchDeleteDTO.getId())
                .in(ObjectUtil.isNotEmpty(imageLibBatchDeleteDTO.getIdList()), BaseDO::getId, imageLibBatchDeleteDTO.getIdList())
                .update();
    }



    public Boolean updateStatus(ImageLibUpdateStatusDTO imageLibUpdateStatusDTO) {
        /* 125 */
        return
                ChainWrappers.lambdaUpdateChain(imageLibMapper)
                        .set(ImageLibDO::getStatus, imageLibUpdateStatusDTO.getStatus())
                        .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                        .eq(ObjectUtil.isNotEmpty(imageLibUpdateStatusDTO.getId()), BaseDO::getId, imageLibUpdateStatusDTO.getId())
                        .in(ObjectUtil.isNotEmpty(imageLibUpdateStatusDTO.getIdList()), BaseDO::getId, imageLibUpdateStatusDTO.getIdList())
                        .update();
    }

}
