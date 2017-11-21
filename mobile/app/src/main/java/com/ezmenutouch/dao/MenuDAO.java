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
          OrderAttributes.SR_NO, OrderAttributes.NAME,OrderAttributes.PRICE,OrderAttributes.ORDER_DATE, OrderAttributes.ORDER_PLACED};

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

  public OrderItem getMovie(OrderItem orderItem){
    String getMovie = "SELECT  * FROM ORDER WHERE SRNO";
    getMovie += orderItem.getSrno();
    Cursor cursor = database.rawQuery(getMovie, null);
    cursor.moveToFirst();
    if (!cursor.isAfterLast()) {
      return cursorToFavOrder(cursor);
    }
    return null;

  }


  private static final String ORDER_TABLE = "create table order(srno integer,name text,price real,orderdate text,orderplaced text);";

  public long  insertOrder(OrderItem orderItem) {
    ContentValues values = new ContentValues();
    values.put(OrderAttributes.SR_NO, orderItem.getSrno());
    values.put(OrderAttributes.NAME, orderItem.getName());
    values.put(OrderAttributes.ORDER_DATE, orderItem.getOrderDate());
    values.put(OrderAttributes.ORDER_PLACED, orderItem.getOrderPlaced());
    values.put(OrderAttributes.PRICE, orderItem.getPrice());
    return database.insert("order", null,
            values);
  }


  public void deleteOrder(FoodItem foodItem) {
    String name  = foodItem.getName();
    database.delete("order", name
            + " = " + name, null);
  }

  public ArrayList<OrderItem> getAllOrders() {
    ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();

    Cursor cursor = database.query("order",
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
    orderItem.setSrno(cursor.getInt(0));
    orderItem.setName(cursor.getString(1));
    orderItem.setPrice(cursor.getFloat(2));
    orderItem.setOrderDate(cursor.getString(3));
    orderItem.setOrderPlaced(cursor.getString(4));
    return orderItem;
  }


  public static class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_FAVOURITE = "movie";


    private static final String DATABASE_NAME = "movie.db";
    private static final int DATABASE_VERSION = 1;





    // Table creation sql statement



    private static final String ORDER_TABLE = "create table order(srno integer,name text,price real,orderdate text,orderplaced text);";



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
      db.execSQL("DROP TABLE IF EXISTS " + "order");
      onCreate(db);
    }

  }
}
