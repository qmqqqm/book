package admin.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler_Mj;

public class ProManagementControler_Mj implements CommandHandler_Mj {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return request.getContextPath()+"/admin/adm_ProUpload_Mj.jsp";
	}

}
