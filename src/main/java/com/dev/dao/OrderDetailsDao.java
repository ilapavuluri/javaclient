package com.dev.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.Secured;
import com.dev.model.Item;
import com.dev.model.OrderDetails;

@Repository("orderDetailsDao")
public class OrderDetailsDao implements OrderDetailsDaoInterface {
	
	@PersistenceContext(unitName = "punit")
	 private EntityManager em;
	
	@Transactional
	@Override
	public OrderDetails createOrder(OrderDetails orderDetails) {		
		em.persist(orderDetails);
		em.flush();
		return orderDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetails> findAllOrders() {
		Query q = em.createQuery("select o from OrderDetails o");
		List<OrderDetails> ordersList = new ArrayList<OrderDetails>();
		ordersList = q.getResultList();
		return ordersList;
	}

}
