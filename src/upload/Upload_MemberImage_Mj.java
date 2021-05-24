package upload;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import member.controler.service.UpdateMemberImageService_mj;

public class Upload_MemberImage_Mj extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		response.setContentType("text/html; charset=utf-8");

		PrintWriter writer = response.getWriter();
		writer.println("<html><body>");

		String contentType = request.getContentType();
		if (contentType != null
				&& contentType.toLowerCase().startsWith("multipart/")) {
			try {
				printPartInfo(request, writer,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			writer.println("multipart가 아님");
			
		}
		writer.println("</body></html>");
	}

	private void printPartInfo(HttpServletRequest request, PrintWriter writer,HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		UpdateMemberImageService_mj updateimg=new UpdateMemberImageService_mj();
		String id=request.getParameter("id");
		String getfile=request.getParameter("getfile");
		System.out.println(id+getfile);
		updateimg.updateimg(id,getfile);
		
		Collection<Part> parts = request.getParts();
		for (Part part : parts) {
			writer.println("<br/> name = " + part.getName());
			String contentType = part.getContentType();
			
			writer.println("<br/> contentType = " + contentType);
			if (part.getHeader("Content-Disposition").contains("filename=")) {
				writer.println("<br/> size = " + part.getSize());
				String fileName = part.getSubmittedFileName();
				writer.println("<br/> filename = " + fileName);
				if (part.getSize() > 0) {
					part.write("E:\\ourstudy\\javaStudy\\jsp\\JBKBOOK\\WebContent\\img\\" + fileName);//서버저장경로설정
					/*part.write("D:\\workspce\\jsp\\JBKBOOK\\WebContent\\img\\" + fileName);*/ //학원저장경로설정
					part.delete();
				}
			} else {
				String value = request.getParameter(part.getName());
				writer.println("<br/> value = " + value);
				
			}
			writer.println("<hr/>");
		}
		response.sendRedirect("/member/mypage.book");
	
	}

}
