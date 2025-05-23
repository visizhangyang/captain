package com.porn.client.message.dto;

import com.porn.client.common.dto.BaseDTO;

public class AdminMessageDTO
        extends BaseDTO {

    protected AdminMessageDTO(AdminMessageDTOBuilder<?, ?> b) {
        super(b);
    }

    public AdminMessageDTO() {
    }

    public static AdminMessageDTOBuilder<?, ?> builder() {
        return new AdminMessageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AdminMessageDTO;
    }

    private static final class AdminMessageDTOBuilderImpl extends AdminMessageDTOBuilder<AdminMessageDTO, AdminMessageDTOBuilderImpl> {
        private AdminMessageDTOBuilderImpl() {
        }

        protected AdminMessageDTOBuilderImpl self() {
            return this;
        }

        public AdminMessageDTO build() {
            return new AdminMessageDTO(this);
        }
    }

    public static abstract class AdminMessageDTOBuilder<C extends AdminMessageDTO, B extends AdminMessageDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

