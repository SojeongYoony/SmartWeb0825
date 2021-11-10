package model;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;

public class BoardSelectService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		Optional<String> optIdx = Optional.ofNullable(request.getParameter("idx"));
		Long idx = Long.parseLong(optIdx.orElse("0"));
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		request.setAttribute("board", boardDAO.selectBoardDTO(idx));
		
		return new ModelAndView("views/selectBoard.jsp", false);
	}

}
