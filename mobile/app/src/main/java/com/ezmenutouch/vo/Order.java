package com.ezmenutouch.vo;

import java.util.List;

/**
 * Created by ravisha on 11/19/17.
 */

public class Order {

    List<FoodItem> orderItems;
    String tableName;
    String totalPrice;
    String orderDate;
    String orderStatus;// initially status is "new", when order palced, the when order is addressed, change he
    // staus to "inprogress" , then once ready change the status = "ready",

    public Order(List<FoodItem> orderItems, String tableName, String totalPrice, String orderDate, String orderStatus) {
        this.orderItems = orderItems;
        this.tableName = tableName;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public List<FoodItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<FoodItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
