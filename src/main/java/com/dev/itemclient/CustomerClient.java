package com.dev.itemclient;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dev.dao.ItemDaoInterface;
import com.dev.dao.CustomerDao;
import com.dev.dao.CustomerDaoInterface;
import com.dev.dao.ItemDao;
import com.dev.model.Customer;
import com.dev.model.Item;

@Path("customers")  // http://localhost:8083/hotel-services/webapi/customers
public class CustomerClient {
	
private CustomerDaoInterface customerDao = new CustomerDao();
	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Customer> getAllCustomers(){
		System.out.println("entered getAllItems");
		return customerDao.getCustomers();
		
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Path("{name}")  //http://localhost:8083/hotel-services/webapi/customers/dosa
	public Customer getItem(@PathParam ("name") String name){
		System.out.println("entered getAllItems");
		return customerDao.getCustomerByName(name);
	}
	
	

}
