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
   printf("1. �������� ���\n");
   printf("2. �������� ��ȸ\n");
   printf("3. ��ü���� ���\n");
   printf("0. ���α׷� ����\n");    
   
}

// 5.
void addBook() {
   int i;
   if ( i < 10) {      
      printf("������ȣ �Է� >>>  "); scanf("%d\n", &books[i].bNo);
      printf("������ �Է� >>>  "); scanf("%s\n", books[i].bName);
      printf("�۰��� �Է� >>> "); scanf("%s\n", books[i].wrName); 
     
   } else {
      printf("���̻� ���� �� �� �����ϴ�.");
   }
} 

// 6. 
void queryBook() {
   int i;
   printf("��ȸ �� ������ȣ �Է� >>> "); scanf("%d\n", books[i].bNo);
   printf("��ȸ ��� : %s\n, %s/n", books[i].bName, books[i].wrName);      
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
   printf("�޴� ����(1,2,3,0) >>> "); scanf("%d", &a);
   switch(a) {
      case 1: addBook(); break;
      case 2: queryBook(); break;
      case 3: printBook(); break;
      case 0: printf("���α׷� ����");
      deault: printf("�ٽ� ���� �ϼ���.");
      
   }
   
   return 0;
}


