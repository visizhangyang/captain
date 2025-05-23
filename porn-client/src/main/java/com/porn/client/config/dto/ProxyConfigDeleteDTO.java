package com.porn.client.config.dto;

import com.porn.client.common.dto.BaseDTO;

public class ProxyConfigDeleteDTO
        extends BaseDTO {

    protected ProxyConfigDeleteDTO(ProxyConfigDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public ProxyConfigDeleteDTO() {
    }

    public static ProxyConfigDeleteDTOBuilder<?, ?> builder() {
        return new ProxyConfigDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProxyConfigDeleteDTO;
    }

    private static final class ProxyConfigDeleteDTOBuilderImpl extends ProxyConfigDeleteDTOBuilder<ProxyConfigDeleteDTO, ProxyConfigDeleteDTOBuilderImpl> {
        private ProxyConfigDeleteDTOBuilderImpl() {
        }

        protected ProxyConfigDeleteDTOBuilderImpl self() {
            return this;
        }

        public ProxyConfigDeleteDTO build() {
            return new ProxyConfigDeleteDTO(this);
        }
    }

    public static abstract class ProxyConfigDeleteDTOBuilder<C extends ProxyConfigDeleteDTO, B extends ProxyConfigDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

