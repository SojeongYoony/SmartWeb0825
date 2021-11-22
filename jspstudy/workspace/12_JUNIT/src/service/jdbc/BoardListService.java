package service.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.jdbc.BoardDAO;
import dto.Board;

public class BoardListService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 목록 가져 옴
		List<Board> list = BoardDAO.getInstance().selectBoardList();
	//	System.out.println(list.toString()); 		// list에 뭐가 담겨 있는지, 정보를 잘 전달 받았는지 확인하는 점검 코드. :: 이것은 DB에서 받아온 data이다.
		
		
		// JSON 데이터로 변환함
		// 1. JSONObject : Board  	   : 한개
		// 2. JSONArray  : List<Board> : 여러개 
		// JSONArray arr = new JSONArray(); 정상 동작 안해서 빈 배열에 강제 삽입했음.
		JSONArray arr = new JSONArray();		// list 전달해주면 내가 만들어 줄게  JSON Array --> lib 사용한 것임
		for(Board board : list) {					// lib 동작 안해서, for 문으로 직접...강제 생산해서 집어넣음.
			JSONObject obj = new JSONObject();
			obj.put("bNo", board.getbNo());
			obj.put("writer", board.getWriter());
			obj.put("content", board.getContent());
			obj.put("bDate", board.getbDate());
			arr.put(obj);
		//	System.out.println(obj.toString()); 	// obj에 뭐가 담겨 있는지, 정보를 잘 전달 받았는지 확인하는 점검 코드.  :: 이것은 전달받은
		}
		
		// JSON 데이터로 변환된 목록 반환함
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(arr);	// index.jsp의 success : function(boards) { }로 반환됨.		== parameter
		out.close();
		
	}

}
