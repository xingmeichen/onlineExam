package onlineExam.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Student;
import onlineExam.persistent.StudentMapper;

public class EditStudentInfController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		this.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		StudentMapper sdao = new StudentMapper();
		Student student = new Student();
		student = (Student) req.getSession().getAttribute("student");
		
		String studentId = student.getStudentId();
		String name = req.getParameter("name");
		if(null != name && !name.equals("")){
			//修改学生姓名
			sdao.updateStudentName(studentId, name);
		}
		
		String sex = req.getParameter("sex");
		if(null != sex && !sex.equals("")){
			//修改学生性别
			sdao.updateStudentSex(studentId, sex);
		}
		
		String dept = req.getParameter("dept");
		if(null != dept && !dept.equals("")){
			//修改学生所在院系
			sdao.updateStudentDept(studentId, dept);
		}
		
		String major = req.getParameter("major");
		if(null != major && !major.equals("")){
			//修改学生专业
			sdao.updateStudentMajor(studentId, major);
		}
		
		//页面跳转		
		student = sdao.getOneStudent(studentId);
		req.getSession().setAttribute("student", student);
		resp.sendRedirect("studentInf.jsp");
		
	}
}
