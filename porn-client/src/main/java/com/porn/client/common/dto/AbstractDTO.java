
package com.porn.client.common.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.req.ServiceRequest;


import java.io.Serializable;





 public class AbstractDTO
         extends ServiceRequest
         implements Serializable
         {
       private static final long serialVersionUID = 749671606656692229L;

    @ApiModelProperty(value = "当前用户ID", hidden = true)
     private Long currentUserId;

    @ApiModelProperty(value = "当前用户名称", hidden = true)
     private String currentUserName;



    public void setCurrentUserId(Long currentUserId) {
        /* 18 */
        this.currentUserId = currentUserId;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AbstractDTO;
    }



    /* 19 */
    protected AbstractDTO(AbstractDTOBuilder<?, ?> b) {
        super(b);
        this.currentUserId = b.currentUserId;
        this.currentUserName = b.currentUserName;
    }

    public static AbstractDTOBuilder<?, ?> builder() {
        return new AbstractDTOBuilderImpl();
    }

    private static final class AbstractDTOBuilderImpl extends AbstractDTOBuilder<AbstractDTO, AbstractDTOBuilderImpl> {
        private AbstractDTOBuilderImpl() {
        }

        protected AbstractDTOBuilderImpl self() {
            return this;
        }

        public AbstractDTO build() {
            return new AbstractDTO(this);
        }
    }

    public static abstract class AbstractDTOBuilder<C extends AbstractDTO, B extends AbstractDTOBuilder<C, B>> extends ServiceRequest.ServiceRequestBuilder<C, B> {
        private Long currentUserId;

        public B currentUserId(Long currentUserId) {
            this.currentUserId = currentUserId;
            return self();
        }

        private String currentUserName;

        public B currentUserName(String currentUserName) {
            this.currentUserName = currentUserName;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public AbstractDTO(Long currentUserId, String currentUserName) {
        /* 20 */
        this.currentUserId = currentUserId;
        this.currentUserName = currentUserName;

    }




    public AbstractDTO() {
    }



    public Long getCurrentUserId() {
        /* 27 */
        return this.currentUserId;

    }


    public String getCurrentUserName() {
        /* 30 */
        return this.currentUserName;

    }

}


