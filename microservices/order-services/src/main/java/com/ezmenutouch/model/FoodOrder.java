package com.ezmenutouch.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FoodOrder {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String tableName;
    private String totalPrice;
    private String orderDate;
    private String orderStatus;
    private String orderedItems;
  
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
  	public String getOrderedItems() {
  		return orderedItems;
  	}
  	public void setOrderedItems(String orderedItems) {
  		this.orderedItems = orderedItems;
  	}
    
    
}
