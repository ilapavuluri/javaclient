package com.dev.dao;

import java.util.List;

import com.dev.model.Item;

public interface ItemDaoInterface {

	List<Item> findAllItems();

	Item getItemByName(String name);
	Item createItem(Item item);
	Item updateItem(String name,String description,int cost,int quantity);
	Item deleteItem(String name);

}