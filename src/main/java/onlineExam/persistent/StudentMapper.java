package onlineExam.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import onlineExam.common.DB;
import onlineExam.domain.po.Student;

public class StudentMapper {

	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	/**
	 * 学生所有信息查询
	 * */
	public List<Student> getStudent() {
		List<Student> list = new ArrayList<Student>();
		String sql = "select * from student";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setStudentDept(rs.getString("studentDept"));
				student.setStudentId(rs.getString("studentId"));
				student.setStudentMajor(rs.getString("studentMajor"));
				student.setStudentName(rs.getString("studentName"));
				student.setStudentPassword(rs.getString("studentPassword"));
				student.setStudentSex(rs.getString("studentSex"));
				list.add(student);
			}
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	/* 学生登录信息查询 */
	public Student queryLogin(String studentId,String studentPassword) {
        
		Student stu = new Student();		
		String sql = "select * from student where studentId=? and studentPassword=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,studentId);
			ps.setString(2, studentPassword);
			rs = ps.executeQuery();
			if(rs.next()) {
				stu.setId(rs.getInt("id"));
				stu.setStudentDept(rs.getString("studentDept"));
				stu.setStudentId(rs.getString("studentId"));
				stu.setStudentMajor(rs.getString("studentMajor"));
				stu.setStudentName(rs.getString("studentName"));
				stu.setStudentPassword(rs.getString("studentPassword"));
				stu.setStudentSex(rs.getString("studentSex"));
			}

			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
		return stu;
	}

	/**
	 * 根据学号studentId查询某个学生的基本信息
	 * 
	 * @throws SQLException
	 * */
	public Student getOneStudent(String studentId) {

		Student student = new Student();
		String sql = "select * from student where studentId=? ";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentId);
			rs = ps.executeQuery();
			if (rs.next()) { // 不能写成 rs!=null
				student.setId(rs.getInt("id"));
				student.setStudentDept(rs.getString("studentDept"));
				student.setStudentId(rs.getString("studentId"));
				student.setStudentMajor(rs.getString("studentMajor"));
				student.setStudentName(rs.getString("studentName"));
				student.setStudentPassword(rs.getString("studentPassword"));
				student.setStudentSex(rs.getString("studentSex"));
			}
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
		return student;
	}

	/**
	 * 查询某个学生某门课程的总成绩
	 * */
	public int queryMark(String studentId, String paperId) {
		int mark = 0;
		// 写sql语句不采用拼接的方式，而是采用占位符？的方式
		String sql = "select sum(mark) from result where studentId=? and paperId=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentId); // sql语句的占位符从1开始
			ps.setString(2, paperId);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("sum(mark)"));
				mark += rs.getInt("sum(mark)");
			}
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mark;

	}
	
	/**
	 * 修改学生学号（只有管理员有此权限）
	 * */
	public boolean updateStudentId(String studentId, String newstudentId){
		
		boolean result = false;
		String sql = "update student set studentId=? where studentId=?";
		Student student = new Student();
		student = getOneStudent(studentId); //首先查找该用户是否已经存在
		if(null == student.getStudentId()) //如果为空（即用户不存在），则修改失败
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, newstudentId);
			ps.setString(2, studentId);
			int i = ps.executeUpdate();
			if(i != 0)
				result = true;
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}
	
	/**
	 * 修改学生密码
	 * */
	public boolean updateStudentPassword(String studentId, String studentPassword){
		
		boolean result = false;
		String sql = "update student set studentPassword=? where studentId=?";
		Student student = new Student();
		student = getOneStudent(studentId);
		if(null == student.getStudentId())
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentPassword);
			ps.setString(2, studentId);
			int i = ps.executeUpdate();
			if(i != 0)
				result = true;
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 修改学生姓名
	 * */
	public boolean updateStudentName(String studentId, String studentName){
		
		boolean result = false;
		String sql = "update student set studentName=? where studentId=?";
		Student student = new Student();
		student = getOneStudent(studentId);
		if(null == student.getStudentId())
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentName);
			ps.setString(2, studentId);
			int i = ps.executeUpdate();
			if(i != 0)
				result = true;
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 修改学生性别
	 * */
	public boolean updateStudentSex(String studentId, String studentSex){
		
		boolean result = false;
		String sql = "update student set studentSex=? where studentId=?";
		Student student = new Student();
		student = getOneStudent(studentId);
		if(null == student.getStudentId())
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentSex);
			ps.setString(2, studentId);
			int i = ps.executeUpdate();
			if(i != 0)
				result = true;
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 修改学生院系
	 * */
	public boolean updateStudentDept(String studentId, String studentDept){
		
		boolean result = false;
		String sql = "update student set studentDept=? where studentId=?";
		Student student = new Student();
		student = getOneStudent(studentId);
		if(null == student.getStudentId())
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentDept);
			ps.setString(2, studentId);
			int i = ps.executeUpdate();
			if(i != 0)
				result = true;
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 修改学生专业
	 * */
	public boolean updateStudentMajor(String studentId, String studentMajor){
		
		boolean result = false;
		String sql = "update student set studentMajor=? where studentId=?";
		Student student = new Student();
		student = getOneStudent(studentId);
		if(null == student.getStudentId())
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentMajor);
			ps.setString(2, studentId);
			int i = ps.executeUpdate();
			if(i != 0)
				result = true;
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 将一个学生用户的信息保存到数据库中（学生用户注册时用到）
	 * */
	public boolean insertStudent(String studentId,String studentPassword,String studentName){
		
		boolean result = false;
		Student student = new Student();	
		student = getOneStudent(studentId);		
		if(null != student.getStudentId()) //如果用户已经存在 ，则插入失败
			return false;
		String sql = "insert into student(studentId,studentPassword,studentName) "
				+ " values(?,?,?)";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentId);
			ps.setString(2, studentPassword);
			ps.setString(3, studentName);
			int i = ps.executeUpdate();
			if(i != 0)
				result = true;
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void main(String[] args) throws SQLException {

		StudentMapper sdao = new StudentMapper();
		boolean result = sdao.insertStudent("s6","s12345","蓝");
		result = sdao.updateStudentSex("s6", "男");
		System.out.println(result);
	}

}
