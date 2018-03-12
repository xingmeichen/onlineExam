package onlineExam.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import onlineExam.common.DB;
import onlineExam.domain.po.Grade;
import onlineExam.domain.po.GradeView;

public class GradeMapper {
	
	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	public List<GradeView> getGrade(){
		
		List<GradeView> list = new ArrayList<GradeView>();
		String sql = "select grade.id,grade.studentId,grade.paperId,grade.grade,"
				+ " paper.paperSubject,paper.paperName"
				+ " from grade,paper "
				+ "where grade.paperId=paper.paperId";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				GradeView gv = new GradeView();
				gv.setMark(rs.getInt("grade"));
				gv.setId(rs.getInt("id"));
				gv.setPaperId(rs.getString("paperId"));
				gv.setStudentId(rs.getString("studentId"));
				gv.setPaperName(rs.getString("paperName"));
				gv.setPaperSubject(rs.getString("paperSubject"));
				list.add(gv);
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
	 * 通过学生的学号和考卷的编号，可以唯一确定一名学生的某门成绩
	 * */
	public Grade getOneGrade(String studentId,String paperId){
		
		Grade g = new Grade();
		String sql = "select * from grade where studentId=? and paperId=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentId);
			ps.setString(2, paperId);
			rs = ps.executeQuery();
			if(rs.next()){
				g.setGrade(rs.getInt("grade"));
				g.setId(rs.getInt("id"));
				g.setPaperId(rs.getString("paperId"));
				g.setStudentId(rs.getString("studentId"));
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
		
		return g;		
	}
	
	/**
	 * 查询某次考试的所有学生的成绩
	 * */
	public List<GradeView> getGradeOfOneExam(String paperId){
		
		List<GradeView> list = new ArrayList<GradeView>();
		String sql = "select grade.id,grade.studentId,grade.paperId,grade.grade,"
				+ " paper.paperSubject,paper.paperName"
				+ " from grade,paper "
				+ " where grade.paperId=paper.paperId and paper.paperId=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);			
			ps.setString(1, paperId);
			rs = ps.executeQuery();
			while(rs.next()){
				GradeView gv = new GradeView();
				gv.setMark(rs.getInt("grade"));
				gv.setId(rs.getInt("id"));
				gv.setPaperId(rs.getString("paperId"));
				gv.setStudentId(rs.getString("studentId"));
				gv.setPaperName(rs.getString("paperName"));
				gv.setPaperSubject(rs.getString("paperSubject"));
				list.add(gv);
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
	
	/**
	 * 查询某个学生的各门课程的成绩
	 * */
	public List<GradeView> getGradeOfOneStudent(String studentId){
		
		List<GradeView> list = new ArrayList<GradeView>();
		String sql = "select grade.id,grade.studentId,grade.paperId,grade.grade,"
				+ " paper.paperSubject,paper.paperName"
				+ " from grade,paper "
				+ "where grade.paperId=paper.paperId and grade.studentId=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);			
			ps.setString(1, studentId);
			rs = ps.executeQuery();
			while(rs.next()){
				while(rs.next()){
					GradeView gv = new GradeView();
					gv.setMark(rs.getInt("grade"));
					gv.setId(rs.getInt("id"));
					gv.setPaperId(rs.getString("paperId"));
					gv.setStudentId(rs.getString("studentId"));
					gv.setPaperName(rs.getString("paperName"));
					gv.setPaperSubject(rs.getString("paperSubject"));
					list.add(gv);
				}
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
	
	/**
	 * 查询某个出卷人对应的所有成绩记录 （教师用户用到）
	 * */
	public List<GradeView> getGradeOfTeacher(String paperMaker){
		
		List<GradeView> list = new ArrayList<GradeView>();
		String sql = "select grade.id,grade.studentId,grade.paperId,grade.grade,"
				+ " paper.paperSubject,paper.paperName"
				+ " from grade,paper "
				+ "where grade.paperId=paper.paperId and paper.paperMaker=?";
		
		con = DB.getConn();		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paperMaker);
			rs = ps.executeQuery();
			while(rs.next()){
				GradeView gv = new GradeView();
				gv.setMark(rs.getInt("grade"));
				gv.setId(rs.getInt("id"));
				gv.setPaperId(rs.getString("paperId"));
				gv.setStudentId(rs.getString("studentId"));
				gv.setPaperName(rs.getString("paperName"));
				gv.setPaperSubject(rs.getString("paperSubject"));
				list.add(gv);
			}
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
		return list;
	}
	
	/**
	 * 插入一条成绩记录
	 * */
	public boolean insertGrade(Grade g){
		
		boolean result = false;
		String sql = "insert into grade(studentId,paperId,grade)values(?,?,?)";
		Grade grade = new Grade();
		grade = getOneGrade(g.getStudentId(),g.getPaperId());
		if(grade != null)//该条记录已经存在，则插入失败，只能尝试修改记录
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, g.getStudentId());
			ps.setString(2, g.getPaperId());
			ps.setInt(3, g.getGrade());
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
	 * 更新一条成绩记录,将g1 改为g2
	 * */
	public boolean updateGrade(Grade g1, Grade g2){
		
		boolean result = false;
		String sql = "update set studentId=?,"
				+ "paperId=?,"
				+ "grade=? "
				+ "where studentId=? and paperId=?";
		Grade grade = new Grade();
		grade = getOneGrade(g1.getStudentId(),g1.getPaperId()); 
		if(grade == null)//该条记录不存在，则修改失败
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, g2.getStudentId());
			ps.setString(2, g2.getPaperId());
			ps.setInt(3, g2.getGrade());
			ps.setString(4, g1.getStudentId());
			ps.setString(5, g1.getPaperId());
			ps.setInt(6, g1.getGrade());
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
	 * 保存一条成绩记录
	 * 如果该条记录已经存在，则更改该记录,将g改为g1，
	 * 如果该条记录不存在，则插入该记录*/
	public boolean saveGrade(Grade g,Grade g1){
		
		boolean result = false;
		result = insertGrade(g1);
		if(!result){//如果插入失败，则尝试更改
			result = updateGrade(g,g1);
		}
		return result;
	}

}
