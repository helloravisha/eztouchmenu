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
import com.ezmenutouch.vo.Dish;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class SportifyRecycleViewAdapter extends RecyclerView.Adapter<SportifyRecycleViewAdapter.MovieViewHolder> {


    private LayoutInflater layoutInflater;
    private static ArrayList<Dish> dishList = new ArrayList<Dish>();
    private  Context context;
    private String movieCategory;
    private MenuList.MovieSelectionListener  movieSelectionListener = null;


    public SportifyRecycleViewAdapter(Context context){
        layoutInflater =   LayoutInflater.from(context);
        this.context = context;
    }

    public void setMovieList(ArrayList<Dish> dishList){
        this.dishList = dishList;
        notifyItemRangeChanged(0, dishList.size());
    }

    public void setMovieCategory(String movieCategory) {
        this.movieCategory = movieCategory;
    }

    public void setMovieSelectionListener(MenuList.MovieSelectionListener  movieSelectionListener){
        this.movieSelectionListener = movieSelectionListener;

    }

    public  class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView movieImage;


        public MovieViewHolder(View movieView,MenuList.MovieSelectionListener  movieSelectionListener) {

            super(movieView);
            movieImage = ( ImageView )movieView.findViewById(R.id.itemImage);
            movieImage.setOnClickListener(this);

        }


       @Override
       public void onClick(View v) {
           Bundle movieBundleObject = new Bundle();
           Dish dish = dishList.get((Integer)v.getTag());
           Intent intent = null;

           if(v.getResources().getBoolean(R.bool.isTablet)){
               movieSelectionListener.setDish(dish);

           }else {
                intent = new Intent(context, DetailedDishActivity.class);
               movieBundleObject.putParcelable(MenuConstants.MOVIE_PARCELABLE_KEY, dish);
               intent.putExtras(movieBundleObject);
               context.startActivity(intent);
           }
       }
   }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.dish,viewGroup,false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view,movieSelectionListener);
        return movieViewHolder;
    }


    @Override
    public void onBindViewHolder(MovieViewHolder viewHolder, int index) {
        Dish currentDish = dishList.get(index);
        Context context1 = viewHolder.movieImage.getContext();
        viewHolder.movieImage.setTag(index);
        if(!Util.haveNetworkConnection(context)){
            System.out.println("No Network Connection..");
        }

        if(currentDish.getBackdropPath() != null ) {
            Picasso.with(context1).load(currentDish.getBackdropPath()).error(R.drawable.noposter).resize(250,350)
                    .into(viewHolder.movieImage);
        }

    }

    @Override
    public int getItemCount() {
        return dishList.size();

    }
}
