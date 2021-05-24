create table member(
id varchar2(20),
pw varchar2(20),
name varchar2(20),
phone varchar2(20));

insert into member (id,pw,name,phone) values('abc','123','홍길동','010-1234-5678');
insert into member (id,pw,name,phone) values('def','456','홍길서','010-2254-1212');
insert into member (id,pw,name,phone) values('ghi','789','홍길남','010-1834-6178');
insert into member (id,pw,name,phone) values('abc','123','홍길북','010-0244-5228');

commit;
select * from member;

--drop table member;
drop table member; 

-- 고객(회원) 테이블 생성 
create table member( 
             id varchar2(50) primary key, 
             passwd varchar2(50) not null, 
             name varchar2(50) not null, 
             address varchar2(50), 
             tel varchar2(50) not null, 
             reg_date date 
); 

-- 관리자 테이블 생성 
create table manager ( 
             managerId varchar2(50) primary key, 
             managerPasswd varchar2(50) not null 
); 
--drop table manager; 

--drop table product_book; 
-- 도서 테이블 생성 
 --책아이디            book_id  number(10) primary key,                                 
 --종류            book_kind varchar2(3) not null,                    
 --제목            book_title varchar2(100) not null,                 
 --가격            book_price number(10) not null,                    
 --수량            book_count number(5) not null,                     
 --저자            author varchar2(20),                               
 --출판사            publishing_com varchar2(30),                       
 --출판일            publishing_date varchar2(15),                      
 --이미지(책)            book_image varchar2(16) default 'nothing.jpg',     
 --책설명            book_content varchar2(500),                        
 --할인률            discount_rate number(3),                           
 --등록일            reg_date Timestamp                                      
create table product_book (
             book_id  number primary key,                                 
             book_kind varchar2(30) not null,                    
             book_title varchar2(500) not null,                 
             book_price number not null,                    
             book_count number not null,                     
             author varchar2(200),                               
             publishing_com varchar2(300),                       
             publishing_date varchar2(150),                      
             book_image varchar2(160) default 'nothing.jpg',     
             book_content varchar2(300),                        
             discount_rate number,                           
             reg_date DATE DEFAULT SYSDATE                                      
); 
--drop table product_book; 
-- 시퀀스 생성 (도서) 
create sequence product_book_seq 
start with 1
increment by 1 
nocycle nocache; 

--drop SEQUENCE product_book_seq;

---- 시퀀스 생성
--CREATE SEQUENCE product_book_seq    -- 시퀀스명 설정
--INCREMENT BY 값             -- 증감값
--START WITH 값               -- 시작값 (증감값이 양수면 MINVALUE부터, 음수면 MAXVALUE부터 시작)
--NOMINVALUE OR MINVALUE 값   -- NOMINVALUE : 최소값 없음
--                            -- MINVALUE : 최소값 설정
--NOMAXVALUE OR MAXVALUE 값   -- NOMAXVALUE : 최대값 없음
--                            -- MAXVALUE : 최대값 설정
--NOCYCLE OR CYCLE            -- CYCLE : 최대값 도달 시 최소값부터 다시 시작
--                            -- NOCYCLE : 최대값 도달 시 시퀀스 생성 중단
--NOCACHE OR CACHE 값         -- 시퀀스를 빨리 제공하기 위해 메모리에 캐쉬 지정
--			    -- NOCACHE시 기본값 20

---- 시퀀스 수정
--ALTER SEQUENCE product_book_seq     -- 시퀀스 수정
--INCREMENT BY 값             -- 증감값
--NOMINVALUE OR MINVALUE 값   -- NOMINVALUE : 최소값 없음
--                            -- MINVALUE : 최소값 설정
--NOMAXVALUE OR MAXVALUE 값   -- NOMAXVALUE : 최대값 없음
--                            -- MAXVALUE : 최대값 설정
--NOCYCLE OR CYCLE            -- CYCLE : 최대값 도달 시 최소값부터 다시 시작
--                            -- NOCYCLE : 최대값 도달 시 시퀀스 생성 중단
--NOCACHE OR CACHE 값         -- 시퀀스를 빨리 제공하기 위해 메모리에 캐쉬 지정
--			    -- NOCACHE시 기본값 20


-- 도서 데이터생성 (도서) 
insert into product_book values (product_book_seq.nextval, '베스트셀러', '주린이가 가장 알고 싶은 최다질문 TOP 77',
16200, 30000, '염승환','메이트북스','2021년01월21일','best1','‘염블리’ 염승환이 전하는 주식투자 ',10,'2021-02-08'); 

insert into product_book values (product_book_seq.nextval, '베스트셀러', '달러구트 꿈 백화점',
13800, 30000, '이미예','팩토리나인 ','2020년 07월 08일','best2','‘염블리’ 잠들어야만 입장 가능한 꿈 백화점에서 일어나는
비밀스럽고도 기묘하며 가슴 뭉클한 판타지 소설 ',5,'2020-07-12'); 

insert into product_book values (product_book_seq.nextval, '베스트셀러', '2030 축의 전환',
18000, 30000, '마우로 기옌','리더스북 ','2020년 10월 16일','best3','변화는 코로나19에서 끝나지 않는다!
2030년, 세계는 더 심오하고 거대한 질적 전환을 맞이한다!',5,'2020-10-28'); 

insert into product_book values (product_book_seq.nextval, '베스트셀러', '공정하다는 착각',
18000, 30000, '마이클 샌델','와이즈베리','2020년 12월 01일','best4','‘마이클 샌델, 10여 년 만에 던지는 충격적 화두!
“지금 서 있는 그 자리, 정말 당신의 능력 때문인가?” ',10,'2020-12-18'); 

insert into product_book values (product_book_seq.nextval, '베스트셀러', '파친코',
14500, 30000, '이민진 ','문학사상','2021년01월21일','best5','어디에도 속하지 못했던 자이니치들의 분노와 슬픔에서 탄생한 대작! ',10,'2021-01-03'); 

insert into product_book values (product_book_seq.nextval, '베스트셀러', '모래알만 한 진실이라도',
16000, 30000, '박완서  ','세계사','2020년 12월 07일','best6','“다이아몬드에는 중고라는 것이 없지.
천년을 가도 만년을 가도 영원히 청춘인 돌.”',10,'2021-01-03');

insert into product_book values (product_book_seq.nextval, '베스트셀러', '모래알만 한 진실이라도',
16000, 30000, '박완서  ','세계사','2020년 12월 07일','best6','“다이아몬드에는 중고라는 것이 없지.
천년을 가도 만년을 가도 영원히 청춘인 돌.”',10,'2021-01-03');

insert into product_book values (product_book_seq.nextval, '베스트셀러', '아몬드',
12000, 30000, '손원평 ','창비','2017년 03월 31일','best7','“다이아몬드에는 중고라는 것이 없지.
천년을 가도 만년을 가도 영원히 청춘인 돌.”',10,'2021-01-03');

insert into product_book values (product_book_seq.nextval, '베스트셀러', '돈의 시나리오',
17000, 30000, '김종봉  ','다산북스','2021년 01월 13일','best8','계획이 있는 돈은 흔들리지 않는다',5,'2021-01-23');

insert into product_book values (product_book_seq.nextval, '베스트셀러', '주식투자 무작정 따라하기(2020)',
18000, 30000, '윤재수  ','길벗','2020년 01월 10일','best9','100만 왕초보가 감동한 최고의 주식투자 입문서',10,'2021-01-03');

insert into product_book values (product_book_seq.nextval, '베스트셀러', '해커스 토익 기출 보카 TOEIC VOCA',
12900, 30000, 'David Cho   ','세계사','2020년 03월 05일','best10','주제별 연상암기로 토익 영단어 30일 완성!',5,'2021-01-02');


insert into product_book values (product_book_seq.nextval, '전문도서', 'ADsP 데이터 분석 준전문가(2021)',
28000, 30000, '윤종식 ','데이터에듀','2021년01월21일','pro1','합격을 위한 완벽 요약집 ',10,'2021-02-08'); 

insert into product_book values (product_book_seq.nextval, '전문도서', 'SQL 자격검정 실전문제',
18000, 30000, '한국데이터진흥원','한국데이터진흥원 ','2016년 11월 17일','pro2','국가공인 SQL전문가 국가공인 SQL개발자',10,'2019-07-12'); 

insert into product_book values (product_book_seq.nextval, '전문도서', '컴퓨터활용능력 1급 필기(2021)',
20000, 30000, '길벗R&D','길벗 ','2020년 11월 24일','pro3','핵심요약+기출문제+모의고사 | 2021년',10,'2021-02-25'); 

insert into product_book values (product_book_seq.nextval, '전문도서', '컴퓨터활용능력 2급 실기(2021)',
22000, 30000, '길벗R&D ','길벗','2020년 10월 23일','pro4','컴활 함수 사전+자동 채점 프로그램+최신기출 ',10,'2020-11-28'); 

insert into product_book values (product_book_seq.nextval, '전문도서', '혼자 공부하는 파이썬',
18000, 30000, '윤인성 ','한빛미디어','2019년 06월 10일','pro5','파이썬 최신 버전 반영',10,'2020-11-13'); 

insert into product_book values (product_book_seq.nextval, '전문도서', '컴퓨터활용능력 1급 실기(2021)',
36000, 30000, '길벗R&D','길벗','2020년 12월 07일','pro6','컴활 함수 사전+자동 채점 프로그램+최신기출',10,'2021-01-03');

insert into product_book values (product_book_seq.nextval, '전문도서', '비전공자를 위한 이해할 수 있는 IT 지식',
16800, 30000, '최원영   ','티더블유아이지','2020년 07월 14일','pro6','IT 시대의 필수 교양서',10,'2020-05-13');

insert into product_book values (product_book_seq.nextval, '전문도서', '전산회계 1급 이론+실기+기출문제(2021)',
25900, 30000, '이남호 ','해커스금융','2021년 01월 18일','pro7','3주 합격! 동영상강의 183강',10,'2021-01-23');

insert into product_book values (product_book_seq.nextval, '전문도서', '전산세무 2급 이론+실기+기출문제',
25900, 30000, '이남호','해커스금융','2020년 04월 09일','pro8','따라만 하면 4주 합격!/동영상강의 93강',10,'2021-01-23');

insert into product_book values (product_book_seq.nextval, '전문도서', '회사에서 바로 통하는 실무 엑셀+파워포인트+워드&한글',
22000, 30000, '전미진  ','한빛미디어','2020년 04월 10일','pro9','사용 가능 현장 밀착형 입문서',10,'2021-01-13');

insert into product_book values (product_book_seq.nextval, '전문도서', '포토샵&일러스트레이터 CC 2021',
22000, 30000, '빨간고래','한빛미디어','2021년 01월 18일','pro10','누구나 쉽게 배워 제대로 써먹는 그래픽 입문서',10,'2021-01-20');


insert into product_book values (product_book_seq.nextval, '학습도서', '투자자산운용사 실제유형 모의고사 4회분 ',
30000, 30000, '유창호','시대고시기획','2021년 01월 05일','study1','신 기출분석 완벽 반영 ',10,'2021-01-20');

insert into product_book values (product_book_seq.nextval, '학습도서', '의학이론(2021)(그림으로 보는) ',
38000, 30000, '정혜심','고시아카데미','2021년 01월 15일','study1','신 기출분석 완벽 반영 ',10,'2021-01-20');

insert into product_book values (product_book_seq.nextval, '학습도서', '투자자산운용사 실제유형 모의고사 4회분 ',
30000, 30000, '유창호','시대고시기획','2021년 01월 05일','study1','신 기출분석 완벽 반영 ',10,'2021-01-20');

insert into product_book values (product_book_seq.nextval, '학습도서', '투자자산운용사 실제유형 모의고사 4회분 ',
30000, 30000, '유창호','시대고시기획','2021년 01월 05일','study1','신 기출분석 완벽 반영 ',10,'2021-01-20');

insert into product_book values (product_book_seq.nextval, '학습도서', '투자자산운용사 실제유형 모의고사 4회분 ',
30000, 30000, '유창호','시대고시기획','2021년 01월 05일','study1','신 기출분석 완벽 반영 ',10,'2021-01-20');

insert into product_book values (product_book_seq.nextval, '학습도서', '투자자산운용사 실제유형 모의고사 4회분 ',
30000, 30000, '유창호','시대고시기획','2021년 01월 05일','study1','신 기출분석 완벽 반영 ',10,'2021-01-20');

insert into product_book values (product_book_seq.nextval, '학습도서', '투자자산운용사 실제유형 모의고사 4회분 ',
30000, 30000, '유창호','시대고시기획','2021년 01월 05일','study1','신 기출분석 완벽 반영 ',10,'2021-01-20');

insert into product_book values (product_book_seq.nextval, '학습도서', '투자자산운용사 실제유형 모의고사 4회분 ',
30000, 30000, '유창호','시대고시기획','2021년 01월 05일','study1','신 기출분석 완벽 반영 ',10,'2021-01-20');

insert into product_book values (product_book_seq.nextval, '학습도서', '투자자산운용사 실제유형 모의고사 4회분 ',
30000, 30000, '유창호','시대고시기획','2021년 01월 05일','study1','신 기출분석 완벽 반영 ',10,'2021-01-20');

insert into product_book values (product_book_seq.nextval, '소설', '투자자산운용사 실제유형 모의고사 4회분 ',
30000, 30000, '유창호','시대고시기획','2021년 01월 05일','novel1','신 기출분석 완벽 반영 ',10,'2021-01-20');

insert into product_book values (product_book_seq.nextval, '만화', '투자자산운용사 실제유형 모의고사 4회분 ',
30000, 30000, '유창호','시대고시기획','2021년 01월 05일','manga1','신 기출분석 완벽 반영 ',10,'2021-01-20');

insert into product_book values (product_book_seq.nextval, '만화', '결제 테스트 ',
1000, 30000, '유창호','시대고시기획','2021년 01월 05일','manga1','신 기출분석 완벽 반영 ',10,'2021-01-20');


select * from product_book;

-- 리뷰 테이블 생성 
--        		review_id  			number(20)		primary key,      	//    리뷰 테이블 no                       
--        		product_id  			number(20)		not null,       	//    상품 테이블 no                       
--             review_mem_id 		varchar2(20) 		not null,        //     회원 아이디      
--             review_write 			varchar2(500) 	not null,        //     리뷰 내용    
--             review_pro_title 	varchar2(100)		not null,            //     상품명  
--             review_star 			number(5)			not null, 		//     별점
--				reg_date DATE DEFAULT SYSDATE							//		작성일
create table review (
             review_id  			number(20)		primary key,                                 
             product_id  			number(20)		not null,                                 
             review_mem_id 		varchar2(20) 		not null,                    
             review_write 			varchar2(500) 	not null,                 
             review_pro_title 	varchar2(100)		not null,                    
             review_star 			number(5)			not null,                                              
             reg_date DATE DEFAULT SYSDATE                                      
); 

--drop table review; 
-- 시퀀스 생성 (도서) 
create sequence review_seq 
start with 1
increment by 1 
nocycle nocache; 

select * from review;

--drop SEQUENCE review_seq;
insert into review values (review_seq.nextval,1, '맴버id0', '리뷰 내용 테스트 ',
'책이름 test1', 2,SYSDATE);
insert into review values (review_seq.nextval,2, '맴버id1', '리뷰 내용 테스트 ',
'책이름 test2', 4,SYSDATE);
insert into review values (review_seq.nextval,3, '맴버id2', '리뷰 내용 테스트 ',
'책이름 test3', 1,SYSDATE);
insert into review values (review_seq.nextval,4, '맴버id3', '리뷰 내용 테스트 ',
'책이름 test4', 3,SYSDATE);
insert into review values (review_seq.nextval,5, '맴버id0', '리뷰 내용 테스트 ',
'책이름 test1', 2,SYSDATE);
insert into review values (review_seq.nextval,6, '맴버id1', '리뷰 내용 테스트 ',
'책이름 test2', 4,SYSDATE);
insert into review values (review_seq.nextval,7, '맴버id2', '리뷰 내용 테스트 ',
'책이름 test3', 1,SYSDATE);
insert into review values (review_seq.nextval,8, '맴버id3', '리뷰 내용 테스트 ',
'책이름 test4', 3,SYSDATE);
insert into review values (review_seq.nextval,9, '맴버id0', '리뷰 내용 테스트 ',
'책이름 test1', 2,SYSDATE);
insert into review values (review_seq.nextval,10, '맴버id1', '리뷰 내용 테스트 ',
'책이름 test2', 4,SYSDATE);
insert into review values (review_seq.nextval,11, '맴버id2', '리뷰 내용 테스트 ',
'책이름 test3', 1,SYSDATE);
insert into review values (review_seq.nextval,12, '맴버id3', '리뷰 내용 테스트 ',
'책이름 test4', 3,SYSDATE);
insert into review values (review_seq.nextval,13, '맴버id0', '리뷰 내용 테스트 ',
'책이름 test1', 2,SYSDATE);
insert into review values (review_seq.nextval,14, '맴버id1', '리뷰 내용 테스트 ',
'책이름 test2', 4,SYSDATE);
insert into review values (review_seq.nextval,15, '맴버id2', '리뷰 내용 테스트 ',
'책이름 test3', 1,SYSDATE);
insert into review values (review_seq.nextval,16, '맴버id3', '리뷰 내용 테스트 ',
'책이름 test4', 3,SYSDATE);
insert into review values (review_seq.nextval,17, '맴버id0', '리뷰 내용 테스트 ',
'책이름 test1', 2,SYSDATE);
insert into review values (review_seq.nextval,18, '맴버id1', '리뷰 내용 테스트 ',
'책이름 test2', 4,SYSDATE);
insert into review values (review_seq.nextval,20, '맴버id2', '리뷰 내용 테스트 ',
'책이름 test3', 1,SYSDATE);
insert into review values (review_seq.nextval,21, '맴버id3', '리뷰 내용 테스트 ',
'책이름 test4', 3,SYSDATE);
insert into review values (review_seq.nextval,22, '맴버id0', '리뷰 내용 테스트 ',
'책이름 test1', 2,SYSDATE);
insert into review values (review_seq.nextval,23, '맴버id1', '리뷰 내용 테스트 ',
'책이름 test2', 4,SYSDATE);
insert into review values (review_seq.nextval,24, '맴버id2', '리뷰 내용 테스트 ',
'책이름 test3', 1,SYSDATE);
insert into review values (review_seq.nextval,25, '맴버id3', '리뷰 내용 테스트 ',
'책이름 test4', 3,SYSDATE);
insert into review values (review_seq.nextval,26, '맴버id0', '리뷰 내용 테스트 ',
'책이름 test1', 2,SYSDATE);
insert into review values (review_seq.nextval,27, '맴버id1', '리뷰 내용 테스트 ',
'책이름 test2', 4,SYSDATE);
insert into review values (review_seq.nextval,28, '맴버id2', '리뷰 내용 테스트 ',
'책이름 test3', 1,SYSDATE);
insert into review values (review_seq.nextval,29, '맴버id3', '리뷰 내용 테스트 ',
'책이름 test4', 3,SYSDATE);
insert into review values (review_seq.nextval,30, '맴버id0', '리뷰 내용 테스트 ',
'책이름 test1', 2,SYSDATE);
insert into review values (review_seq.nextval,31, '맴버id1', '리뷰 내용 테스트 ',
'책이름 test2', 4,SYSDATE);
insert into review values (review_seq.nextval,32, '맴버id2', '리뷰 내용 테스트 ',
'책이름 test3', 1,SYSDATE);
insert into review values (review_seq.nextval,33, '맴버id3', '리뷰 내용 테스트 ',
'책이름 test4', 3,SYSDATE);
insert into review values (review_seq.nextval,34, '맴버id0', '리뷰 내용 테스트 ',
'책이름 test1', 2,SYSDATE);
insert into review values (review_seq.nextval,35, '맴버id1', '리뷰 내용 테스트 ',
'책이름 test2', 4,SYSDATE);
insert into review values (review_seq.nextval,36, '맴버id2', '리뷰 내용 테스트 ',
'책이름 test3', 1,SYSDATE);
insert into review values (review_seq.nextval,37, '맴버id3', '리뷰 내용 테스트 ',
'책이름 test4', 3,SYSDATE);
insert into review values (review_seq.nextval,38, '맴버id0', '리뷰 내용 테스트 ',
'책이름 test1', 2,SYSDATE);
insert into review values (review_seq.nextval,39, '맴버id1', '리뷰 내용 테스트 ',
'책이름 test2', 4,SYSDATE);
insert into review values (review_seq.nextval,40, '맴버id2', '리뷰 내용 테스트 ',
'책이름 test3', 1,SYSDATE);
insert into review values (review_seq.nextval,41, '맴버id3', '리뷰 내용 테스트 ',
'책이름 test4', 3,SYSDATE);
insert into review values (review_seq.nextval,42, '맴버id0', '리뷰 내용 테스트 ',
'책이름 test1', 2,SYSDATE);
insert into review values (review_seq.nextval,43, '맴버id1', '리뷰 내용 테스트 ',
'책이름 test2', 4,SYSDATE);
insert into review values (review_seq.nextval,44, '맴버id2', '리뷰 내용 테스트 ',
'책이름 test3', 1,SYSDATE);
insert into review values (review_seq.nextval,45, '맴버id3', '리뷰 내용 테스트 ',
'책이름 test4', 3,SYSDATE);
insert into review values (review_seq.nextval,18, '맴버id0', '리뷰 내용 테스트 ',
'책이름 test1', 2,SYSDATE);
insert into review values (review_seq.nextval,18, '맴버id1', '리뷰 내용 테스트 ',
'책이름 test2', 4,SYSDATE);
insert into review values (review_seq.nextval,18, '맴버id2', '리뷰 내용 테스트 ',
'책이름 test3', 1,SYSDATE);
insert into review values (review_seq.nextval,18, '맴버id3', '리뷰 내용 테스트 ',
'책이름 test4', 3,SYSDATE);




INSERT INTO product_book_seq 
        (book_id, 
         book_kind, book_title, book_price, 
         book_count, author, publishing_com, publishing_date,book_image,book_content,discount_rate,reg_date)
VALUES
        (book_id.NEXTVAL, 
         'TIGER', 'MANAGER', 7839, 
         SYSDATE, 3000, null, 20);

insert into member values ('aa', 'aa', 'aa맨', '서울 간남규', '02-540-5534', sysdate); 


-- 무통장 입금용 테이블 생성 
create table bank ( 
             account varchar2(30) primary key, 
             bank varchar2(10) not null, name varchar2(12) not null 
); 

-- 카트(장바구니) 테이블 생성 
-- 테이블에 저장하는 곳도 있고 메모리에 넣었다가 지우는 곳도 있다 
create table cart ( 
             cart_id number(10) primary key, 
             buyer varchar2(12) not null, book_id number(10) not null, 
             book_title varchar2(100), buy_price number(10) not null, 
             buy_count number(5) not null, 
             book_image varchar2(16) default 'nothing.jpg' 
); 

-- 구입 테이블 생성 
create table buy ( 
             buy_id number , 
             buyer varchar2(12) not null, book_id varchar2(12), 
             book_title varchar2(100), buy_price number(10) not null, 
             buy_count number(5) not null, 
             book_image varchar2(16) default 'nothing.jpg', 
             buy_date date, 
             account varchar2(50), deliveryName varchar2(12), 
             deliveryTel varchar2(20), deliveryAddress varchar2(80), 
             sanction varchar2(12) default '상품준비중' 
); 

-- 관리자 데이터 생성 
insert into manager values ('master', '1234'); 


-- 회원이 없어서 회원 등록 
-- 이 부분은 나중에 별도로 만들어서 기능 추가해 두어야 한다 
insert into member values ('aa', 'aa', 'aa맨', '서울 간남규', '02-540-5534', sysdate); 
insert into member values ('bb', 'bb', 'bb우먼', '대구 달아구', '053-314-1824', sysdate); 

-- 시퀀스 생성 (도서, 카트) 
create sequence book_seq start with 1 increment by 1 nocycle nocache; 
INSERT INTO product_book (book_id, 
         book_kind, book_title, book_price, 
         book_count, author, publishing_com, publishing_date,book_image,book_content,discount_rate,reg_date)
         VALUES (product_book_seq.NEXTVAL, '베스트셀러', '해커스 토익 기출 보카 TOEIC VOCA',
12900, 30000, 'David Cho   ','세계사','2020년 03월 05일','best10','주제별 연상암기로 토익 영단어 30일 완성!',5,'2021-01-02');


create sequence cart_seq start with 1 increment by 1 nocycle nocache; 

select * from member; 
select * from manager; 
select * from tab; 
select * from book; 


