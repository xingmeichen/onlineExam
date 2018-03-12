package onlineExam.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Student;
import onlineExam.domain.po.Teacher;
import onlineExam.persistent.StudentMapper;
import onlineExam.persistent.TeacherMapper;

/**
 * �û�ע��
 */
public class RegisterController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		String userId = req.getParameter("userId");
		String userName = req.getParameter("userName");
		String userPassword = req.getParameter("userPassword");
		String confirmPassword = req.getParameter("confirmPassword");
		String userType = req.getParameter("userType");

		StudentMapper sdao = new StudentMapper();
		Student s = new Student();
		TeacherMapper tdao = new TeacherMapper();
		Teacher t = new Teacher();

		boolean result = false;
		String message = "";

		// �����ж���������������Ƿ���ȷ
		if (null != userPassword && null != confirmPassword
				&& userPassword.equals(confirmPassword)) {

			// �������Ϊ��
			if (null != userType && null != userId && null != userName) {
				// ѧ���û�ע��
				if (userType.equals("student")) {
					// ���Ȳ��Ҹ��û��Ƿ��Ѿ�����
					s = sdao.getOneStudent(userId);
					if (null == s.getStudentId()) {// ����û�������
						result = sdao.insertStudent(userId, userPassword,
								userName);
						if (!result)
							message = "ע��ʧ��!���û��Ѿ�����";
					} else {
						message = "ע��ʧ�ܣ����û��Ѿ�����";
					}

				} // if(userType.equals("student"))
					// ��ʦ�û�ע��
				else if (userType.equals("teacher")) {
					// ���Ȳ��Ҹ��û��Ƿ��Ѿ�����
					t = tdao.getOneTeacher(userId);
					if (null == t.getTeacherId()) {
						result = tdao.insertTeacher(userId, userPassword,
								userName);
						if (!result)
							message = "ע��ʧ�ܣ����û��Ѿ�����";
					} else {
						message = "ע��ʧ�ܣ����û��Ѿ�����";
					}
				} // else if(userType.equals("teacher"))
			} // if(null != userType && null != userId && null != userName )
		}// if(null != userPassword && null != confirmPassword &&
			// userPassword.equals("confirmPassword") )

		else
			message = "ע��ʧ�ܣ�������������벻һ��";
		// ����������������һ��,���������������Ϊ�գ�

		if (!result) {
			req.getSession().setAttribute("message", message);
			resp.sendRedirect("error.jsp");
		} else if (result) {
			/* ע��ɹ�����Ҫ�޸�session�еĽ�ʦ����ѧ���û�����Ϣ */
			if (userType.equals("teacher")) {// ����������ǽ�ʦ�û�
				// ��ѯ���н�ʦ�û�����Ϣ�������䱣�浽session��
				List<Teacher> listTeacher = new ArrayList<Teacher>();
				listTeacher = tdao.getTeacher();
				req.getSession().setAttribute("listTeacher", listTeacher);
			} else if (userType.equals("student")) { // �����������ѧ���û�
				// ��ѯ����ѧ���û�����Ϣ�������䱣�浽session��
				List<Student> listStudent = new ArrayList<Student>();
				listStudent = sdao.getStudent();
				req.getSession().setAttribute("listStudent", listStudent);
			}

			req.getSession().setAttribute("message", "��ϲ��ע��ɹ�");

			resp.sendRedirect("ahome.jsp");
		}

	}
}
