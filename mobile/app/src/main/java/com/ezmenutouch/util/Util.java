package com.ezmenutouch.util;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ezmenutouch.vo.Dish;
import com.ezmenutouch.vo.OrderItem;


public class Util {

    public static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


    public static  OrderItem cursorToFavOrder(Cursor cursor) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(cursor.getString(0));
        orderItem.setTableName(cursor.getString(1));
        orderItem.setOrderdate(cursor.getString(2));
        orderItem.setItemPrice(cursor.getString(3));
        orderItem.setOrderStatus(cursor.getString(4));
        orderItem.setItemName(cursor.getString(5));


        return orderItem;
    }
}
