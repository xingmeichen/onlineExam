package onlineExam.controller.teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Grade;
import onlineExam.domain.po.GradeView;
import onlineExam.domain.po.Paper;
import onlineExam.domain.po.ResultView;
import onlineExam.domain.po.Student;
import onlineExam.persistent.GradeMapper;
import onlineExam.persistent.GradeViewMapper;
import onlineExam.persistent.ResultMapper;

public class SignMarkByTeacherController extends HttpServlet {

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
		String message = "";
		
		String studentId = req.getParameter("studentId");
		String paperId = req.getParameter("paperId");
		String squestionNumber = req.getParameter("questionNumber");
	    int questionNumber = Integer.parseInt(squestionNumber);
		int grade = 0;
		String sgrade = req.getParameter("grade");
		//需要判断输入的得分是否是纯数字
		
		//如果从页面上获取的得分不为空，并且是纯数字
		if(null !=sgrade && sgrade.matches("[0-9]+")){
			grade = Integer.parseInt(sgrade);
		}
		else
			message = message+"输入的得分错误！（注意得分的范围）";
		
		String smark = req.getParameter("mark"); //相应题目的分值
		int mark = Integer.parseInt(smark);
		
		boolean result = false;
		//如果从页面上获取的值都不为空，并且实际输入得分总是小于等于相应题目的分值
		if(null!=studentId && null!=paperId && null!=squestionNumber && null!=smark && grade<=mark){
			result = rdao.signMarkFromResultViewToResult(studentId, paperId, questionNumber, grade);
		}
		else
			message = message + "\n从页面上获取到错误的值！";
		
		/*如果批改成功，则重新修改session中的listResultView的值，并跳转页面*/
		if (result) {
			List<ResultView> listResultView = new ArrayList<ResultView>();
			List<ResultView> listResultView1 = new ArrayList<ResultView>();
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
			
			//如果某个学生的某套试卷已经全部批阅完毕，则将成绩统计并保存到grade表中
			if(rdao.ifFinishSignMark(studentId, paperId)){
				GradeMapper gdao = new GradeMapper();
				Grade g = new Grade(studentId,paperId);
				Grade g1 = new Grade(grade,studentId,paperId);
				gdao.saveGrade(g, g1);
				
				/**同时修改session中的grade信息
				 * */
				/*从session中获取listStudent*/
				List<Student> listStudent = new ArrayList<Student>();
				listStudent = (List<Student>) req.getSession().getAttribute("listStudent");
				
				/*从session中获取endExam*/
				List<Paper> endExam = new ArrayList<Paper>();
				endExam = (List<Paper>) req.getSession().getAttribute("endExam");
				
				/*获取成绩单的信息*/
				List<GradeView> listGrade = new ArrayList<GradeView>();
				GradeViewMapper gvdao = new GradeViewMapper();				
				listGrade = gvdao.getGradeView(listStudent, endExam);
				
				//将新更新的成绩单的信息放到session中
				req.getSession().setAttribute("listGrade", listGrade);	
				
			}
			//页面跳转
			resp.sendRedirect("resultDetail.jsp");
		}
		else{
			req.getSession().setAttribute("message", message);
			resp.sendRedirect("error.jsp");
		}

	}
}
