package com.dev.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="customers")
//@NamedQueries(
	//	@NamedQuery(name=Customer.FIND_CUSTOMER_REPORTS,query="Select new com.dev.model.CustomerReport(c.name,c.description,i.name,i.cost,i.quantity)" +
	//"from Customer c,Item i where c.name = :name and c.id = i.customer.id")
	//	)
public class Customer {
	//public static final String FIND_CUSTOMER_REPORTS = "findCustomerReports";
	@Column(name="name", unique=true)
	private String name;
	private String description;
	@Id
	@GeneratedValue //i's an auto incrementing field
	private int id;
	
	//@OneToMany(mappedBy="customer",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
   // private List<Item> items = new ArrayList<Item>();
    
	//public List<Item> getItems() {
	//	return items;
	//}
	//public void setItems(List<Item> items) {
	//	this.items = items;
	//}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
