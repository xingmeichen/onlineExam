package onlineExam.controller.teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Judge;
import onlineExam.persistent.JudgeMapper;
import onlineExam.service.impl.PaperServiceImpl;

public class UpdateJudgeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		JudgeMapper jdao = new JudgeMapper();
		PaperServiceImpl paction = new PaperServiceImpl();
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		String question = req.getParameter("question");
		if(null != question && !question.equals("")){
			jdao.updateQuestion(id, question);
		}
		
		String answer = req.getParameter("answer");
		if(null != answer && !answer.equals(""))
			jdao.updateAnswer(id, answer);
		
		if(null != req.getParameter("mark") && !(req.getParameter("mark").equals(""))){
			int mark = Integer.parseInt(req.getParameter("mark"));
			if(null != ((Integer)mark) && !(((Integer)mark).equals("")) )
				jdao.updateMark(id, mark);
		}	
		
		/*修改session中的所有单选题的值*/
		String paperId = req.getParameter("paperId");
		List<Judge> listJudge = new ArrayList<Judge>();
		listJudge = paction.getListJudge(paperId);
		req.getSession().setAttribute("listJudge", listJudge);
		
		resp.sendRedirect("updateQuestion.jsp");
	}
}
