package com.porn.service.recommendapp.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
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
import com.porn.client.recommendapp.api.RecommendAppService;
import com.porn.client.recommendapp.dto.RecommendAppDeleteDTO;
import com.porn.client.recommendapp.dto.RecommendAppQueryDTO;
import com.porn.client.recommendapp.dto.RecommendAppQueryPageDTO;
import com.porn.client.recommendapp.dto.RecommendAppSaveOrUpdateDTO;
import com.porn.client.recommendapp.enums.RecommendTypeEnum;
import com.porn.client.recommendapp.vo.RecommendAppVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.recommendapp.converter.RecommendAppConverter;
import com.porn.service.recommendapp.dao.entity.RecommendAppDO;
import com.porn.service.recommendapp.dao.mapper.RecommendAppMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service

@Transactional(rollbackFor = {Exception.class})
public class RecommendAppServiceImpl implements RecommendAppService {
    private static final Logger log = LoggerFactory.getLogger(RecommendAppServiceImpl.class);


    @Autowired
    private RecommendAppConverter recommendAppConverter;


    @Autowired
    private RecommendAppMapper recommendAppMapper;


    @Autowired
    private MinioApiService minioApiService;


    public RecommendAppVo queryRecommendApp(RecommendAppQueryDTO recommendAppQueryDTO) {

        List<RecommendAppVo> recommendAppVoList = queryRecommendAppList(recommendAppQueryDTO);

        return ObjectUtil.isEmpty(recommendAppVoList) ? null : recommendAppVoList.get(0);

    }

    public List<RecommendAppVo> queryRecommendAppList(RecommendAppQueryDTO recommendAppQueryDTO) {

        List<RecommendAppDO> streamList = ChainWrappers.lambdaQueryChain(recommendAppMapper)
                .eq(ObjectUtil.isNotEmpty(recommendAppQueryDTO.getAppType()), RecommendAppDO::getAppType, recommendAppQueryDTO.getAppType())
                .eq(ObjectUtil.isNotEmpty(recommendAppQueryDTO.getRecommendType()), RecommendAppDO::getRecommendType, recommendAppQueryDTO.getRecommendType())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByAsc(RecommendAppDO::getSortNo)
                .list();

        List<RecommendAppVo> recommendAppVoList = this.recommendAppConverter.toRecommendAppVoList(streamList);


        if (ObjectUtil.isNotEmpty(recommendAppVoList)) {

            recommendAppVoList.forEach(recommendAppVo -> {

                if (ObjectUtil.isNotEmpty(recommendAppVo.getAvatar())) {

                    PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(recommendAppVo.getAvatar()).build());

                    if (ObjectUtil.isNotEmpty(prevFileVo)) {

                        recommendAppVo.setAvatarUrl(prevFileVo.getFileUrl());

                    }

                }

                if (!HttpUtil.isHttp(recommendAppVo.getApkUrl()) && !HttpUtil.isHttps(recommendAppVo.getApkUrl())) {

                    PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(recommendAppVo.getApkUrl()).build());

                    if (ObjectUtil.isNotEmpty(prevFileVo)) {

                        recommendAppVo.setApkUrl(prevFileVo.getFileUrl());

                    }

                }

            });

        }

        return recommendAppVoList;

    }


    public PageVo<RecommendAppVo> queryPage(RecommendAppQueryPageDTO recommendAppQueryPageDTO) {

        Page page = new Page(recommendAppQueryPageDTO.getPageStart().intValue(), recommendAppQueryPageDTO.getPageSize().intValue(), true);

        LambdaQueryWrapper<RecommendAppDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(recommendAppQueryPageDTO.getAppType()), RecommendAppDO::getAppType, recommendAppQueryPageDTO.getAppType());
        wrapper.eq(ObjectUtil.isNotEmpty(recommendAppQueryPageDTO.getRecommendType()), RecommendAppDO::getRecommendType, recommendAppQueryPageDTO.getRecommendType());
        wrapper.like(ObjectUtil.isNotEmpty(recommendAppQueryPageDTO.getLkName()), RecommendAppDO::getName, recommendAppQueryPageDTO.getLkName());
        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        wrapper.orderByAsc(RecommendAppDO::getSortNo);

        IPage<RecommendAppDO> recommendAppPage = this.recommendAppMapper.selectPage((IPage) page, wrapper);

        List<RecommendAppVo> recommendAppVoList = this.recommendAppConverter.toRecommendAppVoList(recommendAppPage.getRecords());

        if (ObjectUtil.isNotEmpty(recommendAppVoList)) {

            recommendAppVoList.forEach(recommendAppVo -> {

                if (ObjectUtil.isNotEmpty(recommendAppVo.getAvatar())) {

                    PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(recommendAppVo.getAvatar()).build());

                    if (ObjectUtil.isNotEmpty(prevFileVo)) {

                        recommendAppVo.setAvatarUrl(prevFileVo.getFileUrl());

                    }

                }

                if (!HttpUtil.isHttp(recommendAppVo.getApkUrl()) && !HttpUtil.isHttps(recommendAppVo.getApkUrl())) {

                    PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(recommendAppVo.getApkUrl()).build());

                    if (ObjectUtil.isNotEmpty(prevFileVo)) {

                        recommendAppVo.setApkUrl(prevFileVo.getFileUrl());

                    }

                }

            });

        }

        return PageVo.<RecommendAppVo>builder()
                .pageStart(recommendAppQueryPageDTO.getPageStart())
                .pageSize(recommendAppQueryPageDTO.getPageSize())
                .total(Long.valueOf(recommendAppPage.getTotal()))
                .data(recommendAppVoList)
                .build();

    }


    public RecommendAppVo saveOrUpdate(RecommendAppSaveOrUpdateDTO recommendAppSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(recommendAppSaveOrUpdateDTO.getId())) {

            RecommendAppDO recommendAppDO = this.recommendAppConverter.toRecommendAppDO(recommendAppSaveOrUpdateDTO);

            List<RecommendAppVo> recommendAppVoList = queryRecommendAppList(RecommendAppQueryDTO.builder().recommendType(recommendAppSaveOrUpdateDTO.getRecommendType()).build());

            if (RecommendTypeEnum.MARKET.getType().equals(recommendAppSaveOrUpdateDTO.getRecommendType()) &&
                    ObjectUtil.isNotEmpty(recommendAppVoList)) {

                throw new BusinessException("营销类型推荐已经存在.");

            }


            recommendAppDO.setSortNo(Integer.valueOf(ObjectUtil.isEmpty(recommendAppVoList) ? 0 : (((RecommendAppVo) recommendAppVoList.get(recommendAppVoList.size() - 1)).getSortNo().intValue() + 1)));

            recommendAppDO.setCopyFlag(Integer.valueOf(ObjectUtil.isEmpty(recommendAppDO.getCopyFlag()) ? 0 : recommendAppDO.getCopyFlag().intValue()));

            if (this.recommendAppMapper.insert(recommendAppDO) <= 0) {

                throw new BusinessException("保存推荐app信息失败.");

            }

            return queryRecommendApp(((RecommendAppQueryDTO.RecommendAppQueryDTOBuilder) RecommendAppQueryDTO.builder().id(recommendAppDO.getId())).build());

        }


        boolean rs = ChainWrappers.lambdaUpdateChain(recommendAppMapper)
                .set(ObjectUtil.isNotEmpty(recommendAppSaveOrUpdateDTO.getAvatar()), RecommendAppDO::getAvatar, recommendAppSaveOrUpdateDTO.getAvatar())
                .set(ObjectUtil.isNotEmpty(recommendAppSaveOrUpdateDTO.getCode()), RecommendAppDO::getCode, recommendAppSaveOrUpdateDTO.getCode())
                .set(ObjectUtil.isNotEmpty(recommendAppSaveOrUpdateDTO.getName()), RecommendAppDO::getName, recommendAppSaveOrUpdateDTO.getName())
                .set(ObjectUtil.isNotEmpty(recommendAppSaveOrUpdateDTO.getAppType()), RecommendAppDO::getAppType, recommendAppSaveOrUpdateDTO.getAppType())
                .set(ObjectUtil.isNotEmpty(recommendAppSaveOrUpdateDTO.getApkUrl()), RecommendAppDO::getApkUrl, recommendAppSaveOrUpdateDTO.getApkUrl())
                .set(ObjectUtil.isNotEmpty(recommendAppSaveOrUpdateDTO.getSortNo()), RecommendAppDO::getSortNo, recommendAppSaveOrUpdateDTO.getSortNo())
                .set(ObjectUtil.isNotEmpty(recommendAppSaveOrUpdateDTO.getCopyFlag()), RecommendAppDO::getCopyFlag, recommendAppSaveOrUpdateDTO.getCopyFlag())
                .set(ObjectUtil.isNotEmpty(recommendAppSaveOrUpdateDTO.getRecommendType()), RecommendAppDO::getRecommendType, recommendAppSaveOrUpdateDTO.getRecommendType())
                .set(RecommendAppDO::getAccountLevels, JSON.toJSONString(CollUtil.defaultIfEmpty(recommendAppSaveOrUpdateDTO.getAccountLevelList(), new ArrayList())))
                .eq(BaseDO::getId, recommendAppSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {

            throw new BusinessException("更新推荐app信息失败.");

        }

        return queryRecommendApp(((RecommendAppQueryDTO.RecommendAppQueryDTOBuilder) RecommendAppQueryDTO.builder().id(recommendAppSaveOrUpdateDTO.getId())).build());

    }

    public Boolean delete(RecommendAppDeleteDTO recommendAppDeleteDTO) {

        return ChainWrappers.lambdaUpdateChain(recommendAppMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, recommendAppDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }

}

