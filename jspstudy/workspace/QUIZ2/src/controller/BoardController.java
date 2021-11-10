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
import model.BoardSelectBoardListService;
import model.BoardSelectService;
import model.BoardService;
import model.BoardUpdateBoardService;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Encoding */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String[] arr = request.getRequestURI().split("/");
		String command = arr[arr.length - 1];
		
		ModelAndView modelAndView = null;
		
		BoardService boardService = null;
		switch(command) {
		case "insert.board" :
			boardService = new BoardInsertService();	// insert. 
			break;
		case "delete.board" :
			boardService = new BoardDeleteService();
			break;
		case "update.board" :
			boardService = new BoardUpdateBoardService();
			break;
		case "selectDto.board" :
			boardService = new BoardSelectService();
			break;
		case "selectList.board" :
			boardService = new BoardSelectBoardListService();
			break;
		case "insertForm.board" :
			modelAndView = new ModelAndView("views/insertBoard.jsp", false);
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
