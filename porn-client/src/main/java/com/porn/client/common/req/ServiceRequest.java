
package com.porn.client.common.req;





import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;





 public class ServiceRequest
         implements Serializable
         {

    @ApiModelProperty(value = "请求资源", hidden = true)
     private transient String beUsedSource;

    @ApiModelProperty(value = "请求序列号", hidden = true)
     private transient String serialNumber;



    protected ServiceRequest(ServiceRequestBuilder<?, ?> b) {
        /* 16 */
        this.beUsedSource = b.beUsedSource;
        this.serialNumber = b.serialNumber;
    }

    public static ServiceRequestBuilder<?, ?> builder() {
        return new ServiceRequestBuilderImpl();
    }

    private static final class ServiceRequestBuilderImpl extends ServiceRequestBuilder<ServiceRequest, ServiceRequestBuilderImpl> {
        private ServiceRequestBuilderImpl() {
        }

        protected ServiceRequestBuilderImpl self() {
            return this;
        }

        public ServiceRequest build() {
            return new ServiceRequest(this);
        }
    }

    public static abstract class ServiceRequestBuilder<C extends ServiceRequest, B extends ServiceRequestBuilder<C, B>> {
        private String beUsedSource;

        public B beUsedSource(String beUsedSource) {
            this.beUsedSource = beUsedSource;
            return self();
        }

        private String serialNumber;

        public B serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return self();
        }

        protected abstract B self();

        public abstract C build();

        public String toString() {
            return "ServiceRequest.ServiceRequestBuilder(beUsedSource=" + this.beUsedSource + ", serialNumber=" + this.serialNumber + ")";
        }
    }

    public ServiceRequest(String beUsedSource, String serialNumber) {
        /* 17 */
        this.beUsedSource = beUsedSource;
        this.serialNumber = serialNumber;

    }



    public ServiceRequest() {
    }

}


