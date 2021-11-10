package model;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class BoardInsertService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setTitle(title);
		boardDTO.setWriter(writer);
		boardDTO.setContent(content);
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		int result = boardDAO.insertBoard(boardDTO);
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(result > 0) {
			out.println("<script>");
			out.println("alert('등록 성공')");
			out.println("location.href='/QUIZ2/selectList.board';");
			out.println("</script>");
			out.close();	
		} else {
				out.println("<script>");
				out.println("alert('사원 등록에 실패했습니다.')");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
					e.printStackTrace();
		}
		return null;
	}
}
