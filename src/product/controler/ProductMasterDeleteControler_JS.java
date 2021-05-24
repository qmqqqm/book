package product.controler;


import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler_Mj;
import product.controler.service.ProductMasterDeleteService;


//이 클래스 모든 상품의 모든 정보를 가져올 클래스이다
//이 컨트롤러는   /product/searchAll.book 요청이오면  호출되는 담당컨트롤러이다
//비즈니스로직실행 <->ProductService2<->ProductDAO2<->db
public class ProductMasterDeleteControler_JS implements CommandHandler_Mj {
	
		private ProductMasterDeleteService productMasterDeleteService = new ProductMasterDeleteService();
		
		@Override
		public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			if(request.getMethod().equalsIgnoreCase("GET")) {//글쓰기폼 보여주기 요청(GET방식) 
				System.out.println("process GET");
				String processform = request.getParameter("book_id");
				request.setAttribute("book_id", processform);
				return processSubmit(request, response);
//				return processForm(request, response);
			}else if(request.getMethod().equalsIgnoreCase("POST")) {
				System.out.println("process POST");
				String processform = request.getParameter("book_id");
				request.setAttribute("book_id", processform);
				return processSubmit(request, response);
			}else {
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return null;
			}
			}
		
		
		private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException {
			//1.파라미터받기
			System.out.println("ProductMasterDeleteControler_JS -process");
			System.out.println(request.getParameter("book_id"));
			
			String book_id = request.getParameter("book_id");
		//	System.out.println(str);
				//2.비즈니스로직실행 <->ProductService2<->ProductDAO2<->db
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			System.out.println("나는 서비스에 가는중");
			productMasterDeleteService.getProductList(book_id);
				
				 System.out.println("나는 서비스에 갔다왔어");
				
				//3.모델- session or request
				System.out.println("ProductMasterDeleteControler_JS -gomain");
				
				//4.View
				return "/product/selectAllMaster.book";
		}
		
}
