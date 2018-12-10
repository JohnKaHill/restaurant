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

import de.pwc.digispace.restaurant.backend.entities.Beverage;
import de.pwc.digispace.restaurant.backend.entities.BeverageType;
import de.pwc.digispace.restaurant.backend.entities.Order;

public class BeverageDAO implements Dao<Beverage>{
	
	public final Logger logger = LoggerFactory.getLogger(BeverageDAO.class);

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	public BeverageDAO() {

	}
	private Connection getConnection() throws SQLException {
		return ConnectionFactory.getInstance().getConnection();
	}
	
	public void add( Beverage beverage ) {
		try {
			String queryString = 
					"INSERT INTO drinks(name, description, price, tax, dateCreated,"
					+ "dateEdited, containsAlcohol, beverageType) VALUES(?,?,?,?,?,?,?,?)";
			connection = getConnection();
			preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setObject(1, beverage.getName());
			preparedStatement.setString(2, beverage.getDescription());
			preparedStatement.setBigDecimal(3, beverage.getPrice());
			preparedStatement.setInt(4, beverage.getTax());
			preparedStatement.setObject(5, beverage.getDateCreated());
			preparedStatement.setObject(6, beverage.getDateEdited());
			preparedStatement.setBoolean(7, beverage.containsAlcohol());
			preparedStatement.setString(8, beverage.getBeverageType().toString());
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
	
	public void update(Beverage beverage) {
		try {
			String queryString = "UPDATE drinks SET name=?, description=?, price=?, tax=?,"
					+ "dateEdited=?, containsAlohol=?, beverageType=? WHERE name=?";
			connection = getConnection();
			preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setObject(1, beverage.getName());
			preparedStatement.setString(2, beverage.getDescription());
			preparedStatement.setBigDecimal(3, beverage.getPrice());
			preparedStatement.setInt(4, beverage.getTax());
			preparedStatement.setObject(5, beverage.getDateEdited());
			preparedStatement.setBoolean(6, beverage.containsAlcohol());
			preparedStatement.setString(7, beverage.getBeverageType().toString());
			int i = preparedStatement.executeUpdate();
			logger.info("{} beverage(s) UPDATED!", i);
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
	
	public void delete(Beverage beverage) {
		try {
			String queryString = "DELETE FROM drinks WHERE name=?";
			connection = getConnection();
			preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, beverage.getName());
			preparedStatement.executeUpdate();
			logger.info("DELETED {} beverage.", beverage.getName());
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
	
	public Beverage findById(String name) {
		try {
			String queryString = "SELECT FROM drinks where name=?";
			connection = getConnection();
			preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			Beverage beverage = new Beverage(resultSet.getString("name"), resultSet.getString("description"),
					resultSet.getBigDecimal("price"), resultSet.getInt("tax"), resultSet.getObject("dateCreated"),
					resultSet.getObject("dateEdited"), BeverageType.valueOf(resultSet.getString("beverageType")), resultSet.getBoolean("containsAlcohol"));
			logger.info("orderId: {}\ntable Number: {}\ndateCreated: {}\n\n", 
					resultSet.getObject("orderId"), resultSet.getInt("tableNumber"), 
					resultSet.getObject("dateCreated");
			return beverage;
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
	
	public List<Beverage> findAll() {
		List<Beverage> drinks = new ArrayList<>();
		try {
			String queryString = "SELECT * FROM orders";
			connection = getConnection();
			preparedStatement = connection.prepareStatement(queryString);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Beverage beverage = new Beverage(resultSet.getObject("orderId"), resultSet.getObject("dateCreated"),
						resultSet.getInt("tableNumber"), resultSet.getBoolean("isOpen"), resultSet.getBoolean("isOccupied"),
						OrderFoodDAO.getById(orderId), OrderBeverageDAO.getById(orderId());
				logger.info("orderId: {}\ntable Number: {}\ndateCreated: {}\n\n", 
						resultSet.getObject("orderId"), resultSet.getInt("tableNumber"), 
						resultSet.getObject("dateCreated");
				drinks.add(beverage);
			}
			return drinks;
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
