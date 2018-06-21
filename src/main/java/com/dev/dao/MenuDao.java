package com.dev.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.model.Menu;

@Repository("menuDao")
public class MenuDao implements MenuDaoInterface{
	
	@PersistenceContext(unitName = "punit")
	 private EntityManager em;

	@Override
	public List<Menu> getMenuList() {
		Query q = em.createQuery("select m from Menu m");
		List<Menu> menuList = new ArrayList<Menu>();
		menuList = q.getResultList();
		return menuList;
	}

	@Override
	@Transactional
	public Menu createMenuItem(Menu menuItem) {
		em.persist(menuItem);
		em.flush();
		return menuItem;
	}

}
