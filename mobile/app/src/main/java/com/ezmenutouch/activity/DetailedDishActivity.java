package com.ezmenutouch.activity;

import android.content.Intent;
import android.database.Cursor;
import com.ezmenutouch.R;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ShareActionProvider;

import com.ezmenutouch.adapter.CustomGridViewAdapter;
import com.ezmenutouch.fragment.ItemReviews;
import com.ezmenutouch.fragment.ItemVideo;
import com.ezmenutouch.util.JasonMenuUtil;
import com.ezmenutouch.util.MenuParser;
import com.ezmenutouch.util.Util;
import com.ezmenutouch.vo.Dish;
import com.ezmenutouch.vo.FoodVideo;
import com.ezmenutouch.vo.OrderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for holding the detailed dish information, this will get triggered in the case of mobile.
 */
public class DetailedDishActivity extends FragmentActivity implements ItemVideo.OnFragmentInteractionListener,ItemReviews.OnFragmentInteractionListener, com.ezmenutouch.fragment.VideoFragment.OnFragmentInteractionListener,LoaderManager.LoaderCallbacks<Cursor>{


    private MenuParser menuParser = new MenuParser();
    private final String  URI = "content://com.ezmenutouch.contentprovider.MovieContentProvider/favmovies";
    private List<OrderItem> dishList = new ArrayList<OrderItem>();
    private CursorLoader cursorLoader;

    Bundle movieBundle = new Bundle();
    private ShareActionProvider mShareActionProvider;
    com.ezmenutouch.fragment.VideoFragment trailersFragment = null;
    Intent mShareIntent  = new Intent();;

    /**
     * Start Detailed Dish activity with all the required initializations.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_dish);
        getSupportLoaderManager().initLoader(1, null, this);

    }


    public void onFragmentInteraction(String id) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater Inflater = getMenuInflater();
        Inflater.inflate(R.menu.sharemenu,menu);
        MenuItem item = menu.findItem(R.id.share);
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();
        if (mShareActionProvider != null ){


            mShareIntent.setAction(Intent.ACTION_SEND);
            mShareIntent.setType("text/plain");

            mShareActionProvider.setShareIntent(mShareIntent);
        }
        return true;
    }




    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        cursorLoader= new CursorLoader(getApplicationContext(), Uri.parse(URI), null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            OrderItem dish = Util.cursorToFavOrder(cursor);
            //System.out.println("Dish info.."+ dish.getTitle());
            dishList.add(dish);
            cursor.moveToNext();
        }
        cursor.close();


    }

    public List<OrderItem> getDishList(){
        return dishList;
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
