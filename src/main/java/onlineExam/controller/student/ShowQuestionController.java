package onlineExam.controller.student;

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
import onlineExam.domain.po.Result;
import onlineExam.domain.po.Single;
import onlineExam.domain.po.Student;
import onlineExam.persistent.ResultMapper;
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
		
		Student student = new Student();
		student = (Student) req.getSession().getAttribute("student");
		String studentId = student.getStudentId();
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
		
		//将题目总数放入session中
		int questionCount = listSingle.size()+listMultiple.size()+listJudge.size()+listFill.size()+listEssay.size();
		req.getSession().setAttribute("questionCount", questionCount);
		
		//将答题结果放入session中
		List<Result> listResult = new ArrayList<Result>();
		ResultMapper rdao = new ResultMapper();
		if(null!=studentId && null!=paperId){
			listResult = rdao.getResultByStudentIdAndPaperId(studentId, paperId);
			req.getSession().setAttribute("listResult", listResult);
		}		
		
		//开始做单选题
		resp.sendRedirect("doSingle.jsp");
	}


}
