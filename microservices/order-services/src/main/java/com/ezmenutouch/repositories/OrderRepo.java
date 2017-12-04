package com.ezmenutouch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezmenutouch.model.FoodOrder;
@Repository
public interface OrderRepo extends JpaRepository<FoodOrder, Long> {

}
