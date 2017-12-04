package com.ezmenutouch.activity;

import android.content.Intent;
import android.database.Cursor;
import com.ezmenutouch.R;
import com.ezmenutouch.adapter.ItemViewAdapter;
import com.ezmenutouch.dao.MenuDAO;
import com.ezmenutouch.util.JasonMenuDataParser;
import com.ezmenutouch.util.OnRequestCompletedListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ezmenutouch.adapter.SportifyRecycleViewAdapter;
import com.ezmenutouch.constants.MenuConstants;
import com.ezmenutouch.util.Util;
import com.ezmenutouch.vo.Dish;
import com.ezmenutouch.vo.FoodItem;

import java.util.ArrayList;

public class MyOrders extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager  recycleLayoutManager;
    ArrayList<Dish> moviesList = null;
    MenuDAO menuDAO = null;
    Bundle movieBundle = new Bundle();
    private JasonMenuDataParser jasonMenuDataParser = new JasonMenuDataParser();
    private final String  URI = "content://com.ezmenutouch.contentprovider.MovieContentProvider/favmovies";
    private ArrayList<Dish> favDishList = new ArrayList<Dish>();
    private CursorLoader cursorLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_sportify);
        getSupportLoaderManager().initLoader(1, null, this);
        recyclerView = ( RecyclerView)findViewById(R.id.recycleView);
        recycleLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(recycleLayoutManager);
        final ItemViewAdapter itemViewAdapter = new ItemViewAdapter(this);
        recyclerView.setAdapter(itemViewAdapter);
        moviesList = null;
        if(menuDAO == null ) {
            menuDAO = new MenuDAO(getApplicationContext());
            menuDAO.open();
        }


        OnRequestCompletedListener listener = new OnRequestCompletedListener() {
            @Override
            public void onRequestCompleted(ArrayList<FoodItem> foodItemList, String moviesCategory) {
                System.out.println("##################Call Back.."+foodItemList);
                if(MenuConstants.ITEMS_MENU.equals(moviesCategory)){
                    itemViewAdapter.setItemList(foodItemList);
                    recyclerView.setHasFixedSize(true);
                }

            }
        };

        jasonMenuDataParser.makeJsonObjectRequest(MenuConstants.MENU_ITEMS_REST_URL,listener);

        String sortOrder = intent.getStringExtra(String.valueOf(R.string.sortOrder));

        // If there is no network or when you select fav movies , display Fav movies..
        if(MenuConstants.FAV_MOVIES.equals(sortOrder  )  || !Util.haveNetworkConnection(getApplicationContext())  ){
            System.out.println("Checking for Fav Movies..");
            setTitle(MenuConstants.FAV_MOVIES);
            jasonMenuDataParser.makeJsonObjectRequest(MenuConstants.MENU_ITEMS_REST_URL,listener);
            //itemViewAdapter.setItemList(favDishList);
            ///jasonMenuDataParser.makeJsonObjectRequest(MenuConstants.MENU_ITEMS_REST_URL,null);
            /// recyclerView.setHasFixedSize(true);
        } else if(null == sortOrder){// if there is no option selcted, show Popular Movies
            setTitle(MenuConstants.POPULAR_MOVIES);
            //jasonMenuDataParser.makeJsonObjectRequest(MenuConstants.MOST_POPULAR_MOVIES_URL, listener);
            jasonMenuDataParser.makeJsonObjectRequest(MenuConstants.MENU_ITEMS_REST_URL,listener);
        }
        else{

            /*// if the selected option is either Popular or high rated and if there is network connectivity this condition executes.
            setTitle(Html.fromHtml(sortOrder));
            if(MenuConstants.POPULAR_MOVIES.equals(sortOrder)) {
                jasonMenuDataParser.makeJsonObjectRequest(MenuConstants.MOST_POPULAR_MOVIES_URL, listener);
            }else{
                jasonMenuDataParser.makeJsonObjectRequest(MenuConstants.HIGH_RATED_MOVIES_URL, listener);
            }*/
            jasonMenuDataParser.makeJsonObjectRequest(MenuConstants.MENU_ITEMS_REST_URL,listener);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater Inflater = getMenuInflater();
        Inflater.inflate(R.menu.menu_sportify_steamer,menu);
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
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //cursorLoader= new CursorLoader(context, Uri.parse("content://com.example.contentproviderexample.MyProvider/cte"), null, null, null, null);
        cursorLoader= new CursorLoader(getApplicationContext(), Uri.parse(URI), null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Dish dish = Util.cursorToFavMovie(cursor);
            favDishList.add(dish);
            cursor.moveToNext();
        }
        cursor.close();


    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
