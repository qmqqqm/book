package member.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.controler.service.FindPwdService_Mj;
import mvc.command.CommandHandler_Mj;

public class FindPwdControler_Mj implements CommandHandler_Mj {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			FindPwdService_Mj findPwd=new FindPwdService_Mj();
			String pwd=null;
			String id=request.getParameter("id");			
			String hp=request.getParameter("hp");
			String birth=request.getParameter("birth");
			if(id==null || hp==null ||birth==null) {
				return request.getContextPath()+"/member/mem_FindPwd_Mj.jsp";
			}
			
			pwd= findPwd.findPwd(id,hp,birth);
			request.setAttribute("userPWD", pwd);
			return request.getContextPath()+"/member/mem_FindPwd_Mj.jsp";
		}

	}
		