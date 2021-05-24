package order.controler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.qna.controler.service.QnaBoardListPageService_JI;
import member.controler.model.UserDTO_Mj;
import mvc.command.CommandHandler_Mj;
import order.controler.service.BasketListService_JI;
import order.controler.service.dto.BasketDTO;
//회원의 장바구니 목목 컨트롤러
public class BasketListControler_Ji implements CommandHandler_Mj {

		private BasketListService_JI basketService = new BasketListService_JI();
		List<BasketDTO> basket = null;
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			//1.로그인된 아이디 가져오기
			HttpSession session = request.getSession();
			UserDTO_Mj user = (UserDTO_Mj) session.getAttribute("AUTHUSER");
			
			if(user == null) {
				return "../order/ord_Basket_Mj.jsp";
			}
			else {
				String id= user.getId();
				basket = basketService.getBasketList(id);
			}
			//3.모델- session or request
			request.setAttribute("basket",basket);
			
			System.out.println(basket); //확인용
			System.out.println("핸들러 다녀옴");
			//4.View
			return "../order/ord_Basket_Mj.jsp";
		}
}
