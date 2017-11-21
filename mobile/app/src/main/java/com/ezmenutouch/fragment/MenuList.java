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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezmenutouch.R;
import com.ezmenutouch.adapter.ItemViewAdapter;
import com.ezmenutouch.constants.MenuConstants;
import com.ezmenutouch.dao.MenuDAO;
import com.ezmenutouch.util.JasonMenuDataParser;
import com.ezmenutouch.util.OnRequestCompletedListener;
import com.ezmenutouch.util.Util;
import com.ezmenutouch.vo.Dish;
import com.ezmenutouch.vo.FoodItem;

import java.util.ArrayList;


public class MenuList extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager  recycleLayoutManager;
    private JasonMenuDataParser jasonMenuDataParser = new JasonMenuDataParser();
    ArrayList<Dish> moviesList = null;
    MenuDAO menuDAO = null;
    View view = null;
    private final String  URI = "content://com.ezmenutouch.contentprovider.MovieContentProvider/favmovies";
    private ArrayList<Dish> favDishList = new ArrayList<Dish>();
    private CursorLoader cursorLoader;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private MovieSelectionListener movieSelectionListener;

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
            favDishList.add(dish);
            cursor.moveToNext();
        }
        cursor.close();


    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    public interface MovieSelectionListener{
        public void setDish(Dish dish);
    }

    public interface DishSelectionListener{
        public void setDish(FoodItem dish);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment movies_list.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuList newInstance(String param1, String param2) {
        MenuList fragment = new MenuList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MenuList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getLoaderManager().initLoader(1, null, this);

        view = inflater.inflate(R.layout.fragment_food_list, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycleViewTab);
        recycleLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(recycleLayoutManager);
        final ItemViewAdapter itemViewAdapter = new ItemViewAdapter(getActivity());
        recyclerView.setAdapter(itemViewAdapter);

        Intent intent = getActivity().getIntent();
        moviesList = null;
        if(menuDAO == null ) {
            menuDAO = new MenuDAO(getActivity().getApplicationContext());
            menuDAO.open();
        }
       final String sortOrder = intent.getStringExtra(String.valueOf(R.string.sortOrder));
        OnRequestCompletedListener listener = new OnRequestCompletedListener() {
            @Override
            public void onRequestCompleted(ArrayList<FoodItem> foodItemList, String moviesCategory) {
                System.out.println("##################Call Back..");
                if(MenuConstants.ITEMS_MENU.equals(moviesCategory)){
                    itemViewAdapter.setItemList(foodItemList);
                    recyclerView.setHasFixedSize(true);
                }

            }
        };
        jasonMenuDataParser.makeJsonObjectRequest(MenuConstants.MENU_ITEMS_REST_URL,listener);
        return view;

    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (movieSelectionListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            movieSelectionListener = (MovieSelectionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        movieSelectionListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
