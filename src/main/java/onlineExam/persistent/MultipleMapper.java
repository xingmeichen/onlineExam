package onlineExam.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import onlineExam.common.DB;
import onlineExam.domain.po.Multiple;

public class MultipleMapper {
	
	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;	
	
	/**
	 * 查询多选题的所有信息
	 * */
	public List<Multiple> getMultipleOption(){
		
		List<Multiple> list = new ArrayList<Multiple>();
		String sql = "select * from multiple";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Multiple mo = new Multiple();
				mo.setA(rs.getString("A"));
				mo.setAnswer(rs.getString("answer"));
				mo.setB(rs.getString("B"));			
				mo.setC(rs.getString("C"));
				mo.setD(rs.getString("D"));
				mo.setId(rs.getInt("id"));
				mo.setMark(rs.getInt("mark"));
				mo.setOptionNumber(rs.getInt("questionNumber"));
				mo.setPaperId(rs.getString("paperId"));
				mo.setQuestion(rs.getString("question"));
				list.add(mo);
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
	 * 查询试卷的某些信息（主要用于答题）*/
	public List<Multiple> getMultipleOptionByPaperId(String paperId){
		
		List<Multiple> list = new ArrayList<Multiple>();
		String sql = "select * from multiple where paperId=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,paperId);
			rs = ps.executeQuery();
			while(rs.next()){
				Multiple mo = new Multiple();
				mo.setA(rs.getString("A"));
				mo.setAnswer(rs.getString("answer"));
				mo.setB(rs.getString("B"));			
				mo.setC(rs.getString("C"));
				mo.setD(rs.getString("D"));
				mo.setId(rs.getInt("id"));
				mo.setMark(rs.getInt("mark"));
				mo.setOptionNumber(rs.getInt("questionNumber"));
				mo.setPaperId(rs.getString("paperId"));
				mo.setQuestion(rs.getString("question"));
				list.add(mo);
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
	 * 查询某套试卷中的某道不定项选择题
	 * */
	public Multiple getOneMultiple(String paperId,int questionNumber){
		
		Multiple m = new Multiple();
		String sql = "select * from multiple where paperId=? and questionNumber=? ";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paperId);
			ps.setInt(2, questionNumber);
			rs = ps.executeQuery();
			if(rs.next()){
				m.setA(rs.getString("A"));
				m.setAnswer(rs.getString("answer"));
				m.setB(rs.getString("B"));
				m.setC(rs.getString("C"));
				m.setD(rs.getString("D"));
				m.setId(rs.getInt("id"));
				m.setMark(rs.getInt("mark"));
				m.setOptionNumber(rs.getInt("questionNumber"));
				m.setPaperId(rs.getString("paperId"));
				m.setQuestion(rs.getString("question"));
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
		return m;
	}
	
	/**
	 * 添加多选题到系统中
	 * */
	public int addMultiple(Multiple multiple){
		int result = 0;
		String sql = "insert into multiple("
				+ "paperId,"
				+ "questionNumber,"
				+ "question,"
				+ "A,B,C,D,"
				+ "answer,"
				+ "mark)values(?,?,?,?,?,?,?,?,?)";
		Multiple m = new Multiple();
		m = getOneMultiple(multiple.getPaperId(),multiple.getOptionNumber());
		if(m.getPaperId() != null) //如果该道题在数据库中已经存在，则插入失败
			return 0;
        con = DB.getConn();
        try {
        	ps = con.prepareStatement(sql);
        	ps.setString(1, multiple.getPaperId());
        	ps.setInt(2, multiple.getOptionNumber());
        	ps.setString(3, multiple.getQuestion());
        	ps.setString(4, multiple.getA());
        	ps.setString(5, multiple.getB());
        	ps.setString(6, multiple.getC());
        	ps.setString(7, multiple.getD());
        	ps.setString(8, multiple.getAnswer());
        	ps.setInt(9, multiple.getMark());
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
	 * 通过id号查询不定项选择题的信息
	 * */
	public Multiple getMultipleById(int id){
		
		Multiple m = new Multiple();
		String sql = "select * from multiple where id =? ";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				m.setA(rs.getString("A"));
				m.setAnswer(rs.getString("answer"));
				m.setB(rs.getString("B"));
				m.setC(rs.getString("C"));
				m.setD(rs.getString("D"));
				m.setId(id);
				m.setMark(rs.getInt("mark"));
				m.setOptionNumber(rs.getInt("optionNumber"));
				m.setPaperId(rs.getString("paperId"));
				m.setQuestion(rs.getString("question"));
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
		return m;
	}
	
	/**
	 * 更改多选题的题目信息
	 * */
	public boolean updateQuestion(int id, String question){
		
		boolean result = false;
		
		Multiple m = new Multiple();
		m = getMultipleById(id);
		if(null == (Integer)m.getId()) //如果要修改的记录不存在，则修改失败
			return false;
		
		String sql = "update multiple set question=? where id=?";
		
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
	 * 更改多选题的A选项信息
	 * */
	public boolean updateA(int id, String A){
		
		boolean result = false;
		
		Multiple m = new Multiple();
		m = getMultipleById(id);
		if(null == (Integer)m.getId()) //如果要修改的记录不存在，则修改失败
			return false;
		
		String sql = "update multiple set A=? where id=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, A);
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
	 * 更改多选题的B选项信息
	 * */
	public boolean updateB(int id, String B){
		
		boolean result = false;
		
		Multiple m = new Multiple();
		m = getMultipleById(id);
		if(null == (Integer)m.getId()) //如果要修改的记录不存在，则修改失败
			return false;
		
		String sql = "update multiple set B=? where id=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, B);
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
	 * 更改多选题的C选项信息
	 * */
	public boolean updateC(int id, String C){
		
		boolean result = false;
		
		Multiple m = new Multiple();
		m = getMultipleById(id);
		if(null == (Integer)m.getId()) //如果要修改的记录不存在，则修改失败
			return false;
		
		String sql = "update multiple set C=? where id=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, C);
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
	 * 更改多选题的D选项信息
	 * */
	public boolean updateD(int id, String D){
		
		boolean result = false;
		
		Multiple m = new Multiple();
		m = getMultipleById(id);
		if(null == (Integer)m.getId()) //如果要修改的记录不存在，则修改失败
			return false;
		
		String sql = "update multiple set D=? where id=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, D);
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
	 * 更改多选题的答案信息
	 * */
	public boolean updateAnswer(int id, String answer){
		
		boolean result = false;
		
		Multiple m = new Multiple();
		m = getMultipleById(id);
		if(null == (Integer)m.getId()) //如果要修改的记录不存在，则修改失败
			return false;
		
		String sql = "update multiple set answer=? where id=?";
		
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
	 * 更改多选题的分值信息
	 * */
	public boolean updateMark(int id, int mark){
		
		boolean result = false;
		
		Multiple m = new Multiple();
		m = getMultipleById(id);
		if(null == (Integer)m.getId()) //如果要修改的记录不存在，则修改失败
			return false;
		
		String sql = "update multiple set mark=? where id=?";
		
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
