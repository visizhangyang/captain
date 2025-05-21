package com.porn.client.recharge.api;

import com.porn.client.common.vo.PageVo;
import com.porn.client.recharge.dto.*;
import com.porn.client.recharge.vo.RechargeVo;

import java.math.BigDecimal;
import java.util.List;

public interface RechargeApiService {
    RechargeVo queryRecharge(RechargeQueryDTO paramRechargeQueryDTO);

    List<RechargeVo> queryRechargeList(RechargeQueryDTO paramRechargeQueryDTO);

    PageVo<RechargeVo> queryPage(RechargeQueryPageDTO paramRechargeQueryPageDTO);

    PageVo<RechargeVo> queryProxyPage(ProxyRechargeQueryPageDTO paramProxyRechargeQueryPageDTO);

    RechargeVo saveOrUpdate(RechargeSaveOrUpdateDTO paramRechargeSaveOrUpdateDTO);

    Boolean receipt(RechargeReceiptDTO paramRechargeReceiptDTO);

    Boolean cancel(RechargeCancelDTO paramRechargeCancelDTO);

    Boolean delete(RechargeDeleteDTO paramRechargeDeleteDTO);

    BigDecimal sumRechargeAmount(RechargeQueryDTO paramRechargeQueryDTO);

    Boolean addCacheItem(RechargeCacheItemDTO paramRechargeCacheItemDTO);

    Boolean clearCacheItem();

    RechargeCacheItemDTO getCacheItem(String paramString);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/recharge/api/RechargeApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */