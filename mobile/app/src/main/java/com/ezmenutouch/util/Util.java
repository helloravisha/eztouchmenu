package com.ezmenutouch.util;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ezmenutouch.vo.Dish;


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


    public static Dish cursorToFavMovie(Cursor cursor) {
        Dish dish = new Dish();
        dish.setMovieId(cursor.getInt(0));
        dish.setLanguage(cursor.getString(6));
        dish.setPopularity(cursor.getDouble(8));
        dish.setBackdropPath(cursor.getString(7));
        dish.setVoteCount(cursor.getInt(5));
        dish.setVoteAverage(cursor.getDouble(4));
        dish.setTitle(cursor.getString(1));
        dish.setReleaseDate(cursor.getString(2));
        dish.setOverview(cursor.getString(3));
        return dish;
    }
}
