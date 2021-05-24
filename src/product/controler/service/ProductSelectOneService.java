package product.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import product.controler.dao.ProductSelectOneDAO;
import product.controler.dto.ProductDTO;

public class ProductSelectOneService {
		//field
		private ProductSelectOneDAO productSelectOneDao = new ProductSelectOneDAO();
		
		//모든 상품의 모든 정보 조회
		public ProductDTO getProductList(String book_id) throws SQLException {
			System.out.println("서비스 메소드 진입"); 	//확인용
				Connection conn=null;
				try {
				conn = ConnectionProvider_Mj.getConnection();
				ProductDTO productStudy = productSelectOneDao.getProductSelectOneList(conn, book_id);
				System.out.println("productStudyDao.getProductList()실행후  가져온 상품의 수productStudy.size()="+productStudy);
				//System.out.println(product);
				
				return productStudy;
				}finally {
					JdbcUtil_Mj.close(conn);
				}
		}

}
