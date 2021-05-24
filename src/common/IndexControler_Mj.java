package common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler_Mj;
import product.controler.dto.ProductDTO;
import product.controler.service.ProductService;

public class IndexControler_Mj implements CommandHandler_Mj {

	//내 코드가져온것
	private ProductService productService = new ProductService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			//1.파라미터받기
			System.out.println("ProductSearchAllControler -process");
				//2.비즈니스로직실행 <->ProductService2<->ProductDAO2<->db
				List<ProductDTO> product_list = productService.getProductList();
				
				//3.모델- session or request
				System.out.println("ProductSearchAllControler -gomain");
				request.setAttribute("product_list",product_list);
				//System.out.println("request.getAttribute()="+request.getAttribute("product_list"));
				
				//4.View
				//return "../main.jsp";
				System.out.println(request.getContextPath()+"/main.jsp");
				return request.getContextPath()+"/main.jsp";
				
		
	}

}
