package com.junmoyu.venus.starter.core.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 用户账号是否被锁定
 *
 * @author moyu.jun
 * @date 2021/12/18
 */
@Getter
@RequiredArgsConstructor
public enum LockedEnum {

    /**
     * 禁用状态 - 正常
     */
    enable(false, "正常"),

    /**
     * 禁用状态 - 禁用
     */
    disable(true, "禁用");

    private final boolean locked;

    private final String description;

    /**
     * 获取枚举标签
     *
     * @param locked 值
     * @return 标签
     */
    public static String getValue(final boolean locked) {
        return Arrays.stream(LockedEnum.values())
                .filter(e -> e.locked == locked)
                .findFirst()
                .map(LockedEnum::getDescription)
                .orElse(null);
    }
}
