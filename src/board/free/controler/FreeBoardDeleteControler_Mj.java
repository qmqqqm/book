package board.free.controler;

//삭제요청이 오면  호출되는 담당컨트롤러
//article/delete.do?no=글번호
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.free.controler.service.FreeBoardDeleteService_Mj;
import mvc.command.CommandHandler_Mj;



public class FreeBoardDeleteControler_Mj implements CommandHandler_Mj {

	private FreeBoardDeleteService_Mj deleteService
	 = new FreeBoardDeleteService_Mj();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		//no=글번호
		int no = Integer.parseInt(request.getParameter("no")); //글번호
		
		//2.비즈니스로직
		int result = deleteService.delete(no);
		//result변수에는 성공시 1의 값이 저장
		
		//3.Model
		request.setAttribute("result", result);
		
		//4.View 		
		return request.getContextPath()+"/board/free/deleteSuccess.jsp";
	}

}






