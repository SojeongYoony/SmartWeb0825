package test;

// TestInterface를 구현하는 클래스
// TestInterface(부모)
// TestA(자식)
public class TestA implements TestInterface{		// 같은 package 내에 있으면 import 필요 없음. 외부에 있으면 import필요	** 일반 클래스는 extends interface는 implements
	// 반드시 모든 추상메소드를 오버라이드 할 것
	@Override
	public void doSome() {
		// 부모는 메소드를 물려만 주고 (뼈대만 있음. 내용이 없는거) 자식이 모든 것을 구현함.		extends는 부모에게 method 내용이 있고, 자식은 그 method에 살을 덧붙이는 것.
		System.out.println("TESTA");
	}

}
