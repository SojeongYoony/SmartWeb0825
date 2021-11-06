package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = GetKey.getkey();		// captchaKey 받아오기.
		GetImage.getImage(key, request);
		request.setAttribute("key", key); 		// key전달 login.jsp로
		// 로그인 화면으로 가서 이미지 보여줘야 한다.
		// 포워드 : request 가지고 이동 - request에 파일명 저장						// 리다이렉트 : request 없이 이동		
		request.getRequestDispatcher("login.jsp").forward(request, response);   // request와 response를 가지고 login.jsp로 이동
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
