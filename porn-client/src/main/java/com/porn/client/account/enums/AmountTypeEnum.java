package com.porn.client.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AmountTypeEnum {
    SUBAVAILABLE_ADDFREEZE(0, "扣可用, 增加冻结"),
    SUBTOTAL_SUBFREEZE(1, "扣总数, 扣冻结"),
    SUBTOTAL_SUBAVAILABLE(2, "扣总数, 扣可用"),
    ADDTOTAL_ADDAVAILABLE(3, "加总数, 加可用"),
    ADDAVAILABLE_SUBFREEZE(4, "加可用, 扣冻结");

    private final Integer type;
    private final String description;

    public static AmountTypeEnum fromType(Integer type) {
        if (type == null) {
            return null;
        }
        for (AmountTypeEnum e : values()) {
            if (e.getType() == type) {
                return e;
            }
        }
        throw new IllegalArgumentException("Unknown AmountTypeEnum type: " + type);
    }

}