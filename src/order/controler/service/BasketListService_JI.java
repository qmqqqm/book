package order.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import order.controler.service.dao.BasketDAO;
import order.controler.service.dto.BasketDTO;

public class BasketListService_JI {
		//field
		private BasketDAO basketDao = new BasketDAO();

		//로그인 완성 후 
		//LoginHandler로부터 받은 id와 비번을 사용하여 작업
		public List<BasketDTO> getBasketList(String id){
			System.out.println("서비스 메소드 진입"); 	//확인용
				Connection conn =null;
				List<BasketDTO> basket = null;
				try {
					conn = ConnectionProvider_Mj.getConnection();
					basket = basketDao.selectById(conn,id);
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					JdbcUtil_Mj.close(conn);
				}
			
				return basket;
	
		}
		
}
