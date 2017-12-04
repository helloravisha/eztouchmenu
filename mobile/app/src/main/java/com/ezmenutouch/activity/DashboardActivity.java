package com.ezmenutouch.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.LinearLayout;


import com.ezmenutouch.R;
import com.ezmenutouch.constants.DashBoardConstants;
import com.ezmenutouch.dao.MenuDAO;
import com.ezmenutouch.util.JasonMenuDataParser;
import com.ezmenutouch.util.SharedPreferenceUtil;
import com.ezmenutouch.vo.OrderItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DashboardActivity extends Activity {


    LinearLayout layoutMenu, layoutOrders, layoutStatus, layoutChat, layoutBill;
    String TAG = "DashboardActivity";
    MenuDAO menuDao = null;
    Activity activity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        activity = this;
        if (menuDao == null ){
            menuDao = new MenuDAO(this.getApplicationContext());
            menuDao.open();
        }
        initUI(savedInstanceState);
    }

    private void initUI(Bundle savedInstanceState) {

        layoutOrders = (LinearLayout) findViewById(R.id.layoutOders);
        layoutBill = (LinearLayout) findViewById(R.id.layoutBill);
        layoutChat = (LinearLayout) findViewById(R.id.layoutChat);
        layoutStatus = (LinearLayout) findViewById(R.id.layoutStatus);
        layoutMenu = (LinearLayout) findViewById(R.id.layoutMenu);


        /*
        waiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this,PolyVoiceActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(i, 0);
                overridePendingTransition(0,0);
            }
        }); */


        layoutChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Intent i = new Intent(DashboardActivity.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(i, 0);
                overridePendingTransition(0,0); */

                Intent voiceIntent = new Intent(DashboardActivity.this, InteractiveVoiceActivity.class);
                startActivity(voiceIntent);
                overridePendingTransition(0,0);



                //JasonMenuDataParser.placeOrder(finalTableName,finalPrice+"",finalItemIDs,"New",orderdate);


            }
        });
        layoutOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                dialog.setCancelable(false);
                dialog.setTitle("Confirm Following order ");
                menuDao.getAllOrders();
                String itemids = "";
                String tableName = "";
                String itemNames = "";
                double price = 0.0;
                ArrayList<OrderItem> orderedItemsInfo =  menuDao.getAllOrders();
                for (OrderItem orders: orderedItemsInfo
                        ) {
                    itemids =  itemids+orders.getItemId() + ",";
                    tableName = orders.getTableName();
                    itemNames += orders.getItemName()+"-- $"+orders.getItemPrice()+"\n";
                    price =  price+Double.parseDouble(orders.getItemPrice());
                }
                if(orderedItemsInfo != null ){
                    if ( orderedItemsInfo.size() > 0 ){
                        final String finalTableName = tableName;
                        int lastIndex = itemids.lastIndexOf(",");
                        String itemIdsInfo = itemids.substring(0,lastIndex);
                        final String finalItemIDs = itemIdsInfo;
                        final String finalPrice = String.valueOf(price);
                        dialog.setMessage(itemNames+"\n"+"Total Bill: $ "+finalPrice);
                        dialog.setPositiveButton("Order", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String orderdate =  new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                                JasonMenuDataParser.placeOrder(finalTableName,finalPrice+"",finalItemIDs,"Placed",orderdate);




                            }
                        })
                                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                    }else{
                        dialog.setMessage("No pending orders");
                        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {




                            }
                        });
                    }
                }


                final AlertDialog alert = dialog.create();
                alert.show();
            }
        });
        layoutStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableName =  SharedPreferenceUtil.getSharedPreferenceString(DashboardActivity.this, DashBoardConstants.PREFS_NAME, DashBoardConstants.TABLE_NAME,"500");;
                JasonMenuDataParser.getOrdersStatus(tableName,DashboardActivity.this);
            }
        });
        layoutMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this,MenuTypeActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(i, 0);
                overridePendingTransition(0,0);
            }
        });

        layoutBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                dialog.setCancelable(false);
                dialog.setTitle("Your total bill ");
                menuDao.getAllOrders();
                String itemids = "";
                String tableName = "";
                String itemNames = "";
                double price = 0.0;
                ArrayList<OrderItem> orderedItemsInfo =  menuDao.getAllOrders();
                for (OrderItem orders: orderedItemsInfo
                        ) {
                    itemids =  itemids+orders.getItemId() + ",";
                    tableName = orders.getTableName();
                    itemNames += orders.getItemName()+"-- $"+orders.getItemPrice()+"\n";
                    price =  price+Double.parseDouble(orders.getItemPrice());
                }
                if(orderedItemsInfo != null ){
                    if ( orderedItemsInfo.size() > 0 ){
                        final String finalTableName = tableName;
                        int lastIndex = itemids.lastIndexOf(",");
                        String itemIdsInfo = itemids.substring(0,lastIndex);
                        final String finalItemIDs = itemIdsInfo;
                        final String finalPrice = String.valueOf(price);
                        dialog.setMessage(itemNames+"\n"+"Total Bill: $ "+finalPrice);
                        dialog.setPositiveButton("pay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                menuDao.deleteAllOrder();
                                Intent voiceIntent = new Intent(DashboardActivity.this, TableActivity.class);
                                startActivity(voiceIntent);
                                overridePendingTransition(0,0);




                            }
                        })
                                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                    }else{
                        dialog.setMessage("No pending orders");
                        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {




                            }
                        });
                    }
                }


                final AlertDialog alert = dialog.create();
                alert.show();
                menuDao.deleteAllOrder();
            }
        });






    }


    @Override
    public void onBackPressed() {

    }
}