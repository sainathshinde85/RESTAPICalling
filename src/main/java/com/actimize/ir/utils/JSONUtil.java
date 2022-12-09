package com.actimize.ir.utils;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class JSONUtil {

    public JSONUtil(){

    }

    private static String parseObjectToJsonString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
    public static String convertObjectToJson(Object object){
        String jsonObject = new Gson().toJson(object);
        return jsonObject;
    }
}
