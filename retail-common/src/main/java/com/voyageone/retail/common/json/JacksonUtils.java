package com.voyageone.retail.common.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voyageone.retail.common.exception.RetailException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * json转换工具类
 */
public class JacksonUtils {


    private static final ObjectMapper DEFAULT_OBJECT_MAPPER = new ObjectMapper();

    public JacksonUtils() {
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
        } catch (IOException var2) {
            throw new RetailException(var2.getMessage(), var2);
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
        } catch (IOException var3) {
            throw new RetailException(var3.getMessage(), var3);
        }
    }

    /**
     * 根据json字符串返回对应java类型List
     *
     * @param jsonString String
     * @param cls        Class
     * @return List
     */
    public static <T> List<T> jsonToBeanList(String jsonString, Class<T> cls) {
        JavaType javaType = DEFAULT_OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, cls);
        try {
            return DEFAULT_OBJECT_MAPPER.readValue(jsonString, javaType);
        } catch (IOException e) {
            throw new RetailException(e);
        }
    }


    /**
     * 根据json字符串返回对应Map类型List
     *
     * @param jsonString String
     * @return List
     */
    public static List<Map<String, Object>> jsonToMapList(String jsonString) {
        JavaType javaType = DEFAULT_OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, Map.class);
        try {
            return DEFAULT_OBJECT_MAPPER.readValue(jsonString, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
