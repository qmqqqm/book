DROP INDEX PK_member_Mj;

DROP INDEX PK_product_Mj;

DROP INDEX PK_order_Mj;

DROP INDEX PK_admin_Mj;

DROP INDEX PK_freeBoard_Mj;

/* 회원 */
DROP TABLE member_Mj 
	CASCADE CONSTRAINTS;

/* 상품 */
DROP TABLE product_Mj 
	CASCADE CONSTRAINTS;

/* 주문 */
DROP TABLE order_Mj 
	CASCADE CONSTRAINTS;

/* 직원 */
DROP TABLE admin_Mj 
	CASCADE CONSTRAINTS;

/* 자유게시판 */
DROP TABLE freeBoard_Mj 
	CASCADE CONSTRAINTS;

/* 회원 */
CREATE TABLE member_Mj (
	mem_No NUMBER NOT NULL, /* 회원번호 */
	mem_Id VARCHAR2(20) NOT NULL, /* 회원ID */
	mem_Pwd VARCHAR2(20) NOT NULL, /* 비밀번호 */
	mem_Name VARCHAR2(50) NOT NULL, /* 이름 */
	mem_Birth DATE NOT NULL, /* 생년월일 */
	mem_Gender VARCHAR2(10) NOT NULL, /* 성별 */
	mem_Hp VARCHAR2(20) NOT NULL, /* 휴대전화번호 */
	mem_Point NUMBER, /* 포인트 */
	mem_Grade NUMBER /* 회원상태 */
);

CREATE UNIQUE INDEX PK_member_Mj
	ON member_Mj (
		mem_Id ASC
	)
	STORAGE (
		BUFFER_POOL DEFAULT
	)
	NOLOGGING
	NOCOMPRESS
	NOSORT
	NOPARALLEL;

ALTER TABLE member_Mj
	ADD
		CONSTRAINT PK_member_Mj
		PRIMARY KEY (
			mem_Id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 상품 */
CREATE TABLE product_Mj (
	pro_No NUMBER NOT NULL, /* 책번호 */
	pro_Name VARCHAR2(40) NOT NULL, /* 책이름 */
	pro_Path VARCHAR2(50) NOT NULL, /* 책이미지 */
	pro_Author VARCHAR2(40) NOT NULL, /* 저자 */
	pro_Publisher VARCHAR2(40) NOT NULL, /* 출판사 */
	pro_Price NUMBER NOT NULL, /* 가격 */
	pro_Point NUMBER, /* 포인트 */
	pro_Discount NUMBER, /* 할인률 */
	pro_Information VARCHAR2(2000) NOT NULL /* 책소개 */
);

CREATE UNIQUE INDEX PK_product_Mj
	ON product_Mj (
		pro_No ASC
	)
	STORAGE (
		BUFFER_POOL DEFAULT
	)
	NOLOGGING
	NOCOMPRESS
	NOSORT
	NOPARALLEL;

ALTER TABLE product_Mj
	ADD
		CONSTRAINT PK_product_Mj
		PRIMARY KEY (
			pro_No
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 주문 */
CREATE TABLE order_Mj (
	ord_No NUMBER NOT NULL, /* 주문번호 */
	pro_No NUMBER NOT NULL, /* 책번호 */
	mem_Id VARCHAR2(20) NOT NULL, /* 회원ID */
	pro_Path VARCHAR2(50) NOT NULL, /* 책이미지 */
	ord_Address VARCHAR2(80) NOT NULL, /* 배송주소 */
	ord_Quantities NUMBER NOT NULL, /* 주문수량 */
	ord_Payment NUMBER /* 결제금액 */
);

CREATE UNIQUE INDEX PK_order_Mj
	ON order_Mj (
		ord_No ASC,
		pro_No ASC,
		mem_Id ASC
	)
	STORAGE (
		BUFFER_POOL DEFAULT
	)
	NOLOGGING
	NOCOMPRESS
	NOSORT
	NOPARALLEL;

ALTER TABLE order_Mj
	ADD
		CONSTRAINT PK_order_Mj
		PRIMARY KEY (
			ord_No,
			pro_No,
			mem_Id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 직원 */
CREATE TABLE admin_Mj (
	adm_No NUMBER NOT NULL, /* 직원번호 */
	adm_Id VARCHAR2(20) NOT NULL, /* 아이디 */
	adm_Pwd VARCHAR2(20) NOT NULL, /* 비밀번호 */
	adm_Hp VARCHAR2(20) NOT NULL /* 휴대전화번호 */
);

CREATE UNIQUE INDEX PK_admin_Mj
	ON admin_Mj (
		adm_No ASC
	)
	STORAGE (
		BUFFER_POOL DEFAULT
	)
	NOLOGGING
	NOCOMPRESS
	NOSORT
	NOPARALLEL;

ALTER TABLE admin_Mj
	ADD
		CONSTRAINT PK_admin_Mj
		PRIMARY KEY (
			adm_No
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 자유게시판 */
CREATE TABLE freeBoard_Mj (
	free_No NUMBER NOT NULL, /* 게시판번호 */
	mem_Name VARCHAR2(50) NOT NULL, /* 회원이름 */
	mem_Id VARCHAR2(20) NOT NULL, /* 회원ID */
	title VARCHAR2(100) NOT NULL, /* 제목 */
	free_regDate DATE DEFAULT SYSDATE, /* 글쓴날자 */
	free_modifiedDate DATE DEFAULT SYSDATE, /* 수정날자 */
	free_ReadCount NUMBER, /* 조회수 */
	free_Content VARCHAR2(2000) NOT NULL /* 내용 */
);

CREATE UNIQUE INDEX PK_freeBoard_Mj
	ON freeBoard_Mj (
		free_No ASC,
		mem_Id ASC
	)
	STORAGE (
		BUFFER_POOL DEFAULT
	)
	NOLOGGING
	NOCOMPRESS
	NOSORT
	NOPARALLEL;

ALTER TABLE freeBoard_Mj
	ADD
		CONSTRAINT PK_freeBoard_Mj
		PRIMARY KEY (
			free_No,
			mem_Id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE order_Mj
	ADD
		CONSTRAINT FK_product_Mj_TO_order_Mj
		FOREIGN KEY (
			pro_No
		)
		REFERENCES product_Mj (
			pro_No
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE order_Mj
	ADD
		CONSTRAINT FK_member_Mj_TO_order_Mj
		FOREIGN KEY (
			mem_Id
		)
		REFERENCES member_Mj (
			mem_Id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE freeBoard_Mj
	ADD
		CONSTRAINT FK_member_Mj_TO_freeBoard_Mj
		FOREIGN KEY (
			mem_Id
		)
		REFERENCES member_Mj (
			mem_Id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

