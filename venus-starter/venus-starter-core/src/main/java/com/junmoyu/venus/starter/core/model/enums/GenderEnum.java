package com.junmoyu.venus.starter.core.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 性别枚举类
 *
 * @author moyu.jun
 * @date 2021/12/18
 */
@Getter
@RequiredArgsConstructor
public enum GenderEnum {

    /**
     * 性别 - 保密
     */
    SECRET(0, "保密"),

    /**
     * 性别 - 男
     */
    MAN(1, "男"),

    /**
     * 性别 - 女
     */
    WOMAN(2, "女");

    private final int gender;

    private final String description;

    /**
     * 获取枚举标签
     *
     * @param gender 性别
     * @return 性别描述
     */
    public static String getValue(final int gender) {
        return Arrays.stream(GenderEnum.values())
                .filter(e -> e.gender == gender)
                .findFirst()
                .orElse(GenderEnum.SECRET)
                .getDescription();
    }
}
