package onlineExam.controller.admit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Essay;
import onlineExam.domain.po.FillBlank;
import onlineExam.domain.po.Judge;
import onlineExam.domain.po.Multiple;
import onlineExam.domain.po.Single;
import onlineExam.service.impl.PaperServiceImpl;

@SuppressWarnings("serial")
public class ShowQuestionController extends HttpServlet {
	

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		this.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		String paperId = req.getParameter("paperId");
		PaperServiceImpl paction = new PaperServiceImpl();
		
		List<Single> listSingle = new ArrayList<Single>();
		listSingle = paction.getListSingle(paperId);
		req.getSession().setAttribute("listSingle", listSingle);
		
		List<Multiple> listMultiple = new ArrayList<Multiple>();
		listMultiple = paction.getListMultiple(paperId);
		req.getSession().setAttribute("listMultiple", listMultiple);
		
		List<Judge> listJudge = new ArrayList<Judge>();
		listJudge = paction.getListJudge(paperId);
		req.getSession().setAttribute("listJudge", listJudge);
		
		List<FillBlank> listFill = new ArrayList<FillBlank>();
		listFill = paction.getListFillBlank(paperId);
		req.getSession().setAttribute("listFill", listFill);
		
		List<Essay> listEssay = new ArrayList<Essay>();
		listEssay= paction.getListEssay(paperId);
		req.getSession().setAttribute("listEssay", listEssay);
		
		resp.sendRedirect("ashowExam.jsp");
	}


}
