package practice_10_12;

import java.util.Scanner;

public class For_06 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int c = sc.nextInt();
		
		for(int i = 1; i <=c; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			System.out.println("Case #" + i +": " + (a+b));
			
		}
		sc.close();
	}

}
