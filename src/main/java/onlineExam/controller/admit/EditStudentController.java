package onlineExam.controller.admit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Student;
import onlineExam.persistent.StudentMapper;
import onlineExam.service.impl.StudentServiceImpl;


public class EditStudentController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		this.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		StudentServiceImpl saction = new StudentServiceImpl();
		String message = "";
		boolean result = false;				
        
		String studentId = req.getParameter("studentId");
		System.out.println(studentId);
		
		String studentPassword = req.getParameter("studentPassword");
		
		if( null != studentPassword && !studentPassword.equals("")){
			result = saction.updateStudentPassword(studentId, studentPassword);
			if(!result)//如果修改失败，修改message的值	
				message = message + "修改密码失败！";
		}
			
		String studentName = req.getParameter("studentName");
		if(null != studentName && !studentName.equals("")){
			result = saction.updateStudentName(studentId, studentName);
			if(!result)//如果修改失败，修改message的值	
				message = message + "修改姓名失败！";
		}
		
		String studentSex = req.getParameter("studentSex");
		if(null != studentSex && !studentSex.equals("")){
			result = saction.updateStudentSex(studentId, studentSex);
			if(!result)//如果修改失败，修改message的值	
				message = message + "修改性别失败！";
		}
		
		String studentDept = req.getParameter("studentDept");
		if(null != studentDept && !studentDept.equals("")){
			result = saction.updateStudentDept(studentId, studentDept);
			if(!result)//如果修改失败，修改message的值	
				message = message + "修改院系失败！";
		}
		
		String studentMajor = req.getParameter("studentMajor");
		if(null != studentMajor && !studentMajor.equals("")){
			result = saction.updateStudentMajor(studentId, studentMajor);
			if(!result)//如果修改失败，修改message的值	
				message = message + "修改专业失败！";
		}	
		//最后再修改学号
		String newstudentId = req.getParameter("newstudentId");		
		if(null != newstudentId && !newstudentId.equals("")){
			result = saction.updateStudentId(studentId, newstudentId);
			if(!result)//如果修改失败，修改message的值	
				message = message + "修改学号失败！";
		}
		
		//页面跳转
		if(!(message.equals(""))){
			req.getSession().setAttribute("message", message);
			//如果修改学生信息可能部分成功，则需要重新查询学生列表的基本信息，以便页面上显示的信息能够与数据库中的数据保存一致
			List<Student> listStudent = new ArrayList<Student>();
			StudentMapper sdao = new StudentMapper();
			listStudent = sdao.getStudent();
			req.getSession().setAttribute("listStudent", listStudent );
			resp.sendRedirect("inf.jsp");
		}
		
		else{ //如果修改学生信息成功，则需要重新查询学生列表的基本信息，以便页面上显示的信息能够与数据库中的数据保存一致
			List<Student> listStudent = new ArrayList<Student>();
			StudentMapper sdao = new StudentMapper();
			listStudent = sdao.getStudent();
			req.getSession().setAttribute("listStudent", listStudent );
			resp.sendRedirect("studentInf.jsp");
		}
		
	}
}
