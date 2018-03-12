package onlineExam.controller.teacher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.service.impl.PaperServiceImpl;

public class AddFillController extends HttpServlet {

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
		int mark = 0;
		questionNumber = Integer.parseInt(req.getParameter("fquestionNumber"));
		String question = req.getParameter("fQuestion");
		
		mark = Integer.parseInt(req.getParameter("fmark"));
		result = paction.addFillBlank(paperId, questionNumber, question, mark);
		
		if(result){
			req.getSession().setAttribute("message", "添加填空题成功");
			resp.sendRedirect("thome.jsp");
		}
		else{
			req.getSession().setAttribute("message", "很抱歉，添加填空题失败,请确认该道题是否已经存在！");
			resp.sendRedirect("inf.jsp");
		}
	}
}























