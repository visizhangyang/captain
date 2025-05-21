package com.porn.service.autowork.converter;

import com.porn.client.autowork.dto.AutoWorkSaveOrUpdateDTO;
import com.porn.client.autowork.vo.AutoWorkVo;
import com.porn.service.autowork.dao.entity.AutoWorkDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AutoWorkConverter {
    AutoWorkVo toAutoWorkVo(AutoWorkDO paramAutoWorkDO);

    List<AutoWorkVo> toAutoWorkList(List<AutoWorkDO> paramList);

    AutoWorkDO toAutoWorkDO(AutoWorkSaveOrUpdateDTO paramAutoWorkSaveOrUpdateDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/autowork/converter/AutoWorkConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */