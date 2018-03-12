package onlineExam.controller.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.FillBlank;
import onlineExam.domain.po.Result;
import onlineExam.domain.po.Student;
import onlineExam.persistent.ResultMapper;
import onlineExam.service.impl.PaperServiceImpl;

public class DoFillController extends HttpServlet {

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
		
		List<FillBlank> listFill = new ArrayList<FillBlank>();      //单选题
		listFill = (List<FillBlank>) req.getSession().getAttribute("listFill");
		
		Student student = new Student();
		student = (Student) req.getSession().getAttribute("student");
		String studentId = student.getStudentId();
		
		String questionType = "fill"; //保存的是单选题的答案，所以对应的题目类型是fill
		String last = req.getParameter("last"); //上一题按钮
		String next = req.getParameter("next"); //下一题按钮		
		
		boolean result = false;
		if(null != listFill && listFill.size()>0){
			FillBlank f = new FillBlank();
			for(int i=0; i<listFill.size(); i++){
				f = listFill.get(i);
				String paperId = f.getPaperId();
				int questionNumber  = f.getQuestionNumber();
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
							
			}
			
			if(null != last)
				resp.sendRedirect("doJudge.jsp");
			else if(null != next)
				resp.sendRedirect("doEssay.jsp");
		
		}
		//如果填空题的题目为空，则直接跳转到下一种考题类型
		else if(null == listFill || listFill.size()==0){ 
			if(null != next)
				resp.sendRedirect("doEssay.jsp");
			else if(null != last)
				resp.sendRedirect("doJudge.jsp");
		}
		else{
			resp.sendRedirect("error.jsp");
		}
		
	}



}
