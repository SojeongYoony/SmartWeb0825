package com.koreait.ex15.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
//	  4) 수정 : members		 + PUT  (수정할 정보는 body에 포함시켜서 처리됨) == post처럼 하면 됨 : 내부적으로 post처럼 동작되지만, 구분을 위해 put이라고 함 -> 삽입과 흡사하다.


@RestController  // 일반 controller가 아니고 RestController이다
@AllArgsConstructor // 생성자를 만들면 field에 @Autowired 처리된다
public class MemberController {
	
	private MemberService service;
	
	// 회원 목록
	@GetMapping(value="api/members/page/{page}", produces = "application/json; charset=UTF-8")
	public Map<String, Object> findAllMember(@PathVariable(value="page", required=false) Optional<Integer> opt) { // PathVariable required=false -> 필수아님 -> null일 수 있음
		Integer page = opt.orElse(1); // null 이면 1로 할 것 
		return service.findAllMember(page); // DB에서 가져온 List를 List<Member>에 저장
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
	
	// 회원조회
	@GetMapping(value="api/members/{memberNo}", produces = "application/json; charset=UTF-8") // 받아온 변수는 중괄호로 묶는다. "{변수}"
	public Map<String, Object> findMember(@PathVariable(value="memberNo")Long memberNo) { // 경로에 변수가 있다.  @PathVariable  -- Path Variable : 위의 변수 이름과 통일되어야 함 value는
		return service.findMember(memberNo); // @PathVariable로 처리한 parameter memberNo를 전달.
	}
	
	// 회원 수정
	@PutMapping(value="api/members", produces = "application/json; charset=UTF-8")
	public Map<String, Object> modifyMember(@RequestBody Member member) {
		return service.modifyMember(member); // insert에서는 response로 Exception처리를 하여 error부분에서 받아오는 것으로 함
	}
	
	// 회원 삭제
	@DeleteMapping(value="api/members/{memberNo}", produces = "application/json; charset=UTF-8") // 회원 조회와 같음 번호를 받아 오므로
	public Map<String, Object> removeMember(@PathVariable(value="memberNo")Long memberNo) {
		return service.removeMember(memberNo);
	}
}
