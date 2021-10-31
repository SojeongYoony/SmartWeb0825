package test;

public class Ex4 {

	public static void main(String[] args) {

		int a = 45;
		int b = a*a; //2025
		int c = b/100;
		int d = b%100;
		
		System.out.println(c + d == a ? "카프리카 수가 맞다" : "카프리카 수가 아니다."); 
	}

}
