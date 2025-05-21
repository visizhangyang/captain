package com.porn.service.withdraw.converter;

import com.porn.client.withdraw.dto.ProxyWithdrawQueryPageDTO;
import com.porn.client.withdraw.dto.WithdrawQueryPageDTO;
import com.porn.client.withdraw.dto.WithdrawSaveOrUpdateDTO;
import com.porn.client.withdraw.vo.WithdrawVo;
import com.porn.service.withdraw.dao.entity.WithdrawDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WithdrawConverter {
    WithdrawVo toWithdrawVo(WithdrawDO paramWithdrawDO);

    List<WithdrawVo> toWithdrawVoList(List<WithdrawDO> paramList);

    WithdrawDO toWithdrawDO(WithdrawSaveOrUpdateDTO paramWithdrawSaveOrUpdateDTO);

    WithdrawQueryPageDTO toWithdrawQueryPageDTO(ProxyWithdrawQueryPageDTO paramProxyWithdrawQueryPageDTO);
}

