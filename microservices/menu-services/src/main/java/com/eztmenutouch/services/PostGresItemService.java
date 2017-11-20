package com.eztmenutouch.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezmenutouch.model.FoodCategory;
import com.ezmenutouch.model.FoodItem;
import com.ezmenutouch.repositories.FoodCategoryRepo;
import com.ezmenutouch.repositories.FoodItemRepo;
@Service
public class PostGresItemService implements ItemService {
	@Autowired
	public FoodItemRepo foodItemRepo;
	public FoodItemRepo getFoodItemRepo() {
		return foodItemRepo;
	}

	public void setFoodItemRepo(FoodItemRepo foodItemRepo) {
		this.foodItemRepo = foodItemRepo;
	}

	public FoodCategoryRepo getFoodCategoryRepo() {
		return foodCategoryRepo;
	}

	public void setFoodCategoryRepo(FoodCategoryRepo foodCategoryRepo) {
		this.foodCategoryRepo = foodCategoryRepo;
	}

	@Autowired
	public FoodCategoryRepo foodCategoryRepo;

	@Override
	public FoodItem add(FoodItem fooditem) {
		return foodItemRepo.save(fooditem);
	}

	@Override
	public FoodItem update(FoodItem fooditem) {
		return foodItemRepo.save(fooditem);
	}

	@Override
	public void delete(FoodItem fooditem) {
		delete(fooditem.getId());
	}

	@Override
	public void delete(Long id) {
		foodItemRepo.findOne(id);
	}

	@Override
	public FoodItem find(Long id) {
		return foodItemRepo.findOne(id);
	}

	@Override
	public List<FoodItem> findAll() {
		return (List<FoodItem>) foodItemRepo.findAll();
	}

	public Set<FoodItem> findItemsByCategory(FoodCategory foodcategory) {
		return foodcategory.getFooditems();
	}

}
