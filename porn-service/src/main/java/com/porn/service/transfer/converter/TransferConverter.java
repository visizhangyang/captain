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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/transfer/converter/TransferConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */