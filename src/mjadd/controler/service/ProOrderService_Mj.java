package mjadd.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import order.controler.service.dao.BasketDAO;
import order.controler.service.dto.BasketDTO;

public class ProOrderService_Mj {

	private BasketDAO basketDao = new BasketDAO();
	
	public int order(String id, int Book_id,int count) {
		Connection conn=null;
		int add = 0;		
		
		try {
			conn = ConnectionProvider_Mj.getConnection();
			conn.setAutoCommit(false); //자동commit x

			add = basketDao.addOrder(conn, id, Book_id,count);
			System.out.println("상품주문 서비스 받은값");

			conn.commit(); //transaction commit
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		return add;
	}
}

