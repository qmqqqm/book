package product.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import product.controler.dao.ProductDetailDAO;
import product.controler.dto.ProductDTO;

public class ProductDetailService {
		//field
		private ProductDetailDAO productDetailDao = new ProductDetailDAO();
		
		//모든 상품의 모든 정보 조회
		public ProductDTO getProductDetailList(int book_id) throws SQLException {
			System.out.println("서비스 메소드 진입"); 	//확인용
				Connection conn=null;
				try {
				conn = ConnectionProvider_Mj.getConnection();
				ProductDTO productDetail = productDetailDao.getProductDetailList(conn,book_id);
				System.out.println("productDetailDao.getProductList()실행후  가져온 상품의 수productDetail.size()="+productDetail);
				//System.out.println(product);
				
				return productDetail;
				}finally {
					JdbcUtil_Mj.close(conn);
				}
			
		}

}
