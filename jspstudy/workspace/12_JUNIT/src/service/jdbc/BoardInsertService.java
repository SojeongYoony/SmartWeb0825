package service.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.jdbc.BoardDAO;
import dto.Board;

public class BoardInsertService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try {
		// request parameter 받아서 변수에 저장하자.		:: $('#f').serialize()로 받은 파라미터들.
		String bNo = request.getParameter("bNo");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		// 하나로 모으자.								:: DB로 보낼 bean
		Board board = new Board();
		board.setbNo(bNo);
		board.setWriter(writer);
		board.setContent(content);
		
		// DB에 삽입									:: dao를 불러서 method의 return을 받아 result에 저장.
		int result = BoardDAO.getInstance().insertBoard(board);
		
		// 성공/실패 여부를 JSON 데이터로 작성.
		// {"result" : true} or {"result" : false}
		JSONObject obj = new JSONObject();
		obj.put("result", result > 0);			// "실을 변수명", true/false   == result > 0 or result == 1 
		
		// JSON 데이터의 반환
		response.setContentType("application/json; charset=UTF-8");			// JSON으로 반환할 것이니까 contentType으로 Encoding한다.
		PrintWriter out = response.getWriter();
		out.println(obj);
		out.close();
		
		// 예외코드 정리
		// 2001 : 동일한 게시글번호 재등록, 필수 칼럼 누락
		// 2002 : 잘못된 데이터 전달
		// 2003 : 알 수 없는 에러	   ex) number format / null pointer ...etc.. exceptions
		
		} catch (SQLIntegrityConstraintViolationException e) {		// jdbc의 exception을 여기로 받아 ajax의 error로 보냄
																							// SQLIntegrityConstraintViolationException  ::  중복 or 필수 칼럼 누락시 발생
			response.setContentType("text/plain; charset=UTF-8"); 
			
			// 에러 메시지 전달
			PrintWriter out = response.getWriter();	
																							//out.println(e.getClass().getName());  // e : Exception object, exception name 
			out.println("동일한 게시글번호가 있거나 필수 정보가 누락되었습니다.");		
			
			// 에러 코드 전달
			response.setStatus(2001);	// 에러 코드 2001 발생 
		
		
		} catch(SQLException e) {
																							//	e.printStackTrace();		// console창에 뜬다.
			response.setContentType("text/plain; charset=UTF-8"); 
			
			// 에러 메시지 전달
			PrintWriter out = response.getWriter();	
			out.println("잘못된 데이터가 전달되었습니다.");		
			
			// 에러 코드 전달
			response.setStatus(2002);	// 에러 코드 2002발생
			
			
		} catch(Exception e) {
			response.setContentType("text/plain; charset=UTF-8"); 
			
			// 에러 메시지 전달
			PrintWriter out = response.getWriter();	
			out.println("알 수 없는 예외가 발생했습니다.");		
			
			// 에러 코드 전달
			response.setStatus(2003);	// 에러 코드 2003발생
		}
		
	}

}
