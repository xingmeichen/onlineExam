package onlineExam.controller.teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.ResultView;
import onlineExam.persistent.ResultMapper;

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
		
		ResultMapper rdao = new ResultMapper();
		List<ResultView> listResultView = new ArrayList<ResultView>();
		List<ResultView> listResultView1 = new ArrayList<ResultView>();
		
		String paperId = req.getParameter("paperId");
		String studentId = req.getParameter("studentId");
		
		if(null != paperId && null != studentId){
			int flag = 0;
			String[] questionType = {"single","multiple","judge","fill_blank","essay"};
			while(flag < 5)	{
				listResultView1 = rdao.getResultView(paperId, studentId,questionType[flag++]);
				if(null != listResultView1){
					ResultView rv = new ResultView();
					for(int j=0; j<listResultView1.size(); j++){
						rv = listResultView1.get(j);
						listResultView.add(rv);
					}
				}
			}
		}
		
		req.getSession().setAttribute("listResultView", listResultView);
		resp.sendRedirect("resultDetail.jsp");
	}

}
