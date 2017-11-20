package com.eztmenutouch.services;

import java.util.List;
import java.util.Set;

import com.ezmenutouch.model.FoodCategory;
import com.ezmenutouch.model.FoodItem;

public interface ItemService {
	
	public FoodItem add(FoodItem fooditem);
	public FoodItem update(FoodItem fooditem);
	public void delete(FoodItem fooditem);
	public List<FoodItem> findAll();
	public Set<FoodItem> findItemsByCategory(FoodCategory foodcategory);
	public FoodItem find(Long id);
	void delete(Long id);
	
}
