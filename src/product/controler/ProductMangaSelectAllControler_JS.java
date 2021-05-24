package product.controler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler_Mj;
import product.controler.dto.ProductDTO;
import product.controler.service.ProductMangaService;


//이 클래스 모든 상품의 모든 정보를 가져올 클래스이다
//이 컨트롤러는   /product/searchAll.book 요청이오면  호출되는 담당컨트롤러이다
//비즈니스로직실행 <->ProductService2<->ProductDAO2<->db
public class ProductMangaSelectAllControler_JS implements CommandHandler_Mj {
	
		private ProductMangaService productMangaService = new ProductMangaService();
		
		@Override
		public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
				//1.파라미터받기
			System.out.println("ProductMangaSelectAllControler_JS -process");
				//2.비즈니스로직실행 <->ProductService2<->ProductDAO2<->db
				List<ProductDTO> product_manga_list = productMangaService.getProductList();
				
				//3.모델- session or request
				System.out.println("ProductMangaSelectAllControler_JS -gomain");
				request.setAttribute("product_manga_list",product_manga_list);
				System.out.println("request.getAttribute()="+request.getAttribute("product_manga_list"));
				
				//4.View
				return "../main.jsp";
			}
}
