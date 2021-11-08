package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import model.Circle;
import model.Rectangle;
import model.Shape;


// 모든 mapping 값은 .do를 참조
// URLMapping의 suffix가 .do인 모든 요청을 처리하는 컨트롤러
@WebServlet("*.do")
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// URLMapping 확인 -- 어떤 .do인지 확인하는 작업

		String requestURI = request.getRequestURI();			// request.getRequestURI() // 어떤 경로를 통해 왔는지 모두 확인 가능
		String command = requestURI.substring(requestURI.lastIndexOf("/") + 1);		// 마지막 /를 찾아서 그 index + 1 값을 command 실행 == [0] : mapping을 확인했다.
		
		// 모든 model은 Shape 인터페이스를 구현한다.
		Shape shape = null;							// shape의 메모리를 하나 잡아둠 일단 null로 사용공간을 비워둠
		
		// modelAndView 선언
		ModelAndView modelAndView = null;
		// command에 따른 model선택
		switch(command) {
		case "rectangle.do" : 						// command가 rectangl.do일 때 할 일.
			shape = new Rectangle();
			break;
		case "circle.do" :
			shape = new Circle();					// 어떤 model이든 타입은 모두 shape이다.
			break;
		case "input.do" :							// command가 없으면 modelAndView 경로로만 이동한다.
			modelAndView = new ModelAndView();
			modelAndView.setView("views/input.jsp");
			modelAndView.setRedirect(true);				// false ==  forward : 경로는 ct에게 공개되지 않으므로 뒤에 경로는 보이지 않는다 
			break;
		}
		
		// model 실행하는 코드		// 여기서 전달
		if (shape != null) {
			modelAndView = shape.execute(request, response);		// modelAndView객체가 반환을 받아야 함.			
		} else {
			
		}
		
		// modelAndView가 없는 경우 	(ajax 처리	--> page이동이 없는 기술. response에 의해서 inputpage로 이동.)
		// Model이 직접 결과를 반환하는 경우	=> response를 직접 작업하는 경우.		--> response가 있기 때문에 (응답하는 객체)
		if ( modelAndView == null ) {
			return;
		}
		
		// modelAndView가 있는 경우 (ajax가 아닌 모든 경우  --> page이동이 있음 ==> 화면 이동이 있음.)
		if ( modelAndView.isRedirect() ) {				// boolean --> "is"Redirect		// boolean이 아니면 getRedirect
			response.sendRedirect( modelAndView.getView() );  // 아까 저장해 둔 이동 경로를 꺼내서 쓴다.
		} else {
			request.getRequestDispatcher( modelAndView.getView() ).forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
