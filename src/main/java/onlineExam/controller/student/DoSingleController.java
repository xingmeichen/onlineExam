package onlineExam.controller.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Result;
import onlineExam.domain.po.Single;
import onlineExam.domain.po.Student;
import onlineExam.persistent.ResultMapper;
import onlineExam.service.impl.PaperServiceImpl;

public class DoSingleController extends HttpServlet {

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
		
		List<Single> listSingle = new ArrayList<Single>();      //单选题
		listSingle = (List<Single>) req.getSession().getAttribute("listSingle");
		
		Student student = new Student();
		student = (Student) req.getSession().getAttribute("student");
		String studentId = student.getStudentId();
		
		String questionType = "single"; //保存的是单选题的答案，所以对应的题目类型是single
		
		String next = req.getParameter("next"); //下一题按钮
		
		String message = "";
		
		boolean result = false;
		if(null != listSingle && listSingle.size()>0){
			Single s = new Single();
			for(int i=0; i<listSingle.size(); i++){
				s = listSingle.get(i);
				String paperId = s.getPaperId();
				int questionNumber  = s.getOptionNumber();
				String answer = req.getParameter(((Integer)questionNumber).toString());	
				if(null != answer)//如果答题结果不为空，则将答题结果保存到数据库中
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
				rdao.signSingleOptionMark(studentId); //系统自动打分
				rdao.signSingleOptionMarkN(studentId);
			}		    
		   
		    if(null != next)
		    	resp.sendRedirect("doMultiple.jsp");		  
		}
		else if(null==listSingle || listSingle.size()==0){
			if(null != next){
				resp.sendRedirect("doMultiple.jsp");
			}			
		}
		else{
			resp.sendRedirect("error.jsp");
		}
		
	}



}
