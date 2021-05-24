package order.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.controler.model.UserDTO_Mj;
import mvc.command.CommandHandler_Mj;
import order.controler.service.BasketDeleteService_Ji;

public class BasketDeleteControler_Ji implements CommandHandler_Mj {

	private BasketDeleteService_Ji deleteService = new  BasketDeleteService_Ji();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//1.로그인된 아이디 가져오기
		HttpSession session = request.getSession();
		UserDTO_Mj user = (UserDTO_Mj) session.getAttribute("AUTHUSER");
		String id= user.getId();
		
		int p_no = Integer.parseInt(request.getParameter("pro_no"));
		
		int delete = deleteService.deletecart(id, p_no);
		
		request.setAttribute("delete", delete);
		
		return "../order/ord_basketDeleteSuccess_JI.jsp";
	}

}
