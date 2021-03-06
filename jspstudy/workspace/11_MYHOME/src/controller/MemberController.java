package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import service.member.MemberIdCheckService;
import service.member.MemberJoinService;
import service.member.MemberLeaveService;
import service.member.MemberLoginService;
import service.member.MemberLogoutService;
import service.member.MemberService;

@WebServlet("*.member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청/응답 기본 처리 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// JSP의 요청 확인 
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		
		MemberService service = null;
		ModelAndView mav = null;
		
		switch(command) {
		case "login.member" :
			service = new MemberLoginService();
			break;
		case "logout.member" :
			service = new MemberLogoutService();
			break;
		case "joinForm.member" :
			mav = new ModelAndView("member/join.jsp", false);
			break;
		case "join.member" :
			service = new MemberJoinService();
			break;
		case "idCheck.member" :
			service = new MemberIdCheckService();
			break;
		case "leave.member" :
			service = new MemberLeaveService();
			break;
		}
		
		// service가 사용되지 않은 경우 (단순 이동) service 실행이 불가능
		if (service != null) {
			try {				
				mav = service.execute(request, response);			
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if (mav == null) {
			return;
		}
		
		if (mav.isRedirect()) {						
			response.sendRedirect(mav.getView());	
		} else {									
			request.getRequestDispatcher(mav.getView()).forward(request, response);	
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
