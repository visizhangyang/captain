package com.porn.service.log.converter;

import com.porn.client.log.vo.OperateLogVo;
import com.porn.service.log.dao.entity.OperateLogDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OperateLogConverter {
    OperateLogVo toOperateLogVo(OperateLogDO paramOperateLogDO);

    List<OperateLogVo> toOperateLogVoList(List<OperateLogDO> paramList);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/log/converter/OperateLogConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */