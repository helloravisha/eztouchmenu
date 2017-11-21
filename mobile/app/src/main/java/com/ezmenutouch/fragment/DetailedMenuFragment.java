package com.ezmenutouch.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ShareActionProvider;

import com.ezmenutouch.R;
import com.ezmenutouch.util.Util;
import com.ezmenutouch.vo.Dish;
import com.ezmenutouch.vo.FoodItem;

import java.util.ArrayList;
import java.util.List;

//import android.app.Fragment;

/**
 * This will get triggered in the case of tab display. this fragment in turn holds three fragments
 * for holding the Dish info, videos and reviews.
 */
public class DetailedMenuFragment extends Fragment implements ItemVideo.OnFragmentInteractionListener,ItemReviews.OnFragmentInteractionListener, VideoFragment.OnFragmentInteractionListener, LoaderManager.LoaderCallbacks<Cursor> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private VideoFragment trailers = null;
    private final String  URI = "content://com.ezmenutouch.contentprovider.MovieContentProvider/favmovies";
    private List<Dish> dishList = new ArrayList<Dish>();
    private CursorLoader cursorLoader = null;

    private ShareActionProvider mShareActionProvider;
    Intent sharedIntent  = new Intent();;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailedMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailedMenuFragment newInstance(String param1, String param2) {
        DetailedMenuFragment detailedMenuFragment = new DetailedMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        detailedMenuFragment.setArguments(args);
        return detailedMenuFragment;
    }

    public DetailedMenuFragment() {
        // Required empty public constructor
    }

    public void setSharedIntent(Intent sharedIntent){
      this.sharedIntent = sharedIntent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.sharemenu, menu);


        MenuItem item = menu.findItem(R.id.share);
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();
        if (mShareActionProvider != null ){


            sharedIntent.setAction(Intent.ACTION_SEND);
            sharedIntent.setType("text/plain");

            mShareActionProvider.setShareIntent(sharedIntent);
        }


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getLoaderManager().initLoader(1, null,this );
        return inflater.inflate(R.layout.fragment_detailed_movie, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @Override
    public void onFragmentInteraction(Uri uri) {



    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
       cursorLoader= new CursorLoader(getActivity().getApplicationContext(), Uri.parse(URI), null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Dish dish = Util.cursorToFavMovie(cursor);
            dishList.add(dish);
            cursor.moveToNext();
        }
        cursor.close();


    }



    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
