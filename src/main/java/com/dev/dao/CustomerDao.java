package com.dev.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.model.Customer;
import com.dev.model.CustomerReport;


@Repository("customerDao")
public class CustomerDao implements CustomerDaoInterface{

	
	@PersistenceContext(unitName = "punit",type=PersistenceContextType.EXTENDED)
	 private EntityManager em;
	
	@Override
	@Transactional
	public void createCustomer(Customer customer) {
		em.persist(customer);
		em.flush();
	}
	
	@Override
	@Transactional
	public Customer updateCustomer(String customerName, String description) {
		Customer customer = getCustomerByName(customerName);
		customer.setDescription(description);
		em.merge(customer);
		em.flush();
		return customer;
	}
	
	@Override
	@Transactional
	public Customer deleteCustomer(String customerName) {
		Customer customer = getCustomerByName(customerName);
		em.remove(customer);
		em.flush();
		return customer;
	}

	@Override
	public Customer getCustomerByName(String customerName) {
		Query q = em.createQuery("select c from Customer c WHERE c.name = :name");
		q.setParameter("name", customerName);
		Customer customer = (Customer)q.getSingleResult();
		return customer;
	}
	
	@Override
	public List<Customer> getCustomers() {
		Query q = em.createQuery("select c from Customer c");
		List<Customer> customerList = new ArrayList<Customer>();
		customerList = q.getResultList();
		return customerList;
	}

	@Override
	public List<CustomerReport> getCustomerReport(String customerName) {
	//	Query query = em.createQuery("Select new com.dev.model.CustomerReport(c.name,c.description,i.name,i.cost,i.quantity)" +
	//"from Customer c,Item i where c.name = :name and c.id = i.customer.id");
		//TypedQuery<CustomerReport> query = em.createNamedQuery(Customer.FIND_CUSTOMER_REPORTS,CustomerReport.class);
		//query.setParameter("name", customerName);
		//List<CustomerReport> l = query.getResultList();
		//return l;
		return null;
	}
	

}
