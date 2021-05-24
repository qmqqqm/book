--데이터베이스 컨넥션 설정방법
--Oracle11 우클릭 Properties 선택
--DriverProperties 선택
--Host에 jbkbook.iptime.org 입력
--User name 에 jbk 입력
--PassWord 에 jbk 입력
--아래는 샘 플 데이터 참고해 주세요
--회원table 삭제
DROP TABLE member_Mj;

--회원table 생성
CREATE TABLE member_Mj(
	mem_No				number(6)  		constraint MEMBER_mNO_pk  	primary key,
	mem_Name  		varchar2(60)  constraint MEMBER_mNAME_nn  not null,
	mem_Id  			varchar2(100) constraint MEMBER_mID_nn 		not null,
	mem_Pwd 		varchar2(15)  constraint MEMBER_mPWD_nn  	not null,
	mem_Nickname varchar2(60),
	mem_Email  	varchar2(80),
	mem_Birth		date,
	 unique(mID) constraint MEMBER_MID_UK
);

--sequence 삭제
DROP   SEQUENCE mem_Seq_Mj;
--sequence 생성
CREATE SEQUENCE mem_Seq_Mj;

--sample data입력
INSERT INTO member_Mj(mNO,mNAME,mID,mPWD,mNICKNAME,mEMAIL,mBirth)
VALUES(mem_Seq_Mj.nextval,'홍GD','hongid','123','hong닉','hh@test.com','1990/01/29');
INSERT INTO member_Mj(mNO,mNAME,mID,mPWD,mNICKNAME,mEMAIL,mBirth)
VALUES(mem_Seq_Mj.nextval,'이sh','leeid','123','장군','lee@test.com','1985/03/20');
INSERT INTO member_Mj(mNO,mNAME,mID,mPWD,mNICKNAME,mEMAIL,mBirth)
VALUES(mem_Seq_Mj.nextval,'sejong대왕','sid','123','킹왕짱','ss@test.com','1980/05/15');
INSERT INTO member_Mj(mNO,mNAME,mID,mPWD,mNICKNAME,mEMAIL,mBirth)
VALUES(mem_Seq_Mj.nextval,'김구','kimid','123','지킴이','k99@test.com','1975/07/01');

commit;

-- 회원목록 조회
SELECT * FROM member_Mj;

-- 특정회원 정보 조회
SELECT * 
FROM member_Mj
WHERE mem_No=?;

-- 회원정보 수정
UPDATE member_Mj
SET    mem_Name=?,
			 mem_Pwd=?,
			 mem_Nickname=?,
			 mem_Email=?,
			 mem_Birth=?
WHERE men_NO=?;

-- 특정 회원 삭제
DELETE FROM member_Mj
WHERE mem_No=?;

select * from noticeboard_jg;






