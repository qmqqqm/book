package product.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler_Mj;
import product.controler.dto.ProductDTO;
import product.controler.service.ProductDetailService;
import product.review.dto.ReviewArticle;
import product.review.service.ReviewService;

//이 클래스 모든 상품의 모든 정보를 가져올 클래스이다
//이 컨트롤러는   /product/searchAll.book 요청이오면  호출되는 담당컨트롤러이다
//비즈니스로직실행 <->ProductService2<->ProductDAO2<->db
public class ProductDetailControler_JS implements CommandHandler_Mj {

	private ProductDetailService productDetailService = new ProductDetailService();
	private ReviewService reviewService = new ReviewService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1.파라미터받기
		System.out.println("ProductDetailControler_JS -process");
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		String pageNoVal = request.getParameter("pageNo");
		System.out.println("book_id="+book_id);
		System.out.println("pageNo="+pageNoVal);

		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		
		// 2.비즈니스로직실행 <->ProductService2<->ProductDAO2<->db
		ProductDTO product_Detail_list = productDetailService.getProductDetailList(book_id);
		System.out.println("product_Detail_list");
		ReviewArticle review_list = reviewService.getReviewList(book_id,pageNo);
		System.out.println("getReviewList");
		// 3.모델- session or request
		System.out.println("ProductDetailControler_JS -gomain");
		request.setAttribute("ProductDetailService", product_Detail_list);
		System.out.println(product_Detail_list);
		
		System.out.println("ReviewSearchAllControler -gomain");
		request.setAttribute("review_list",review_list);
		System.out.println("request.getAttribute() 리뷰="+request.getAttribute("review_list"));
	
		
		
		// 4.View
		/* return "../product/pro_DetailView_Mj.jsp"; */
		
		  return request.getContextPath()+"/product/pro_DetailView_JS.jsp";
		  }
}
