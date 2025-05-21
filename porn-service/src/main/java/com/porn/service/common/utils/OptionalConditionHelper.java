package com.porn.service.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import java.util.Collection;

public class OptionalConditionHelper {

    public static <T, V> void addEq(LambdaQueryChainWrapper<T> query, V value, SFunction<T, ?> column) {
        if (ObjectUtil.isNotEmpty(value)) {
            query.eq(column, value);
        }
    }

    public static <T, V> void addIn(LambdaQueryChainWrapper<T> query, Collection<V> values, SFunction<T, ?> column) {
        if (CollUtil.isNotEmpty(values)) {
            query.in(column, values);
        }
    }

    public static <T, V> void addEq(LambdaQueryWrapper<T> wrapper, V value, SFunction<T, ?> column) {
        if (ObjectUtil.isNotEmpty(value)) {
            wrapper.eq(column, value);
        }
    }

    public static <T> void addLike(LambdaQueryWrapper<T> wrapper, String value, SFunction<T, ?> column) {
        if (StrUtil.isNotBlank(value)) {
            wrapper.like(column, value);
        }
    }

    public static <T, V> void addIn(LambdaQueryWrapper<T> wrapper, Collection<V> values, SFunction<T, ?> column) {
        if (CollUtil.isNotEmpty(values)) {
            wrapper.in(column, values);
        }
    }

    public static <T, V extends Comparable<?>> void addGe(LambdaQueryWrapper<T> wrapper, V value, SFunction<T, ?> column) {
        if (ObjectUtil.isNotEmpty(value)) {
            wrapper.ge(column, value);
        }
    }

    public static <T, V extends Comparable<?>> void addLe(LambdaQueryWrapper<T> wrapper, V value, SFunction<T, ?> column) {
        if (ObjectUtil.isNotEmpty(value)) {
            wrapper.le(column, value);
        }
    }
}


