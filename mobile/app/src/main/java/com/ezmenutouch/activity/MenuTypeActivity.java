package com.ezmenutouch.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;


import com.ezmenutouch.R;
import com.ezmenutouch.constants.MenuConstants;
import com.ezmenutouch.dao.MenuDAO;
import com.ezmenutouch.util.OnRequestCompletedListener;
import com.ezmenutouch.vo.FoodItem;
import com.ezmenutouch.vo.OrderItem;

import java.util.ArrayList;


public class MenuTypeActivity extends Activity {


    LinearLayout layoutAppetizers, layoutBreads, layoutBeverages,layoutDeserts,layoutBiryanis,layoutEntrees  ;
    ImageView imgAppetizers,imgDeserts,imgBeverages,imgBreads,imgBiryanis,imgEntrees;
    String TAG = "DashboardActivity";
    MenuDAO menuDao = null;
    Activity activity = null;
    Bundle movieBundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_type);
        activity = this;
        if (menuDao == null ){
            menuDao = new MenuDAO(this.getApplicationContext());
            menuDao.open();
        }


        OnRequestCompletedListener listener = new OnRequestCompletedListener() {
            @Override
            public void onRequestCompleted(ArrayList<FoodItem> moviesList, String moviesCategory) {
                if(MenuConstants.HIGH_RATED_MOVIES.equals(moviesCategory)){
                    movieBundle.putParcelableArrayList(moviesCategory, moviesList);
                }else if(MenuConstants.POPULAR_MOVIES.equals(moviesCategory)){
                    movieBundle.putParcelableArrayList(moviesCategory,moviesList);
                }

            }
        };

        initUI(savedInstanceState);
    }

    private void initUI(Bundle savedInstanceState) {

        layoutAppetizers = (LinearLayout) findViewById(R.id.layoutAppetizers);

        layoutBreads = (LinearLayout) findViewById(R.id.layoutBreads);
        layoutBeverages = (LinearLayout) findViewById(R.id.layoutBeverages);

        layoutEntrees = (LinearLayout) findViewById(R.id.layoutEntrees);
        layoutDeserts = (LinearLayout) findViewById(R.id.layoutDeserts);
        layoutBiryanis = (LinearLayout) findViewById(R.id.layoutBiryanis);

        imgAppetizers = (ImageView) findViewById(R.id.imgAppetizers);
        imgDeserts = (ImageView) findViewById(R.id.imgDeserts);
        imgBeverages = (ImageView) findViewById(R.id.imgBeverages);

        imgBreads = (ImageView) findViewById(R.id.imgBreads);
        imgBiryanis = (ImageView) findViewById(R.id.imgBiryanis);
        imgEntrees = (ImageView) findViewById(R.id.imgEntrees);



/*
        layoutAppetizers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuTypeActivity.this,PolyVoiceActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(i, 0);
                overridePendingTransition(0,0);
            }
        }); */

        layoutAppetizers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if(getResources().getBoolean(R.bool.isTablet)){
                    System.out.println("Entered Tablet..");
                    intent = new Intent(MenuTypeActivity.this, DetailedDishTabActivity.class);
                }else {
                    System.out.println("Entered Mobile..");

                    intent =  new Intent(MenuTypeActivity.this, MyOrders.class);
                    intent.putExtra("menutype","132");
                }
                intent.putExtras(movieBundle);
                startActivity(intent);

            }
        });

        layoutBreads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if(getResources().getBoolean(R.bool.isTablet)){
                    System.out.println("Entered Tablet..");
                    intent = new Intent(MenuTypeActivity.this, DetailedDishTabActivity.class);
                }else {
                    System.out.println("Entered Mobile..");
                    intent =  new Intent(MenuTypeActivity.this, MyOrders.class);
                    intent.putExtra("menutype","135");

                }
                intent.putExtras(movieBundle);
                startActivity(intent);

            }
        });

        layoutBeverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if(getResources().getBoolean(R.bool.isTablet)){
                    System.out.println("Entered Tablet..");
                    intent = new Intent(MenuTypeActivity.this, DetailedDishTabActivity.class);
                }else {
                    System.out.println("Entered Mobile..");
                    intent =  new Intent(MenuTypeActivity.this, MyOrders.class);
                    intent.putExtra("menutype","133");

                }
                intent.putExtras(movieBundle);
                startActivity(intent);

            }
        });


        layoutEntrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if(getResources().getBoolean(R.bool.isTablet)){
                    System.out.println("Entered Tablet..");
                    intent = new Intent(MenuTypeActivity.this, DetailedDishTabActivity.class);
                }else {
                    System.out.println("Entered Mobile..");
                    intent =  new Intent(MenuTypeActivity.this, MyOrders.class);
                    intent.putExtra("menutype","134");

                }
                intent.putExtras(movieBundle);
                startActivity(intent);

            }
        });

        layoutDeserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if(getResources().getBoolean(R.bool.isTablet)){
                    System.out.println("Entered Tablet..");
                    intent = new Intent(MenuTypeActivity.this, DetailedDishTabActivity.class);
                }else {
                    System.out.println("Entered Mobile..");
                    intent =  new Intent(MenuTypeActivity.this, MyOrders.class);
                    intent.putExtra("menutype","137");

                }
                intent.putExtras(movieBundle);
                startActivity(intent);

            }
        });

        layoutBiryanis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if(getResources().getBoolean(R.bool.isTablet)){
                    System.out.println("Entered Tablet..");
                    intent = new Intent(MenuTypeActivity.this, DetailedDishTabActivity.class);
                }else {
                    System.out.println("Entered Mobile..");
                    intent =  new Intent(MenuTypeActivity.this, MyOrders.class);
                    intent.putExtra("menutype","136");

                }
                intent.putExtras(movieBundle);
                startActivity(intent);

            }
        });

        /*

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                dialog.setCancelable(false);
                dialog.setTitle("Confirm Order");
                menuDao.getAllOrders();
                String name = "";
                ArrayList<OrderItem> orderedItemsInfo =  menuDao.getAllOrders();
                for (OrderItem orders: orderedItemsInfo
                        ) {
                    name = name+" , \n"+orders.getItemName();
                }
                dialog.setMessage("Are you sure you "+name );
                dialog.setPositiveButton("Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {





                    }
                })
                        .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();
            }
        }); */

    }


    @Override
    public void onBackPressed() {

    }
}