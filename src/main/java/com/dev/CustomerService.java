package com.dev;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev.dao.CustomerDaoInterface;
import com.dev.dao.ItemDaoInterface;
import com.dev.model.Customer;
import com.dev.model.Item;
import com.dev.model.CustomerReport;

@Path("customer") //http://localhost:8083/hotel-services/webapi/customer
public class CustomerService {
	
	private static CustomerDaoInterface customerDao;
	
	@Autowired
    public void setCustomerDao(CustomerDaoInterface customerDao) {
		if(customerDao == null) {
			System.out.println("customerDao is not getting initialized....");
		}
        this.customerDao = customerDao;
    }
	
	public static CustomerDaoInterface getCustomerDao(){
		return customerDao;
	}
	
	@POST
	@Path("createcustomer")//this is concatination to what is there above in the class path(http://localhost:8083/hotel-services/webapi/customer/createcustomer)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)//this consumes the url
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})//the return type will be....
	public Customer createNewCustomer(MultivaluedMap<String,String> formParams){
		System.out.println(formParams.getFirst("name"));
		System.out.println(formParams.getFirst("description"));
		Customer customer = new Customer();
		customer.setName(formParams.getFirst("name"));
		customer.setDescription(formParams.getFirst("description"));
		customerDao.createCustomer(customer);
		return customer;
	}
	
	@POST
	@Path("updatecustomer")//this is concatination to what is there above in the class path(http://localhost:8083/hotel-services/webapi/customer/updatecustomer)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)//this consumes the url
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})//the return type will be....
	public Customer updateCustomer(MultivaluedMap<String,String> formParams){
		Customer customer = customerDao.updateCustomer(formParams.getFirst("name"),formParams.getFirst("description"));
		return customer;
	}
	
	@POST
	@Path("deletecustomer")//this is concatination to what is there above in the class path(http://localhost:8083/hotel-services/webapi/customer/deletecustomer)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)//this consumes the url
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})//the return type will be....
	public Customer deleteCustomer(MultivaluedMap<String,String> formParams){
		Customer customer = customerDao.deleteCustomer(formParams.getFirst("name"));
		return customer;
	}
	
	
	@GET
	@Path("customers")//this is concatination to what is there above in the class path(http://localhost:8083/hotel-services/webapi/customer/customers)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})//the return type will be....
	public List<Customer> getAllCustomers(){
		List<Customer> customerList = new ArrayList<Customer>();
		customerList = customerDao.getCustomers();
		return customerList;
	}
	
	@GET
	//@Path("customerReport")//this is concatination to what is there above in the class path(http://localhost:8083/hotel-services/webapi/customer/customerReport)
	@Path("{name}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)//this consumes the url
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})//the return type will be....
	public List<CustomerReport> getCustomerReport(@PathParam("name") String customerName){
		List<CustomerReport> customerReport = new ArrayList<CustomerReport>();
		customerReport = CustomerService.getCustomerDao().getCustomerReport(customerName);
		return customerReport;
	}

}
