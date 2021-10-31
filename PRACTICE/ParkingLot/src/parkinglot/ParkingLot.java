package parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingLot {
	
	private String name;
	private List<Car> cars; // 자동차 리스트가있고 차가 들어올 것이다. 자동차들을 저장해라
	private Scanner sc = new Scanner(System.in);
	
	public ParkingLot(String name) {
		this.name = name;
		cars = new ArrayList<Car>(); // 자동차가 저장 될 arraylist를 만듦
	}
	
	public void addCar() {
		System.out.println("차량번호 입력 >>>" );
		String carNo = sc.next();
		System.out.println("모델 입력 >>>");
		String model = sc.next();
//		Car car = new Car(carNo, model);
//		cars.add(car);
		cars.add(new Car(carNo, model));   // 위의 문장과 동일하다.
				
		
	}
	
	public void deleteCar() {
		System.out.println("제거할 차량번호 입력 >>> ");
		String carNo = sc.next();
		for (Car car : cars) {   // car가 cars 중 하나
			if(car.getCarNo().equals(carNo)) {   // 만약 int : if (car.getCarNo()==carNo) {
				System.out.println(car.getCarNo() + "차량이 삭제 되었다.");
				cars.remove(car);
				return;
				
			}
		}
		
		System.out.println(carNo + "차량이 존재하지 않습니다.");
		
	}
	
	public void printAllCar() {
		for (Car car : cars) {
			System.out.println(car);
		}
	}
	
	
	public void menu() {
		System.out.println("1.추가, 2.삭제, 3.전체조회, 0.종료");
	}
	
	public void carManage() {
		
		try {
			while(true) {
				menu();
				System.out.print("선택(1, 2, 3, 0) >>>" );
				switch (sc.nextInt()) {
				case 1: addCar(); break;
				case 2: deleteCar(); break;
				case 3: printAllCar(); break;
				case 0: System.out.println("프로그램 종료"); return;
				default : System.out.println("다시 선택하세요.");
				}
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
}
