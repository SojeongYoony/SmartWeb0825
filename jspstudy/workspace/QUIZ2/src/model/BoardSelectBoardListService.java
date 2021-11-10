package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;

public class BoardSelectBoardListService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		request.setAttribute("boardList", boardDAO.selectBoardList());
		
		return new ModelAndView("views/selectBoardList.jsp", false);
	}

}
