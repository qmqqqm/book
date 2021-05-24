/* 회원 등급0은 정상 1은 휴면 2는 탈퇴 */
/* 회원 */
DROP TABLE member_Mj 
	CASCADE CONSTRAINTS;
CREATE TABLE member_Mj (
	mem_No NUMBER primary key,, /* 회원번호 */
	mem_Id VARCHAR2(20) NOT NULL, /* 아이디 */
	mem_Pwd VARCHAR2(20) NOT NULL, /* 비밀번호 */
	mem_Name VARCHAR2(50) NOT NULL, /* 이름 */
	mem_Birth DATE NOT NULL, /* 생년월일 */
	mem_Gender VARCHAR2(10) NOT NULL, /* 성별 */
	mem_Hp VARCHAR2(20) Unique, /* 휴대전화번호 */
	mem_Point NUMBER, /* 포인트 */
	mem_Grade NUMBER  default 0/* 회원상태 */
);

drop SEQUENCE member_Mj_seq;
create SEQUENCE member_Mj_seq;
/* 컬럼추가 */
ALTER TABLE member_Mj ADD mem_image VARCHAR2(50);

insert into member_Mj values(member_Mj_seq.nextval,'wjsaudwo','111','전명재','1977/07/14','남','010-8465-5009',5000,0);
insert into member_Mj values(member_Mj_seq.nextval,'wjsaudwo1','111','전명재','1977/07/14','남','010-8465-5009',5000,0);
insert into member_Mj values(member_Mj_seq.nextval,'wjsaudwo2','111','전명재','1977/07/14','남','010-8465-5009',5000,0);
insert into member_Mj values(member_Mj_seq.nextval,'wjsaudwo3','111','전명재','1977/07/14','남','010-8465-5009',5000,0);
select * from member_Mj;
select * from member_Mj;
purge recyclebin;
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

drop SEQUENCE product_Mj_seq;
create SEQUENCE product_Mj_seq;
insert into product_Mj values(product_Mj_seq.nextval,'어린왕자자','aa','쌩떽쥐베리','경영',10000,100,0.1,'테스트');
insert into product_Mj values(product_Mj_seq.nextval,'어린왕자1','aa','쌩떽쥐베리','경영',10000,100,0.1,'테스트');
insert into product_Mj values(product_Mj_seq.nextval,'어린왕자2','aa','쌩떽쥐베리','경영',10000,100,0.1,'테스트');
insert into product_Mj values(product_Mj_seq.nextval,'어린왕자3','aa','쌩떽쥐베리','경영',10000,100,0.1,'테스트');
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
drop SEQUENCE order_Mj_seq;
create SEQUENCE order_Mj_seq;

insert into order_Mj values(order_Mj_seq.nextval,1,'wjsaudwo','aa','서울',1,9000);
insert into order_Mj values(order_Mj_seq.nextval,1,'wjsaudwo','aa','서울',1,9000);
insert into order_Mj values(order_Mj_seq.nextval,1,'wjsaudwo','aa','서울',1,9000);
insert into order_Mj values(order_Mj_seq.nextval,1,'wjsaudwo','aa','서울',1,9000);

select * from order_Mj;

/* 직원 */
CREATE TABLE admin_Mj (
	adm_No NUMBER NOT NULL, /* 직원번호 */
	adm_Id VARCHAR2(20) NOT NULL, /* 아이디 */
	adm_Pwd VARCHAR2(20) NOT NULL, /* 비밀번호 */
	adm_Hp VARCHAR2(20) NOT NULL /* 휴대전화번호 */
);

drop SEQUENCE admin_Mj_seq;
create SEQUENCE admin_Mj_seq;

insert into admin_Mj values(admin_Mj_seq.nextval,'admin','111','010-8465-5009');
insert into admin_Mj values(admin_Mj_seq.nextval,'admin1','111','010-8465-5009');
insert into admin_Mj values(admin_Mj_seq.nextval,'admin2','111','010-8465-5009');
insert into admin_Mj values(admin_Mj_seq.nextval,'admin3','111','010-8465-5009');
select * from admin_Mj;

/* 자유게시판 */
delete from freeBoard_Mj;

CREATE TABLE freeBoard_Mj (
	free_No NUMBER NOT NULL, /* 게시판번호 */
	mem_Name VARCHAR2(50) NOT NULL, /* 회원이름 */
	mem_Id VARCHAR2(20) NOT NULL, /* 회원ID */
	title VARCHAR2(100) NOT NULL, /* 제목 */
	free_regDate DATE DEFAULT SYSDATE, /* 글쓴날자 */
	free_modifiedDate DATE, /* 수정날자 */
	free_ReadCount NUMBER, /* 조회수 */
	free_Content VARCHAR2(2000) NOT NULL /* 내용 */
);

drop SEQUENCE freeBoard_Mj_seq;
create SEQUENCE freeBoard_Mj_seq;

insert into freeBoard_Mj values(freeBoard_Mj_seq.nextval,'전명재','wjsaudwo','제목1',SYSDATE,SYSDATE,0,'테스트');
insert into freeBoard_Mj values(freeBoard_Mj_seq.nextval,'전명재','wjsaudwo1','제목2',SYSDATE,SYSDATE,0,'테스트');
insert into freeBoard_Mj values(freeBoard_Mj_seq.nextval,'전명재','wjsaudwo2','제목3',SYSDATE,SYSDATE,0,'테스트');
insert into freeBoard_Mj values(freeBoard_Mj_seq.nextval,'전명재','wjsaudwo3','제목4',SYSDATE,SYSDATE,0,'테스트');

select * from freeBoard_Mj;

select no,free_No,mem_No,mem_Name,mem_Id , title,
	free_regDate ,free_modifiedDate ,free_ReadCount, 
	free_Content from freeBoard_Mj_view
	where No between 1 and 1+1;
	
/* 자유게시판 *페이징 처리용 뷰생성 */	
	drop view freeBoard_Mj_view;
create or replace view freeBoard_Mj_view
as
select rownum no,free_No,mem_Name,mem_Id , title,
	free_regDate ,free_modifiedDate ,free_ReadCount, 
	free_Content,isshow from (select * from freeBoard_Mj
	order by free_regDate desc);

select * from freeBoard_Mj_view;
	
--자유게시판 컬럼추가------------	
ALTER TABLE freeBoard_Mj ADD isShow number default 1;
ALTER TABLE freeBoard_Mj ADD isShow number default 1;
ALTER TABLE freeBoard_Mj DROP COLUMN isShow;	
select * from freeBoard_Mj;

----뷰테스트 -----
select free_No,mem_Name,mem_Id , title,
					free_regDate ,free_modifiedDate ,free_ReadCount,free_Content
					from freeBoard_Mj_view
					where No between 1 and 3 and isshow=1; 
					
					and isshow=1;
					
		
select count(*) as cnt from freeBoard_Mj WHERE isshow=1;
select * from freeBoard_Mj;

UPDATE freeBoard_Mj SET  isShow=0 WHERE  free_No=4;	

update member_Mj set mem_Name='수정', mem_Birth='19770714', mem_Gender='몰라', mem_Hp='몰라' where mem_Id ='wjsaudwo';
select * from member_Mj;
UPDATE member_Mj SET 	  mem_Grade=0 WHERE  mem_Id='wjsaudwo';

purge recyclebin;
select * from cart_ji;

desc product_book;
select * from product_book;
select book_id, book_kind,book_title, book_price,book_count,author,publishing_com,publishing_date,book_image,book_content,discount_rate,reg_date from product_book;

--검색기능 쿼리문 테스트
select book_id, book_kind,book_title, book_price,book_count, 
	 author,publishing_com,publishing_date,book_image,
	 book_content,discount_rate,reg_date 
	 from product_book 
     where book_content like  '%세계사%' or book_title like '%세계사%'  or author like '%세계사%' or publishing_com like '%세계사%';

select * from admin_mj;
select * from tab;
select * from order_Jg;
select * from product_book;


alter table qnaboard_JI modify (qna_title varchar2(4000)) ;
alter table qnaboard_JI modify (qna_content varchar2(4000)) ;

select * from member_Mj;