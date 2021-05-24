package mjadd.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mjadd.controler.service.ProDetailService_Mj;
import mjadd.controler.service.dto.ProSearchDTO_Mj;
import mvc.command.CommandHandler_Mj;
//상품상세페이지 컨트롤러
public class ProDetailControler_Mj implements CommandHandler_Mj {
	//서비스객체생성
	ProDetailService_Mj detailService = new ProDetailService_Mj();
	ProSearchDTO_Mj scitem=new ProSearchDTO_Mj();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//파라미터 받기
		int bookid=Integer.parseInt(request.getParameter("id"));
		//상품상세조회를 위한 서비스 클래스호출
		scitem = detailService.getDetail(bookid);			
		//모델
		request.setAttribute("SCITEM",scitem);
		//뷰
		return request.getContextPath()+"/mjadd/pro_Detail_Mj.jsp";
	}

}
