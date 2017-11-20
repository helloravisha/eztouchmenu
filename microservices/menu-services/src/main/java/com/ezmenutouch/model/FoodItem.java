package com.ezmenutouch.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class FoodItem {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    private float price;
    private String imagepath;
    
  
    @ManyToOne
    @JsonIgnore
	private FoodCategory foodcategory;

    
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public FoodCategory getFoodcategory() {
		return foodcategory;
	}
	public void setFoodcategory(FoodCategory foodcategory) {
		this.foodcategory = foodcategory;
	}
	
	
}
