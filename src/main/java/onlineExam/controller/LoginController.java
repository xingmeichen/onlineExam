package onlineExam.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Admin;
import onlineExam.domain.po.CSView;
import onlineExam.domain.po.Course;
import onlineExam.domain.po.GradeView;
import onlineExam.domain.po.Paper;
import onlineExam.domain.po.Student;
import onlineExam.domain.po.Teacher;
import onlineExam.persistent.AdminMapper;
import onlineExam.persistent.CSMapper;
import onlineExam.persistent.CourseMapper;
import onlineExam.persistent.GradeViewMapper;
import onlineExam.persistent.PaperMapper;
import onlineExam.persistent.StudentMapper;
import onlineExam.persistent.TeacherMapper;

/**
 * 这是一个登录servlet，它的功能是实现用户的登录 具体功能如下：
 * 管理员用户登录：用户名和密码，已经用户类型匹配后，查询教师其权限范围内的所有信息：（信息可以通过首页的导航栏查看）
 * 教师用户登录：用户名和密码，已经用户类型匹配后，查询教师其权限范围内的所有信息：（信息可以通过首页的导航栏查看）
 * 学生用户登录：用户名和密码，已经用户类型匹配后，查询学生用户其权限范围内的所有信息：（信息可以通过首页的导航栏查看）
 * */
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PaperMapper pdao = new PaperMapper();
		CourseMapper cdao = new CourseMapper();
		StudentMapper sdao = new StudentMapper();
		TeacherMapper tdao = new TeacherMapper();
		AdminMapper adao = new AdminMapper();
		CSMapper csdao = new CSMapper();
		GradeViewMapper gvdao = new GradeViewMapper();

		/* 获取考试中、尚未开始、考试结束等三种状态的考试信息 */
		List<Paper> currentExam = new ArrayList<Paper>();

		List<Paper> notStartExam = new ArrayList<Paper>();

		List<Paper> endExam = new ArrayList<Paper>();

		/* 获取成绩单的信息 */
		List<GradeView> listGrade = new ArrayList<GradeView>();

		/* 考生以及对应的考试信息 */
		List<CSView> listCS = new ArrayList<CSView>();

		// 设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		/* 获取jsp页面上的值 */
		// 用户名
		String userId = req.getParameter("userId");
		// 用户密码
		String userPassword = req.getParameter("userPassword");
		// 用户类型
		String userType = req.getParameter("userType");

		/* 首先就将用户类型保存到session中,不管接下来登录成功与否 * */
		if (userType != null)
			req.getSession().setAttribute("userType", userType);
		// 学生登录
		if (userType.equals("student")) {
			Student student = new Student();
			student = sdao.queryLogin(userId, userPassword);
			// 如果查询到的用户不为空
			if (null != student.getStudentId()) {
				// 将学生用户的信息保存到session中
				req.getSession().setAttribute("student", student); // 灏嗗鐢熺敤鎴风殑淇℃伅淇濆瓨鍒皊ession涓�
				// 获取学生的学号
				String studentId = student.getStudentId(); // 鑾峰彇瀛︾敓鐨勫鍙�
				// 通过学生学号查询该生的当前考试信息，并保存到session中
				currentExam = pdao.getCurrenExamOfStudent(studentId);
				req.getSession().setAttribute("currentExam", currentExam);

				// 通过学生学号查询该生的尚未开始的考试的信息，并保存到session中
				notStartExam = pdao.getNotStartExamOfStudent(studentId);
				req.getSession().setAttribute("notStartExam", notStartExam);

				// 通过学生学号查询该生的已经结束的考试的信息，并保存到session中 endExam =
				// pdao.getEndExamOfStudent(studentId);
				req.getSession().setAttribute("endExam", endExam);

				// 通过学生学号查询该生的成绩，注意只能查询到已经结束的考试的成绩，并保存到session中
				listGrade = gvdao.getGradeViewOfStudent(student.getStudentId(),
						endExam);
				req.getSession().setAttribute("listGrade", listGrade);

				// 查询学生的选课表，并将其保存到session中
				listCS = csdao.getCSViewByStudent(studentId);
				req.getSession().setAttribute("listCS", listCS);

				// 跳转到学生用户的主页
				resp.sendRedirect("student/shome.jsp");
			} else {
				req.getSession().setAttribute("message",
						"瀵逛笉璧凤紝鎮ㄧ殑鐢ㄦ埛鍚嶆垨瀵嗙爜鏈夎\n鎴栬�呯敤鎴峰悕涓庣敤鎴风被鍨嬩笉涓�鑷达紒");
				resp.sendRedirect("error.jsp");
			}
		} else if (userType.equals("teacher")) {
			// 教师登录
			Teacher teacher = new Teacher();
			teacher = tdao.queryLogin(userId, userPassword);
			if (null != teacher.getTeacherId()) {
				// 将教师信息保存到session中
				req.getSession().setAttribute("teacher", teacher);
				// 获取教师编号
				String teacherId = teacher.getTeacherId();

				// 通过教师编号查询当前考试的考试列表，并将其保存到session中
				currentExam = pdao.getCurrenExamOfTeacher(teacherId);
				req.getSession().setAttribute("currentExam", currentExam);

				// 通过教师编号查询尚未开始的考试的考试列表，并将其保存到session中
				notStartExam = pdao.getNotStartExamOfTeacher(teacherId);
				req.getSession().setAttribute("notStartExam", notStartExam);

				// 通过教师编号查询已经结束的考试的考试列表，并将其保存到session中
				endExam = pdao.getEndExamOfTeacher(teacherId);
				req.getSession().setAttribute("endExam", endExam);

				// 通过教师编号查询学生的选课信息，并将其保存到session中
				listCS = csdao.getCSViewByTeacher(teacherId);
				req.getSession().setAttribute("listCS", listCS);

				/*
				 * 教师用户要查询所有修了自己的课程的学生的成绩， 首先需要得到所有考生列表，该列表可以通过listCS，即选课表得到
				 */
				List<Student> listStudent = new ArrayList<Student>();
				if (null != listCS) {
					CSView cs = new CSView();
					for (int j = 0; j < listCS.size(); j++) {
						cs = listCS.get(j);
						Student stu = new Student();
						String studentId = cs.getStudentId();
						stu.setStudentId(studentId);
						listStudent.add(stu);
					}
				}
				/* 得到了所有考生列表，从而可以查询到成绩单 */
				listGrade = gvdao.getGradeView(listStudent, endExam);
				req.getSession().setAttribute("listGrade", listGrade);

				// 跳转到教师用户的主页
				resp.sendRedirect("teacher/thome.jsp");
			} else {
				req.getSession().setAttribute("message",
						"对不起，您的用户名或密码有误，或者用户名与用户类型不一致！");
				resp.sendRedirect("error.jsp");
			}
		} else if (userType.equals("admin")) {
			// 管理员登录
			Admin admin = new Admin();
			admin = adao.queryLogin(userId, userPassword);
			if (null != admin.getAdminId()) {
				// 将管理员用户信息保存到session中
				req.getSession().setAttribute("admin", admin);

				// 查询当前考试的信息，并将其保存到session中
				currentExam = pdao.getCurrenExam();
				req.getSession().setAttribute("currentExam", currentExam);

				// 查询尚未开始的考试的信息，并将其保存到session中
				notStartExam = pdao.getNotStartExam();
				req.getSession().setAttribute("notStartExam", notStartExam);

				// 查询已经结束的考试的信息，并将其保存到session中
				endExam = pdao.getEndExam();
				req.getSession().setAttribute("endExam", endExam);

				// 查询所有教师用户的信息，并将其保存到session中
				List<Teacher> listTeacher = new ArrayList<Teacher>();
				listTeacher = tdao.getTeacher();
				req.getSession().setAttribute("listTeacher", listTeacher);

				// 查询所有学生用户的信息，并将其保存到session中
				List<Student> listStudent = new ArrayList<Student>();
				listStudent = sdao.getStudent();
				req.getSession().setAttribute("listStudent", listStudent);

				// 查询所有考卷信息，并将其保存到session中
				List<Paper> listPaper = new ArrayList<Paper>();
				listPaper = pdao.getPaper();
				req.getSession().setAttribute("listPaper", listPaper);

				// 查询所有课程信息，并将其保存到session中
				List<Course> listCourse = new ArrayList<Course>();
				listCourse = cdao.getCourse();
				req.getSession().setAttribute("listCourse", listCourse);

				// 查询所有成绩单，并将其保存到session中，注意只有已经结束的考试才能够查询到
				listGrade = gvdao.getGradeView(listStudent, endExam);
				req.getSession().setAttribute("listGrade", listGrade);

				// 查询所有选课表的信息，并将其保存到session中
				listCS = csdao.getCSView();
				req.getSession().setAttribute("listCS", listCS);

				// 跳转到管理员用户主页
				resp.sendRedirect("admin/ahome.jsp");
			} else {
				// 如果登陆失败
				req.getSession().setAttribute("message",
						"对不起，您的用户名或密码有误，或者用户名与用户类型不一致！");
				resp.sendRedirect("error.jsp");
			}
		}
	}

}
