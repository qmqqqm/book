package member.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import member.controler.service.dao.MemberDAO_Mj;
import member.controler.service.dto.MemberDTO_Mj;
import member.error.DuplicateIdException_Mj;

//회원가입 처리를 진행하는 서비스 클래스
public class MemberJoinService_Mj {

	private MemberDAO_Mj memberDao = new MemberDAO_Mj();
	
	public void join(MemberJoinRequestService_Mj joinReq) {
		
		Connection conn = null;
		try {
			conn = ConnectionProvider_Mj.getConnection();
			conn.setAutoCommit(false);
			//입력받은 아이디로 회원정보조회후 결과를 MemberDTO_Mj 객체에 저장
			MemberDTO_Mj member = memberDao.selectById(conn, joinReq.getId());
			//가입된 회원이 있으면 에러처리
			if (member != null) {
				JdbcUtil_Mj.rollback(conn);
				throw new DuplicateIdException_Mj();
			}
			
			//회원가입처리를 위해 DAO호출
			memberDao.join(conn, 
					new MemberDTO_Mj(							
							joinReq.getName(),
							joinReq.getPwd(),
							joinReq.getId(), 
							joinReq.getBirth(), 
							joinReq.getGender(), 
							joinReq.getHp()));
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil_Mj.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil_Mj.close(conn);
		}
	}
}
