package com.ezmenutouch.util;

import android.widget.TextView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ezmenutouch.constants.MenuAttributes;
import com.ezmenutouch.constants.MenuConstants;
import com.ezmenutouch.vo.Dish;
import com.ezmenutouch.vo.FoodItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.volley.Request.Method.GET;



public class JasonMenuDataParser {


    private ArrayList<FoodItem> foodItemList =  null;


    public void makeJsonObjectRequest(final String URL,final OnRequestCompletedListener requestListener) {

      System.out.println("Make Jason Request..."+URL);
        Listener listener = new Listener() {
            @Override
            public void onResponse(Object response) {
                try {
                    System.out.println("Making  onResponse request..");
                    if(URL.contains(MenuConstants.MENU_ITEMS_REST_URL)) {
                        System.out.println("Entered menu items  parsing..");
                        foodItemList = new ArrayList<>();
                        foodItemList = parseItemObject(response);
                        System.out.println("Making  menu request..");
                        requestListener.onRequestCompleted(foodItemList, MenuConstants.ITEMS_MENU);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }


        };

        ErrorListener errorListener = new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error while parsing response.."+URL);
                System.out.println("Error while parsing response.."+error);
            }
        };

        System.out.println("Make Volley API Request..."+URL);
       //JsonObjectRequest jsonObjReq = new JsonObjectRequest(GET, URL, (String) null, listener, errorListener);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(GET, URL, (String) null, listener, errorListener);
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        System.out.println("End make  Volley API Request..."+URL);

    }


    private ArrayList<FoodItem> parseItemObject(Object response) throws Exception {
        ArrayList<FoodItem> foodItemList = new ArrayList<FoodItem>();
        JSONArray jSONArray = (JSONArray) response;
        System.out.println("Menu Object.."+jSONArray);
        for (int i = 0; i < jSONArray.length(); i++) {
            FoodItem foodItem = new FoodItem();
            JSONObject itemObject = (JSONObject) jSONArray.get(i);
            foodItem.setId(itemObject.getString(MenuAttributes.ITEM_ID));
            foodItem.setName(itemObject.getString(MenuAttributes.ITEM_NAME));
            foodItem.setDescription(itemObject.getString(MenuAttributes.ITEM_DESCRIPTION));
            foodItem.setImagePath(itemObject.getString(MenuAttributes.ITEM_IMAGE_PATH));
            foodItem.setPrice(itemObject.getString(MenuAttributes.ITEM_PRICE));
            foodItemList.add(foodItem);
        }
        return foodItemList;
    }




}






