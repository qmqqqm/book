package member.controler;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.controler.service.MemberIdCheckService_Mj;
import mvc.command.CommandHandler_Mj;



//이 servelt클래스는 회원가입시 회원id 사용가능여부 체크하는 클래스  
public class MemberIdCheckControler_Mj implements CommandHandler_Mj{

	private MemberIdCheckService_Mj memberJoinService = new MemberIdCheckService_Mj();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		//1.파라미터받기 		
		String id = request.getParameter("id");	
		//3.Model		
		PrintWriter out = response.getWriter();
		
		
		boolean overlappedID = memberJoinService.overlappedID(id);
		//user가 입력한 id가   이미 사용중인 id이면  true가 리턴=>사용불가
		//그렇지 않으면 false가 리턴=>사용가능
		
		if (overlappedID == true) {
			//serve가 브라우저로  text형태로 응답(response)한다 
			out.print("not_usable"); 
		} else {
			out.print("usable");
		}
		
		//4.View		
		return null;
	}

}//class	

    





