package onlineExam.controller.teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Result;
import onlineExam.domain.po.ResultView;
import onlineExam.persistent.ResultMapper;

public class ResultListController extends HttpServlet {
	
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
		
		ResultMapper rdao = new ResultMapper();
		List<Result> listResult = new ArrayList<Result>();
		List<ResultView> listResultView = new ArrayList<ResultView>();
		List<ResultView> listResultView1 = new ArrayList<ResultView>();
		
		//得到某个套试卷的所有答题结果
		listResult = rdao.getResultOfPaperMaker(paperId);	
		req.getSession().setAttribute("listResult", listResult);
		resp.sendRedirect("resultList.jsp");
	}
}
