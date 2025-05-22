
package com.porn.service.notice.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.enums.LangTypeEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.notice.api.NoticeApiService;
import com.porn.client.notice.dto.*;
import com.porn.client.notice.vo.NoticeVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.notice.converter.NoticeConverter;
import com.porn.service.notice.dao.entity.NoticeDO;
import com.porn.service.notice.dao.mapper.NoticeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;



























@Service

@Transactional(rollbackFor = {Exception.class})
 public class NoticeApiServiceImpl implements NoticeApiService {
    /*  30 */   private static final Logger log = LoggerFactory.getLogger(NoticeApiServiceImpl.class);




    @Autowired
     private NoticeConverter noticeConverter;



    @Autowired
     private NoticeMapper noticeMapper;





    public NoticeVo queryNotice(NoticeQueryDTO noticeQueryDTO) {
        /*  44 */
        List<NoticeVo> noticeVoList = queryNoticeList(noticeQueryDTO);
        /*  45 */
        return ObjectUtil.isEmpty(noticeVoList) ? null : noticeVoList.get(0);

    }










    public List<NoticeVo> queryNoticeList(NoticeQueryDTO noticeQueryDTO) {
        /*  56 */
        List<NoticeDO> noticeList = ChainWrappers.lambdaQueryChain(noticeMapper)
                .eq(ObjectUtil.isNotEmpty(noticeQueryDTO.getId()), BaseDO::getId, noticeQueryDTO.getId())
                .eq(ObjectUtil.isNotEmpty(noticeQueryDTO.getTopFlag()), NoticeDO::getTopFlag, noticeQueryDTO.getTopFlag())
                .eq(ObjectUtil.isNotEmpty(noticeQueryDTO.getLangType()), NoticeDO::getLangType, noticeQueryDTO.getLangType())
                .like(ObjectUtil.isNotEmpty(noticeQueryDTO.getLkTitle()), NoticeDO::getTitle, noticeQueryDTO.getLkTitle())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByDesc(NoticeDO::getTopFlag, BaseDO::getCreateTime)
                .list();
        /*  57 */
        if (ObjectUtil.isEmpty(noticeList)) {
            /*  58 */
            return Collections.emptyList();

        }
        /*  60 */
        List<NoticeVo> noticeVoList = this.noticeConverter.toNoticeVoList(noticeList);
        /*  61 */
        if (ObjectUtil.isNotEmpty(noticeVoList)) {
            /*  62 */
            noticeVoList.forEach(noticeVo -> {

                if (ObjectUtil.isNotEmpty(noticeVo.getLangType())) {

                    LangTypeEnum langTypeEnum = LangTypeEnum.queryByType(noticeVo.getLangType());

                    if (ObjectUtil.isNotEmpty(langTypeEnum)) {

                        noticeVo.setLangTypeName(langTypeEnum.getDescription());

                    }

                }

            });

        }
        /*  71 */
        return noticeVoList;

    }



    public PageVo<NoticeVo> queryPage(NoticeQueryPageDTO noticeQueryPageDTO) {
        /*  75 */
        Page page = new Page(noticeQueryPageDTO.getPageStart().intValue(), noticeQueryPageDTO.getPageSize().intValue(), true);
        /*  76 */
//        QueryWrapper<NoticeDO> queryWrapper = new QueryWrapper();
//        LambdaQueryChainWrapper lambdaQueryChainWrapper = ChainWrappers.lambdaQueryChain(noticeMapper)
//                .eq(ObjectUtil.isNotEmpty(noticeQueryPageDTO.getTopFlag()), NoticeDO::getTopFlag, noticeQueryPageDTO.getTopFlag())
//        /*  79 */.eq(ObjectUtil.isNotEmpty(noticeQueryPageDTO.getLangType()), NoticeDO::getLangType, noticeQueryPageDTO.getLangType())
//        /*  80 */.like(ObjectUtil.isNotEmpty(noticeQueryPageDTO.getLkTitle()), NoticeDO::getTitle, noticeQueryPageDTO.getLkTitle())
//        /*  81 */.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
//        /*  82 */.orderByDesc(NoticeDO::getTopFlag, BaseDO::getCreateTime);
        LambdaQueryWrapper<NoticeDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(noticeQueryPageDTO.getTopFlag()), NoticeDO::getTopFlag, noticeQueryPageDTO.getTopFlag());
        wrapper.eq(ObjectUtil.isNotEmpty(noticeQueryPageDTO.getLangType()), NoticeDO::getLangType, noticeQueryPageDTO.getLangType());
        wrapper.like(ObjectUtil.isNotEmpty(noticeQueryPageDTO.getLkTitle()), NoticeDO::getTitle, noticeQueryPageDTO.getLkTitle());
        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        wrapper.orderByDesc(NoticeDO::getTopFlag, BaseDO::getCreateTime);


        /*  77 */
//        ((LambdaQueryWrapper) ((LambdaQueryWrapper) ((LambdaQueryWrapper) ((LambdaQueryWrapper) queryWrapper.lambda()
///*  78 */.eq(ObjectUtil.isNotEmpty(noticeQueryPageDTO.getTopFlag()), NoticeDO::getTopFlag, noticeQueryPageDTO.getTopFlag()))
///*  79 */.eq(ObjectUtil.isNotEmpty(noticeQueryPageDTO.getLangType()), NoticeDO::getLangType, noticeQueryPageDTO.getLangType()))
///*  80 */.like(ObjectUtil.isNotEmpty(noticeQueryPageDTO.getLkTitle()), NoticeDO::getTitle, noticeQueryPageDTO.getLkTitle()))
///*  81 */.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag()))
///*  82 */.orderByDesc((Object[]) new SFunction[]{NoticeDO::getTopFlag, BaseDO::getCreateTime});
        /*  83 */
        IPage<NoticeDO> noticePage = this.noticeMapper.selectPage((IPage) page, wrapper);
        /*  84 */
        List<NoticeVo> noticeVoList = this.noticeConverter.toNoticeVoList(noticePage.getRecords());
        /*  85 */
        if (ObjectUtil.isNotEmpty(noticeVoList)) {
            /*  86 */
            noticeVoList.forEach(noticeVo -> {

                if (ObjectUtil.isNotEmpty(noticeVo.getLangType())) {

                    LangTypeEnum langTypeEnum = LangTypeEnum.queryByType(noticeVo.getLangType());

                    if (ObjectUtil.isNotEmpty(langTypeEnum)) {

                        noticeVo.setLangTypeName(langTypeEnum.getDescription());

                    }

                }

            });

        }
        /*  95 */
        return PageVo.<NoticeVo>builder()
/*  96 */.pageStart(noticeQueryPageDTO.getPageStart())
/*  97 */.pageSize(noticeQueryPageDTO.getPageSize())
/*  98 */.total(Long.valueOf(noticePage.getTotal()))
/*  99 */.data(noticeVoList)
/* 100 */.build();

    }



    public NoticeVo saveOrUpdate(NoticeSaveOrUpdateDTO noticeSaveOrUpdateDTO) {
        /* 104 */
        if (ObjectUtil.isEmpty(noticeSaveOrUpdateDTO.getId())) {
            /* 105 */
            NoticeDO noticeDO = this.noticeConverter.toNoticeDO(noticeSaveOrUpdateDTO);
            /* 106 */
            if (this.noticeMapper.insert(noticeDO) <= 0) {
                /* 107 */
                throw new BusinessException("保存公告信息失败.");

            }
            /* 109 */
            return queryNotice(((NoticeQueryDTO.NoticeQueryDTOBuilder) NoticeQueryDTO.builder().id(noticeDO.getId())).build());

        }







        /* 118 */
        boolean rs =  ChainWrappers.lambdaUpdateChain(noticeMapper)
                .set(ObjectUtil.isNotEmpty(noticeSaveOrUpdateDTO.getTopFlag()), NoticeDO::getTopFlag, noticeSaveOrUpdateDTO.getTopFlag())
                .set(ObjectUtil.isNotEmpty(noticeSaveOrUpdateDTO.getLangType()), NoticeDO::getLangType, noticeSaveOrUpdateDTO.getLangType())
                .set(ObjectUtil.isNotEmpty(noticeSaveOrUpdateDTO.getTitle()), NoticeDO::getTitle, noticeSaveOrUpdateDTO.getTitle())
                .set(ObjectUtil.isNotEmpty(noticeSaveOrUpdateDTO.getContent()), NoticeDO::getContent, noticeSaveOrUpdateDTO.getContent())
                .eq(BaseDO::getId, noticeSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
        /* 119 */
        if (!rs)
             {
            /* 121 */
            throw new BusinessException("更新公告信息失败.");

        }
        /* 123 */
        return queryNotice(((NoticeQueryDTO.NoticeQueryDTOBuilder) NoticeQueryDTO.builder().id(noticeSaveOrUpdateDTO.getId())).build());

    }




    public Boolean delete(NoticeDeleteDTO noticeDeleteDTO) {
        /* 128 */
        return ChainWrappers.lambdaUpdateChain(noticeMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(ObjectUtil.isNotEmpty(noticeDeleteDTO.getId()), BaseDO::getId, noticeDeleteDTO.getId())
                .in(ObjectUtil.isNotEmpty(noticeDeleteDTO.getIdList()), BaseDO::getId, noticeDeleteDTO.getIdList())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 133 */.update();

    }



    public Boolean updateCreateTime(NoticeUpdateCreateTimeDTO noticeUpdateCreateTimeDTO) {
        /* 137 */
        return  ChainWrappers.lambdaUpdateChain(noticeMapper)
                .set(ObjectUtil.isNotEmpty(noticeUpdateCreateTimeDTO.getCreateTime()), BaseDO::getCreateTime, noticeUpdateCreateTimeDTO.getCreateTime())
                .eq(BaseDO::getId, noticeUpdateCreateTimeDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 141 */.update();

    }



    public List<NoticeVo> queryNoticeReadStatusList(NoticeQueryReadStatusDTO noticeQueryReadStatusDTO) {
        /* 145 */
        return this.noticeMapper.queryNoticeReadStatusList(noticeQueryReadStatusDTO);

    }

}


