package onlineExam.controller.teacher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.service.impl.PaperServiceImpl;

public class AddMultipleController extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		this.doPost(req, resp);
	}


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
		
		questionNumber = Integer.parseInt(req.getParameter("mquestionNumber"));
		question = req.getParameter("mquestion");
		A = req.getParameter("mA");
		B = req.getParameter("mB");
		C = req.getParameter("mC");
		D = req.getParameter("mD");
		answer = req.getParameter("manswer");
		mark = Integer.parseInt(req.getParameter("mmark"));
		result = paction.addMultiple(paperId, questionNumber, question, A, B, C, D, answer, mark);
		if(result){
			req.getSession().setAttribute("message", "添加不定项选择题成功");
			resp.sendRedirect("thome.jsp");
		}			
		else {
			req.getSession().setAttribute("message", "很抱歉，加不定项选择题失败");
			resp.sendRedirect("error.jsp");
		}
			
	}
}
