#include <stdio.h>

#define LENGTH 10

typedef int AgeData;

AgeData age;	// int age;   AgeData=int type�� �Ǵ� ��  

typedef struct book {		// Ÿ���� ���� ���ڴ� type define 
	int bookNo;
	char title[31];
	char author[31];
} Book;		// Book Ÿ���� ����

Book books[LENGTH];		// Book books[10] ���̰� 10�� �迭
int idx = 0;

void menu() {
	printf("1. ���");
	printf("1. ���");
	printf("1. ���");
	printf("1. ���");	
}  

void addBook() {
	// Book�� ������ �ε����� idx�̴�.
	// �ε��� ���� ���� : 0 ~ (LENGTH - 1)
	if (idx == LENGTH) {
		printf("�迭�� �� á��\n");
		return;
	} 
	// å ��ȣ : 1001 ~ 9999 
	if
}
