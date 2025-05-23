package com.porn.service.mobile.converter;

import com.porn.client.account.dto.AccountSaveOrUpdateDTO;
import com.porn.client.account.vo.AccountStatisticsVo;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.merchant.vo.MerchantVo;
import com.porn.client.mobile.dto.RegisterApiRequestDTO;
import com.porn.client.mobile.vo.*;
import com.porn.client.order.vo.OrderVo;
import com.porn.client.recharge.vo.RechargeVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MobileConverter {
    AccountSaveOrUpdateDTO toAccountSaveOrUpdateDTO(RegisterApiRequestDTO paramRegisterApiRequestDTO);

    LoginAccountVo toLoginAccountVo(AccountVo paramAccountVo);

    PlatformStatisticsVo toPlatformStatisticsVo(AccountStatisticsVo paramAccountStatisticsVo);

    GoodsItemVo toGoodsItemVo(MerchantVo paramMerchantVo);

    MerchantTradeItemVo toMerchantTradeItemVo(OrderVo paramOrderVo);

    List<MerchantTradeItemVo> toMerchantTradeItemVoList(List<OrderVo> paramList);

    CreateRechargeVo toCreateRechargeVo(RechargeVo paramRechargeVo);
}

