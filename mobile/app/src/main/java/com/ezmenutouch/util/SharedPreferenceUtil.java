package com.ezmenutouch.util;

import android.content.Context;
import android.content.SharedPreferences;


import com.ezmenutouch.constants.AppConstants;





public class SharedPreferenceUtil {


    private static final String TAG = "SharedPreferenceUtil";






    private SharedPreferences sharedPreferences = null;



    public SharedPreferenceUtil(SharedPreferences sharedPreferences, boolean b){
        this.sharedPreferences = sharedPreferences;
    }



    public void setDataFromSharedPreference(String key, String data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, data);
        editor.commit();
    }



    /**
     * Gives the data from a given  key, use this method only for getting string data.
     * @return
     */
    public String getDataFromSharedPreference(String key){
        return  sharedPreferences.getString(key, AppConstants.NO_DATA);
    }






























    /**
     * Used for getting the string shared preference.
     * @param contextName
     * @param sharedPrefName
     * @param fieldName
     * @param defaultValue
     * @return
     */
    public static String getSharedPreferenceString(Context contextName, String sharedPrefName, String fieldName, String defaultValue)
    {
        SharedPreferences sharedPreferences = contextName.getSharedPreferences(sharedPrefName,Context.MODE_PRIVATE);
        return sharedPreferences.getString(fieldName, defaultValue);
    }

    /**
     * Used for writing the string to the shared preference.
     * @param contextName
     * @param sharedPrefName
     * @param fieldName
     * @param fieldvalue
     */
    public static void setSharedPreferenceString(Context contextName,String sharedPrefName,String fieldName,String fieldvalue )
    {
        SharedPreferences sp = contextName.getSharedPreferences(sharedPrefName,Context.MODE_PRIVATE);
        SharedPreferences.Editor toEdit=sp.edit();
        toEdit.putString(fieldName,fieldvalue);
        toEdit.commit();
    }


}
