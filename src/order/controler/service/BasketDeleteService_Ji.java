package order.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import order.controler.service.dao.BasketDAO;

public class BasketDeleteService_Ji {

	private BasketDAO basketDao = new BasketDAO();
	
	
	public int deletecart(String id, int p_no) {
		Connection conn=null;
		int delete = 0;
		try {
			conn = ConnectionProvider_Mj.getConnection();
			delete = basketDao.delete(conn, id, p_no);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		return delete;
	}

}
