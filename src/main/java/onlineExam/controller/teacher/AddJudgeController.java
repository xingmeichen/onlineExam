package onlineExam.controller.teacher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.service.impl.PaperServiceImpl;

public class AddJudgeController extends HttpServlet {

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
		String answer = null;
		int mark = 0;		
		
		questionNumber = Integer.parseInt(req.getParameter("jquestionNumber"));
		System.out.println();
		
		question = req.getParameter("jquestion");	
		System.out.println(question);
		
		answer = req.getParameter("janswer");
		System.out.println(answer);
		
		mark = Integer.parseInt(req.getParameter("jmark"));
		System.out.println(mark);
		
		result = paction.addJudge(paperId, questionNumber, question, answer, mark);
		
		if(result){
			req.getSession().setAttribute("message", "添加判断题成功");
			resp.sendRedirect("thome.jsp");
		}
		else{
			req.getSession().setAttribute("message", "很抱歉，添加判断题失败，请确认该道题是否已经存在");
			resp.sendRedirect("inf.jsp");
		}
	}
}
