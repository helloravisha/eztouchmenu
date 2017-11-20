package com.ezmenutouch.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ezmenutouch.model.FoodCategory;
import com.ezmenutouch.repositories.FoodCategoryRepo;
import com.ezmenutouch.util.CustomErrorType;


@RestController
@RequestMapping("/categories")
public class CategoryRestController {
    @Autowired
    private FoodCategoryRepo foodCategoryRepo;

    public static final Logger logger = LoggerFactory.getLogger(CategoryRestController.class);

    @RequestMapping(value = "")
    public List<FoodCategory> list(ModelMap model){
       return (List<FoodCategory>) foodCategoryRepo.findAll();
    }

    // -------------------Retrieve All Babies ---------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<FoodCategory>> listAllUsers() {
        List<FoodCategory> products = (List<FoodCategory>) foodCategoryRepo.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<FoodCategory>>(products, HttpStatus.OK);
    }

    // -------------------Retrieve one Baby------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBabies(@PathVariable("id") Long id) {
        logger.info("Fetching User with id {}", id);
        FoodCategory product = foodCategoryRepo.findOne(id);
        if (product == null) {
            logger.error("Product with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Baby with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<FoodCategory>(product, HttpStatus.OK);
    }

    // -------------------Create a Baby-------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createFoodCategory(@RequestBody FoodCategory foodcategory, UriComponentsBuilder ucBuilder) {
        logger.info("Creating FoodCategory : {}", foodcategory);

        if (foodCategoryRepo.exists(Example.<FoodCategory>of(foodcategory, ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.ignoreCase())))) {
           // logger.error("Unable to create. A Baby with email {} already exist", product.getId().concat(product.getCost()()));
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Unable to create. A Category with id " +
            		foodcategory.getId() + " already exist."),HttpStatus.CONFLICT);
        }
        foodCategoryRepo.save(foodcategory);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/v1/foodcategory/{id}").buildAndExpand(foodcategory.getId()).toUri());
        return new ResponseEntity<FoodCategory>(foodcategory,HttpStatus.CREATED);
    }

    // ------------------- Update a User ------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateFoodCategory(@PathVariable("id") Long id, @RequestBody FoodCategory foodCategory) {
        logger.info("Updating User with id {}", id);

        FoodCategory currentFoodCategory = foodCategoryRepo.findOne(id);

        if (currentFoodCategory == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentFoodCategory.setName(foodCategory.getName());
        currentFoodCategory.setDescription(foodCategory.getDescription());
        currentFoodCategory.setImagepath(foodCategory.getImagepath());
        foodCategoryRepo.save(currentFoodCategory);
        return new ResponseEntity<FoodCategory>(currentFoodCategory, HttpStatus.OK);
    }

    // ------------------- Delete a Food Item ------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFoodCategory(@PathVariable("id") Long id) {
        logger.info("Fetching & Deleting User with id {}", id);

        FoodCategory currentFoodCategory = foodCategoryRepo.findOne(id);
        if (currentFoodCategory == null) {
            logger.error("Unable to delete. FoodCategory with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        foodCategoryRepo.delete(id);
        return new ResponseEntity<FoodCategory>(currentFoodCategory,HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Food Items-----------------------------

    
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<FoodCategory> deleteAllFoodCategory() {
        logger.info("Deleting All Users");

        foodCategoryRepo.deleteAll();
        return new ResponseEntity<FoodCategory>(HttpStatus.NO_CONTENT);
    }
}
