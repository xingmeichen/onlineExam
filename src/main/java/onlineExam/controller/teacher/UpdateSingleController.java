package onlineExam.controller.teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Single;
import onlineExam.persistent.SingleMapper;
import onlineExam.service.impl.PaperServiceImpl;

public class UpdateSingleController extends HttpServlet {

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
		
		SingleMapper sdao = new SingleMapper();
		PaperServiceImpl paction = new PaperServiceImpl();
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		String question = req.getParameter("question");
		if(null != question && !question.equals("")){
			sdao.updateQuestion(id, question);
		}
		
		String A = req.getParameter("A");
		if(null != A && !A.equals(""))
			sdao.updateA(id, A);
		
		String B = req.getParameter("B");
		if(null != B && !B.equals(""))
			sdao.updateB(id, B);
		
		String C = req.getParameter("C");
		if(null != C && !C.equals(""))
			sdao.updateC(id, C);
		
		String D = req.getParameter("D");
		if(null != D && !D.equals(""))
			sdao.updateD(id, D);
		
		String answer = req.getParameter("answer");
		if(null != answer && !answer.equals(""))
			sdao.updateAnswer(id, answer);
		
		if(null != req.getParameter("mark") && !(req.getParameter("mark").equals(""))){
			int mark = Integer.parseInt(req.getParameter("mark"));
			if(null != ((Integer)mark) && !(((Integer)mark).equals("")) )
				sdao.updateMark(id, mark);
		}	
		
		/*修改session中的所有单选题的值*/
		String paperId = req.getParameter("paperId");
		List<Single> listSingle = new ArrayList<Single>();
		listSingle = paction.getListSingle(paperId);
		req.getSession().setAttribute("listSingle", listSingle);
		
		resp.sendRedirect("updateQuestion.jsp");
		
		
	}

	
}
