package mjadd.controler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mjadd.controler.service.ProSearchService_Mj;
import mjadd.controler.service.dto.ProSearchDTO_Mj;
import mvc.command.CommandHandler_Mj;
//상품조회을 담당하는 컨트롤러
public class ProSearchControler_Mj implements CommandHandler_Mj {	
	//필요한 객체준비
	ProSearchService_Mj serachService = new ProSearchService_Mj();
	List<ProSearchDTO_Mj> proSearch=null;
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//파라미터 받기
		String search=request.getParameter("usersearch");
		//비즈니스로직수행
		proSearch = serachService.getSrachList(search);
		//모델
		request.setAttribute("proSearch",proSearch);
		//뷰
		return request.getContextPath()+"/mjadd/pro_Search_Mj.jsp";
	}

}
