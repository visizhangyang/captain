package com.porn.client.goods.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("商品查询分页DTO")
public class GoodsQueryPageDTO extends BasePageDTO {
    @ApiModelProperty("商户id")
    private Long merchantId;

    @ApiModelProperty("商品状态, GoodsStatusEnum")
    private Integer goodsStatus;

    @ApiModelProperty("商户id列表")
    private List<Long> merchantIdList;

    @ApiModelProperty("账户名称模糊搜索")
    private String lkAccountName;

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("商品类型GoodsTypeEnum, 0-普通商品, 1-重点关注")
    private Integer goodsType;

    protected GoodsQueryPageDTO(GoodsQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.goodsStatus = b.goodsStatus;
        this.merchantIdList = b.merchantIdList;
        this.lkAccountName = b.lkAccountName;
        this.accountId = b.accountId;
        this.goodsType = b.goodsType;
    }

    public GoodsQueryPageDTO(Long merchantId, Integer goodsStatus, List<Long> merchantIdList, String lkAccountName, Long accountId, Integer goodsType) {

        this.merchantId = merchantId;
        this.goodsStatus = goodsStatus;
        this.merchantIdList = merchantIdList;
        this.lkAccountName = lkAccountName;
        this.accountId = accountId;
        this.goodsType = goodsType;

    }

    public GoodsQueryPageDTO() {
    }

    public static GoodsQueryPageDTOBuilder<?, ?> builder() {
        return new GoodsQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof GoodsQueryPageDTO;
    }

    public Long getMerchantId() {

        return this.merchantId;

    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getGoodsStatus() {

        return this.goodsStatus;

    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public List<Long> getMerchantIdList() {

        return this.merchantIdList;

    }

    public void setMerchantIdList(List<Long> merchantIdList) {
        this.merchantIdList = merchantIdList;
    }

    public String getLkAccountName() {

        return this.lkAccountName;

    }

    public void setLkAccountName(String lkAccountName) {
        this.lkAccountName = lkAccountName;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getGoodsType() {

        return this.goodsType;

    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    private static final class GoodsQueryPageDTOBuilderImpl extends GoodsQueryPageDTOBuilder<GoodsQueryPageDTO, GoodsQueryPageDTOBuilderImpl> {
        private GoodsQueryPageDTOBuilderImpl() {
        }

        protected GoodsQueryPageDTOBuilderImpl self() {
            return this;
        }

        public GoodsQueryPageDTO build() {
            return new GoodsQueryPageDTO(this);
        }
    }

    public static abstract class GoodsQueryPageDTOBuilder<C extends GoodsQueryPageDTO, B extends GoodsQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private Long merchantId;
        private Integer goodsStatus;
        private List<Long> merchantIdList;
        private String lkAccountName;
        private Long accountId;
        private Integer goodsType;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        public B goodsStatus(Integer goodsStatus) {
            this.goodsStatus = goodsStatus;
            return self();
        }

        public B merchantIdList(List<Long> merchantIdList) {
            this.merchantIdList = merchantIdList;
            return self();
        }

        public B lkAccountName(String lkAccountName) {
            this.lkAccountName = lkAccountName;
            return self();
        }

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B goodsType(Integer goodsType) {
            this.goodsType = goodsType;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

