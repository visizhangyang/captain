package com.porn.service.test.converter;

import com.porn.client.test.dto.TestDTO;
import com.porn.service.test.dao.entity.TestDO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestConverter {
    TestDO toTestDO(TestDTO paramTestDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/test/converter/TestConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */