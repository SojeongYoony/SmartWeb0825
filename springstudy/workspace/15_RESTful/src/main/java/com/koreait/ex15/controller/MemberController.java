package com.koreait.ex15.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.ex15.domain.Member;
import com.koreait.ex15.service.MemberService;

import lombok.AllArgsConstructor;

// REST 방식

// 1. URL + Method에 의해서 동작이 결정
// 2. URL에 파라미터가 경로의 일부로 포함 -- ? / = 으로 붙이지 않고  "/"로 잘려서 mapping에 표시
// 3. URL + Method
//	  1) 목록 : members		 + GET
//	  2) 개별 : members/1	 + GET  // 회원번호가 1번인 사람의 정보 가져오기
//	  3) 삽입 : members		 + POST	// 삽입할 내용을 body에 포함시키는 post 방식


@RestController  // 일반 controller가 아니고 RestController이다
@AllArgsConstructor // 생성자를 만들면 field에 @Autowired 처리된다
public class MemberController {
	
	private MemberService service;
	
	// 회원 목록
	@GetMapping(value="api/members", produces = "application/json; charset=UTF-8")
	public Map<String, Object> findAllMember() {
		return service.findAllMember(); // DB에서 가져온 List를 List<Member>에 저장
	}
	
	// 회원 등록
	@PostMapping(value="api/members", produces = "application/json; charset=UTF-8")
	public Map<String, Object> addMember(@RequestBody Member member, HttpServletResponse response) { // ResponseEntity : 응답 타입이라고 하는 Class가 있는데 Map과 큰 차이 없음.
		try {
			return service.addMember(member); // 중복 Exception이 발생할 수 있음.
		} catch (DuplicateKeyException e) {
//			System.out.println(e.getClass().getName()); // Checking Class' name of Exception
			try {
				response.setStatus(500);
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().println("이미 사용 중인 아이디입니다.");
			} catch (Exception e2) { // IOException 발생
				e2.printStackTrace();
			}
		} catch (DataIntegrityViolationException e) {
			try {
				response.setStatus(600);
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().println("필수 정보가 누락되었습니다.");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
//			System.out.println(e.getClass().getName());
		}
		return null;	// 동작할 일 없음.
	}
}
