package de.pwc.digispace.restaurant.backend.entities;

import java.time.LocalDateTime;

public class Invoice {
	
	private PaymentMethodType paymentMethod;
	
	private LocalDateTime datePaid;
	
	private Order order;

	public Invoice(Order order, PaymentMethodType paymentMethod) {
		super();
		order.setOpen(false);
		order.setOccupied(false);
		this.order = order;
		this.paymentMethod = paymentMethod;
		this.datePaid = LocalDateTime.now();
	}

	public PaymentMethodType getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethodType paymentMethod) {
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
