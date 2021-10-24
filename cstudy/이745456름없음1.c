#include <stdio.h>

// 1.
typedef struct book {
   long bNo;
   char bName[31];
   char wrName[31];
   
} Book;

// 2.
#define LENGTH 10   

// 3.   
Book books[LENGTH];

// 4.
void menu() {
   printf("1. 도서정보 등록\n");
   printf("2. 도서정보 조회\n");
   printf("3. 전체도서 목록\n");
   printf("0. 프로그램 종료\n");    
   
}

// 5.
void addBook() {
   int i;
   if ( i < 10) {      
      printf("도서번호 입력 >>>  "); scanf("%d\n", &books[i].bNo);
      printf("도서명 입력 >>>  "); scanf("%s\n", books[i].bName);
      printf("작가명 입력 >>> "); scanf("%s\n", books[i].wrName); 
     
   } else {
      printf("더이상 저장 할 수 없습니다.");
   }
} 

// 6. 
void queryBook() {
   int i;
   printf("조회 할 도서번호 입력 >>> "); scanf("%d\n", books[i].bNo);
   printf("조회 결과 : %s\n, %s/n", books[i].bName, books[i].wrName);      
}

// 7.
void printBook() {
   int i;
   for (i = 0; i < LENGTH; i++) {
      printf("%d %s %s\n", &books[i].bNo, books[i].bName, books[i].wrName);   
   } 
}

// 8.
int main() {
   int a;
   menu();
   printf("메뉴 선택(1,2,3,0) >>> "); scanf("%d", &a);
   switch(a) {
      case 1: addBook(); break;
      case 2: queryBook(); break;
      case 3: printBook(); break;
      case 0: printf("프로그램 종료");
      deault: printf("다시 선택 하세요.");
      
   }
   
   return 0;
}


