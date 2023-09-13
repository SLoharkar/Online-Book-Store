package com.online.book.store.configuration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderRegistration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long OID;
	
	String Book_name;
	
	String Price;
	
	String busername;
	

	public String getBusername() {
		return busername;
	}

	public void setBusername(String busername) {
		this.busername = busername;
	}

	public Long getOID() {
		return OID;
	}
	
	public String getBook_name() {
		return Book_name;
	}

	public void setBook_name(String book_name) {
		Book_name = book_name;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public void setOID(Long oID) {
		OID = oID;
	}

	
}
