/**
 * 
 */
package com.ezmenutouch.services;

import java.util.List;

import com.ezmenutouch.model.FoodOrder;

/**
 * @author rajisunder
 *
 */
public interface OrderService {
	public FoodOrder add(FoodOrder Order);
	public FoodOrder update(FoodOrder Order);
	public void delete(FoodOrder Order);
	public FoodOrder find(Long id);
	public List<FoodOrder> findAll();
}
