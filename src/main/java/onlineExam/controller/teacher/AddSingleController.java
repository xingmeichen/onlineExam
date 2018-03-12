package onlineExam.controller.teacher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.service.impl.PaperServiceImpl;

public class AddSingleController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		PaperServiceImpl paction = new PaperServiceImpl();
		boolean result = false;
		
		String paperId = req.getParameter("paperId");		

		int questionNumber = 0;
		String question = null;
		String A = null;
		String B = null;
		String C = null;
		String D = null;
		String answer = null;
		int mark = 0;
		
		questionNumber = Integer.parseInt(req.getParameter("squestionNumber"));
		question = req.getParameter("squestion");
		A = req.getParameter("sA");
		B = req.getParameter("sB");
		C = req.getParameter("sC");
		D = req.getParameter("sD");
		answer = req.getParameter("sanswer");
		mark = Integer.parseInt(req.getParameter("smark"));
		result = paction.addSingle(paperId, questionNumber, question, A, B, C, D, answer, mark);
		
		if(result){
			req.setAttribute("message", "添加单选题成功");
			resp.sendRedirect("thome.jsp");
		}
		else{
			req.setAttribute("message", "很抱歉，添加单选题失败");
		}

	}
}

















