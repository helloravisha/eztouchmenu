package com.ezmenutouch.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.ezmenutouch.constants.MenuAttributes;
import com.ezmenutouch.constants.OrderAttributes;
import com.ezmenutouch.vo.Dish;
import com.ezmenutouch.vo.FoodItem;
import com.ezmenutouch.vo.OrderItem;

import android.util.Log;

public class MenuDAO {

  // Database fields
  private SQLiteDatabase database;
  private MySQLiteHelper dbHelper;

  private String[] allColumns = {
          OrderAttributes.ITEM_ID, OrderAttributes.TABLE_NAME,OrderAttributes.ORDER_DATE,OrderAttributes.ITEM_PRICE, OrderAttributes.ORDER_STATUS,OrderAttributes.ITEM_NAME};

  public MenuDAO(Context context) {
    dbHelper = new MySQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public String[] getFavTableColoumns(){
    return allColumns;

  }



  public void close() {
    dbHelper.close();
  }


  public SQLiteDatabase getDataBase(Context context){
    if(database == null ) {
      dbHelper = new MySQLiteHelper(context);
      database = dbHelper.getWritableDatabase();
    }
    return database;
  }




  public long  insertOrder(OrderItem orderItem) {
    ContentValues values = new ContentValues();
    values.put(OrderAttributes.ITEM_ID, orderItem.getItemId());
    values.put(OrderAttributes.TABLE_NAME, orderItem.getTableName());
    values.put(OrderAttributes.ORDER_DATE, orderItem.getOrderdate());
    values.put(OrderAttributes.ITEM_PRICE, orderItem.getItemPrice());
    values.put(OrderAttributes.ORDER_STATUS, orderItem.getOrderStatus());
    values.put(OrderAttributes.ITEM_NAME, orderItem.getItemName());
    return database.insert("orders", null,
            values);
  }


  public void deleteOrder(OrderItem foodItem) {
    String name  = foodItem.getTableName();
    database.delete("orders", name
            + " = " + name, null);
  }

  public void deleteAllOrder() {
    ArrayList<OrderItem> orderedItemsInfo =  getAllOrders();

    for (OrderItem orders: orderedItemsInfo
            ) {
      String name  = orders.getTableName();
      database.delete("orders", name
              + " = " + name, null);
      break;
    }
  }



  public ArrayList<OrderItem> getAllOrders() {
    ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();

    Cursor cursor = database.query("orders",
            allColumns, null, null, null, null, null);
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      OrderItem dish = cursorToFavOrder(cursor);
      orderItemList.add(dish);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return orderItemList;
  }


  private OrderItem cursorToFavOrder(Cursor cursor) {
    OrderItem orderItem = new OrderItem();
    orderItem.setItemId(cursor.getString(0));
    orderItem.setTableName(cursor.getString(1));
    orderItem.setOrderdate(cursor.getString(2));
    orderItem.setItemPrice(cursor.getString(3));
    orderItem.setOrderStatus(cursor.getString(4));
    orderItem.setItemName(cursor.getString(5));

    return orderItem;
  }


  public static class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_FAVOURITE = "orders";


    private static final String DATABASE_NAME = "orders.db";
    private static final int DATABASE_VERSION = 1;


    private static final String ORDER_TABLE = "create table orders ( "+ OrderAttributes.ITEM_ID+" text,"+OrderAttributes.TABLE_NAME+" text,"+OrderAttributes.ORDER_DATE+" text,"
            +OrderAttributes.ITEM_PRICE+" text,"+OrderAttributes.ORDER_STATUS+" text,"+OrderAttributes.ITEM_NAME+" text);";


    // Table creation sql statement






    public MySQLiteHelper(Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
      database.execSQL(ORDER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      Log.w(MySQLiteHelper.class.getName(),
              "Upgrading database from version " + oldVersion + " to "
                      + newVersion + ", which will destroy all old data");
      db.execSQL("DROP TABLE IF EXISTS " + "orders");
      onCreate(db);
    }

  }
}
