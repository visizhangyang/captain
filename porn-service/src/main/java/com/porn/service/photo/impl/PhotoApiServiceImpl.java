package com.porn.service.photo.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.minio.api.MinioApiService;
import com.porn.client.minio.dto.PrevFileDTO;
import com.porn.client.minio.vo.PrevFileVo;
import com.porn.client.photo.api.PhotoApiService;
import com.porn.client.photo.dto.PhotoQueryDTO;
import com.porn.client.photo.dto.PhotoQueryPageDTO;
import com.porn.client.photo.dto.PhotoSaveOrUpdateDTO;
import com.porn.client.photo.vo.PhotoVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.photo.converter.PhotoConverter;
import com.porn.service.photo.dao.entity.PhotoDO;
import com.porn.service.photo.dao.mapper.PhotoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collections;
import java.util.List;


@Service

@Transactional(rollbackFor = {Exception.class})
public class PhotoApiServiceImpl implements PhotoApiService {
    private static final Logger log = LoggerFactory.getLogger(PhotoApiServiceImpl.class);


    @Autowired
    private PhotoMapper photoMapper;


    @Autowired
    private PhotoConverter photoConverter;


    @Autowired
    private MinioApiService minioApiService;


    public PhotoVo queryPhoto(PhotoQueryDTO photoQueryDTO) {

        List<PhotoVo> photoVoList = queryPhotoList(photoQueryDTO);

        return ObjectUtil.isEmpty(photoVoList) ? null : photoVoList.get(0);

    }


    public List<PhotoVo> queryPhotoList(PhotoQueryDTO photoQueryDTO) {

        List<PhotoDO> photoDOList = ChainWrappers.lambdaQueryChain(photoMapper)
                .eq(ObjectUtil.isNotEmpty(photoQueryDTO.getId()), BaseDO::getId, photoQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(photoQueryDTO.getAccountId()), PhotoDO::getAccountId, photoQueryDTO.getAccountId())
                .eq(ObjectUtil.isNotEmpty(photoQueryDTO.getLocalIdentifier()), PhotoDO::getLocalIdentifier, photoQueryDTO.getLocalIdentifier())
                .like(ObjectUtil.isNotEmpty(photoQueryDTO.getLkAccountName()), PhotoDO::getAccountName, photoQueryDTO.getLkAccountName())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();

        if (ObjectUtil.isEmpty(photoDOList)) {

            return Collections.emptyList();

        }

        List<PhotoVo> photoVoList = this.photoConverter.toPhotoVoList(photoDOList);

        if (ObjectUtil.isNotEmpty(photoVoList)) {

            for (PhotoVo photoVo : photoVoList) {

                if (ObjectUtil.isNotEmpty(photoVo.getFilePath())) {

                    PrevFileVo prevFileVo = this.minioApiService.prevFilePhoto(PrevFileDTO.builder().filePath(photoVo.getFilePath()).build());

                    if (ObjectUtil.isNotEmpty(prevFileVo)) {

                        photoVo.setFileUrl(prevFileVo.getFileUrl());

                    }

                }

            }

        }

        return photoVoList;

    }

    public PageVo<PhotoVo> queryPage(PhotoQueryPageDTO photoQueryPageDTO) {

        Page page = new Page(photoQueryPageDTO.getPageStart().intValue(), photoQueryPageDTO.getPageSize().intValue(), true);

        LambdaQueryWrapper<PhotoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(photoQueryPageDTO.getAccountId()), PhotoDO::getAccountId, photoQueryPageDTO.getAccountId());
        wrapper.eq(ObjectUtil.isNotEmpty(photoQueryPageDTO.getLocalIdentifier()), PhotoDO::getLocalIdentifier, photoQueryPageDTO.getLocalIdentifier());
        wrapper.like(ObjectUtil.isNotEmpty(photoQueryPageDTO.getLkAccountName()), PhotoDO::getAccountName, photoQueryPageDTO.getLkAccountName());
        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        wrapper.orderByDesc(BaseDO::getCreateTime);

        IPage<PhotoDO> photoPage = this.photoMapper.selectPage((IPage) page, wrapper);

        List<PhotoVo> photoVoList = this.photoConverter.toPhotoVoList(photoPage.getRecords());

        if (ObjectUtil.isNotEmpty(photoVoList)) {

            for (PhotoVo photoVo : photoVoList) {

                if (ObjectUtil.isNotEmpty(photoVo.getFilePath())) {

                    PrevFileVo prevFileVo = this.minioApiService.prevFilePhoto(PrevFileDTO.builder().filePath(photoVo.getFilePath()).build());

                    if (ObjectUtil.isNotEmpty(prevFileVo)) {

                        photoVo.setFileUrl(prevFileVo.getFileUrl());

                    }

                }

            }

        }

        return PageVo.<PhotoVo>builder()
                .pageStart(photoQueryPageDTO.getPageStart())
                .pageSize(photoQueryPageDTO.getPageSize())
                .total(Long.valueOf(photoPage.getTotal()))
                .data(photoVoList)
                .build();

    }

    public PageVo<PhotoVo> queryRemotePage(PhotoQueryPageDTO photoQueryPageDTO) {

        String url = "http://43.154.153.41:8888/mng/business/photo/queryPage";


        String rsp = HttpUtil
                .createPost(url)
                .setConnectionTimeout(10000)
                .setReadTimeout(10000)
                .setProxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 1080)))
                .contentType("application/json")
                .body(JSON.toJSONString(photoQueryPageDTO))
                .execute()
                .body();

        JSONObject jObj = JSON.parseObject(rsp);

        JSONObject jBody = jObj.getJSONObject("body");

        PageVo<PhotoVo> page = (PageVo<PhotoVo>) jBody.toJavaObject(new TypeReference<PageVo<PhotoVo>>() {
        });

        return page;

    }


    public PhotoVo saveOrUpdate(PhotoSaveOrUpdateDTO photoSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(photoSaveOrUpdateDTO.getId())) {

            PhotoDO photoDO = PhotoDO.builder().accountId(photoSaveOrUpdateDTO.getAccountId()).accountName(photoSaveOrUpdateDTO.getAccountName()).filePath(photoSaveOrUpdateDTO.getFilePath()).localIdentifier(photoSaveOrUpdateDTO.getLocalIdentifier()).build();

            if (this.photoMapper.insert(photoDO) <= 0) {

                throw new BusinessException("保存照片信息失败.");

            }

            return queryPhoto(((PhotoQueryDTO.PhotoQueryDTOBuilder) PhotoQueryDTO.builder().id(photoDO.getId())).build());

        }


        boolean rs = ChainWrappers.lambdaUpdateChain(photoMapper)
                .set(ObjectUtil.isNotEmpty(photoSaveOrUpdateDTO.getAccountId()), PhotoDO::getAccountId, photoSaveOrUpdateDTO.getAccountId())
                .set(ObjectUtil.isNotEmpty(photoSaveOrUpdateDTO.getAccountName()), PhotoDO::getAccountName, photoSaveOrUpdateDTO.getAccountName())
                .set(ObjectUtil.isNotEmpty(photoSaveOrUpdateDTO.getFilePath()), PhotoDO::getFilePath, photoSaveOrUpdateDTO.getFilePath())
                .set(ObjectUtil.isNotEmpty(photoSaveOrUpdateDTO.getLocalIdentifier()), PhotoDO::getLocalIdentifier, photoSaveOrUpdateDTO.getLocalIdentifier())
                .eq(BaseDO::getId, photoSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新照片信息失败.");

        }

        return queryPhoto(((PhotoQueryDTO.PhotoQueryDTOBuilder) PhotoQueryDTO.builder().id(photoSaveOrUpdateDTO.getId())).build());

    }

}

