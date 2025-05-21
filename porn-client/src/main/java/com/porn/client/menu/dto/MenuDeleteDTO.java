
package com.porn.client.menu.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class MenuDeleteDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof MenuDeleteDTO;
    }



    /* 14 */
    protected MenuDeleteDTO(MenuDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public static MenuDeleteDTOBuilder<?, ?> builder() {
        return new MenuDeleteDTOBuilderImpl();
    }

    private static final class MenuDeleteDTOBuilderImpl extends MenuDeleteDTOBuilder<MenuDeleteDTO, MenuDeleteDTOBuilderImpl> {
        protected MenuDeleteDTOBuilderImpl self() {
            return this;
        }

        private MenuDeleteDTOBuilderImpl() {
        }

        public MenuDeleteDTO build() {
            return new MenuDeleteDTO(this);
        }
    }

    public static abstract class MenuDeleteDTOBuilder<C extends MenuDeleteDTO, B extends MenuDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public MenuDeleteDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/menu/dto/MenuDeleteDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */