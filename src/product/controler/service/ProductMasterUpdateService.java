package product.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import product.controler.dao.ProductMasterUpdateDAO;
import product.controler.dto.ProductDTO;

public class ProductMasterUpdateService {
		//field
		private ProductMasterUpdateDAO  productMasterUpdateDAO = new ProductMasterUpdateDAO ();
		
		//모든 상품의 모든 정보 조회
		public ProductDTO getProductList(ProductDTO product_master_update) throws SQLException {
			System.out.println("서비스 메소드 진입"); 	//확인용
				Connection conn=null;
				try {
				conn = ConnectionProvider_Mj.getConnection();
				productMasterUpdateDAO.getProductUpdate(conn, product_master_update);
				System.out.println("productProDao.getProductList()실행후  가져온 상품의 수productPro.size()="+product_master_update);
				//System.out.println(product);
				
				return product_master_update;
				}finally {
					JdbcUtil_Mj.close(conn);
				}
		}

}
