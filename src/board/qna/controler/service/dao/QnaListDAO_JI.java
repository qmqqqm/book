package board.qna.controler.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import board.qna.controler.model.QnaBoardListWriter_JI;
import board.qna.controler.service.dto.QnaListDTO_JI;
import jdbc.JdbcUtil_Mj;

//QnA table의 data 관련 작업을 위한 클래스이다
public class QnaListDAO_JI {
		//(페이지별로 원하는 특정 범위의)글목록 조회 
		//startRow	: 시작행번호
		//size		: 읽어올 행수(record count)
		public List<QnaListDTO_JI> select(Connection conn, int startRow, int size) throws SQLException {
			
			String sql = "SELECT qna_no, adm_Id, qna_title, "+
						 "qna_content, qna_regdate, qna_modifieddate, qna_readcount, rnum"+
						 " FROM (SELECT ROW_NUMBER() OVER(ORDER BY qna_regdate desc) as rnum, qnaboard_JI.*  FROM qnaboard_JI WHERE isShow=1 ) "+
						 " WHERE rnum BETWEEN ? AND ?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				//쿼리실행-객체준비,실행
				stmt = conn.prepareStatement(sql);
				//?셋팅
				stmt.setInt(1, startRow+1); //시작행번호
				stmt.setInt(2, startRow + size);	  //읽어올 행수(record count)	
				
				rs = stmt.executeQuery();
				
				//다양한 type의  data의 개수가 여러개
				List<QnaListDTO_JI>  result  = new ArrayList<QnaListDTO_JI>();
				 
				while( rs.next() ) {
					//convertArticle() call해서 return받은 Article객체를
					//List컬렉션에 추가(add)한다
					result.add(convertArticle(rs)); //select해서 return받은 record수만큼 반복
				}
				
				return result;
			}finally {//자원반납
				JdbcUtil_Mj.close(rs);
				JdbcUtil_Mj.close(stmt);
			}
		}//select()

		//p647 36라인
		private QnaListDTO_JI convertArticle(ResultSet rs) throws SQLException {
			//참고  rs.getXxx("")
			//Xxx는 java에서 취급할 데이터타입으로
			//클래스타입이 들어 올 수 있다  예) getString() getDate()

			//return new 클래스명(); 하면  특정클래스의 객체를 return하겠다는 의미
			//p647 27라인
			return 
				new QnaListDTO_JI(rs.getInt("qna_no"),
						rs.getString("adm_Id"), 
						rs.getString("qna_title"), 
						rs.getString("qna_content"), 
						rs.getDate("qna_regdate"), 
						rs.getDate("qna_modifieddate"), 
						rs.getInt("qna_readcount"),
						rs.getInt("rnum")
						);
		}//convertArticle()
		
		//전체 게시물수 조회<-DB의 article 테이블의 전체 record수 
		public int selectCount(Connection conn) throws SQLException {
				String sql = "SELECT  COUNT(*) as cnt "+
							 " FROM qnaboard_JI "+
							 " WHERE isShow=1";
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				try {
					//쿼리실행-객체준비,실행
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					
					//qnaboard_JI테이블에 입력된 게시물이 존재한다면 게시물수를 return
					if( rs.next() ) {
						return rs.getInt("cnt"); //rs.getInt(1);와 동일
					}
				
					//qnaboard_JI테이블에 입력된 게시물이 존재하지않으면 0을 return
					return 0;
				
				}finally {//자원반납
					JdbcUtil_Mj.close(rs);
					JdbcUtil_Mj.close(stmt);
				}
		}
		
		//article테이블에서  특정글번호 조회
		public QnaListDTO_JI selectById(Connection conn, int no)  throws SQLException {
			String sql = "SELECT qna_no, adm_Id, qna_title, " +
						 " qna_content, qna_regdate, qna_modifieddate, qna_readcount "+
						 " FROM qnaboard_JI "+
						 " WHERE qna_no = ? "+
		    		  	 " AND isShow=1"; 
			PreparedStatement stmt = null;
			ResultSet rs = null;
					
			try {
				//쿼리실행-객체준비,실행
				stmt = conn.prepareStatement(sql);
				//?셋팅
				stmt.setInt(1, no);
				rs = stmt.executeQuery();
				QnaListDTO_JI article = null;
				if( rs.next() ) {  //select결과가 존재한다면
					article = new QnaListDTO_JI(rs.getInt("qna_no"),
							rs.getString("adm_Id"), 
							rs.getString("qna_title"), 
							rs.getString("qna_content"), 
							rs.getDate("qna_regdate"), 
							rs.getDate("qna_modifieddate"), 
							rs.getInt("qna_readcount")
							);
				}
				return article;
			}finally {//자원반납
				JdbcUtil_Mj.close(rs);
				JdbcUtil_Mj.close(stmt);
			}
		}//selectById
		
		//article테이블에서 특정글번호 조회수증가
		public void incrementReadCount(Connection conn, int no) 
				throws SQLException {
			String sql = null;
			PreparedStatement stmt = null;
			try {
				sql = "UPDATE  qnaboard_JI "+
				      " SET     qna_readcount = qna_readcount+1 "+
				      " WHERE   qna_no = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);	//?세팅		
				stmt.executeUpdate();
			}finally{ //resource 반납
				JdbcUtil_Mj.close(stmt);
			}		
		}//incrementReadCount

		public QnaListDTO_JI insert(Connection conn, QnaListDTO_JI qnaArticleDto) throws SQLException {
				  String sql = null;
			      PreparedStatement pstmt = null; //insert를 싱행하기위한 객체
			      Statement stmt = null; //마지막 insert된 글번호를 조회하기위한 객체
			      ResultSet rs = null; //마지막 insert된 글번호를 담기위한 객체
			      try {
			         sql = "INSERT INTO qnaboard_JI(qna_no,adm_Id,qna_title,qna_content,qna_regdate,qna_modifieddate,qna_readcount ) " + 
			   			   "VALUES(qnaboard_JI_seq.nextval,?,?,?,?,?,0)";
			         
			         
			         pstmt = conn.prepareStatement(sql);
			         pstmt.setString(1,qnaArticleDto.getId());   //?세팅      
			         pstmt.setString(2,qnaArticleDto.getQ_title());   //?세팅      
			         pstmt.setString(3,qnaArticleDto.getQ_content());   //?세팅      
			         pstmt.setTimestamp(4,toTimestamp(qnaArticleDto.getQ_regdate()));
			         pstmt.setTimestamp(5,toTimestamp(qnaArticleDto.getQ_modifieddate()));

			         int insertedCount = pstmt.executeUpdate();
			         if(insertedCount>0) { 
			        	
			        		 //new QnaListDTO_JI (qna_no, adm_Id, qna_title, qna_content, qna_regdate, qna_modifieddate, qna_readcount )
			        		 return new QnaListDTO_JI (
			        				 qnaArticleDto.getQ_No(),
			        				 qnaArticleDto.getId(),
			        				 qnaArticleDto.getQ_title(),
			        				 qnaArticleDto.getQ_content(),
			        				 qnaArticleDto.getQ_regdate(),
			        				 qnaArticleDto.getQ_modifieddate(),
			        				 0,
			        				 qnaArticleDto.getQ_rnum());
			        	 }//if
			         
			         return null;
			      }finally{ //resource 반납
			    	 JdbcUtil_Mj.close(rs);
			    	 JdbcUtil_Mj.close(pstmt);
			    	 JdbcUtil_Mj.close(stmt);
			      }
			}//insert
		
		
		//수정
		public int update(Connection conn,int articleNumber,String title,String content) throws SQLException{
			
				String sql = null;
				PreparedStatement stmt = null;
				try {
					sql = "UPDATE qnaboard_JI " + 
							"SET qna_title=?,qna_content=?, qna_modifieddate=sysdate " + 
							"WHERE qna_no=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1,title);
					stmt.setString(2,content);
					stmt.setInt(3,articleNumber);
					return stmt.executeUpdate();
				}finally {
					JdbcUtil_Mj.close(stmt);
				}
		}//update
		
		
	
		//Date타입을 Timestamp으로 변환하는 함수
		//자바에서 취급하던 Date타입을 
		//db의 DATETIME 타입의 컬럼값으로 사용하기 위함
		private Timestamp toTimestamp(Date regdate) {
			//new Timestamp(long타입) 생성자를 이용해서 객체를 생성
			/*Date객체의    getTime() 는
			  1970년 1월 1일 자정이후의 milliseconds를 long type으로 리턴하는 함수
			  Returns the number of milliseconds 
			  since January 1, 1970, 00:00:00 GMT represented by this Date object.
			  */
			return new Timestamp( regdate.getTime() );
		}
		
		
		//삭제이지만 우리는 update를 이용해서 노출 허용하지 않음
		public int delete(Connection conn, int no) throws SQLException {
			
			String sql = null;
			PreparedStatement stmt = null;
			try {
				sql = "UPDATE qnaboard_JI " + 
					  " SET isShow=0 " + 
					  " WHERE qna_no=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				return stmt.executeUpdate();
				//update(즉 삭제에 해당하는 부분이) 성공되면
				//1를 return 한다
				//실패시 0을 return 한다
			}finally {
				JdbcUtil_Mj.close(stmt);
			}
		}//delete
		
		
}
