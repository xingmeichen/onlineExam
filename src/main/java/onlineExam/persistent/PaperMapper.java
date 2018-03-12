package onlineExam.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import onlineExam.common.DB;
import onlineExam.domain.po.Paper;

public class PaperMapper{	

	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;	
	
	/**
	 * 查询试卷的所有信息
	 * @throws SQLException 
	 * */
	public List<Paper> getPaper() {
		List<Paper> list = new ArrayList<Paper>();
		String sql = "select * from paper";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Paper paper = new Paper();
				paper.setCourseId(rs.getString("courseId"));
				paper.setId(rs.getInt("id"));
				paper.setMark(rs.getInt("mark"));
				paper.setPaperDuration(rs.getString("paperDuration"));
				paper.setPaperEndTime(rs.getTimestamp("paperEndTime"));
				paper.setPaperId(rs.getString("paperId"));
				paper.setPaperMaker(rs.getString("paperMaker"));
				paper.setPaperName(rs.getString("paperName"));
				paper.setPaperStartTime(rs.getTimestamp("paperStartTime")); 
				paper.setPaperSubject(rs.getString("paperSubject"));
				list.add(paper);
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
	 * 通过试卷的编号paperId查找试卷的相关信息
	 * @throws SQLException 
	 * */
	public Paper getPaperByPaperId(String paperId){
		
		Paper paper = new Paper();
		String sql = "select * from paper where paperId=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paperId);
			rs = ps.executeQuery();
			if(rs.next()){
				paper.setCourseId(rs.getString("courseId"));
				paper.setId(rs.getInt("id"));
				paper.setMark(rs.getInt("mark"));
				paper.setPaperDuration(rs.getString("paperDuration"));
				paper.setPaperEndTime(rs.getTimestamp("paperEndTime"));
				paper.setPaperId(rs.getString("paperId"));
				paper.setPaperMaker(rs.getString("paperMaker"));
				paper.setPaperName(rs.getString("paperName"));
				paper.setPaperStartTime(rs.getTimestamp("paperStartTime"));
				paper.setPaperSubject(rs.getString("paperSubject"));
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

		return paper;				
	}
	
	/**
	 * 通过试卷的编号paperMaker查找试卷的相关信息 
	 * */
	public List<Paper> getPaperByPaperMaker(String paperMaker){
		
		List<Paper> list = new ArrayList<Paper>();
		String sql = "select * from paper where paperMaker=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paperMaker);
			rs = ps.executeQuery();
			while(rs.next()){
				Paper paper = new Paper();
				paper.setCourseId(rs.getString("courseId"));
				paper.setId(rs.getInt("id"));
				paper.setMark(rs.getInt("mark"));
				paper.setPaperDuration(rs.getString("paperDuration"));
				paper.setPaperEndTime(rs.getTimestamp("paperEndTime"));
				paper.setPaperId(rs.getString("paperId"));
				paper.setPaperMaker(rs.getString("paperMaker"));
				paper.setPaperName(rs.getString("paperName"));
				paper.setPaperStartTime(rs.getTimestamp("paperStartTime"));
				paper.setPaperSubject(rs.getString("paperSubject"));
				list.add(paper);
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
	 * 获取当前处于考试时间范围的的所有考试科目的基本信息 
	 * */
	public List<Paper> getCurrenExam() {
		
		List<Paper> list = new ArrayList<Paper>();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String snow = f.format(new Date());
		Timestamp now = Timestamp.valueOf(snow);
		String sql ="select * from paper where ? between paperStartTime and paperEndTime";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1, now);
			rs = ps.executeQuery();
			while(rs.next()){
				Paper paper = new Paper();
				paper.setCourseId(rs.getString("courseId"));
				paper.setId(rs.getInt("id"));
				paper.setMark(rs.getInt("mark"));
				paper.setPaperDuration(rs.getString("paperDuration"));
				paper.setPaperEndTime(rs.getTimestamp("paperEndTime"));
				paper.setPaperId(rs.getString("paperId"));
				paper.setPaperMaker(rs.getString("paperMaker"));
				paper.setPaperName(rs.getString("paperName"));
				paper.setPaperStartTime(rs.getTimestamp("paperStartTime"));
				paper.setPaperSubject(rs.getString("paperSubject"));
				list.add(paper);				
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
	 * 获取当前处于考试时间范围的的所有考试科目的基本信息,
	 * 同时某学生有这门考试
	 * */
	public List<Paper> getCurrenExamOfStudent(String studentId) {
		
		List<Paper> list = new ArrayList<Paper>();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String snow = f.format(new Date());
		Timestamp now = Timestamp.valueOf(snow);
		String sql ="select * from paper where ? between paperStartTime and paperEndTime "
				+ "and paper.courseId = ?";
		String sqlCS = "select courseId from cs where studentId=?";
		
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;	
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1, now);
			ps1 = con.prepareStatement(sqlCS);
			ps1.setString(1, studentId);
			rs1 = ps1.executeQuery();
			while(rs1.next()){
				String courseId = rs1.getString(1);
				ps.setString(2, courseId);
				rs = ps.executeQuery();
				while(rs.next()){
					Paper paper = new Paper();
					paper.setCourseId(rs.getString("courseId"));
					paper.setId(rs.getInt("id"));
					paper.setMark(rs.getInt("mark"));
					paper.setPaperDuration(rs.getString("paperDuration"));
					paper.setPaperEndTime(rs.getTimestamp("paperEndTime"));
					paper.setPaperId(rs.getString("paperId"));
					paper.setPaperMaker(rs.getString("paperMaker"));
					paper.setPaperName(rs.getString("paperName"));
					paper.setPaperStartTime(rs.getTimestamp("paperStartTime"));
					paper.setPaperSubject(rs.getString("paperSubject"));
					list.add(paper);				
				}
			}
			if(rs1 !=null)
				rs1.close();
			if(rs != null)
				rs.close();	
			if(ps1 != null)
				ps1.close();
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
	 * 获取当前处于考试时间范围的的所有考试科目的基本信息 
	 * 同时某教师发布了该试卷
	 * */
	public List<Paper> getCurrenExamOfTeacher(String teacherId) {
		
		List<Paper> list = new ArrayList<Paper>();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String snow = f.format(new Date());
		Timestamp now = Timestamp.valueOf(snow);
		String sql ="select * from paper where ? between paperStartTime and paperEndTime and "
				+ "paperMaker=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1, now);
			ps.setString(2, teacherId);
			rs = ps.executeQuery();
			while(rs.next()){
				Paper paper = new Paper();
				paper.setCourseId(rs.getString("courseId"));
				paper.setId(rs.getInt("id"));
				paper.setMark(rs.getInt("mark"));
				paper.setPaperDuration(rs.getString("paperDuration"));
				paper.setPaperEndTime(rs.getTimestamp("paperEndTime"));
				paper.setPaperId(rs.getString("paperId"));
				paper.setPaperMaker(rs.getString("paperMaker"));
				paper.setPaperName(rs.getString("paperName"));
				paper.setPaperStartTime(rs.getTimestamp("paperStartTime"));
				paper.setPaperSubject(rs.getString("paperSubject"));
				list.add(paper);				
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
	 * 查询尚未开始的考试科目的信息
	 * */
	public List<Paper> getNotStartExam() {
		
		List<Paper> list = new ArrayList<Paper>();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String snow = f.format(new Date());
		Timestamp now = Timestamp.valueOf(snow);
		String sql ="select * from paper where ?<paperStartTime";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1, now);
			rs = ps.executeQuery();
			while(rs.next()){
				Paper paper = new Paper();
				paper.setCourseId(rs.getString("courseId"));
				paper.setId(rs.getInt("id"));
				paper.setMark(rs.getInt("mark"));
				paper.setPaperDuration(rs.getString("paperDuration"));
				paper.setPaperEndTime(rs.getTimestamp("paperEndTime"));
				paper.setPaperId(rs.getString("paperId"));
				paper.setPaperMaker(rs.getString("paperMaker"));
				paper.setPaperName(rs.getString("paperName"));
				paper.setPaperStartTime(rs.getTimestamp("paperStartTime"));
				paper.setPaperSubject(rs.getString("paperSubject"));
				list.add(paper);				
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
	 * 查询尚未开始的考试科目的信息
	 * 同时某学生有该门考试
	 * */
	public List<Paper> getNotStartExamOfStudent(String studentId) {
		
		List<Paper> list = new ArrayList<Paper>();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String snow = f.format(new Date());
		Timestamp now = Timestamp.valueOf(snow);
		String sql ="select * from paper where ?<paperStartTime and "
				+ "paper.courseId=?";
		
		String sqlCS = "select courseId from cs where studentId=?";
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;	
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1, now);
			ps1 = con.prepareStatement(sqlCS);
			ps1.setString(1, studentId);
			rs1 = ps1.executeQuery();
			while(rs1.next()){
				String courseId = rs1.getString(1);
				ps.setString(2, courseId);
				rs = ps.executeQuery();
				while(rs.next()){
					Paper paper = new Paper();
					paper.setCourseId(rs.getString("courseId"));
					paper.setId(rs.getInt("id"));
					paper.setMark(rs.getInt("mark"));
					paper.setPaperDuration(rs.getString("paperDuration"));
					paper.setPaperEndTime(rs.getTimestamp("paperEndTime"));
					paper.setPaperId(rs.getString("paperId"));
					paper.setPaperMaker(rs.getString("paperMaker"));
					paper.setPaperName(rs.getString("paperName"));
					paper.setPaperStartTime(rs.getTimestamp("paperStartTime"));
					paper.setPaperSubject(rs.getString("paperSubject"));
					list.add(paper);				
				}				
			}
			if(rs1 !=null)
				rs1.close();
			if(rs != null)
				rs.close();	
			if(ps1 != null)
				ps1.close();
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
	 * 查询尚未开始的考试科目的信息
	 * */
	public List<Paper> getNotStartExamOfTeacher(String teacherId) {
		
		List<Paper> list = new ArrayList<Paper>();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String snow = f.format(new Date());
		Timestamp now = Timestamp.valueOf(snow);
		String sql ="select * from paper where ?<paperStartTime and "
				+ "paperMaker=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1, now);
			ps.setString(2, teacherId);
			rs = ps.executeQuery();
			while(rs.next()){
				Paper paper = new Paper();
				paper.setCourseId(rs.getString("courseId"));
				paper.setId(rs.getInt("id"));
				paper.setMark(rs.getInt("mark"));
				paper.setPaperDuration(rs.getString("paperDuration"));
				paper.setPaperEndTime(rs.getTimestamp("paperEndTime"));
				paper.setPaperId(rs.getString("paperId"));
				paper.setPaperMaker(rs.getString("paperMaker"));
				paper.setPaperName(rs.getString("paperName"));
				paper.setPaperStartTime(rs.getTimestamp("paperStartTime"));
				paper.setPaperSubject(rs.getString("paperSubject"));
				list.add(paper);				
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
	 * 查询已经结束的考试科目的信息
	 * */
	public List<Paper> getEndExam() {
		
		List<Paper> list = new ArrayList<Paper>();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String snow = f.format(new Date());
		Timestamp now = Timestamp.valueOf(snow);
		String sql ="select * from paper where ?> paperEndTime";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1, now);;
			rs = ps.executeQuery();
			while(rs.next()){
				Paper paper = new Paper();
				paper.setCourseId(rs.getString("courseId"));
				paper.setId(rs.getInt("id"));
				paper.setMark(rs.getInt("mark"));
				paper.setPaperDuration(rs.getString("paperDuration"));
				paper.setPaperEndTime(rs.getTimestamp("paperEndTime"));
				paper.setPaperId(rs.getString("paperId"));
				paper.setPaperMaker(rs.getString("paperMaker"));
				paper.setPaperName(rs.getString("paperName"));
				paper.setPaperStartTime(rs.getTimestamp("paperStartTime"));
				paper.setPaperSubject(rs.getString("paperSubject"));				
				list.add(paper);				
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
	 * 查询已经结束的考试科目的信息
	 * 同时某学生有该门考试
	 * */
	public List<Paper> getEndExamOfStudent(String studentId) {
		
		List<Paper> list = new ArrayList<Paper>();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String snow = f.format(new Date());
		Timestamp now = Timestamp.valueOf(snow);
		String sql ="select * from paper where ?> paperEndTime and "
				+ "paper.courseId=? ";
		
		String sqlCS = "select courseId from cs where studentId=?";
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1, now);
			ps1 = con.prepareStatement(sqlCS);
			ps1.setString(1, studentId);
			rs1 = ps1.executeQuery();
			while(rs1.next()){
				String courseId = rs1.getString(1);
				ps.setString(2, courseId);
				rs = ps.executeQuery();
				while(rs.next()){
					Paper paper = new Paper();
					paper.setCourseId(rs.getString("courseId"));
					paper.setId(rs.getInt("id"));
					paper.setMark(rs.getInt("mark"));
					paper.setPaperDuration(rs.getString("paperDuration"));
					paper.setPaperEndTime(rs.getTimestamp("paperEndTime"));
					paper.setPaperId(rs.getString("paperId"));
					paper.setPaperMaker(rs.getString("paperMaker"));
					paper.setPaperName(rs.getString("paperName"));
					paper.setPaperStartTime(rs.getTimestamp("paperStartTime"));
					paper.setPaperSubject(rs.getString("paperSubject"));				
					list.add(paper);				
				}
			}

			if(rs1 !=null)
				rs1.close();
			if(rs != null)
				rs.close();	
			if(ps1 != null)
				ps1.close();
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
	 * 查询已经结束的考试科目的信息
	 * 同时某教师发布了该门考试
	 * */
	public List<Paper> getEndExamOfTeacher(String teacherId) {
		
		List<Paper> list = new ArrayList<Paper>();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String snow = f.format(new Date());
		Timestamp now = Timestamp.valueOf(snow);
		String sql ="select * from paper where ?> paperEndTime and "
				+ "paperMaker=? ";		
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1, now);
			ps.setString(2, teacherId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Paper paper = new Paper();
				paper.setCourseId(rs.getString("courseId"));
				paper.setId(rs.getInt("id"));
				paper.setMark(rs.getInt("mark"));
				paper.setPaperDuration(rs.getString("paperDuration"));
				paper.setPaperEndTime(rs.getTimestamp("paperEndTime"));
				paper.setPaperId(rs.getString("paperId"));
				paper.setPaperMaker(rs.getString("paperMaker"));
				paper.setPaperName(rs.getString("paperName"));
				paper.setPaperStartTime(rs.getTimestamp("paperStartTime"));
				paper.setPaperSubject(rs.getString("paperSubject"));
				list.add(paper);
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
	
	public List<Paper> getNotEndExam() {
		
		List<Paper> list = new ArrayList<Paper>();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String snow = f.format(new Date());
		Timestamp now = Timestamp.valueOf(snow);
		String sql ="select * from paper where ?<= paperEndTime order by paperEndTime";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1, now);
			rs = ps.executeQuery();
			while(rs.next()){
				Paper paper = new Paper();
				paper.setCourseId(rs.getString("courseId"));
				paper.setId(rs.getInt("id"));
				paper.setMark(rs.getInt("mark"));
				paper.setPaperDuration(rs.getString("paperDuration"));
				paper.setPaperEndTime(rs.getTimestamp("paperEndTime"));
				paper.setPaperId(rs.getString("paperId"));
				paper.setPaperMaker(rs.getString("paperMaker"));
				paper.setPaperName(rs.getString("paperName"));
				paper.setPaperStartTime(rs.getTimestamp("paperStartTime"));
				paper.setPaperSubject(rs.getString("paperSubject"));				
				list.add(paper);				
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
	
	
	public boolean addPaper(Paper paper){
		
		boolean result = false;
		String sql = "insert into paper("
				+ "paperId,"
				+ "paperName,"
				+ "courseId,"
				+ "paperSubject,"
				+ "paperMaker,"
				+ "paperStartTime,"
				+ "paperEndTime,"
				+ "paperDuration,"
				+ "mark)"
				+ " values(?,?,?,?,?,?,?,?,?)";		
		Paper p = new Paper();
		p = getPaperByPaperId(paper.getPaperId()); 
		if(p.getPaperId() != null) //如果该试卷已经存在，则插入失败
			return false;
		con = DB.getConn();
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paper.getPaperId());
			ps.setString(2, paper.getPaperName());
			ps.setString(3, paper.getCourseId());
			ps.setString(4, paper.getPaperSubject());
			ps.setString(5, paper.getPaperMaker());
			ps.setTimestamp(6, paper.getPaperStartTime());
			ps.setTimestamp(7, paper.getPaperEndTime());
			ps.setString(8, paper.getPaperDuration());
			ps.setInt(9, paper.getMark());			
			i = ps.executeUpdate();	
			
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(i>0)
			result = true;
		return result;
		
	}
	
	/**
	 * 更改考卷名称，主要是教师用户用到
	 * */
	public boolean updatePaperName(String paperId,String paperName){
		
		boolean result = false;
		String sql = "update paper set paperName=? where paperId=? ";
		
		Paper p = new Paper();
		p = getPaperByPaperId(paperId); 
		if(null == p.getPaperId() ) //如果该试卷不存在，则更改失败
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,paperName);
			ps.setString(2, paperId);
			int i = ps.executeUpdate();
			if(i>0)
				result = true;
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
	
	/**
	 * 更改试卷的课程编号
	 * */
	public boolean updateCourseId(String paperId,String courseId){
		
		boolean result = false;
		String sql = "update paper set courseId=? where paperId=?";
		
		Paper p = new Paper();
		p = getPaperByPaperId(paperId); 
		if(null == p.getPaperId() ) //如果该试卷不存在，则更改失败
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,courseId);
			ps.setString(2, paperId);
			int i = ps.executeUpdate();
			if(i>0)
				result = true;
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
	
	/**
	 * 更改考卷的考试科目
	 * */
	public boolean updatePaperSubject(String paperId,String paperSubject){
		
		boolean result = false;
		String sql = "update paper set paperSubject=? where paperId=?";
		
		Paper p = new Paper();
		p = getPaperByPaperId(paperId); 
		if(null == p.getPaperId() ) //如果该试卷不存在，则更改失败
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,paperSubject);
			ps.setString(2, paperId);
			int i = ps.executeUpdate();
			if(i>0)
				result = true;
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
	
	/**
	 * 更改开考时间
	 * */
	public boolean updatePaperStartTime(String paperId, Timestamp paperStartTime){
		
		boolean result = false;
		String sql = "update paper set paperStartTime=? where paperId=?";
		
		Paper p = new Paper();
		p = getPaperByPaperId(paperId); 
		if(null == p.getPaperId() ) //如果该试卷不存在，则更改失败
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1,paperStartTime);
			ps.setString(2, paperId);
			int i = ps.executeUpdate();
			if(i>0)
				result = true;
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
	
	/**
	 * 更改考试结束时间
	 * */
	public boolean updatePaperEndTime(String paperId, Timestamp paperEndTime){
		
		boolean result = false;
		String sql = "update paper set paperEndTime=? where paperId=?";
		
		Paper p = new Paper();
		p = getPaperByPaperId(paperId); 
		if(null == p.getPaperId() ) //如果该试卷不存在，则更改失败
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1,paperEndTime);
			ps.setString(2, paperId);
			int i = ps.executeUpdate();
			if(i>0)
				result = true;
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
	
	/**
	 * 更改考试时长*/
	public boolean updatePaperDuration(String paperId, String paperDuration){
		
		boolean result = false;
		String sql = "update paper set paperDuration=? where paperId=?";
		
		Paper p = new Paper();
		p = getPaperByPaperId(paperId); 
		if(null == p.getPaperId() ) //如果该试卷不存在，则更改失败
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,paperDuration);
			ps.setString(2, paperId);
			int i = ps.executeUpdate();
			if(i>0)
				result = true;
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
	
	/**
	 * 更改考卷总分值
	 * */
	public boolean updateMark(String paperId, int mark){
		
		boolean result = false;
		String sql = "update paper set mark=? where paperId=?";
		
		Paper p = new Paper();
		p = getPaperByPaperId(paperId); 
		if(null == p.getPaperId() ) //如果该试卷不存在，则更改失败
			return false;
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1,mark);
			ps.setString(2, paperId);
			int i = ps.executeUpdate();
			if(i>0)
				result = true;
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
		
		PaperMapper pdao = new PaperMapper();	
		
	}


}
