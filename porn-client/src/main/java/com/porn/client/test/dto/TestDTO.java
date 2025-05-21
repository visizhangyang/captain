
package com.porn.client.test.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;







@ApiModel("测试实体")
 public class TestDTO
         extends AbstractDTO
         {
    
    @ApiModelProperty("名称")
     private String name;

    
    
    public void setName(String name) {
        /* 17 */
        this.name = name;
    }


    protected boolean canEqual(Object other) {
        return other instanceof TestDTO;
    }



    /* 18 */
    protected TestDTO(TestDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
    }

    public static TestDTOBuilder<?, ?> builder() {
        return new TestDTOBuilderImpl();
    }

    private static final class TestDTOBuilderImpl extends TestDTOBuilder<TestDTO, TestDTOBuilderImpl> {
        private TestDTOBuilderImpl() {
        }

        protected TestDTOBuilderImpl self() {
            return this;
        }

        public TestDTO build() {
            return new TestDTO(this);
        }
    }

    public static abstract class TestDTOBuilder<C extends TestDTO, B extends TestDTOBuilder<C, B>> extends AbstractDTO.AbstractDTOBuilder<C, B> {
        public B name(String name) {
            this.name = name;
            return self();
        }

        private String name;

        protected abstract B self();

        public abstract C build();

        
    }

    
    
    
    public String getName() {
        /* 23 */
        return this.name;
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/test/dto/TestDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */