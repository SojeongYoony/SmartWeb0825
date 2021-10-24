#include<stdio.h>


// FUNCTION DECLARATION
void menu();
void addBook();



typedef struct{
	int b_no;
	char b_title[31];
	char b_author[31];
} Book;


#define LENGTH 10

Book books[LENGTH];
int book_idx = 0;


void menu(){
   printf("::: ������� ���α׷� :::\n");
   printf("1. �������� ���\n");
   printf("2. �������� ��ȸ\n");
   printf("3. ��ü���� ���\n");
   printf("0. ���α׷� ����\n");
}


void addBook(){

Book n_book;
int nb_no;
char nb_title[31];
char nb_author[31];

printf("�ű� å ��ȣ �Է� >>> ");

while(1) {
	scanf("%d", &nb_no);
	fflush(stdin);
	if(nb_no < 1001 || nb_no > 9999){
		printf("å ��ȣ�� 1001-9999 ���̷� �Է�");
	}else{
		n_book.b_no=nb_no;
		break;
	}
}

    // cmd�� input�� �ְ��� new line�� ���� Enter�� input���� �ν�, ��Ⱑ ����.
    // fflush(stdin); -> ��� ���� ���������� 
    // + fflush(stdin)�Լ����� 	while (getchar() != '\n'); ���°� ����  
	
    printf("�ű� å ���� �Է� >>> ");
   	    scanf("%s", n_book.b_title);
   	    fflush(stdin);

   	    
	
    printf("�ű� å ���� �Է� >>> ");
        scanf("%s", n_book.b_author);

if(book_idx==LENGTH){
	printf("��������� ���� á���ϴ�.");
	} else {
		books[LENGTH] = n_book;
		book_idx++;
	}
		


}
int main() {
	menu();
	addBook();
	return 0;
}
