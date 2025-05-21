
package com.porn.service.notice.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
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
    /* 27 */   private static final Logger log = LoggerFactory.getLogger(NoticeAccountApiServiceImpl.class);
    
    
    
    
    @Autowired
     private NoticeAccountConverter noticeAccountConverter;
    
    
    
    @Autowired
     private NoticeAccountMapper noticeAccountMapper;

    
    
    
    
    public NoticeAccountVo queryNoticeAccount(NoticeAccountQueryDTO noticeAccountQueryDTO) {
        /* 41 */
        List<NoticeAccountVo> noticeAccountVoList = queryNoticeAccountList(noticeAccountQueryDTO);
        /* 42 */
        return ObjectUtil.isEmpty(noticeAccountVoList) ? null : noticeAccountVoList.get(0);
        
    }

    
    
    
    
    
    
    
    
    public List<NoticeAccountVo> queryNoticeAccountList(NoticeAccountQueryDTO noticeAccountQueryDTO) {
        /* 52 */
        List<NoticeAccountDO> noticeAccountList =  ChainWrappers.lambdaQueryChain(noticeAccountMapper)
                .eq(ObjectUtil.isNotEmpty(noticeAccountQueryDTO.getAccountId()), NoticeAccountDO::getAccountId, noticeAccountQueryDTO.getAccountId())
                .eq(ObjectUtil.isNotEmpty(noticeAccountQueryDTO.getNoticeId()), NoticeAccountDO::getNoticeId, noticeAccountQueryDTO.getNoticeId())
                .in(ObjectUtil.isNotEmpty(noticeAccountQueryDTO.getNoticeIdList()), NoticeAccountDO::getNoticeId, noticeAccountQueryDTO.getNoticeIdList())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
                .list();
        /* 53 */
        if (ObjectUtil.isEmpty(noticeAccountList)) {
            /* 54 */
            return Collections.emptyList();
            
        }
        /* 56 */
        List<NoticeAccountVo> noticeAccountVoList = this.noticeAccountConverter.toNoticeAccountVoList(noticeAccountList);
        /* 57 */
        return noticeAccountVoList;
        
    }

    
    
    
    public Boolean batchSave(NoticeAccountBatchSaveDTO noticeAccountBatchSaveDTO) {
        /* 62 */
        if (ObjectUtil.isEmpty(noticeAccountBatchSaveDTO) ||
                /* 63 */       ObjectUtil.isEmpty(noticeAccountBatchSaveDTO.getAccountId()) ||
                /* 64 */       ObjectUtil.isEmpty(noticeAccountBatchSaveDTO.getNoticeIdList())) {
            /* 65 */
            return Boolean.FALSE;
            
        }
        /* 67 */
        for (Long noticeId : noticeAccountBatchSaveDTO.getNoticeIdList()) {
            
            
            
            /* 71 */
            NoticeAccountSaveOrUpdateDTO noticeAccountSaveOrUpdateDTO = NoticeAccountSaveOrUpdateDTO.builder().accountId(noticeAccountBatchSaveDTO.getAccountId()).noticeId(noticeId).build();
            /* 72 */
            saveOrUpdate(noticeAccountSaveOrUpdateDTO);
            
        }
        /* 74 */
        return Boolean.TRUE;
        
    }

    
    
    
    public Boolean saveOrUpdate(NoticeAccountSaveOrUpdateDTO noticeAccountSaveOrUpdateDTO) {
        /* 79 */
        if (ObjectUtil.isEmpty(noticeAccountSaveOrUpdateDTO.getId())) {
            /* 80 */
            NoticeAccountDO noticeAccountDO = this.noticeAccountConverter.toNoticeAccountDO(noticeAccountSaveOrUpdateDTO);
            /* 81 */
            return Boolean.valueOf((this.noticeAccountMapper.insert(noticeAccountDO) > 0));
            
        }
        /* 83 */
        return ChainWrappers.lambdaUpdateChain(noticeAccountMapper)
                .set(ObjectUtil.isNotEmpty(noticeAccountSaveOrUpdateDTO.getAccountId()), NoticeAccountDO::getAccountId, noticeAccountSaveOrUpdateDTO.getAccountId())
                .set(ObjectUtil.isNotEmpty(noticeAccountSaveOrUpdateDTO.getNoticeId()), NoticeAccountDO::getNoticeId, noticeAccountSaveOrUpdateDTO.getNoticeId())
                .eq(BaseDO::getId, noticeAccountSaveOrUpdateDTO.getId())
                .eq(BaseDO::getDelFlag, DelFlagEnum.NORMAL.getFlag())
/* 88 */.update();
        
    }
    
}
