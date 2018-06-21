package com.dev.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.dev.model.Item;

@Repository("itemDao")
public class ItemDao implements ItemDaoInterface {

	@PersistenceContext(unitName = "punit")
	 private EntityManager em;

	
	/* (non-Javadoc)
	 * @see com.dev.repository.ItemRepository#findAllItems()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public  List<Item> findAllItems(){
		Query q = em.createQuery("select i from Item i");
		List<Item> itemList = new ArrayList<Item>();
		itemList = q.getResultList();
		return itemList;
	}

	@Override
	public Item getItemByName(String name) {
		Query q = em.createQuery("select i from Item i WHERE i.name = :name");
		q.setParameter("name", name);
		Item item = (Item)q.getSingleResult();
		return item;
	}

	@Transactional
	public Item createItem(Item item) {		
		em.persist(item);
		em.flush();
		return item;
	}

	@Override
	@Transactional
	public Item updateItem(String name, String description, int cost, int quantity) {
		Item item = getItemByName(name);
		if(item.getDescription()!=description){
			item.setDescription(description);
		}
		if(item.getCost()!=cost){
			item.setCost(cost);
		}
		
		em.merge(item);
		em.flush();
		return item;
	}

	@Override
	@Transactional
	public Item deleteItem(String name) {
		Item item = getItemByName(name);
		em.remove(item);
		em.flush();
		return item;
	}

}
