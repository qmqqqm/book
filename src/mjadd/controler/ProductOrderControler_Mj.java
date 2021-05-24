package mjadd.controler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;

import member.controler.model.UserDTO_Mj;
import mjadd.controler.service.ProOrderService_Mj;
import mvc.command.CommandHandler_Mj;
import order.controler.service.BasketAllDeleteService_Ji;
import order.controler.service.BasketOrderService_Ji;
import order.controler.service.BasketDeleteService_Ji;
import order.controler.service.dto.BasketDTO;
//상품결제 컨트롤러
public class ProductOrderControler_Mj implements CommandHandler_Mj {

	private ProOrderService_Mj  orderService = new  ProOrderService_Mj();
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.로그인된 아이디 가져오기
		String id=getClientIp(request);		
		HttpSession session = request.getSession();
		UserDTO_Mj user = (UserDTO_Mj) session.getAttribute("AUTHUSER");
		if(user!=null) {
		id = user.getId();
		}
		
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		int count = Integer.parseInt(request.getParameter("count"));
		System.out.println("받은값"+id+book_id+count);
				
		int result = orderService.order(id, book_id,count); //2
		request.setAttribute("result", result);
		System.out.println(result);
		
		return request.getContextPath()+"/mjadd/pro_OrderSuccess_Mj.jsp";
		
		
	}
	public static String getClientIp(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	    if (ip == null) ip = request.getRemoteAddr();
	    return ip;
	}
}
