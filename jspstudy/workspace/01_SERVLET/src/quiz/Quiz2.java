package quiz;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Quiz2
 */
@WebServlet("/Quiz2")
public class Quiz2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Quiz2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 날짜 : date
		//Optional<String> optDate = Optional.ofNullable(request.getParameter("date"));
		String date = request.getParameter("date");
		
		// 작성자 : from
		// Optional<String> optFrom = Optional.ofNullable(request.getParameter("from"));
		String from = request.getParameter("from");
		
		// 수신자 : to
		//Optional<String> optTo = Optional.ofNullable(request.getParameter("to"));
		String to = request.getParameter("to");
			
		// 내용 : content
		//Optional<String> optContent = Optional.ofNullable(request.getParameter("content"));
		String content = request.getParameter("content");
		
		System.out.println(date + from + to + content);
		
		// response 타입
		response.setContentType("text/html; charset=UTF-8");
		
		
		// file 생성
		String dir = "D:\\SmartWeb0809\\jspstudy\\";
		String fileName = date+"_"+ from + ".txt";
		String file = dir+fileName;
		FileWriter fw = null;
		fw = new FileWriter(file);
		
		fw.write("작성일 : " + date + "\n");
		fw.write("보내는사람 : " + from + "\n");
		fw.write("받는사람 : " + to + "\n");
		fw.write(content);
		System.out.println("파일 생성완료");
		System.out.println(dir);
		fw.close();
		
		// response 출력 스트림
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('"+ from + "이(가) " + to + "에게 전송" +"');");
		out.println("alert('편지 작성 화면으로 돌아갑니다.');");
		out.println("history.back();");
		out.println("</script>");
		out.close();
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
