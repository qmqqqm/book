package product.review;


import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler_Mj;
import product.review.dto.ReviewDTO;
import product.review.service.ReviewUpdateService;


//이 클래스 모든 상품의 모든 정보를 가져올 클래스이다
//이 컨트롤러는   /product/searchAll.book 요청이오면  호출되는 담당컨트롤러이다
//비즈니스로직실행 <->ProductService2<->ProductDAO2<->db
public class ReviewUpdateConctroler_JS implements CommandHandler_Mj {
	
		private ReviewUpdateService reviewUpdateService = new ReviewUpdateService();
		
		@Override
		public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			if(request.getMethod().equalsIgnoreCase("GET")) {//글쓰기폼 보여주기 요청(GET방식) 
				System.out.println("process GET");
				return processForm(request, response);
			}else if(request.getMethod().equalsIgnoreCase("POST")) {
				System.out.println("process POST");
				return processSubmit(request, response);
			}else {
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return null;
			}
			}
		
		
		private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException {
			//1.파라미터받기
			System.out.println("ReviewUpdateConctroler_JS -process");
			System.out.println("Integer.parseInt(request.getParameter(\"review_id\")):"+Integer.parseInt(request.getParameter("review_id")));
//			System.out.println("Integer.parseInt(request.getParameter(\"product_id\")):"+Integer.parseInt(request.getParameter("product_id")));
			System.out.println("request.getParameter(\"review_mem_id\"):"+request.getParameter("review_mem_id"));
			System.out.println("request.getParameter(\"review_write\"):"+request.getParameter("review_write"));
			System.out.println("request.getParameter(\"review_pro_title\"):"+request.getParameter("review_pro_title"));
			System.out.println("Integer.parseInt(request.getParameter(\"review_star\")):"+Integer.parseInt(request.getParameter("review_star")));
			//String str = request.getParameter("book_kind");
		//	System.out.println(str);
				//2.비즈니스로직실행 <->ProductService2<->ProductDAO2<->db
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			ReviewDTO review_Update = new ReviewDTO(
					Integer.parseInt(request.getParameter("review_id")),
					Integer.parseInt(request.getParameter("product_id")),
					request.getParameter("review_mem_id"),//
					request.getParameter("review_write"),//
					request.getParameter("review_pro_title"),//
					Integer.parseInt(request.getParameter("review_star")));//
		
			System.out.println("나는 서비스에 가는중");
			reviewUpdateService.getReviewUpdate(review_Update);
				
				 System.out.println("나는 서비스에 갔다왔어");
				
				//3.모델- session or request
					request.setAttribute("review_id", request.getParameter("review_id"));
					request.setAttribute("product_id", request.getParameter("product_id"));
					request.setAttribute("review_mem_id", request.getParameter("review_mem_id"));
					request.setAttribute("review_write", request.getParameter("review_write"));
					request.setAttribute("review_pro_title", request.getParameter("review_pro_title"));
					request.setAttribute("review_star", request.getParameter("review_star"));
				 
				 System.out.println("모델- session or request");
				 
				//4.View
				return "/mainSub/bookDetail.book?book_id="+request.getParameter("product_id");
		}
		
		private String processForm(HttpServletRequest request, HttpServletResponse response) {
			System.out.println("processForm");
			request.setAttribute("review_id", request.getParameter("review_id"));
			request.setAttribute("product_id", request.getParameter("book_id"));
			request.setAttribute("review_mem_id", request.getParameter("review_mem_id"));
			request.setAttribute("review_write", request.getParameter("review_write"));
			request.setAttribute("review_pro_title", request.getParameter("review_pro_title"));
			request.setAttribute("review_star", request.getParameter("review_star"));
			System.out.println(request.getParameter("review_pro_title")+"-----------------------");
			
			return "../product/pro_Review_UpdateView_JS.jsp";
		}
}
