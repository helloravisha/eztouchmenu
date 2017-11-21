package com.ezmenutouch.vo;

/**
 * Created by ravisha on 11/17/17.
 */

public class OrderItem {


    int srno;
    String name;
    float price;
    String orderDate;
    String orderPlaced;
    String tableName;
    String OrderStatus;
    String orderId;



    public OrderItem(int srno, String name, float price, String orderDate, String orderPlaced, String tableName) {
        this.srno = srno;
        this.name = name;
        this.price = price;
        this.orderDate = orderDate;
        this.orderPlaced = orderPlaced;
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }



    public OrderItem(){

    }



    public int getSrno() {
        return srno;
    }

    public void setSrno(int srno) {
        this.srno = srno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderPlaced() {
        return orderPlaced;
    }

    public void setOrderPlaced(String orderPlaced) {
        this.orderPlaced = orderPlaced;
    }
}
