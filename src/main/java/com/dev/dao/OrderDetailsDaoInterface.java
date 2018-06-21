package com.dev.dao;

import java.util.List;

import com.dev.model.OrderDetails;

//import com.dev.model.Menu;

public interface OrderDetailsDaoInterface {

	//List<OrderDetails> getMenuList();

	OrderDetails createOrder(OrderDetails orderDetails);

	List<OrderDetails> findAllOrders();
	
}
