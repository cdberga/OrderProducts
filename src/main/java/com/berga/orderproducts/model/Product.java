package com.berga.orderproducts.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	private Integer id;
	private String name;
	private String description;
	private BigDecimal price;
	private Category category;
	
	public Product() {
	}
	
	public Product(String name, String description, BigDecimal price, Category category) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	@Id
	@GeneratedValue
	@Column(name="PRODUCT_ID")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
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
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Product: Name: " + name + " - Id: " + id + " - " + description + " - R$ " + price + " - " + category;
	}
}
