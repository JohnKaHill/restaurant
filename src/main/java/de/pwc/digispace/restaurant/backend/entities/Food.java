package de.pwc.digispace.restaurant.backend.entities;

import java.math.BigDecimal;

/**
 * 
 * @author john
 * Implements Products that can be ordered by customers
 * created: 30.11.2018
 *
 */
public class Food extends Product {
		
	private FoodType foodCategory;

	private boolean containsMeat;

	public Food(String name, BigDecimal price, int tax, FoodType foodCategory, boolean containsMeat, String description) {
		super(name, price, tax, description);
		this.containsMeat = containsMeat;
		this.foodCategory = foodCategory;
	}

	public FoodType getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(FoodType foodCategory) {
		this.foodCategory = foodCategory;
	}

	public boolean containsMeat() {
		return containsMeat;
	}

	public void containsMeat(boolean containsMeat) {
		this.containsMeat = containsMeat;
	}	
	
	

}
