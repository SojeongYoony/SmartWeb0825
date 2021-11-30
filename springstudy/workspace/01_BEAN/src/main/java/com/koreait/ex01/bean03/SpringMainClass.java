package com.koreait.ex01.bean03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("app-context3.xml");
		
		ListBean listBean = ctx.getBean("listBean", ListBean.class);
		System.out.println("--- 분식 메뉴 ---");
		listBean.listInfo();
		
		ListBean japaneseFood = ctx.getBean("japaneseFood", ListBean.class);
		System.out.println("--- 일본 음식 ---");
		japaneseFood.listInfo();
		
		SetBean setBean = ctx.getBean("setBean", SetBean.class);	// 기억 : set을 이용하면 중복값이 나오지 않는다.
		System.out.println("--- SET TEST ---");
		setBean.setInfo();
		
		MapBean mapBean = ctx.getBean("mapBean", MapBean.class);
		System.out.println("--- MAP TEST ---");
		mapBean.mapInfo();
		
		ctx.close();
		
	}

}
