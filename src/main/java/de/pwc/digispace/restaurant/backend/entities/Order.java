package de.pwc.digispace.restaurant.backend.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 
 * @author john
 * Implements Orders and Invoices for the restaurant
 * created: 30.11.2018
 *
 */

public class Order {
		
	private UUID orderId;
	
	private LocalDateTime dateCreated;
	
	private int tableNumber;
	
	private boolean isOpen = true;
	
	private boolean isOccupied = true;
	
	private List<Food> meals;
	
	private List<Beverage> drinks;
			
	public Order(int tableNumber) {
		super();
		orderId = UUID.randomUUID();
		this.dateCreated = LocalDateTime.now();
		this.tableNumber = tableNumber;
	}

	public Order(int tableNumber, List<Beverage> drinks) {
		super();
		orderId = UUID.randomUUID();
		this.dateCreated = LocalDateTime.now();
		this.tableNumber = tableNumber;
		this.drinks = drinks;
	}
	
	public Order(UUID orderId, LocalDateTime dateCreated, int tableNumber, boolean isOpen, boolean isOccupied,
			List<Food> meals, List<Beverage> drinks) {
		super();
		this.orderId = orderId;
		this.dateCreated = dateCreated;
		this.tableNumber = tableNumber;
		this.isOpen = isOpen;
		this.isOccupied = isOccupied;
		this.meals = meals;
		this.drinks = drinks;
	}

	public Order(int tableNumber, List<Food> meals, List<Beverage> drinks) {
		super();
		orderId = UUID.randomUUID();
		this.dateCreated = LocalDateTime.now();
		this.tableNumber = tableNumber;
		this.meals = meals;
		this.drinks = drinks;
	}
	
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public List<Food> getMeals() {
		return meals;
	}

	public void setMeals(List<Food> meals) {
		this.meals = meals;
	}

	public List<Beverage> getDrinks() {
		return drinks;
	}

	public void setDrinks(List<Beverage> drinks) {
		this.drinks = drinks;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
}
