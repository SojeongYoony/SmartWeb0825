package com.koreait.ex04.domain;

public class MemberBuilder {
	
	private Long idx;
	private String id;
	private String name;
	
	// select setters only  -- type : MemberBuilder / return MemberBuilder type of object
	public MemberBuilder setIdx(Long idx) {
		this.idx = idx;
		return this;
	}
	public MemberBuilder setId(String id) {
		this.id = id;
		return this;
	}
	public MemberBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public Member build() {		// Member Type of object
		return new Member(idx, id, name);
	}
	
}
