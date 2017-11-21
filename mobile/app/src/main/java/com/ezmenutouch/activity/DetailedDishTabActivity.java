package com.ezmenutouch.activity;

import android.content.Intent;
import com.ezmenutouch.R;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ezmenutouch.constants.MenuConstants;
import com.ezmenutouch.fragment.DetailedMenuFragment;
import com.ezmenutouch.fragment.ItemReviews;
import com.ezmenutouch.fragment.ItemVideo;
import com.ezmenutouch.fragment.MenuList;
import com.ezmenutouch.fragment.VideoFragment;
import com.ezmenutouch.fragment.VideoFragment;
import com.ezmenutouch.vo.Dish;

import java.util.ArrayList;

public class DetailedDishTabActivity extends FragmentActivity implements MenuList.OnFragmentInteractionListener,DetailedMenuFragment.OnFragmentInteractionListener,ItemVideo.OnFragmentInteractionListener,ItemReviews.OnFragmentInteractionListener, VideoFragment.OnFragmentInteractionListener,MenuList.MovieSelectionListener{

    Bundle movieBundle = new Bundle();
    Dish dish = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportify_tab);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater Inflater = getMenuInflater();
        Inflater.inflate(R.menu.menu_sportify_steamer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.popular:
                Intent i = new Intent(this,MenuActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(i, 0);
                overridePendingTransition(0,0);
                break;
            }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void setDish(Dish dish) {
        this.dish = dish;
        DetailedMenuFragment detailedMenuFragment = (DetailedMenuFragment)getSupportFragmentManager().findFragmentById(R.id.detailedMovieFragment);
        // detailedMenuFragment.updateMovieInfo(dish);


    }
}
