package de.pwc.digispace.restaurant.backend.entities;

import java.time.LocalDateTime;

public class Invoice {
	
	private PaymentMethod paymentMethod;
	
	private LocalDateTime datePaid;
	
	private Order order;

	public Invoice(Order order, PaymentMethod paymentMethod) {
		super();
		order.setOpen(false);
		order.setOccupied(false);
		this.order = order;
		this.paymentMethod = paymentMethod;
		this.datePaid = LocalDateTime.now();
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public LocalDateTime getDatePaid() {
		return datePaid;
	}
	
	
	
	

}
