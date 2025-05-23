package com.porn.service.transfer.converter;

import com.porn.client.transfer.dto.TransferSaveOrUpdateDTO;
import com.porn.client.transfer.vo.TransferVo;
import com.porn.service.transfer.dao.entity.TransferDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransferConverter {
    TransferVo toTransferVo(TransferDO paramTransferDO);

    List<TransferVo> toTransferVoList(List<TransferDO> paramList);

    TransferDO toTransferDO(TransferSaveOrUpdateDTO paramTransferSaveOrUpdateDTO);
}

