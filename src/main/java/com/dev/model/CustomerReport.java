package com.dev.model;

import java.util.List;

public class CustomerReport {
	
	private String customerName;
	private String customerDescription;
	private String itemName;
	private int cost;
	private int quantity;
	
	public String getCustomerName() {
		return customerName;
	}
	
	public CustomerReport(String customerName,String customerDescription,String itemName,int cost,int quantity){
		this.customerName=customerName;
		this.customerDescription=customerDescription;
		this.itemName=itemName;
		this.cost=cost;
		this.quantity=quantity;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	

	public String getCustomerDescription() {
		return customerDescription;
	}

	public void setCustomerDescription(String customerDescription) {
		this.customerDescription = customerDescription;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
