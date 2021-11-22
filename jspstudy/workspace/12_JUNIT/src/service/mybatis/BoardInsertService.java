package service.mybatis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.exceptions.PersistenceException;
import org.json.JSONObject;

import dao.mybatis.BoardDAO;
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
		
		// catch 블록의 response는 ajax의 error로 응답을 보냄.
		
		} catch (PersistenceException e) {		// PersistenceException :: Mybatis에서 DB오류나면 발생
			// 텍스트의 타입 : text/plain
			response.setContentType("text/plain; charset=UTF-8"); 		// http에서는 text/plain을 가짐
			
			// 에러 메시지 전달
			PrintWriter out = response.getWriter();						// 위의response type과 아래의 response type이 다르므로 따로 선언해 주는 것이 좋다
			out.println("DB오류 발생");
			
			// 에러 코드 전달
			response.setStatus(1111);	// 에러 코드 1 발생 (1은 마음대로 정했음.)
		}
		
	}

}
