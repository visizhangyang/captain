
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/test/impl/TestServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */