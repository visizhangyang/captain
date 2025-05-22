package com.porn.client.transfer.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.transfer.dto.*;
import com.porn.client.transfer.vo.TransferVo;

import java.util.List;

public interface TransferApiService {
    TransferVo queryTransfer(TransferQueryDTO paramTransferQueryDTO);

    List<TransferVo> queryTransferList(TransferQueryDTO paramTransferQueryDTO);

    PageVo<TransferVo> queryPage(TransferQueryPageDTO paramTransferQueryPageDTO);

    TransferVo saveOrUpdate(TransferSaveOrUpdateDTO paramTransferSaveOrUpdateDTO);

    Boolean audit(TransferAuditDTO paramTransferAuditDTO);

    Boolean reject(TransferRejectDTO paramTransferRejectDTO);

    Boolean delete(TransferDeleteDTO paramTransferDeleteDTO);
}


