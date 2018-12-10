package de.pwc.digispace.restaurant.backend.dao;

import java.util.List;
import java.util.UUID;

import de.pwc.digispace.restaurant.backend.entities.Order;

public interface Dao<T> {
	
	void add(T t);
	void update(T t);
	void delete(T t);
	List<T> findAll();
	T findById(UUID id);

}
