package onlineExam.controller.admit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Teacher;
import onlineExam.persistent.TeacherMapper;
import onlineExam.service.impl.TeacherServiceImpl;

public class EditTeacherController extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		this.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		TeacherServiceImpl taction = new TeacherServiceImpl();
		String message = "";
		boolean result = false;
		
		String teacherId = req.getParameter("teacherId");
		
		String teacherPassword = req.getParameter("teacherPassword");
		if(null != teacherPassword && !teacherPassword.equals("")){
			result = taction.updateTeacherPassword(teacherId, teacherPassword);
			if(!result) //如果修改失败，修改message的值				
				message = message + "修改密码失败！";
		}
			
		String teacherName = req.getParameter("teacherName");
		if(null != teacherName && !teacherName.equals("")){
			result = taction.updateTeacherName(teacherId, teacherName);
			if(!result) //如果修改失败，修改message的值				
				message = message + "修改姓名失败！";
		}
		
		String teacherSex = req.getParameter("teacherSex");
		if(null != teacherSex && !teacherSex.equals("")){
			result = taction.updateTeacherSex(teacherId, teacherSex);
			if(!result)//如果修改失败，修改message的值					
				message = message + "修改性别失败！";
		}
		
		String teacherDept = req.getParameter("teacherDept");
		if(null != teacherDept && !teacherDept.equals("")){
			result = taction.updateTeacherDept(teacherId, teacherDept);
			if(!result)//如果修改失败，修改message的值					
				message = message + "修改院系失败！";
		}
		
		String teacherPost = req.getParameter("teacherPost");
		if(null != teacherPost && !teacherPost.equals("")){
			result = taction.updateTeacherPost(teacherId, teacherPost);
			if(!result)//如果修改失败，修改message的值				
				message = message + "修改职务失败！";
		}
		
		//修改教师编号
		String newTeacherId = req.getParameter("newteacherId");
		if(null != newTeacherId && !newTeacherId.equals("")){
			result = taction.updateTeacherId(teacherId, newTeacherId);
			if(!result)//如果修改失败，修改message的值					
				message = message + "修改教师编号失败！";
		}
		
		//页面跳转
		if(!message.equals("")){
			req.getSession().setAttribute("message", message);
			//如果修改教师信息可能部分成功，则需要重新查询教师列表的基本信息，以便页面上显示的信息能够与数据库中的数据保存一致
			List<Teacher> listTeacher = new ArrayList<Teacher>();
			TeacherMapper tdao = new TeacherMapper();
			listTeacher = tdao.getTeacher();
			req.getSession().setAttribute("listTeacher", listTeacher);
			resp.sendRedirect("inf.jsp");
		}
		else{
			//如果修改教师信息成功，则需要重新查询教师列表的基本信息，以便页面上显示的信息能够与数据库中的数据保存一致
			List<Teacher> listTeacher = new ArrayList<Teacher>();
			TeacherMapper tdao = new TeacherMapper();
			listTeacher = tdao.getTeacher();
			req.getSession().setAttribute("listTeacher", listTeacher);
			resp.sendRedirect("teacherInf.jsp");
		}
	}
	
	
}
