package de.pwc.digispace.restaurant.backend.dao;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import de.pwc.digispace.restaurant.backend.entities.Order;

public class OrderDAO {
	
	public final Logger logger = LoggerFactory.getLogger(OrderDAO.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	// GET order by Id /order/{orderId}
	public Order findById( UUID orderId) {
		return jdbcTemplate.queryForObject("SELECT * FROM orders WHERE id=?",
				new Object[] { orderId }, new BeanPropertyRowMapper<Order>(Order.class));
	}

	// GET all orders /order
	public List<Order> findAll() {
		List<Order> orders = jdbcTemplate.query("SELECT * FROM orders", new BeanPropertyRowMapper<Order>(Order.class));
		for(Order order : orders) {
			logger.info(Integer.toString(order.getTableNumber()));
		}
		return orders;
	}
	
	// POST new order with Id /order/{orderId}
	public void updateOrder( UUID orderId ) {
//		jdbcTemplate.
	}
	
	
}
