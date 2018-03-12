package onlineExam.controller.teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Paper;
import onlineExam.domain.po.Teacher;
import onlineExam.service.impl.TeacherServiceImpl;

@SuppressWarnings("serial")
public class PaperListOfTeacherController extends HttpServlet {


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		this.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");		
		
		Teacher teacher = (Teacher)req.getSession().getAttribute("teacher");
		String paperMaker = teacher.getTeacherId();
		
		List<Paper> listPaperOfTeacher = new ArrayList<Paper>();
		TeacherServiceImpl taction = new TeacherServiceImpl();
		listPaperOfTeacher = taction.getListPaperByPaperMaker(paperMaker);
		req.getSession().setAttribute("listPaperOfTeacher", listPaperOfTeacher);
		resp.sendRedirect("paperListOfTeacher.jsp");
						
	}
}
