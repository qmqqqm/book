package member.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import member.controler.model.UserDTO_Mj;
import member.controler.service.dao.MemberDAO_Mj;
import member.controler.service.dto.MemberDTO_Mj;


//로그인관련 비즈니스 로직을 수행하는 클래스
public class MemberLoginService_Mj {
	//field
	private MemberDAO_Mj memberDao = new MemberDAO_Mj();

	//LoginHandler로부터 받은 id와 비번을 사용하여 작업
	public UserDTO_Mj  login(String id, String pwd) {		
		Connection conn = null;
		try {
			//컨넥션 얻기
			conn = ConnectionProvider_Mj.getConnection();
			//DAO호출후 처리결과를  MemberDTO_Mj 객체로받아 member 변수에저장
			MemberDTO_Mj member = memberDao.selectById(conn, id);	
			//가입된 회원이나 비밀번호가 아니라면 UserDTO_Mj객체에 널반환 
			if(member==null) {
				return new UserDTO_Mj();
			}
			//회원정보가 있다면 UserDTO_Mj객체반환
			return new UserDTO_Mj(member.getName(),member.getId(),member.matchPassword(pwd));
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		
	}
}







