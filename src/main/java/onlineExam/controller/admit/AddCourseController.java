package onlineExam.controller.admit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Course;
import onlineExam.persistent.CourseMapper;

public class AddCourseController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		this.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		String courseId = req.getParameter("courseId");
		String courseName = req.getParameter("courseName");
		
		CourseMapper cdao = new CourseMapper();
		
		boolean result = false;
		
		if(null != courseId && !courseId.equals("") &&
				null != courseName && !courseName.equals("")){
			result = cdao.inserCourse(courseId, courseName);
			
		}
		if(result){
			/*如果添加课程成功，则需要修改session中课程信息的值*/
			//查询所有课程信息，并将其保存到session中
			List<Course> listCourse = new ArrayList<Course>();
			listCourse = cdao.getCourse();
			req.getSession().setAttribute("listCourse", listCourse);
			resp.sendRedirect("courseInf.jsp");
		}
		else{
			req.getSession().setAttribute("message", "添加课程失败，请确认课程号是否已经存在！");
			resp.sendRedirect("inf.jsp");
		}
		
		
	}
}
