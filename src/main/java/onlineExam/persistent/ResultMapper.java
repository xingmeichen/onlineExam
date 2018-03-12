package onlineExam.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import onlineExam.common.DB;
import onlineExam.domain.po.GradeView;
import onlineExam.domain.po.Result;
import onlineExam.domain.po.ResultView;

public class ResultMapper {
	
	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	/**
	 * 查询答题结果的所有信息
	 * @throws SQLException 
	 * */
	public List<Result> getResult() {
		
		List<Result> list = new ArrayList<Result>();
		String sql = "select * from result";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Result result = new Result();
				result.setAnswer(rs.getString("answer"));
				result.setId(rs.getInt("id"));
				result.setMark(rs.getInt("mark"));
				result.setPaperId(rs.getString("paperId"));
				result.setQuestionNumber(rs.getInt("questionNumber"));
				result.setStudentId(rs.getString("studentId"));
				result.setQuestionType(rs.getString("questionType"));
				result.setSignStatus(rs.getString("signStatus"));
				result.setQuestionNumber(rs.getInt("questionNumber"));
				list.add(result);
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
	 * 根据学生学号以及考卷编号查询答题结果，
	 * 此方法主要用于在学生答题过程中随时可以查看自己的答题情况
	 * */
	public List<Result> getResultByStudentIdAndPaperId(String studentId,String paperId){
		
		List<Result> list = new ArrayList<Result>();
		String sql = "select questionNumber,answer "
				+ " from result "
				+ " where studentId=? and paperId=? order by questionNumber asc";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentId);
			ps.setString(2, paperId);
			rs = ps.executeQuery();
			while(rs.next()){
				Result r = new Result();
				r.setQuestionNumber(rs.getInt("questionNumber"));
				r.setAnswer(rs.getString("answer"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 根据出卷人查找答题结果,
	 * 出卷人可以由试卷编号对应上result
	 * 按每个学生的答题结果分组
	 * */
	public List<Result> getResultOfPaperMaker(String paperId){
		
		List<Result> list = new ArrayList<Result>();
		String sql = "select distinct studentId, paperId from result where paperId=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paperId);			
			rs = ps.executeQuery();
			while(rs.next()){
				Result r = new Result();
				r.setPaperId(rs.getString("paperId"));
				r.setStudentId(rs.getString("studentId"));
				list.add(r);
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
	
	public List<ResultView> getResultView(String paperId, String studentId,String questionType){
		
		List<ResultView> list = new ArrayList<ResultView>();
		String sql = "";
		
		if(questionType.equals("single")){
			sql = "select result.answer,result.questionNumber,"
					+ " result.paperId,result.studentId,result.signStatus,result.questionType,"
					+ " single.question,single.mark "
					+ " from result,single "
					+ " where result.paperId=single.paperId and "
					+ " result.questionNumber=single.questionNumber and "
					+ " result.paperId=? and studentId=? order by questionNumber";
		}
		else if(questionType.equals("multiple")){
			sql = "select result.answer,result.questionNumber,"
					+ " result.paperId,result.studentId,result.signStatus,result.questionType,"
					+ " multiple.question,multiple.mark "
					+ " from result,multiple "
					+ " where result.paperId=multiple.paperId and "
					+ " result.questionNumber=multiple.questionNumber and "
					+ " result.paperId=? and studentId=? order by questionNumber";
		}
		else if(questionType.equals("judge")){
			sql = "select result.answer,result.questionNumber,"
					+ " result.paperId,result.studentId,result.signStatus,result.questionType,"
					+ " judge.question,judge.mark "
					+ " from result,judge "
					+ " where result.paperId=judge.paperId and "
					+ " result.questionNumber=judge.questionNumber and "
					+ " result.paperId=? and studentId=? order by questionNumber";
		}
		else if(questionType.equals("fill_blank")){
			sql = "select result.answer,result.questionNumber,"
					+ "result.paperId,result.studentId,result.signStatus,result.questionType,"
					+ " fill_blank.question,fill_blank.mark "
					+ " from result,fill_blank "
					+ " where result.paperId=fill_blank.paperId and "
					+ " result.questionNumber=fill_blank.questionNumber and "
					+ " result.paperId=? and studentId=? order by questionNumber";
		}
		else if(questionType.equals("essay")){
			sql = "select result.answer,result.questionNumber,"
					+ "result.paperId,result.studentId,result.signStatus,result.questionType,"
					+ " essay.question,essay.mark "
					+ " from result,essay "
					+ " where result.paperId=essay.paperId and "
					+ " result.questionNumber=essay.questionNumber and "
					+ " result.paperId=? and studentId=? order by questionNumber";
			}
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, paperId);
			ps.setString(2, studentId);
			rs = ps.executeQuery();
			while(rs.next()){
				ResultView rv = new ResultView();
				rv.setStudentId(rs.getString("studentId"));
				rv.setPaperId(rs.getString("paperId"));
				rv.setAnswer(rs.getString("answer"));
				if(questionType.equals("fill_blank")){
					String question = rs.getString("question");
					rv.setQuestion(question);
				}
				else
					rv.setQuestion(rs.getString("question"));
				rv.setQuestionNumber(rs.getInt("questionNumber"));
				rv.setMark(rs.getInt("mark"));
				rv.setSignStatus(rs.getString("signStatus"));
				rv.setQuetionType(rs.getString("questionType"));
				list.add(rv);
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
	 * 将答题结果插入到数据库中
	 * * */
	public boolean insertResult(String studentId,String paperId,int questionNumber,String answer,String questionType){
		
		boolean flag = false;
		int j=0;
		String sql = "insert into result(studentId,paperId,questionNumber,answer,mark,signStatus,questionType) "
				+ "values(?,?,?,?,?,?,?)";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentId);
			ps.setString(2, paperId);
			ps.setInt(3, questionNumber);
			ps.setString(4, answer);
			ps.setInt(5, 0); // 还未批改试卷的情况下，默认得分为0
			ps.setString(6, "N");
			ps.setString(7, questionType);
			j = ps.executeUpdate();
			if (j != 0)
				flag = true;
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 更新重新提交的答题结果
	 * */
	public boolean updateResult(String studentId,String paperId,int questionNumber,String answer){
		
		boolean flag = false;
		int j=0;
		String sql = "update result set answer=?,mark=?,signStatus=? "
				+ "where studentId=? and paperId=? and questionNumber=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, answer);
			ps.setInt(2, 0);  //重新更改结果之后，尚未批改试卷，得分为0
			ps.setString(3, "N");  //重新更改结果之后，尚未批改试卷，批改状态为N
			ps.setString(4, studentId);
			ps.setString(5, paperId);
			ps.setInt(6, questionNumber);
			j = ps.executeUpdate();
			if (j != 0)
				flag = true;
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * 将答题结果保存到数据中的时候首先判断该记录是否已经存在
	 * 如果已经存在，则更改该记录
	 * 如果尚未存在该记录，则将一条记录插入到结果表result中
	 * */
	public boolean saveResult(String studentId,String paperId,int questionNumber,String answer,String questionType){
		
		boolean flag = false;
		String sql = "select * from result where "
				+ "studentId=? and "
				+ "paperId=? and "
				+ "questionNumber=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentId);
			ps.setString(2, paperId);
			ps.setInt(3, questionNumber);
			rs = ps.executeQuery();
			if(rs.next()) //记录已经存在，则更新记录
				flag = updateResult(studentId,paperId,questionNumber,answer);			
			else 
				flag = insertResult(studentId,paperId,questionNumber,answer,questionType);
			
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 对于单选题由系统自动登记分数
	 * */	
	public boolean signSingleOptionMark(String studentId){		
		
		boolean flag = false;
		
		String sql = "update result,single set result.mark = single.mark,result.signStatus=? "
				+ "where result.paperId = single.paperId and "
				+ "result.questionNumber = single.questionNumber and "
				+ "result.answer = single.answer and "
				+ "result.studentId=?";		
		con = DB.getConn();
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "Y");
			ps.setString(2, studentId);
			i = ps.executeUpdate();			
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(i!=0)
			flag = true;		
		return flag;
	}
	
	/**
	 * 对于单选题由系统自动登记分数
	 * 如果单选题的答题结果和参考答案不一致，则得分记为0分，同时判分状态也改为Y
	 * */	
	public boolean signSingleOptionMarkN(String studentId){		
		
		boolean flag = false;
		
		String sql = "update result,single set result.mark=?,result.signStatus=? "
				+ "where result.paperId = single.paperId and "
				+ "result.questionNumber = single.questionNumber and "
				+ "result.answer <> single.answer and "
				+ "result.studentId=?";		
		con = DB.getConn();
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, 0);
			ps.setString(1, "Y");
			ps.setString(2, studentId);
			i = ps.executeUpdate();			
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(i!=0)
			flag = true;		
		return flag;
	}
	
	/**
	 * 对于多选题，由系统自动登记分数
	 * */
	public boolean signMultipleOptionMark(String studentId){		
		
		boolean flag = false;
		
		String sql = "update result,multiple set result.mark = multiple.mark,result.signStatus=? "
				+ "where result.paperId = multiple.paperId and "
				+ "result.questionNumber = multiple.questionNumber and "
				+ "result.answer = multiple.answer and "
				+ "result.studentId=?";
		
		con = DB.getConn();
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "Y");
			ps.setString(2, studentId);
			i = ps.executeUpdate();
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(i!=0)
			flag = true;		
		return flag;
	}
	
	/**
	 * 对于多选题，由系统自动登记分数
	 * 如果多选题的答题结果与参考答案不一致，则得分记为0，同时判分状态也改为Y
	 * */
	public boolean signMultipleOptionMarkN(String studentId){		
		
		boolean flag = false;
		
		String sql = "update result,multiple set result.mark=?,result.signStatus=? "
				+ "where result.paperId = multiple.paperId and "
				+ "result.questionNumber = multiple.questionNumber and "
				+ "result.answer <> multiple.answer and "
				+ "result.studentId=?";
		
		con = DB.getConn();
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, 0);
			ps.setString(2, "Y");
			ps.setString(3, studentId);
			i = ps.executeUpdate();
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(i!=0)
			flag = true;		
		return flag;
	}
	
	/**
	 * 对于判断题，由系统自动登记分数
	 * */
	public boolean signJudgeQuestionMark(String studentId){		
		
		boolean flag = false;
		
		String sql = "update result,judge set result.mark = judge.mark,result.signStatus=? "
				+ "where result.paperId = judge.paperId and "
				+ "result.questionNumber = judge.questionNumber and "
				+ "result.answer = judge.answer and "
				+ "result.studentId=?";
		
		con = DB.getConn();
		int i=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "Y");
			ps.setString(2, studentId);
			i = ps.executeUpdate();
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(i!=0)
			flag = true;		
		return flag;
	}
	
	/**
	 * 对于判断题，由系统自动登记分数 
	 * 如果判断题的答题结果和参考答案不一致，则得分记为0，同时判分状态改为Y
	 * */
	public boolean signJudgeQuestionMarkN(String studentId){		
		
		boolean flag = false;
		
		String sql = "update result,judge set result.mark=?,result.signStatus=? "
				+ "where result.paperId = judge.paperId and "
				+ "result.questionNumber = judge.questionNumber and "
				+ "result.answer <> judge.answer and "
				+ "result.studentId=?";
		
		con = DB.getConn();
		int i=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1,0);
			ps.setString(2, "Y");
			ps.setString(3, studentId);
			i = ps.executeUpdate();
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(i!=0)
			flag = true;		
		return flag;
	}
	
	/**
	 * 填空题由老师批阅
	 * */
	public boolean signFillBlankMark(String studentId,String paperId,int questionNumber,int mark){
		
		boolean flag = false;
		String sql = "update result set mark=?,signStatus=? "
				+ "where studentId=? and "
				+ "paperId=? and"
				+ "questionNumber=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mark);			
			ps.setString(2, "Y");
			ps.setString(3, studentId);
			ps.setString(4, paperId);
			ps.setInt(5, questionNumber);			
			int i = ps.executeUpdate();
			if(i != 0)
				flag = true;
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 问答题由老师批阅
	 * */
	public boolean signEssayMark(String studentId,String paperId,int questionNumber,int mark){

		boolean flag = false;
		String sql = "update result set mark=?, signStatus=?"
				+ "where studentId=? and "
				+ "paperId=? and "
				+ "questionNumber=? ";
		con = DB.getConn();
		try {
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, mark);			
			ps.setString(2, "Y");
			ps.setString(3, studentId);
			ps.setString(4, paperId);
			ps.setInt(5, questionNumber);			
			int i = ps.executeUpdate();
			if(i != 0)
				flag = true;
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return flag;
	}
	
/*	public Grade sumGrade(String studentId,String paperId){
		
		Grade g  = new Grade();
		String sql = "select sum(mark) where studentId=? and paperId=?";		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentId);
			ps.setString(2, paperId);
			rs = ps.executeQuery();
			if(rs.next()){
				g.setGrade(rs.getInt(1));
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
		g.setStudentId(studentId);
		g.setPaperId(paperId);	
		return g;
	}*/
	
	/**
	 * 将ResultView中的学生得分grade设置为result中的mark
	 * */
	public boolean signMarkFromResultViewToResult(String studentId,String paperId,int questionNumber,int grade){
		
		boolean result = false;
		String sql = "update result set mark=?,signStatus=?"
				+ " where result.studentId=? and "
				+ " result.paperId=? and "
				+ " result.questionNumber=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, grade);
			ps.setString(2, "Y");
			ps.setString(3, studentId);
			ps.setString(4, paperId);
			ps.setInt(5, questionNumber);
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
	 * 得到成绩视图的所有内容*/
	public List<GradeView> insertGradeView(){
		
		List<GradeView> list = new ArrayList<GradeView>();
		
		String sql = "select result.id,result.paperId,result.mark,result.studentId,"
				+ " paper.paperName,paper.paperSubject,paper.courseId,paper.paperMaker,"
				+ " student.studentName "
				+ " where paper.paperId = result.paperId and "
				+ " student.studentId = result.studentId and "
				+ "result.signStatus=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "Y");
			rs = ps.executeQuery();
			while(rs.next()){
				GradeView gv = new GradeView();
				gv.setId(rs.getInt("id"));
				gv.setCourseId(rs.getString("courseId"));
			    gv.setMark(rs.getInt("mark"));			    
			    gv.setPaperId(rs.getString("paperId"));
			    gv.setPaperName(rs.getString("paperName"));
			    gv.setPaperSubject(rs.getString("paperSubject"));
			    gv.setStudentId(rs.getString("studentId"));
			    gv.setStudentName(rs.getString("studentName"));
			    gv.setPaperMaker(rs.getString("paperMaker"));			   
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
	 * 查询某学生的某套考卷的所有答题结果记录数目,
	 * 学号和考卷编号这两个字段可以确定一个学生的某套考卷答题结果
	 */	
	public int countResult(String studentId,String paperId){
		
		int count = 0;
		String sql = "select count(id) from result "
				+ " where studentId=? and paperId=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentId);
			ps.setString(2, paperId);
			rs = ps.executeQuery();
			while(rs.next()){
				count = rs.getInt("count(id)");
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
		return count;
	}
	
	/**
	 * 查询某个学生的某套试卷的批阅状态为"Y"的数目
	 * */
	public int countSignStatus(String studentId,String paperId){
		
		int count = 0;
		String sql = "select count(id) from result "
				+ " where studentId=? and paperId=? and signStatus=?";
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentId);
			ps.setString(2, paperId);
			ps.setString(3, "Y");
			rs = ps.executeQuery();
			while(rs.next()){
				count = rs.getInt("count(id)");
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
		
		return count;
	}
	
	/**
	 * 判断某个学生的某套考卷是否已经批阅完毕，
	 * 如果批阅完毕，则返回true，否则返回false
	 * 答题结果的数目刚好等于批阅试卷的状态为"Y"的数目，则批阅完毕，否则尚未批阅完毕
	 * */
	public boolean ifFinishSignMark(String studentId,String paperId){
		
		boolean result = false;
		if( countSignStatus(studentId,paperId) == countResult(studentId,paperId) )
			result = true;
		return result;
	}

	
	public static void main(String[] args) {
		
		ResultMapper rdao = new ResultMapper();
		int i = rdao.countSignStatus("s1", "p1");
		System.out.println(i);
	}
}











