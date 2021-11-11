package model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDao;
import dto.BoardDto;

public class BoardInsertService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BoardDto boardDto = new BoardDto();
		boardDto.setWriter(writer);
		boardDto.setTitle(title);
		boardDto.setContent(content);
		
		BoardDao boardDao = BoardDao.getInstance();
		int result = boardDao.insertBoard(boardDto);
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(result > 0) {
				out.println("<script>");
				out.println("alert('등록 성공')");
				out.println("location.href='/QBATI/selectAllList.board';");
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
