package com.lxm.ss.ssc.Utils;//package club.fromfactory.Utils;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonSyntaxException;
//import com.google.gson.reflect.TypeToken;
//
//import club.fromfactory.modle.CategorysDeserializer;
//import club.fromfactory.modle.SortFilter;
//import club.fromfactory.modle.SortFilterDeserializer;
//import club.fromfactory.modle.SubCategoryDeserializer;
//
///**
// * Created by lxm on 2016/11/18.
// */
//
//public class GsonUtils<T> {
//
//    private static GsonUtils gsonUtils;
//    private Gson gson = new Gson();
//
//    public static GsonUtils getInstance() {
//        if (gsonUtils == null) {
//            gsonUtils = new GsonUtils();
//        }
//        return gsonUtils;
//    }
//
//    private Gson setJsonDeserializer() {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(SortFilter.class, new CategorysDeserializer());
//        gsonBuilder.registerTypeAdapter(SortFilter.class, new SubCategoryDeserializer());
//        gsonBuilder.registerTypeAdapter(SortFilter.class, new SortFilterDeserializer());
//        gson = gsonBuilder.create();
//
//        return gson;
//    }
//
//    public T parseJson(String message, TypeToken<T> typeToken) {
//        T t = null;
//        try {
//            t = gson.fromJson(message, typeToken.getType());
//        } catch (JsonSyntaxException e) {
//            e.printStackTrace();
//        }
//        if (t != null) {
//            Zlog.ii("lxm volley parseJson :" + t.toString());
//        }
//        return t;
//    }
//
//    public T parseJsonWithDeserializer(String message, TypeToken<T> typeToken) {
//        Zlog.ii("lxm ss volley parseJson:" + message);
//        Gson gson = setJsonDeserializer();
//        T t = null;
//        try {
//            t = gson.fromJson(message, typeToken.getType());
//        } catch (JsonSyntaxException e) {
//            e.printStackTrace();
//        }
//        if (t != null) {
//            Zlog.ii("lxm volley parseJson :" + t.toString());
//        }
//        return t;
//    }
//
//    public String toJson(Object o, TypeToken<T> typeToken) {
//        gson = new Gson();
//        String json = null;
//        try {
//            json = gson.toJson(o, typeToken.getType());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (json != null) {
//            Zlog.ii("lxm volley toJson :" + json);
//        }
//        return json;
//    }
//}
