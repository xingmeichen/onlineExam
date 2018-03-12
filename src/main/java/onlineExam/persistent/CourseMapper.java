package onlineExam.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import onlineExam.common.DB;
import onlineExam.domain.po.Course;

public class CourseMapper {

	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	public List<Course> getCourse() {

		List<Course> list = new ArrayList<Course>();

		String sql = "select * from course order by id ";

		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Course c = new Course();
				c.setCourseId(rs.getString("courseId"));
				c.setCourseName(rs.getString("courseName"));
				c.setId(rs.getInt("id"));
				list.add(c);
			}
			if (null != rs)
				rs.close();
			if (null != ps)
				ps.close();
			if (null != con)
				con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 通过id号查询课程信息
	 * */
	public Course getCourseById(int id) {

		Course c = new Course();
		String sql = "select * from course where id=?";

		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				c.setCourseId(rs.getString("courseId"));
				c.setCourseName(rs.getString("courseName"));
				c.setId(rs.getInt("id"));
			}
			if (null != rs)
				rs.close();
			if (null != ps)
				ps.close();
			if (null != con)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * 更改课程编号
	 * */
	public boolean updateCourseId(int id, String courseId) {

		boolean result = false;
		Course c = new Course();
		c = getCourseById(id);
		if (null == (Integer) c.getId()) // 如果该记录不存在，则更改失败
			return false;

		String sql = "update course set courseId=? where id=? ";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, courseId);
			ps.setInt(2, id);
			int i = ps.executeUpdate();
			if (i > 0)
				result = true;

			if (null != ps)
				ps.close();
			if (null != con)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 更改课程名称
	 * */
	public boolean updateCourseName(int id, String courseName) {

		boolean result = false;
		Course c = new Course();
		c = getCourseById(id);
		if (null == (Integer) c.getId()) // 如果该记录不存在，则更改失败
			return false;

		String sql = "update course set courseName=? where id=? ";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, courseName);
			ps.setInt(2, id);
			int i = ps.executeUpdate();
			if (i > 0)
				result = true;

			if (null != ps)
				ps.close();
			if (null != con)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据课程编号以确认某门课程是否存在 如果存在，则返回true，否则返回false
	 * */
	public boolean ifExistOneCourse(String courseId) {

		boolean result = false;
		String sql = "select * from course where courseId=?";

		String flag = "";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, courseId);
			rs = ps.executeQuery();
			while (rs.next()) {
				flag = rs.getString("courseId");
			}
			if (null != rs)
				rs.close();
			if (null != ps)
				ps.close();
			if (null != con)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag.endsWith("")) // 为空，则不存在
			result = false;
		return result;
	}

	/**
	 * 新增课程 如果新增成功则返回true，否则返回false
	 * */
	public boolean inserCourse(String courseId, String courseName) {

		boolean result = false;
		if (ifExistOneCourse(courseId)) // 如果该门课程已经存在，则新增失败
			return false;

		String sql = "insert into course(courseId,courseName) values(?,?)";

		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, courseId);
			ps.setString(2, courseName);
			int i = ps.executeUpdate();
			if (i > 0)
				result = true;
			if (null != ps)
				ps.close();
			if (null != con)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
