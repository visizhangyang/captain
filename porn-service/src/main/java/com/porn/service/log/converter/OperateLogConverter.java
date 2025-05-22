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


