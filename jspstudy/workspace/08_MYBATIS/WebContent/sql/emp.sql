DROP TABLE emp;
CREATE TABLE emp 
(
	num		NUMBER	PRIMARY KEY,
	name	VARCHAR2(20),
	salary  NUMBER,
	hire	VARCHAR2(10)	-- '2021-11-10'
);

DROP SEQUENCE emp_seq;
CREATE SEQUENCE emp_seq INCREMENT BY 1 START WITH 10000 NOMINVALUE NOMAXVALUE NOCYCLE NOCACHE;

INSERT INTO emp VALUES (emp_seq.nextval, '이정재', 3600, '2021-11-10');
COMMIT