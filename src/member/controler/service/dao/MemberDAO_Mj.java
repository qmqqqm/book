package member.controler.service.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import jdbc.JdbcUtil_Mj;
import member.controler.service.dto.MemberDTO_Mj;
//이 클래스는 오라클 Member table에 access하여 CRUD작업을 할 수 있는 기능을 제공

public class MemberDAO_Mj {	
	
	//회원가입시 id사용가능여부 확인
	public boolean overlappedID(Connection conn, String id)
			 throws SQLException {
		
		boolean result = false;
		String sql = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			//Oracle
			//DECODE함수는 오직 동등비교(==)
			//DECODE(컬럼,값,'조건이 일치할 경우의 VALUE','조건이 일치하지 않을경우의 VALUE')			
			sql = "SELECT decode(count(*),1,'true','false') as result "+
				    "FROM   member_Mj "+
				    "WHERE  mem_Id=?";			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				//result 컬럼의 값을 가져와 Boolean으로 변환
				result = Boolean.parseBoolean(rs.getString("result"));
			}

			return result;
		}finally{ 
			//resource 반납
			JdbcUtil_Mj.close(rs);
			JdbcUtil_Mj.close(stmt);
		}
	}//overlappedID
	//입력받은 아이디와 일치하는 데이터 추출
	public  MemberDTO_Mj  selectById(Connection conn, String id)
	 throws SQLException {
		
		String sql = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		MemberDTO_Mj member = null; //개인 회원의 다양한 정보를 담기위한 객체변수 선언
		try {
			sql = "SELECT  mem_No,mem_image,mem_Id, mem_Pwd,mem_Name," + 
			              "mem_Birth,mem_Gender,mem_Hp, " + 
					" mem_Point,mem_Grade"+
				  " FROM   member_Mj " + 
				  " WHERE  mem_Id=? and mem_Grade=0";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);	//?세팅	
			rs = stmt.executeQuery();
			if(rs.next()) { //특정 회원의 정보가 있으면
				//db에서 조회한 컬럼의 값을  
				//매개변수를 가진 constructor를 이용하여 MemberDTO_Mj에 set
				member = new MemberDTO_Mj(rs.getInt("mem_No"),
										rs.getString("mem_image"), 
										rs.getString("mem_Id"), 
										rs.getString("mem_Pwd"), 
										rs.getString("mem_Name"), 
										rs.getString("mem_Birth"), 
										rs.getString("mem_Gender"), 
										rs.getString("mem_Hp"),
										rs.getInt("mem_Point"),
										rs.getInt("mem_Grade"));										
			}			
		return member;
		}finally{ 
			//resource 반납
			JdbcUtil_Mj.close(rs);
			JdbcUtil_Mj.close(stmt);
		}
	}
	
	//비밀번호 변경
	public void changPwd(Connection conn, MemberDTO_Mj member) {
		String sql = null;
		PreparedStatement stmt = null;
	
		try {
			sql = "UPDATE MEMBER" + 
					" SET mem_Pwd=?" + 
					" WHERE mem_Id=? and mem_Grade=0";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getPwd());	//?세팅	
			stmt.setString(2, member.getId());	//?세팅	
			stmt.executeUpdate();
						
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{ 
			//resource 반납
			JdbcUtil_Mj.close(stmt);
		}
				
	}
	
	//회원가입
	public void join(Connection conn, MemberDTO_Mj mem) throws SQLException {
		String sql = null;
		PreparedStatement pstmt = null;	
		try {
		sql="insert into member_Mj values(member_Mj_seq.nextval,?,?,?,?,?,?,0,0,'')";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1,mem.getId()); 
			pstmt.setString(2,mem.getPwd()); 
			pstmt.setString(3,mem.getName()); 
			pstmt.setString(4, mem.getBirth()); 
			pstmt.setString(5,mem.getGender()); 
			pstmt.setString(6,mem.getHp());
			pstmt.executeUpdate();
		}finally{ //resource 반납
			JdbcUtil_Mj.close(pstmt);
		}
	}
	
	//회원정보수정
	public int update(Connection conn, MemberDTO_Mj memReq) throws SQLException {
		String sql = null;
		PreparedStatement pstmt = null;			
		try {
		sql="update member_Mj set mem_Name = ?, mem_Birth=?, mem_Gender=?, mem_Hp=? where mem_Id = ? and mem_Grade=0";
		pstmt = conn.prepareStatement(sql);			 
		pstmt.setString(1,memReq.getName()); 			
		pstmt.setString(2,memReq.getBirth()); 
		pstmt.setString(3,memReq.getGender()); 
		pstmt.setString(4,memReq.getHp());
		pstmt.setString(5,memReq.getId()); 
		int count=pstmt.executeUpdate();
		
		return count;
	}finally{ //resource 반납
		JdbcUtil_Mj.close(pstmt);
	}
	}
	
		//자바 Date타입을 디비의 Timestamp으로 변환하는 함수	
		private Timestamp toTimestamp(java.util.Date date) {
		
			return new Timestamp( date.getTime() );
		}
		//회원 탈퇴
		public int delete(Connection conn, String id) throws SQLException {
			String sql = null;
			PreparedStatement stmt = null;
			try {
				sql = "UPDATE member_Mj "+
				      "SET 	  mem_Grade=2 "+
				      "WHERE  mem_Id=?";			
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,id);	//?세팅		
				return stmt.executeUpdate();
				//update(즉 삭제에 해당하는 부분이) 성공되면
				//1를 return 한다
				//실패시 0을 return 한다
			}finally{ //resource 반납
				JdbcUtil_Mj.close(stmt);
			}
		
			
		}
		//아이디찾기
		public String getId(Connection conn, String name, String hp, String birth) throws SQLException {
			String sql = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String id=null;
			
			try {
				sql = "SELECT  mem_Id "+
					  " FROM   member_Mj " + 
					  " WHERE  mem_Name=? and mem_Birth=? and mem_Hp=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, name);	//?세팅	
				stmt.setString(2, birth);	//?세팅	
				stmt.setString(3, hp);	//?세팅	
				rs = stmt.executeQuery();
				if(rs.next()) { //특정 회원의 정보가 있으면
					//db에서 조회한id 컬럼의 값을 리턴  
					
					id=rs.getString("mem_Id");	
					
				}			
				return id;
			}finally{ //resource 반납
				JdbcUtil_Mj.close(stmt);
			}
			
			
		}
		//비번찾기
		public String getPwd(Connection conn, String id, String hp, String birth) throws SQLException {
			String sql = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String pwd=null;
			try {
			
				sql = "SELECT  mem_Pwd "+
					  " FROM   member_Mj " + 
					  " WHERE  mem_Id=? and mem_Birth=? and mem_Hp=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);	//?세팅	
				stmt.setString(2, birth);	//?세팅	
				stmt.setString(3, hp);	//?세팅	
				rs = stmt.executeQuery();
				if(rs.next()) { //특정 회원의 정보가 있으면
					//db에서 조회한id 컬럼의 값을 리턴  
					
					pwd=rs.getString("mem_Pwd");	
					
				}			
				return pwd;
		}finally{ //resource 반납
			JdbcUtil_Mj.close(rs);
			JdbcUtil_Mj.close(stmt);
		}
		}
		//이미지 업데이트
		public void updateimg(Connection conn,String id, String getfile) throws SQLException {
			String sql = null;
			PreparedStatement pstmt = null;			
			try {
			sql="update member_Mj set mem_Image = ? where mem_Id = ? and mem_Grade=0";
			pstmt = conn.prepareStatement(sql);			 
			pstmt.setString(1,getfile); 			
			pstmt.setString(2,id); 			
			pstmt.executeUpdate();
			
			
		}finally{ //resource 반납
			JdbcUtil_Mj.close(pstmt);
		}
	}
			
}

		






