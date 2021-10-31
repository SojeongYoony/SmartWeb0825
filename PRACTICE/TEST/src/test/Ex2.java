package test;

public class Ex2 {

	public static void main(String[] args) {
		// 다음	time을	시,	분,	초로	변경하는	코드를	작성하고	결과를	확인하시오.
		// <<	선언할	변수	>>
		// int	time	=	3690;		//	3690초를	의미한다.
		// int	hour;
		// int	minute;
		// int	second;
		// <<	출력	결과	>>
		// 3690초	=	1시간	1분	30초
		
		int time = 3690; //3690초 
		int hour = time/3600;
		int minute = time % 3600 / 60;
		int second = time % 60;
		
		System.out.println(time + "초" + "\t" + "=" + "\t" +hour + "시간" + minute + "분  " + second + "초");
		

	}

}
