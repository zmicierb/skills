package com.barysevich.project.utils;


import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SerializationUtils
{
    public static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules()
            .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false)
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static final TypeReference<Map<String, String>> MAP_TYPE_REF = new TypeReference<Map<String, String>>()
    {
    };


    public static <T> String toJson(final T object)
    {
        try
        {
            return MAPPER.writeValueAsString(object);
        }
        catch (final IOException e)
        {
            throw new IllegalArgumentException("Can't serialize object [" + object + "]", e);
        }
    }


    public static <T> T fromJson(final String content, final Class<T> clazz)
    {
        try
        {
            return MAPPER.readValue(content, clazz);
        }
        catch (final IOException e)
        {
            throw new IllegalArgumentException("Can't deserialize data [" + content + "]", e);
        }
    }


    public static <T> T fromJson(final String content, final JavaType javaType)
    {
        try
        {
            return MAPPER.readValue(content, javaType);
        }
        catch (final IOException e)
        {
            throw new IllegalArgumentException("Can't deserialize data [" + content + "]", e);
        }
    }


    public static <T> T fromJson(final String content, final TypeReference<T> typeReference)
    {
        try
        {
            return MAPPER.readValue(content, typeReference);
        }
        catch (final IOException e)
        {
            throw new IllegalArgumentException("Can't deserialize data [" + content + "]", e);
        }
    }


    public static <T> T fromJsonNode(final JsonNode jsonNode, final Class<T> clazz)
    {
        try
        {
            return MAPPER.treeToValue(jsonNode, clazz);
        }
        catch (final IOException e)
        {
            throw new IllegalArgumentException("Can't deserialize data [" + jsonNode + "]", e);
        }
    }


    public static JsonNode readTree(final String content)
    {
        try
        {
            return MAPPER.readTree(content);
        }
        catch (IOException e)
        {
            throw new IllegalArgumentException("Can't read json tree [" + content + "]", e);
        }
    }


    public static Map<String, String> toMap(final Object data)
    {
        try
        {
            return MAPPER.convertValue(data, MAP_TYPE_REF);
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Can't serialize object [" + data + "]", e);
        }
    }


    public static <T> T fromMap(final Map<String, String> map, final Class<T> type)
    {
        try
        {
            return MAPPER.convertValue(map, type);
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Can't deserialize map " + map, e);
        }
    }


    /**
     * Вытаскивает данные из мапы, собирает поджик и собирает мапу без использованных полей.
     *
     * @param map  Мапа с данными
     * @param type Тип класса.
     * @param <T>  Класс
     * @return Полученные объект и мапу без заиспользованных полей.
     */
    public static <T> Map.Entry<T, Map<String, String>> extractDataAndCleanMap(final Map<String, String> map,
                                                                               final Class<T> type)
    {
        final T data = fromMap(map, type);

        final Map<String, String> cleanMap = buildLowercaseKeyMap(map);
        final Map<String, String> dataMap = buildLowercaseKeyMap(toMap(data));

        cleanMap.keySet().removeAll(dataMap.keySet());

        return new AbstractMap.SimpleEntry<>(data, cleanMap);
    }


    /**
     * Очищает мапу от элементов, которые есть в POJO
     *
     * @param data Данные
     * @param map  Мапа
     * @param <T>  Тип данных
     * @return Очищенная мапа.
     */
    public static <T> Map<String, String> cleanMap(final T data, final Map<String, String> map)
    {
        final Map<String, String> cleanMap = buildLowercaseKeyMap(map);
        final Map<String, String> dataMap = buildLowercaseKeyMap(toMap(data));

        cleanMap.keySet().removeAll(dataMap.keySet());

        return cleanMap;
    }


    private static Map<String, String> buildLowercaseKeyMap(final Map<String, String> map)
    {
        return map.entrySet().stream()
                .collect(HashMap::new, (m,v)->m.put(v.getKey().toLowerCase(), v.getValue()), HashMap::putAll);
    }
}
