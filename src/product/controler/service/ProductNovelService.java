package product.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import product.controler.dao.ProductNovelDAO;
import product.controler.dto.ProductDTO;

public class ProductNovelService {
		//field
		private ProductNovelDAO productNovelDao = new ProductNovelDAO();
		
		//모든 상품의 모든 정보 조회
		public List<ProductDTO> getProductList() throws SQLException {
			System.out.println("서비스 메소드 진입"); 	//확인용
				Connection conn=null;
				try {
				conn = ConnectionProvider_Mj.getConnection();
				List<ProductDTO> productNovel = productNovelDao.getProductNovelList(conn);
				System.out.println("productStudyDao.getProductList()실행후  가져온 상품의 수productStudy.size()="+productNovel.size());
				//System.out.println(product);
				
				return productNovel;
			}finally {
				JdbcUtil_Mj.close(conn);
			}
		}

}
