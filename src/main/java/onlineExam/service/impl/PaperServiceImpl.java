package onlineExam.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import onlineExam.domain.po.Essay;
import onlineExam.domain.po.FillBlank;
import onlineExam.domain.po.Judge;
import onlineExam.domain.po.Multiple;
import onlineExam.domain.po.Paper;
import onlineExam.domain.po.Single;
import onlineExam.persistent.EssayMapper;
import onlineExam.persistent.FillBlankMapper;
import onlineExam.persistent.JudgeMapper;
import onlineExam.persistent.MultipleMapper;
import onlineExam.persistent.PaperMapper;
import onlineExam.persistent.ResultMapper;
import onlineExam.persistent.SingleMapper;

public class PaperServiceImpl {
	
	PaperMapper pdao = new PaperMapper();
	
	/**
	 *获取试卷列表
	 * */
	public List<Paper> getListPaper(){
		
		List<Paper> list = new ArrayList<Paper>();
		list = pdao.getPaper();
		return list;
	}
	
	/**
	 * 获取试卷的基本信息
	 * */
	public Paper getPaperInf(String paperId){
		
		Paper p = new Paper();
		p = pdao.getPaperByPaperId(paperId);
		return p; 		
	}
	
	/**
	 * 判断当前时间是否是考试时间
	 * （1）如果返回-1表示还未到考试时间
	 * （2）如果返回0表示正在考试中
	 * （3）如果返回1表示考试已经结束
	 * @throws SQLException 
	 * */
	public int ifExamTime(String paperId) throws SQLException{
		
		int result = 0;
		PaperMapper pdao = new PaperMapper();
		Paper paper = new Paper();
		paper = pdao.getPaperByPaperId(paperId);
		 //HH表示采用24小时制，如果是hh表示采用12小时制
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-MM-ss");
		String date =sdf.format(new Date()); /*获取当前系统的时间并将其格式化*/
		Timestamp now = Timestamp.valueOf(date); /*将当前系统时间转换成Timestamp类型，使其与数据库中的时间类型相对应*/
		Timestamp start = paper.getPaperStartTime();
		Timestamp end = paper.getPaperEndTime();
		if( (now.compareTo(start))>=0 && now.compareTo(end)<=0)
			result = 0;
		else if(now.compareTo(start)<0)
			result = -1;
		else if(now.compareTo(end)>0)
			result = 1;
		return result;
	}
	
	/**
	 * 获取所有考试科目,并且按照考试尚未开始，考试中，考试已经结束三类分开保存
	 * */

	
	/**
	 * 获取正处于考试时间范围的所有考试科目的试卷信息
	 * @throws SQLException 
	 * */	
	public List<Paper> getCurrentExam(){
		
		List<Paper> list = new ArrayList<Paper>();
		list = pdao.getCurrenExam(); 
		return list;
	}
	
	/**
	 * 查询尚未开始的考试科目的信息
	 * */
	public List<Paper> getNotStartExam(){
		
		List<Paper> list = new ArrayList<Paper>();
		list = pdao.getNotStartExam();
		return list;		
	}
	
	/**
	 * 查询已经结束的考试科目信息
	 * */
	public List<Paper> getEndExam(){
		
		List<Paper> list = new ArrayList<Paper>();
		list = pdao.getEndExam();
		return list;		
	}
	
	/*查询尚未结束的考试信息*/
	public List<Paper> getNotEndExam(){
		List<Paper> list = new ArrayList<Paper>();
		list = pdao.getNotEndExam();
		return list;
	}
	
	public List<Single> getListSingle(String paperId){
		
		List<Single> listSingle = new ArrayList<Single>();
		SingleMapper singleDao = new SingleMapper();
		listSingle = singleDao.getSinglePaperByPaperId(paperId);
		return listSingle;
	}
	
	public List<Multiple> getListMultiple(String paperId){
		
		List<Multiple> listMultiple = new ArrayList<Multiple>();
		MultipleMapper multipleDao = new MultipleMapper();
		listMultiple = multipleDao.getMultipleOptionByPaperId(paperId);
		return listMultiple;
	}
	
	public List<Judge> getListJudge(String paperId){
		
		/*判断题查询*/
		List<Judge> listJudge = new ArrayList<Judge>();
		JudgeMapper judgeDao = new JudgeMapper();
		listJudge = judgeDao.getJudgeByPaperId(paperId); 
		return listJudge;
	}
	
	public List<FillBlank> getListFillBlank(String paperId){
		
		/*填空题查询*/		
		List<FillBlank> listFill = new ArrayList<FillBlank>();
		FillBlankMapper fillDao = new FillBlankMapper();
		listFill = fillDao.getFillBlankByPaperId(paperId);
		return listFill;
	}
	
	public List<Essay> getListEssay(String paperId){
		
		/*问答题查询*/
		List<Essay> listEssay = new ArrayList<Essay>();
		EssayMapper essayDao = new EssayMapper();
		listEssay = essayDao.getEssayQuestionByPaperId(paperId);
		return listEssay;
		
	}
	
	/**
	 * 客观题由系统统一打分
	 * */
	public boolean signMarkBySystem(String studentId){
		
		boolean result = false;
		ResultMapper rdao = new ResultMapper();
		boolean s = rdao.signSingleOptionMark(studentId);
		if(s)
			result = true;
		boolean m = rdao.signMultipleOptionMark(studentId);
		if(m)
			result = true;		
		boolean j = rdao.signJudgeQuestionMark(studentId);
		if(j)
			result = true;
		return result;
	}	
	
	/**
	 *主观题由老师打分
	 * */
	public boolean signMarkByTeacher(String studentId,String paperId,int questionNumber,int mark){
		
		boolean result = false;
		ResultMapper rdao  = new ResultMapper();
		boolean f = rdao.signFillBlankMark(studentId, paperId, questionNumber, mark);
		if(f)
			result = true;
		boolean e = rdao.signEssayMark(studentId, paperId, questionNumber, mark);
		if(e)
			result = true;
		return result;		
	}
	
	/**
	 * 老师添加试卷到系统中
	 * */ 
	public boolean addPaper(String paperId,String paperName,String courseId,String paperSubject,
			String paperMaker,Timestamp paperStartTime,Timestamp paperEndTime,String paperDuration,int mark){	
		Paper paper = new Paper(paperId, paperName, courseId, paperSubject, paperMaker, paperStartTime, paperEndTime, paperDuration, mark);
		PaperMapper pdao = new PaperMapper();
		boolean result = pdao.addPaper(paper);
		return result;
	}
	
	/**
	 * 向系统中添加单选题*/	
	public boolean addSingle(String paperId,int questionNumber,String question,
			String A,String B,String C,String D,String answer,int mark){
		
		Single single = new Single(paperId, questionNumber, question, A, B, C, D, answer, mark);
		SingleMapper sdao = new SingleMapper();		
		int i = sdao.addSingle(single);
		if(i>0)
			return true;
		else 
			return false;
		
	}
	
	/**
	 * 将多选题添加到系统中*/
	public boolean addMultiple(String paperId,int questionNumber,String question,
			String A,String B,String C,String D,String answer,int mark){
		
		Multiple multiple= new Multiple(paperId, questionNumber, question, A, B, C, D, answer, mark);
		MultipleMapper mdao = new MultipleMapper();		
		int i = mdao.addMultiple(multiple);
		if(i>0)
			return true;
		else 
			return false;
		
	}
	
	/**
	 * 判断题添加到系统中*/
	public boolean addJudge(String paperId,int questionNumber,String question,String answer,int mark){
		
		Judge judge= new Judge(paperId, questionNumber, question, answer, mark);
		JudgeMapper jdao = new JudgeMapper();		
		int i = jdao.addJudge(judge);
		if(i>0)
			return true;
		else 
			return false;
	}
	
	/**
	 * 将填空题添加到系统中*/
	public boolean addFillBlank(String paperId, int questionNumber, 
			String question,int mark){
		
		FillBlank fillBlank= new FillBlank(paperId, questionNumber, question, mark);
		FillBlankMapper fdao = new FillBlankMapper();		
		int i = fdao.addFillBlank(fillBlank);
		if(i>0)
			return true;
		else 
			return false;
	}	

	
	/**
	 * 将问答题添加到系统中*/
	public boolean addEssay(String paperId, int questionNumber, 
			String question,int mark){		
		Essay essay= new Essay(paperId, questionNumber, question,mark);
		EssayMapper edao = new EssayMapper();		
		int i = edao.addEssay(essay);
		if(i>0)
			return true;
		else 
			return false;
	}
		
	/**
	 * 学生提交试卷
	 * */	
	public boolean doExam(String studentId,String paperId,
			int questionNumber,String answer,String questionType){
	
		ResultMapper rdao = new ResultMapper();
		boolean result = rdao.saveResult(studentId,paperId,questionNumber,answer,questionType);
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PaperServiceImpl paction = new PaperServiceImpl();
/*		String paperId = "p2";
		String paperName = "2015学年秋季学期数据库原理期末考试";
		String courseId = "c2" ;
		String paperSubject ="数据库原理";
		String paperMaker = "t2";
		Timestamp paperStartTime = Timestamp.valueOf("2015-12-24 8:00:00");
		Timestamp paperEndTime = Timestamp.valueOf("2016-01-09 8:00:00");
		int paperDuration = 7200;
		int mark = 100;
		boolean result = paction.addPaper(paperId, paperName, courseId, paperSubject, paperMaker, paperStartTime, paperEndTime, paperDuration, mark);

		System.out.println(result);*/
		Paper p = new Paper(); 
		Multiple m = new Multiple();
		List<Multiple> list = new ArrayList<Multiple>();
		list = paction.getListMultiple("p1");
		System.out.println(list.size());
		
		for(int i=0; i<list.size(); i++){
			m = list.get(i);
			System.out.println(m.getAnswer());
		}
	}


}










