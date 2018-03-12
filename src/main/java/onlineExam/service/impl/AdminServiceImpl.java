package onlineExam.service.impl;

import java.util.ArrayList;
import java.util.List;

import onlineExam.domain.po.Student;
import onlineExam.domain.po.Teacher;
import onlineExam.persistent.StudentMapper;
import onlineExam.persistent.TeacherMapper;


public class AdminServiceImpl {
	
	public List<Teacher> getListTeacher(){
		
		List<Teacher> list = new ArrayList<Teacher>();
		TeacherMapper tdao = new TeacherMapper();
		list = tdao.getTeacher();
		return list;
	}
	
	public List<Student> getListStudent(){
		
		List<Student> list = new ArrayList<Student>();
		StudentMapper sdao = new StudentMapper();
		list = sdao.getStudent();
		return list;
	}
}
