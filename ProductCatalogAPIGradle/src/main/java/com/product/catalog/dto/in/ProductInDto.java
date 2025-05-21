package com.product.catalog.dto.in;

public class ProductInDto {

	private String productName;
	private String description;
	private String assignedTo;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	@Override
	public String toString() {
		return "ProductInDto [productName=" + productName + ", description=" + description + ", assignedTo="
				+ assignedTo + "]";
	}
	
	public ProductInDto() {
	}
	public ProductInDto(String productName, String description, String assignedTo) {
		super();
		this.productName = productName;
		this.description = description;
		this.assignedTo = assignedTo;
	}
	
	
}
