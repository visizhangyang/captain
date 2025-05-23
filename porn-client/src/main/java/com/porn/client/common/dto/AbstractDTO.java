package com.porn.client.common.dto;

import com.porn.client.common.req.ServiceRequest;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class AbstractDTO
        extends ServiceRequest
        implements Serializable {
    private static final long serialVersionUID = 749671606656692229L;

    @ApiModelProperty(value = "当前用户ID", hidden = true)
    private Long currentUserId;

    @ApiModelProperty(value = "当前用户名称", hidden = true)
    private String currentUserName;

    protected AbstractDTO(AbstractDTOBuilder<?, ?> b) {
        super(b);
        this.currentUserId = b.currentUserId;
        this.currentUserName = b.currentUserName;
    }

    public AbstractDTO(Long currentUserId, String currentUserName) {

        this.currentUserId = currentUserId;
        this.currentUserName = currentUserName;

    }

    public AbstractDTO() {
    }

    public static AbstractDTOBuilder<?, ?> builder() {
        return new AbstractDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AbstractDTO;
    }

    public Long getCurrentUserId() {

        return this.currentUserId;

    }

    public void setCurrentUserId(Long currentUserId) {

        this.currentUserId = currentUserId;
    }

    public String getCurrentUserName() {

        return this.currentUserName;

    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
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
        private String currentUserName;

        public B currentUserId(Long currentUserId) {
            this.currentUserId = currentUserId;
            return self();
        }

        public B currentUserName(String currentUserName) {
            this.currentUserName = currentUserName;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

