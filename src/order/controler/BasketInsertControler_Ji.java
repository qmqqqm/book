package order.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.controler.model.UserDTO_Mj;
import mvc.command.CommandHandler_Mj;
import order.controler.service.BasketInsertService_Ji;
//유저의 바스킷 리스트
public class BasketInsertControler_Ji implements CommandHandler_Mj {

	private BasketInsertService_Ji basketInsertService = new BasketInsertService_Ji();
	private int result;
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("핸들러 진입");
		
		//로그인기능 완성후 
		//1.로그인된 아이디 가져오기
		HttpSession session = request.getSession();
		UserDTO_Mj user = (UserDTO_Mj) session.getAttribute("AUTHUSER");
		int no = 0;
		if(user == null) {
			return "../member/login.book";
		}
		else {
			//유저아이디
			String id = user.getId();
			no = Integer.parseInt(request.getParameter("no")); //제품번호
			int amount = Integer.parseInt(request.getParameter("quantity")); //제품수량
			System.out.println(no);
			System.out.println(amount);
			
			result = basketInsertService.addBasket(id, no, amount);
		}
		//3.모델- session or request
		request.setAttribute("result",result);
		request.setAttribute("book_no",no);
		
		System.out.println(result); //확인용
		
		//4.View
		return "../order/ord_basketInsertSuccess_JI.jsp";
		}
	}
