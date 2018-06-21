package com.dev;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev.dao.ItemDaoInterface;
import com.dev.dao.OrderDetailsDao;
import com.dev.dao.OrderDetailsDaoInterface;
import com.dev.model.Customer;
import com.dev.model.Item;
import com.dev.model.OrderDetails;

@Path("orderDetails")// http://localhost:8083/hotel-services/webapi/orderDetails
public class OrderDetailsService {
	private static OrderDetailsDaoInterface orderDetailsDao;
	@Autowired
    public void setOrderDetailsDao(OrderDetailsDaoInterface orderDetailsDao) {
		
		System.out.println("called setItemRepository.....");
		System.out.println("Object hashcode: " + this.hashCode());
		if(orderDetailsDao == null) {
			System.out.println("orderdetails is not getting initialized....");
		}
        this.orderDetailsDao = orderDetailsDao;
    }
	
	public static OrderDetailsDaoInterface getItemDao(){
		return orderDetailsDao;
	}

	
	@POST
	@Path("create")//this is concatination to what is there above in the class path(http://localhost:8083/hotel-services/webapi/orderDetails/create)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)//this consumes the url
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})//the return type will be....
	@Secured
	public Response createOrder(MultivaluedMap<String,String> formParams){
		OrderDetails orderDetails = new OrderDetails();
		String customerName = formParams.getFirst("customerName");
		String itemName = formParams.getFirst("itemName");
		Double quantity = (double) Integer.parseInt(formParams.getFirst("quantity"));
		orderDetails.setQuantity(quantity);
		Customer customer = null;
		if(CustomerService.getCustomerDao()!=null){
				customer = CustomerService.getCustomerDao().getCustomerByName(customerName);
		}
		orderDetails.setCustomer(customer);
		Item item  = null;
		if(ItemService.getItemDao()!=null){
			item = ItemService.getItemDao().getItemByName(itemName);
		}
		orderDetails.setCost(quantity*item.getCost());
		orderDetails.setCustomer_name(customerName);
		orderDetails.setItem_name(itemName);
		orderDetails.setItem(item);
		orderDetailsDao.createOrder(orderDetails);
		return Response.ok().entity(orderDetails).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		//return orderDetails;
	}
	
	@GET
	//@SecurityChecked
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response getAllOrders(){
		List<OrderDetails> orders = orderDetailsDao.findAllOrders();
		return Response.ok().entity(orders).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		//return orders;
	}
	
	
}
