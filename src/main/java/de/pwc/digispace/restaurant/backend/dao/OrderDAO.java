package de.pwc.digispace.restaurant.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.pwc.digispace.restaurant.backend.entities.Order;

public class OrderDAO implements Dao<Order>{
	
	public final Logger logger = LoggerFactory.getLogger(OrderDAO.class);

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	public OrderDAO() {

	}
	private Connection getConnection() throws SQLException {
		return ConnectionFactory.getInstance().getConnection();
	}
	
	public void add( Order order ) {
		try {
			String queryString = 
					"INSERT INTO orders(orderId, dateCreated, tableNumber,"
					+ "isOpen, isOccupied) VALUES(?,?,?,?,?)";
			connection = getConnection();
			preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setObject(1, order.getOrderId());
			preparedStatement.setObject(2, order.getDateCreated());
			preparedStatement.setInt(3, order.getTableNumber());
			preparedStatement.setBoolean(4, order.isOpen());
			preparedStatement.setBoolean(5, order.isOccupied());
			int i = preparedStatement.executeUpdate();
			logger.info("{} orders added successfully", i);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(Order order) {
		try {
			String queryString = "UPDATE orders SET tableNumber=?, "
					+ "isOpen=?, isOccupied=? WHERE orderId=?";
			connection = getConnection();
			preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setInt(1, order.getTableNumber());
			preparedStatement.setBoolean(2, order.isOpen());
			preparedStatement.setBoolean(3, order.isOccupied());
			preparedStatement.setObject(4, order.getOrderId());
			int i = preparedStatement.executeUpdate();
			logger.info("{} orders UPDATED!", i);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete(Order order) {
		try {
			String queryString = "DELETE FROM orders WHERE orderid=?";
			connection = getConnection();
			preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setObject(1, order.getOrderId());
			int i = preparedStatement.executeUpdate();
			logger.info("DELETED {} order.", i);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Order findById(UUID orderId) {
		try {
			String queryString = "SELECT FROM orders where orderId=?";
			connection = getConnection();
			preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setObject(1, orderId);
			resultSet = preparedStatement.executeQuery();
			Order order = new Order(resultSet.getObject("orderId"), resultSet.getObject("dateCreated"),
					resultSet.getInt("tableNumber"), resultSet.getBoolean("isOpen"), resultSet.getBoolean("isOccupied"),
					OrderFoodDAO.getById(orderId), OrderBeverageDAO.getById(orderId());
			logger.info("orderId: {}\ntable Number: {}\ndateCreated: {}\n\n", 
					resultSet.getObject("orderId"), resultSet.getInt("tableNumber"), 
					resultSet.getObject("dateCreated");
			return order;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Order> findAll() {
		List<Order> orders = new ArrayList<>();
		try {
			String queryString = "SELECT * FROM orders";
			connection = getConnection();
			preparedStatement = connection.prepareStatement(queryString);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order(resultSet.getObject("orderId"), resultSet.getObject("dateCreated"),
						resultSet.getInt("tableNumber"), resultSet.getBoolean("isOpen"), resultSet.getBoolean("isOccupied"),
						OrderFoodDAO.getById(orderId), OrderBeverageDAO.getById(orderId());
				logger.info("orderId: {}\ntable Number: {}\ndateCreated: {}\n\n", 
						resultSet.getObject("orderId"), resultSet.getInt("tableNumber"), 
						resultSet.getObject("dateCreated");
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
		
}
