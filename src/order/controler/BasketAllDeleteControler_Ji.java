package order.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.controler.model.UserDTO_Mj;
import mvc.command.CommandHandler_Mj;
import order.controler.service.BasketAllDeleteService_Ji;
import order.controler.service.BasketDeleteService_Ji;

//장바구니 전체 삭제
public class BasketAllDeleteControler_Ji implements CommandHandler_Mj {


	private BasketAllDeleteService_Ji allDeleteService = new  BasketAllDeleteService_Ji();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.로그인된 아이디 가져오기
				HttpSession session = request.getSession();
				UserDTO_Mj user = (UserDTO_Mj) session.getAttribute("AUTHUSER");
				String id= user.getId();
				
				int allDelete = allDeleteService.allDeletecart(id);
				
				request.setAttribute("allDelete", allDelete);
				System.out.println(allDelete);
				
				return "../order/ord_basketAllDeleteSuccess_JI.jsp";
			}
	}
