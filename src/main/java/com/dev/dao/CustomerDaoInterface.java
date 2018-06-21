package com.dev.dao;

import java.util.List;


import com.dev.model.Customer;
import com.dev.model.Item;
import com.dev.model.CustomerReport;

public interface CustomerDaoInterface {
	
	void createCustomer(Customer customer);
	Customer getCustomerByName(String customerName);
	Customer updateCustomer(String customerName,String customerDescription);
	Customer deleteCustomer(String customerName);
	List<Customer> getCustomers();
	List<CustomerReport> getCustomerReport(String first);

}
