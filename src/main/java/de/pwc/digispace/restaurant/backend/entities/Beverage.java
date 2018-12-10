package de.pwc.digispace.restaurant.backend.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

	public Beverage(String name, String description, BigDecimal price, int tax, LocalDateTime dateCreated,
			LocalDateTime dateEdited, BeverageType beverageType, boolean containsAlcohol) {
		super(name, description, price, tax, dateCreated, dateEdited);
		this.beverageType = beverageType;
		this.containsAlcohol = containsAlcohol;
	}

	public Beverage(String name, BigDecimal price, int tax, BeverageType beverageType, boolean containsAlcohol, String description) {
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

	public boolean containsAlcohol() {
		return containsAlcohol;
	}

	public void containsAlcohol(boolean containsAlcohol) {
		this.containsAlcohol = containsAlcohol;
	}
	

}
