package com.ezmenutouch.vo;

/**
 * Created by ravisha on 11/17/17.
 */

public class OrderItem {

    String itemId;
    String tableName;
    String itemPrice;
    String orderStatus;
    String orderdate;
    String itemName;

    public OrderItem(){

    }

    public OrderItem(String itemId, String tableName, String itemPrice, String orderStatus, String orderdate, String itemName) {
        this.itemId = itemId;
        this.tableName = tableName;
        this.itemPrice = itemPrice;
        this.orderStatus = orderStatus;
        this.orderdate = orderdate;
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
