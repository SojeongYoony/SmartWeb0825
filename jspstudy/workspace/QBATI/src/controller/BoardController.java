package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import model.BoardDeleteService;
import model.BoardInsertService;
import model.BoardSelectAllListService;
import model.BoardSelectOneService;
import model.BoardService;
import model.BoardUpdateService;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURI = request.getRequestURI();
		String[] arr = requestURI.split("/");
		String command = arr[arr.length - 1];
		
		BoardService boardService = null;
		ModelAndView modelAndView = null;
		
		switch(command) {
		case "selectAllList.board" :
			boardService = new BoardSelectAllListService();
			break;
		case "selectOne.board" :
			boardService = new BoardSelectOneService();
			break;
		case "insert.board" :
			boardService = new BoardInsertService();
			break;
		case "update.board" :
			boardService = new BoardUpdateService();			
			break;
		case "insertForm.board" :
			modelAndView = new ModelAndView("views/insertList.jsp", false);
			break;
		case "delete.board" :
			boardService = new BoardDeleteService();
			break;
		
		}
		
		if (boardService != null) {
			modelAndView = boardService.execute(request, response);
		}
		
		if (modelAndView == null) {
			return;
		}
		
		if (modelAndView.isRedirect()) {
			response.sendRedirect(modelAndView.getView());
		} else {
			request.getRequestDispatcher(modelAndView.getView()).forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
