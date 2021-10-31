package test;

import java.util.Scanner;

public class Ex7 {

	// 발생한 난수(r)가 기존 배열에 존재하는지 확인하는 메소드 
		public static boolean exists(int a[], int from, int r) {
			for (int j = 0; j < from; j++) {
				if (a[j] == r) {
					return true;	// 존재하면 true 리턴
				}
			}
			return false;			// 존재하지 않으면 false 리턴
		}

		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("몇 개의 랜덤을 생성할까요? >> ");
			int n = scanner.nextInt();

			if (n <= 0 || n > 100) {
				System.out.print("1~100사이로 입력하세요!");
				scanner.close();
				return; // 프로그램 종료
			}
			
			int array[] = new int[n]; // n개의 정수 배열 생성

			for (int i = 0; i < array.length; i++) {
				int r = (int) (Math.random() * 100 + 1); // 1~100 범위의 랜덤 정수
				// 정수 r이 배열 array[0] ~ array[i-1] 에 있는지 검사
				if (exists(array, i, r)) {	// 중복이 있다면
					i--;					// 다시 랜덤을 발생시켜 입력해야 하므로 증가된 인덱스를 취소시킴
					continue;
				}
				array[i] = r;
			}

			for (int i = 0; i < array.length; i++) {
				if (i == 0)
					System.out.print(array[i] + "\t");
				else {
					if (i % 10 == 0)			// 한 줄에 10개씩 출력하기 위해서
						System.out.println();	// 다음 줄로 넘어가기
					System.out.print(array[i] + "\t");
				}
			}

			scanner.close();
	}

}
