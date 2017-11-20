package com.ezmenutouch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezmenutouch.model.FoodCategory;
@Repository
public interface FoodCategoryRepo extends JpaRepository<FoodCategory, Long> {

}
