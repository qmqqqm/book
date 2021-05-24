package mjadd.controler.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.JdbcUtil_Mj;
import mjadd.controler.service.dto.ProSearchDTO_Mj;

public class ProSearchDAO_Mj {
	//select book_id, book_kind,book_title, book_price,book_count,
	//author,publishing_com,publishing_date,book_image,book_content,discount_rate,reg_date 
	//from product_book;
	//사용자가입력한 검색어로 데이터베이스내 상품정보을 검색하는 쿼리
	public List<ProSearchDTO_Mj> proSearch(Connection conn, String search) throws SQLException {		
						
			String sql = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;		
			try {
				sql = "select book_id, book_kind,book_title, book_price,book_count, "+
						" author,publishing_com,publishing_date,book_image, "+
						" book_content,discount_rate,reg_date "+
						" from product_book "+
						"	where book_content like ? or book_title like ?  or author like ? or publishing_com like ?";

				stmt = conn.prepareStatement(sql);
			
				stmt.setString(1,"%"+search+"%");	//?세팅	
				stmt.setString(2,"%"+search+"%");	//?세팅	
				stmt.setString(3,"%"+search+"%");	//?세팅	
				stmt.setString(4,"%"+search+"%");	//?세팅	
			 			
				rs = stmt.executeQuery();
				List<ProSearchDTO_Mj>  result  = new ArrayList<ProSearchDTO_Mj>();
				
				while( rs.next() ) {
					//convertSearch() call해서 return받은 ProSearchDTO_Mj객체를
					//List컬렉션에 추가(add)한다
					result.add(convertSearch(rs)); //select해서 return받은 record수만큼 반복
				}
				
				return result;
				}finally{ 
					
					JdbcUtil_Mj.close(rs);
					JdbcUtil_Mj.close(stmt);
				}
				}
				
				
				private ProSearchDTO_Mj convertSearch(ResultSet rs) throws SQLException {					
					
					//select book_id, book_kind,book_title, book_price,book_count,
					//author,publishing_com,publishing_date,book_image,book_content,discount_rate,reg_date 
					//from product_book;
					
					return 
						new ProSearchDTO_Mj(
									rs.getInt("book_id"), 
									rs.getString("book_kind"), 
									rs.getString("book_title"), 
									rs.getInt("book_price"), 
									rs.getInt("book_count"),
									rs.getString("author"),
									rs.getString("publishing_com"),
									rs.getString("publishing_date"),									
									rs.getString("book_image"),
									rs.getString("book_content"),
									rs.getInt("discount_rate"),
									rs.getDate("reg_date"));
									
					
				}

				//id를 받아 상품상세정보을 조회하는 쿼리문
				public ProSearchDTO_Mj selectID(Connection conn, int bookid) throws SQLException {
					System.out.println("디에이오 진입 받은 파라미터search="+bookid);			
					String sql = null;
					PreparedStatement stmt = null;
					ResultSet rs = null;		
					try {
						sql = "select book_id, book_kind,book_title, book_price,book_count, "+
								" author,publishing_com,publishing_date,book_image, "+
								" book_content,discount_rate,reg_date "+
								" from product_book "+
								"	where book_id= ?";

						stmt = conn.prepareStatement(sql);
					
						stmt.setInt(1,bookid);	
						
					 			
						rs = stmt.executeQuery();
						ProSearchDTO_Mj  result  = new ProSearchDTO_Mj();
						
						if( rs.next() ) {
							//convertSearch() call해서 return받은 ProSearchDTO_Mj객체를 돌려준다
							
							result=convertSearch(rs); 						}						
						
						return result;
						}finally{ 
							JdbcUtil_Mj.close(rs);
							JdbcUtil_Mj.close(stmt);
						}
						}
						
		
		
 	}


