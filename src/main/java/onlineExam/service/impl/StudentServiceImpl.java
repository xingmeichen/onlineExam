package onlineExam.service.impl;

import javax.annotation.Resource;

import onlineExam.domain.po.Student;
import onlineExam.persistent.StudentMapper;
import onlineExam.service.IStudentService;

public class StudentServiceImpl implements IStudentService {

	/**
	 * studentMapper *
	 */
	@Resource
	private StudentMapper studentMapper;
	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public Student getOneStudentByStudentId(String studentId) {

		Student student = new Student();
		student = studentMapper.getOneStudent(studentId);
		return student;
	}

	/***
	 * Description: 修改学号 修改成功返回true 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean updateStudentId(String studentId, String newstudentId) {

		return studentMapper.updateStudentId(studentId, newstudentId);
	}

	/***
	 * Description: 修改学生密码， 修改成功则返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean updateStudentPassword(String studentId,
			String studentPassword) {
		return studentMapper.updateStudentPassword(studentId, studentPassword);
	}

	/***
	 * Description: 修改学生姓名， 修改成功则返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean updateStudentName(String studentId, String studentName) {
		return studentMapper.updateStudentName(studentId, studentName);
	}

	/***
	 * Description: 修改学生性别， 修改成功则返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean updateStudentSex(String studentId, String studentSex) {
		return studentMapper.updateStudentSex(studentId, studentSex);
	}

	/***
	 * Description: 修改学生院系， 修改成功则返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean updateStudentDept(String studentId, String studentDept) {
		return studentMapper.updateStudentDept(studentId, studentDept);
	}

	/***
	 * Description: 修改学生院系， 修改成功则返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean updateStudentMajor(String studentId, String studentMajor) {
		return studentMapper.updateStudentMajor(studentId, studentMajor);
	}
}
