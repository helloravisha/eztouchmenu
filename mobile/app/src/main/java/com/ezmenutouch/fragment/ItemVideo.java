package com.ezmenutouch.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezmenutouch.R;
import com.ezmenutouch.activity.DashboardActivity;
import com.ezmenutouch.activity.MenuTypeActivity;
import com.ezmenutouch.activity.PolyVoiceActivity;
import com.ezmenutouch.constants.DashBoardConstants;
import com.ezmenutouch.constants.MenuConstants;
import com.ezmenutouch.dao.MenuDAO;
import com.ezmenutouch.util.JasonMenuDataParser;
import com.ezmenutouch.util.JasonMenuUtil;
import com.ezmenutouch.util.MenuParser;
import com.ezmenutouch.util.OnRequestCompletedGenericListener;
import com.ezmenutouch.util.SharedPreferenceUtil;
import com.ezmenutouch.vo.FoodItem;
import com.ezmenutouch.vo.OrderItem;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ItemVideo extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FoodItem foodItem = null;
    MenuDAO menuDao = null;

    TextView txtOverview = null;
    Button btnFavourite = null;
    Button btnMyOrders = null;
    Button btnBackToMenu = null;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private JasonMenuUtil jasonMenuUtil = new JasonMenuUtil();
    private MenuParser menuParser = new MenuParser();
    private View view = null;


    private String review = null;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemVideo.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemVideo newInstance(String param1, String param2) {
        ItemVideo fragment = new ItemVideo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ItemVideo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if (menuDao == null ){
            menuDao = new MenuDAO(getActivity().getApplicationContext());
            menuDao.open();
        }
    }


    public void updateMovieData(FoodItem foodItem){
        updateMovie(foodItem);


    }

    public void updateMovie(FoodItem foodItem){

        btnFavourite = (Button)view.findViewById(R.id.btnFavourite);

        this.foodItem = foodItem;
        ImageView movieImageView = (ImageView)view.findViewById(R.id.detailedFoodImage);
        //dish = getActivity().getIntent().getExtras().getParcelable(MenuConstants.MOVIE_PARCELABLE_KEY);
        if(foodItem == null ){
            return;
        }
        Picasso.with(getActivity()).load(foodItem.getImagePath()).error(R.drawable.noposter).resize(300, 400)
                .into(movieImageView);
        TextView price = ( TextView )view.findViewById(R.id.price);
        price.setText("$ "+foodItem.getPrice()+"0");
        TextView overview = ( TextView )view.findViewById(R.id.overview);
        overview.setText(foodItem.getDescription().trim());
       // overview.setMovementMethod(new ScrollingMovementMethod());
       // TextView voteAverage = ( TextView )view.findViewById(R.id.voteAverage);
        //voteAverage.setText(MenuConstants.DETAILED_MOVIE_VOTE_AVERAGE_PREFIX + foodItem..getVoteAverage() + "");
        Button title = ( Button )view.findViewById(R.id.title);
        title.setText(foodItem.getName());
        txtOverview = (TextView)view.findViewById(R.id.overview);

        btnMyOrders = (Button)view.findViewById(R.id.btnMyOrders);
        btnBackToMenu  = (Button)view.findViewById(R.id.btnBackToMenu);

        btnMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),MenuTypeActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(i, 0);
                //overridePendingTransition(0,0);
            }
        });

        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),DashboardActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(i, 0);
                //overridePendingTransition(0,0);
            }
        });



        /*
        if (menuDao.getMovie(dish) != null ){
            btnFavourite.setText(MenuConstants.DELETE_FAVOURITE);
        }else{
            btnFavourite.setText(MenuConstants.ADD_FAVOURITE);
        }*/

        btnFavourite.setOnClickListener(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragment_food_video, container, false);

        if (!getResources().getBoolean(R.bool.isTablet)) {
            foodItem = getActivity().getIntent().getExtras().getParcelable(MenuConstants.MOVIE_PARCELABLE_KEY);
            //getActivity().setTitle(MenuConstants.MOVIE_DETAIL);
            ImageView movieImageView = (ImageView) view.findViewById(R.id.detailedFoodImage);
            foodItem = getActivity().getIntent().getExtras().getParcelable(MenuConstants.MOVIE_PARCELABLE_KEY);
            if (foodItem == null) {
                btnFavourite = (Button)view.findViewById(R.id.btnFavourite);
                return view;
            }
            updateMovie(foodItem);
        }
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
    public void onClick(View v) {
        if (menuDao == null ){
            menuDao = new MenuDAO(getActivity().getApplicationContext());
            menuDao.open();
        }
        System.out.println("Food Item###########"+foodItem.getName());

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setCancelable(false);
        dialog.setTitle("Order");
        dialog.setMessage("Add to order :  "+foodItem.getName());
        dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

               String tableName =  SharedPreferenceUtil.getSharedPreferenceString(getActivity(), DashBoardConstants.PREFS_NAME, DashBoardConstants.TABLE_NAME,"500");;
               String itemPrice = foodItem.getPrice();
               String itemId = foodItem.getId();
                String orderStatus = "Placed";
                String orderdate =  new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

                String itemName = foodItem.getName();
               menuDao.insertOrder(new OrderItem(itemId,tableName,itemPrice,orderStatus,orderdate,itemName));
                ArrayList<OrderItem> orderedItemsInfo =  menuDao.getAllOrders();
                for (OrderItem orders: orderedItemsInfo
                     ) {
                    System.out.println("orders.."+orders.getTableName());
                }

               // JasonMenuDataParser.placeOrder("ravisha");
            }
        })
                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();
        // menuDao.insertMovie(foodItem);
       //  btnFavourite.setText(MenuConstants.DELETE_FAVOURITE);
        //List<Dish> movies = menuDao.getAllMovies();
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

    public void  loadReview(Map<Long,List<String>> trailersInfo){
       // String reviewLoaded =
    }

}
