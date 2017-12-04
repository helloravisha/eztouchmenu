package com.ezmenutouch.util;

import com.ezmenutouch.vo.FoodItem;

import java.util.ArrayList;


public interface OnRequestCompletedListener {
    void onRequestCompleted(ArrayList<FoodItem> moviesList, String moviesCategory);
}
