package com.porn.client.inline.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class InlineSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("最小在线人数")
    private Long minInlineCount;

    @ApiModelProperty("最大搬砖金额")
    private Long maxInlineCount;

    @ApiModelProperty("在线时间范围(小)")
    private String minInlineTime;

    @ApiModelProperty("在线时间范围(大)")
    private String maxInlineTime;

    protected InlineSaveOrUpdateDTO(InlineSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.minInlineCount = b.minInlineCount;
        this.maxInlineCount = b.maxInlineCount;
        this.minInlineTime = b.minInlineTime;
        this.maxInlineTime = b.maxInlineTime;
    }

    public InlineSaveOrUpdateDTO(Long minInlineCount, Long maxInlineCount, String minInlineTime, String maxInlineTime) {

        this.minInlineCount = minInlineCount;
        this.maxInlineCount = maxInlineCount;
        this.minInlineTime = minInlineTime;
        this.maxInlineTime = maxInlineTime;

    }

    public InlineSaveOrUpdateDTO() {
    }

    public static InlineSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new InlineSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof InlineSaveOrUpdateDTO;
    }

    public Long getMinInlineCount() {

        return this.minInlineCount;

    }

    public void setMinInlineCount(Long minInlineCount) {
        this.minInlineCount = minInlineCount;
    }

    public Long getMaxInlineCount() {

        return this.maxInlineCount;

    }

    public void setMaxInlineCount(Long maxInlineCount) {
        this.maxInlineCount = maxInlineCount;
    }

    public String getMinInlineTime() {

        return this.minInlineTime;

    }

    public void setMinInlineTime(String minInlineTime) {
        this.minInlineTime = minInlineTime;
    }

    public String getMaxInlineTime() {

        return this.maxInlineTime;

    }

    public void setMaxInlineTime(String maxInlineTime) {
        this.maxInlineTime = maxInlineTime;
    }

    private static final class InlineSaveOrUpdateDTOBuilderImpl extends InlineSaveOrUpdateDTOBuilder<InlineSaveOrUpdateDTO, InlineSaveOrUpdateDTOBuilderImpl> {
        private InlineSaveOrUpdateDTOBuilderImpl() {
        }

        protected InlineSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public InlineSaveOrUpdateDTO build() {
            return new InlineSaveOrUpdateDTO(this);
        }
    }

    public static abstract class InlineSaveOrUpdateDTOBuilder<C extends InlineSaveOrUpdateDTO, B extends InlineSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long minInlineCount;
        private Long maxInlineCount;
        private String minInlineTime;
        private String maxInlineTime;

        public B minInlineCount(Long minInlineCount) {
            this.minInlineCount = minInlineCount;
            return self();
        }

        public B maxInlineCount(Long maxInlineCount) {
            this.maxInlineCount = maxInlineCount;
            return self();
        }

        public B minInlineTime(String minInlineTime) {
            this.minInlineTime = minInlineTime;
            return self();
        }

        public B maxInlineTime(String maxInlineTime) {
            this.maxInlineTime = maxInlineTime;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

