package com.porn.service.notice.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.porn.client.common.enums.DelFlagEnum;
import com.porn.client.notice.api.NoticeAccountApiService;
import com.porn.client.notice.dto.NoticeAccountBatchSaveDTO;
import com.porn.client.notice.dto.NoticeAccountQueryDTO;
import com.porn.client.notice.dto.NoticeAccountSaveOrUpdateDTO;
import com.porn.client.notice.vo.NoticeAccountVo;
import com.porn.service.common.entity.BaseDO;
import com.porn.service.notice.converter.NoticeAccountConverter;
import com.porn.service.notice.dao.entity.NoticeAccountDO;
import com.porn.service.notice.dao.mapper.NoticeAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service

@Transactional(rollbackFor = {Exception.class})
public class NoticeAccountApiServiceImpl implements NoticeAccountApiService {
    private static final Logger log = LoggerFactory.getLogger(NoticeAccountApiServiceImpl.class);


    @Autowired
    private NoticeAccountConverter noticeAccountConverter;


    @Autowired
    private NoticeAccountMapper noticeAccountMapper;


    public NoticeAccountVo queryNoticeAccount(NoticeAccountQueryDTO noticeAccountQueryDTO) {

        List<NoticeAccountVo> noticeAccountVoList = queryNoticeAccountList(noticeAccountQueryDTO);

        return ObjectUtil.isEmpty(noticeAccountVoList) ? null : noticeAccountVoList.get(0);

    }


    public List<NoticeAccountVo> queryNoticeAccountList(NoticeAccountQueryDTO noticeAccountQueryDTO) {

        List<NoticeAccountDO> noticeAccountList = ChainWrappers.lambdaQueryChain(noticeAccountMapper)
                .eq(ObjectUtil.isNotEmpty(noticeAccountQueryDTO.getAccountId()), NoticeAccountDO::getAccountId, noticeAccountQueryDTO.getAccountId())
                .eq(ObjectUtil.isNotEmpty(noticeAccountQueryDTO.getNoticeId()), NoticeAccountDO::getNoticeId, noticeAccountQueryDTO.getNoticeId())
                .in(ObjectUtil.isNotEmpty(noticeAccountQueryDTO.getNoticeIdList()), NoticeAccountDO::getNoticeId, noticeAccountQueryDTO.getNoticeIdList())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();

        if (ObjectUtil.isEmpty(noticeAccountList)) {

            return Collections.emptyList();

        }

        List<NoticeAccountVo> noticeAccountVoList = this.noticeAccountConverter.toNoticeAccountVoList(noticeAccountList);

        return noticeAccountVoList;

    }


    public Boolean batchSave(NoticeAccountBatchSaveDTO noticeAccountBatchSaveDTO) {

        if (ObjectUtil.isEmpty(noticeAccountBatchSaveDTO) ||
                ObjectUtil.isEmpty(noticeAccountBatchSaveDTO.getAccountId()) ||
                ObjectUtil.isEmpty(noticeAccountBatchSaveDTO.getNoticeIdList())) {

            return Boolean.FALSE;

        }

        for (Long noticeId : noticeAccountBatchSaveDTO.getNoticeIdList()) {


            NoticeAccountSaveOrUpdateDTO noticeAccountSaveOrUpdateDTO = NoticeAccountSaveOrUpdateDTO.builder().accountId(noticeAccountBatchSaveDTO.getAccountId()).noticeId(noticeId).build();

            saveOrUpdate(noticeAccountSaveOrUpdateDTO);

        }

        return Boolean.TRUE;

    }


    public Boolean saveOrUpdate(NoticeAccountSaveOrUpdateDTO noticeAccountSaveOrUpdateDTO) {

        if (ObjectUtil.isEmpty(noticeAccountSaveOrUpdateDTO.getId())) {

            NoticeAccountDO noticeAccountDO = this.noticeAccountConverter.toNoticeAccountDO(noticeAccountSaveOrUpdateDTO);

            return Boolean.valueOf((this.noticeAccountMapper.insert(noticeAccountDO) > 0));

        }

        return ChainWrappers.lambdaUpdateChain(noticeAccountMapper)
                .set(ObjectUtil.isNotEmpty(noticeAccountSaveOrUpdateDTO.getAccountId()), NoticeAccountDO::getAccountId, noticeAccountSaveOrUpdateDTO.getAccountId())
                .set(ObjectUtil.isNotEmpty(noticeAccountSaveOrUpdateDTO.getNoticeId()), NoticeAccountDO::getNoticeId, noticeAccountSaveOrUpdateDTO.getNoticeId())
                .eq(BaseDO::getId, noticeAccountSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .update();

    }

}
