package test;

public class Ex3 {
	
	public static void main(String[] args) {
    // 임의의   나이를   저장한   뒤,   다음   기준에   따라   출력하시오.
    // 0~7세:   “미취학아동”
    // 8~13세:   “초등학생”
    // 14~16세:   “중학생”
    // 17~19세:   “고등학생”
    // 20~100세:   “성인”
    // 이외의   나이:   “불가능한   나이”
    // <<   출력   결과   >>
    // 나이   입력   >>>   8
    // 8세는   미취학아동입니다.
		
		
		int age = 28;
		if (age < 0 || age > 100) {
			System.out.println("불가능한 나이");
		}else if (age<=7){
			System.out.println(age + "세는 미취학아동입니다. ");
		}else if (age<=13){
			System.out.println(age + "세는 초등학생입니다.");
		}else if (age<=16){
			System.out.println(age + "세는 중학생입니다.");
		}else if (age<=19){
			System.out.println(age + "세는 고등학생입니다.");
		}else if (age<=100){
			System.out.println(age + "세는 성인입니다.");
		}else {
			System.out.println("불가능한 나이");
		
				


}

	
}
}