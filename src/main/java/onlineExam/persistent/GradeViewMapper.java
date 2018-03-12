package onlineExam.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import onlineExam.common.DB;
import onlineExam.domain.po.GradeView;
import onlineExam.domain.po.Paper;
import onlineExam.domain.po.Student;

public class GradeViewMapper {	
	
	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	/**
	 * 查询所有考生的成绩单(管理员用户，教师用户用到)
	 * 一个学号，一个考卷编号，会对应一个考生的一门考试的成绩
	 * */
	public List<GradeView> getGradeView(List<Student> listStudent,List<Paper> listPaper){
		
		List<GradeView> list = new ArrayList<GradeView>();
		
		String sql = "select grade.grade,grade.id,grade.studentId,grade.paperId,"
				+ " student.studentName,"
				+ " paper.courseId,paper.paperId,paper.paperName,paper.paperMaker,paper.paperSubject "
				+ " from grade,student,paper "
				+ " where grade.studentId=student.studentId and "
				+ " grade.paperId = paper.paperId and "
				+ " grade.studentId=? and grade.paperId=?";
		
/*		String sql = "select sum(result.mark),result.id,result.studentId,"
				+ " student.studentName,"
				+ " paper.courseId,paper.paperId,paper.paperName,paper.paperMaker,paper.paperSubject "
				+ " from result,student,paper "
				+ " where result.studentId=student.studentId and "
				+ " result.paperId=paper.paperId and "
				+ " result.studentId=? and paper.paperId=? and signStatus=?";		
*/
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			Student student = new Student();
			for(int i=0; i<listStudent.size(); i++){
				student = listStudent.get(i);
				String studentId = student.getStudentId();
				if(null != studentId){
					ps.setString(1, studentId);
					Paper paper = new Paper();
					for(int j=0; j<listPaper.size();j++){
						paper = listPaper.get(j);
						String paperId = paper.getPaperId();
						if(null != paperId){
							ps.setString(2, paperId);							
							rs = ps.executeQuery();
							while(rs.next()){
								GradeView gv = new GradeView();
								gv.setId(rs.getInt("id"));
								gv.setCourseId(rs.getString("courseId"));
							    gv.setMark(rs.getInt("grade"));			    
							    gv.setPaperId(rs.getString("paperId"));
							    gv.setPaperName(rs.getString("paperName"));
							    gv.setPaperSubject(rs.getString("paperSubject"));
							    gv.setStudentId(student.studentId);
							    gv.setStudentName(rs.getString("student.studentName"));
							    gv.setPaperMaker(rs.getString("paperMaker"));			   
							    list.add(gv);
							}
						}
					}
				}
				
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
	 * 查询某个学生的所有成绩单
	 * 一个学号，一个考卷编号，会对应一个考生的一门考试的成绩
	 * 一个学生会有多门考试，所以，需要根据每门考试的试卷编号查询一门成绩
	 * 从而最终得到考试结果*/
	public List<GradeView> getGradeViewOfStudent(String studentId,List<Paper> listPaper){
		
		List<GradeView> list = new ArrayList<GradeView>();
		
		String sql = "select grade.grade,grade.id,grade.studentId,grade.paperId,"
				+ " student.studentName,"
				+ " paper.courseId,paper.paperId,paper.paperName,paper.paperMaker,paper.paperSubject "
				+ " from grade,student,paper "
				+ " where grade.studentId=student.studentId and "
				+ " grade.paperId = paper.paperId and "
				+ " grade.studentId=? and grade.paperId=?";
		
/*		String sql = "select sum(result.mark),result.id,result.studentId,"
				+ " student.studentName,"
				+ " paper.courseId,paper.paperId,paper.paperName,paper.paperMaker,paper.paperSubject "
				+ " from result,student,paper "
				+ " where result.studentId=student.studentId and "
				+ " result.paperId=paper.paperId and "
				+ " result.studentId=? and paper.paperId=? and signStatus=?";	*/
		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, studentId);
			Paper paper = new Paper();
			for(int i=0; i<listPaper.size(); i++){
				paper = listPaper.get(i);
				String paperId = paper.getPaperId();
				if(null != paperId){
					ps.setString(2, paperId);
					rs = ps.executeQuery();
					while(rs.next()){
						GradeView gv = new GradeView();
						gv.setId(rs.getInt("id"));
						gv.setCourseId(rs.getString("courseId"));
					    gv.setMark(rs.getInt("sum(result.mark)"));			    
					    gv.setPaperId(rs.getString("paperId"));
					    gv.setPaperName(rs.getString("paperName"));
					    gv.setPaperSubject(rs.getString("paperSubject"));
					    gv.setStudentId(studentId);
					    gv.setStudentName(rs.getString("studentName"));
					    gv.setPaperMaker(rs.getString("paperMaker"));			   
					    list.add(gv);
					}
				}
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
	
	public static void main(String[] args){
		
		List<GradeView> list = new ArrayList<GradeView>();
		
		GradeViewMapper gvdao = new GradeViewMapper();
		List<Student> listStudent = new ArrayList<Student>();
		List<Paper> listPaper = new ArrayList<Paper>();
		StudentMapper sdao = new StudentMapper();
		listStudent = sdao.getStudent();
		System.out.println(listStudent.size());
		System.out.println();
		PaperMapper pdao = new PaperMapper();
		listPaper = pdao.getPaper();
		System.out.println(listPaper.size());
		System.out.println();
		list = gvdao.getGradeView(listStudent, listPaper);		
		if(null != list){
			GradeView gv = new GradeView();
			for(int i=0; i<list.size(); i++){
				gv = list.get(i);
				System.out.println(i);
				System.out.println(gv.getMark());
				System.out.println(gv.getStudentId());
				System.out.println(gv.getPaperId());				
				System.out.println("****************");
			}
		}
	}

}
