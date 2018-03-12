package onlineExam.controller.admit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.CSView;
import onlineExam.domain.po.Student;
import onlineExam.persistent.CSMapper;
import onlineExam.persistent.StudentMapper;

public class SelectCourseController extends HttpServlet {

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
		String studentId = req.getParameter("studentId");
//		System.out.println(studentId);
		
		boolean result = false;
		if(null != studentId && !studentId.equals("")){
			
			Student s = new Student();
			StudentMapper sdao = new StudentMapper();
			//查询该生在数据库中的信息
			s = sdao.getOneStudent(studentId);
			if(null != s.getStudentId()){ //如果该生在数据库中
				CSMapper csdao = new CSMapper();
				result = csdao.insertCS(courseId, studentId);
				if(result){ //如果选课成功
					//修改session中的选课表的信息
					/*考生以及对应的考试信息*/
					List<CSView> listCS = new ArrayList<CSView>();
					//查询所有选课表的信息，并将其保存到session中
					listCS = csdao.getCSView();
					req.getSession().setAttribute("listCS", listCS);
				}
			}
		}
		if(result){
			resp.sendRedirect("courseInf.jsp");
		}
		else {
			req.getSession().setAttribute("message", "选课失败，请确认该生是否存在或者其是否已经选了改门课程");
			resp.sendRedirect("inf.jsp");
		}
		

	}
}
