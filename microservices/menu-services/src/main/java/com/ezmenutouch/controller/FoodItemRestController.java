package com.ezmenutouch.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ezmenutouch.model.FoodItem;
import com.ezmenutouch.repositories.FoodCategoryRepo;
import com.ezmenutouch.repositories.FoodItemRepo;
import com.ezmenutouch.util.CustomErrorType;


@RestController
@RequestMapping("/api/v1/fooditems")
public class FoodItemRestController {
    @Autowired
    private FoodItemRepo foodItemRepo;
    @Autowired
    private FoodCategoryRepo foodCategoryRepo;

    public static final Logger logger = LoggerFactory.getLogger(FoodItemRestController.class);

    @RequestMapping(value = "")
    public List<FoodItem> list(ModelMap model){
       return (List<FoodItem>) foodItemRepo.findAll();
    }

    // -------------------Retrieve All Babies ---------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<FoodItem>> listAllFoodItems() {
        List<FoodItem> products = (List<FoodItem>) foodItemRepo.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<FoodItem>>(products, HttpStatus.OK);
    }

    // -------------------Retrieve one Baby------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getFoodItems(@PathVariable("id") Long id) {
        logger.info("Fetching User with id {}", id);
        FoodItem product = foodItemRepo.findOne(id);
        if (product == null) {
            logger.error("Product with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Baby with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<FoodItem>(product, HttpStatus.OK);
    }

    // -------------------Create a FoodItem-------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createFoodItem(@RequestBody FoodItem fooditem, UriComponentsBuilder ucBuilder) {
        logger.info("Creating FoodItem : {}", fooditem);
        if (foodItemRepo.exists(Example.<FoodItem>of(fooditem, ExampleMatcher.matching()
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.ignoreCase())))) {
           logger.error("Unable to create. A Baby with email {} already exist", fooditem.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " +
            		fooditem.getId() + " already exist."),HttpStatus.CONFLICT);
        }
        foodItemRepo.save(fooditem);
        return new ResponseEntity<FoodItem>(fooditem, HttpStatus.CREATED);
    }

    // ------------------- Update a User ------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateFoodItem(@PathVariable("id") Long id, @RequestBody FoodItem fooditem) {
        logger.info("Updating User with id {}", id);

        FoodItem currentFoodItem = foodItemRepo.findOne(id);

        if (currentFoodItem == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentFoodItem.setName(fooditem.getName());
        currentFoodItem.setPrice(fooditem.getPrice());
        currentFoodItem.setDescription(fooditem.getDescription());
        currentFoodItem.setImagepath(fooditem.getImagepath());
        foodItemRepo.save(currentFoodItem);
        return new ResponseEntity<FoodItem>(currentFoodItem, HttpStatus.OK);
    }

    // ------------------- Delete a Food Item ------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFoodItem(@PathVariable("id") Long id) {
        logger.info("Fetching & Deleting User with id {}", id);

        FoodItem currentProduct = foodItemRepo.findOne(id);
        if (currentProduct == null) {
            logger.error("Unable to delete. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        foodItemRepo.delete(id);
        return new ResponseEntity<FoodItem>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Users-----------------------------

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<FoodItem> deleteAllFoodItem() {
        logger.info("Deleting All Users");

        foodItemRepo.deleteAll();
        return new ResponseEntity<FoodItem>(HttpStatus.NO_CONTENT);
    }
}
