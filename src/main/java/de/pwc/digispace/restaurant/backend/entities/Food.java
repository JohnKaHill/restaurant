package de.pwc.digispace.restaurant.backend.entities;

/**
 * 
 * @author john
 * Implements Products that can be ordered by customers
 * created: 30.11.2018
 *
 */
public class Food extends Product {
		
	private FoodCategory foodCategory;

	private boolean containsMeat;

	public Food(String name, double price, int tax, FoodCategory foodCategory, boolean containsMeat, String description) {
		super(name, price, tax, description);
		this.containsMeat = containsMeat;
		this.foodCategory = foodCategory;
	}

	public FoodCategory getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(FoodCategory foodCategory) {
		this.foodCategory = foodCategory;
	}

	public boolean isContainsMeat() {
		return containsMeat;
	}

	public void setContainsMeat(boolean containsMeat) {
		this.containsMeat = containsMeat;
	}	
	
	

}
