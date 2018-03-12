package onlineExam.controller.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Judge;
import onlineExam.domain.po.Result;
import onlineExam.domain.po.Student;
import onlineExam.persistent.ResultMapper;
import onlineExam.service.impl.PaperServiceImpl;

public class DoJudgeController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		this.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		PaperServiceImpl paction = new PaperServiceImpl();
		ResultMapper rdao = new ResultMapper();
		
		List<Judge> listJudge = new ArrayList<Judge>();      //单选题
		listJudge = (List<Judge>) req.getSession().getAttribute("listJudge");
		
		Student student = new Student();
		student = (Student) req.getSession().getAttribute("student");
		String studentId = student.getStudentId();
		
		String questionType = "judge"; //保存的是单选题的答案，所以对应的题目类型是single
		
		String last = req.getParameter("last"); //上一题按钮
		String next = req.getParameter("next"); //下一题按钮
		
		
		boolean result = false;
		if(null != listJudge && listJudge.size()>0){
			Judge j = new Judge();
			for(int i=0; i<listJudge.size(); i++){
				j = listJudge.get(i);
				String paperId = j.getPaperId();
				int questionNumber  = j.getQuestionNumber();
				String answer = req.getParameter(((Integer)questionNumber).toString());	
				if(null != answer)
				{
					//将答题结果保存到数据库中
					result = rdao.saveResult(studentId, paperId, questionNumber, answer, questionType);
					//答题结果保存到数据库中之后并更新session中的答题结果
					List<Result> listResult = new ArrayList<Result>();					
					if(null!=studentId && null!=paperId){
						listResult = rdao.getResultByStudentIdAndPaperId(studentId, paperId);
						req.getSession().setAttribute("listResult", listResult);
					}
				}
				
				rdao.signJudgeQuestionMark(studentId);	
				rdao.signJudgeQuestionMarkN(studentId);
			}			

			if(null != last)
				resp.sendRedirect("doMultiple.jsp");
			else if(null != next)
				resp.sendRedirect("doFill.jsp");			
		}
		else if(null==listJudge || listJudge.size()==0){
			if(null != next)
				resp.sendRedirect("doFill.jsp");
			else if(null != last)
				resp.sendRedirect("doMultiple.jsp");
		}
		else{
			resp.sendRedirect("error.jsp");
		}
		
	}




}
