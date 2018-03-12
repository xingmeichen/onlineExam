package onlineExam.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import onlineExam.common.DB;
import onlineExam.domain.po.Teacher;

public class TeacherMapper {
	
	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	/**
	 * 查询教师所有信息
	 * */
	public List<Teacher> getTeacher() {
		List<Teacher> list = new ArrayList<Teacher>();
		String sql = "select * from teacher";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setTeacherId(rs.getString("teacherId"));
				teacher.setTeacherPassword(rs.getString("teacherPassword"));
				teacher.setTeacherPost(rs.getString("teacherPost"));
				teacher.setTeacherSex(rs.getString("teacherSex"));
				teacher.setTeacherDept(rs.getString("teacherDept"));
				teacher.setTeacherName(rs.getString("teacherName"));
				list.add(teacher);
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
		return list;
	}
	
	public Teacher queryLogin(String teacherId,String teacherPassword ) {
		Teacher teacher = new Teacher();
		String sql = "select * from teacher where teacherId=? and teacherPassword=?";		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, teacherId);
			ps.setString(2, teacherPassword);
			rs = ps.executeQuery();
			if(rs.next()){				
				teacher.setTeacherId(rs.getString("teacherId"));
				teacher.setTeacherPassword(rs.getString("teacherPassword"));			
				teacher.setId(rs.getInt("id"));
				teacher.setTeacherDept(rs.getString("teacherDept"));
				teacher.setTeacherName(rs.getString("teacherName"));
				teacher.setTeacherPost(rs.getString("teacherPost"));
				teacher.setTeacherSex(rs.getString("teacherSex"));
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
		return teacher;
	}
	
	/**
	 * 根据教师的编号teacherId查询教师的基本信息
	 *
	 * */
	public Teacher getOneTeacher(String teacherId) {
		
		Teacher teacher = new Teacher();
		String sql = "select * from teacher where teacherId=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, teacherId);
			rs = ps.executeQuery();
			if(rs.next()){
				teacher.setId(rs.getInt("id"));
				teacher.setTeacherId(rs.getString("teacherId"));
				teacher.setTeacherPassword(rs.getString("teacherPassword"));
				teacher.setTeacherPost(rs.getString("teacherPost"));
				teacher.setTeacherSex(rs.getString("teacherSex"));
				teacher.setTeacherDept(rs.getString("teacherDept"));
				teacher.setTeacherName(rs.getString("teacherName"));
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
		return teacher;
	}
	
	
	/**
	 * 修改教师编号
	 * */
	public boolean updateTeacherId(String teacherId,String newTeacherId){
		boolean result = false;
		String sql = "update teacher set teacherId = ? where teacherId=?";
		Teacher t = new Teacher();
		t = getOneTeacher(teacherId);
		if(null == t.getTeacherId()) //如果该教师编号不存在，则修改失败
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, newTeacherId);
			ps.setString(2, teacherId);
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
	 * 修改教师登录密码
	 * */
	public boolean updateTeacherPassword(String teacherId, String teacherPassword){
		
		boolean result = false;
		String sql = "update teacher set teacherPassword=? where teacherId=?";
		Teacher teacher = new Teacher();
		teacher= getOneTeacher(teacherId);
		if(null == teacher.getTeacherId())//如果为空，修改失败
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, teacherPassword);
			ps.setString(2, teacherId);
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
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 修改教师姓名
	 * */
	public boolean updateTeacherName(String teacherId, String teacherName){
		
		boolean result = false;
		String sql = "update teacher set teacherName=? where teacherId=?";
		Teacher teacher = new Teacher();
		teacher= getOneTeacher(teacherId);
		if(null == teacher.getTeacherId()) //如果用户不存在，则修改失败
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, teacherName);
			ps.setString(2, teacherId);
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
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 修改教师性别
	 * */
	public boolean updateTeacherSex(String teacherId, String teacherSex){
		
		boolean result = false;
		String sql = "update teacher set teacherSex=? where teacherId=?";
		Teacher teacher = new Teacher();
		teacher= getOneTeacher(teacherId);
		if(null == teacher.getTeacherId()) //如果用户不存在，则修改失败
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, teacherSex);
			ps.setString(2, teacherId);
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
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 修改教师所在院系
	 * */
	public boolean updateTeacherDept(String teacherId, String teacherDept){
		
		boolean result = false;
		String sql = "update teacher set teacherDept=? where teacherId=?";
		Teacher teacher = new Teacher();
		teacher= getOneTeacher(teacherId);
		if(null == teacher.getTeacherId()) //如果用户不存在，则修改失败
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, teacherDept);
			ps.setString(2, teacherId);
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
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 修改教师职务
	 * */
	public boolean updateTeacherPost(String teacherId, String teacherPost){
		
		boolean result = false;
		String sql = "update teacher set teacherPost=? where teacherId=?";
		Teacher teacher = new Teacher();
		teacher= getOneTeacher(teacherId);
		if(null == teacher.getTeacherId()) //如果用户不存在，则修改失败
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, teacherPost);
			ps.setString(2, teacherId);
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
		
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean insertTeacher(String teacherId,String teacherPassword,String teacherName){
		
		boolean result = false;
		Teacher t = new Teacher();
		t = getOneTeacher(teacherId);
		if(null != t.getTeacherId()) //如果用户已经存在，则插入失败
			return false;
		String sql = "insert into teacher(teacherId,teacherPassword,teacherName) "
				+ "values(?,?,?)";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, teacherId);
			ps.setString(2, teacherPassword);
			ps.setString(3, teacherName);
			int i = ps.executeUpdate();
			if(i != 0) //如果插入成功
				result = true; //则result的值改为true
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

		TeacherMapper tdao = new TeacherMapper();
		Teacher t = new Teacher();
		t = tdao.getOneTeacher("t3");
		boolean result = tdao.insertTeacher("t4", "t2884jfoo","郝");
		System.out.println(result);
		result = tdao.updateTeacherPassword("t4", "t12345");
		tdao.updateTeacherDept("t4", "计算机学院");
		tdao.updateTeacherPost("t4", "教授");
		tdao.updateTeacherSex("t4", "女");
		System.out.println(t.getTeacherId());
	}

}
