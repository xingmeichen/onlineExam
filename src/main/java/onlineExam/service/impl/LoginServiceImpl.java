package onlineExam.service.impl;

import javax.annotation.Resource;

import onlineExam.domain.po.Admin;
import onlineExam.domain.po.Student;
import onlineExam.domain.po.Teacher;
import onlineExam.persistent.AdminMapper;
import onlineExam.persistent.StudentMapper;
import onlineExam.persistent.TeacherMapper;
import onlineExam.service.ILoginService;

public class LoginServiceImpl implements ILoginService{
	
	
	/**
	 * teacherMapper *
	 */
	@Resource
	private TeacherMapper teacherMapper;
	
	/**
	 * studentMapper *
	 */
	@Resource
	private StudentMapper studentMapper;
	
	/**
	 * adminMapper *
	 */
	@Resource
	private AdminMapper adminMapper;
	
	/**
	 * 学生登录系统
	 * */
	@Override
	public Student loginStudent(String userId,String userPassword){
		
		Student student = new Student();		
		student = studentMapper.getOneStudent(userId);
		if(userId.equals(student.getStudentId()) && userPassword.equals(student.getStudentPassword()))
			return student;		
		else return null;
	}
	
	/**
	 * 教师登录系统
	 * */
	@Override
	public Teacher loginTeacher(String userId, String userPassword) {

		Teacher teacher = new Teacher();
		teacher = teacherMapper.getOneTeacher(userId);
		/*用户名和密码一致*/
		if (userId.equals(teacher.getTeacherId()) 
				&& userPassword.equals(teacher.getTeacherPassword())) {
			return teacher;
		}
		return teacher;
	}

	/**
	 * 管理员登录系统
	 * */
	public Admin loginAdmin(String userId, String userPassword) {

		Admin admin = new Admin();
		admin = adminMapper.getOneAdmin(userId);
        /*用户名和密码一致*/
		if (userId.equals(admin.getAdminId())
				&& userPassword.equals(admin.getAdminPassword())) {
			return admin;
		}
		return admin;
	}
	
	public static void main(String[] args) {
		
		LoginServiceImpl laction = new LoginServiceImpl();
		Student student = new Student();
		System.out.println(student);
		System.out.println(laction);
		student = laction.loginStudent("878", "9239898");
		System.out.println(student.getStudentDept());
		System.out.println("hello");
		
	}

}
