package com.ezmenutouch.util;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ezmenutouch.constants.DashBoardConstants;
import com.ezmenutouch.constants.MenuAttributes;
import com.ezmenutouch.constants.MenuConstants;
import com.ezmenutouch.vo.FoodItem;
import com.ezmenutouch.vo.OrderItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.Request.Method.GET;


public class JasonMenuDataParser {


    private ArrayList<FoodItem> foodItemList = null;


    public void makeJsonObjectRequest(final String URL, final OnRequestCompletedListener requestListener) {

        System.out.println("Make Jason Request..." + URL);
        Listener listener = new Listener() {
            @Override
            public void onResponse(Object response) {
                try {
                    System.out.println("Making  onResponse request..");
                    if (URL.contains(MenuConstants.MENU_ITEMS_REST_URL)) {
                        System.out.println("Entered menu items  parsing..");
                        foodItemList = new ArrayList<>();
                        //foodItemList = parseItemObject(response);
                        foodItemList = parseItemCateObject(response);
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
                System.out.println("Error while parsing response.." + URL);
                System.out.println("Error while parsing response.." + error);
            }
        };

        System.out.println("Make Volley API Request..." + URL);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(GET, URL, (String) null, listener, errorListener);

        //JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(GET, URL, (String) null, listener, errorListener);
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        System.out.println("End make  Volley API Request..." + URL);

    }


    public static void getOrdersStatus(String tableName, final Activity activity) {


        Listener listener = new Listener() {
            @Override
            public void onResponse(Object response) {
                try {

                    String orderStatus = parseOrderObject(response);

                    AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                    dialog.setCancelable(false);
                    dialog.setTitle("Status");

                    if("no".equals(orderStatus)){
                        dialog.setMessage("No orders placed for this Table.");

                    }else {
                        dialog.setMessage("Your order is " + orderStatus);
                    }
                    dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {



                            // JasonMenuDataParser.placeOrder("ravisha");
                        }
                    });
                    final AlertDialog alert = dialog.create();
                    alert.show();

                    // Show your order status here.
                    // requestListener.onRequestCompleted(foodItemList, MenuConstants.ITEMS_MENU);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }


        };

        ErrorListener errorListener = new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error while parsing response..");
                System.out.println("Error while parsing response.." + error);
            }
        };

        System.out.println("Make Volley API Request...");
        //JsonObjectRequest jsonObjReq = new JsonObjectRequest(GET, URL, (String) null, listener, errorListener);

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(GET, MenuConstants.ORDER_ITEMS_STATUS_REST_URL + tableName, (String) null, listener, errorListener);
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        System.out.println("End make  Volley API Request...");


    }


    // {"id":123,"tableName":"444","totalPrice":"23.0","orderDate":"2017.11.30.01.14.11","orderStatus":"New","orderedItems":"77,79,7"}]

    private static String parseOrderObject(Object response) throws Exception {
        if(null == response ){
            return "no";
        }else {
            JSONObject jsonObject = (JSONObject) response;
            return jsonObject.getString("orderStatus");
        }
    }


    public static void placeOrder(String tableName, String price, String orderedItems, String orderStatus, String orderDate) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("tableName", tableName);
        params.put("totalPrice", price);
        params.put("orderedItems", orderedItems);
        params.put("orderStatus", orderStatus);
        params.put("orderDate", orderDate);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, MenuConstants.ORDER_ITEMS_REST_URL, new JSONObject(params),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("response -->> " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("change Pass response -->> " + error.toString());
                    }
                });

        request.setRetryPolicy(new

                DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        AppController.getInstance().addToRequestQueue(request);
    }

    private ArrayList<FoodItem> parseItemCateObject(Object response) throws Exception {
        ArrayList<FoodItem> foodItemList = new ArrayList<FoodItem>();
        JSONObject jsonObject = (JSONObject) response;
        JSONArray jSONArray = (JSONArray) jsonObject.getJSONArray("fooditems");
        System.out.println("Menu Object.." + jSONArray);
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


    private ArrayList<FoodItem> parseItemObject(Object response) throws Exception {
        ArrayList<FoodItem> foodItemList = new ArrayList<FoodItem>();
        JSONArray jSONArray = (JSONArray) response;
        System.out.println("Menu Object.." + jSONArray);
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






