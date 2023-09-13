package com.online.book.store.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.online.book.store.configuration.OrderRegistration;

public interface OrderRepo extends CrudRepository<OrderRegistration, Integer>
{
	List<OrderRegistration> findAll();
	
	public List<OrderRegistration> findByBusername(String busername);

}
