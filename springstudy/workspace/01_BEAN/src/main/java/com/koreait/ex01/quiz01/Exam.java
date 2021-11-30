package com.koreait.ex01.quiz01;

import java.util.List;

public class Exam {

	private List<Integer> scores;
	private double average;
	private char grade;
	
	// getter / setter
	public List<Integer> getScores() {	
		return scores;
	}
	public void setScores(List<Integer> scores) { // <property name="scores"> 태그에 의해서 호출됨 - xml에 실린걸 가져오고 이게 호출 될 것
		this.scores = scores;
		setAverage();  /* 추가한 메소드 실행 */
		setGrade();	   /* 추가한 메소드 실행 */
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	public void setAverage() {	/* 추가 : overloading */
		int total = 0;
		int length = scores.size();
		for (int i = 0; i < length; i++) {
			total += scores.get(i);
		}
		this.average = (double)total / length;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	public void setGrade() { /* 추가 */
		if (average >= 90 ) grade = 'A';
		else if (average >= 80 ) grade = 'B';
		else if (average >= 70 ) grade = 'C';
		else if (average >= 60 ) grade = 'D';
		else grade = 'F';
	}
	
}
