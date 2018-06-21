package com.dev.itemclient;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.dao.ItemDaoInterface;
import com.dev.dao.ItemDao;
import com.dev.model.Item;

//@Path("items")

public class ItemClient {

	private Client client;
	
	@Autowired
	private ItemDaoInterface itemDao;
	public ItemClient(){
		client = ClientBuilder.newClient();
	}
	
	public List<Item> getAllItems(){
		WebTarget target = client.target("http://localhost:8083/hotel-services/webapi/");
		List<Item> response = target.path("items").request(MediaType.APPLICATION_JSON).get(List.class);
		System.out.println("entered getAllItems");
		return response;
	}
	
	
	//@Transactional	
	public Item createItem(Item item){
		WebTarget target = client.target("http://localhost:8083/hotel-services/webapi/");
		Response response = target.path("items/item").
				request(MediaType.APPLICATION_JSON).
				post(Entity.entity(item, MediaType.APPLICATION_JSON));
		if(response.getStatus()!=200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server ");
		}
		return response.readEntity(Item.class);
		
	}
	
	
	public Item getItem(@PathParam ("name") String name){
		WebTarget target = client.target("http://localhost:8083/hotel-services/webapi/");
		Response response = target.path("items/" + name).request(MediaType.APPLICATION_JSON).get(Response.class);
		return response.readEntity(Item.class);
	}
}
