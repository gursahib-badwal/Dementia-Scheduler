package com.example.dementiademo;


import org.json.JSONException;
import org.json.JSONObject;

public class


MyUser {
    public String uuid;
    public String username;
//    public String password;
    public String user_type;
    public String first_name;
    public String last_name;
    public String gender;
//        public Date birthday;
    public String description;

//    public static MyUser getUserFromJson(String json){
//        MyUser user = new MyUser();
//        user.uuid =
//    }

    public static MyUser getUserByJson(String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MyUser myUser = new MyUser();

        myUser.uuid = jsonObject.optString("uuid", "");
        myUser.username = jsonObject.optString("username", "");
        myUser.user_type = jsonObject.optString("user_type", "");
        myUser.first_name = jsonObject.optString("first_name", "");
        myUser.last_name = jsonObject.optString("last_name", "");
        myUser.gender = jsonObject.optString("gender", "");
        myUser.description = jsonObject.optString("description", "");
        return myUser;
    }
}
