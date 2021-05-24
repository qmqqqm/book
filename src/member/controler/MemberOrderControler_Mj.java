package member.controler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import member.controler.model.UserDTO_Mj;
import member.controler.service.MemberOrderService_Mj;
import member.controler.service.dto.MemberOrderDTO_Mj;
import mvc.command.CommandHandler_Mj;

//회원이 주문한 삼품을 조회하는 컨트롤러
public class MemberOrderControler_Mj implements CommandHandler_Mj {
		
		String id=null;
		private MemberOrderService_Mj orderService = new MemberOrderService_Mj();
		List<MemberOrderDTO_Mj> order = null;
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			//유저정보 가져오기			
			HttpSession session = request.getSession();
			UserDTO_Mj user = (UserDTO_Mj) session.getAttribute("AUTHUSER");
			//유저정보가 없다면 주문정보 로그인페이지로 이동
			if(user == null) {
				return request.getContextPath()+"/member/login.book";
			}
			else {
				String id= user.getId();
				//오더정보조회을 위해 서비스 클래스 호출
				order = orderService.getOrderList(id);
			}
			//3.모델- session or request
			request.setAttribute("order",order);
			
			//4.View
			return request.getContextPath()+"/member/mem_OrderList_Mj.jsp";
		}
}
