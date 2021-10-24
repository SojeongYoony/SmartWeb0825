#include <stdio.h>

#define LENGTH 10

typedef int AgeData;

AgeData age;	// int age;   AgeData=int type이 되는 것  

typedef struct book {		// 타입을 만들어서 쓰겠다 type define 
	int bookNo;
	char title[31];
	char author[31];
} Book;		// Book 타입의 정의

Book books[LENGTH];		// Book books[10] 길이가 10인 배열
int idx = 0;

void menu() {
	printf("1. 등록");
	printf("1. 등록");
	printf("1. 등록");
	printf("1. 등록");	
}  

void addBook() {
	// Book을 저장할 인덱스는 idx이다.
	// 인덱스 가용 범위 : 0 ~ (LENGTH - 1)
	if (idx == LENGTH) {
		printf("배열이 꽉 찼음\n");
		return;
	} 
	// 책 번호 : 1001 ~ 9999 
	if
}
