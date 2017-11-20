package com.ezmenutouch.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class FoodCategory {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    private String imagepath;
    
    @OneToMany(mappedBy="foodcategory")
    private Set<FoodItem> fooditems = new HashSet<FoodItem>();

   
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
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	

    public Set<FoodItem> getFooditems() {
		return fooditems;
	}
	public void setFooditems(Set<FoodItem> fooditems) {
		this.fooditems = fooditems;
	}
    
    
	
}
