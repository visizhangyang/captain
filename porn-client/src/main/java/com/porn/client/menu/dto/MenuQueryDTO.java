package com.porn.client.menu.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class MenuQueryDTO extends BaseDTO {
    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("菜单名")
    private String name;

    @ApiModelProperty("菜单名(模糊搜索)")
    private String lkName;

    @ApiModelProperty("图标类型, IconTypeEnum")
    private Integer iconType;

    @ApiModelProperty("菜单ID列表")
    private List<Long> menuIdList;

    protected MenuQueryDTO(MenuQueryDTOBuilder<?, ?> b) {
        super(b);
        this.userId = b.userId;
        this.name = b.name;
        this.lkName = b.lkName;
        this.iconType = b.iconType;
        this.menuIdList = b.menuIdList;
    }

    public MenuQueryDTO(Long userId, String name, String lkName, Integer iconType, List<Long> menuIdList) {
        this.userId = userId;
        this.name = name;
        this.lkName = lkName;
        this.iconType = iconType;
        this.menuIdList = menuIdList;
    }

    public MenuQueryDTO() {
    }

    public static MenuQueryDTOBuilder<?, ?> builder() {
        return new MenuQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MenuQueryDTO;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLkName() {
        return this.lkName;
    }

    public void setLkName(String lkName) {
        this.lkName = lkName;
    }

    public Integer getIconType() {
        return this.iconType;
    }

    public void setIconType(Integer iconType) {
        this.iconType = iconType;
    }

    public List<Long> getMenuIdList() {
        return this.menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }

    private static final class MenuQueryDTOBuilderImpl extends MenuQueryDTOBuilder<MenuQueryDTO, MenuQueryDTOBuilderImpl> {
        private MenuQueryDTOBuilderImpl() {
        }

        protected MenuQueryDTOBuilderImpl self() {
            return this;
        }

        public MenuQueryDTO build() {
            return new MenuQueryDTO(this);
        }
    }

    public static abstract class MenuQueryDTOBuilder<C extends MenuQueryDTO, B extends MenuQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long userId;
        private String name;
        private String lkName;
        private Integer iconType;
        private List<Long> menuIdList;

        public B userId(Long userId) {
            this.userId = userId;
            return self();
        }

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B lkName(String lkName) {
            this.lkName = lkName;
            return self();
        }

        public B iconType(Integer iconType) {
            this.iconType = iconType;
            return self();
        }

        public B menuIdList(List<Long> menuIdList) {
            this.menuIdList = menuIdList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

