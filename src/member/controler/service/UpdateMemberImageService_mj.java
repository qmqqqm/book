package member.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import member.controler.service.dao.MemberDAO_Mj;

public class UpdateMemberImageService_mj {
	MemberDAO_Mj memberDao = new MemberDAO_Mj();
	public void updateimg(String id, String getfile) throws SQLException {
		Connection conn = null;
			try {
				conn = ConnectionProvider_Mj.getConnection();
				memberDao.updateimg(conn,id,getfile);
			
		} finally {
			JdbcUtil_Mj.close(conn);
		}
	

}
}
