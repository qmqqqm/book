package product.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import product.controler.dao.ProductSearchDAO;
import product.controler.dto.ProductDTO;

public class ProductSearchService {
		//field
		private ProductSearchDAO productSearchDao = new ProductSearchDAO();
		
		//모든 상품의 모든 정보 조회
		public List<ProductDTO> getProductList(String book_title) throws SQLException {
			System.out.println("서비스 메소드 진입"); 	//확인용
				Connection conn=null;
				try {
				conn = ConnectionProvider_Mj.getConnection();
				List<ProductDTO> productSearch = productSearchDao.getProductSearchList(conn, book_title);
				System.out.println("productSearchDao.getProductList()실행후  가져온 상품의 수productPro.size()="+productSearch);
				System.out.println("getProductList="+productSearch);
				
				return productSearch;
				}finally {
					JdbcUtil_Mj.close(conn);
				}
		}

}
