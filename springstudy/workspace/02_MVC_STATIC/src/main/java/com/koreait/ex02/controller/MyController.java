package com.koreait.ex02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
	@Controller
	안녕 난 Controller야 서블릿이 아니라 자바 클래스야
*/

@Controller			// controller임을 알려주는 annotation
public class MyController {

	// 메소드 1개 = 요청 1개, 응답 1개
	@RequestMapping(value="/", method = RequestMethod.GET)	// get방식의 요청이라는 뜻
	// value="/"				  : mapping이 컨텍스트 패스이다. (http://localhost:9090/ex02) - 한마디로 
	// method = RequestMethod.GET : GET방식의 요청이다
	
	// 반환타입 : String (View 이름 즉, JSP 이름을 반환한다)
	// 메소드명 : index (아무 일도 안 한다) 		-- 중복되지만 않으면 된다.
	// 매개변수 : 사용자 요청 및 응답을 처리한다
	public String index() {
		return "index";
		
		// return "index"; 는 servlet-context.xml(DispatcherServlet)에 의해서 다음
		// return "/WEB-INF/views/index.jsp"; 로 처리된다.
		//			prefix + index + suffix
		
	}
	
	@RequestMapping(value="imageView.do", method = RequestMethod.GET)
	public String imageView() {
		return "gallery/detail";
		/*  기본 동작은 forward 이다.  */
		/*  /WEB-INF/views/gallery/detail.jsp로 forward로 가겠다   */
	}
	
}
