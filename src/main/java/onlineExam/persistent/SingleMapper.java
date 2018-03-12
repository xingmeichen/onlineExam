package onlineExam.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import onlineExam.common.DB;
import onlineExam.domain.po.Single;

public class SingleMapper {
	
	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;	
	
	/**
	 * 查询单选题的所有信息
	 * @throws SQLException 
	 * */
	public List<Single> getSingleOption(){
		
		List<Single> list = new ArrayList<Single>();
		String sql = "select * from single";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Single singleOption = new Single();
				singleOption.setA(rs.getString("A"));
				singleOption.setAnswer(rs.getString("answer"));
				singleOption.setB(rs.getString("B"));
				singleOption.setC(rs.getString("C"));
				singleOption.setD(rs.getString("D"));
				singleOption.setId(rs.getInt("id"));
				singleOption.setMark(rs.getInt("mark"));
				singleOption.setOptionNumber(rs.getInt("questionNumber"));
				singleOption.setPaperId(rs.getString("paperId"));
				singleOption.setQuestion(rs.getString("question"));
				singleOption.setAnswer(rs.getString("answer"));
				list.add(singleOption);
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
	 * 通过试卷编号查询试卷的某些信息（主要用于答卷）
	 * */
	public List<Single> getSinglePaperByPaperId(String paperId) {
		
		List<Single> list = new ArrayList<Single>();
		String sql = "select * from single where paperId=? ";
		con = DB.getConn();		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paperId);
			rs = ps.executeQuery();
			while(rs.next()){
				Single singleOption = new Single();
				singleOption.setA(rs.getString("A"));			
				singleOption.setB(rs.getString("B"));
				singleOption.setC(rs.getString("C"));
				singleOption.setD(rs.getString("D"));
				singleOption.setId(rs.getInt("id"));
				singleOption.setMark(rs.getInt("mark"));
				singleOption.setOptionNumber(rs.getInt("questionNumber"));
				singleOption.setPaperId(rs.getString("paperId"));
				singleOption.setQuestion(rs.getString("question"));
				singleOption.setAnswer(rs.getString("answer"));
				list.add(singleOption);
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
	 * 找出某套试卷的某道单选题*/
	public Single getOneSingle(String paperId, int questionNumber){
		
		Single singleOption = new Single();
		String sql = "select * from single where paperId=? and questionNumber=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paperId);
			ps.setInt(2, questionNumber);
			rs = ps.executeQuery();
			if(rs.next()){				
				singleOption.setA(rs.getString("A"));			
				singleOption.setB(rs.getString("B"));
				singleOption.setC(rs.getString("C"));
				singleOption.setD(rs.getString("D"));
				singleOption.setId(rs.getInt("id"));
				singleOption.setMark(rs.getInt("mark"));
				singleOption.setOptionNumber(rs.getInt("questionNumber"));
				singleOption.setPaperId(rs.getString("paperId"));
				singleOption.setQuestion(rs.getString("question"));
				singleOption.setAnswer(rs.getString("answer"));
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
		return singleOption;
		
	}
	
	/**
	 * 添加单选题到系统中
	 * */
	public int addSingle(Single single){
		
		int result = 0;
		String sql = "insert into single("
				+ "paperId,"
				+ "questionNumber,"
				+ "question,"
				+ "A,B,C,D,"
				+ "answer,"
				+ "mark)values(?,?,?,?,?,?,?,?,?)";
		
		Single s = new Single();
		s = getOneSingle(single.getPaperId(),single.getOptionNumber());
		if(s.getPaperId() != null) //如果该到题目在数据库中已经存在，则插入失败
			return 0;
        con = DB.getConn();
        try {
        	ps = con.prepareStatement(sql);
        	ps.setString(1,single.getPaperId());
        	ps.setInt(2, single.getOptionNumber());
        	ps.setString(3, single.getQuestion());
        	ps.setString(4, single.getA());
        	ps.setString(5, single.getB());
        	ps.setString(6, single.getC());
        	ps.setString(7, single.getD());
        	ps.setString(8, single.getAnswer());
        	ps.setInt(9, single.getMark());
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
	 * 根据id号查找一条single记录
	 * */
	public Single getSingleById(int id){
		
		Single s = new Single();
		String sql = "select * from single where id=?";
		
		con = DB.getConn();
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			while(rs.next()){
				s.setA(rs.getString("A"));
				s.setB(rs.getString("B"));
				s.setC(rs.getString("C"));
				s.setD(rs.getString("D"));
				s.setAnswer(rs.getString("answer"));
				s.setId(rs.getInt("id"));
				s.setMark(rs.getInt("mark"));
				s.setOptionNumber(rs.getInt("optionNumber"));
				s.setPaperId(rs.getString("paperId"));
				s.setQuestion(rs.getString("question"));
			}
			if(null != rs)
				rs.close();
			if(null != ps)
				ps.close();
			if(null != con)
				con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return s;
	}
	
	/**
	 * 更改单选题的题目*/
	public boolean updateQuestion(int id, String question){
		
		boolean result = false;
		String sql = "update single set question=? where id=?";
		
		/*首先查询该记录是否存在，如果不存在，则修改失败*/
		Single s = new Single();
		int sid = s.getId();
		if( null == ((Integer)sid) )
			return false;
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, question);
			ps.setInt(2,id);
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
	
	/**
	 * 更改单选题的A选项*/
	public boolean updateA(int id, String A){
		
		boolean result = false;
		String sql = "update single set A=? where id=?";
		
		/*首先查询该记录是否存在，如果不存在，则修改失败*/
		Single s = new Single();
		int sid = s.getId();
		if( null == ((Integer)sid) )
			return false;
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, A);
			ps.setInt(2,id);
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
	
	/**
	 * 更改单选题的B选项*/
	public boolean updateB(int id, String B){
		
		boolean result = false;
		String sql = "update single set B=? where id=?";
		
		/*首先查询该记录是否存在，如果不存在，则修改失败*/
		Single s = new Single();
		int sid = s.getId();
		if( null == ((Integer)sid) )
			return false;
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, B);
			ps.setInt(2,id);
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
	
	/**
	 * 更改单选题的C选项*/
	public boolean updateC(int id, String C){
		
		boolean result = false;
		String sql = "update single set C=? where id=?";
		
		/*首先查询该记录是否存在，如果不存在，则修改失败*/
		Single s = new Single();
		int sid = s.getId();
		if( null == ((Integer)sid) )
			return false;
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, C);
			ps.setInt(2,id);
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
	
	/**
	 * 更改单选题的D选项*/
	public boolean updateD(int id, String D){
		
		boolean result = false;
		String sql = "update single set D=? where id=?";
		
		/*首先查询该记录是否存在，如果不存在，则修改失败*/
		Single s = new Single();
		int sid = s.getId();
		if( null == ((Integer)sid) )
			return false;
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, D);
			ps.setInt(2,id);
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
	
	/**
	 * 更改单选题的答案*/
	public boolean updateAnswer(int id, String answer){
		
		boolean result = false;
		String sql = "update single set answer=? where id=?";
		
		/*首先查询该记录是否存在，如果不存在，则修改失败*/
		Single s = new Single();
		int sid = s.getId();
		if( null == ((Integer)sid) )
			return false;
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, answer);
			ps.setInt(2,id);
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
	
	/**
	 * 更改单选题的分值*/
	public boolean updateMark(int id, int mark){
		
		boolean result = false;
		String sql = "update single set mark=? where id=?";
		
		/*首先查询该记录是否存在，如果不存在，则修改失败*/
		Single s = new Single();
		int sid = s.getId();
		if( null == ((Integer)sid) )
			return false;
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mark);
			ps.setInt(2,id);
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
		
		SingleMapper sdao = new SingleMapper();
		String s = "";
		boolean result1 = !s.equals("");
		boolean result2 = !(s.equals(""));
		System.out.println(result1+" "+result2);
	
	}
}
