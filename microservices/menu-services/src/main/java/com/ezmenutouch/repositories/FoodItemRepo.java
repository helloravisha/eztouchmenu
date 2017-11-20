package com.ezmenutouch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezmenutouch.model.FoodItem;
@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem, Long> {
	
}
