package com.ezmenutouch.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.ezmenutouch.R;
import com.ezmenutouch.constants.MenuConstants;
import com.ezmenutouch.util.JasonMenuUtil;
import com.ezmenutouch.util.MenuParser;
import com.ezmenutouch.util.Util;
import com.ezmenutouch.vo.Dish;
import com.ezmenutouch.util.OnRequestCompletedListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ezmenutouch.util.JasonMenuDataParser;
import com.ezmenutouch.vo.FoodItem;

import java.util.ArrayList;

/**
 * Class acts as a base activity for the complete application.
 * @author ravisha
 */
public class MenuActivity extends Activity implements View.OnClickListener {

    private Button sportifySteamerBtn;
    private Button scoredAppBtn;
    private Button libraryAppBtn;
    private Button buidItBiggerBtn;
    private Button xyzReaderBtn;
    private Button capsstoneBtn;

    private int duration = Toast.LENGTH_SHORT;
    private JasonMenuDataParser jasonMenuDataParser = new JasonMenuDataParser();
    private JasonMenuUtil jasonMenuUtil = new JasonMenuUtil();

    private MenuParser menuParser = new MenuParser();
    // List<Dish> highRatedMovieList = null;
    //List<Dish> mostPopularMovieList = null;
    Bundle movieBundle = new Bundle();

    /**
     * Initilaize all the ui components here.
     */
    private void initUIComponents(){
        // getActionBar().setBackgroundDrawable(new
        //       ColorDrawable(Color.parseColor(MenuConstants.ACTION_BAR_BGCOLOR)));
        //  setTitle(R.string.app_name);
        // getActionBar().setTitle(Html.fromHtml("<font color=\"#f1f1f1\">" + getString(R.string.app_name) + "</font>"));
        sportifySteamerBtn = (Button) findViewById(R.id.sportifySteamerBtn);
        scoredAppBtn = (Button) findViewById(R.id.scoredAppBtn);
        libraryAppBtn = (Button) findViewById(R.id.libraryAppBtn);
        buidItBiggerBtn = (Button) findViewById(R.id.buidItBiggerBtn);
        capsstoneBtn = (Button) findViewById(R.id.capsstoneBtn);
        xyzReaderBtn = (Button) findViewById(R.id.xyzReaderBtn);

        sportifySteamerBtn.setOnClickListener(this);
        scoredAppBtn.setOnClickListener(this);
        libraryAppBtn.setOnClickListener(this);
        buidItBiggerBtn.setOnClickListener(this);
        capsstoneBtn.setOnClickListener(this);
        xyzReaderBtn.setOnClickListener(this);

    }


    /**
     * used for starting the activity with all the required components.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        System.out.println("Menu Activity.."+getResources().getBoolean(R.bool.isTablet));

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
        // initData(listener);

        initUIComponents();

        System.out.println("Tab one one.."+getResources().getBoolean(R.bool.isTablet));
        //jasonMenuDataParser.makeJsonObjectRequest(MenuConstants.MENU_ITEMS_REST_URL,listener);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //   getMenuInflater().inflate(R.menu.menu_portfolio, menu);
        return true;
    }


    /**
     * Listener method for all the button events.
     * @param v
     */
    @Override
    public void onClick(View v) {
        Context context = getApplicationContext();

        switch (v.getId()) {
            case R.id.sportifySteamerBtn:
                Toast sportifyToast = Toast.makeText(context, MenuConstants.SPORTIFY_APP_BUTTON_TEXT, duration);
                sportifyToast.show();
                Intent intent = null;
                if(getResources().getBoolean(R.bool.isTablet)){
                    System.out.println("Entered Tablet..");
                    intent = new Intent(MenuActivity.this, DetailedDishTabActivity.class);
                }else {
                    System.out.println("Entered Mobile..");
                    intent =  new Intent(MenuActivity.this, MyOrders.class);
                }
                intent.putExtras(movieBundle);
                startActivity(intent);
                break;
            case R.id.scoredAppBtn:
                Toast scoredAppToast = Toast.makeText(context,MenuConstants.SCORED_APP_BUTTON_TEXT, duration);
                scoredAppToast.show();
                break;
            case R.id.libraryAppBtn:
                Toast libarayAppToast = Toast.makeText(context,MenuConstants.LIBRARY_APP_BUTTON_TEXT, duration);
                libarayAppToast.show();
                break;
            case R.id.buidItBiggerBtn:
                Toast buidItBiggerToast = Toast.makeText(context, MenuConstants.BUILD_APP_BUTTON_TEXT, duration);
                buidItBiggerToast.show();
                break;
            case R.id.capsstoneBtn:
                Toast capsstoneToast = Toast.makeText(context, MenuConstants.CAPSSTONE_APP_BUTTON_TEXT, duration);
                capsstoneToast.show();
                break;
            case R.id.xyzReaderBtn:
                Toast xyzReaderToast = Toast.makeText(context,MenuConstants.XYZREADER_APP_BUTTON_TEXT, duration);
                xyzReaderToast.show();
                break;


        }
    }

}



