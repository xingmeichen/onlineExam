package onlineExam.controller.teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Multiple;
import onlineExam.persistent.MultipleMapper;
import onlineExam.service.impl.PaperServiceImpl;

public class UpdateMultipleController extends HttpServlet {

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
		
		MultipleMapper mdao = new MultipleMapper();
		PaperServiceImpl paction = new PaperServiceImpl();
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		String question = req.getParameter("question");
		if(null != question && !question.equals("")){
			mdao.updateQuestion(id, question);
		}
		
		String A = req.getParameter("A");
		if(null != A && !A.equals(""))
			mdao.updateA(id, A);
		
		String B = req.getParameter("B");
		if(null != B && !B.equals(""))
			mdao.updateB(id, B);
		
		String C = req.getParameter("C");
		if(null != C && !C.equals(""))
			mdao.updateC(id, C);
		
		String D = req.getParameter("D");
		if(null != D && !D.equals(""))
			mdao.updateD(id, D);
		
		String answer = req.getParameter("answer");
		if(null != answer && !answer.equals(""))
			mdao.updateAnswer(id, answer);
		
		if(null != req.getParameter("mark") && !(req.getParameter("mark").equals(""))){
			int mark = Integer.parseInt(req.getParameter("mark"));
			if(null != ((Integer)mark) && !(((Integer)mark).equals("")) )
				mdao.updateMark(id, mark);
		}	
		
		/*修改session中的所有单选题的值*/
		String paperId = req.getParameter("paperId");
		List<Multiple> listMultiple = new ArrayList<Multiple>();
		listMultiple = paction.getListMultiple(paperId);
		req.getSession().setAttribute("listMultiple", listMultiple);
		
		resp.sendRedirect("updateQuestion.jsp");
		
		
	}
}
