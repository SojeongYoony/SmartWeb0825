#include<stdio.h>


// FUNCTION DECLARATION
void menu();
void addBook();
void queryBook();
void printBook();


typedef struct{
	int number;
	char title[31];
	char author[31];
} Book;


#define BOOK_ARR_SIZE 10

Book book_arr[BOOK_ARR_SIZE];
int book_idx = 0;


void menu(){
   printf("::: 도서등록 프로그램 :::\n");
   printf("1. 도서정보 등록\n");
   printf("2. 도서정보 조회\n");
   printf("3. 전체도서 목록\n");
   printf("0. 프로그램 종료\n");
}


void addBook(){

    Book newBook; 
    int book_num;
    char book_title[31];
    char book_author[31];

    
    while(1){
    	while (getchar() != '\n');
    	printf("신규 책 번호 입력 >>> ");
    	scanf("%d",&book_num);
		
         
        if(book_num<1001 || book_num>9999){
            printf("책 번호는 1001~9999 사이어야 합니다. >>> ");
        } else{
            newBook.number = book_num;
            break;
        }
    }

    // cmd에 input을 넣고나서 new line을 위한 Enter가 input으로 인식, 찌꺼기가 남음.
    // fflush(stdin); -> 찌꺼기 변기 물내려야함 
    // + fflush(stdin)함수보다 	while (getchar() != '\n'); 쓰는게 좋음  
	while (getchar() != '\n');
    printf("신규 책 제목 입력 >>> ");
   	    scanf("%s", newBook.title);

   	    
	while (getchar() != '\n');
    printf("신규 책 저자 입력 >>> ");
        scanf("%s", newBook.author);

		

    
    if(book_idx==BOOK_ARR_SIZE){
        printf("죄송합니다. 저장공간이 가득 찼습니다.");
    } else{
            book_arr[book_idx] = newBook;
            book_idx++;
    }

}

void queryBook(){
    int qnum;
	int i;
    int isfound=0;

    printf("조회할 책 번호 입력 >>> ");
    while(1){
    scanf("%d",&qnum);
        fflush(stdin);  
        if(qnum<1001 || qnum>9999){
            printf("책 번호는 1001~9999 사이어야 합니다. >>> ");
        } else{
            break;
        }
    }


	 
    for (i = 0; i < book_idx; i++)
    {
        if(book_arr[i].number == qnum){
            printf("조회 결과: %s %s\n", book_arr[i].title, book_arr[i].author);
            isfound = 1;
            break;
        }
    }

    if(!isfound) {
        printf("도서번호 %d를 가진 도서가 없습니다.\n",qnum);
    }
    
     
}

void printBook(){
	int i;
    if(book_idx==0){
        printf("죄송합니다. 저장된 도서가 없습니다.");
        return;
    }

    for (i = 0; i < book_idx; i++)
    {
        printf("%d %s %s\n",book_arr[i].number,book_arr[i].title,book_arr[i].author);
    }
    
}

int main()
{	
    int choice;
    while(1){
        menu();
        printf("메뉴 선택 (1,2,3,0) >>> ");
        scanf("%d",&choice);

        switch (choice)
        {
        case 0:{
            printf("프로그램을 종료합니다.\n");
            break;
        }
        case 1:{
            addBook();
            break;
        }
        case 2:{
            queryBook();
            break;
        }
        case 3:{
            printBook();
            break;
        }    
        
        default:
            printf("잘못된 메뉴 번호입니다. 다시 선택해주십시오.\n");
        }
        
        printf("\n");
        if(choice==0) break;
    }

	return 0; 
}
