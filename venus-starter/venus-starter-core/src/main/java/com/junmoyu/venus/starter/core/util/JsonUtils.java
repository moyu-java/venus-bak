package com.junmoyu.venus.starter.core.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Json 操作工具类
 *
 * @author moyu.jun
 * @date 2022/3/18
 */
@Slf4j
public final class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static ObjectMapper getInstance() {
        return MAPPER;
    }

    /**
     * To json string.
     *
     * @param object the object
     * @return the string
     */
    public static String toJson(final Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            log.warn("write to json string error: {}", object, e);
            return "{}";
        }
    }

    /**
     * To object from json string.
     *
     * @param value json string
     * @param clz   the object class
     * @param <T>   object type
     * @return the object
     */
    public static <T> T toObject(String value, Class<T> clz) {
        try {
            return MAPPER.readValue(value, clz);
        } catch (JsonProcessingException e) {
            log.warn("read to object error: {}", value, e);
            return null;
        }
    }

    /**
     * To object from json string.
     *
     * @param value         Json str
     * @param typeReference 要转换的类型
     * @param <T>           泛型
     * @return JavaBean 对象
     */
    public static <T> T toObject(String value, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(value) || typeReference == null) {
            return null;
        }
        try {
            if (typeReference.getType().equals(String.class)) {
                return (T) value;
            }
            return MAPPER.readValue(value, typeReference);
        } catch (Exception e) {
            log.warn("read to object error: {}", value, e);
            return null;
        }
    }

    /**
     * To array from json string.
     *
     * @param value json string
     * @param clz   the object class
     * @param <T>   object type
     * @return the array
     */
    public static <T> List<T> toArray(String value, Class<T> clz) {
        JavaType javaType = MAPPER.getTypeFactory().constructCollectionType(Collection.class, clz);
        try {
            return MAPPER.readValue(value, javaType);
        } catch (JsonProcessingException e) {
            log.warn("read to array error: {}", value, e);
            return null;
        }
    }

    /**
     * Remove class object.
     *
     * @param object the object
     * @return the object
     */
    public static Object removeClass(final Object object) {
        if (object instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) object;
            Object result = map.get("result");
            if (result instanceof Map) {
                Map<?, ?> resultMap = (Map<?, ?>) result;
                resultMap.remove("class");
            }
            map.remove("class");
        }
        return object;
    }

    @JsonIgnoreProperties("class")
    @interface IgnoreType {
    }
}
