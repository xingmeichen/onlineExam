package onlineExam.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import onlineExam.common.DB;
import onlineExam.domain.po.CS;
import onlineExam.domain.po.CSView;

public class CSMapper {
	
	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;	
	
	/**
	 * 查询所有考生信息以及对应的考卷*/
	public List<CSView> getCSView(){
		
		List<CSView> list = new ArrayList<CSView>();
		
		String sql = "select cs.studentId,cs.courseId,student.studentName,"
				+ " paper.paperId,paper.paperName,paper.paperSubject "
				+ " from cs,student,paper "
				+ " where cs.courseId=paper.courseId and "
				+ " cs.studentId=student.studentId ";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				CSView csv = new CSView();
				csv.setCourseId(rs.getString("courseId"));
				csv.setPaperId(rs.getString("paperId"));
				csv.setPaperName(rs.getString("paperName"));
				csv.setPaperSubject(rs.getString("paperSubject"));
				csv.setStudentId(rs.getString("studentId"));
				csv.setStudentName(rs.getString("studentName"));
				list.add(csv);
			}
			if(null != rs)
				rs.close();
			if(null != ps)
				ps.close();
			if(null != con)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * 查询某个考生信息以及对应的考卷*/
	public List<CSView> getCSViewByStudent(String studentId){
		
		List<CSView> list = new ArrayList<CSView>();
		
		String sql = "select cs.studentId,cs.courseId,student.studentName,"
				+ "paper.paperId,paper.paperName,paper.paperSubject "
				+ " from cs,student,paper "
				+ " where cs.studentId=student.studentId and "
				+ " cs.courseId=paper.courseId and cs.studentId=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentId);
			rs = ps.executeQuery();
			while(rs.next()){
				CSView csv = new CSView();
				csv.setCourseId(rs.getString("courseId"));
				csv.setPaperId(rs.getString("paperId"));
				csv.setPaperName(rs.getString("paperName"));
				csv.setPaperSubject(rs.getString("paperSubject"));
				csv.setStudentId(rs.getString("studentId"));
				csv.setStudentName(rs.getString("studentName"));
				list.add(csv);
			}
			if(null != rs)
				rs.close();
			if(null != ps)
				ps.close();
			if(null != con)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 查找到修了某个老师的课程的所有考生信息以及对应的考卷*/
	public List<CSView> getCSViewByTeacher(String paperMaker){
		
		List<CSView> list = new ArrayList<CSView>();
		
		String sql = "select cs.id, cs.studentId,cs.courseId,student.studentName,"
				+ "paper.paperId,paper.paperName,paper.paperSubject "
				+ " from cs,student,paper "
				+ "where cs.studentId=student.studentId and "
				+ "cs.courseId=paper.courseId and paper.paperMaker=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paperMaker);
			rs = ps.executeQuery();
			while(rs.next()){
				CSView csv = new CSView();
				csv.setCourseId(rs.getString("cs.courseId"));
				csv.setPaperId(rs.getString("paperId"));
				csv.setPaperName(rs.getString("paperName"));
				csv.setPaperSubject(rs.getString("paperSubject"));
				csv.setStudentId(rs.getString("cs.studentId"));
				csv.setStudentName(rs.getString("studentName"));
				list.add(csv);
			}
			if(null != rs)
				rs.close();
			if(null != ps)
				ps.close();
			if(null != con)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<CS> getCS(){
		
		List<CS> list = new ArrayList<CS>();
		String sql = "select * from cs";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				CS cs = new CS();
				cs.setCourseId(rs.getString("courseId"));
				cs.setId(rs.getInt("id"));
				cs.setStudentId(rs.getString("studentId"));
				list.add(cs);
			}
			if(null != rs)
				rs.close();
			if(null != ps)
				ps.close();
			if(null != con)
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 判断某个学生是否已经选了某门课
	 * 如果已经选了该课程，则返回true,否则返回false
	 * */
	public boolean ifExistOneCS(String studentId, String courseId){
		
		boolean result = false;
		String sql = "select * from cs where studentId=? and courseId=?";
		 
		String flag = "";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentId);
			ps.setString(2, courseId);
			rs = ps.executeQuery();
			while(rs.next()){
				flag = rs.getString("studentId");
				System.out.println("*****");
				System.out.println(flag);
				System.out.println("*****");
			}
			if(null != rs)
				rs.close();
			if(null != ps)
				ps.close();
			if(null != con)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!flag.equals(""))//如果学生学号在选课表中不为空
			result = true;
		return result;
	}
	
	/**
	 * 某生选了某门课，则将选课记录保存到数据库表中
	 * */
	public boolean insertCS(String courseId, String studentId){
		
		boolean result = false;
		if(ifExistOneCS(studentId, courseId)) //如果该记录已经存在，则直接返回false
			return false;
		
		String sql = "insert into cs(courseId,studentId) values(?,?)";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, courseId);
			ps.setString(2, studentId);
			int i = ps.executeUpdate();
			if(i>0)
				result = true;
			if(null != rs)
				rs.close();
			if(null != ps)
				ps.close();
			if(null != con)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		List<CSView> list = new ArrayList<CSView>();
		List<CS> listCS = new ArrayList<CS>();
		CSMapper csdao = new CSMapper();
		
		boolean result = false;
		result = csdao.insertCS("c9", "s1");
		System.out.println(result);
	}

}
