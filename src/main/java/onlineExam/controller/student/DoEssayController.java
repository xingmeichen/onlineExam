package onlineExam.controller.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Essay;
import onlineExam.domain.po.Result;
import onlineExam.domain.po.Student;
import onlineExam.persistent.ResultMapper;
import onlineExam.service.impl.PaperServiceImpl;

public class DoEssayController extends HttpServlet {

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
		
		List<Essay> listEssay = new ArrayList<Essay>();      //单选题
		listEssay = (List<Essay>) req.getSession().getAttribute("listEssay");
		
		Student student = new Student();
		student = (Student) req.getSession().getAttribute("student");
		String studentId = student.getStudentId();
		
		//保存的是单选题的答案，所以对应的题目类型是single
		String questionType = "essay"; 
		
		//上一题按钮
		String last = req.getParameter("last");	
		//完成考试按钮
		String save = req.getParameter("save"); 
		
		boolean result = false;
		if(null != listEssay && listEssay.size()>0){
			Essay e = new Essay();
			for(int i=0; i<listEssay.size(); i++){
				e = listEssay.get(i);
				String paperId = e.getPaperId();
				int questionNumber  = e.getQuestionNumber();
				String answer = req.getParameter(((Integer)questionNumber).toString());
				if(null != answer){
					result = rdao.saveResult(studentId, paperId, questionNumber, answer, questionType);

					//答题结果保存到数据库中之后并更新session中的答题结果
					List<Result> listResult = new ArrayList<Result>();					
					if(null!=studentId && null!=paperId){
						listResult = rdao.getResultByStudentIdAndPaperId(studentId, paperId);
						req.getSession().setAttribute("listResult", listResult);
					}		
				}
				
				
				if(!result) //如果保存答案失败，则退出循环
					break;				
			}
			
			if(!result)
				resp.sendRedirect("error.jsp");
			else if(null != last)
				resp.sendRedirect("doJudge.jsp");
			else if(null != save)
				resp.sendRedirect("examEnd.jsp");
			else 
				resp.sendRedirect("error.jsp");
		}
		else if(null==listEssay || listEssay.size()==0){
			if(null != last)
				resp.sendRedirect("doFill.jsp");
		}
		else{
			resp.sendRedirect("error.jsp");
		}
		
	}



}
