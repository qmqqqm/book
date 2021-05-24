package product.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import product.controler.dao.ProductMasterDeleteDAO;

public class ProductMasterDeleteService {
		//field
		private ProductMasterDeleteDAO  productMasterDeleteDAO = new ProductMasterDeleteDAO ();
		
		//모든 상품의 모든 정보 조회
		public void getProductList(String book_id) throws SQLException {
			System.out.println("서비스 메소드 진입"); 	//확인용
				Connection conn=null;
				try {
				conn = ConnectionProvider_Mj.getConnection();
				productMasterDeleteDAO.getProductDelete(conn, book_id);
				System.out.println("productProDao.getProductList()실행후  가져온 상품의 수productPro.size()="+book_id);
				//System.out.println(product);
				}finally {
					JdbcUtil_Mj.close(conn);
				}
		}

}
