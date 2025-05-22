package com.porn.service.test.converter;

import com.porn.client.test.dto.TestDTO;
import com.porn.service.test.dao.entity.TestDO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestConverter {
    TestDO toTestDO(TestDTO paramTestDTO);
}


