package onlineExam.controller.admit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Student;
import onlineExam.domain.po.Teacher;
import onlineExam.persistent.StudentMapper;
import onlineExam.persistent.TeacherMapper;

public class ShowEditController extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		this.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		String teacherId = req.getParameter("teacherId");
		String studentId = req.getParameter("studentId");
		if(teacherId != null){
			Teacher t = new Teacher();
			TeacherMapper tdao = new TeacherMapper();
			t = tdao.getOneTeacher(teacherId);
			req.getSession().setAttribute("oneTeacher", t);
			req.getSession().setAttribute("teacherId",teacherId);
			resp.sendRedirect("editInf.jsp");
		}
		else if(studentId != null){
			Student s = new Student();
			StudentMapper sdao = new StudentMapper();
			s = sdao.getOneStudent(studentId);
			req.getSession().setAttribute("oneStudent", s);
			req.getSession().setAttribute("studentId",studentId);
			resp.sendRedirect("editInf.jsp");
		}		
	}
}
