package com.porn.client.test.dto;

import com.porn.client.common.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("测试实体")
public class TestDTO
        extends AbstractDTO {

    @ApiModelProperty("名称")
    private String name;

    protected TestDTO(TestDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
    }

    public static TestDTOBuilder<?, ?> builder() {
        return new TestDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof TestDTO;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {

        this.name = name;
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
        private String name;

        public B name(String name) {
            this.name = name;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

