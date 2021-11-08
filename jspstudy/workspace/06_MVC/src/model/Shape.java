package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public interface Shape {
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response);		
	// 설계의 기본임. 설계도, PM이 작성하고 실제 method를 채우는게 개발자들의 역할.
	// 지금은 void의 반환타입이 없는데 나중에 있는 형태로 바꿀 것이다. --> 반환 타입이 ModelAndView이다.	: 반환값을 ModelAndView 메모리에 저장하여 그것을 전달한다.
}
