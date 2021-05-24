package board.free.controler.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import board.free.controler.model.FreeBoardListWriter_Mj;
import board.free.controler.service.dto.FreeBoardDTO_Mj;
import jdbc.JdbcUtil_Mj;



//p647
//이 클래스는 article테이블의 data 관련 작업을 위한 클래스이다
public class FreeBoardDAO_Mj {
		
	//시작행번호와 읽어올행의 수에 맞게 글목록 조회 
	//startRow	: 시작행번호
	//size		: 읽어올 행수(record count)
	public List<FreeBoardDTO_Mj> select(Connection conn, int startRow, int size) throws SQLException {
		
		String sql = "select free_No,mem_Name,mem_Id , title, " + 
				"	free_regDate ,free_modifiedDate ,free_ReadCount,free_Content " + 
				"	from freeBoard_Mj_view" + 
				"	where No between ? and ? and isshow=1";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			//쿼리실행-객체준비,실행
			stmt = conn.prepareStatement(sql);
			//?셋팅
			stmt.setInt(1, startRow+1); //시작행번호
			
			stmt.setInt(2, startRow+size);
				  //읽어올 행수(record count)	
			
			rs = stmt.executeQuery();
			
			//어레이 리스트에 DTO객체저장후 result 변수에저장
			List<FreeBoardDTO_Mj>  result  = new ArrayList<FreeBoardDTO_Mj>();
			 
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

	//Dto에 값설정 메소드
	private FreeBoardDTO_Mj convertArticle(ResultSet rs) throws SQLException {	

		return 
			new FreeBoardDTO_Mj(rs.getInt("free_No"),
					//Writer객체를 넣어주겠다
					new FreeBoardListWriter_Mj( rs.getString("mem_Id"), rs.getString("mem_Name")), 
					rs.getString("TITLE"),
					rs.getDate("free_regDate"), 
					rs.getDate("free_modifiedDate"), 
					rs.getInt("free_ReadCount"),
					rs.getString("free_Content")
					);
		
	}

	
	//전체 게시물수 조회 메소드
	public int selectCount(Connection conn) throws SQLException {
			String sql = "select count(*) as cnt from freeBoard_Mj WHERE isshow=1";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				//쿼리실행-객체준비,실행
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				
				//프리보드 테이블에 입력된 게시물이 존재한다면 게시물수를 return
				if( rs.next() ) {
					return rs.getInt("cnt");
				}
			
				//프리보드 테이블에 입력된 게시물이 존재하지않으면 0을 return
				return 0;
			
			}finally {//자원반납
				JdbcUtil_Mj.close(rs);
				JdbcUtil_Mj.close(stmt);
			}
	}
	//자유게시판 글번호로 상세조회 메소드
	public FreeBoardDTO_Mj selectById(Connection conn, int no)  throws SQLException {
		String sql = "SELECT free_No, mem_Id, mem_Name, " + 
				     "       title, free_regDate, free_modifiedDate,free_ReadCount,free_Content  "+
					 " FROM freeBoard_Mj "+
					 " WHERE free_No = ? AND  isshow=1";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		System.out.println("dao셀렉트 아이디 진입 no"+no);
		try {
			//쿼리실행-객체준비,실행
			stmt = conn.prepareStatement(sql);
			//?셋팅
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			FreeBoardDTO_Mj article = null;
			if( rs.next() ) {  //select결과가 존재한다면
				article = convertArticle(rs);
				System.out.println("컨버터후"+article);
			}
			return article;
		}finally {//자원반납
			JdbcUtil_Mj.close(rs);
			JdbcUtil_Mj.close(stmt);
		}
	}

//article테이블에서 특정글번호 조회수증가-p656 20라인
	public void incrementReadCount(Connection conn, int no) 
			throws SQLException {
		String sql = null;
		PreparedStatement stmt = null;
		System.out.println("dao 리드 카운트 진입");
		try {
			sql = "UPDATE  freeBoard_Mj "+
			      "SET     free_ReadCount = free_ReadCount+1 "+
			      "WHERE   free_No = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);	//?세팅		
			stmt.executeUpdate();
		}finally{ //resource 반납
			JdbcUtil_Mj.close(stmt);
		}	
	}
	//자유게시판 글쓰기 처리
	public FreeBoardDTO_Mj insert(Connection conn, FreeBoardDTO_Mj article) 
			throws SQLException {
		System.out.println("dao진입");
				String sql = null;
				PreparedStatement pstmt = null;//insert를 실행하기위한 객체				
				ResultSet rs = null;   
				try {
					sql 
					= "INSERT INTO freeBoard_Mj(free_No,mem_Name,mem_Id,title,free_regDate,free_modifiedDate,free_ReadCount,free_Content) " + 
					  "VALUES(freeBoard_Mj_seq.nextval,?,?,?,?,?,0,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,article.getWriter().getName());
					pstmt.setString(2,article.getWriter().getId());		
					pstmt.setString(3,article.getTitle());
					pstmt.setTimestamp(4,toTimestamp(article.getRegdate()));
					pstmt.setTimestamp(5,toTimestamp(article.getModifiedDate()));
					pstmt.setString(6,article.getContent());					
					//입력성공한 횟수를 insertedCount 에저장
					int insertedCount = pstmt.executeUpdate();
					if(insertedCount>0) { 							
							 //입력성공시 FreeBoardDTO_Mj 객체 리턴
							return  new FreeBoardDTO_Mj( article.getNumber(),article.getWriter(),
												article.getTitle(), 
												article.getRegdate(), 
												article.getModifiedDate(), 0,article.getContent());
					
					}
					
					return null;
				}finally{ //resource 반납
					JdbcUtil_Mj.close(rs);
					
					JdbcUtil_Mj.close(pstmt);
				}
		}

		
		//Date 타입을  Timestamp 타입으로 변환
		private Timestamp toTimestamp(Date regdate) {			
			return new Timestamp( regdate.getTime() );
		}
		//자유게시판 글수정 메소드
		public int update(Connection conn,
				int articleNumber,String title,String content) throws SQLException {
			
			String sql = null;
			PreparedStatement stmt = null;
			try {
				sql = "UPDATE freeBoard_Mj  "+
				      "SET 	  title=?, free_modifiedDate=sysdate , free_Content=? "+
					  "WHERE  free_No=?";		
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, title);	//?세팅
				stmt.setString(2, content);	//?세팅
				stmt.setInt(3, articleNumber);
				return stmt.executeUpdate();
			}finally{ //resource 반납
				JdbcUtil_Mj.close(stmt);
			}
		}//update

		//자유게시판 글삭제 실행 메소드 
		public int delete(Connection conn, int no) throws SQLException {
			String sql = null;
			PreparedStatement stmt = null;
			try {
				sql = "UPDATE freeBoard_Mj "+
				      "SET 	  isShow=0 "+
				      "WHERE  free_No=?";			
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1,no);	//?세팅		
				return stmt.executeUpdate();
				
			}finally{ //resource 반납
				JdbcUtil_Mj.close(stmt);
			}
		
			
		}

	
		
		
	}



	




