package order.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import order.controler.service.dao.BasketDAO;

//장바구니 전체삭제 서비스
public class BasketAllDeleteService_Ji {
	
	private BasketDAO basketDao = new BasketDAO();
	
	public int allDeletecart(String id) {
		Connection conn = null;
		int allDelete = 0;
		try {
			conn = ConnectionProvider_Mj.getConnection();
			allDelete = basketDao.allDelete(conn, id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		return allDelete;
	}
}
