package com.koreait.ex05.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.ex05.domain.Board;

@RestController   // ajax만 처리하는 컨트롤러, @ResponseBody를 생략함.  -- 내가 지금 반환하는 String은 View가 아님을 알려주는 것 이었는데 애초에 Annotation을 JSON전용으로 해버렸으니까 필요없지
@RequestMapping("board")
public class BoardController {

	@GetMapping(value = "v1.do",
			 produces = "application/json; charset=UTF-8")
	public Board v1(Board board) {
		return board;
	}
	
	@PostMapping(value = "v2.do",
			produces = "application/json; charset=UTF-8")
	public Board v2(@RequestBody Board board) { // @RequestBody를 붙여서 요청이 Body에 포함되어 있다.
		return board;
	}
	
	@PostMapping(value = "v3.do",
			produces = "application/json; charset=UTF-8")
	public Map<String, Object> v3(@RequestBody Map<String, Object> map) {
		return map;
	}
	
	
}
