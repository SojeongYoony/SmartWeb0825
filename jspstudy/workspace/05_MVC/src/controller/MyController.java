package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Lotto;
import model.Now;
import model.Today;

// @WebServlet({"/today.do","/now.do"})			// 이렇게도 가능은 하다. 하지만 하지말 것. Servlet : url mapping
// suffix 값이 .do인 모든 요청을 처리하시오.	// 꼭 필요함. 실무에서는 문서화 되어 있음 뭐가 무엇인지. -- MyController가 처리할 수 있다.
@WebServlet("*.do")						// (/)으로 split , 배열 마지막 요소가 요청이다.
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		/* 요청 확인 */
		// request.getRequestURI()	: 요청을 확인하는 용도로 사용함.	--> 주소를 열어보면 됨.
		// String[] arr = request.getRequestURI().split("/");	// {"", "MVC", "today.do"} --> 배열의 첫번째 요소는 빈 문자열이다.
		// System.out.println(arr[arr.length-1]);	// 언제나 길이-1가 마지막요소
		
		int begin = request.getRequestURI().lastIndexOf("/");
		String command = request.getRequestURI().substring(begin + 1); // +1 하면 / 안나옴.  둘 중 하나만 잘 할줄 알면 됨.
		
		
		/* 요청에 따른 Model의 선택 */
		/* 아래 switch와 같음,
		if (command.equals("today.do")) {					// 오늘 날짜를 가지고 오라고 했다. 		command의 문자열을 비교하여 equals today.do가 있으면
			Today today = new Today();
			today.execute();			
		} else if(command.equals("now.do")) {      			// 현재 시간을 가져오라고 했다

		}
		*/
		
		switch (command) {
		case "today.do" :
			Today today = new Today();
			today.execute(request, response);
			break;
		case "now.do" :
			Now now = new Now();
			now.execute(request, response);
			break;
		case "lotto.do" :
			Lotto lotto = new Lotto();
			lotto.execute(request, response);
			break;
		
		} 
		
		
		/* 응답 View로 이동 */
		// request를 전달하는 forward
		// 각 Model이 request에 결과를 저장해 두었음.
		request.getRequestDispatcher("views/output.jsp").forward(request, response);


		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
