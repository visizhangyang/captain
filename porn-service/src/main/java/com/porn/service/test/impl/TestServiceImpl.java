
package com.porn.service.test.impl;



import com.porn.client.test.dto.TestDTO;
import com.porn.client.test.service.TestService;
import com.porn.service.test.converter.TestConverter;
import com.porn.service.test.dao.entity.TestDO;
import com.porn.service.test.dao.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

















@Service

@Transactional(rollbackFor = {Exception.class})
 public class TestServiceImpl
         implements TestService
         {

    @Autowired
     private TestMapper testMapper;

    @Autowired
     private TestConverter testConverter;



    public String save(TestDTO testDTO) {
        /* 29 */
        TestDO testDO = this.testConverter.toTestDO(testDTO);
        /* 30 */
        this.testMapper.insert(testDO);
        /* 31 */
        return "保存成功....";

    }

}


