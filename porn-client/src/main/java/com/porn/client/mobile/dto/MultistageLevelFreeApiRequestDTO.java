
package com.porn.client.mobile.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;






 public class MultistageLevelFreeApiRequestDTO
         implements Serializable
         {

    @ApiModelProperty("1-一级, 2-二级, 3-三级")
     private Integer levelType;



    public void setLevelType(Integer levelType) {
        /* 15 */
        this.levelType = levelType;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MultistageLevelFreeApiRequestDTO;
    }



    /* 16 */
    protected MultistageLevelFreeApiRequestDTO(MultistageLevelFreeApiRequestDTOBuilder<?, ?> b) {
        this.levelType = b.levelType;
    }

    public static MultistageLevelFreeApiRequestDTOBuilder<?, ?> builder() {
        return new MultistageLevelFreeApiRequestDTOBuilderImpl();
    }

    private static final class MultistageLevelFreeApiRequestDTOBuilderImpl extends MultistageLevelFreeApiRequestDTOBuilder<MultistageLevelFreeApiRequestDTO, MultistageLevelFreeApiRequestDTOBuilderImpl> {
        private MultistageLevelFreeApiRequestDTOBuilderImpl() {
        }

        protected MultistageLevelFreeApiRequestDTOBuilderImpl self() {
            return this;
        }

        public MultistageLevelFreeApiRequestDTO build() {
            return new MultistageLevelFreeApiRequestDTO(this);
        }
    }

    public static abstract class MultistageLevelFreeApiRequestDTOBuilder<C extends MultistageLevelFreeApiRequestDTO, B extends MultistageLevelFreeApiRequestDTOBuilder<C, B>> {
        public B levelType(Integer levelType) {
            this.levelType = levelType;
            return self();
        }

        private Integer levelType;

        protected abstract B self();

        public abstract C build();

    }

    public MultistageLevelFreeApiRequestDTO(Integer levelType) {
        /* 17 */
        this.levelType = levelType;

    }


    public MultistageLevelFreeApiRequestDTO() {
    }



    public Integer getLevelType() {
        /* 22 */
        return this.levelType;

    }

}


