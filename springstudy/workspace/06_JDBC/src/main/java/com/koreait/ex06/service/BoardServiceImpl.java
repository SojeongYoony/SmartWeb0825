package com.koreait.ex06.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.ex06.domain.Board;
import com.koreait.ex06.repository.BoardRepository;

public class BoardServiceImpl implements BoardService{

	// field
	@Autowired 				// 다 뒤져서 해당 type의 bean을 가져옴.
	private BoardRepository repository;
	
	@Override
	public List<Board> selectBoardList() {
		return repository.selectBoardList();
//		return BoardRepository.getInstance().selectBoardList();
	} // selectBoard
	
	@Override
	public void insertBoard(HttpServletRequest request, HttpServletResponse response) {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Board board = new Board();
		board.setWriter(writer);
		board.setTitle(title);
		board.setContent(content);
		int result = repository.insertBoard(board);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.println("<script>");
				out.println("alert('게시글 등록 성공')");
				out.println("location.href='/ex06/board/selectBoardList.do'"); // redirect처리 : location을 이용한 이동은 redirect와 동일하다.
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('게시글 등록 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Board selectBoardByNo(Long no) {
		return repository.selectBoardByNo(no);
	}// insertBoard
	
	@Override
	public void updateBoard(Board board, HttpServletResponse response) {
		int result = repository.updateBoard(board);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.println("<script>");
				out.println("alert('게시글 수정 성공')");
				out.println("location.href='/ex06/board/selectBoardByNo.do?no=" + board.getNo() + "'"); // redirect처리 : location을 이용한 이동은 redirect와 동일하다.
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('게시글 수정 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // updateBoard
	
	@Override
	public void deleteBoard(Long no, HttpServletResponse response) {
		int result = repository.deleteBoard(no);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.println("<script>");
				out.println("alert('게시글 삭제 성공')");
				out.println("location.href='/ex06/board/selectBoardList.do'"); // redirect처리 : location을 이용한 이동은 redirect와 동일하다.
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('게시글 삭제 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // deleteBoard
}
