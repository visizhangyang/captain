
package com.porn.client.nickname.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class NickNameBatchDeleteDTO
         extends BaseDTO
         {

    @ApiModelProperty("id列表")
     private List<Long> idList;



    public void setIdList(List<Long> idList) {
        /* 16 */
        this.idList = idList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof NickNameBatchDeleteDTO;
    }



    /* 17 */
    protected NickNameBatchDeleteDTO(NickNameBatchDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
    }

    public static NickNameBatchDeleteDTOBuilder<?, ?> builder() {
        return new NickNameBatchDeleteDTOBuilderImpl();
    }

    private static final class NickNameBatchDeleteDTOBuilderImpl extends NickNameBatchDeleteDTOBuilder<NickNameBatchDeleteDTO, NickNameBatchDeleteDTOBuilderImpl> {
        private NickNameBatchDeleteDTOBuilderImpl() {
        }

        protected NickNameBatchDeleteDTOBuilderImpl self() {
            return this;
        }

        public NickNameBatchDeleteDTO build() {
            return new NickNameBatchDeleteDTO(this);
        }
    }

    public static abstract class NickNameBatchDeleteDTOBuilder<C extends NickNameBatchDeleteDTO, B extends NickNameBatchDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        private List<Long> idList;

        protected abstract B self();

        public abstract C build();

    }

    public NickNameBatchDeleteDTO(List<Long> idList) {
        /* 18 */
        this.idList = idList;

    }


    public NickNameBatchDeleteDTO() {
    }



    public List<Long> getIdList() {
        /* 23 */
        return this.idList;

    }

}


