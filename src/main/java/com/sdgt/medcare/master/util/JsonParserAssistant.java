package com.sdgt.medcare.master.util;

import com.core.exceptions.DataException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Json parser Assistant.
 *
 * <p>SD Global Technologies.
 * Copyright (c) 2019. All rights reserved.</p>
 *
 * @author Karthik Chandra
 */
@Component
public class JsonParserAssistant {
    private static Logger logger = LoggerFactory.getLogger(JsonParserAssistant.class);

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Parse json string to given class type.
     *
     * @param payloadJson actual json data.
     * @param busClass the class type to which the json data that has to be parsed.
     * @param <T> class.
     * @return the object instance of specified class.
     */
    public static <T> T parse(String payloadJson, Class<T> busClass) {
        final ObjectMapper om = new ObjectMapper();
        try {
            JsonNode rootNode = om.readTree(payloadJson);
            JsonNode entityNode = rootNode.path("entity");
            if (entityNode != null) {
                if (entityNode.isMissingNode()) {
                    return om.readValue(rootNode.toString(), busClass);
                }
                return om.readValue(entityNode.toString(), busClass);
            }
        } catch (IOException e) {
            throw new DataException("JSON Parsing Error  " + payloadJson, e);
        }

        logger.error("Error in json parse. {}", payloadJson);
        return null;
    }

    /**
     * Parse json string containing list of given class type.
     *
     * @param payloadJson actual json data which is a list.
     * @param busClass the class type to which the json data that has to be parsed.
     * @param <T> class.
     * @return the object instance of specified class.
     */
    public static <T> List<T> parseList(String payloadJson, Class<T> busClass) {
        final JsonArray jsonArray = JsonParserAssistant.populateArrayFromObject(payloadJson);
        if (jsonArray == null) {
            //TODO logger
            return Collections.emptyList();
        }

        List<T> tList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i ++) {
            tList.add(JsonParserAssistant.parse(jsonArray.get(i).getAsString(), busClass));
        }
        return tList;

    }

    /**
     * Get {@link JsonObject} from given jason string.
     *
     * @param jsonString a valid json string.
     * @return {@link JsonObject} object.
     */
    public static JsonObject getJsonObject(String jsonString) {
        final JsonParser parser = new JsonParser();
        return parser.parse(jsonString).getAsJsonObject();
    }

    /**
     * Gets Json string from given POJO.
     *
     * @param t   object of generic type {@link T}
     * @param <T> generic class
     * @return a valid json string.
     * @throws JsonProcessingException if unable to parse POJO to Json string.
     */
    public static <T> String getJsonString(final T t) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException:Message: {}", e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Parse {@link String} holding json data to {@link JsonArray}
     *
     * @param object a valid json string.
     * @return a {@link JsonArray} object.
     */
    public static JsonArray populateArrayFromObject(final String object) {
        final JsonParser parser = new JsonParser();
        final JsonElement jsonElement = parser.parse(object);
        return jsonElement.getAsJsonArray();
    }

    public String readAsValueAsString(String json, String attribute) {
        // TODO Auto-generated method stub

        try {
            if (json.contains(attribute)) {
                return objectMapper.readTree(json).get(attribute).asText();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            new DataException("JSON Parsing Error  " + json, e);
        }
        return null;
    }
}
