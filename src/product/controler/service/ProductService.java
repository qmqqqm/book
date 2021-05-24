package product.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import product.controler.dao.ProductDAO;
import product.controler.dto.ProductDTO;

public class ProductService {
		//field
		private ProductDAO productDao = new ProductDAO();
		
		//모든 상품의 모든 정보 조회
		public List<ProductDTO> getProductList() throws SQLException {
			System.out.println("서비스 메소드 진입"); 	//확인용
				Connection conn=null;
				try {
				conn = ConnectionProvider_Mj.getConnection();
				List<ProductDTO> product = productDao.getProductList(conn);
				System.out.println("productDao.getProductList()실행후  가져온 상품의 수product.size()="+product.size());
				//System.out.println(product);
				
				return product;
				}finally {
					JdbcUtil_Mj.close(conn);
				}
		}

}
