#include<stdio.h>
#include<string.h>

// FUNCTION DECLARATION
void inputInformation();
void generateFile();


typedef struct{
	char name[31];
    int korean;
    int english;
    int math;
    int total;
    float avg;
} Student;

#define STUDENT_ARR_SIZE 3
#define FILE_NAME "score.csv"

Student student_arr[STUDENT_ARR_SIZE];


void inputInformation(){
	Student new_student;
	int kor, eng, math, total;
	int i; 


    printf("학생 3명의 정보를 차례대로 입력하세요\n\n");

    for (i = 0; i < STUDENT_ARR_SIZE; i++)
    {
     
        printf("학생 %d 이름 >>> ",i+1);
        gets(new_student.name);

        printf("국어 >>> ");
        while(1){
            scanf("%d",&kor);
            fflush(stdin);
            if(kor<0 || kor>100){
                printf("잘못된 점수입니다. 다시 입력하세요 >>> ");
            }  else{
                new_student.korean = kor;
                break;
            } 
        }
  
        printf("영어 >>> ");
        while(1){
            scanf("%d",&eng);
            fflush(stdin);
            if(eng<0 || eng>100){
                printf("잘못된 점수입니다. 다시 입력하세요 >>> ");
            }  else{
                new_student.english = eng;
                break;
            } 
        }
  
        printf("수학 >>> ");
        while(1){
            scanf("%d",&math);
            fflush(stdin);
            if(math<0 || math>100){
                printf("잘못된 점수입니다. 다시 입력하세요 >>> ");
            }  else{
                new_student.math = math;
                break;
            } 
        }

        total = kor+eng+math;
        new_student.total = total;
        new_student.avg = total/3.0;

        student_arr[i] = new_student;
    }
    

}


void generateFile(){

    FILE *fp;
    fp = fopen(FILE_NAME,"wt");

    fputs("성명,국어,영어,수학,총점,평균\n",fp);
    int i;

    for (i = 0; i < STUDENT_ARR_SIZE; i++)
    {   
        
        fputs(student_arr[i].name,fp);

        char kor_str[6];
        sprintf(kor_str,",%d,",student_arr[i].korean);
        fputs(kor_str,fp);

        char eng_str[5];
        sprintf(eng_str,"%d,",student_arr[i].english);
        fputs(eng_str,fp);
        
        char math_str[5];
        sprintf(math_str,"%d,",student_arr[i].math);
        fputs(math_str,fp);

        char total_str[5];
        sprintf(total_str,"%d,",student_arr[i].total);
        fputs(total_str,fp);

        char avg_str[7];
        sprintf(avg_str,"%.2f",student_arr[i].avg);
        fputs(avg_str,fp);

        fputs("\n",fp);
    }

    // 꼭 닫자.
    fclose(fp);
}

int main(){
    inputInformation();
    generateFile();
    printf("\n%s 파일이 생성되었습니다.",FILE_NAME);
}

