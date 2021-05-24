package member.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import member.controler.service.dao.MemberDAO_Mj;
import member.controler.service.dto.MemberDTO_Mj;
import member.error.InvalidPwdException_Mj;
import member.error.MemberNotFoundException_Mj;


//비밀번호 변경요청을 처리하는 서비스 클래스
public class MemberChangePwdService_Mj {

	MemberDAO_Mj memberDao = new MemberDAO_Mj();
	//로그인한 user의 비번을 변경
	public void changePwd(String mId, String curPwd, String newPwd) {
	
		Connection conn = null;
		try {
			conn = ConnectionProvider_Mj.getConnection();
			
			//db 쿼리작업 CUD실패시  auto-commit않도록 설정			
			conn.setAutoCommit(false);
			
			//로그인한 user의 db안의 현재 비번을 조회
			MemberDTO_Mj member = memberDao.selectById(conn, mId);
			
			//회원정보가 없는 경우 에러처리
			if(member==null) {
				throw new MemberNotFoundException_Mj();
			}
			
			//user가 입력한 현재 비번과 일치하지않으면 에러처리
			if(!member.matchPassword(curPwd)) {
				throw new InvalidPwdException_Mj();
			}
			
			member.changePwd(newPwd);
			//로그인한 user의 비번을 변경
			memberDao.changPwd(conn, member);
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil_Mj.close(conn);
		}
		
	}

}






