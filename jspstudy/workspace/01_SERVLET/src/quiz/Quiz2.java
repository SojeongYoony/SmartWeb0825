package quiz;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
		if (from.isEmpty()) {
			from = "작성자 없음";
		}
		
		// 수신자 : to
		//Optional<String> optTo = Optional.ofNullable(request.getParameter("to"));
		String to = request.getParameter("to");
		if (from.isEmpty()) {
			to = "수신자 없음";
		}
		// 내용 : content
		//Optional<String> optContent = Optional.ofNullable(request.getParameter("content"));
		String content = request.getParameter("content");
		if (content.isEmpty() ) {
			content = "내용 없음";
		}

		// 잘 받아왔나 확인
		System.out.println(date + from + to + content);
		
		// 작성자 IP 알아내는 법
		// 1. 직접 접속한 경우    : request.getRemoteAddr();
		// 2. 거쳐서 접속한 경우 : request.getHeader("X-Forwarded-For");
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null) {			// Optional을 사용할 수 있음
			ip = request.getRemoteAddr();
		}
		
		// file 생성
		// String dir = "D:\\SmartWeb0809\\jspstudy\\";
		File dir = new File("D:\\SmartWeb0809\\jspstudy\\workspace\\01_SERVLET\\storage");
		if (dir.exists() == false) {
			dir.mkdirs();
		}
		
		String fileName = date+"_"+ from + ".txt";
		
		// 파일 저장 경로
		// String file = dir+fileName;
		File file = new File(dir, fileName);		
		
		// 문자 기반 출력 스트림
		// FileWriter, PrintWriter, BufferedWriter 등
//		FileWriter fw = null;
//		bw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		
		// 데이터 보내기
		bw.write("작성일 : " + date + "\n");
		bw.write("보내는사람 : " + from + "\n");
		bw.write("받는사람 : " + to + "\n");
		bw.write(content);
		System.out.println("파일 생성완료");
		System.out.println(dir);
		if (bw != null) bw.close();	
		
		// response 타입
		response.setContentType("text/html; charset=UTF-8");
		
		// response 출력 스트림
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('"+ from + "이(가) " + to + "에게 전송" +"');");
		out.println("alert('"+ file.getAbsolutePath() + " 파일이 생성되었습니다.');");
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
