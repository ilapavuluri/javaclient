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

import com.dev.dao.ItemDaoInterface;
import com.dev.dao.CustomerDaoInterface;
//import com.dev.dao.CustomerDao;
//import com.dev.dao.CustomerDaoInterface;
import com.dev.dao.ItemDao;
//import com.dev.model.Customer;
import com.dev.model.Item;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Path("items")// http://localhost:8083/hotel-services/webapi/items
public class ItemService {
	
	private static ItemDaoInterface itemDao;
	
	@Autowired
    public void setItemDao(ItemDaoInterface itemDao) {
		
		System.out.println("called setItemRepository.....");
		System.out.println("Object hashcode: " + this.hashCode());
		if(itemDao == null) {
			System.out.println("itemRepository is not getting initialized....");
		}
        this.itemDao = itemDao;
    }
	
	public static ItemDaoInterface getItemDao(){
		return itemDao;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Item> getAllItems(){
		List<Item> items = itemDao.findAllItems();
		return items;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("{name}")//http://localhost:8083/hotel-services/webapi/items/dosa
	//here we can either send the response in response format like below else we can directly send the activity also
	public Item getItem(@PathParam("name") String itemName){
		if(itemName == null || itemName.length() < 3) {
		}
		System.out.println("Getting itemName: " +itemName);
		Item item =  itemDao.getItemByName(itemName);
		if(item == null) {
		}
		return item;
	}
	
	@POST
	@Path("item")//this is concatination to what is there above in the class path(http://localhost:8083/hotel-services/webapi/items/item)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)//this consumes the url
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})//the return type will be....
	public Item createItem(MultivaluedMap<String,String> formParams){
		Item item = new Item();
		item.setName(formParams.getFirst("name"));
		int cost = Integer.parseInt(formParams.getFirst("cost"));
		item.setCost(cost);
		item.setDescription(formParams.getFirst("description"));
		itemDao.createItem(item);
		return item;
	}
	
	@POST
	@Path("updateitem")//this is concatination to what is there above in the class path(http://localhost:8083/hotel-services/webapi/customer/updatecustomer)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)//this consumes the url
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})//the return type will be....
	public Item updateItem(MultivaluedMap<String,String> formParams){
		Item item = itemDao.updateItem(formParams.getFirst("name"),formParams.getFirst("description"),Integer.parseInt(formParams.getFirst("cost")),
				Integer.parseInt(formParams.getFirst("quantity")));
		return item;
	}
	
	@POST
	@Path("deleteitem")//this is concatination to what is there above in the class path(http://localhost:8083/hotel-services/webapi/customer/deletecustomer)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)//this consumes the url
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})//the return type will be....
	public Item deleteCustomer(MultivaluedMap<String,String> formParams){
		Item item = itemDao.deleteItem(formParams.getFirst("name"));
		return item;
	}

}

