package com.jonoon.clubapp.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by runzhang.han on 2015/1/5 15:17.
 */
public class GsonHelper {
	
    public static <T> T readJsonObject(String json, java.lang.Class<T> target){

        T data = null;
        try {
            Gson gson = new Gson();
            data = gson.fromJson(json,target);
        }catch (JsonSyntaxException exception){
            exception.printStackTrace();
        }
        return data;
    }
//
//    public static  <T> String toJsonString(java.util.ArrayList<T> target){
//
//        String data = null;
//        try {
//            Gson gson = new Gson();
//            data=gson.toJson(target);
//        }catch (JsonSyntaxException exception){
//            exception.printStackTrace();
//        }
//        return data;
//    }

    public static  <T> String toJsonString(java.lang.Class<T>  target){

        String data = null;
        try {
            Gson gson = new Gson();
            data=gson.toJson(target);
        }catch (JsonSyntaxException exception){
            exception.printStackTrace();
        }
        return data;
    }
    
    public static <T> ArrayList<T> fromJsonObject(String json, Class<T> cls ){

        ArrayList<T> data = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<T>>(){}.getType();
            data = gson.fromJson(json,listType);
        }catch (JsonSyntaxException exception){
            exception.printStackTrace();
        }
        L.e("GsonHelper", data.size()+"");
        return data;
    }
    
   
	
}
