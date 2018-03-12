package onlineExam.controller.teacher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.service.impl.PaperServiceImpl;

public class AddQuestionController extends HttpServlet {

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
		
		String paperId = (String) req.getSession().getAttribute("paperId");
		
		String single = req.getParameter("single");
		String multiple = req.getParameter("multiple");
		String judge = req.getParameter("judge");
		String fill = req.getParameter("fill");
		String essay = req.getParameter("essay");
		int questionNumber = 0;
		String question = null;
		String A = null;
		String B = null;
		String C = null;
		String D = null;
		String answer = null;
		int mark = 0;
		
		/*如果是单选题输入*/
		if(single != null){
			questionNumber = Integer.parseInt(req.getParameter("squestionNumber"));
			question = req.getParameter("squestion");
			A = req.getParameter("sA");
			B = req.getParameter("sB");
			C = req.getParameter("sC");
			D = req.getParameter("sD");
			answer = req.getParameter("sanswer");
			mark = Integer.parseInt(req.getParameter("smark"));
			result = paction.addSingle(paperId, questionNumber, question, A, B, C, D, answer, mark);
			if(result)
				resp.sendRedirect("addQuestion.jsp");
			else 
				resp.sendRedirect("error.jsp");
		}
		
		/*如果是不定项选择题输入*/
		else if(multiple != null){
			questionNumber = Integer.parseInt(req.getParameter("mquestionNumber"));
			question = req.getParameter("mquestion");
			A = req.getParameter("mA");
			B = req.getParameter("mB");
			C = req.getParameter("mC");
			D = req.getParameter("mD");
			answer = req.getParameter("manswer");
			mark = Integer.parseInt(req.getParameter("mmark"));
			result = paction.addMultiple(paperId, questionNumber, question, A, B, C, D, answer, mark);
			if(result)
				resp.sendRedirect("addQuestion.jsp");
			else 
				resp.sendRedirect("error.jsp");
		}
		
		/*如果是判断题输入*/
		else if(judge != null){
			questionNumber = Integer.parseInt(req.getParameter("jquestionNumber"));
			question = req.getParameter("jquestion");			
			answer = req.getParameter("janswer");
			mark = Integer.parseInt(req.getParameter("jmark"));
			result = paction.addJudge(paperId, questionNumber, question, answer, mark);
			if(result)
				resp.sendRedirect("addQuestion.jsp");
			else 
				resp.sendRedirect("erro.jsp");
		}
		
		/*如果是填空题输入*/
		else if(fill != null){
			questionNumber = Integer.parseInt(req.getParameter("fquestionNumber"));
			String fquestion = req.getParameter("fQuestion");						
			mark = Integer.parseInt(req.getParameter("smark"));
			result = paction.addFillBlank(paperId, questionNumber, fquestion, mark);
			if(result)
				resp.sendRedirect("addQuestion.jsp");
			else 
				resp.sendRedirect("erro.jsp");
		}
		
		/*如果是问答题输入*/
		else if(essay != null){
			questionNumber = Integer.parseInt(req.getParameter("equestionNumber"));
			question = req.getParameter("equestion");			
			mark = Integer.parseInt(req.getParameter("emark"));
			result = paction.addEssay(paperId, questionNumber, question, mark);
			if(result)
				resp.sendRedirect("addQuestion.jsp");
			else 
				resp.sendRedirect("erro.jsp");
		}
		
		else
			resp.sendRedirect("error.jsp");
	}

}
