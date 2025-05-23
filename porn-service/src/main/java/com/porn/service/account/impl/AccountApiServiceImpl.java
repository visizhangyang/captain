package com.porn.service.account.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.*;
import com.porn.client.account.enums.*;
import com.porn.client.account.vo.AccountStatisticsVo;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.common.enums.MailTypeEnum;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.common.vo.BaseVo;
import com.porn.client.common.vo.PageVo;
import com.porn.client.imglib.api.ImageLibApiService;
import com.porn.client.imglib.dto.ImageLibQueryPageDTO;
import com.porn.client.imglib.dto.ImageLibUpdateStatusDTO;
import com.porn.client.imglib.enums.ImageTypeEnum;
import com.porn.client.imglib.vo.ImageLibVo;
import com.porn.client.minio.api.MinioApiService;
import com.porn.client.minio.dto.PrevFileDTO;
import com.porn.client.minio.vo.PrevFileVo;
import com.porn.client.mobile.vo.MyTeamTreeVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.photo.enums.PhotoStatusEnum;
import com.porn.client.plan.api.PlanInsApiService;
import com.porn.client.plan.dto.PlanInsQueryDTO;
import com.porn.client.plan.dto.PlanInsSaveOrUpdateDTO;
import com.porn.client.plan.enums.PlanInsStatusEnum;
import com.porn.client.plan.vo.PlanInsVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.client.stream.dto.StreamSaveOrUpdateDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.user.api.UserApiService;
import com.porn.client.user.dto.UserQueryDTO;
import com.porn.client.user.vo.UserVo;
import com.porn.service.account.converter.AccountConverter;
import com.porn.service.account.dao.entity.AccountDO;
import com.porn.service.account.dao.mapper.AccountMapper;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.common.utils.OptionalConditionHelper;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.dingdingmsg.dto.ProxyMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = {Exception.class})
public class AccountApiServiceImpl implements AccountApiService {
    private static final Logger log = LoggerFactory.getLogger(AccountApiServiceImpl.class);
    private static final Map<Integer, SFunction<AccountDO, ?>> FIELD_MAPPING = new HashMap<>();

    static {
        FIELD_MAPPING.put(0, AccountDO::getStatus);
        FIELD_MAPPING.put(1, AccountDO::getWorkStatus);
        FIELD_MAPPING.put(2, AccountDO::getWithdrawStatus);
        FIELD_MAPPING.put(3, AccountDO::getTransferStatus);
        FIELD_MAPPING.put(4, AccountDO::getWorkAutoAudit);
        FIELD_MAPPING.put(5, AccountDO::getKeynoteFollow);
        FIELD_MAPPING.put(6, AccountDO::getAutoWork);
        FIELD_MAPPING.put(7, AccountDO::getUploadStatus);
        FIELD_MAPPING.put(8, AccountDO::getPlanEnableStatus);
        FIELD_MAPPING.put(9, AccountDO::getRewardEnableStatus);
    }

    @Autowired
    private AccountConverter accountConverter;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private MinioApiService minioApiService;
    @Autowired
    private StreamApiService streamApiService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ImageLibApiService imageLibApiService;
    @Lazy
    @Autowired
    private OrderApiService orderApiService;
    @Autowired
    private ParamsetApiService paramsetApiService;
    @Autowired
    private UserApiService userApiService;
    @Autowired
    private PlanInsApiService planInsApiService;
    @Lazy
    @Autowired
    private DingdingMsgSender dingdingMsgSender;
    private List<AccountLevelEnum> accountLevelEnumList = Arrays.asList(AccountLevelEnum.values());

    public AccountVo queryAccount(AccountQueryDTO accountQueryDTO) {
        List<AccountVo> accountVoList = queryAccountList(accountQueryDTO);
        return ObjectUtil.isEmpty(accountVoList) ? null : accountVoList.get(0);
    }

    public List<AccountVo> queryAccountList(AccountQueryDTO accountQueryDTO) {
//      List<AccountDO> accountList = ((LambdaQueryChainWrapper)((LambdaQueryChainWrapper)((LambdaQueryChainWrapper)((LambdaQueryChainWrapper)((LambdaQueryChainWrapper)((LambdaQueryChainWrapper)((LambdaQueryChainWrapper)((LambdaQueryChainWrapper)((LambdaQueryChainWrapper)((LambdaQueryChainWrapper)((LambdaQueryChainWrapper)((LambdaQueryChainWrapper)((LambdaQueryChainWrapper)((LambdaQueryChainWrapper)((LambdaQueryChainWrapper)((LambdaQueryChainWrapper)ChainWrappers.lambdaQueryChain((BaseMapper)this.accountMapper).eq(ObjectUtil.isNotEmpty(accountQueryDTO.getId()), BaseDO::getId, accountQueryDTO.getId())).eq(ObjectUtil.isNotEmpty(accountQueryDTO.getName()), AccountDO::getName, accountQueryDTO.getName())).in(ObjectUtil.isNotEmpty(accountQueryDTO.getAccountIdList()), BaseDO::getId, accountQueryDTO.getAccountIdList())).in(ObjectUtil.isNotEmpty(accountQueryDTO.getPromotionCodeList()), AccountDO::getPromotionCode, accountQueryDTO.getPromotionCodeList())).in(ObjectUtil.isNotEmpty(accountQueryDTO.getParentPromotionCodeList()), AccountDO::getParentPromotionCode, accountQueryDTO.getParentPromotionCodeList())).eq(ObjectUtil.isNotEmpty(accountQueryDTO.getAccountType()), AccountDO::getAccountType, accountQueryDTO.getAccountType())).in(ObjectUtil.isNotEmpty(accountQueryDTO.getAccountTypeList()), AccountDO::getAccountType, accountQueryDTO.getAccountTypeList())).eq(ObjectUtil.isNotEmpty(accountQueryDTO.getParentId()), AccountDO::getParentId, accountQueryDTO.getParentId())).in(ObjectUtil.isNotEmpty(accountQueryDTO.getParentIdList()), AccountDO::getParentId, accountQueryDTO.getParentIdList())).eq(ObjectUtil.isNotEmpty(accountQueryDTO.getAccountLevel()), AccountDO::getAccountLevel, accountQueryDTO.getAccountLevel())).in(ObjectUtil.isNotEmpty(accountQueryDTO.getAccountLevelList()), AccountDO::getAccountLevel, accountQueryDTO.getAccountLevelList())).eq(ObjectUtil.isNotEmpty(accountQueryDTO.getAutoWork()), AccountDO::getAutoWork, accountQueryDTO.getAutoWork())).eq(ObjectUtil.isNotEmpty(accountQueryDTO.getDeviceId()), AccountDO::getDeviceId, accountQueryDTO.getDeviceId())).eq(ObjectUtil.isNotEmpty(accountQueryDTO.getPhotoStatus()), AccountDO::getPhotoStatus, accountQueryDTO.getPhotoStatus())).eq(ObjectUtil.isNotEmpty(accountQueryDTO.getUploadStatus()), AccountDO::getUploadStatus, accountQueryDTO.getUploadStatus())).eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())).list();
        LambdaQueryChainWrapper<AccountDO> query = ChainWrappers.lambdaQueryChain(accountMapper);

        OptionalConditionHelper.addEq(query, accountQueryDTO.getId(), BaseDO::getId);
        OptionalConditionHelper.addEq(query, accountQueryDTO.getName(), AccountDO::getName);
        OptionalConditionHelper.addIn(query, accountQueryDTO.getAccountIdList(), BaseDO::getId);
        OptionalConditionHelper.addIn(query, accountQueryDTO.getPromotionCodeList(), AccountDO::getPromotionCode);
        OptionalConditionHelper.addIn(query, accountQueryDTO.getParentPromotionCodeList(), AccountDO::getParentPromotionCode);
        OptionalConditionHelper.addEq(query, accountQueryDTO.getAccountType(), AccountDO::getAccountType);
        OptionalConditionHelper.addIn(query, accountQueryDTO.getAccountTypeList(), AccountDO::getAccountType);
        OptionalConditionHelper.addEq(query, accountQueryDTO.getParentId(), AccountDO::getParentId);
        OptionalConditionHelper.addIn(query, accountQueryDTO.getParentIdList(), AccountDO::getParentId);
        OptionalConditionHelper.addEq(query, accountQueryDTO.getAccountLevel(), AccountDO::getAccountLevel);
        OptionalConditionHelper.addIn(query, accountQueryDTO.getAccountLevelList(), AccountDO::getAccountLevel);
        OptionalConditionHelper.addEq(query, accountQueryDTO.getAutoWork(), AccountDO::getAutoWork);
        OptionalConditionHelper.addEq(query, accountQueryDTO.getDeviceId(), AccountDO::getDeviceId);
        OptionalConditionHelper.addEq(query, accountQueryDTO.getPhotoStatus(), AccountDO::getPhotoStatus);
        OptionalConditionHelper.addEq(query, accountQueryDTO.getUploadStatus(), AccountDO::getUploadStatus);

// 固定条件
        query.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());
        List<AccountDO> accountList = query.list();

        if (ObjectUtil.isEmpty(accountList))
            return Collections.emptyList();

        List<AccountVo> accountVoList = this.accountConverter.toAccountVoList(accountList);
        if (ObjectUtil.isNotEmpty(accountVoList))
            accountVoList.forEach(accountVo -> {
                if (ObjectUtil.isNotEmpty(accountVo.getAvatar())) {
                    PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(accountVo.getAvatar()).build());
                    if (ObjectUtil.isNotEmpty(prevFileVo))
                        accountVo.setAvatarUrl(prevFileVo.getFileUrl());
                }
            });
        return accountVoList;
    }

    public PageVo<AccountVo> queryPage(AccountQueryPageDTO accountQueryPageDTO) {
        Page page = new Page(accountQueryPageDTO.getPageStart().intValue(), accountQueryPageDTO.getPageSize().intValue(), true);

        LambdaQueryWrapper<AccountDO> queryWrapper = new LambdaQueryWrapper<>();

        OptionalConditionHelper.addLike(queryWrapper, accountQueryPageDTO.getLkName(), AccountDO::getName);
        OptionalConditionHelper.addLike(queryWrapper, accountQueryPageDTO.getLkPromotionCode(), AccountDO::getPromotionCode);
        OptionalConditionHelper.addLike(queryWrapper, accountQueryPageDTO.getLkParentPromotionCode(), AccountDO::getParentPromotionCode);
        OptionalConditionHelper.addLike(queryWrapper, accountQueryPageDTO.getLkRemark(), AccountDO::getRemark);
        OptionalConditionHelper.addEq(queryWrapper, accountQueryPageDTO.getName(), AccountDO::getName);
        OptionalConditionHelper.addEq(queryWrapper, accountQueryPageDTO.getStatus(), AccountDO::getStatus);
        OptionalConditionHelper.addEq(queryWrapper, accountQueryPageDTO.getId(), BaseDO::getId);
        OptionalConditionHelper.addIn(queryWrapper, accountQueryPageDTO.getAccountIdList(), BaseDO::getId);
        OptionalConditionHelper.addEq(queryWrapper, accountQueryPageDTO.getAccountLevel(), AccountDO::getAccountLevel);
        OptionalConditionHelper.addEq(queryWrapper, accountQueryPageDTO.getAccountType(), AccountDO::getAccountType);
        OptionalConditionHelper.addIn(queryWrapper, accountQueryPageDTO.getAccountTypeList(), AccountDO::getAccountType);
        OptionalConditionHelper.addIn(queryWrapper, accountQueryPageDTO.getAccountLevelList(), AccountDO::getAccountLevel);
        OptionalConditionHelper.addEq(queryWrapper, accountQueryPageDTO.getAutoWork(), AccountDO::getAutoWork);
        OptionalConditionHelper.addEq(queryWrapper, accountQueryPageDTO.getDeviceId(), AccountDO::getDeviceId);
        OptionalConditionHelper.addLike(queryWrapper, accountQueryPageDTO.getLkDeviceId(), AccountDO::getDeviceId);
        OptionalConditionHelper.addEq(queryWrapper, accountQueryPageDTO.getPhotoStatus(), AccountDO::getPhotoStatus);
        OptionalConditionHelper.addEq(queryWrapper, accountQueryPageDTO.getUploadStatus(), AccountDO::getUploadStatus);

// 时间范围
        OptionalConditionHelper.addGe(queryWrapper, accountQueryPageDTO.getCreateTimeStart(), BaseDO::getCreateTime);
        OptionalConditionHelper.addLe(queryWrapper, accountQueryPageDTO.getCreateTimeEnd(), BaseDO::getCreateTime);

// 固定条件
        queryWrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());

// 排序
        queryWrapper.orderByDesc(BaseDO::getCreateTime);

        IPage<AccountDO> accountPage = this.accountMapper.selectPage((IPage) page, (Wrapper) queryWrapper);
        List<AccountVo> accountVoList = this.accountConverter.toAccountVoList(accountPage.getRecords());
        if (ObjectUtil.isNotEmpty(accountVoList)) {
            List<Long> parentIdList = (List<Long>) accountVoList.stream().filter(acc -> ObjectUtil.isNotEmpty(acc.getParentId())).map(AccountVo::getParentId).distinct().collect(Collectors.toList());
            Map<Long, AccountVo> parentAccountVoMap = MapUtil.newHashMap();
            if (ObjectUtil.isNotEmpty(parentIdList)) {
                List<AccountVo> parentAccountVoList = queryAccountList(AccountQueryDTO.builder().accountIdList(parentIdList).build());
                parentAccountVoMap.putAll((Map<? extends Long, ? extends AccountVo>) parentAccountVoList.stream().collect(Collectors.toMap(BaseVo::getId, Function.identity(), (a, b) -> a)));
            }
            List<Long> idList = (List<Long>) accountVoList.stream().map(BaseVo::getId).distinct().collect(Collectors.toList());
            Map<Long, List<AccountVo>> accountVoMap = MapUtil.newHashMap();
            if (ObjectUtil.isNotEmpty(idList)) {
                List<AccountVo> childAccountVoList = queryAccountList(AccountQueryDTO.builder().parentIdList(idList).build());
                accountVoMap.putAll((Map<? extends Long, ? extends List<AccountVo>>) childAccountVoList.stream().filter(acc -> ObjectUtil.isNotEmpty(acc.getParentId())).collect(Collectors.groupingBy(AccountVo::getParentId)));
            }
            PlanInsQueryDTO planInsQueryDTO = PlanInsQueryDTO.builder().accountIdList(idList).status(PlanInsStatusEnum.PROGRESSING.getStatus()).build();
            List<PlanInsVo> planInsVoList = this.planInsApiService.queryPlanInsList(planInsQueryDTO);
            Map<Long, PlanInsVo> planInsVoMap = ObjectUtil.isEmpty(planInsVoList) ? MapUtil.newHashMap() : (Map<Long, PlanInsVo>) planInsVoList.stream().collect(Collectors.toMap(PlanInsVo::getAccountId, Function.identity(), (x, y) -> x));
            accountVoList.forEach(accountVo -> {
                if (ObjectUtil.isNotEmpty(accountVo.getAvatar())) {
                    PrevFileVo prevFileVo = this.minioApiService.prevFile(PrevFileDTO.builder().filePath(accountVo.getAvatar()).build());
                    if (ObjectUtil.isNotEmpty(prevFileVo))
                        accountVo.setAvatarUrl(prevFileVo.getFileUrl());
                }
                if (ObjectUtil.isNotEmpty(accountVo.getParentId())) {
                    AccountVo parentAccountVo = (AccountVo) parentAccountVoMap.get(accountVo.getParentId());
                    if (ObjectUtil.isNotEmpty(parentAccountVo))
                        accountVo.setParentAccount(parentAccountVo.getName());
                }
                List<AccountVo> childLs = (List<AccountVo>) accountVoMap.get(accountVo.getId());
                accountVo.setDirectPromotionCount(Integer.valueOf(ObjectUtil.isEmpty(childLs) ? 0 : childLs.size()));
                StreamQueryDTO workStreamQueryDTO = StreamQueryDTO.builder().accountId(accountVo.getId()).typeList(Arrays.asList(new Integer[]{StreamTypeEnum.WORKING_ADD.getType()})).flag(StreamTypeEnum.WORKING_ADD.getFlag()).build();
                BigDecimal workIncome = this.streamApiService.statisticsTotalProxyProfit(workStreamQueryDTO);
                accountVo.setWorkIncome(ObjectUtil.isEmpty(workIncome) ? BigDecimal.ZERO : workIncome);
                StreamQueryDTO proxyStreamQueryDTO = StreamQueryDTO.builder().accountId(accountVo.getId()).typeList(Arrays.asList(new Integer[]{StreamTypeEnum.PROXY_1.getType(), StreamTypeEnum.PROXY_2.getType(), StreamTypeEnum.PROXY_3.getType()})).flag(StreamTypeEnum.PROXY_1.getFlag()).build();
                BigDecimal proxyIncome = this.streamApiService.statisticsTotalProxyProfit(proxyStreamQueryDTO);
                accountVo.setProxyIncome(ObjectUtil.isEmpty(proxyIncome) ? BigDecimal.ZERO : proxyIncome);
                StreamQueryDTO rechargeStreamQueryDTO = StreamQueryDTO.builder().accountId(accountVo.getId()).typeList(Arrays.asList(new Integer[]{StreamTypeEnum.RECHARGE.getType()})).flag(StreamTypeEnum.WORKING_ADD.getFlag()).build();
                BigDecimal rechargeIncome = this.streamApiService.statisticsTotalProxyProfit(rechargeStreamQueryDTO);
                accountVo.setRechargeIncome(ObjectUtil.isEmpty(rechargeIncome) ? BigDecimal.ZERO : rechargeIncome);
                StreamQueryDTO transferStreamQueryDTO = StreamQueryDTO.builder().accountId(accountVo.getId()).typeList(Arrays.asList(new Integer[]{StreamTypeEnum.TRANSFER_DST.getType()})).flag(StreamTypeEnum.WORKING_ADD.getFlag()).build();
                BigDecimal transferIncome = this.streamApiService.statisticsTotalProxyProfit(transferStreamQueryDTO);
                accountVo.setTransferIncome(ObjectUtil.isEmpty(transferIncome) ? BigDecimal.ZERO : transferIncome);
                StreamQueryDTO otherStreamQueryDTO = StreamQueryDTO.builder().accountId(accountVo.getId()).typeList(Arrays.asList(new Integer[]{StreamTypeEnum.REDENVELOPE.getType(), StreamTypeEnum.TREASURE.getType()})).flag(StreamTypeEnum.WORKING_ADD.getFlag()).build();
                BigDecimal otherIncome = this.streamApiService.statisticsTotalProxyProfit(otherStreamQueryDTO);
                accountVo.setOtherIncome(ObjectUtil.isEmpty(otherIncome) ? BigDecimal.ZERO : otherIncome);
                StreamQueryDTO rewardStreamQueryDTO = StreamQueryDTO.builder().accountId(accountVo.getId()).typeList(Arrays.asList(new Integer[]{StreamTypeEnum.LOTTERY.getType()})).flag(StreamTypeEnum.WORKING_ADD.getFlag()).build();
                BigDecimal rewardIncome = this.streamApiService.statisticsTotalProxyProfit(rewardStreamQueryDTO);
                accountVo.setRewardIncome(ObjectUtil.isEmpty(rewardIncome) ? BigDecimal.ZERO : rewardIncome);
                StreamQueryDTO totalStreamQueryDTO = StreamQueryDTO.builder().accountId(accountVo.getId()).flag(StreamTypeEnum.WORKING_ADD.getFlag()).build();
                BigDecimal totalIncome = this.streamApiService.statisticsTotalProxyProfit(totalStreamQueryDTO);
                accountVo.setTotalIncome(ObjectUtil.isEmpty(totalIncome) ? BigDecimal.ZERO : totalIncome);
                PlanInsVo planInsVo = (PlanInsVo) planInsVoMap.get(accountVo.getId());
                accountVo.setPlanStatus(ObjectUtil.isEmpty(planInsVo) ? CommonConst.IZERO : CommonConst.IONE);
            });
        }
        return PageVo.<AccountVo>builder()
                .pageStart(accountQueryPageDTO.getPageStart())
                .pageSize(accountQueryPageDTO.getPageSize())
                .total(Long.valueOf(accountPage.getTotal()))
                .data(accountVoList)
                .build();
    }

    public PageVo<AccountVo> queryProxyPage(ProxyAccountQueryPageDTO proxyAccountQueryPageDTO) {
        AccountQueryProxyTeamsDTO accountQueryProxyTeamsDTO = AccountQueryProxyTeamsDTO.builder().mngUserId(proxyAccountQueryPageDTO.getCurrentUserId()).build();
        List<Long> accountIdList = queryProxyTeams(accountQueryProxyTeamsDTO);
        if (ObjectUtil.isEmpty(accountIdList))
            return PageVo.<AccountVo>builder()
                    .pageStart(proxyAccountQueryPageDTO.getPageStart())
                    .pageSize(proxyAccountQueryPageDTO.getPageSize())
                    .total(CommonConst.LZERO)
                    .data(Collections.emptyList())
                    .build();
        AccountQueryPageDTO accountQueryPageDTO = this.accountConverter.toAccountQueryPageDTO(proxyAccountQueryPageDTO);
        accountQueryPageDTO.setAccountIdList(accountIdList);
        return queryPage(accountQueryPageDTO);
    }

    public Boolean enableOrDisable(AccountEnableOrDisableDTO dto) {
        SFunction<AccountDO, ?> field = FIELD_MAPPING.get(dto.getType());
        if (field == null) {
            throw new IllegalArgumentException("无效的type类型: " + dto.getType());
        }

        EnableStatusEnum targetStatus = EnableStatusEnum.ENABLE.equals(dto.getStatus())
                ? EnableStatusEnum.DISABLED
                : EnableStatusEnum.ENABLE;

        LambdaUpdateChainWrapper<AccountDO> wrapper = ChainWrappers
                .lambdaUpdateChain((BaseMapper<AccountDO>) this.accountMapper)
                .set(field, targetStatus.getStatus());

        // id 或 idList 判断
        if (ObjectUtil.isNotEmpty(dto.getId())) {
            wrapper.eq(BaseDO::getId, dto.getId());
        }
        if (ObjectUtil.isNotEmpty(dto.getIdList())) {
            wrapper.in(BaseDO::getId, dto.getIdList());
        }

        wrapper.eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());

        return wrapper.update();
    }

    public Boolean delete(AccountDeleteDTO accountDeleteDTO) {
        // 下线账号
        boolean offRs = offlineOthers(
                AccountOfflineDTO.builder()
                        .id(accountDeleteDTO.getId())
                        .activeToken("")
                        .build()
        );

        // 构建逻辑删除条件
        LambdaUpdateChainWrapper<AccountDO> wrapper = ChainWrappers
                .lambdaUpdateChain((BaseMapper<AccountDO>) this.accountMapper)
                .set(BaseDO::getDelFlag, DelFlagEnum.DELETED.getFlag())
                .eq(ObjectUtil.isNotEmpty(accountDeleteDTO.getId()), BaseDO::getId, accountDeleteDTO.getId())
                .in(ObjectUtil.isNotEmpty(accountDeleteDTO.getIdList()), BaseDO::getId, accountDeleteDTO.getIdList())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());

        // 执行更新并返回结果
        boolean rs = wrapper.update();

        return rs && offRs;
    }

    public Boolean resetPwd(AccountResetPwdDTO accountResetPwdDTO) {
        // 默认重置密码
        String defaultPwd = SecureUtil.md5("123456");

        // 判断字段类型并设置对应字段
        SFunction<AccountDO, ?> pwdField =
                Integer.valueOf(0).equals(accountResetPwdDTO.getResetType())
                        ? AccountDO::getLoginPwd
                        : AccountDO::getTradePwd;

        // 执行更新
        boolean result = ChainWrappers
                .lambdaUpdateChain((BaseMapper<AccountDO>) this.accountMapper)
                .set(pwdField, defaultPwd)
                .eq(BaseDO::getId, accountResetPwdDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        return result;
    }

    public AccountVo saveOrUpdate(AccountSaveOrUpdateDTO dto) {
        if (ObjectUtil.isEmpty(dto.getId())) {
            return createAccount(dto);
        } else {
            return updateAccount(dto);
        }
    }

    private AccountVo createAccount(AccountSaveOrUpdateDTO dto) {
        // 校验名称是否已存在
        boolean exists = ChainWrappers.lambdaQueryChain(accountMapper)
                .eq(AccountDO::getName, dto.getName())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .oneOpt().isPresent();

        if (exists) {
            throw new BusinessException("账号名称已存在: " + dto.getName());
        }

        AccountDO accountDO = accountConverter.toAccountDO(dto);
        setDefaultValues(accountDO, dto);

        // 绑定父推广码对应的账户ID
        if (ObjectUtil.isNotEmpty(dto.getParentPromotionCode())) {
            AccountVo parent = queryAccount(AccountQueryDTO.builder()
                    .promotionCodeList(Collections.singletonList(dto.getParentPromotionCode()))
                    .build());

            if (ObjectUtil.isNotEmpty(parent)) {
                accountDO.setParentId(parent.getId());
            }
        }

        if (accountMapper.insert(accountDO) <= 0) {
            throw new BusinessException("账户保存失败");
        }

        return queryAccount(AccountQueryDTO.builder()
                .id(accountDO.getId())
                .name(accountDO.getName())
                .build());
    }

    private AccountVo updateAccount(AccountSaveOrUpdateDTO dto) {
        // 自推广码不能等于父推广码
        if (ObjectUtil.isNotEmpty(dto.getPromotionCode()) &&
                dto.getPromotionCode().equalsIgnoreCase(dto.getParentPromotionCode())) {
            throw new BusinessException("推广码不能与父推广码相同");
        }

        boolean success = ChainWrappers.lambdaUpdateChain(accountMapper)
                .set(ObjectUtil.isNotEmpty(dto.getName()), AccountDO::getName, dto.getName())
                .set(ObjectUtil.isNotEmpty(dto.getQq()), AccountDO::getQq, dto.getQq())
                .set(ObjectUtil.isNotEmpty(dto.getWechat()), AccountDO::getWechat, dto.getWechat())
                .set(ObjectUtil.isNotEmpty(dto.getPhone()), AccountDO::getPhone, dto.getPhone())
                .set(ObjectUtil.isNotEmpty(dto.getNickName()), AccountDO::getNickName, dto.getNickName())
                .set(ObjectUtil.isNotEmpty(dto.getAvatar()), AccountDO::getAvatar, dto.getAvatar())
                .set(ObjectUtil.isNotEmpty(dto.getStatus()), AccountDO::getStatus, dto.getStatus())
                .set(ObjectUtil.isNotEmpty(dto.getPromotionCode()), AccountDO::getPromotionCode, dto.getPromotionCode())
                .set(ObjectUtil.isNotEmpty(dto.getParentPromotionCode()), AccountDO::getParentPromotionCode, dto.getParentPromotionCode())
                .set(ObjectUtil.isNotEmpty(dto.getReceiveAddress()), AccountDO::getReceiveAddress, dto.getReceiveAddress())
                .set(ObjectUtil.isNotEmpty(dto.getAccountLevel()), AccountDO::getAccountLevel, dto.getAccountLevel())
                .set(ObjectUtil.isNotEmpty(dto.getSubVisit()), AccountDO::getSubVisit, dto.getSubVisit())
                .set(ObjectUtil.isNotEmpty(dto.getRemark()), AccountDO::getRemark, dto.getRemark())
                .set(ObjectUtil.isNotEmpty(dto.getKeynoteFollow()), AccountDO::getKeynoteFollow, dto.getKeynoteFollow())
                .set(ObjectUtil.isNotEmpty(dto.getAutoWork()), AccountDO::getAutoWork, dto.getAutoWork())
                .set(ObjectUtil.isNotEmpty(dto.getDeviceId()), AccountDO::getDeviceId, dto.getDeviceId())
                .set(ObjectUtil.isNotEmpty(dto.getPhotoStatus()), AccountDO::getPhotoStatus, dto.getPhotoStatus())
                .set(ObjectUtil.isNotEmpty(dto.getUploadStatus()), AccountDO::getUploadStatus, dto.getUploadStatus())
                .eq(BaseDO::getId, dto.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!success) {
            throw new BusinessException("更新失败");
        }

        return queryAccount(AccountQueryDTO.builder()
                .id(dto.getId())
                .build());
    }

    private void setDefaultValues(AccountDO accountDO, AccountSaveOrUpdateDTO dto) {
        accountDO.setReceiveAddress(StrUtil.nullToEmpty(accountDO.getReceiveAddress()));
        accountDO.setQq(StrUtil.nullToEmpty(accountDO.getQq()));
        accountDO.setWechat(StrUtil.nullToEmpty(accountDO.getWechat()));
        accountDO.setPhone(StrUtil.nullToEmpty(accountDO.getPhone()));
        accountDO.setTotalBalance(BigDecimal.ZERO);
        accountDO.setAvailableBalance(BigDecimal.ZERO);
        accountDO.setFreezeBalance(BigDecimal.ZERO);
        accountDO.setStatus(EnableStatusEnum.ENABLE.getStatus());
        accountDO.setTradePwd(StrUtil.emptyIfNull(accountDO.getTradePwd()));
        accountDO.setPromotionCode(ObjectUtil.defaultIfEmpty(accountDO.getPromotionCode(), genPromotionCode()));
        accountDO.setParentPromotionCode(StrUtil.nullToDefault(accountDO.getParentPromotionCode(), ""));
        accountDO.setLoginPwd(SecureUtil.md5(StrUtil.emptyIfNull("123456")));
        accountDO.setAccountLevel(ObjectUtil.defaultIfNull(dto.getAccountLevel(), AccountLevelEnum.NORMAL.getLevel()));
        accountDO.setWorkStatus(EnableStatusEnum.ENABLE.getStatus());
        accountDO.setWithdrawStatus(EnableStatusEnum.ENABLE.getStatus());
        accountDO.setTransferStatus(EnableStatusEnum.ENABLE.getStatus());
        accountDO.setAccountType(dto.getAccountType());
        accountDO.setWorkAutoAudit(EnableStatusEnum.DISABLED.getStatus());
        accountDO.setSubVisit(EnableStatusEnum.DISABLED.getStatus());
        accountDO.setRemark(StrUtil.nullToEmpty(accountDO.getRemark()));
        accountDO.setKeynoteFollow(ObjectUtil.defaultIfNull(accountDO.getKeynoteFollow(), EnableStatusEnum.DISABLED.getStatus()));
        accountDO.setAutoWork(ObjectUtil.defaultIfNull(accountDO.getAutoWork(), EnableStatusEnum.DISABLED.getStatus()));
        accountDO.setDeviceId(StrUtil.nullToEmpty(accountDO.getDeviceId()));
        accountDO.setPhotoStatus(ObjectUtil.defaultIfNull(accountDO.getPhotoStatus(), PhotoStatusEnum.WAIT_GRANT.getStatus()));
        accountDO.setUploadStatus(ObjectUtil.defaultIfNull(accountDO.getUploadStatus(), EnableStatusEnum.DISABLED.getStatus()));
    }

    protected String genPromotionCode() {
        return RandomUtil.randomString(8);
    }

    public boolean validatePwd(AccountValidatePwdDTO dto) {
        if (ObjectUtil.hasEmpty(dto.getId(), dto.getPwd(), dto.getType())) {
            return false;
        }

        AccountDO account = ChainWrappers.lambdaQueryChain(accountMapper)
                .eq(BaseDO::getId, dto.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .one();

        if (account == null) {
            throw new BusinessException("账户不存在或已被删除");
        }

        String encryptedInputPwd = SecureUtil.md5(dto.getPwd());

        if (AccountValidateTypeEnum.LOGIN_PWD.getType() == dto.getType()) {
            return StrUtil.equals(account.getLoginPwd(), encryptedInputPwd);
        } else if (AccountValidateTypeEnum.TRADE_PWD.getType() == dto.getType()) {
            return StrUtil.equals(account.getTradePwd(), encryptedInputPwd);
        }

        return false;
    }

    public boolean modifyPwd(AccountModifyPwdDTO dto) {
        if (ObjectUtil.hasEmpty(dto.getId(), dto.getType(), dto.getNewPwd())) {
            throw new BusinessException("参数缺失");
        }

        // 查询账户
        AccountDO account = ChainWrappers.lambdaQueryChain(accountMapper)
                .eq(BaseDO::getId, dto.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .one();

        if (account == null) {
            throw new BusinessException("账户不存在");
        }

        boolean isLoginPwd = AccountModifyPwdTypeEnum.LOGIN_PWD.getType().equals(dto.getType());
        boolean isTradePwd = AccountModifyPwdTypeEnum.TRADE_PWD.getType().equals(dto.getType());

        // 检查旧密码（如果要求校验）
        if (dto.isCheckPwd()) {
            String oldPwdHash = SecureUtil.md5(StrUtil.emptyToDefault(dto.getOldPwd(), ""));
            String currentPwd = isLoginPwd ? account.getLoginPwd() : account.getTradePwd();
            if (!StrUtil.equalsIgnoreCase(currentPwd, oldPwdHash)) {
                throw new BusinessException("旧密码错误");
            }
        }

        // 更新密码字段
        String newPwdHash = SecureUtil.md5(dto.getNewPwd());
        LambdaUpdateChainWrapper<AccountDO> updateWrapper = ChainWrappers.lambdaUpdateChain(accountMapper)
                .eq(BaseDO::getId, dto.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag());

        if (isLoginPwd) {
            updateWrapper.set(AccountDO::getLoginPwd, newPwdHash);
        } else if (isTradePwd) {
            updateWrapper.set(AccountDO::getTradePwd, newPwdHash);
        } else {
            throw new BusinessException("未知密码类型");
        }

        return updateWrapper.update();
    }

    public AccountStatisticsVo accountStatistics(AccountStatisticsDTO dto) {
        // 查询正常状态账户总数
        int totalCount = ChainWrappers.lambdaQueryChain(accountMapper)
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .count();

        // 查询当天新增账户数
        LocalDateTime now = LocalDateTime.now();
        int todayCount = ChainWrappers.lambdaQueryChain(accountMapper)
                .ge(BaseDO::getCreateTime, LocalDateTimeUtil.beginOfDay(now))
                .le(BaseDO::getCreateTime, LocalDateTimeUtil.endOfDay(now))
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .count();

        // 构造返回对象
        return AccountStatisticsVo.builder()
                .totalAccountCount((long) totalCount)
                .todayAccountCount((long) todayCount)
                .build();
    }

    public AccountVo operateAmount(AccountAmountOperateDTO dto) {
        AccountVo account = queryAccount(AccountQueryDTO.builder().id(dto.getId()).build());
        if (ObjectUtil.isEmpty(account)) {
            throw new BusinessException("账号不存在");
        }

        BigDecimal operateAmount = dto.getOperateAmount();
        BigDecimal total = account.getTotalBalance();
        BigDecimal available = account.getAvailableBalance();
        BigDecimal freeze = account.getFreezeBalance();

        BalanceTriplet before = new BalanceTriplet(total, available, freeze);
        BalanceTriplet after = calculateBalance(before, operateAmount, dto.getAmountType());

        // 更新数据库
        boolean updated = ChainWrappers.lambdaUpdateChain(accountMapper)
                .set(AccountDO::getTotalBalance, after.total)
                .set(AccountDO::getAvailableBalance, after.available)
                .set(AccountDO::getFreezeBalance, after.freeze)
                .eq(BaseDO::getId, dto.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

        if (!updated) {
            throw new BusinessException("余额操作更新失败");
        }

        // 记录资金流水
        streamApiService.saveOrUpdate(StreamSaveOrUpdateDTO.builder()
                .accountId(account.getId())
                .accountName(account.getName())
                .beforeTotalBalance(total)
                .beforeAvailableBalance(available)
                .beforeFreezeBalance(freeze)
                .afterTotalBalance(after.total)
                .afterAvailableBalance(after.available)
                .afterFreezeBalance(after.freeze)
                .bizId(dto.getBizId())
                .amount(operateAmount)
                .type(dto.getStreamTypeEnum().getType())
                .flag(dto.getStreamTypeEnum().getFlag())
                .build());

        // 查询更新后账户
        AccountVo updatedAccount = queryAccount(AccountQueryDTO.builder().id(dto.getId()).build());

        // 自动升级账户等级（可单独封装）
        upgradeAccountLevelIfNeeded(updatedAccount);

        return updatedAccount;
    }

    private void upgradeAccountLevelIfNeeded(AccountVo account) {
        ParamsetVo param = paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());
        if (ObjectUtil.isEmpty(param)) return;

        BigDecimal available = account.getAvailableBalance();
        if (available.compareTo(param.getPartnerLevelIncAmount()) >= 0) {
            saveOrUpdate(AccountSaveOrUpdateDTO.builder().id(account.getId()).accountLevel(AccountLevelEnum.PARTNER.getLevel()).build());
            account.setAccountLevel(AccountLevelEnum.PARTNER.getLevel());
        } else if (available.compareTo(param.getLargeLevelIncAmount()) >= 0) {
            saveOrUpdate(AccountSaveOrUpdateDTO.builder().id(account.getId()).accountLevel(AccountLevelEnum.LARGE.getLevel()).build());
            account.setAccountLevel(AccountLevelEnum.LARGE.getLevel());
        }
    }

    private BalanceTriplet calculateBalance(BalanceTriplet before, BigDecimal amount, Integer type) {
        switch (AmountTypeEnum.fromType(type)) {
            case SUBAVAILABLE_ADDFREEZE:
                if (before.available.compareTo(amount) < 0)
                    throw new BusinessException("可用余额不足");
                return new BalanceTriplet(before.total,
                        before.available.subtract(amount),
                        before.freeze.add(amount));

            case SUBTOTAL_SUBFREEZE:
                if (before.total.compareTo(amount) < 0 || before.freeze.compareTo(amount) < 0)
                    throw new BusinessException("余额或冻结资金不足");
                return new BalanceTriplet(before.total.subtract(amount),
                        before.available,
                        before.freeze.subtract(amount));

            case SUBTOTAL_SUBAVAILABLE:
                if (before.total.compareTo(amount) < 0 || before.available.compareTo(amount) < 0)
                    throw new BusinessException("余额或可用资金不足");
                return new BalanceTriplet(before.total.subtract(amount),
                        before.available.subtract(amount),
                        before.freeze);

            case ADDTOTAL_ADDAVAILABLE:
                return new BalanceTriplet(before.total.add(amount),
                        before.available.add(amount),
                        before.freeze);

            case ADDAVAILABLE_SUBFREEZE:
                if (before.freeze.compareTo(amount) < 0)
                    throw new BusinessException("冻结金额不足");
                return new BalanceTriplet(before.total,
                        before.available.add(amount),
                        before.freeze.subtract(amount));

            default:
                throw new BusinessException("非法资金操作类型");
        }
    }

    public Boolean freeze(AccountFreezeDTO accountFreezeDTO) {
        // 构建查询对象
        AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder()
                .id(accountFreezeDTO.getId())
                .build();

        // 查询账户信息
        AccountVo accountVo = queryAccount(accountQueryDTO);
        if (ObjectUtil.isEmpty(accountVo)) {
            throw new BusinessException("账户不存在");
        }

        BigDecimal freezeAmount = accountFreezeDTO.getAmount();
        Integer freezeType = accountFreezeDTO.getFreezeType();

        // 校验余额是否足够，构建资金操作对象
        AccountAmountOperateDTO.AccountAmountOperateDTOBuilder builder = AccountAmountOperateDTO.builder()
                .id(accountVo.getId())
                .operateAmount(freezeAmount)
                .bizId(-1L);  // 统一设置业务ID

        if (Integer.valueOf(0).equals(freezeType)) {
            // 冻结可用余额
            if (accountVo.getAvailableBalance().compareTo(freezeAmount) < 0) {
                throw new BusinessException("可用余额不足，无法冻结");
            }
            builder.amountType(AmountTypeEnum.SUBAVAILABLE_ADDFREEZE.getType())
                    .streamTypeEnum(StreamTypeEnum.SYSTEM_LOCK);
        } else {
            // 解冻冻结余额
            if (accountVo.getFreezeBalance().compareTo(freezeAmount) < 0) {
                throw new BusinessException("冻结余额不足，无法解冻");
            }
            builder.amountType(AmountTypeEnum.ADDAVAILABLE_SUBFREEZE.getType())
                    .streamTypeEnum(StreamTypeEnum.SYSTEM_UNLOCK);
        }

        // 执行金额操作
        AccountAmountOperateDTO accountAmountOperateDTO = builder.build();
        return ObjectUtil.isNotEmpty(operateAmount(accountAmountOperateDTO));
    }

    public Boolean createRobot(AccountRobotCreateDTO accountRobotCreateDTO) {
        // 1. 构建查询参数，分页获取指定数量的未启用图片
        ImageLibQueryPageDTO queryDTO = ImageLibQueryPageDTO.builder()
                .imageType(ImageTypeEnum.ACCOUNT.getType())
                .status(EnableStatusEnum.DISABLED.getStatus())
                .pageStart(1)
                .pageSize(accountRobotCreateDTO.getCreateCount())
                .build();

        PageVo<ImageLibVo> pageVo = imageLibApiService.queryPage(queryDTO);

        // 2. 校验查询结果
        if (ObjectUtil.isEmpty(pageVo) || ObjectUtil.isEmpty(pageVo.getData())) {
            return Boolean.FALSE;
        }

        List<ImageLibVo> imageList = pageVo.getData();
        List<Long> imgIdList = new ArrayList<>();

        // 3. 创建机器人账户
        for (ImageLibVo image : imageList) {
            AccountSaveOrUpdateDTO accountDTO = AccountSaveOrUpdateDTO.builder()
                    .name(genName())
                    .nickName("")
                    .avatar(image.getImgPath())
                    .loginPwd("123456")
                    .qq("")
                    .wechat("")
                    .phone("")
                    .parentPromotionCode("")
                    .totalBalance(BigDecimal.ZERO)
                    .availableBalance(BigDecimal.ZERO)
                    .freezeBalance(BigDecimal.ZERO)
                    .accountLevel(genAccountLevel().getLevel())
                    .status(EnableStatusEnum.ENABLE.getStatus())
                    .accountType(AccountTypeEnum.ROBOT.getType())
                    .build();

            saveOrUpdate(accountDTO);
            imgIdList.add(image.getId());
        }

        // 4. 更新图片状态为启用
        ImageLibUpdateStatusDTO updateStatusDTO = ImageLibUpdateStatusDTO.builder()
                .idList(imgIdList)
                .status(EnableStatusEnum.ENABLE.getStatus())
                .build();

        return imageLibApiService.updateStatus(updateStatusDTO);
    }

    public Boolean updateRegTime(AccountUpdateCreateTimeDTO dto) {
        return ChainWrappers.lambdaUpdateChain(accountMapper)
                .set(ObjectUtil.isNotEmpty(dto.getCreateTime()), BaseDO::getCreateTime, dto.getCreateTime())
                .eq(BaseDO::getId, dto.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
    }

    @Async
    public Boolean offlineOthers(AccountOfflineDTO accountOfflineDTO) {
        Set<String> keys = this.redisTemplate.keys(String.format("account:session:%s", new Object[]{"*"}));
        String activeKey = String.format("account:session:%s", new Object[]{accountOfflineDTO.getActiveToken()});
        if (ObjectUtil.isNotEmpty(keys))
            for (String key : keys) {
                try {
                    if (activeKey.equalsIgnoreCase(key))
                        continue;
                    String accountStr = (String) this.redisTemplate.opsForValue().get(key);
                    if (ObjectUtil.isEmpty(accountStr))
                        continue;
                    if (!accountStr.startsWith("{") ||
                            !accountStr.endsWith("}")) {
                        this.redisTemplate.delete(key);
                        continue;
                    }
                    AccountVo accountVo = (AccountVo) JSON.parseObject(accountStr, AccountVo.class);
                    if (ObjectUtil.isEmpty(accountVo))
                        continue;
                    if (ObjectUtil.isNotEmpty(accountOfflineDTO.getId()) && accountOfflineDTO
                            .getId().equals(accountVo.getId())) {
                        this.redisTemplate.delete(key);
                        break;
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        return Boolean.TRUE;
    }

    public Boolean updateRemark(AccountUpdateRemarkDTO dto) {
        return ChainWrappers.lambdaUpdateChain(accountMapper)
                .set(AccountDO::getRemark, StrUtil.nullToDefault(dto.getRemark(), ""))
                .eq(BaseDO::getId, dto.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
    }

    public Boolean addOrSub(AccountAddOrSubDTO dto) {
        // 校验金额
        if (ObjectUtil.isEmpty(dto.getAmount()) || dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("操作金额必须大于 0");
        }

        // 查询账户
        AccountVo account = queryAccount(AccountQueryDTO.builder().id(dto.getId()).build());
        if (ObjectUtil.isEmpty(account)) {
            throw new BusinessException("账户不存在");
        }

        BigDecimal newTotalBalance;
        BigDecimal newAvailableBalance;

        // 加款
        if (CommonConst.IZERO.equals(dto.getType())) {
            newTotalBalance = NumberUtil.add(account.getTotalBalance(), dto.getAmount());
            newAvailableBalance = NumberUtil.add(account.getAvailableBalance(), dto.getAmount());
        }
        // 减款
        else {
            if (account.getAvailableBalance().compareTo(dto.getAmount()) < 0) {
                throw new BusinessException("可用余额不足");
            }
            newTotalBalance = NumberUtil.sub(account.getTotalBalance(), dto.getAmount());
            newAvailableBalance = NumberUtil.sub(account.getAvailableBalance(), dto.getAmount());
        }

        // 执行更新
        return ChainWrappers.lambdaUpdateChain(accountMapper)
                .set(AccountDO::getTotalBalance, newTotalBalance)
                .set(AccountDO::getAvailableBalance, newAvailableBalance)
                .eq(BaseDO::getId, dto.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
    }

    public Boolean updateAccountLevel(AccountLevelDTO dto) {
        return ChainWrappers.lambdaUpdateChain(accountMapper)
                .set(AccountDO::getAccountLevel, dto.getAccountLevel())
                .eq(BaseDO::getId, dto.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
    }

    public Boolean batchUpdateAccountLevel(AccountBatchLevelDTO dto) {
        return ChainWrappers.lambdaUpdateChain(accountMapper)
                .set(AccountDO::getAccountLevel, dto.getAccountLevel())
                .eq(ObjectUtil.isNotEmpty(dto.getId()), BaseDO::getId, dto.getId())
                .in(ObjectUtil.isNotEmpty(dto.getIdList()), BaseDO::getId, dto.getIdList())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
    }

    public List<MyTeamTreeVo> proxyTree(AccountProxyDTO dto) {
        AccountVo account = queryAccount(AccountQueryDTO.builder().id(dto.getId()).build());
        if (ObjectUtil.isEmpty(account)) {
            return Collections.emptyList();
        }

        // 构建三级代理列表
        List<AccountVo> firstLevel = queryAccountList(AccountQueryDTO.builder().parentId(account.getId()).build());
        List<Long> firstIds = firstLevel.stream().map(BaseVo::getId).collect(Collectors.toList());

        List<AccountVo> secondLevel = ObjectUtil.isNotEmpty(firstLevel)
                ? queryAccountList(AccountQueryDTO.builder().parentIdList(firstIds).build())
                : Collections.emptyList();
        List<Long> secondIds = secondLevel.stream().map(BaseVo::getId).collect(Collectors.toList());

        List<AccountVo> thirdLevel = ObjectUtil.isNotEmpty(secondLevel)
                ? queryAccountList(AccountQueryDTO.builder().parentIdList(secondIds).build())
                : Collections.emptyList();

        // 构建 root 节点
        MyTeamTreeVo root = MyTeamTreeVo.builder()
                .accountId(account.getId())
                .accountName(StrUtil.blankToDefault(account.getName(), ""))
                .avatar(StrUtil.blankToDefault(account.getAvatar(), ""))
                .avatarUrl(StrUtil.blankToDefault(account.getAvatarUrl(), ""))
                .workTotalAmount(BigDecimal.ZERO)
                .freeTotalAmount(BigDecimal.ZERO)
                .registerTime(account.getCreateTime())
                .parentAccountId(CommonConst.LZERO)
                .build();

        // 整合所有子账号
        List<AccountVo> allSubs = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(firstLevel)) allSubs.addAll(firstLevel);
        if (ObjectUtil.isNotEmpty(secondLevel)) allSubs.addAll(secondLevel);
        if (ObjectUtil.isNotEmpty(thirdLevel)) allSubs.addAll(thirdLevel);

        // 组装结果
        List<MyTeamTreeVo> result = new ArrayList<>();
        result.add(root);
        result.addAll(toTeamTreeVoListNew(allSubs));
        return result;
    }

    public Boolean changeParent(AccountChangeParentDTO dto) {
        Long accountId = dto.getId();
        Long newParentId = dto.getParentId();

        // 校验必要字段
        if (ObjectUtil.isEmpty(accountId) || ObjectUtil.isEmpty(newParentId)) {
            throw new BusinessException("账号ID或父ID不能为空");
        }

        // 查询当前账号
        AccountVo account = queryAccount(AccountQueryDTO.builder().id(accountId).build());
        if (ObjectUtil.isEmpty(account)) {
            throw new BusinessException("账号不存在");
        }

        // 查询新父账号
        AccountVo newParent = queryAccount(AccountQueryDTO.builder().id(newParentId).build());
        if (ObjectUtil.isEmpty(newParent)) {
            throw new BusinessException("父账号不存在");
        }

        // 检查是否形成闭环（不能将自身或其子孙设置为父级）
        List<Long> descendantIds = new ArrayList<>();
        Stack<Long> stack = new Stack<>();
        stack.push(accountId);
        descendantIds.add(accountId);

        while (!stack.isEmpty()) {
            Long currentId = stack.pop();
            List<AccountVo> children = queryAccountList(AccountQueryDTO.builder().parentId(currentId).build());
            if (ObjectUtil.isNotEmpty(children)) {
                List<Long> childIds = children.stream().map(BaseVo::getId).collect(Collectors.toList());
                descendantIds.addAll(childIds);
                stack.addAll(childIds);
            }
        }

        if (descendantIds.contains(newParentId)) {
            throw new BusinessException(String.format("不允许将 [%s] 设置为 [%s] 的父账号，会导致循环引用",
                    newParent.getName(), account.getName()));
        }

        // 更新操作
        return ChainWrappers.lambdaUpdateChain(accountMapper)
                .set(AccountDO::getParentId, newParentId)
                .eq(BaseDO::getId, accountId)
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
    }

    public Boolean clearReceiveAddr(AccountclearReceiveAddrDTO dto) {
        return ChainWrappers.lambdaUpdateChain(accountMapper)
                .set(AccountDO::getReceiveAddress, "")
                .eq(BaseDO::getId, dto.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
    }

    public Boolean modifyDeviceId(AccountcModifyDeviceIdDTO dto) {
        return ChainWrappers.lambdaUpdateChain(accountMapper)
                .set(AccountDO::getDeviceId, dto.getDeviceId())
                .eq(BaseDO::getId, dto.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
    }

    public Boolean modifyPhotoStatus(AccountModifyPhotoStatusDTO dto) {
        return ChainWrappers.lambdaUpdateChain(accountMapper)
                .set(AccountDO::getPhotoStatus, dto.getPhotoStatus())
                .eq(BaseDO::getId, dto.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
    }

    public Boolean changeName(AccountChangeNameDTO dto) {
        AccountVo accountVo = queryAccount(AccountQueryDTO.builder().name(dto.getName()).build());
        if (ObjectUtil.isNotEmpty(accountVo))
            if (!accountVo.getId().equals(dto.getId()))
                throw new BusinessException("账户不存在");

        return ChainWrappers.lambdaUpdateChain(accountMapper)
                .set(AccountDO::getName, dto.getName())
                .eq(BaseDO::getId, dto.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();
    }

    public List<Long> queryProxyTeams(AccountQueryProxyTeamsDTO dto) {
        Long mngUserId = dto.getMngUserId();
        if (ObjectUtil.isEmpty(mngUserId)) {
            return Collections.emptyList();
        }

        // 查询用户
        UserQueryDTO userQueryDTO = UserQueryDTO.builder().id(mngUserId).build();
        UserVo userVo = userApiService.queryUser(userQueryDTO);
        if (ObjectUtil.isEmpty(userVo)) {
            throw new BusinessException("用户不存在");
        }

        // 查询当前账号
        AccountVo rootAccount = queryAccount(AccountQueryDTO.builder().name(userVo.getName()).build());
        if (ObjectUtil.isEmpty(rootAccount)) {
            throw new BusinessException("账号不存在");
        }

        // 递归查询下级账号
        List<AccountVo> allAccounts = new ArrayList<>();
        allAccounts.add(rootAccount);

        List<AccountVo> currentLevel = ListUtil.toList(rootAccount);
        for (int i = 0; i < 3; i++) {
            if (ObjectUtil.isEmpty(currentLevel)) break;
            List<Long> parentIds = currentLevel.stream().map(BaseVo::getId).collect(Collectors.toList());
            currentLevel = queryAccountList(AccountQueryDTO.builder().parentIdList(parentIds).build());
            allAccounts.addAll(currentLevel);
        }

        return allAccounts.stream()
                .map(BaseVo::getId)
                .distinct()
                .collect(Collectors.toList());
    }

    public Boolean forceStopPlan(AccountForceStopPlanDTO accountForceStopPlanDTO) {
        PlanInsQueryDTO planInsQueryDTO = PlanInsQueryDTO.builder().accountId(accountForceStopPlanDTO.getId()).status(PlanInsStatusEnum.PROGRESSING.getStatus()).build();
        PlanInsVo planInsVo = this.planInsApiService.queryPlanIns(planInsQueryDTO);
        if (ObjectUtil.isEmpty(planInsVo))
            throw new BusinessException("");
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(planInsVo.getAccountId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(planInsVo.getId()).streamTypeEnum(StreamTypeEnum.PLAN_UNLOCK).operateAmount(planInsVo.getTotalInvest()).build();
        operateAmount(accountAmountOperateDTO);
        PlanInsSaveOrUpdateDTO planInsSaveOrUpdateDTO = ((PlanInsSaveOrUpdateDTO.PlanInsSaveOrUpdateDTOBuilder) PlanInsSaveOrUpdateDTO.builder().id(planInsVo.getId())).status(PlanInsStatusEnum.COMPLETED.getStatus()).build();
        this.planInsApiService.saveOrUpdate(planInsSaveOrUpdateDTO);
        AccountVo accountVo = queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(planInsVo.getAccountId())).build());
        this.dingdingMsgSender.sendMsg(
                ProxyMsgDTO.builder()
                        .accountId(planInsVo.getAccountId())
                        .msg("+ accountVo.getName() + " + planInsVo.getTotalInvest().stripTrailingZeros().toPlainString() + "]")
                        .build());
        return Boolean.TRUE;
    }

    protected List<MyTeamTreeVo> toTeamTreeVoListNew(List<AccountVo> accountList) {
        if (ObjectUtil.isEmpty(accountList))
            return Collections.emptyList();
        return toTeamTreeVoList((List<Long>) accountList.stream().map(BaseVo::getId).collect(Collectors.toList()));
    }

    protected List<MyTeamTreeVo> toTeamTreeVoList(List<Long> accountIdList) {
        if (ObjectUtil.isEmpty(accountIdList))
            return Collections.emptyList();
        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(OrderQueryDTO.builder().accountIdList(accountIdList).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).build());
        if (null == orderVoList)
            orderVoList = ListUtil.list(false);
        List<AccountVo> accountVoList = queryAccountList(AccountQueryDTO.builder().accountIdList(accountIdList).build());
        Map<Long, AccountVo> accountVoMap = (Map<Long, AccountVo>) accountVoList.stream().collect(Collectors.toMap(BaseVo::getId, Function.identity()));
        Map<Long, List<OrderVo>> orderMap = (Map<Long, List<OrderVo>>) orderVoList.stream().collect(Collectors.groupingBy(OrderVo::getAccountId));
        List<MyTeamTreeVo> result = new ArrayList<>();
        accountVoList.forEach(accountVo -> {
            List<OrderVo> orders = (List<OrderVo>) orderMap.get(accountVo.getId());
            MyTeamTreeVo myTeamTreeVo = MyTeamTreeVo.builder().accountId(accountVo.getId()).accountName(ObjectUtil.isEmpty(accountVo) ? "" : accountVo.getName()).avatar((ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getAvatar())) ? "" : accountVo.getAvatar()).avatarUrl(
                    (ObjectUtil.isEmpty(accountVo) || ObjectUtil.isEmpty(accountVo.getAvatarUrl())) ? "" : accountVo.getAvatarUrl()).workTotalAmount(ObjectUtil.isEmpty(orders) ? BigDecimal.ZERO : orders.stream().map(OrderVo::getOrderAmount).reduce(BigDecimal.ZERO, BigDecimal::add)).freeTotalAmount(ObjectUtil.isEmpty(orders) ? BigDecimal.ZERO : orders.stream().map(OrderVo::getFreeAmount).reduce(BigDecimal.ZERO, BigDecimal::add)).registerTime(accountVo.getCreateTime()).parentAccountId(accountVo.getParentId()).build();
            result.add(myTeamTreeVo);
        });
        return result;
    }

    protected String genName() {
        MailTypeEnum mailTypeEnum = (MailTypeEnum) RandomUtil.randomEle(ListUtil.toList((Object[]) MailTypeEnum.values()));
        String prefix = "";
        switch (mailTypeEnum) {
            case QQ:
                prefix = StrUtil.join("", new Object[]{Integer.valueOf(RandomUtil.randomInt(1, 10)), RandomUtil.randomNumbers(RandomUtil.randomInt(9, 14))});
                break;
            case WY163:
            case GMAIL:
            case OUTLOOK:
                prefix = RandomUtil.randomString("abcdefghijklmnopqrstuvwxyz", RandomUtil.randomInt(6, 13));
                break;
        }
        return StrUtil.join("", new Object[]{prefix, mailTypeEnum.getDescription()});
    }

    protected AccountLevelEnum genAccountLevel() {
        return RandomUtil.randomEles(this.accountLevelEnumList, 1).get(0);
    }

    /**
     * 封装三种余额
     */
    private static class BalanceTriplet {
        final BigDecimal total;
        final BigDecimal available;
        final BigDecimal freeze;

        BalanceTriplet(BigDecimal total, BigDecimal available, BigDecimal freeze) {
            this.total = total;
            this.available = available;
            this.freeze = freeze;
        }
    }
}
