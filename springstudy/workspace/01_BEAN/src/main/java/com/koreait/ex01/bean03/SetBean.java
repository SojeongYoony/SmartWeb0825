package com.koreait.ex01.bean03;

import java.util.Set;

public class SetBean {
	
	// field
	private Set<String> set;

	// constructor
	// 디폴트 생성자

	// getter / setter
	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}
	
	// method
	public void setInfo() {
		// Set은 인덱스가 없다. (순서가 없는 자료형이기 때문에) -- 오직 향상 for문만 가능하다 cuz set has no index : 특징 중복되지 않는다.
		for (String str : set) {	// set에 있는 걸 하나씩 빼서 str로 넘겨준다
			if(str != null) {
				System.out.println(str);
			}
		}
	}
	
}
