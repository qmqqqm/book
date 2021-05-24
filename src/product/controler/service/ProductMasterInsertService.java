package product.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import product.controler.dao.ProductMasterInsertDAO;
import product.controler.dto.ProductDTO;

public class ProductMasterInsertService {
		//field
		private ProductMasterInsertDAO  productMasterInserDAO = new ProductMasterInsertDAO ();
		
		//모든 상품의 모든 정보 조회
		public ProductDTO getProductList(ProductDTO product_master_insert) throws SQLException {
			System.out.println("서비스 메소드 진입"); 	//확인용
				Connection conn=null;
				try {
				conn = ConnectionProvider_Mj.getConnection();
				ProductDTO productMasterInsert = productMasterInserDAO.getProductInsert(conn, product_master_insert);
				System.out.println("productProDao.getProductList()실행후  가져온 상품의 수productPro.size()="+productMasterInsert);
				//System.out.println(product);
				
				return productMasterInsert;
				}finally {
					JdbcUtil_Mj.close(conn);
				}
		}

}
