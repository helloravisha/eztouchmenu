/**
 * 
 */
package com.eztmenutouch.services;

import java.util.List;

import com.ezmenutouch.model.FoodCategory;

/**
 * @author rajisunder
 *
 */
public interface CategoryService {
	public FoodCategory add(FoodCategory foodcategory);
	public FoodCategory update(FoodCategory foodcategory);
	public void delete(FoodCategory foodcategory);
	public FoodCategory find(Long id);
	public List<FoodCategory> findAll();
}
