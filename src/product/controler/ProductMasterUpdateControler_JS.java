package product.controler;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.command.CommandHandler_Mj;
import product.controler.dto.ProductDTO;
import product.controler.service.ProductMasterUpdateService;


//이 클래스 모든 상품의 모든 정보를 가져올 클래스이다
//이 컨트롤러는   /product/searchAll.book 요청이오면  호출되는 담당컨트롤러이다
//비즈니스로직실행 <->ProductService2<->ProductDAO2<->db
public class ProductMasterUpdateControler_JS implements CommandHandler_Mj {
	
		private ProductMasterUpdateService productMasterUpdateService = new ProductMasterUpdateService();
		
		@Override
		public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			if(request.getMethod().equalsIgnoreCase("GET")) {//글쓰기폼 보여주기 요청(GET방식) 
				return processForm(request, response);
			}else if(request.getMethod().equalsIgnoreCase("POST")) {
				return processSubmit(request, response);
			}else {
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return null;
			}
			}
		
		
		private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			//1.파라미터받기
			System.out.println("ProductMasterUpdateControler_JS -process");
			System.out.println(request.getParameter("book_id"));
			//String str = request.getParameter("book_kind");
		//	System.out.println(str);
				//2.비즈니스로직실행 <->ProductService2<->ProductDAO2<->db
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			//-이미지 저장------------------------------------------------------------------------------------------------------------
			//		     String path="C:/workspace/jsp/test/WebContent/img";
		     String path="C:\\jsp-workspace\\JBKBOOK\\WebContent\\img";
		/* 													C:/workspace/jsp/test/WebContent/img
		 */     int size=1024*1024*10; 
		     String han="utf-8";
		 		//  1. request 객체,  2. 저장될 서버 경로, 3.파일 최대 크기,4. 인코딩 방식, 5. 같은 이름의 파일명 방지 처리
		     MultipartRequest multi=new MultipartRequest(request,path,size,han,new DefaultFileRenamePolicy());
		     
		     System.out.println("multi.getFilesystemName="+multi.getFilesystemName("fname_insert"));
		     String book_id=multi.getParameter("book_id");
		     String book_kind=multi.getParameter("book_kind");
		     String book_title=multi.getParameter("book_title");
		     String book_price=multi.getParameter("book_price");
		     String book_count=multi.getParameter("book_count");
		     String author=multi.getParameter("author");
		     String publishing_com=multi.getParameter("publishing_com");
		     String publishing_date=multi.getParameter("publishing_date");
		     String fname=multi.getFilesystemName("file"); 
		     String book_content=multi.getParameter("book_content");
		     String discount_rate=multi.getParameter("discount_rate");
		     
		     System.out.println(" String fname=multi.getFilesystemName(\"fname_insert\");  후"+multi.getFilesystemName("fname_insert"));
		     //-------------------------------------------------------------------------------------------------------------------------------		     
		     System.out.println("book_id="+book_id);
		System.out.println("book_kind="+book_kind);
		System.out.println("book_title="+book_title);
		System.out.println("book_price="+book_price);
		System.out.println("book_count="+book_count);
		System.out.println("author="+author);
		System.out.println("publishing_com="+publishing_com);
		System.out.println("publishing_date="+publishing_date);
		System.out.println("fname="+fname);
		System.out.println("book_content="+book_content);
		System.out.println("discount_rate="+discount_rate);
		    
			
			ProductDTO product_master_update = new ProductDTO(
																		Integer.parseInt(book_id),
																		book_kind,
																		book_title,
																		Integer.parseInt(book_price),
																		Integer.parseInt(book_count),
																		author,
																		publishing_com,
																		publishing_date,
																		fname,
																		book_content,
																		Integer.parseInt(discount_rate));
		
			System.out.println("나는 서비스에 가는중");
			productMasterUpdateService.getProductList(product_master_update);
				
				 System.out.println("나는 서비스에 갔다왔어");
				
				//3.모델- session or request
				System.out.println("ProductMasterUpdateControler_JS -gomain");
				request.setAttribute("product_master_update",product_master_update);
				System.out.println("request.getAttribute()="+request.getAttribute("product_master_update"));
				
				//4.View
				return "/product/selectAllMaster.book";
		}
		
		private String processForm(HttpServletRequest request, HttpServletResponse response) {
			String processform = request.getParameter("book_id");
			request.setAttribute("book_id", processform);
			return "../product/product_Update_JS.jsp";
		}
}
