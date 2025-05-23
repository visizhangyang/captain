package com.porn.client.inline.dto;

import com.porn.client.common.dto.BaseDTO;

public class InlineQueryDTO
        extends BaseDTO {

    protected InlineQueryDTO(InlineQueryDTOBuilder<?, ?> b) {
        super(b);
    }

    public InlineQueryDTO() {
    }

    public static InlineQueryDTOBuilder<?, ?> builder() {
        return new InlineQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof InlineQueryDTO;
    }

    private static final class InlineQueryDTOBuilderImpl extends InlineQueryDTOBuilder<InlineQueryDTO, InlineQueryDTOBuilderImpl> {
        private InlineQueryDTOBuilderImpl() {
        }

        protected InlineQueryDTOBuilderImpl self() {
            return this;
        }

        public InlineQueryDTO build() {
            return new InlineQueryDTO(this);
        }
    }

    public static abstract class InlineQueryDTOBuilder<C extends InlineQueryDTO, B extends InlineQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

