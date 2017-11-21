package com.ezmenutouch.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ezmenutouch.R;
import com.ezmenutouch.adapter.ReviewGridAdapter;
import com.ezmenutouch.constants.MenuConstants;
import com.ezmenutouch.util.JasonMenuUtil;
import com.ezmenutouch.util.MenuParser;
import com.ezmenutouch.util.OnRequestCompletedGenericListener;
import com.ezmenutouch.vo.Dish;
import com.ezmenutouch.vo.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ItemReviews extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private JasonMenuUtil jasonMenuUtil = new JasonMenuUtil();
    private MenuParser menuParser = new MenuParser();
    private View view = null;
    private Dish dish = null;

    private ReviewGridAdapter reviewGridAdapter;

    ArrayList<Review> gridArray = new ArrayList<Review>();



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemReviews.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemReviews newInstance(String param1, String param2) {
        ItemReviews fragment = new ItemReviews();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ItemReviews() {
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
        view =  inflater.inflate(R.layout.fragment_food_reviews, container, false);
        dish = this.getActivity().getIntent().getExtras().getParcelable(MenuConstants.MOVIE_PARCELABLE_KEY);
        return view;


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

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void requestJASONObject(OnRequestCompletedGenericListener genericListenerJason, String URL) {
        try {
            jasonMenuUtil.makeJsonObjectRequest(URL, genericListenerJason);
        } catch (Exception exception) {

        }
    }

}
