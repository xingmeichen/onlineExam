package onlineExam.controller.admit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.CSView;
import onlineExam.domain.po.Course;
import onlineExam.domain.po.Paper;
import onlineExam.persistent.CSMapper;
import onlineExam.persistent.CourseMapper;
import onlineExam.persistent.PaperMapper;

public class EditCourseController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		CourseMapper cdao = new CourseMapper();
		boolean result = false;
		int flag = 0;

		int id = Integer.parseInt(req.getParameter("id"));

		String courseId = req.getParameter("courseId");
		if (null != courseId && !courseId.equals("")) {
			result = cdao.updateCourseId(id, courseId);
			if (result)// 如果更改成功，则作一个标记
				flag++;
		}

		String courseName = req.getParameter("courseName");
		if (null != courseName && !courseName.equals("")) {
			result = cdao.updateCourseName(id, courseName);
			if (result) // 如果更改成功，则作一个标记
				flag++;
		}

		// 如果课程信息有改动，则更改session中的课程信息
		if (flag > 0) {
			// 查询所有课程信息，并将其保存到session中
			List<Course> listCourse = new ArrayList<Course>();
			listCourse = cdao.getCourse();
			req.getSession().setAttribute("listCourse", listCourse);

			// 因为course表与paper表有关，所以，同时也要修改session中的paper信息
			// 查询所有考卷信息，并将其保存到session中
			List<Paper> listPaper = new ArrayList<Paper>();
			PaperMapper pdao = new PaperMapper();
			listPaper = pdao.getPaper();
			req.getSession().setAttribute("listPaper", listPaper);

			// 因为course表与cs表有关，所以，同时也要修改session中的cs的值
			// 查询所有选课表的信息，并将其保存到session中
			/* 考生以及对应的考试信息 */
			List<CSView> listCS = new ArrayList<CSView>();
			CSMapper csdao = new CSMapper();
			listCS = csdao.getCSView();
			req.getSession().setAttribute("listCS", listCS);
		}

		resp.sendRedirect("courseInf.jsp");

	}
}
