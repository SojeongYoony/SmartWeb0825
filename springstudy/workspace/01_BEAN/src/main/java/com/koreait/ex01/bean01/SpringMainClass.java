package com.koreait.ex01.bean01;

import org.springframework.context.support.AbstractApplicationContext;		// Spring Class임
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {

		// GenericXmlApplicationContext 클래스	-- 기존 자바의 Class 명을 피해 만들다 보니 이름이 길다.
		
		// 1. spring bean configuration file이 만든 <bean>을 생성하는 스프링 클래스	** SpringClass임 주의 JavaClass아님 주의
		// 2. AbstractApplicationContext 클래스의 자식 클래스
		
		String resourceLocations = "classpath:app-context1.xml";		// classpath: 생락 가능  -- file 명 지정
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		// 스프링은 app-context1.xml에 만들어 둔 <bean> 태그를 모두 bean으로 생성해서 가지고 있음.
		// getBean() 메소드를 이용해서 생성된 bean을 가져옴.
		
		EngineerCalculator e1 = ctx.getBean("eCalculator1", EngineerCalculator.class);	// class는 .class라고 지정해준다.
		e1.add();
		e1.sub();
		e1.multiply();
		e1.div();
		e1.mod();

		ctx.close(); // 닫아줄 것 
		
		
		
	}

}
