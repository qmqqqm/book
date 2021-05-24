package member.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.controler.service.FindIdService_Mj;
import mvc.command.CommandHandler_Mj;

public class FindIdControler_Mj implements CommandHandler_Mj {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FindIdService_Mj findId=new FindIdService_Mj();
		String id=null;
		String name=request.getParameter("name");
		String hp=request.getParameter("hp");
		String birth=request.getParameter("birth");
		if(name==null || hp==null ||birth==null) {
			return request.getContextPath()+"/member/mem_FindId_Mj.jsp";
		}
		
		id= findId.findId(name,hp,birth);
		request.setAttribute("userID", id);
		return request.getContextPath()+"/member/mem_FindId_Mj.jsp";
	}

}
