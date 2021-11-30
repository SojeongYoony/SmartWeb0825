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
		
		// 제어의 역전
		// IoC : Inversion of Control
		// 기존에는 개발자가 new를 이용해서 직접 객체를 생성했지만,
		// 스프링은 스프링이 객체를 생성하고 개발자는 가져다 사용한다. 는 의미이다.	
		// -- 여전히 new를 사용하여 객체 생성이 가능하지만 new는 java에서 도는 것 이라 version 상관없이 어디서든 사용 가능하지만, 
		// 	  spring방식으로 객체를 생성하게 되면, 다른 버전에서 안 먹힐 수도 있다.		]
		// -- new를 이용하여 객체를 생성하는 것이 나쁜 것은 아니지만, 손이 많이 가므로 spring객체 생성 법으로 만드는 것이 좋다
		// 요점 : 어떤 방법을 사용하던 편한 방법을 이용하면 됨.
		
		EngineerCalculator e1 = ctx.getBean("eCalculator1", EngineerCalculator.class);	// class는 .class라고 지정해준다.
		e1.add();
		e1.sub();
		e1.multiply();
		e1.div();
		e1.mod();
		System.out.println("---------------");
		
		EngineerCalculator e2 = ctx.getBean("eCalculator2", EngineerCalculator.class);
		e2.add();
		e2.sub();
		e2.multiply();
		e2.div();
		e2.mod();
		System.out.println("---------------");
		
		EngineerCalculator e3 = ctx.getBean("eCalculator3", EngineerCalculator.class);	// 궁극적으로 외울 필요는 없다 단, 알아두면 좋다
		e3.add();
		e3.sub();
		e3.multiply();
		e3.div();
		e3.mod();
		System.out.println("---------------");
		
		EngineerCalculator e4 = ctx.getBean("eCalculator4", EngineerCalculator.class);
		e4.add();
		e4.sub();
		e4.multiply();
		e4.div();
		e4.mod();
		
		ctx.close(); // 닫아줄 것 
		
		
		
	}

}
