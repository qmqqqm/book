package member.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import member.controler.service.dao.MemberOrderDAO_Mj;
import member.controler.service.dto.MemberOrderDTO_Mj;

public class MemberOrderService_Mj {
	//field
	private MemberOrderDAO_Mj basketDao = new MemberOrderDAO_Mj();
	
	
	
	//오더 컨트롤러에서받은 id을 사용하여 작업
	public List<MemberOrderDTO_Mj> getOrderList(String id) throws SQLException {
	
		Connection conn=null;
		try {		
			conn = ConnectionProvider_Mj.getConnection();
			//오더정보 조회을 위해 DAO호출
			List<MemberOrderDTO_Mj> order = basketDao.selectById(conn,id);
		
			return order;
				
		}finally {
			JdbcUtil_Mj.close(conn);
		}
	}
}
