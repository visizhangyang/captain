package com.porn.client.nickname.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class NickNameUploadDTO
        extends BaseDTO {

    @ApiModelProperty("行数据")
    private List<String> lines;

    protected NickNameUploadDTO(NickNameUploadDTOBuilder<?, ?> b) {
        super(b);
        this.lines = b.lines;
    }

    public NickNameUploadDTO(List<String> lines) {

        this.lines = lines;

    }

    public NickNameUploadDTO() {
    }

    public static NickNameUploadDTOBuilder<?, ?> builder() {
        return new NickNameUploadDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof NickNameUploadDTO;
    }

    public List<String> getLines() {

        return this.lines;

    }

    public void setLines(List<String> lines) {

        this.lines = lines;
    }

    private static final class NickNameUploadDTOBuilderImpl extends NickNameUploadDTOBuilder<NickNameUploadDTO, NickNameUploadDTOBuilderImpl> {
        private NickNameUploadDTOBuilderImpl() {
        }

        protected NickNameUploadDTOBuilderImpl self() {
            return this;
        }

        public NickNameUploadDTO build() {
            return new NickNameUploadDTO(this);
        }
    }

    public static abstract class NickNameUploadDTOBuilder<C extends NickNameUploadDTO, B extends NickNameUploadDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private List<String> lines;

        public B lines(List<String> lines) {
            this.lines = lines;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

