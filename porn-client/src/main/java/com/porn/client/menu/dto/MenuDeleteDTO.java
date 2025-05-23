package com.porn.client.menu.dto;

import com.porn.client.common.dto.BaseDTO;

public class MenuDeleteDTO
        extends BaseDTO {

    protected MenuDeleteDTO(MenuDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public MenuDeleteDTO() {
    }

    public static MenuDeleteDTOBuilder<?, ?> builder() {
        return new MenuDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MenuDeleteDTO;
    }

    private static final class MenuDeleteDTOBuilderImpl extends MenuDeleteDTOBuilder<MenuDeleteDTO, MenuDeleteDTOBuilderImpl> {
        private MenuDeleteDTOBuilderImpl() {
        }

        protected MenuDeleteDTOBuilderImpl self() {
            return this;
        }

        public MenuDeleteDTO build() {
            return new MenuDeleteDTO(this);
        }
    }

    public static abstract class MenuDeleteDTOBuilder<C extends MenuDeleteDTO, B extends MenuDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

