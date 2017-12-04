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

import com.ezmenutouch.model.FoodOrder;
import com.ezmenutouch.repositories.OrderRepo;
import com.ezmenutouch.util.CustomErrorType;

@RestController
@RequestMapping("/orders")
public class OrderRestController {
	@Autowired
	private OrderRepo orderRepo;

	public static final Logger logger = LoggerFactory.getLogger(OrderRestController.class);

	@RequestMapping(value = "")
	public List<FoodOrder> list(ModelMap model) {
		return (List<FoodOrder>) orderRepo.findAll();
	}

	// -------------------Retrieve All Orders
	// ---------------------------------------

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<FoodOrder>> listAllUsers() {
		List<FoodOrder> products = (List<FoodOrder>) orderRepo.findAll();
		if (products.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<FoodOrder>>(products, HttpStatus.OK);
	}

	// -------------------Retrieve an FoodOrder
	// ------------------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getOrders(@PathVariable("id") Long id) {
		logger.info("Fetching User with id {}", id);
		FoodOrder product = orderRepo.findOne(id);
		if (product == null) {
			logger.error("Product with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Baby with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FoodOrder>(product, HttpStatus.OK);
	}
	
	// -------------------Retrieve an FoodOrder by Table Name
		// ----------------------------------------------
	@RequestMapping(value = "/byTableName/{tableName}", method = RequestMethod.GET)
	public ResponseEntity<?> getOrdersByTableName(@PathVariable("tableName") String tableName) {
		logger.info("Fetching Order with TableName {}", tableName);
		
		FoodOrder inputOrder = new FoodOrder();
		inputOrder.setTableName(tableName);
		FoodOrder order = orderRepo.findOne(Example.<FoodOrder>of(inputOrder, ExampleMatcher.matching()
	                .withMatcher("tableName", ExampleMatcher.GenericPropertyMatchers.ignoreCase())));
		if (order == null) {
			logger.error("TableName not found.", tableName);
			return new ResponseEntity(new CustomErrorType("Order with tableName " + tableName + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FoodOrder>(order, HttpStatus.OK);
	}

	// -------------------Create an FoodOrder-------------------------------------------

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> createOrder(@RequestBody FoodOrder order, UriComponentsBuilder ucBuilder) {
		logger.info("Creating FoodOrder : {}", order);
		
		orderRepo.save(order);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri());
		return new ResponseEntity<FoodOrder>(order, HttpStatus.CREATED);
	}

	// ------------------- Update an FoodOrder
	// ------------------------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateOrder(@PathVariable("id") Long id, @RequestBody FoodOrder order) {
		logger.info("Updating User with id {}", id);

		FoodOrder currentOrder = orderRepo.findOne(id);

		if (currentOrder == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentOrder.setOrderStatus(order.getOrderStatus());
		orderRepo.save(currentOrder);
		return new ResponseEntity<FoodOrder>(currentOrder, HttpStatus.OK);
	}

	// ------------------- Delete an FoodOrder ------------------------------------

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		FoodOrder currentOrder = orderRepo.findOne(id);
		if (currentOrder == null) {
			logger.error("Unable to delete. FoodOrder with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		orderRepo.delete(id);
		return new ResponseEntity<FoodOrder>(currentOrder, HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Orders-----------------------------

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public ResponseEntity<FoodOrder> deleteAllOrder() {
		logger.info("Deleting All Users");

		orderRepo.deleteAll();
		return new ResponseEntity<FoodOrder>(HttpStatus.NO_CONTENT);
	}
}
