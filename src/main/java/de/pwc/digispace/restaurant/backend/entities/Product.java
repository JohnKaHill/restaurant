package de.pwc.digispace.restaurant.backend.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
		
	private String name;
		
	private String description;
	
	private BigDecimal price;
	
	private int tax;
	
	private LocalDateTime dateCreated;
	
	private LocalDateTime dateEdited;

	public Product(String name, String description, BigDecimal price, int tax, LocalDateTime dateCreated,
			LocalDateTime dateEdited) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.tax = tax;
		this.dateCreated = dateCreated;
		this.dateEdited = dateEdited;
	}

	public Product(String name, BigDecimal price, int tax, String description) {
		super();
		this.name = name;
		this.price = price;
		this.tax = tax;
		this.description = description;
		this.dateCreated = LocalDateTime.now();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public LocalDateTime getDateEdited() {
		return dateEdited;
	}

	public void setDateEdited(LocalDateTime dateEdited) {
		this.dateEdited = dateEdited;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	
	

}
