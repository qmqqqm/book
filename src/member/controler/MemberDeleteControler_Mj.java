package member.controler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.controler.service.MemberDeleteService_Mj;
import mvc.command.CommandHandler_Mj;



//삭제요청이 오면  호출되는 담당컨트롤러
public class MemberDeleteControler_Mj implements CommandHandler_Mj {

	private MemberDeleteService_Mj deleteService
	 = new MemberDeleteService_Mj();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DeleteArticleHandler의 process()진입");
		
		//1.파라미터받기
		//no=글번호
		String id = request.getParameter("id"); //글번호
		System.out.println("삭제할 회원id="+id); //확인용		
		//2.비즈니스로직
		int result = deleteService.delete(id);
		//result변수에는 성공시 1의 값이 저장된다
		
		//3.Model
		request.setAttribute("result", result);
		
		//4.View 
		return request.getContextPath()+"/member/mem_DeleteSuccess.jsp";
	}

}






