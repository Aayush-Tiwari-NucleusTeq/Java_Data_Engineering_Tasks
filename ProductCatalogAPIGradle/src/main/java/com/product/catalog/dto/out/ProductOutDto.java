package com.product.catalog.dto.out;

public class ProductOutDto {
	
	private int productId;
	private String productName;
	private String description;
	private String assignedTo;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
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
		return "ProductOutDto [productId=" + productId + ", productName=" + productName + ", description=" + description
				+ ", assignedTo=" + assignedTo + "]";
	}
	
	public ProductOutDto() {
	}
	public ProductOutDto(int productId, String productName, String description, String assignedTo) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.assignedTo = assignedTo;
	}
	
}
