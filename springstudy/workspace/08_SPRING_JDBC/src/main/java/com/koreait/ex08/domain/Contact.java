package com.koreait.ex08.domain;

public class Contact {
	
	// field
	private int no;
	private String name;
	private String tel;
	private String address;
	private String birthday;
	
	// getters / setter
	public int getNo() {
		return no;
	}
	public String getName() {
		return name;
	}
	public String getTel() {
		return tel;
	}
	public String getAddress() {
		return address;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	private Contact(ContactBuilder builder) {
		this.no = builder.no;
		this.name = builder.name;
		this.tel = builder.tel;
		this.address = builder.address;
		this.birthday = builder.birthday;
	}
	public Contact() {

	}
	
	// ContactBuilder class
	public static class ContactBuilder {

		// field
		private int no;
		private String name;
		private String tel;
		private String address;
		private String birthday;
		
		public ContactBuilder() {

		}
		
		// setters
		public ContactBuilder setNo(int no) {
			this.no = no;
			return this;
		}
		public ContactBuilder setName(String name) {
			this.name = name;
			return this;
		}
		public ContactBuilder setTel(String tel) {
			this.tel = tel;
			return this;
		}
		public ContactBuilder setAddress(String address) {
			this.address = address;
			return this;
		}
		public ContactBuilder setBirthday(String birthday) {
			this.birthday = birthday;
			return this;
		}
		
		// build() : Contact 반환
		public Contact build() {
			return new Contact(this);
		}
		
	}
	
	
}
