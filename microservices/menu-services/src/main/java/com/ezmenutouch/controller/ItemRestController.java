package com.ezmenutouch.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import com.ezmenutouch.util.CustomErrorType;
import com.eztmenutouch.services.ItemService;

@RestController
@RequestMapping("/fooditems")
public class ItemRestController {
    @Autowired
    private ItemService foodItemService;

    public static final Logger logger = LoggerFactory.getLogger(ItemRestController.class);

    @RequestMapping(value = "")
    public List<FoodItem> list(ModelMap model){
       return foodItemService.findAll();
    }

    // -------------------Retrieve All Babies ---------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllFoodItems() {
        List<FoodItem> items =  foodItemService.findAll();
        if (items.isEmpty()) {
            return new ResponseEntity<CustomErrorType>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<FoodItem>>(items, HttpStatus.OK);
    }

    // -------------------Retrieve one Baby------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getFoodItems(@PathVariable("id") Long id) {
        logger.info("Fetching User with id {}", id);
        FoodItem item = foodItemService.find(id);
        if (item == null) {
            logger.error("Product with id {} not found.", id);
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Item with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<FoodItem>(item, HttpStatus.OK);
    }

    // -------------------Create a FoodItem-------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createFoodItem(@RequestBody FoodItem fooditem, UriComponentsBuilder ucBuilder) {
        logger.info("Creating FoodItem : {}", fooditem);
        FoodItem createdFoodItem = foodItemService.add(fooditem);
        return new ResponseEntity<FoodItem>(createdFoodItem, HttpStatus.CREATED);
    }

    // ------------------- Update a User ------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateFoodItem(@PathVariable("id") Long id, @RequestBody FoodItem fooditem) {
        logger.info("Updating Item with id {}", id);
        FoodItem currentFoodItem = foodItemService.find(id);
        if (currentFoodItem == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        foodItemService.update(fooditem);
        return new ResponseEntity<FoodItem>(fooditem, HttpStatus.OK);
    }

    // ------------------- Delete a Food Item ------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFoodItem(@PathVariable("id") Long id) {
        logger.info("Fetching & Deleting User with id {}", id);

        FoodItem currentFoodItem = foodItemService.find(id);
        if (currentFoodItem == null) {
            logger.error("Unable to delete. User with id {} not found.", id);
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        foodItemService.delete(id);
        return new ResponseEntity<FoodItem>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Users-----------------------------

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<FoodItem> deleteAllFoodItem() {
        logger.info("Deleting All Users");

      //  foodItemService.deleteAll();
        return new ResponseEntity<FoodItem>(HttpStatus.NO_CONTENT);
    }
}
