package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ValidationServlet")
public class ValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ValidationServlet() {
        super();
 
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 검증에 필요한 key와 value를 가져온다 -> request에 담겨 있는 값을 사용할 수 있도록 : parameter를 빼야 함 
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		boolean result = GetValidation.getValidation(request);		// request를 받아온다 .static 처리라 객체 만들 피료 없음
		if (result) {
			out.println("<script>");
			out.println("alert('검증 성공');");
			out.println("location.href='index.jsp'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('검증 실패');");
			out.println("location.href='/naver.api/LoginServlet'");
			out.println("</script>");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
