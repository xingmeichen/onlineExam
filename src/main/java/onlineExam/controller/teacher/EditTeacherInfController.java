package onlineExam.controller.teacher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Teacher;
import onlineExam.persistent.TeacherMapper;

public class EditTeacherInfController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		this.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		TeacherMapper tdao = new TeacherMapper();
		Teacher teacher = new Teacher();
		teacher = (Teacher) req.getSession().getAttribute("teacher");
		String teacherId = teacher.getTeacherId();
		
		String name = req.getParameter("name");
		if(null != name && !name.equals("")){
			//修改教师姓名
			tdao.updateTeacherName(teacherId, name);
		}
		
		String sex = req.getParameter("sex");
		if(null != sex && !sex.equals("")){
			//修改教师性别
			tdao.updateTeacherSex(teacherId, sex);
		}
		
		String dept = req.getParameter("dept");
		if(null != dept && !dept.equals("")){
			//修改教师所在院系
			tdao.updateTeacherDept(teacherId, dept);
		}
		
		String post = req.getParameter("post");
		if(null != post && !post.equals("")){
			//修改教师职务
			tdao.updateTeacherPost(teacherId, post);
		}
		
		teacher = tdao.getOneTeacher(teacherId);
		req.getSession().setAttribute("teacher", teacher);
		resp.sendRedirect("teacherInf.jsp");
	}
}
