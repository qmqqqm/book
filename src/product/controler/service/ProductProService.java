package product.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import product.controler.dao.ProductProDAO;
import product.controler.dto.ProductDTO;

public class ProductProService {
		//field
		private ProductProDAO productProDao = new ProductProDAO();
		
		//모든 상품의 모든 정보 조회
		public List<ProductDTO> getProductList() throws SQLException {
			System.out.println("서비스 메소드 진입"); 	//확인용
				Connection conn=null;
				try {
				conn = ConnectionProvider_Mj.getConnection();
				List<ProductDTO> productPro = productProDao.getProductProList(conn);
				System.out.println("productProDao.getProductList()실행후  가져온 상품의 수productPro.size()="+productPro.size());
				//System.out.println(product);
				
				return productPro;
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		}

}
