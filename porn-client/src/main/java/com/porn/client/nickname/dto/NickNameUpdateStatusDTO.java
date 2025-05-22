
package com.porn.client.nickname.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class NickNameUpdateStatusDTO
         extends BaseDTO {

    @ApiModelProperty("id列表")
     private List<Long> idList;

    @ApiModelProperty("使用状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
     private Integer status;



    public void setIdList(List<Long> idList) {
        /* 17 */
        this.idList = idList;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof NickNameUpdateStatusDTO;
    }



    /* 18 */
    protected NickNameUpdateStatusDTO(NickNameUpdateStatusDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
        this.status = b.status;
    }

    public static NickNameUpdateStatusDTOBuilder<?, ?> builder() {
        return new NickNameUpdateStatusDTOBuilderImpl();
    }

    private static final class NickNameUpdateStatusDTOBuilderImpl extends NickNameUpdateStatusDTOBuilder<NickNameUpdateStatusDTO, NickNameUpdateStatusDTOBuilderImpl> {
        private NickNameUpdateStatusDTOBuilderImpl() {
        }

        protected NickNameUpdateStatusDTOBuilderImpl self() {
            return this;
        }

        public NickNameUpdateStatusDTO build() {
            return new NickNameUpdateStatusDTO(this);
        }
    }

    public static abstract class NickNameUpdateStatusDTOBuilder<C extends NickNameUpdateStatusDTO, B extends NickNameUpdateStatusDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private List<Long> idList;

        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        private Integer status;

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public NickNameUpdateStatusDTO(List<Long> idList, Integer status) {
        /* 19 */
        this.idList = idList;
        this.status = status;

    }


    public NickNameUpdateStatusDTO() {
    }



    public List<Long> getIdList() {
        /* 24 */
        return this.idList;

    }


    public Integer getStatus() {
        /* 27 */
        return this.status;

    }

}


