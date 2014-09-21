package com.sv.swivl.app.loader;

import android.util.Log;
import com.sv.swivl.app.model.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class UsersRequestQueryBuilder {

    private static final String URL = "https://api.github.com/users";
    private static final String GET_PARAMETR = "?since=";

    private ArrayList<User> users;

    public UsersRequestQueryBuilder() {
        users = new ArrayList<User>();
    }

    private JSONArray getJson(int i) throws IOException{
        //Create request
        String url = URL + GET_PARAMETR + i;
        HttpGet httpRequest = new HttpGet(url);

        //Execute request
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpRequest);

        //Get result
        JSONArray rawResponse = null;
        try {
            rawResponse = new JSONArray(EntityUtils.toString(httpResponse.getEntity()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        httpClient.getConnectionManager().closeExpiredConnections();

        return rawResponse;
    }

    private ArrayList<User> parseJson(JSONArray json) {
        try {
            if (json.length() == 0) {
                return users;
            }

            User user;
            for(int i=0; i < json.length(); i++) {
                String login = json.getJSONObject(i).getString("login");
                String htmlURL = json.getJSONObject(i).getString("html_url");

                user = new User(login, htmlURL);
                users.add(user);
            }

        } catch (JSONException jsonException) {
            Log.d("debug", jsonException.getMessage());
        }

        return users;
    }

    public ArrayList<User> getUsers(int i) throws IOException {
        JSONArray json = getJson(i);
        return parseJson(json);
    }
}
