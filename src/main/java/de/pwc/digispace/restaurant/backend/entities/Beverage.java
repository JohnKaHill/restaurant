package de.pwc.digispace.restaurant.backend.entities;

/**
 * 
 * @author john
 * Implements Products that can be ordered by customers
 * created: 30.11.2018
 *
 */
public class Beverage extends Product {
		
	private BeverageType beverageType;

	private boolean containsAlcohol;

	public Beverage(String name, double price, int tax, BeverageType beverageType, boolean containsAlcohol, String description) {
		super(name, price, tax, description);
		this.containsAlcohol = containsAlcohol;
		this.beverageType = beverageType;
	}

	public BeverageType getBeverageType() {
		return beverageType;
	}

	public void setBeverageType(BeverageType beverageType) {
		this.beverageType = beverageType;
	}

	public boolean isContainsAlcohol() {
		return containsAlcohol;
	}

	public void setContainsAlcohol(boolean containsAlcohol) {
		this.containsAlcohol = containsAlcohol;
	}
	

}
