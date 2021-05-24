package order.controler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.controler.model.UserDTO_Mj;
import mvc.command.CommandHandler_Mj;
import order.controler.service.BasketAllDeleteService_Ji;
import order.controler.service.BasketOrderService_Ji;
import order.controler.service.BasketDeleteService_Ji;
import order.controler.service.dto.BasketDTO;
//상품결제 컨트롤러
public class BasketOrderControler_Ji implements CommandHandler_Mj {

	private BasketOrderService_Ji  orderService = new  BasketOrderService_Ji();
	private BasketDeleteService_Ji addDeleteService = new  BasketDeleteService_Ji();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.로그인된 아이디 가져오기
				
		HttpSession session = request.getSession();
		UserDTO_Mj user = (UserDTO_Mj) session.getAttribute("AUTHUSER");
		String id = user.getId();
		
		
		String checked = request.getParameter("chk[]");
		String[] strNo = null;
		strNo = checked.split(",");
		
		//확인용
		for ( int i = 0 ; i < strNo.length ; i++ ) {
		System.out.println(strNo[i]);
		}
			
		int[] no = new int[strNo.length];
		for(int i=0;i<strNo.length; i++){ 
			no[i] = Integer.parseInt(strNo[i]); 
			System.out.println("test = "+no[i]);
			}
		
		int result = orderService.order(id, no); //2
		
		
		request.setAttribute("result", result);
		System.out.println(result);
		
		return "../order/ord_OrderSuccess_JI.jsp";
		
		
	}
}
