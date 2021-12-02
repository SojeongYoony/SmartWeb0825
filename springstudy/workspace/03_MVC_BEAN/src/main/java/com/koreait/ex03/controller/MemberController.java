package com.koreait.ex03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.ex03.config.MemberConfig;
import com.koreait.ex03.domain.Member;

@Controller
public class MemberController {			// controller이므로 java에서 하던 것 처럼 하면 된다
	// method를 전역에서 사용하기 위해 field로 깔고 시작함
	AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(MemberConfig.class); // MemberConfig class 읽어온다.
	// 같은 Type의 객체(bean)가 여러개 있을 경우 ctx를 선언하여 사용하도록 한다.

	// Spring4 부터 @GetMapping, @PostMapping 지원
	
	// @RequestMapping(value="/", method = RequestMethod.GET)	// get / post / put / delete
	@GetMapping("/")		
	// value 속성만 사용하므로 value만 작성하면된다. method는 이미 annotation에서 언급하여 작성할 필요가 없음
	public String index(HttpServletRequest request) {	// 약간 JSP 스러운 code
				// method명은 역할이 없다. 아무 이름이나 줘도 됨.
		
		// member1을 만들어서 index.jsp로 보내기
		Member member1 = ctx.getBean("member1", Member.class);
		request.setAttribute("member1", member1);	// { "id" : "admin", "pw" : "1234" }
		
		return "index";		// 기본 이동은 forward이므로 request가 전달 된다.
	}
	
	@GetMapping("list.do")
	public String a() {
		return "index";
	}
	
	// @RequestMapping(value="memberView.do", method = RequestMethod.GET)	
	// get은 생략도 가능하지만, 4.3부터 getMapping을 지원함 --> version upgrade가 필요하다. before use
	@GetMapping("memberView1.do")
	public String b(Model model) {		// Spring ver.  -- Model Interface를 사용 : request를 싸고 있음 request의 역할을 수행할 수 있음
		
		// Model
		// 안녕 난 request를 사용하는 인터페이스야 보안이 더 좋지
		// addAttribute()를 사용하면 실제로는 request.setAttribute()처럼 동작하지
		Member member2 = ctx.getBean("member2", Member.class);
		model.addAttribute("member", member2);
		
		// member2를 만들어서 memberDetail.jsp로 보내기
		return "member/memberDetail";
	}
	
	
	@Autowired
	@Qualifier(value="member3")
	private Member member3;
	@GetMapping("memberView2.do")
	public String d(Model model) {
		model.addAttribute("member", member3);
		return "member/memberDetail";
	}
	
	
	@Autowired
	@Qualifier(value="member4")
	private Member member4;
	@GetMapping("memberView3.do")
	public String e(Model model) {
		model.addAttribute("member", member4);
		return "member/memberDetail";
	}
	
}
