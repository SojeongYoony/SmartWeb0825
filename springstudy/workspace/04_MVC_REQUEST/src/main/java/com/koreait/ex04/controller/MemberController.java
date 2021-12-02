package com.koreait.ex04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.ex04.domain.Member;
import com.koreait.ex04.domain.MemberBuilder;

@Controller
public class MemberController {
	
	// welcome page 작업
	@GetMapping({ "/", "index.do" })	// 어제의 궁금증 해결!!! 이렇게 하면 될 것 같은데 어떻게 하는지 몰라서 고민했었음..
	public String index() {
		return "index";
	} // 별도의 controller로 분리하여 board controller와 동일하게 mapping 분리 작업을 할 수 있다. 그냥 하게 되면, 망가짐 cuz of mapping
	
 // @GetMapping("/member/v1.do")       : 슬래시(/)로 시작해도 된다.     
	@GetMapping("member/v1.do") // a link / location --> GetMapping 
	public String b() {
//		return "/member/memberDetail"; : 슬래시(/)로 시작해도 된다.     
		return "member/memberDetail";
	}
//	    <a href="member/v2.do?idx=1&id=user&name=james">
	@GetMapping("member/v2.do")
	public String c(HttpServletRequest request) {	// parameter 처리를 매개변수가 해야 한다.
				//  session을 꺼내 쓰기도 편하다. 그리고 오류가 날 일이 없다. -- Spring의 new ver.에만 지원하는 기능이 아니므로.
		Long idx = Long.parseLong(request.getParameter("idx"));
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		// Member 생성 - 1
		Member member1 = new Member();
		member1.setIdx(idx);
		member1.setId(id);
		member1.setName(name);
		
		// Member 생성 - 2
		Member member2 = new Member(idx, id, name);
		
		// Member 생성 - 3 (Builder Pattern 이용하기)
		// Member 생성 두번째 방법의 대체품으로 나옴. 
		// 2번 사용 방법 시, 없는 값은 null로 때려줘야 하는데 이방법을 사용하게 되면, 필요한 값만 채워서 넘겨주면된다.
		Member member3 = new MemberBuilder()	
				.setIdx(idx)
				.setId(id)
				.setName(name)
				.build();
		
		// request를 이용해서 Member 넘기기
		request.setAttribute("member", member3);
		return "member/memberDetail";
	}
	
//	    <a href="member/v3.do?idx=1&id=user&name=james">	
	@GetMapping("member/v3.do")
	public String d(@RequestParam(value = "idx")  Long idx,	// @RequestParam(value="파라미터명") 저장할 변수의 타입 변수명,
					@RequestParam(value = "id")   String id,		
					@RequestParam(value = "name") String name, Model model) {
		// @RequestParam(value = "idx") : idx라는 parameter를 처리하라는 뜻   
		// Long idx : LongType idx에 저장해라. == idx라는 parameter를 Long idx에 저장하여 처리해라.
		
		// Model은 JSP에 값을 넘기는 역할이다.
		model.addAttribute("member", new Member(idx, id, name));
		return "member/memberDetail";
	}
	
//      <a href="member/v4.do">	
	@GetMapping("member/v4.do")
	public String e(@RequestParam(value ="idx",   required = false, defaultValue = "999")  Long idx,			// required = false  --> 필수는 아니라는 뜻.
					@RequestParam(value = "id",   required = false, defaultValue = "chul") String id,
					@RequestParam(value = "name", required = false, defaultValue = "철이") String name, Model model) {
		
	// @Requestparam 은 위의 설명을 참조.
	// required = false : 꼭 받아올 필요는 없다는 뜻
	// defaultValue = 받아온 값이 없을 경우, 기본 default(setting 값)를 정해준다. Optional로 Null처리 했던 것과 같은듯.
		
		model.addAttribute("member", new Member(idx, id, name));
		return "member/memberDetail";
	}
	
/*	    <a href="member/v5.do?idx=3&id=user&name=piglet">	
		-- parameter의 이름이 setter와 정확히 일치하도록 주의해야한다.   */
	@GetMapping("member/v5.do")
	public String f(Member member,	// Member 클래스의 setter가 파라미터를 모두 받아 준다.
					Model model) {
		model.addAttribute("member", member);	// 매개변수의 member를 그대로 넘기는 것.
		return "member/memberDetail";
	}
	
/*	    <a href="member/v6.do?idx=4&id=user&name=tigger">           */
	@GetMapping("member/v6.do")	// member/v5.do와 동일하게 동작.
	public String g(@ModelAttribute(value = "member") Member member) { 
 // parameter로 받은 값을 Member member에 저장하고 그 값을 @ModelAttribute의 value로 실어서 전달.	
 // 보통 단순 page 이동의 개념, 값만 가지고 전달 할 때 -- 상세페이지에서 수정페이지로 이동할 때
 // (상세페이지의 값을 수정페이지로 옮겨만 주면 되니까) : 값만 가지고 페이지만 이동할 때. -- 가장 줄여서 쓸 수 있는 version
		return "member/memberDetail";
	}
}
