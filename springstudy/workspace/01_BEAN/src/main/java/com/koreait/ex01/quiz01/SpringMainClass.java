package com.koreait.ex01.quiz01;

import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("quiz01.xml");

		Student student = ctx.getBean("student", Student.class);
		
		System.out.println("name : " + student.getName());
		System.out.println("age : " + student.getAge());
		System.out.println("score : " + student.getExam().getScores().toString());
		System.out.println("average : " + student.getExam().getAverage());
		System.out.println("grade : " + student.getExam().getGrade());
		
		/* 강사님이 하신 방법은, for문을 사용하여 key / value를 함께 사용하는 방법이다 */
		for (Map.Entry<String, String> entry : student.getHome().entrySet()) {
			System.out.println("homes's " + entry.getKey() + " : " + entry.getValue());
		}
		
		/* 내가 작성한 방법은 그냥 불러오는 방식이다 key값과 value값을 함께 사용하는 방법을 학습하도록 하자 */
		System.out.println("------------------- 소정의 방식 -------------------");
		System.out.println("homes's address : " + student.getHome().get("address"));
		System.out.println("homes's tel : " + student.getHome().get("tel"));
		
		ctx.close();
		
	}
	
	
}
