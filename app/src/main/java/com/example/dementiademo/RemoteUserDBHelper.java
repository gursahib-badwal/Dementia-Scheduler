package com.example.dementiademo;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RemoteUserDBHelper {

    public static MyUser currentUser;

    public void postUser(String username, String password) {
        try {
            URL url = new URL(String.format("http://williamoverflow.com:8080/addUser?username=%s&password=%s",
                    username, password));

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.connect();

            Object obj = conn.getContent(); //not necessary, in case if you want status code,
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean tryLogin(String username, String password) {
        //TODO: Consider use code to identify reasons later.
        try {
            URL url = new URL(String.format("http://williamoverflow.com:8080/login?username=%s&password=%s",
                    username, password));


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream is = url.openStream();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is));
            String res = "";
            String line;
            while((line = br.readLine()) != null)
                res += line;

            conn.disconnect();
            return res.equals("1");   //Horo's efficient way of login status
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String queryUserByUsername(String username){
        try {
            URL url = new URL(String.format("http://williamoverflow.com:8080/user/%s",
                    username));

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream is = url.openStream();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is));
            String res = "";
            String line;
            while((line = br.readLine()) != null)
                res += line;

            System.out.println(res);
            currentUser = MyUser.getUserByJson(res);

            conn.disconnect();
            return res;
        } catch (IOException e) {
            currentUser = null;
            e.printStackTrace();
        }
        return "";
    }


}
