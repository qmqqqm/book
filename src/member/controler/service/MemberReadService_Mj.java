package member.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import member.controler.service.dao.MemberDAO_Mj;
import member.controler.service.dto.MemberDTO_Mj;
import member.error.MemberNotFoundException_Mj;

//회원벙보 조회을 위한 서비스 클래스
public class MemberReadService_Mj {
	private MemberDAO_Mj memberDao = new MemberDAO_Mj();
	public MemberDTO_Mj getMember(String id) {
		Connection conn=null;
		try {
			conn = ConnectionProvider_Mj.getConnection();	
		
		MemberDTO_Mj member = memberDao.selectById(conn, id);
		
		if( member==null ) {
			throw new MemberNotFoundException_Mj();
		}
		
		return member;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil_Mj.close(conn);
		}
	}

}
