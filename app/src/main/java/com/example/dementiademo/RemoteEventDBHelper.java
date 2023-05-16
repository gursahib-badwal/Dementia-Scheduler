package com.example.dementiademo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RemoteEventDBHelper {

    public static void postEvent(MyEvent myEvent) {
        try {
            URL url = new URL(String.format("http://williamoverflow.com:8080/addEvent?event_type=%s&event_time=%s&title=%s&description=%s&to_user=%s&from_user=%s",
                    myEvent.event_type, myEvent.event_time, myEvent.title, myEvent.description, myEvent.to_user, myEvent.from_user));

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.connect();

            Object obj = conn.getContent(); //not necessary, in case if you want status code,
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static List<MyEvent> queryEventsByToUsername(String username){
        try {
            URL url = new URL(String.format("http://williamoverflow.com:8080/event/%s",
                    username));

            List<MyEvent> l_res = new ArrayList<>();
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

            l_res = MyEvent.getEventsFromJson(res);

            conn.disconnect();
            return l_res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
