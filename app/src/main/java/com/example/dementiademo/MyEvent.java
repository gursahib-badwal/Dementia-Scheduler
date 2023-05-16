package com.example.dementiademo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MyEvent {
    public String uuid;
    public Integer event_type;
    public java.sql.Timestamp event_time;
    public String title;
    public String description;
    public String to_user;
    public String from_user;

    public static List<MyEvent> getEventsFromJson(String json) {
        Type lUType = new TypeToken<List<MyEvent>>() {
        }.getType();
        Gson gson = new Gson();
        return gson.fromJson(json, lUType);
    }

}
