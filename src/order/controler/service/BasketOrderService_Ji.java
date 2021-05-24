package order.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import order.controler.service.dao.BasketDAO;
import order.controler.service.dto.BasketDTO;

public class BasketOrderService_Ji {

	private BasketDAO basketDao = new BasketDAO();
	
	public int order(String id, int[] no) {
		Connection conn =null;
		int add = 0;
		int addDelete = 0;
		
		try {
			conn = ConnectionProvider_Mj.getConnection();
			conn.setAutoCommit(false); //자동commit x

			add = basketDao.addOrder(conn, id, no);
			addDelete = basketDao.addDelete(conn, id, no);

			conn.commit(); //transaction commit
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		return add+addDelete;
	}
}
/*
 * public int order(String id, int no) { Connection conn; int add = 0; int
 * addDelete = 0;
 * 
 * try { conn = ConnectionProvider_Mj.getConnection();
 * conn.setAutoCommit(false); //자동commit x
 * 
 * add = basketDao.addOrder(conn, id, no); addDelete = basketDao.addDelete(conn,
 * id, no);
 * 
 * conn.commit(); //transaction commit
 * 
 * } catch (SQLException e) { e.printStackTrace(); } return add+addDelete; } }
 */
