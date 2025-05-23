package com.porn.client.menu.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class MenuSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("图标类型, IconTypeEnum")
    private Integer iconType;

    @ApiModelProperty("图标路径")
    private String iconPath;

    @ApiModelProperty("url请求路径")
    private String urlPath;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("排序值")
    private Integer sortNo;

    @ApiModelProperty("父ID")
    private Long parentId;

    protected MenuSaveOrUpdateDTO(MenuSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.iconType = b.iconType;
        this.iconPath = b.iconPath;
        this.urlPath = b.urlPath;
        this.description = b.description;
        this.sortNo = b.sortNo;
        this.parentId = b.parentId;
    }

    public MenuSaveOrUpdateDTO(String name, Integer iconType, String iconPath, String urlPath, String description, Integer sortNo, Long parentId) {

        this.name = name;
        this.iconType = iconType;
        this.iconPath = iconPath;
        this.urlPath = urlPath;
        this.description = description;
        this.sortNo = sortNo;
        this.parentId = parentId;

    }

    public MenuSaveOrUpdateDTO() {
    }

    public static MenuSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new MenuSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MenuSaveOrUpdateDTO;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIconType() {

        return this.iconType;

    }

    public void setIconType(Integer iconType) {
        this.iconType = iconType;
    }

    public String getIconPath() {

        return this.iconPath;

    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getUrlPath() {

        return this.urlPath;

    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getDescription() {

        return this.description;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortNo() {

        return this.sortNo;

    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Long getParentId() {

        return this.parentId;

    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    private static final class MenuSaveOrUpdateDTOBuilderImpl extends MenuSaveOrUpdateDTOBuilder<MenuSaveOrUpdateDTO, MenuSaveOrUpdateDTOBuilderImpl> {
        private MenuSaveOrUpdateDTOBuilderImpl() {
        }

        protected MenuSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public MenuSaveOrUpdateDTO build() {
            return new MenuSaveOrUpdateDTO(this);
        }
    }

    public static abstract class MenuSaveOrUpdateDTOBuilder<C extends MenuSaveOrUpdateDTO, B extends MenuSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String name;
        private Integer iconType;
        private String iconPath;
        private String urlPath;
        private String description;
        private Integer sortNo;
        private Long parentId;

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B iconType(Integer iconType) {
            this.iconType = iconType;
            return self();
        }

        public B iconPath(String iconPath) {
            this.iconPath = iconPath;
            return self();
        }

        public B urlPath(String urlPath) {
            this.urlPath = urlPath;
            return self();
        }

        public B description(String description) {
            this.description = description;
            return self();
        }

        public B sortNo(Integer sortNo) {
            this.sortNo = sortNo;
            return self();
        }

        public B parentId(Long parentId) {
            this.parentId = parentId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

