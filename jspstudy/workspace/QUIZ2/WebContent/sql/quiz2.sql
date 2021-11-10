/* table */
DROP TABLE board;
CREATE TABLE board
(
	idx			NUMBER,
	title 		VARCHAR2(100),
	writer 		VARCHAR2(100),
	content 	VARCHAR2(1000),
	register	VARCHAR2(10),
	PRIMARY KEY(idx)
);
/* register : string --> 작성일 */

/* sequence */
DROP SEQUENCE board_seq;
CREATE SEQUENCE board_seq INCREMENT BY 1 START WITH 1000 NOCYCLE NOCACHE;

/* INSERT */
