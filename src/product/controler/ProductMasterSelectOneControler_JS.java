package product.controler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler_Mj;
import product.controler.dto.ProductDTO;
import product.controler.service.ProductSelectOneService;


//이 클래스 모든 상품의 모든 정보를 가져올 클래스이다
//이 컨트롤러는   /product/searchAll.book 요청이오면  호출되는 담당컨트롤러이다
//비즈니스로직실행 <->ProductService2<->ProductDAO2<->db
public class ProductMasterSelectOneControler_JS implements CommandHandler_Mj {
	
		private ProductSelectOneService productSelectOneService = new ProductSelectOneService();
		
		
		@Override
		public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String book_id= request.getParameter("book_id");
				//1.파라미터받기
			System.out.println("ProductMasterSelectOneControler_JS -process");
				//2.비즈니스로직실행 <->ProductService2<->ProductDAO2<->db
				ProductDTO product_SelectOne_list = productSelectOneService.getProductList(book_id);
				
				//3.모델- session or request
				System.out.println("ProductMasterSelectOneControler_JS -gomain");
				request.setAttribute("product_SelectOne_list",product_SelectOne_list);
				System.out.println("request.getAttribute()="+request.getAttribute("product_SelectOne_list"));
				
				//4.View
				return "../product/product_Update_selectOne_JS.jsp";
			}
}
