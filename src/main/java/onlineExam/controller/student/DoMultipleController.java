package onlineExam.controller.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Multiple;
import onlineExam.domain.po.Result;
import onlineExam.domain.po.Student;
import onlineExam.persistent.ResultMapper;
import onlineExam.service.impl.PaperServiceImpl;

public class DoMultipleController extends HttpServlet {

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
		
		List<Multiple> listMultiple = new ArrayList<Multiple>();      //单选题
		listMultiple = (List<Multiple>) req.getSession().getAttribute("listMultiple");
		
		Student student = new Student();
		student = (Student) req.getSession().getAttribute("student");
		String studentId = student.getStudentId();
		
		String questionType = "multiple"; //保存的是单选题的答案，所以对应的题目类型是single
		
		String last = req.getParameter("last"); //上一题按钮
		String next = req.getParameter("next"); //下一题按钮
		
		boolean result = false;
		if(null != listMultiple && listMultiple.size()>0){
			Multiple m = new Multiple();
			for(int i=0; i<listMultiple.size(); i++){
				m = listMultiple.get(i);
				String paperId = m.getPaperId();
				int questionNumber  = m.getOptionNumber();
				String answer="";
				String answerA = req.getParameter(((Integer)questionNumber).toString()+"A");

				if(null != answerA)
					answer = answer+answerA;

				String answerB = req.getParameter(((Integer)questionNumber).toString()+"B");

				if(null != answerB)
					answer = answer + answerB;

				String answerC = req.getParameter(((Integer)questionNumber).toString()+"C");

				if(null != answerC)
					answer = answer + answerC;

				String answerD = req.getParameter(((Integer)questionNumber).toString()+"D");

				if(null != answerD)
					answer = answer + answerD;
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
				
				//修改结果表中的考生得分
				rdao.signMultipleOptionMark(studentId);	
				rdao.signMultipleOptionMarkN(studentId);
			}			
			
			if(null != last)
				resp.sendRedirect("doSingle.jsp");
			else if(null != next)
				resp.sendRedirect("doJudge.jsp");
			
		}
		//如果该类题型为空，则跳转到上一题或者下一题
		else if(null==listMultiple || listMultiple.size()==0){			
			if(null != next) //如果是下一题按钮被点击
				resp.sendRedirect("doJudge.jsp");
			else if(null != last)
				resp.sendRedirect("doSingle.jsp");
		}
		else{
			resp.sendRedirect("error.jsp");
		}
		
	}



}
