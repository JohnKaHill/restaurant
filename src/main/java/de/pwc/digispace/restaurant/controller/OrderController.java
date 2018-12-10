package de.pwc.digispace.restaurant.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.pwc.digispace.restaurant.backend.dao.OrderDAO;
import de.pwc.digispace.restaurant.backend.entities.Order;

@RestController
@RequestMapping(value="order")
public class OrderController {

	@Autowired
	OrderDAO orderRepository;
	
	@GetMapping( produces = "application/json")
	public List<Order> getOrders(@PathVariable UUID id) {
		return orderRepository.findAll();
	}
	
	@GetMapping(value="/{id}", produces = "application/json")
	public Order getOrder(@PathVariable UUID id) {
		return orderRepository.findById(id);
	}
	
	
	
}
