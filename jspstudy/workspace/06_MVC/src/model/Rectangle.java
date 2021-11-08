package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class Rectangle implements Shape {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {	// request에는 width와 height가 들어 있음.
		
		// 꺼낸다		: (사용자의)요청 처리
		String strWidth = request.getParameter("width");
		int width = Integer.parseInt(strWidth);
		
		String strHeight = request.getParameter("height");		// request parameter를 저장해둔 것.  ---> rectangle.jsp에서 param 값을 가져올 때 여기서 저장된 걸 가져오는 것이다.
		int height = Integer.parseInt(strHeight);
		
		int area = width * height;
		
		// 응답 처리	(request에 대한 response)
		// 1. 결과를 반환한다.
		// 2. 응답 View를 결정한다.		-- 어디에 가서 내 응답 결과를 보여준다.		--> Q. output page가 두개가 되는 건가..?		--> YES!
		// 3. 이동 방식(redirect, forward)을 결정한다.						--> 어떤 방식으로 응답을 처리할 지.
		
		// 응답 처리 - 1
		// 1) 결과를 반환하는 경우  : request에 결과 저장 + forward 이동
		request.setAttribute("area", area);			// request Attribute로 저장한 부분 rectangle.jsp 의 ${area}에서 불러올 때 여기 값을 가져오는 것이다.
																// 2) 결과 반환이 없는 경우 : redirect 이동 					--> 반환할 게 없으니 전달할 게 없고 request에 저장할 필요가 없으므로 redirect로 이동함.
		
		// 응답 처리 - 2  : ModelAndView 클래스가 담당				--> 처리하고 보여줄 클래스
		// 1) 응답 View : 앞으로 이동할 View(JSP)의 경로를 작성 			--> 결과를 보여줄 JSP (java servlet page)
		// 2) 이동 방식 : forward 또는 redirect		--> 둘 중하나니까 boolean
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView("views/rectangle.jsp");	// 결과는 views의 rectangle.jsp 에 보여 주겠다.
		modelAndView.setRedirect(false); 				// false : forward --> redirect 아니다. 그러면 forward하겠다.
		
		// ModelAndView 객체를 컨트롤러에 반환 		: 실제 이동은 controller가 하므로 반환값을 전달해줘야함
		return modelAndView;
		
		

	}

}
