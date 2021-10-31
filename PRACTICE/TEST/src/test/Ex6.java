package test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Ex6 {
	
		
		public static void main(String[] args) {

			Scanner sc = new Scanner(System.in);
			Set<Integer> unit = new HashSet<Integer>();
			
			System.out.print("배열의 크기를 정해주세요 > " );
			int size = sc.nextInt();
			while(unit.size()<size) {
			   for(int i=0; i<size; i++) {
				   unit.add((int)(Math.random()*100)+1);
				   break;
			   } 
			}
			System.out.println("unit의 배열 : " + unit);	
		
	}
}
