package onlineExam.controller.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.ResultView;
import onlineExam.service.impl.TeacherServiceImpl;

public class ResultDetailController extends HttpServlet {

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
		String studentId = req.getParameter("studentId");
		
		List<ResultView> listResultViewFill = new ArrayList<ResultView>();
		TeacherServiceImpl taction = new TeacherServiceImpl();		
		listResultViewFill = taction.getResultView(paperId, studentId, "fill_blank");
		req.getSession().setAttribute("listResultViewFill", listResultViewFill);
		
		List<ResultView> listResultViewEssay = new ArrayList<ResultView>();
		listResultViewEssay = taction.getResultView(paperId, studentId, "essay");
		req.getSession().setAttribute("listResultViewEssay", listResultViewEssay);
		resp.sendRedirect("resultDetail.jsp");
	}
}
