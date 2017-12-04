package com.ezmenutouch.vo;

import java.util.List;

/**
 * Created by ravisha on 11/19/17.
 */

public class Order {

    List<OrderItem> orderItemList;
    String tableName;

    public Order(List<OrderItem> orderItemList, String tableName) {
        this.orderItemList = orderItemList;
        this.tableName = tableName;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
