package com.ezmenutouch.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ezmenutouch.R;
import com.ezmenutouch.activity.DetailedDishActivity;
import com.ezmenutouch.constants.MenuConstants;
import com.ezmenutouch.fragment.MenuList;
import com.ezmenutouch.util.JasonMenuUtil;
import com.ezmenutouch.util.MenuParser;
import com.ezmenutouch.util.Util;
import com.ezmenutouch.vo.FoodItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ItemViewHolder> {


    private LayoutInflater layoutInflater;
    private static ArrayList<FoodItem> dishList = new ArrayList<FoodItem>();
    private  Context context;
    private JasonMenuUtil jasonMenuUtil = new JasonMenuUtil();
    private MenuParser menuParser = new MenuParser();
    private String dishCategory;
    private MenuList.DishSelectionListener   dishSelectionListener = null;


    public ItemViewAdapter(Context context){
        layoutInflater =   LayoutInflater.from(context);
        this.context = context;
    }

    public void setItemList(ArrayList<FoodItem> dishList){
        this.dishList = dishList;
        System.out.println("Begin: Setting food Item with size.."+dishList.size());
        for (FoodItem foodItem:dishList) {
            System.out.println("food Item.."+foodItem.getName());
        }
        System.out.println("End: Setting food Item with size.."+dishList.size());
        notifyItemRangeChanged(0, dishList.size());
    }

    public void setDishCategory(String dishCategory) {
        this.dishCategory = dishCategory;
    }

    public void setDishSelectionListener(MenuList.DishSelectionListener  dishSelectionListener){
        this.dishSelectionListener = dishSelectionListener;

    }

    public  class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView itemImage;


        public ItemViewHolder(View movieView,MenuList.DishSelectionListener  dishSelectionListener) {

            super(movieView);
            itemImage = ( ImageView )movieView.findViewById(R.id.itemImage);
            itemImage.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            Bundle itemBundleObject = new Bundle();
            FoodItem dish = dishList.get((Integer)v.getTag());
            Intent intent = null;

            if(v.getResources().getBoolean(R.bool.isTablet)){
                dishSelectionListener.setDish(dish);

            }else {
                intent = new Intent(context, DetailedDishActivity.class);
                itemBundleObject.putParcelable(MenuConstants.MOVIE_PARCELABLE_KEY, dish);
                intent.putExtras(itemBundleObject);
                context.startActivity(intent);
            }
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.dish,viewGroup,false);
        ItemViewHolder movieViewHolder = new ItemViewHolder(view,dishSelectionListener);
        return movieViewHolder;
    }


    @Override
    public void onBindViewHolder(ItemViewHolder viewHolder, int index) {
        FoodItem currentDish = dishList.get(index);
        Context context1 = viewHolder.itemImage.getContext();
        viewHolder.itemImage.setTag(index);
        if(!Util.haveNetworkConnection(context)){
            System.out.println("No Network Connection..");
        }

        if(currentDish.getImagePath() != "null" ) {
            // Hard coding the image path , currentDish.getImagePath()
            Picasso.with(context1).load(currentDish.getImagePath()).error(R.drawable.noposter).resize(250,350)
                    .into(viewHolder.itemImage);
        }else{
            // Hard coded Path
            Picasso.with(context1).load(R.drawable.noposter).error(R.drawable.noposter).resize(250,350)
                    .into(viewHolder.itemImage);
        }

    }

    @Override
    public int getItemCount() {
        return dishList.size();

    }
}
