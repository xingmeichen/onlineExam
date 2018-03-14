package onlineExam.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

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
import onlineExam.service.IPaperService;

public class PaperServiceImpl implements IPaperService {

	/**
	 * paperMapper *
	 */
	@Resource
	private PaperMapper paperMapper;	

	/**
	 * singleMapper *
	 */
	@Resource
	private SingleMapper singleMapper;

	/**
	 * multipleMapper *
	 */
	@Resource
	private MultipleMapper multipleMapper;	

	/**
	 * judgeMapper *
	 */
	@Resource
	private JudgeMapper judgeMapper;

	/**
	 * fillBlankMapper *
	 */
	@Resource
	private FillBlankMapper fillBlankMapper;
	
	/**
	 * esayMapper *
	 */
	@Resource
	private EssayMapper essayMapper;
	
	@Resource
	private ResultMapper resultMapper;



	/**
	 * 获取试卷列表
	 * */
	@Override
	public List<Paper> getListPaper() {

		List<Paper> list = new ArrayList<Paper>();
		list = paperMapper.getPaper();
		return list;
	}

	/**
	 * 获取试卷的基本信息
	 * */
	@Override
	public Paper getPaperInf(String paperId) {
		Paper p = new Paper();
		p = paperMapper.getPaperByPaperId(paperId);
		return p;
	}

	/**
	 * 判断当前时间是否是考试时间 （1）如果返回-1表示还未到考试时间 （2）如果返回0表示正在考试中 （3）如果返回1表示考试已经结束
	 * 
	 * @throws SQLException
	 * */
	@Override
	public int ifExamTime(String paperId) throws SQLException {

		int result = 0;

		Paper paper = new Paper();
		paper = paperMapper.getPaperByPaperId(paperId);
		// HH表示采用24小时制，如果是hh表示采用12小时制
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-MM-ss");
		String date = sdf.format(new Date()); /* 获取当前系统的时间并将其格式化 */
		Timestamp now = Timestamp.valueOf(date); /*
												 * 将当前系统时间转换成Timestamp类型，
												 * 使其与数据库中的时间类型相对应
												 */
		Timestamp start = paper.getPaperStartTime();
		Timestamp end = paper.getPaperEndTime();
		if ((now.compareTo(start)) >= 0 && now.compareTo(end) <= 0)
			result = 0;
		else if (now.compareTo(start) < 0)
			result = -1;
		else if (now.compareTo(end) > 0)
			result = 1;
		return result;
	}

	/**
	 * 获取正处于考试时间范围的所有考试科目的试卷信息
	 * 
	 * @throws SQLException
	 * */
	@Override
	public List<Paper> getCurrentExam() {

		List<Paper> list = new ArrayList<Paper>();
		list = paperMapper.getCurrenExam();
		return list;
	}

	/**
	 * 查询尚未开始的考试科目的信息
	 * */
	@Override
	public List<Paper> getNotStartExam() {

		List<Paper> list = new ArrayList<Paper>();
		list = paperMapper.getNotStartExam();
		return list;
	}

	/**
	 * 查询已经结束的考试科目信息
	 * */
	@Override
	public List<Paper> getEndExam() {

		List<Paper> list = new ArrayList<Paper>();
		list = paperMapper.getEndExam();
		return list;
	}

	/* 查询尚未结束的考试信息 */
	@Override
	public List<Paper> getNotEndExam() {
		List<Paper> list = new ArrayList<Paper>();
		list = paperMapper.getNotEndExam();
		return list;
	}

	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public List<Single> getListSingle(String paperId) {

		List<Single> listSingle = new ArrayList<Single>();
		listSingle = singleMapper.getSinglePaperByPaperId(paperId);
		return listSingle;
	}

	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public List<Multiple> getListMultiple(String paperId) {

		List<Multiple> listMultiple = new ArrayList<Multiple>();
		listMultiple = multipleMapper.getMultipleOptionByPaperId(paperId);
		return listMultiple;
	}

	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public List<Judge> getListJudge(String paperId) {

		/* 判断题查询 */
		List<Judge> listJudge = new ArrayList<Judge>();
		listJudge = judgeMapper.getJudgeByPaperId(paperId);
		return listJudge;
	}

	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public List<FillBlank> getListFillBlank(String paperId) {

		/* 填空题查询 */
		List<FillBlank> listFill = new ArrayList<FillBlank>();		
		listFill = fillBlankMapper.getFillBlankByPaperId(paperId);
		return listFill;
	}

	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public List<Essay> getListEssay(String paperId) {

		/* 问答题查询 */
		List<Essay> listEssay = new ArrayList<Essay>();
		listEssay = essayMapper.getEssayQuestionByPaperId(paperId);
		return listEssay;

	}

	/***
	 * Description: 客观题由系统统一打分</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean signMarkBySystem(String studentId) {

		boolean result = false;
		boolean s = resultMapper.signSingleOptionMark(studentId);
		if (s)
			result = true;
		boolean m = resultMapper.signMultipleOptionMark(studentId);
		if (m)
			result = true;
		boolean j = resultMapper.signJudgeQuestionMark(studentId);
		if (j)
			result = true;
		return result;
	}

	/***
	 * Description: 主观题由老师打分</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean signMarkByTeacher(String studentId, String paperId,
			int questionNumber, int mark) {

		boolean result = false;
		boolean f = resultMapper.signFillBlankMark(studentId, paperId, questionNumber,
				mark);
		if (f)
			result = true;
		boolean e = resultMapper
				.signEssayMark(studentId, paperId, questionNumber, mark);
		if (e)
			result = true;
		return result;
	}

	/***
	 * Description: 老师添加试卷到系统中</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean addPaper(String paperId, String paperName, String courseId,
			String paperSubject, String paperMaker, Timestamp paperStartTime,
			Timestamp paperEndTime, String paperDuration, int mark) {
		Paper paper = new Paper(paperId, paperName, courseId, paperSubject,
				paperMaker, paperStartTime, paperEndTime, paperDuration, mark);
		boolean result = paperMapper.addPaper(paper);
		return result;
	}

	/***
	 * Description: 向系统中添加单选题</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean addSingle(String paperId, int questionNumber,
			String question, String A, String B, String C, String D,
			String answer, int mark) {

		Single single = new Single(paperId, questionNumber, question, A, B, C,
				D, answer, mark);
		int i = singleMapper.addSingle(single);
		if (i > 0)
			return true;
		else
			return false;
	}

	/***
	 * Description: 将多选题添加到系统中</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean addMultiple(String paperId, int questionNumber,
			String question, String A, String B, String C, String D,
			String answer, int mark) {

		Multiple multiple = new Multiple(paperId, questionNumber, question, A,
				B, C, D, answer, mark);
		int i = multipleMapper.addMultiple(multiple);
		if (i > 0)
			return true;
		else
			return false;

	}

	/***
	 * Description: 判断题添加到系统中</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean addJudge(String paperId, int questionNumber,
			String question, String answer, int mark) {

		Judge judge = new Judge(paperId, questionNumber, question, answer, mark);
		int i = judgeMapper.addJudge(judge);
		if (i > 0)
			return true;
		else
			return false;
	}

	/***
	 * Description: 将填空题添加到系统中</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean addFillBlank(String paperId, int questionNumber,
			String question, int mark) {

		FillBlank fillBlank = new FillBlank(paperId, questionNumber, question,
				mark);
		int i = fillBlankMapper.addFillBlank(fillBlank);
		if (i > 0)
			return true;
		else
			return false;
	}

	/***
	 * Description:将问答题添加到系统中 </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean addEssay(String paperId, int questionNumber,
			String question, int mark) {
		Essay essay = new Essay(paperId, questionNumber, question, mark);
		int i = essayMapper.addEssay(essay);
		if (i > 0)
			return true;
		else
			return false;
	}

	/***
	 * Description: 学生提交试卷</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean doExam(String studentId, String paperId, int questionNumber,
			String answer, String questionType) {

		boolean result = resultMapper.saveResult(studentId, paperId, questionNumber,
				answer, questionType);
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PaperServiceImpl paction = new PaperServiceImpl();
		Multiple m = new Multiple();
		List<Multiple> list = new ArrayList<Multiple>();
		list = paction.getListMultiple("p1");
		System.out.println(list.size());

		for (int i = 0; i < list.size(); i++) {
			m = list.get(i);
			System.out.println(m.getAnswer());
		}
	}
}
