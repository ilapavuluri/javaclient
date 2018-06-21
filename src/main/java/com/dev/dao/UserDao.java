package com.dev.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.model.User;

@Repository("userDao")
public class UserDao implements UserDaoInterface{
	@PersistenceContext(unitName = "punit")
	 private EntityManager em;
	 
	@Override
	public List<User> getUsers() {
		Query q = em.createQuery("select u from User u");
		List<User> userList = new ArrayList<User>();
		userList = q.getResultList();
		return userList;
	}
	
	@Override
	public User getUserByName(String userName) {
		Query q = em.createQuery("select c from User c WHERE c.username = :userName");
		q.setParameter("userName", userName);
		User user = (User)q.getSingleResult();
		return user;
	}
	
	@Override
	@Transactional
	public void createUser(User user){
		em.persist(user);
		em.flush();
	}

}
