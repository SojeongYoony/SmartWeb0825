package model;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDao;

public class BoardSelectOneService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Optional<String> optIdx = Optional.ofNullable(request.getParameter("idx"));
		Long idx = Long.parseLong(optIdx.orElse("0"));
		
		BoardDao dao = BoardDao.getInstance();
		request.setAttribute("board", dao.selectOne(idx));
		System.out.println(idx);
		
		return new ModelAndView("views/selectedList.jsp", false);
		
	}

}
