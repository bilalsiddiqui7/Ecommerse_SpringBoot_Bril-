package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cartdetails")
public class CartItems {
	@Id
	int id;
	@Column(name = "pname")
	String pname;
	@Column(name = "pdesc")
	String pdesc;
	@Column(name = "price")
	String price;
	@Column(name = "image")
	String image;
	@Column(name = "quantity")
	int quantity;

	public CartItems() {

	}

	public CartItems(int id, String pname, String pdesc, String price, String image, int quantity) {
		super();
		this.id = id;
		this.pname = pname;
		this.pdesc = pdesc;
		this.price = price;
		this.image = image;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItems [id=" + id + ", pname=" + pname + ", pdesc=" + pdesc + ", price=" + price + ", image=" + image
				+ ", quantity=" + quantity + "]";
	}
	
	

}
