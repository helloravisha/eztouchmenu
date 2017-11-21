package com.ezmenutouch.util;

import android.widget.TextView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ezmenutouch.vo.Dish;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.volley.Request.Method.GET;


/**
 * Jason parser for making webservice request and parsing the jason response.
 * Created by ravisha.
 */
public class JasonMenuUtil {

    private String jsonResponse;
    private TextView txtResponse;
    private ArrayList<Dish> popularMovies = null;
    private JSONObject jsonObject = null;
    private ArrayList<Dish> highRatedMovies = null;

    public void makeJsonObjectRequest(final String URL,final OnRequestCompletedGenericListener genericListenerJason) {


        Listener listener = new Listener() {
            @Override
            public void onResponse(Object response) {
                try {
                    jsonObject =   parseJasonObject(response);
                    genericListenerJason.onRequestCompleted(jsonObject, URL);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }


        };

        ErrorListener errorListener = new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(GET, URL, (String) null, listener, errorListener);
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }


    private JSONObject parseJasonObject(Object response) throws Exception {
        ArrayList<Dish> movies = new ArrayList<Dish>();
        JSONObject jsonObject = (JSONObject) response;
        return jsonObject;
    }
}






