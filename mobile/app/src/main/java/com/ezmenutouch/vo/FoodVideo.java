package com.ezmenutouch.vo;

import android.graphics.Bitmap;

/**
 * Created by ravisha.
 */
public class FoodVideo {
    String title;
    Bitmap image;

    public FoodVideo(Bitmap image,String title){
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
