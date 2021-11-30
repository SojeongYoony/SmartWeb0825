package com.koreait.ex01.bean02;

public class Car {

	// field
	private String model; //자동차 모델명
	private Engine engine;
	
	// constructor
	// default
	public Car() {
		
	}
	
	// field를 이용한 constructor
	public Car(String model, Engine engine) {
		super();
		this.model = model;
		this.engine = engine;
	}
	
	// getter / setter		-- VERY IMPORTANT : 아래에 있는 것을 PROPERTY TAG가 사용하는 것 이므로 꼭 생성해 둘 것 
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Engine getEngine() {
		return engine;
	}
	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	
	
}
