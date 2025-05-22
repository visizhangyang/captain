
package com.porn.service.merchant.impl;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.PageVo;
import com.porn.client.imglib.api.ImageLibApiService;
import com.porn.client.imglib.dto.ImageLibQueryPageDTO;
import com.porn.client.imglib.dto.ImageLibUpdateStatusDTO;
import com.porn.client.imglib.enums.ImageTypeEnum;
import com.porn.client.imglib.vo.ImageLibVo;
import com.porn.client.merchant.api.MerchantApiService;
import com.porn.client.merchant.dto.*;
import com.porn.client.merchant.enums.AuthLevelEnum;
import com.porn.client.merchant.enums.MemberLevelEnum;
import com.porn.client.merchant.enums.MerchantTypeEnum;
import com.porn.client.merchant.vo.MerchantVo;
import com.porn.client.minio.api.MinioApiService;
import com.porn.client.minio.dto.PrevFileDTO;
import com.porn.client.minio.vo.PrevFileVo;
import com.porn.client.nickname.api.NickNameApiService;
import com.porn.client.nickname.dto.NickNameQueryDTO;
import com.porn.client.nickname.dto.NickNameUpdateStatusDTO;
import com.porn.client.nickname.vo.NickNameVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.merchant.converter.MerchantConverter;
import com.porn.service.merchant.dao.entity.MerchantDO;
import com.porn.service.merchant.dao.mapper.MerchantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service

@Transactional(rollbackFor = {Exception.class})
 public class MerchantApiServiceImpl implements MerchantApiService {
    /*  56 */   private static final Logger log = LoggerFactory.getLogger(MerchantApiServiceImpl.class);



    @Autowired
     private MerchantMapper merchantMapper;


    @Autowired
     private MerchantConverter merchantConverter;


    @Autowired
     private MinioApiService minioApiService;


    @Autowired
     private ImageLibApiService imageLibApiService;


    @Autowired
     private NickNameApiService nickNameApiService;




    public MerchantVo queryMerchant(MerchantQueryDTO merchantQueryDTO) {
        /*  76 */
        List<MerchantVo> merchantVoList = queryMerchantList(merchantQueryDTO);
        /*  77 */
        return ObjectUtil.isEmpty(merchantVoList) ? null : merchantVoList.get(0);

    }









    public List<MerchantVo> queryMerchantList(MerchantQueryDTO merchantQueryDTO) {
        /*  87 */
        List<MerchantDO> merchantList = ChainWrappers.lambdaQueryChain(merchantMapper)
                .eq(ObjectUtil.isNotEmpty(merchantQueryDTO.getId()), BaseDO::getId, merchantQueryDTO.getId())
                .in(ObjectUtil.isNotEmpty(merchantQueryDTO.getMerchantIdList()), BaseDO::getId, merchantQueryDTO.getMerchantIdList())
                .eq(ObjectUtil.isNotEmpty(merchantQueryDTO.getName()), MerchantDO::getName, merchantQueryDTO.getName())
                .eq(ObjectUtil.isNotEmpty(merchantQueryDTO.getMerchantType()), MerchantDO::getMerchantType, merchantQueryDTO.getMerchantType())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();
        /*  88 */
        if (ObjectUtil.isEmpty(merchantList)) {
            /*  89 */
            return Collections.emptyList();

        }
        /*  91 */
        List<MerchantVo> merchantVoList = this.merchantConverter.toMerchantVoList(merchantList);
        /*  92 */
        if (ObjectUtil.isNotEmpty(merchantVoList)) {
            /*  93 */
            merchantVoList.forEach(merchantVo -> {

                if (ObjectUtil.isNotEmpty(merchantVo.getAvatar())) {

                    PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(merchantVo.getAvatar()).build());


                    if (ObjectUtil.isNotEmpty(prevFileVo)) {

                        merchantVo.setAvatarUrl(prevFileVo.getFileUrl());

                    }

                }

            });

        }
        /* 103 */
        return merchantVoList;

    }



    public PageVo<MerchantVo> queryPage(MerchantQueryPageDTO merchantQueryPageDTO) {
        /* 107 */
        Page page = new Page(merchantQueryPageDTO.getPageStart().intValue(), merchantQueryPageDTO.getPageSize().intValue(), true);
        /* 108 */
        LambdaQueryWrapper<MerchantDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(merchantQueryPageDTO.getName()), MerchantDO::getName, merchantQueryPageDTO.getName());
        wrapper.like(ObjectUtil.isNotEmpty(merchantQueryPageDTO.getLkName()), MerchantDO::getName, merchantQueryPageDTO.getLkName());
        wrapper.eq(ObjectUtil.isNotEmpty(merchantQueryPageDTO.getStatus()), MerchantDO::getStatus, merchantQueryPageDTO.getStatus());
        wrapper.eq(ObjectUtil.isNotEmpty(merchantQueryPageDTO.getMemberLevel()), MerchantDO::getMemberLevel, merchantQueryPageDTO.getMemberLevel());
        wrapper.eq(ObjectUtil.isNotEmpty(merchantQueryPageDTO.getAuthLevel()), MerchantDO::getAuthLevel, merchantQueryPageDTO.getAuthLevel());
        wrapper.eq(ObjectUtil.isNotEmpty(merchantQueryPageDTO.getMerchantType()), MerchantDO::getMerchantType, merchantQueryPageDTO.getMerchantType());
        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        wrapper.orderByDesc(BaseDO::getCreateTime);

        /* 109 */
        /* 118 */
        IPage<MerchantDO> merchantPage = this.merchantMapper.selectPage((IPage) page, wrapper);
        /* 119 */
        List<MerchantVo> merchantVoList = this.merchantConverter.toMerchantVoList(merchantPage.getRecords());
        /* 120 */
        if (ObjectUtil.isNotEmpty(merchantVoList)) {
            /* 121 */
            merchantVoList.forEach(merchantVo -> {

                if (ObjectUtil.isNotEmpty(merchantVo.getAvatar())) {

                    PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(merchantVo.getAvatar()).build());


                    if (ObjectUtil.isNotEmpty(prevFileVo)) {

                        merchantVo.setAvatarUrl(prevFileVo.getFileUrl());

                    }

                }

            });

        }
        /* 131 */
        return PageVo.<MerchantVo>builder()
/* 132 */.pageStart(merchantQueryPageDTO.getPageStart())
/* 133 */.pageSize(merchantQueryPageDTO.getPageSize())
/* 134 */.total(Long.valueOf(merchantPage.getTotal()))
/* 135 */.data(merchantVoList)
/* 136 */.build();

    }



    public Boolean enableOrDisable(MerchantEnableOrDisableDTO merchantEnableOrDisableDTO) {
        /* 140 */
        return ChainWrappers.lambdaUpdateChain(merchantMapper)
                .set(MerchantDO::getStatus, EnableStatusEnum.ENABLE.getStatus().equals(merchantEnableOrDisableDTO.getStatus()) ? EnableStatusEnum.DISABLED.getStatus() : EnableStatusEnum.ENABLE.getStatus())
                .eq(BaseDO::getId, merchantEnableOrDisableDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
        /* 144 */.update();
    }



    public MerchantVo saveOrUpdate(MerchantSaveOrUpdateDTO merchantSaveOrUpdateDTO) {
        /* 148 */
        if (ObjectUtil.isEmpty(merchantSaveOrUpdateDTO.getId())) {
            /* 149 */
            MerchantDO merchantDO1 = this.merchantConverter.toMerchantDO(merchantSaveOrUpdateDTO);
            /* 150 */
            merchantDO1.setAccumulateAmount(BigDecimal.ZERO);
            /* 151 */
            merchantDO1.setAccumulateCount(CommonConst.LZERO);
            /* 152 */
            merchantDO1.setMemberLevelName(MemberLevelEnum.queryByLevel(merchantDO1.getMemberLevel()));
            /* 153 */
            merchantDO1.setAuthLevelName(AuthLevelEnum.queryByLevel(merchantDO1.getAuthLevel()));
            /* 154 */
            merchantDO1.setMerchantTag(StrUtil.emptyToDefault(merchantDO1.getMerchantTag(), ""));
            /* 155 */
            if (this.merchantMapper.insert(merchantDO1) <= 0) {
                /* 156 */
                throw new BusinessException("保存商户信息失败.");

            }
            /* 158 */
            return queryMerchant(((MerchantQueryDTO.MerchantQueryDTOBuilder) MerchantQueryDTO.builder().id(merchantDO1.getId())).name(merchantDO1.getName()).build());

        }




        /* 164 */
        MerchantDO merchantDO = ChainWrappers.lambdaQueryChain(merchantMapper)
                .eq(BaseDO::getId, merchantSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .one();
        /* 165 */
        if (ObjectUtil.isEmpty(merchantDO)) {
            /* 166 */
            throw new BusinessException("商户不存在.");

        }

        /* 169 */
        if (!merchantDO.getName().equals(merchantSaveOrUpdateDTO.getName())) {




            /* 174 */
            MerchantDO dbMerchantDO = ChainWrappers.lambdaQueryChain(merchantMapper)
                    .eq(MerchantDO::getName, merchantSaveOrUpdateDTO.getName())
                    .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                    .one();
            /* 175 */
            if (ObjectUtil.isNotEmpty(dbMerchantDO))
                 {
                /* 177 */
                throw new BusinessException("商户[" + merchantSaveOrUpdateDTO.getName() + "]已存在.");

            }

        }


















        /* 198 */
        boolean rs = ChainWrappers.lambdaUpdateChain(merchantMapper)
                .set(ObjectUtil.isNotEmpty(merchantSaveOrUpdateDTO.getName()), MerchantDO::getName, merchantSaveOrUpdateDTO.getName())
                .set(ObjectUtil.isNotEmpty(merchantSaveOrUpdateDTO.getAvatar()), MerchantDO::getAvatar, merchantSaveOrUpdateDTO.getAvatar())
                .set(ObjectUtil.isNotEmpty(merchantSaveOrUpdateDTO.getRateRange()), MerchantDO::getRateRange, merchantSaveOrUpdateDTO.getRateRange())
                .set(ObjectUtil.isNotEmpty(merchantSaveOrUpdateDTO.getEnsureAmount()), MerchantDO::getEnsureAmount, merchantSaveOrUpdateDTO.getEnsureAmount())
                .set(ObjectUtil.isNotEmpty(merchantSaveOrUpdateDTO.getAreaName()), MerchantDO::getAreaName, merchantSaveOrUpdateDTO.getAreaName())
                .set(ObjectUtil.isNotEmpty(merchantSaveOrUpdateDTO.getMemberLevel()), MerchantDO::getMemberLevel, merchantSaveOrUpdateDTO.getMemberLevel())
                .set(ObjectUtil.isNotEmpty(merchantSaveOrUpdateDTO.getMemberLevel()), MerchantDO::getMemberLevelName, MemberLevelEnum.queryByLevel(merchantSaveOrUpdateDTO.getMemberLevel()))
                .set(ObjectUtil.isNotEmpty(merchantSaveOrUpdateDTO.getAuthLevel()), MerchantDO::getAuthLevel, merchantSaveOrUpdateDTO.getAuthLevel())
                .set(ObjectUtil.isNotEmpty(merchantSaveOrUpdateDTO.getAuthLevel()), MerchantDO::getAuthLevelName, AuthLevelEnum.queryByLevel(merchantSaveOrUpdateDTO.getAuthLevel()))
                .set(ObjectUtil.isNotEmpty(merchantSaveOrUpdateDTO.getMailAuth()), MerchantDO::getMailAuth, merchantSaveOrUpdateDTO.getMailAuth())
                .set(ObjectUtil.isNotEmpty(merchantSaveOrUpdateDTO.getPhoneAuth()), MerchantDO::getPhoneAuth, merchantSaveOrUpdateDTO.getPhoneAuth())
                .set(ObjectUtil.isNotEmpty(merchantSaveOrUpdateDTO.getKycAuth()), MerchantDO::getKycAuth, merchantSaveOrUpdateDTO.getKycAuth())
                .set(ObjectUtil.isNotEmpty(merchantSaveOrUpdateDTO.getAddressAuth()), MerchantDO::getAddressAuth, merchantSaveOrUpdateDTO.getAddressAuth())
                .set(ObjectUtil.isNotEmpty(merchantSaveOrUpdateDTO.getMerchantTag()), MerchantDO::getMerchantTag, merchantSaveOrUpdateDTO.getMerchantTag())
                .set(MerchantDO::getStatus, ObjectUtil.defaultIfNull(merchantSaveOrUpdateDTO.getStatus(), EnableStatusEnum.ENABLE.getStatus()))
                .eq(BaseDO::getId, merchantSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
        /* 199 */
        if (!rs) {
            /* 200 */
            throw new BusinessException("更新商户信息失败.");

        }
        /* 202 */
        return queryMerchant(((MerchantQueryDTO.MerchantQueryDTOBuilder) MerchantQueryDTO.builder().id(merchantSaveOrUpdateDTO.getId())).name(merchantSaveOrUpdateDTO.getName()).build());

    }




    public Boolean delete(MerchantDeleteDTO merchantDeleteDTO) {
        /* 207 */
        return ChainWrappers.lambdaUpdateChain(merchantMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(BaseDO::getId, merchantDeleteDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 211 */.update();

    }








    public Boolean createRobot(MerchantRobotCreateDTO merchantRobotCreateDTO) {
        /* 220 */
        ImageLibQueryPageDTO imageLibQueryPageDTO = ((ImageLibQueryPageDTO.ImageLibQueryPageDTOBuilder) ((ImageLibQueryPageDTO.ImageLibQueryPageDTOBuilder) ImageLibQueryPageDTO.builder().imageType(ImageTypeEnum.MERCHANT.getType()).status(EnableStatusEnum.DISABLED.getStatus()).pageStart(Integer.valueOf(1))).pageSize(merchantRobotCreateDTO.getCreateCount())).build();
        /* 221 */
        PageVo<ImageLibVo> pageVo = this.imageLibApiService.queryPage(imageLibQueryPageDTO);
        /* 222 */
        if (ObjectUtil.isEmpty(pageVo) ||
                /* 223 */       ObjectUtil.isEmpty(pageVo.getData())) {
            /* 224 */
            return Boolean.FALSE;

        }


        /* 228 */
        List<NickNameVo> nickNameVoList = this.nickNameApiService.queryNickNameList(NickNameQueryDTO.builder().status(EnableStatusEnum.DISABLED.getStatus()).build());
        /* 229 */
        if (ObjectUtil.isEmpty(nickNameVoList) || nickNameVoList
/* 230 */.size() < merchantRobotCreateDTO.getCreateCount().intValue()) {
            /* 231 */
            throw new BusinessException("昵称数量不足, 昵称可用数量[" + (ObjectUtil.isEmpty(nickNameVoList) ? 0 : nickNameVoList.size()) + "], 请先导入");

        }

        /* 234 */
        List<Long> nickIds = new ArrayList<>();
        /* 235 */
        List<ImageLibVo> imageLibVoList = pageVo.getData();
        /* 236 */
        List<Long> imgIdList = new ArrayList<>();
        /* 237 */
        for (int i = 0; i < imageLibVoList.size(); i++) {
            /* 238 */
            ImageLibVo imageLibVo = imageLibVoList.get(i);
            /* 239 */
            Integer memberLevel = (Integer) RandomUtil.randomEle(merchantRobotCreateDTO.getMemberLevelList(), 1);
            /* 240 */
            Integer authLevel = (Integer) RandomUtil.randomEle(merchantRobotCreateDTO.getAuthLevelList(), 1);
















            /* 257 */
            MerchantSaveOrUpdateDTO merchantSaveOrUpdateDTO = MerchantSaveOrUpdateDTO.builder().name(((NickNameVo) nickNameVoList.get(i)).getNickName()).avatar(imageLibVo.getImgPath()).ensureAmount(RandomUtil.randomBigDecimal(merchantRobotCreateDTO.getEnsureAmountMin(), merchantRobotCreateDTO.getEnsureAmountMax()).setScale(0, RoundingMode.HALF_UP)).rateRange(StrUtil.toString(Integer.valueOf(RandomUtil.randomInt(merchantRobotCreateDTO.getRateRangeMin().intValue(), merchantRobotCreateDTO.getRateRangeMax().intValue())))).areaName((String) RandomUtil.randomEle((Object[]) CITYS, CITYS.length)).memberLevel(memberLevel).memberLevelName(MemberLevelEnum.queryByLevel(memberLevel)).authLevel(authLevel).authLevelName(AuthLevelEnum.queryByLevel(authLevel)).mailAuth((Integer) RandomUtil.randomEle(merchantRobotCreateDTO.getMailAuthList(), merchantRobotCreateDTO.getMailAuthList().size())).phoneAuth((Integer) RandomUtil.randomEle(merchantRobotCreateDTO.getPhoneAuthList(), merchantRobotCreateDTO.getPhoneAuthList().size())).kycAuth((Integer) RandomUtil.randomEle(merchantRobotCreateDTO.getKycAuthList(), merchantRobotCreateDTO.getKycAuthList().size())).addressAuth((Integer) RandomUtil.randomEle(merchantRobotCreateDTO.getAddressAuthList(), merchantRobotCreateDTO.getAddressAuthList().size())).status(EnableStatusEnum.ENABLE.getStatus()).merchantType(MerchantTypeEnum.ROBOT.getType()).build();
            /* 258 */
            saveOrUpdate(merchantSaveOrUpdateDTO);
            /* 259 */
            imgIdList.add(imageLibVo.getId());
            /* 260 */
            nickIds.add(((NickNameVo) nickNameVoList.get(i)).getId());

        }




        /* 266 */
        NickNameUpdateStatusDTO nickNameUpdateStatusDTO = NickNameUpdateStatusDTO.builder().idList(nickIds).status(EnableStatusEnum.DISABLED.getStatus()).build();
        /* 267 */
        this.nickNameApiService.updateStatus(nickNameUpdateStatusDTO);





        /* 273 */
        ImageLibUpdateStatusDTO imageLibUpdateStatusDTO = ImageLibUpdateStatusDTO.builder().idList(imgIdList).status(EnableStatusEnum.ENABLE.getStatus()).build();
        /* 274 */
        return this.imageLibApiService.updateStatus(imageLibUpdateStatusDTO);

    }



    public Boolean confirmOrder(MerchantConfirmOrderDTO merchantConfirmOrderDTO) {
        /* 278 */
        MerchantVo merchantVo = queryMerchant(((MerchantQueryDTO.MerchantQueryDTOBuilder) MerchantQueryDTO.builder().id(merchantConfirmOrderDTO.getId())).build());
        /* 279 */
        if (ObjectUtil.isEmpty(merchantVo)) {
            /* 280 */
            throw new BusinessException("商户信息不存在");

        }
        /* 282 */
        return   ChainWrappers.lambdaUpdateChain(merchantMapper)
                .set(MerchantDO::getAccumulateCount, Long.valueOf(ObjectUtil.isEmpty(merchantVo.getAccumulateCount()) ? 1L : (merchantVo.getAccumulateCount().longValue() + 1L)))
                .set(MerchantDO::getAccumulateAmount, ObjectUtil.isEmpty(merchantVo.getAccumulateAmount()) ? merchantConfirmOrderDTO.getAmount() : NumberUtil.add(merchantVo.getAccumulateAmount(), merchantConfirmOrderDTO.getAmount()))
                .eq(BaseDO::getId, merchantVo.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 287 */.update();

    }






    /* 294 */   public static String[] CITYS = new String[]{"伦敦", "纽约", "香港", "新加坡", "上海", "北京", "迪拜", "巴黎", "东京", "悉尼", "洛杉矶", "多伦多", "孟买", "阿姆斯特丹", "米兰", "法兰克福", "墨西哥城", "圣保罗", "芝加哥", "吉隆坡", "马德里", "莫斯科", "雅加达", "布鲁塞尔。", "华沙", "首尔", "约翰内斯堡", "苏黎世", "墨尔本", "伊斯坦布尔", "曼谷", "斯德哥尔摩", "维也纳", "广州", "都柏林", "台北（中国台湾）", "布宜诺斯艾利斯", "旧金山", "卢森堡巿", "蒙特利尔", "慕尼黑", "德里", "圣地亚哥", "波士顿", "马尼拉", "深圳", "利雅得", "里斯本", "布拉格", "班加罗尔。", "华盛顿", "达拉斯", "圣菲波哥大", "迈阿密", "罗马", "汉堡", "休斯敦", "柏林", "成都", "杜塞尔多夫", "特拉维夫", "巴塞罗那", "布达佩斯", "多哈", "利马", "哥本哈根", "亚特兰大", "布加勒斯特", "温哥华", "布里斯班", "开罗", "贝鲁特", "奥克兰", "胡志明市", "雅典", "丹佛", "天津", "阿布扎比", "珀斯", "卡萨布兰卡", "基辅", "蒙得维的亚", "奥斯陆", "赫尔辛基", "钦奈", "河内", "南京", "费城", "开普敦", "杭州", "内罗毕", "西雅图", "麦纳麦", "卡拉奇", "里约热内卢", "重庆", "巴拿马城", "武汉", "大阪", "沈阳", "西安", "危地马拉城", "大连", "圣彼得堡", "拉各斯", "基多", "济南", "圣萨尔瓦多", "堪培拉", "乔治敦（开曼）", "马斯喀特", "底特律", "爱丁堡", "吉达", "海德拉巴", "拉合尔", "奥斯汀", "长沙", "圣荷西", "加尔各答", "夏洛特", "圣路易斯", "浦那", "安特卫普", "鹿特丹", "阿德莱德", "波尔图", "巴库", "瓜达拉哈拉", "卢布尔雅那", "青岛", "阿尔及尔", "苏州", "贝尔法斯特", "格拉斯哥", "麦德林", "科隆", "金边", "伊斯兰堡", "凤凰城", "里加", "第比利斯", "合肥", "昆明", "德班", "维尔纽斯", "哥德堡", "圣胡安", "南特", "安卡拉", "圣多明各", "弗洛茨瓦夫", "渥太华", "达卡", "马尔默", "布里斯托", "地拉那", "科伦坡", "都灵", "瓦伦西亚", "瓜亚基尔", "台中", "路易港", "阿克拉", "亚松森", "毕尔巴鄂", "马普托", "杜阿拉", "拿骚", "哈拉雷", "波兹南", "罗安达", "克利夫兰", "福州", "名古屋", "堪萨斯城", "卡托维兹", "马拉加", "克雷塔罗", "哈尔滨", "密尔沃基", "槟城", "盐湖城"};

}


