package com.koreait.final1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.final1.domain.Board;
import com.koreait.final1.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping(value="/")
	public String index() {
		return "index";
	}

	@GetMapping(value="/board/list")
	public String list(Model model) {
		model.addAttribute("list", boardService.getBoards());
		model.addAttribute("boardCount", boardService.getBoardCount());
		return "/board/list";
	}

	@GetMapping(value="/board/addForm")
	public String addForm() {
		return "/board/insert";
	}

	@PostMapping(value="/board/add")
	public void add(Board board, HttpServletRequest request, HttpServletResponse response) {
		try {
			int result = boardService.addBoard(board);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.append("<script>");
				out.append("alert('삽입 성공');");
				out.append("location.href='" + request.getContextPath() + "/board/list';");
				out.append("</script>");
				out.close();
			} else {
				out.append("<script>");
				out.append("alert('삽입 실패');");
				out.append("history.back();");
				out.append("</script>");
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping(value="/board/detail")
	public String detail(Long idx, Model model) {
		model.addAttribute("board", boardService.getBoard(idx));
		return "/board/detail";
	}

	@GetMapping(value="/board/modify")
	public void modify(Board board, HttpServletRequest request, HttpServletResponse response) {
		try {
			
			int result = boardService.modifyBoard(board);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.append("<script>");
				out.append("alert('수정 성공');");
				out.append("location.href='" + request.getContextPath() + "/board/detail?idx="+board.getIdx()+"';");
				out.append("</script>");
				out.close();
			} else {
				out.append("<script>");
				out.append("alert('수정 실패');");
				out.append("history.back();");
				out.append("</script>");
				out.close();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping(value="/board/remove")
	public void remove(Long idx, HttpServletRequest request, HttpServletResponse response) {
		int result = boardService.removeBoard(idx);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.append("<script>");
				out.append("alert('삭제 성공');");
				out.append("location.href='" + request.getContextPath() + "/board/list';");
				out.append("</script>");
				out.close();
			} else {
				out.append("<script>");
				out.append("alert('삭제 실패');");
				out.append("history.back();");
				out.append("</script>");
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
