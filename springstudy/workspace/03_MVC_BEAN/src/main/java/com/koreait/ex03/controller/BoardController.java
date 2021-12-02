package com.koreait.ex03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.ex03.domain.Board;

@Controller
public class BoardController {

	
	// ctx 없이 bean 생성하기 -- Autowired를 사용하기 때문에 : Auto Wired - 말 그대로 자동으로 연결한다는 뜻.
	// AbstractApplicationContext ctx = new AnnotaitionConfigApplicationContext(객체클레스명.class); 을 작성할 필요가 없음
	// 단, Annotation으로 알려줘야함.
	
	// 1. @Inject, @Resource, @Autowired와 같은 애너테이션 중 하나를 사용한다 (annotation 사용한다.)
	// 2. @Autowired를 가장 많이 사용한다.		-- 객체 Type을 참조	: 딱 하나 !!!
	// 어제의 궁금증 해소..! : 어제 Board에 여러개의 객체를 생성해서 불러오게끔 했는데 오류가 났다 그 이유는 Class의 Type을 참조하기 때문에,
	// 어떤걸 골라와야 하는지 알지 못 해서 그런 것 이었다.
	// 반환된 객체가 여러개일 경우에는 Autowired를 사용할 수 없고, 만일 사용하고 싶다면 Resource 라는 Annotation을 사용할 수 있는데, 그럴 이유는 잘 없다.
	// ctx를 하면 id를 참조하여 데려올 수 있음 참고
	

	/* 
	   @Autowired
	   1. 객체 타입(class)이 일치하는 객체를 찾아서 알아서 생성한다.
	   2. 필드, 생성자, setter를 대상으로 함
	   3. 필드는 필드 자체에 생성되고, 생성자나 setter는 매개변수에 생성
	  
      [ @Autowired ]
	  1. field에 @Autowired 지정하기
	     필드마다 붙여야 함. 필드가 10개이면 10개의 @Autowired가 필요함. 자주 안 씀.
	
	   @Autowired
	   private Board myBoard; // 반환타입이 Board인 Bean을 찾아서 가져와서 myBoard에 주입 --> Dependency Injection(DI) ** 중요한 용어
	*/
	/* [ @Autowired ] *****
	   2. 생성자에 @Autowired 지정하기
	      생성자를 만들면, 생성자의 매개변수로 자동 주입된다.
	      생성자에 @Autowired를 생략해도 된다.
	
	  private Board myBoard;				     // --- (3)
	  @Autowired	 // 생략할 수 있다
	  public BoardController(Board myBoard) {  // 생성자를 만들면, 매개변수로 자동 주입된다.  --- (1)
		 super();
		 this.myBoard = myBoard;				 // 매개변수로 들어와서 이것이 field로 전달 됨 --> private Board myBoard;로 전달. --- (2)
	  }
	*/
	/*
	 [ @Autowired ] *****
	 3. setter에 @Autowired 지정하기
	 	@Autowired를 꼭 적어야 한다.
	 	setter 메소드명을 마음대로 작성해도 된다.
	 	
     private Board myBoard;					// --- (3)
	 @Autowired  // 생략할 수 없다. 꼭 적어야 한다.
	 public void setBoard(Board myBoard) {   // 매개변수로 자동 주입된다. 	--- (1)			setter의 method명은 아무렇게나 정해도 상관없다 어차피 내부적으로 동작하는 것은 Autowired이다.
	 	 this.myBoard = myBoard;				// --- (2)
	 }
	*/

	private Board myBoard;		// 주로 많이 사용하게 될 방법은 2번과 3번이다. 1번은 잘 안 씀 

	public BoardController(Board myBoard) {
		super();
		this.myBoard = myBoard;
	}



	// GetMapping / PostMapping은 Spring 4.3부터 지원하므로 해당 Method Mapping을 사용하기 위해서는 반드시
	// version up이 필요하다!! 잊지말기  ** update는 pom.xml에서 하면 됨.
	@GetMapping("boardView.do") // 여기서 method를 작성할 필요가 없는 이유는 이미 annotation에서 언급하였기 때문.
	public String a(Model model) { // request(매개변수)는 좀 더 upgrade된 Model interface를 사용한다.(보안이 더 좋음) : Spring ver.
		model.addAttribute("board", myBoard);
		return "board/boardDetail";
	}
	
	// Model interface의 Method
	// addAttribute method를 이용하여 attribute 속성으로 등록한다. ("속성이름", 속성값);
	// setAttribute 아니고 addAttribute로 바뀌었음 헷갈리지 말기.
 	
}
