package com.ezmenutouch.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ezmenutouch.R;
import com.ezmenutouch.adapter.CustomGridViewAdapter;
import com.ezmenutouch.constants.MenuConstants;
import com.ezmenutouch.util.JasonMenuUtil;
import com.ezmenutouch.util.MenuParser;
import com.ezmenutouch.util.OnRequestCompletedGenericListener;
import com.ezmenutouch.vo.Dish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VideoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View view = null;

    CustomGridViewAdapter customGridAdapter;

    ArrayList<com.ezmenutouch.vo.FoodVideo> gridArray = new ArrayList<com.ezmenutouch.vo.FoodVideo>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Dish dish = null;
    private JasonMenuUtil jasonMenuUtil = new JasonMenuUtil();
    private MenuParser menuParser = new MenuParser();

    private OnFragmentInteractionListener mListener;
    private Intent sharedIntent;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Trailers.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public VideoFragment() {
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
         view = inflater.inflate(R.layout.fragment_video, container, false);
        return view;



    }
    private boolean status = false;
    public void setComplete(boolean status){
        this.status = status;
    }

    public boolean getComplete(){
        return status;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    public void setSharedTrailInfo(Intent sharedIntent){
       this.sharedIntent = sharedIntent;

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

    Map<String,List<String>> movieTrailerList = new HashMap<>();
    public void setMovieInfo( Map<String,List<String>> movieInfo){
        this.movieTrailerList = movieInfo;
    }

    public Map<String,List<String>> getMovieInfo(){
        return movieTrailerList;
    }

    private void requestJASONObject(OnRequestCompletedGenericListener genericListenerJason, String URL){
        try{
            jasonMenuUtil.makeJsonObjectRequest(URL,genericListenerJason);
        }catch(Exception exception){

        }

    }



}
