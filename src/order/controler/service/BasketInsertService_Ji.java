package order.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import order.controler.service.dao.BasketDAO;
import order.controler.service.dto.BasketDTO;

public class BasketInsertService_Ji {
	private BasketDAO basketDao = new BasketDAO();

	//로그인 완성 후 
	//LoginHandler로부터 받은 id와 비번을 사용하여 작업
	//구매하기 버튼을 누르면 로그인한 id 제품번호 수량을 가져와 장바구니 디비에 입력및 업데이트 하는 컨트롤러
	public int addBasket(String id, int no, int amount){
		System.out.println("서비스 진입");
		Connection conn=null;
		int result = 0;
		try {
			conn = ConnectionProvider_Mj.getConnection();
			result = basketDao.addBasket(conn, id, no, amount);
			System.out.println(result); //확인용
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		 return result;
	}
	
	
	

}
