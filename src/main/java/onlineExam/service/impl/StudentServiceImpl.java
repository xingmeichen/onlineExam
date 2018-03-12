package onlineExam.service.impl;

import onlineExam.domain.po.Student;
import onlineExam.persistent.StudentMapper;

public class StudentServiceImpl {
	
	public Student getOneStudentByStudentId(String studentId){
		
		Student student = new Student();
		StudentMapper sdao = new StudentMapper();
		student = sdao.getOneStudent(studentId);
		return student;
	}
	
	/*
	 * 修改学号
	 * 修改成功返回true
	 * 否则返回false*/
	public boolean updateStudentId(String studentId, String newstudentId){
		
		StudentMapper sdao = new StudentMapper();
		return sdao.updateStudentId(studentId, newstudentId);
	}
	
	/*
	 * 修改学生密码，
	 * 修改成功则返回true,
	 * 否则返回false*/
	public boolean updateStudentPassword(String studentId,String studentPassword){
		
		StudentMapper sdao = new StudentMapper();
		return sdao.updateStudentPassword(studentId, studentPassword);
	}
	
	/*
	 * 修改学生姓名，
	 * 修改成功则返回true,
	 * 否则返回false*/
	public boolean updateStudentName(String studentId,String studentName){
		
		StudentMapper sdao = new StudentMapper();
		return sdao.updateStudentName(studentId, studentName);
	}
	
	
	/*
	 * 修改学生性别，
	 * 修改成功则返回true,
	 * 否则返回false*/
	public boolean updateStudentSex(String studentId,String studentSex){
		
		StudentMapper sdao = new StudentMapper();
		return sdao.updateStudentSex(studentId, studentSex);
	}
	
	
	/*
	 * 修改学生院系，
	 * 修改成功则返回true,
	 * 否则返回false*/
	public boolean updateStudentDept(String studentId,String studentDept){
		
		StudentMapper sdao = new StudentMapper();
		return sdao.updateStudentDept(studentId, studentDept);
	}
	
	/*
	 * 修改学生院系，
	 * 修改成功则返回true,
	 * 否则返回false*/
	public boolean updateStudentMajor(String studentId,String studentMajor){
		
		StudentMapper sdao = new StudentMapper();
		return sdao.updateStudentMajor(studentId, studentMajor);
	}
	
	
	//


}
