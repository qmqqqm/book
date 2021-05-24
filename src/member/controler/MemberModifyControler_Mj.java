package member.controler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.controler.service.ModifyMemberService_Mj;
import member.controler.service.dto.MemberDTO_Mj;
import mvc.command.CommandHandler_Mj;




//수정처리 컨트롤러
public class MemberModifyControler_Mj implements CommandHandler_Mj {

	
	
	
	//멤버수정을 위한 ModifyMemberService 참조변수 선언
	private ModifyMemberService_Mj modifyService  
		= new ModifyMemberService_Mj();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	
		//1.파라미터받기			
		String id = request.getParameter("id");					
		String name = request.getParameter("name");	
		String birth = request.getParameter("birth");	
		System.out.println("수정컨트롤러진입"+birth);
		String gender = request.getParameter("gender");	
		String hp = request.getParameter("hp");	
				
		
		//비즈니스로직을 수행할 때 필요한 데이터를  MemberDTO_Mj객체로 생성 
		MemberDTO_Mj memReq = 
			new MemberDTO_Mj(id,name,birth,gender,hp);
		//회원정보 수정을 위한 서비스 클래스 호출
		int count=modifyService.modify(memReq);
		
		//3.Model
		request.setAttribute("memReq", memReq); //수정된 글정보
		request.setAttribute("count",count);		
		//4.View
		return request.getContextPath()+"/member/mem_ModifySuccess.jsp";
	}
	
	

}




