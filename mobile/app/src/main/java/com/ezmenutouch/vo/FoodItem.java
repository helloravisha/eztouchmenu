package com.ezmenutouch.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Parcelable movie class for holding all te movie information.
 * Created by ravisha
 */
public class FoodItem implements Parcelable{
    String id;
    String name;
    String description;
    String price;
    String imagePath;

    public FoodItem(Parcel input){
        id = input.readString();
        name = input.readString();
        description = input.readString();
        price = input.readString();
        imagePath = input.readString();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(price);
        dest.writeString(imagePath);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }



    public FoodItem(){

    }








    @Override
    public int describeContents() {
        return 0;
    }



    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };
}
