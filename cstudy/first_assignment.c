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
   printf("::: ������� ���α׷� :::\n");
   printf("1. �������� ���\n");
   printf("2. �������� ��ȸ\n");
   printf("3. ��ü���� ���\n");
   printf("0. ���α׷� ����\n");
}


void addBook(){

    Book newBook; 
    int book_num;
    char book_title[31];
    char book_author[31];

    
    while(1){
    	while (getchar() != '\n');
    	printf("�ű� å ��ȣ �Է� >>> ");
    	scanf("%d",&book_num);
		
         
        if(book_num<1001 || book_num>9999){
            printf("å ��ȣ�� 1001~9999 ���̾�� �մϴ�. >>> ");
        } else{
            newBook.number = book_num;
            break;
        }
    }

    // cmd�� input�� �ְ��� new line�� ���� Enter�� input���� �ν�, ��Ⱑ ����.
    // fflush(stdin); -> ��� ���� ���������� 
    // + fflush(stdin)�Լ����� 	while (getchar() != '\n'); ���°� ����  
	while (getchar() != '\n');
    printf("�ű� å ���� �Է� >>> ");
   	    scanf("%s", newBook.title);

   	    
	while (getchar() != '\n');
    printf("�ű� å ���� �Է� >>> ");
        scanf("%s", newBook.author);

		

    
    if(book_idx==BOOK_ARR_SIZE){
        printf("�˼��մϴ�. ��������� ���� á���ϴ�.");
    } else{
            book_arr[book_idx] = newBook;
            book_idx++;
    }

}

void queryBook(){
    int qnum;
	int i;
    int isfound=0;

    printf("��ȸ�� å ��ȣ �Է� >>> ");
    while(1){
    scanf("%d",&qnum);
        fflush(stdin);  
        if(qnum<1001 || qnum>9999){
            printf("å ��ȣ�� 1001~9999 ���̾�� �մϴ�. >>> ");
        } else{
            break;
        }
    }


	 
    for (i = 0; i < book_idx; i++)
    {
        if(book_arr[i].number == qnum){
            printf("��ȸ ���: %s %s\n", book_arr[i].title, book_arr[i].author);
            isfound = 1;
            break;
        }
    }

    if(!isfound) {
        printf("������ȣ %d�� ���� ������ �����ϴ�.\n",qnum);
    }
    
     
}

void printBook(){
	int i;
    if(book_idx==0){
        printf("�˼��մϴ�. ����� ������ �����ϴ�.");
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
        printf("�޴� ���� (1,2,3,0) >>> ");
        scanf("%d",&choice);

        switch (choice)
        {
        case 0:{
            printf("���α׷��� �����մϴ�.\n");
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
            printf("�߸��� �޴� ��ȣ�Դϴ�. �ٽ� �������ֽʽÿ�.\n");
        }
        
        printf("\n");
        if(choice==0) break;
    }

	return 0; 
}
