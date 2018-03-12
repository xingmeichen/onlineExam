package onlineExam.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Paper;
import onlineExam.service.impl.PaperServiceImpl;

@SuppressWarnings("serial")
public class ShowPaperController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		String paperId = req.getParameter("paperId");
		PaperServiceImpl paction = new PaperServiceImpl();
		List<Object> listQuestion = new ArrayList<Object>();
		
		System.out.println(paperId);
		
		Paper p = new Paper();
		if(paperId != null){
			p = paction.getPaperInf(paperId);	
		}

		req.getSession().setAttribute("thisPaper",p);		
		resp.sendRedirect("showPaper.jsp");
	}
}
