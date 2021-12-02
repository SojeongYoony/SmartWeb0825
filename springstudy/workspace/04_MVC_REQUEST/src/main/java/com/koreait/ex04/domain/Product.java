package com.koreait.ex04.domain;

public class Product {
	
	private String modelName;	// Board와 비교하였을 때, 모두 선택 요소이다.
	private int price;
	
	// getters only
	public String getModelName() {
		return modelName;
	}
	public int getPrice() {
		return price;
	}
	
	// constructor
	private Product(Builder builder) {
		this.modelName = builder.modelName;
		this.price = builder.price;
	}
	
	// Builder 내부클래스
	public static class Builder {
		
		// field
		private String modelName;
		private int price;
		
		// constructor
		public Builder() {
			
		}
		
		// setters only
		public Builder setModelName(String modelName) {
			this.modelName = modelName;
			return this;
		}
		public Builder setPrice(int price) {
			this.price = price;
			return this;
		}
		
		// build() : Product를 반환함
		public Product build() {
			return new Product(this);
		}
		
	}
	
}
