package com.cmw.tools.json;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class JacksonUtil {

    static Logger log= LoggerFactory.getLogger(JacksonUtil.class);
    /**
     * 将对象转换成json格式
     *
     * @param ts
     * @return
     */
    public static String objectToJson(Object ts) throws ErrorException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return mapper.writeValueAsString(ts);
        } catch (JsonProcessingException e) {
            throw new ErrorException(e.getMessage());
        }
    }

    /**
     * 将对象转换成json格式空值保留
     *
     * @param ts
     * @return
     */
    public static String objectToJsonNull(Object ts) throws ErrorException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(ts);
        } catch (JsonProcessingException e) {
            throw new ErrorException(e.getMessage());
        }
    }

    /**
     * 将对象转换成json格式(并自定义日期格式)
     *
     * @param ts
     * @return
     */

    public static String objectToJsonDateSerializer(Object ts, String dateformat) throws ErrorException {
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat(dateformat);
        mapper.setDateFormat(df);

        try {
            return mapper.writeValueAsString(ts);
        } catch (JsonProcessingException e) {
            throw new ErrorException(e.getMessage());
        }

    }

    /**
     * 将json格式转换成list对象，并准确指定类型
     *
     * @param jsonStr
     * @return
     */
    public static List jsonToList(String jsonStr, Class c) throws ErrorException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, c);
        try {
            return mapper.readValue(jsonStr, listType);
        } catch (IOException e) {
            throw new ErrorException(e.getMessage());
        }
    }

    private static <T> T fromJson(String jsonStr, Class<T> c) throws ErrorException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonStr, c);
        } catch (IOException e) {
            throw new ErrorException(e.getMessage());
        }
    }

//    /**
//     * 将json格式转换成map对象
//     *
//     * @param jsonStr
//     * @return
//     */
//    public static Map<?, ?> jsonToMap(String jsonStr) throws ErrorException {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            return mapper.readValue(jsonStr, LinkedHashMap.class);
//        } catch (IOException e) {
//            throw new ErrorException(e.getMessage());
//        }
//    }

    /**
     * 将json转换成bean对象
     *
     * @param jsonStr
     * @return
     * @throws ErrorException 
     */
    public static <T> T jsonToBean(String jsonStr, Class<T> cl) throws ErrorException {
        return fromJson(jsonStr, cl);
    }


}