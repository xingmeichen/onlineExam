package onlineExam.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import onlineExam.domain.po.Student;
import onlineExam.domain.po.Teacher;
import onlineExam.persistent.StudentMapper;
import onlineExam.persistent.TeacherMapper;
import onlineExam.service.IAdminService;

public class AdminServiceImpl implements IAdminService {
	
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
	
	/***
	 * Description: 查询教师信息列表</br>
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public List<Teacher> getListTeacher(){
		
		List<Teacher> list = new ArrayList<Teacher>();		
		list = teacherMapper.getTeacher();
		return list;
	}
	
	/***
	 * Description: 查询学生信息列表</br>
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public List<Student> getListStudent(){		
		List<Student> list = new ArrayList<Student>();
		list = studentMapper.getStudent();
		return list;
	}
	
}
