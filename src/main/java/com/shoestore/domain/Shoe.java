package com.shoestore.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Shoe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String brand;
	private String gtype;
	private String category;
	private String availablesize;
	
	private double listPrice;
	private double ourPrice;
	
	
	@Column(columnDefinition="text")
	private String description;
	private int inStockNumber;
	
	
	@OneToMany(mappedBy= "shoe")
	@JsonIgnore
	private List<ShoeToCartItem> shoeToCartItemList;
	
	@Transient
	private MultipartFile shoeImage;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getGtype() {
		return gtype;
	}

	public void setGtype(String gtype) {
		this.gtype = gtype;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAvailablesize() {
		return availablesize;
	}

	public void setAvailablesize(String availablesize) {
		this.availablesize = availablesize;
	}

	

	public double getListPrice() {
		return listPrice;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	public double getOurPrice() {
		return ourPrice;
	}

	public void setOurPrice(double ourPrice) {
		this.ourPrice = ourPrice;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInStockNumber() {
		return inStockNumber;
	}

	public void setInStockNumber(int inStockNumber) {
		this.inStockNumber = inStockNumber;
	}

	public MultipartFile getShoeImage() {
		return shoeImage;
	}

	public void setShoeImage(MultipartFile shoeImage) {
		this.shoeImage = shoeImage;
	}
	
	public List<ShoeToCartItem> getShoeToCartItemList() {
		return shoeToCartItemList;
	}

	public void setShoeToCartItemList(List<ShoeToCartItem> shoeToCartItemList) {
		this.shoeToCartItemList = shoeToCartItemList;
	}

}
