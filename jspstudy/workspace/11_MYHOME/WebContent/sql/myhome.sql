/*
	댓글형 게시판
	1. 공지사항 : notice
	2. 댓     글  : reply
*/

DROP TABLE REPLY;
DROP TABLE NOTICE;

CREATE TABLE NOTICE
(
/* 게시글 번호 */	NNO				NUMBER,				
/* 작성자 이름 */	WRITER  		VARCHAR2(50),
/* 게시글 제목 */	TITLE			VARCHAR2(2000),	/* NOTNULL 필요 */
/* 게시글 내용 */	CONTENT 		VARCHAR2(4000),
/* 글의 조회수 */	HIT 			NUMBER,
/* 작성자 IP */	IP				VARCHAR2(32),
/* 최초 작성일 */	NDATE			DATE,
/* 최종 수정일 */	NLASTMODIFIED 	DATE
);

CREATE TABLE REPLY
(
/* 댓글 번호    */	RNO				NUMBER,
/* 댓글작성자  */	WRITER			VARCHAR2(50),
/* 댓글 내용    */	CONTENT			VARCHAR2(2000),
/* 작성자 IP */	IP				VARCHAR2(32),
/* 게시글 번호 */	NNO				NUMBER,
/* 최초 작성일 */	RDATE			DATE
);

ALTER TABLE NOTICE ADD CONSTRAINT NOTICE_PK PRIMARY KEY(NNO);
ALTER TABLE REPLY ADD CONSTRAINT REPLY_PK PRIMARY KEY(RNO);

ALTER TABLE REPLY ADD CONSTRAINT REPLY_NOTICE_FK FOREIGN KEY(NNO) REFERENCES NOTICE(NNO) ON DELETE CASCADE;

DROP SEQUENCE NOTICE_SEQ;
DROP SEQUENCE REPLY_SEQ;
CREATE SEQUENCE NOTICE_SEQ NOCACHE;
CREATE SEQUENCE REPLY_SEQ NOCACHE;



INSERT INTO NOTICE 
VALUES (NOTICE_SEQ.NEXTVAL, '관리자', '이용 시 주의사항', '바른말 사용하기', 0, '0:0:0:0:0:0:1', SYSDATE, SYSDATE);
COMMIT

/* 
	회원
	1. 회원 : member
	2. 기록 : member_log
*/
DROP TABLE MEMBER_LOG;
DROP TABLE MEMBER;

CREATE TABLE MEMBER
(
	/* 회원번호 */ MNO			NUMBER,
	/* 아이디    */ ID			VARCHAR2(32) NOT NULL UNIQUE,	/* PK가 아닌 외래키는 UNIQUE가 꼭 필요 함 */
	/* 비밀번호 */ PW			VARCHAR2(32) NOT NULL,
	/*  이름     */ NAME			VARCHAR2(50),
	/*  메일     */ EMAIL		VARCHAR2(200),
	/*  가입일  */ MDATE		DATE
);
CREATE TABLE MEMBER_LOG
(
	/* 기록번호 */ LNO			NUMBER,
	/*  아이디  */ ID			VARCHAR2(32),
	/*로그인일시*/ LOGIN		DATE
);

ALTER TABLE MEMBER ADD CONSTRAINT MEMBER_PK PRIMARY KEY(MNO);
ALTER TABLE MEMBER_LOG ADD CONSTRAINT MEMBER_LOG_PK PRIMARY KEY(LNO);

ALTER TABLE MEMBER_LOG ADD CONSTRAINT MEMBER_LOG_MEMBER_FK FOREIGN KEY(ID) REFERENCES MEMBER(ID) ON DELETE CASCADE;

DROP SEQUENCE MEMBER_SEQ;
DROP SEQUENCE MEMBER_LOG_SEQ;

CREATE SEQUENCE MEMBER_SEQ NOCACHE;
CREATE SEQUENCE MEMBER_LOG_SEQ NOCACHE;

INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL, 'admin', '1111', '관리자', 'admin@myhome.com', SYSDATE);
INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL, 'scott', '1111', '스콧', 'scott@myhome.com', SYSDATE);

COMMIT


/*
	계층형 게시판
	자유게시판 
*/

DROP TABLE FREE;
CREATE TABLE FREE
(
	/* 글번호       */	FNO				NUMBER,
	/* 작성자       */	WRITER			VARCHAR2(32),	
	/* 글내용       */	CONTENT			VARCHAR2(4000),
	/*  IP    */	IP				VARCHAR2(32),
	/* 조회수       */	HIT				NUMBER,
	/* 최초작성일 */	CREATED			DATE,
	/* 최종수정일 */	LASTMODIFIED 	DATE,
	/* 삭제여부    */	STATE			NUMBER, /* 정상 게시글 : 0, 삭제 게시글 : -1 */
	/* 게시글/댓글 */	DEPTH			NUMBER,	/* 게시글 : 0,    댓글	   : 1이상  */
	/* 동일그룹    */	GROUPNO			NUMBER,	/* 게시글 : 자신의 글번호(FNO), 댓글 : 게시글의 글번호(FNO)  댓글은 게시글의 글번호를 참조함 :: selfJoin :: have to be same type as fno */
	/* 그룹내순서 */	GROUPORD		NUMBER	/* 동일그룹내 표시순서를 의미 */
);

DROP SEQUENCE FREE_SEQ;
CREATE SEQUENCE FREE_SEQ NOCACHE;
ALTER TABLE FREE ADD CONSTRAINT FREE_PK PRIMARY KEY(FNO);














