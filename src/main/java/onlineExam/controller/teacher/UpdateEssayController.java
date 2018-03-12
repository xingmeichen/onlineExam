package onlineExam.controller.teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Essay;
import onlineExam.persistent.EssayMapper;
import onlineExam.service.impl.PaperServiceImpl;

public class UpdateEssayController extends HttpServlet {

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
		
		EssayMapper edao = new EssayMapper();
		PaperServiceImpl paction = new PaperServiceImpl();
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		String question = req.getParameter("question");
		if(null != question && !question.equals("")){
			edao.updateQuestion(id, question);
		}		
		
		if(null != req.getParameter("mark") && !(req.getParameter("mark").equals(""))){
			int mark = Integer.parseInt(req.getParameter("mark"));
			if(null != ((Integer)mark) && !(((Integer)mark).equals("")) )
				edao.updateMark(id, mark);
		}	
		
		/*修改session中的所有单选题的值*/
		String paperId = req.getParameter("paperId");
		List<Essay> listEssay= new ArrayList<Essay>();
		listEssay = paction.getListEssay(paperId);
		req.getSession().setAttribute("listEssay", listEssay);
		
		resp.sendRedirect("updateQuestion.jsp");
	}
}
