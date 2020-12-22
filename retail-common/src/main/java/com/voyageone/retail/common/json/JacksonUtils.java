package com.voyageone.retail.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * json工具类
 */
public class JacksonUtils {

    private final static ObjectMapper DEFAULT_OBJECT_MAPPER;
    private final static ObjectMapper NOT_NULL_OBJECT_MAPPER;
    private final static ObjectMapper OBJECT_MAPPER_WITH_DATE_FORMAT;

    static {
        DEFAULT_OBJECT_MAPPER = new ObjectMapper();
        DEFAULT_OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        NOT_NULL_OBJECT_MAPPER = new ObjectMapper();
        NOT_NULL_OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        OBJECT_MAPPER_WITH_DATE_FORMAT = new ObjectMapper();
        OBJECT_MAPPER_WITH_DATE_FORMAT.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OBJECT_MAPPER_WITH_DATE_FORMAT.setDateFormat(format);
    }


    public static String toJson(Object obj) {
        return bean2Json(obj);
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        return json2Bean(json, tClass);
    }

    public static <T> T fromJson(InputStream src, Class<T> valueType) throws IOException {
        return DEFAULT_OBJECT_MAPPER.readValue(src, valueType);
    }

    /**
     * 根据json字符串返回对应java类型
     *
     * @param obj Object
     * @return String
     */
    public static String bean2Json(Object obj) {
        try {
            return DEFAULT_OBJECT_MAPPER.writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    /**
     * 根据json字符串返回对应java类型
     *
     * @param jsonStr String
     * @param cls     Class<T>
     * @return T
     */
    public static <T> T json2Bean(String jsonStr, Class<T> cls) {
        try {
            return DEFAULT_OBJECT_MAPPER.readValue(jsonStr, cls);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {

    }
}
