package onlineExam.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import onlineExam.common.DB;
import onlineExam.domain.po.Judge;

public class JudgeMapper {
	
	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;	
	
	/**
	 * 查询判断题的所有信息
	 * */
	public List<Judge> getJudgeQuestion(){
		
		List<Judge> list = new ArrayList<Judge>();
		String sql = "select * from judge";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Judge jq = new Judge();
				jq.setAnswer(rs.getString("answer"));
				jq.setId(rs.getInt("id"));
				jq.setMark(rs.getInt("mark"));
				jq.setPaperId(rs.getString("paperId"));
				jq.setQuestion(rs.getString("question"));
				jq.setQuestionNumber(rs.getInt("questionNumber"));
				list.add(jq);
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
	 * 根据试卷编号paperId查询试卷的信息（主要用于答题）
	 * */
	public List<Judge> getJudgeByPaperId(String paperId){
		
		List<Judge> list = new ArrayList<Judge>();
		String sql = "select * from judge where paperId=? ";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,paperId);
			rs = ps.executeQuery();
			while(rs.next()){
				Judge jq = new Judge();			
				jq.setId(rs.getInt("id"));
				jq.setMark(rs.getInt("mark"));
				jq.setPaperId(rs.getString("paperId"));
				jq.setQuestion(rs.getString("question"));
				jq.setQuestionNumber(rs.getInt("questionNumber"));
				list.add(jq);
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
	 * 查询某套试卷的某道判断题*/
	public Judge getOneJudge(String paperId,int questionNumber){
		
		Judge jq = new Judge();
		String sql = "select * from judge where paperId=? and questionNumber=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paperId);
			ps.setInt(2, questionNumber);
			rs = ps.executeQuery();
			if(rs.next()){						
				jq.setId(rs.getInt("id"));
				jq.setMark(rs.getInt("mark"));
				jq.setPaperId(rs.getString("paperId"));
				jq.setQuestion(rs.getString("question"));
				jq.setQuestionNumber(rs.getInt("questionNumber"));
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
		return jq;
	}
	
	/**
	 * 添加判断题到系统中
	 * */
	public int addJudge(Judge judge){
		int result = 0;
		String sql = "insert into judge("
				+ "paperId,"
				+ "questionNumber,"
				+ "question,"
				+ "answer,"
				+ "mark)values(?,?,?,?,?)";
		
		Judge j = new Judge();
		j = getOneJudge(judge.getPaperId(),judge.getQuestionNumber());
		if(j.getPaperId() != null) //如果该道题在数据库中已经存在，则插入失败
			return 0;
		
        con = DB.getConn();
        try {
        	ps = con.prepareStatement(sql);
        	ps.setString(1, judge.getPaperId());
        	ps.setInt(2, judge.getQuestionNumber());
        	ps.setString(3, judge.getQuestion());
        	ps.setString(4, judge.getAnswer());
        	ps.setInt(5, judge.mark);
        	result = ps.executeUpdate();
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
	 * 通过id查询判断题信息
	 * */
	public Judge getJudgeById(int id){
		
		Judge j = new Judge();
		
		String sql = "select * from judge where id=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				j.setAnswer(rs.getString("answer"));
				j.setId(id);
				j.setMark(rs.getInt("mark"));
				j.setPaperId(rs.getString("paperId"));
				j.setQuestion(rs.getString("question"));
				j.setQuestionNumber(rs.getInt("questionNumber"));
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
		
		return j;
	}
	
	/**
	 * 修改判断题的题目信息
	 * */
	public boolean updateQuestion(int id, String question){
		
		boolean result = false;
		
		Judge j = new Judge();
		j = getJudgeById(id);
		if(null == (Integer)j.getId()) //如果不存在该记录，则修改失败
			return false;
		
		String sql = "update judge set question=? where id=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, question);
			ps.setInt(2, id);
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
	 * 修改判断题的答案信息
	 * */
	public boolean updateAnswer(int id, String answer){
		
		boolean result = false;
		
		Judge j = new Judge();
		j = getJudgeById(id);
		if(null == (Integer)j.getId()) //如果不存在该记录，则修改失败
			return false;
		
		String sql = "update judge set answer=? where id=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, answer);
			ps.setInt(2, id);
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
	 * 修改判断题的分值信息
	 * */
	public boolean updateMark(int id, int mark){
		
		boolean result = false;
		
		Judge j = new Judge();
		j = getJudgeById(id);
		if(null == (Integer)j.getId()) //如果不存在该记录，则修改失败
			return false;
		
		String sql = "update judge set mark=? where id=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mark);
			ps.setInt(2, id);
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

}
