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
   printf("::: 도서등록 프로그램 :::\n");
   printf("1. 도서정보 등록\n");
   printf("2. 도서정보 조회\n");
   printf("3. 전체도서 목록\n");
   printf("0. 프로그램 종료\n");
}


void addBook(){

Book n_book;
int nb_no;
char nb_title[31];
char nb_author[31];

printf("신규 책 번호 입력 >>> ");

while(1) {
	scanf("%d", &nb_no);
	fflush(stdin);
	if(nb_no < 1001 || nb_no > 9999){
		printf("책 번호는 1001-9999 사이로 입력");
	}else{
		n_book.b_no=nb_no;
		break;
	}
}

    // cmd에 input을 넣고나서 new line을 위한 Enter가 input으로 인식, 찌꺼기가 남음.
    // fflush(stdin); -> 찌꺼기 변기 물내려야함 
    // + fflush(stdin)함수보다 	while (getchar() != '\n'); 쓰는게 좋음  
	
    printf("신규 책 제목 입력 >>> ");
   	    scanf("%s", n_book.b_title);
   	    fflush(stdin);

   	    
	
    printf("신규 책 저자 입력 >>> ");
        scanf("%s", n_book.b_author);

if(book_idx==LENGTH){
	printf("저장공간이 가득 찼습니다.");
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
