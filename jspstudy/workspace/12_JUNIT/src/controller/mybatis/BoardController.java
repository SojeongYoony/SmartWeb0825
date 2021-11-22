package controller.mybatis;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.mybatis.BoardDeleteService;
import service.mybatis.BoardInsertService;
import service.mybatis.BoardListService;
import service.mybatis.BoardService;

@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");	// 이따 바뀜 ex) ajax == json 
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		
		BoardService service = null;
		
		switch(command) {
		// select boards
		case "selectBoardList.do" :
			service = new BoardListService();
			break;
		// insert	
		case "insertBoard.do" :
			service = new BoardInsertService();
			break;
		// delete
		case "deleteBoard.do" :
			service = new BoardDeleteService();
			break;
			
		}

		/* 실행 코드 */
		if (service != null) {
			service.execute(request, response);  // modelandview가 없기 때문에 request, response 모두전달.
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
