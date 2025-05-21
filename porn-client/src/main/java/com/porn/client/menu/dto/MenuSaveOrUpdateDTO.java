
package com.porn.client.menu.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;

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


    /* 15 */
    public void setName(String name) {
        this.name = name;
    }

    public void setIconType(Integer iconType) {
        this.iconType = iconType;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MenuSaveOrUpdateDTO;
    }



    /* 16 */
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

    public static MenuSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new MenuSaveOrUpdateDTOBuilderImpl();
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

        public B name(String name) {
            this.name = name;
            return self();
        }

        private String urlPath;
        private String description;
        private Integer sortNo;
        private Long parentId;

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

    public MenuSaveOrUpdateDTO(String name, Integer iconType, String iconPath, String urlPath, String description, Integer sortNo, Long parentId) {
        /* 17 */
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



    public String getName() {
        /* 22 */
        return this.name;

    }


    public Integer getIconType() {
        /* 25 */
        return this.iconType;

    }


    public String getIconPath() {
        /* 28 */
        return this.iconPath;

    }


    public String getUrlPath() {
        /* 31 */
        return this.urlPath;

    }


    public String getDescription() {
        /* 34 */
        return this.description;

    }


    public Integer getSortNo() {
        /* 37 */
        return this.sortNo;

    }


    public Long getParentId() {
        /* 40 */
        return this.parentId;

    }
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/menu/dto/MenuSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */