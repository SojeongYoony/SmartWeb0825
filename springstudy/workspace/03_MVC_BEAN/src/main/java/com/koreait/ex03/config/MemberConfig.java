package com.koreait.ex03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.koreait.ex03.domain.Member;

@Configuration  			// 가장 먼저 해야 할 annotation
public class MemberConfig {

	@Bean				// Bean을 만드는 annotation
	public Member member1() {		// member1객체 : set method를 이용하여 해당 field값을 채워주는 방법
		Member member = new Member();
		member.setId("admin");
		member.setPw("1234");
		return member;
	}
	
	@Bean
	public Member member2() {		// member2객체 : 직접 실어주는 방법
		return new Member("webmaster", "5678");
	}
	
	
	
	
	
}
