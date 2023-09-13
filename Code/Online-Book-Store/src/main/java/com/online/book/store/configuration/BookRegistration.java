package com.online.book.store.configuration;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BookRegistration {

	@Id
	String Book_title;
	
	String Author;
	
	String Reviews;
	
	String rate;
	
	String Price;

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getBook_title() {
		return Book_title;
	}

	public void setBook_title(String book_title) {
		Book_title = book_title;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getReviews() {
		return Reviews;
	}

	public void setReviews(String reviews) {
		Reviews = reviews;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	
	
	
	
}
