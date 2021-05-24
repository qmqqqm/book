package admin.controler.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import admin.controler.service.dto.AdminDTO;
import admin.controler.service.dto.Article;
import jdbc.JdbcUtil_Mj;

public class AdminDAO {
	//접근제한자   속성  리턴유형  메서드명(매개변수리스트) {}
	
		//개인회원정보조회
		//로그인	-p592 15라인
		//비번변경을 위해 기존 비밀번호를 조회-p621 21라인
		public  AdminDTO  selectById(Connection conn, String id)
		 throws SQLException {
			
			String sql = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			AdminDTO member = null; //개인 회원의 다양한 정보를 담기위한 객체변수 선언
			try {
				sql = "SELECT  adm_No, adm_Id, adm_Pwd,adm_Hp " + 
					  " FROM   admin_Mj " + 
					  " WHERE  adm_Id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);	//?세팅	
				rs = stmt.executeQuery();
				if(rs.next()) { //특정 회원의 정보가 있으면
					//여기에서는 
					//db에서 조회한 컬럼의 값을   => rs.get데이터타입(String 컬럼명)
					//매개변수를 가진 constructor를 이용하여 MemberDTO에 set
					member = new AdminDTO(rs.getInt("adm_No"), 
											rs.getString("adm_Id"), 
											rs.getString("adm_Pwd"), 
											rs.getString("adm_Hp"));
											//우리는      mBirth 컬럼의 타입이 date이다
											//교재에서는 regdate컬럼의 타입이 datetime이다
										    //toDate(rs.getTimestamp("mBirth")))
											//java의 type은 Date이고
											//jdbc의 type은 timestamp이므로
											//timestamp를 Date로 변환하는 함수를 적용하였다
				}
				return member;
			}catch(NullPointerException e) {
				member.setAdm_Id("1");
				return member;
			}finally{ //p593 32라인
				//resource 반납
				JdbcUtil_Mj.close(rs);
				JdbcUtil_Mj.close(stmt);
			}
		}//selectById()
		
		//Timestamp를 Date로 변환 -p593 38라인
		private Date toDate(Timestamp date) {
		    //삼항연산자          (조건)? true값: false값;
			//매개변수 date가 null이 아니면  date를 사용하여 Date객체생성하여 return
			return 	(date==null)? null : new Date(date.getTime());	
		}

		public int selectCount(Connection conn) throws SQLException {
			String sql = "SELECT  COUNT(*) as cnt "+
					 "FROM admin_Mj";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			//쿼리실행-객체준비,실행
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			//article테이블에 입력된 게시물이 존재한다면 게시물수를 return
			if( rs.next() ) {
				return rs.getInt("cnt"); //rs.getInt(1);와 동일
			}
		
			//article테이블에 입력된 게시물이 존재하지않으면 0을 return
			return 0;
		
		}finally {//자원반납
			JdbcUtil_Mj.close(rs);
			JdbcUtil_Mj.close(stmt);
		}
}//selectCount

		public List<Article> select(Connection conn, int startRow, int size) throws SQLException {
			
			String sql = "SELECT *FROM (SELECT ROWNUM num, A.* " + 
					"          FROM admin_mj a) " + 
					" WHERE NUM BETWEEN ? AND ?";
						 
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				//쿼리실행-객체준비,실행
				stmt = conn.prepareStatement(sql);
				//?셋팅
				stmt.setInt(1, startRow+1); 
				stmt.setInt(2, startRow + size); 
				rs = stmt.executeQuery();
				
				//다양한 type의  data의 개수가 여러개
				List<Article>  result  = new ArrayList<Article>();
				 
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

		private Article convertArticle(ResultSet rs) throws SQLException {
			//참고  rs.getXxx("")
			//Xxx는 java에서 취급할 데이터타입으로
			//클래스타입이 들어 올 수 있다  예) getString() getDate()

			//return new 클래스명(); 하면  특정클래스의 객체를 return하겠다는 의미
			//p647 27라인
			return 
				new Article(rs.getInt("adm_no"),
							rs.getString("adm_id"),
							rs.getString("adm_pwd"), 
							rs.getString("adm_hp"));
		}//convertArticle()

		public Article selectById(Connection conn, int articleNum) throws SQLException {
		      String sql = "SELECT adm_no,adm_id,adm_pwd,adm_hp "+
		                "FROM admin_mj "+
		                "WHERE adm_no=?";
		      PreparedStatement stmt = null;
		      ResultSet rs = null;
		      Article article = null;
		      try {
		         //쿼리실행-객체준비,실행
		         stmt = conn.prepareStatement(sql);
		         //?셋팅
		         stmt.setInt(1,articleNum);
		         rs = stmt.executeQuery();
		         if( rs.next() ) { //select 결과가 존재한다면
		        	 article = convertArticle(rs);
		         }
		      }finally {//자원반납
		    	  JdbcUtil_Mj.close(rs);
		    	  JdbcUtil_Mj.close(stmt);
		      }
			return article;
		   }//selectById

		public int update(Connection conn, int articleNumber, String id, String pwd, String hp) throws SQLException {
			
		      String sql = null;
		      PreparedStatement stmt = null;
		      try {
		         sql = "update admin_mj set adm_id=?,adm_pwd=?,adm_hp=? "
		         		+ "where adm_no =?";
		         stmt = conn.prepareStatement(sql);
		         stmt.setString(1, id);   //?세팅      
		         stmt.setString(2, pwd);   //?세팅      
		         stmt.setString(3, hp);   //?세팅      
		         stmt.setInt(4, articleNumber);   //?세팅      
		         return stmt.executeUpdate();
		         
		      
		      }finally{ //resource 반납
		    	  JdbcUtil_Mj.close(stmt);
		      }
		   }//update

		public  AdminDTO  selectByNo(Connection conn, int no)
				 throws SQLException {
					
					String sql = null;
					PreparedStatement stmt = null;
					ResultSet rs = null;
					AdminDTO member = null; //개인 회원의 다양한 정보를 담기위한 객체변수 선언
					try {
						sql = "SELECT  adm_No, adm_Id, adm_Pwd,adm_Hp " + 
							  " FROM   admin_Mj " + 
							  " WHERE  adm_no=?";
						stmt = conn.prepareStatement(sql);
						stmt.setInt(1, no);	//?세팅	
						rs = stmt.executeQuery();
						if(rs.next()) { //특정 회원의 정보가 있으면
							//여기에서는 
							//db에서 조회한 컬럼의 값을   => rs.get데이터타입(String 컬럼명)
							//매개변수를 가진 constructor를 이용하여 MemberDTO에 set
							member = new AdminDTO(rs.getInt("adm_No"), 
													rs.getString("adm_Id"), 
													rs.getString("adm_Pwd"), 
													rs.getString("adm_Hp"));
													//우리는      mBirth 컬럼의 타입이 date이다
													//교재에서는 regdate컬럼의 타입이 datetime이다
												    //toDate(rs.getTimestamp("mBirth")))
													//java의 type은 Date이고
													//jdbc의 type은 timestamp이므로
													//timestamp를 Date로 변환하는 함수를 적용하였다
						}
					return member;
					}finally{ //p593 32라인
						//resource 반납
						JdbcUtil_Mj.close(rs);
						JdbcUtil_Mj.close(stmt);
					}
				}//selectById()
		
		public int delete(Connection conn,int no) throws SQLException {
		      String sql = null;
		      PreparedStatement stmt = null;
		      try {
		    	  System.out.println("no="+no);
		         sql = "delete  from admin_Mj where adm_no=?";
		         stmt = conn.prepareStatement(sql);
		         stmt.setInt(1, no);   //?세팅   
		         return stmt.executeUpdate();
		         
		      }finally{ //resource 반납
		         JdbcUtil_Mj.close(stmt);
		      }
		   }//   delete
		
	}
