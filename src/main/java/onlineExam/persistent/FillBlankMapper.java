package onlineExam.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import onlineExam.common.DB;
import onlineExam.domain.po.FillBlank;


public class FillBlankMapper {
	
	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;	
	
	/**
	 * 查询填空题的所有信息
	 * @throws SQLException 
	 * */
	public List<FillBlank> getFillBlankQuestion() {
		
		List<FillBlank> list = new ArrayList<FillBlank>();
		String sql = "select * from fill_blank ";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				FillBlank fbq = new FillBlank();
				fbq.setAnswer(rs.getString("answer"));
				fbq.setQuestion(rs.getString("question"));
				fbq.setId(rs.getInt("id"));
				fbq.setMark(rs.getInt("mark"));
				fbq.setPaperId(rs.getString("paperId"));
				fbq.setQuestionNumber(rs.getInt("questionNumber"));
				list.add(fbq);
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
	 * 根据试卷编号paperId查询试卷的信息
	 * */
	public List<FillBlank> getFillBlankByPaperId(String paperId){
		
		List<FillBlank> list = new ArrayList<FillBlank>();
		String sql = "select * from fill_blank where paperId=? ";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paperId);
			rs = ps.executeQuery();
			while(rs.next()){
				FillBlank fbq = new FillBlank();
				fbq.setQuestion(rs.getString("question"));
				fbq.setId(rs.getInt("id"));
				fbq.setMark(rs.getInt("mark"));
				fbq.setPaperId(rs.getString("paperId"));
				fbq.setQuestionNumber(rs.getInt("questionNumber"));
				list.add(fbq);
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
	 * 查询某套试卷的某道填空题
	 * */
	public FillBlank getOneFillBlank(String paperId,int questionNumber){
		
		FillBlank f = new FillBlank();
		String sql = "select * from fill_blank where paperId=? and questionNumber=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paperId);
			ps.setInt(2,questionNumber);
			rs = ps.executeQuery();
			if(rs.next()){
				f.setQuestion(rs.getString("question"));
				f.setId(rs.getInt("id"));
				f.setMark(rs.getInt("mark"));
				f.setPaperId(rs.getString("paperId"));
				f.setQuestionNumber(rs.getInt("questionNumber"));
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
		return f;
	}
	
	/**
	 * 添加填空题到系统中
	 * */
	public int addFillBlank(FillBlank fillBlank){
		int result = 0;
		String sql = "insert into fill_blank("
				+ "paperId,"
				+ "questionNumber,"
				+ "question,"
				+ "mark)values(?,?,?,?)";
		
		FillBlank f = new FillBlank();
		f = getOneFillBlank(fillBlank.getPaperId(),fillBlank.getQuestionNumber());
		if(f.getPaperId() != null) //如果该道填空题已经存在，则插入失败
			return 0;
		
        con = DB.getConn();
        try {
        	ps = con.prepareStatement(sql);
        	ps.setString(1, fillBlank.getPaperId());
        	ps.setInt(2, fillBlank.getQuestionNumber());
        	ps.setString(3, fillBlank.getQuestion());        	   
        	ps.setInt(4, fillBlank.getMark());
        	result = ps.executeUpdate();
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
	 * 通过id号查询填空题的信息
	 * */
	public FillBlank getFillBlankById(int id){
		
		FillBlank f = new FillBlank();
		
		String sql = "select * from judge where id=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				f.setAnswer(rs.getString("answer"));
				f.setId(id);
				f.setMark(rs.getInt("mark"));
				f.setPaperId(rs.getString("paperId"));
				f.setQuestion(rs.getString("question"));
				f.setQuestionNumber(rs.getInt("questionNumber"));
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
		return f;
	}
	
	/**
	 * 更改填空题的题目信息
	 * */
	public boolean updateQuestion(int id, String question){
		
		boolean result = false;
		
		FillBlank f = new FillBlank();
		f = getFillBlankById(id);
		if(null == (Integer)f.getId())
			return false;
		
		String sql = "update fill_blank set question=? where id=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,question);
			ps.setInt(2, id);
			int i = ps.executeUpdate();
			if(i>0)
				result = true;
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
	 * 更改填空题的题目信息
	 * */
	public boolean updateMark(int id, int mark){
		
		boolean result = false;
		
		FillBlank f = new FillBlank();
		f = getFillBlankById(id);
		if(null == (Integer)f.getId())
			return false;
		
		String sql = "update fill_blank set question=? where id=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1,mark);
			ps.setInt(2, id);
			int i = ps.executeUpdate();
			if(i>0)
				result = true;
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

}
