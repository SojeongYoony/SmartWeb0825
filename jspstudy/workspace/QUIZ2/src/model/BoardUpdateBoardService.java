package model;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class BoardUpdateBoardService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		String title  = request.getParameter("title");
		String content = request.getParameter("content");
		Long idx = Long.parseLong(request.getParameter("idx"));
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setTitle(title);
		boardDTO.setContent(content);
		boardDTO.setIdx(idx);
		
		System.out.println(boardDTO);
		
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		int result = boardDAO.updateBoard(boardDTO);
		
		ModelAndView modelAndView = null;
		if(result > 0) {
			modelAndView = new ModelAndView("/QUIZ2/selectList.board", true);
		}else {
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.print("<script>");
				out.print("alert('사원 정보가 수정되지 않았습니다.');");
				out.print("history.back();");
				out.print("</script>");
				out.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return modelAndView;
	}

}
