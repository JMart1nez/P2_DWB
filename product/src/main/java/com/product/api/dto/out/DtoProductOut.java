package com.product.api.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DtoProductOut {

	@JsonProperty("product_id")
	private Integer product_id;

	@JsonProperty("gtin")
	private String gtin;

	@JsonProperty("product")
	private String product;

	@JsonProperty("description")
	private String description;

	@JsonProperty("price")
	private Float price;

	@JsonProperty("stock")
	private Integer stock;

	@JsonProperty("category")
	private String category;

	@JsonProperty("images")
	private String images;

	@JsonProperty("status")
	private Integer status;

	public DtoProductOut() {
	}

	public DtoProductOut(Integer product_id, String gtin, String product, String description, Float price,
			Integer stock, String category, String images, Integer status) {
		this.product_id = product_id;
		this.gtin = gtin;
		this.product = product;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.category = category;
		this.images = images;
		this.status = status;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}