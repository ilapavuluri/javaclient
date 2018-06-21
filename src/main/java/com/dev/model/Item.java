package com.dev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Range;

@XmlRootElement
@Entity
@Table(name="items")

public class Item {
	
	@Column(name="name", unique=true)
	private String name;
	private String description;
	
	@Range(min=5, max=100)
	private int cost;
	
	@Id
	@GeneratedValue //i's an auto incrementing field
	@Column(name="ITEM_ID")
	private int id;
	
	

	//@ManyToOne
	//private Customer customer;
	
	//public Customer getCustomer() {
	//	return customer;
	//}
	//public void setCustomer(Customer customer) {
	//	this.customer = customer;
	//}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name="desc")
	public String getDescription() {
		return description;
	}
	public Item(){
		
	}
	public Item(String name, String description, int cost) {
		super();
		this.name = name;
		this.description = description;
		this.cost = cost;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
	@PrePersist
	@PreUpdate
	private void validate(){
		if(description==null || "".equals(description)){
			throw new IllegalArgumentException("invalid description");
		}
		if(cost<1){
			throw new IllegalArgumentException("invalid cost");
		}
	}
	
	@PostLoad
	@PostPersist
	@PostUpdate
	private void updateCalFields(){
		//
	}
	

}
