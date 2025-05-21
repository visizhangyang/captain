
package com.porn.service.goods.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.goods.api.GoodsApiService;
import com.porn.client.goods.dto.*;
import com.porn.client.goods.enums.GoodsStatusEnum;
import com.porn.client.goods.vo.GoodsVo;
import com.porn.client.merchant.api.MerchantApiService;
import com.porn.client.merchant.dto.MerchantQueryDTO;
import com.porn.client.merchant.vo.MerchantVo;
import com.porn.client.minio.api.MinioApiService;
import com.porn.client.minio.dto.PrevFileDTO;
import com.porn.client.minio.vo.PrevFileVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.goods.converter.GoodsConverter;
import com.porn.service.goods.dao.entity.GoodsDO;
import com.porn.service.goods.dao.mapper.GoodsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;



@Service

@Transactional(rollbackFor = {Exception.class})
 public class GoodsApiServiceImpl implements GoodsApiService {
    /*  47 */   private static final Logger log = LoggerFactory.getLogger(GoodsApiServiceImpl.class);



    @Autowired
     private GoodsConverter goodsConverter;



    @Autowired
     private GoodsMapper goodsMapper;



    @Autowired
     private MinioApiService minioApiService;



    @Autowired
     private MerchantApiService merchantApiService;


    @Autowired
     private ParamsetApiService paramsetApiService;


    @Autowired
     private AccountApiService accountApiService;




    public GoodsVo queryGoods(GoodsQueryDTO goodsQueryDTO) {
        /*  73 */
        List<GoodsVo> goodsVoList = queryGoodsList(goodsQueryDTO);
        /*  74 */
        return ObjectUtil.isEmpty(goodsVoList) ? null : goodsVoList.get(0);

    }









    public List<GoodsVo> queryGoodsList(GoodsQueryDTO goodsQueryDTO) {
        // 构建查询条件
        LambdaQueryChainWrapper<GoodsDO> query = ChainWrappers.lambdaQueryChain((BaseMapper<GoodsDO>) this.goodsMapper);

        if (ObjectUtil.isNotEmpty(goodsQueryDTO.getMerchantId())) {
            query.eq(BaseDO::getId, goodsQueryDTO.getMerchantId());
        }
        if (ObjectUtil.isNotEmpty(goodsQueryDTO.getMerchantName())) {
            query.eq(GoodsDO::getMerchantName, goodsQueryDTO.getMerchantName());
        }
        if (ObjectUtil.isNotEmpty(goodsQueryDTO.getId())) {
            query.eq(BaseDO::getId, goodsQueryDTO.getId());
        }

        query.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByDesc(BaseDO::getCreateTime);

        List<GoodsDO> goodsList = query.list();

        if (CollUtil.isEmpty(goodsList)) {
            return Collections.emptyList();
        }

        // 转换为 VO
        List<GoodsVo> goodsVoList = goodsConverter.toGoodsVoList(goodsList);

        // 补充头像链接
        goodsVoList.forEach(this::fillMerchantAvatar);

        return goodsVoList;


    }



    public PageVo<GoodsVo> queryPage(GoodsQueryPageDTO goodsQueryPageDTO) {
        // 构建分页对象
        Page<GoodsDO> page = new Page<>(
                goodsQueryPageDTO.getPageStart().intValue(),
                goodsQueryPageDTO.getPageSize().intValue(),
                true
        );

        // 构建查询条件
        LambdaQueryWrapper<GoodsDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(goodsQueryPageDTO.getMerchantId()), GoodsDO::getMerchantId, goodsQueryPageDTO.getMerchantId())
                .in(ObjectUtil.isNotEmpty(goodsQueryPageDTO.getMerchantIdList()), GoodsDO::getMerchantId, goodsQueryPageDTO.getMerchantIdList())
                .eq(ObjectUtil.isNotEmpty(goodsQueryPageDTO.getGoodsStatus()), GoodsDO::getGoodsStatus, goodsQueryPageDTO.getGoodsStatus())
                .like(ObjectUtil.isNotEmpty(goodsQueryPageDTO.getLkAccountName()), GoodsDO::getAccountName, goodsQueryPageDTO.getLkAccountName())
                .eq(ObjectUtil.isNotEmpty(goodsQueryPageDTO.getAccountId()), GoodsDO::getAccountId, goodsQueryPageDTO.getAccountId())
                .eq(ObjectUtil.isNotEmpty(goodsQueryPageDTO.getGoodsType()) && CommonConst.IZERO.equals(goodsQueryPageDTO.getGoodsType()),
                        GoodsDO::getAccountId, CommonConst.IZERO)
                .notIn(ObjectUtil.isNotEmpty(goodsQueryPageDTO.getGoodsType()) && !CommonConst.IZERO.equals(goodsQueryPageDTO.getGoodsType()),
                        GoodsDO::getAccountId, CommonConst.IZERO)
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .orderByDesc(BaseDO::getCreateTime);

        // 分页查询
        IPage<GoodsDO> goodsPage = goodsMapper.selectPage(page, wrapper);

        // DO 转 VO
        List<GoodsVo> goodsVoList = goodsConverter.toGoodsVoList(goodsPage.getRecords());

        // 填充头像
        goodsVoList.forEach(this::fillMerchantAvatar);

        // 返回分页结果
        return PageVo.<GoodsVo>builder()
                .pageStart(goodsQueryPageDTO.getPageStart())
                .pageSize(goodsQueryPageDTO.getPageSize())
                .total(goodsPage.getTotal())
                .data(goodsVoList)
                .build();

    }

    private void fillMerchantAvatar(GoodsVo goodsVo) {
        if (ObjectUtil.isEmpty(goodsVo.getMerchantAvatar())) {
            return;
        }

        PrevFileVo prevFileVo = minioApiService.prevFile(
                PrevFileDTO.builder().filePath(goodsVo.getMerchantAvatar()).build()
        );

        if (ObjectUtil.isNotEmpty(prevFileVo)) {
            goodsVo.setMerchantAvatar(prevFileVo.getFileUrl());
        }
    }


    private MerchantVo fetchMerchant(Long merchantId) {
        if (ObjectUtil.isEmpty(merchantId)) return null;
        return this.merchantApiService.queryMerchant(
                MerchantQueryDTO.builder().id(merchantId).build()
        );
    }

    private AccountVo fetchAccount(Long accountId) {
        if (ObjectUtil.isEmpty(accountId) || CommonConst.LZERO.equals(accountId)) return null;
        return this.accountApiService.queryAccount(
                AccountQueryDTO.builder().id(accountId).build()
        );
    }

    public GoodsVo saveOrUpdate(GoodsSaveOrUpdateDTO goodsSaveOrUpdateDTO) {
        if (ObjectUtil.isEmpty(goodsSaveOrUpdateDTO.getId())) {
            GoodsDO goodsDO = this.goodsConverter.toGoodsDO(goodsSaveOrUpdateDTO);
            goodsDO.setGoodsStatus(GoodsStatusEnum.WAIT_WORING.getStatus());
            goodsDO.setAccountId(ObjectUtil.isEmpty(goodsDO.getAccountId()) ? CommonConst.LZERO : goodsDO.getAccountId());
            goodsDO.setAccountName(ObjectUtil.isEmpty(goodsDO.getAccountName()) ? "" : goodsDO.getAccountName());

            MerchantVo merchantVo = fetchMerchant(goodsDO.getMerchantId());
            if (merchantVo != null) {
                goodsDO.setMerchantName(merchantVo.getName());
                goodsDO.setMerchantAvatar(merchantVo.getAvatar());
            }

            AccountVo accountVo = fetchAccount(goodsDO.getAccountId());
            if (accountVo != null) {
                goodsDO.setAccountName(accountVo.getName());
            }

            if (this.goodsMapper.insert(goodsDO) <= 0) {
                throw new BusinessException("保存商品信息失败.");
            }

            return queryGoods(GoodsQueryDTO.builder().id(goodsDO.getId()).build());
        }

        // 更新逻辑
        MerchantVo merchantVo = fetchMerchant(goodsSaveOrUpdateDTO.getMerchantId());
        if (merchantVo != null) {
            goodsSaveOrUpdateDTO.setMerchantName(merchantVo.getName());
            goodsSaveOrUpdateDTO.setMerchantAvatar(merchantVo.getAvatar());
        }

        boolean rs = ChainWrappers.lambdaUpdateChain(this.goodsMapper)
                .set(ObjectUtil.isNotEmpty(goodsSaveOrUpdateDTO.getMerchantId()), GoodsDO::getMerchantId, goodsSaveOrUpdateDTO.getMerchantId())
                .set(ObjectUtil.isNotEmpty(goodsSaveOrUpdateDTO.getMerchantName()), GoodsDO::getMerchantName, goodsSaveOrUpdateDTO.getMerchantName())
                .set(ObjectUtil.isNotEmpty(goodsSaveOrUpdateDTO.getMerchantAvatar()), GoodsDO::getMerchantAvatar, goodsSaveOrUpdateDTO.getMerchantAvatar())
                .set(ObjectUtil.isNotEmpty(goodsSaveOrUpdateDTO.getAmount()), GoodsDO::getAmount, goodsSaveOrUpdateDTO.getAmount())
                .set(ObjectUtil.isNotEmpty(goodsSaveOrUpdateDTO.getRate()), GoodsDO::getRate, goodsSaveOrUpdateDTO.getRate())
                .set(ObjectUtil.isNotEmpty(goodsSaveOrUpdateDTO.getFreeAmount()), GoodsDO::getFreeAmount, goodsSaveOrUpdateDTO.getFreeAmount())
                .set(ObjectUtil.isNotEmpty(goodsSaveOrUpdateDTO.getGoodsStatus()), GoodsDO::getGoodsStatus, goodsSaveOrUpdateDTO.getGoodsStatus())
                .set(ObjectUtil.isNotEmpty(goodsSaveOrUpdateDTO.getAccountId()), GoodsDO::getAccountId, goodsSaveOrUpdateDTO.getAccountId())
                .set(ObjectUtil.isNotEmpty(goodsSaveOrUpdateDTO.getAccountName()), GoodsDO::getAccountName, goodsSaveOrUpdateDTO.getAccountName())
                .eq(BaseDO::getId, goodsSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!rs) {
            throw new BusinessException("更新商品信息失败.");
        }

        return queryGoods(GoodsQueryDTO.builder().id(goodsSaveOrUpdateDTO.getId()).build());

    }





    public Boolean batchCreate(GoodsBatchSaveDTO goodsBatchSaveDTO) {
        /* 191 */
        ParamsetQueryDTO paramsetQueryDTO = ParamsetQueryDTO.builder().build();
        /* 192 */
        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(paramsetQueryDTO);
        /* 193 */
        if (ObjectUtil.isEmpty(paramsetVo)) {
            /* 194 */
            throw new BusinessException("全局配置不存在.");

        }
        /* 196 */
        for (int i = 0; i < goodsBatchSaveDTO.getCreateCount().intValue(); i++) {
            /* 197 */
            BigDecimal amount = new BigDecimal(RandomUtil.randomInt(goodsBatchSaveDTO.getMinAmount().intValue(), goodsBatchSaveDTO.getMaxAmount().intValue()));










            /* 208 */
            GoodsSaveOrUpdateDTO goodsSaveOrUpdateDTO = GoodsSaveOrUpdateDTO.builder().merchantId(goodsBatchSaveDTO.getMerchantId()).merchantName(goodsBatchSaveDTO.getMerchantName()).merchantAvatar(goodsBatchSaveDTO.getMerchantAvatar()).amount(amount).rate(BigDecimal.ZERO).freeAmount(BigDecimal.ZERO).goodsStatus(GoodsStatusEnum.WAIT_WORING.getStatus()).accountId(goodsBatchSaveDTO.getAccountId()).accountName(goodsBatchSaveDTO.getAccountName()).build();
            /* 209 */
            saveOrUpdate(goodsSaveOrUpdateDTO);

        }
        /* 211 */
        return Boolean.TRUE;

    }



    public Boolean delete(GoodsDeleteDTO goodsDeleteDTO) {
        return ChainWrappers.lambdaUpdateChain(goodsMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(ObjectUtil.isNotEmpty(goodsDeleteDTO.getId()), BaseDO::getId, goodsDeleteDTO.getId())
                .in(ObjectUtil.isNotEmpty(goodsDeleteDTO.getIdList()), BaseDO::getId, goodsDeleteDTO.getIdList())
                .update();
    }



    public Integer queryGoodsCount(GoodsQueryCountDTO goodsQueryCountDTO) {

        return ChainWrappers.lambdaQueryChain(goodsMapper)
                .eq(ObjectUtil.isNotEmpty(goodsQueryCountDTO.getMerchantId()), GoodsDO::getMerchantId, goodsQueryCountDTO.getMerchantId())
                .eq(ObjectUtil.isNotEmpty(goodsQueryCountDTO.getGoodsStatus()), GoodsDO::getGoodsStatus, goodsQueryCountDTO.getGoodsStatus())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .count();
    }



    public List<GoodsVo> groupRandGoodsList(GoodsQueryPageDTO goodsQueryPageDTO) {
        /* 232 */
        List<GoodsVo> resultList = this.goodsMapper.groupRandGoodsList(goodsQueryPageDTO);
        /* 233 */
        if (ObjectUtil.isNotEmpty(resultList)) {
            /* 234 */
            resultList.forEach(goodsVo -> {

                if (ObjectUtil.isNotEmpty(goodsVo.getMerchantAvatar())) {

                    PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(goodsVo.getMerchantAvatar()).build());


                    if (ObjectUtil.isNotEmpty(prevFileVo)) {

                        goodsVo.setMerchantAvatar(prevFileVo.getFileUrl());

                    }

                }

            });

        }
        /* 244 */
        return resultList;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/goods/impl/GoodsApiServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */