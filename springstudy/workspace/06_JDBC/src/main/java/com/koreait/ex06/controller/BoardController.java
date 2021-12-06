package com.koreait.ex06.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board")
public class BoardController {
	
	// 로그 생성기
	// System.out.println()대체 
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);	// 기록을 남김.
	
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {	// model : jsp로 값을 넘김		-- paging / list 등 여러 정보를 넘길 예정
		logger.info("selectBoardList() 호출");		// console에 정보가 찍힘
		return "board/list";
	}
	
}
