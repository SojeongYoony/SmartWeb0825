package service.mybatis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.exceptions.PersistenceException;
import org.json.JSONObject;

import dao.mybatis.BoardDAO;
import dto.Board;

public class BoardDeleteService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 'bNo=' + $(this).prev().val()
		String bNo = request.getParameter("bNo");
		
		// DB에 삭제									:: dao를 불러서 method의 return을 받아 result에 저장.
		int result = BoardDAO.getInstance().deleteBoard(bNo);
		
		// 성공/실패 여부를 JSON 데이터로 작성.
		// {"result" : true} or {"result" : false}
		JSONObject obj = new JSONObject();
		obj.put("result", result > 0);			// "실을 변수명", true/false   == result > 0 or result == 1 
		
		// JSON 데이터의 반환
		response.setContentType("application/json; charset=UTF-8");			// JSON으로 반환할 것이니까 contentType으로 Encoding한다.
		PrintWriter out = response.getWriter();
		out.println(obj);
		out.close();

		
	}

}
