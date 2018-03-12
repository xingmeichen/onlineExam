package onlineExam.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import onlineExam.common.DB;
import onlineExam.domain.po.Essay;

public class EssayMapper {

	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;	
	
	/**
	 * 查询问答题的所有信息
	 * @throws SQLException 
	 * */
	public List<Essay> getEssayQuestion(){
		
		List<Essay> list = new ArrayList<Essay>();
		String sql = "select * from essay ";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Essay eq = new Essay();
				eq.setAnswer(rs.getString("answer"));
				eq.setId(rs.getInt("id"));
				eq.setMark(rs.getInt("mark"));
				eq.setPaperId(rs.getString("paperId"));
				eq.setQuestion(rs.getString("question"));
				eq.setQuestionNumber(rs.getInt("questionNumber"));
				list.add(eq);
			}
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (Exception e) {
			
		}
		return list;
	}
	
	/**
	 * 根据试卷编号paperId查询试卷信息（主要用于答题）
	 * */
	public List<Essay> getEssayQuestionByPaperId(String paperId){
		
		List<Essay> list = new ArrayList<Essay>();
		String sql = "select * from essay where paperId=? ";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paperId);
			rs = ps.executeQuery();
			while(rs.next()){
				Essay eq = new Essay();
				eq.setId(rs.getInt("id"));
				eq.setMark(rs.getInt("mark"));
				eq.setPaperId(rs.getString("paperId"));
				eq.setQuestion(rs.getString("question"));
				eq.setQuestionNumber(rs.getInt("questionNumber"));
				list.add(eq);
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
	 * 查询某套试卷的某道问答题
	 * */
	public Essay getOneEssay(String paperId, int questionNumber){
		
		Essay e = new Essay();
		String sql = "select * from essay where paperId=? and questionNumber=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paperId);
			ps.setInt(2, questionNumber);
			rs = ps.executeQuery();
			if(rs.next()){
				e.setId(rs.getInt("id"));
				e.setMark(rs.getInt("mark"));
				e.setPaperId(rs.getString("paperId"));
				e.setQuestion(rs.getString("question"));
				e.setQuestionNumber(rs.getInt("questionNumber"));
			}
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
		
	}
	
	/**
	 * 添加问答题到系统中
	 * */
	public int addEssay(Essay essay){
		
		int result = 0;
		String sql = "insert into essay("
				+ "paperId,"
				+ "questionNumber,"
				+ "question,"
				+ "mark)values(?,?,?,?)";
		
		Essay es = new Essay();
		es = getOneEssay(essay.getPaperId(),essay.getQuestionNumber());
		if(es.getPaperId() != null)//如果该道题在数据库中已经存在，则插入失败
			return 0;
        con = DB.getConn();
        try {
        	ps = con.prepareStatement(sql);
        	ps.setString(1, essay.getPaperId());
        	ps.setInt(2, essay.getQuestionNumber());
        	ps.setString(3, essay.getQuestion());
        	ps.setInt(4, essay.getMark());
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
	 * 通过id号查询问答题的信息
	 * */
	public Essay getEssayById(int id){
		
		Essay e = new Essay();
		String sql = "select * from essay where id=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				e.setAnswer(rs.getString("answer"));
				e.setId(id);
				e.setMark(rs.getInt("mark"));
				e.setPaperId(rs.getString("paperId"));
				e.setQuestion(rs.getString("question"));
				e.setQuestionNumber(rs.getInt("questionNumber"));
			}
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		return e;
	}
	
	/**
	 * 修改问答题的题目信息
	 * */
	public boolean updateQuestion(int id, String question){
		
		boolean result = false;
		
		Essay e = new Essay();
		e = getEssayById(id);
		if(null == (Integer)e.getId())
			return false;
		
		String sql = "update essay set question=? where id=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, question);
			ps.setInt(2, id);
			int i = ps.executeUpdate();
			if(i>0)
				result = true;
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 修改问答题的分值信息
	 * */
	public boolean updateMark(int id, int mark){
		
		boolean result = false;
		
		Essay e = new Essay();
		e = getEssayById(id);
		if(null == (Integer)e.getId())
			return false;
		
		String sql = "update essay set mark=? where id=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mark);
			ps.setInt(2, id);
			int i = ps.executeUpdate();
			if(i>0)
				result = true;
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}

}
