package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDao;

public class BoardSelectAllListService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		BoardDao dao = BoardDao.getInstance();
		int totalCount = dao.getTotalCount();
		
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("boardList", dao.selectAllBoardList());
		
		return new ModelAndView("views/allBoardList.jsp", false);
	}

}
