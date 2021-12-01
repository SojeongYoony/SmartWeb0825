package com.koreait.ex03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.ex03.domain.Board;

@Controller
public class BoardController {

	
	// ctx 없이 bean 생성하기
	
	// 1. @Inject, @Resource, @Autowired와 같은 애너테이션 중 하나를 사용한다 (annotation 사용한다.)
	// 2. @Autowired를 가장 많이 사용한다.
	
	// @Autowired
	// 1. 객체 타입(class)이 일치하는 객체를 찾아서 알아서 생성한다.
	// 2. 필드, 생성자, setter를 대상으로 함
	// 3. 필드는 필드 자체에 생성되고, 생성자나 setter는 매개변수에 생성

	// field
	@Autowired
	private Board myBoard; // 반환타입이 Board인 Bean을 찾아서 가져와서 myBoard에 주입 --> Dependency Injection(DI) ** 중요한 용어
	
	@GetMapping("boardView.do")
	public String a(Model model) {
		model.addAttribute("board", myBoard);
		return "board/boardDetail";
	}
	
}
