package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class Circle implements Shape {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {


		// 요청 처리
		String strRadius = request.getParameter("radius");
		double radius = Double.parseDouble(strRadius);
		
		double area = Math.PI * radius * radius;
		
		// 결과 저장하기 
		request.setAttribute("area", area);			// 응답View에서 사용
		
		// 응답 처리
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView("views/circle.jsp");
		modelAndView.setRedirect(false);  			// forward
		
		// modelAndView 결과 반환
		return modelAndView;
		
	}

}
