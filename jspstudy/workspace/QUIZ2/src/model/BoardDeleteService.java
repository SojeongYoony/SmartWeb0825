package model;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;

public class BoardDeleteService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		Optional<String> optIdx = Optional.ofNullable(request.getParameter("idx"));
		Long idx = Long.parseLong(optIdx.orElse("0"));
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		int result = boardDAO.deleteBoard(idx);
		
		ModelAndView modelAndView = null;
			if(result > 0 ) {
				modelAndView = new ModelAndView("/QUIZ2/selectList.board", true);
			} else {
				PrintWriter out = null;
				try {
					out = response.getWriter();
					out.println("<script>");
					out.println("alert('삭제 실패');");
					out.println("history.back();");
					out.println("</script>");
					out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		return modelAndView;
	}

}
