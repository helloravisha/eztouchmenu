package com.ezmenutouch.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.LinearLayout;


import com.ezmenutouch.R;



public class DashboardActivity extends Activity {


    LinearLayout waiter, timing, orders, contacts, menu,  bill;
    String TAG = "DashboardActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initUI(savedInstanceState);
    }

    private void initUI(Bundle savedInstanceState) {

        waiter = (LinearLayout) findViewById(R.id.waiter);
        timing = (LinearLayout) findViewById(R.id.timing);
        orders = (LinearLayout) findViewById(R.id.orders);
        contacts = (LinearLayout) findViewById(R.id.contacts);
        menu = (LinearLayout) findViewById(R.id.menu);
        bill = (LinearLayout) findViewById(R.id.bill);


        waiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        timing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this,MenuActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(i, 0);
                overridePendingTransition(0,0);
            }
        });

        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });






    }


    @Override
    public void onBackPressed() {

    }
}