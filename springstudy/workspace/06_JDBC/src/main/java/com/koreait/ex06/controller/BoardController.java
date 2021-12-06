package com.koreait.ex06.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.ex06.domain.Board;
import com.koreait.ex06.service.BoardService;
import com.koreait.ex06.service.BoardServiceImpl;

@Controller
@RequestMapping("board")
public class BoardController {
	
	// 로그 생성기
	// System.out.println()대체 
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);	// 기록을 남김.
	
	// BoardService Interface
	@Autowired
	private BoardService service;
	
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {	// model : jsp로 값을 넘김		-- paging / list 등 여러 정보를 넘길 예정
		logger.info("selectBoardList() 호출");		// console에 정보가 찍힘
		List<Board> list = service.selectBoardList();
	 // logger.info(list.toString());	// board 목록을 console에 찍어봄
		model.addAttribute("list", list); // 내부적으로 request.setAttribute("list", list)임 : board/list.jsp로 list를 보냄
		return "board/list";		// board/list.jsp로 forward함(model에 저장한 list가 전달 됨)
	}
	
	@GetMapping(value="insertBoardForm.do") //a link니까 getmapping == a link or location은 Get type
	public String insertBoardForm() {
		return "board/insert";
	}
	
	@PostMapping(value="insertBoard.do")
	public void insertBoard(HttpServletRequest request, HttpServletResponse response) {
		service.insertBoard(request, response);
		// 삽입후에 첫번째 mapping으로 이동하면서 위의 method가 재실행 되고, update된 후의 목록을 다시 가져와서 삽입한 data가 화면에 나타난다.
		// 삽입이나 삭제 등, DB의 update가 있는 경우, 반드시 redirect 할 것.
		// result 값을 script를 넘겨서 거기서 적절한 메세지를 띄우는 방법이 있음(전용 script) 작업할 때, redirect 해야함
	// BoardServiceImpl에서 이미 response가 이뤄졌으므로, return이 필요없음 따라서 반환타입도 void로 변경함.
	}
	
	@GetMapping(value = "selectBoardByNo.do")
	public String selectBoardByNo(@RequestParam(value="no")Long no, Model model) {
		model.addAttribute("board", service.selectBoardByNo(no));
		return "board/detail";
	}
	
	// mapping updateBoardForm.do
	@GetMapping(value = "updateBoardForm.do")
	public String updateBoardForm(@ModelAttribute(value="board") Board board) { // 전달되는 정보 세개가 Bean으로 가고, 그 bean을 Model에 board라는 이름으로 add attribute 함.
		// detail.jsp에서 보낸 파라미터 3개는 Board board가 받고,
		// model에 "board" 속성으로 저장함 : model.addAttribute("board", board)를 @ModelAttribute annotation이 수행 함.
		return "board/update"; // redirect를 명시 안 할 경우, 기본 이동은 forward임.
	}
	
	// mapping updateBoard.do
	@PostMapping(value = "updateBoard.do")
	public void updateBoard(Board board, HttpServletResponse response) { // request나 response를 사용하고 싶으면 controller에서 선언하고 넘겨주는 방식으로! 엉뚱한 곳에서만 하지 말 것
		service.updateBoard(board, response);
	}
	
	// mapping deleteBoard.do
	@GetMapping(value = "deleteBoard.do")
	public void deleteBoard(@RequestParam(value = "no", required = false, defaultValue = "0") Long no, // 번호 전달이 안 되면, 삭제 실패 메세지가 뜰 수 있도록 (0을 기본 default했는데, 0은 없는번호니까 삭제될 수가 없음)
					HttpServletResponse response) { 
		service.deleteBoard(no, response);
		
	}
	
}
