package onlineExam.memo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Paper {
	
	public int id; //编号	
	public int problemSum;  //题目数量
	public int score; //总分
	public double difficulty; //难度系数
	public double adapt; //适应度
	public double  kpCoverage; //知识点分布	
	public List<Problem> problemList; //题目信息
	int [] eachTypeCount = {10,10,10,3,3}; //每份考卷都是10道单选题，10道不定项选择题，10道判断题，3道填空题，3道问答题
	
	public String paprName; //考卷名称
	public String courseId; //考卷对应的课程号
	public String paperSubject; //考试科目
	public String paperMaker; //出卷人
	public Timestamp startTime; //考试开始时间
	public Timestamp endTime;  //考试结束时间
	public String paperDuration;  //表示考试时长
	
	//不带参数的构造函数
	Paper(){
			this.id = 0;
			this.problemSum = 0;
			this.score = 0;
			this.difficulty = 0.00;
			this.adapt = 0.00;
			this.kpCoverage = 0.00;
			this.problemList = new ArrayList<Problem>();		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProblemSum() {
		return problemSum;
	}
	public void setProblemSum(int problemSum) {
		this.problemSum = problemSum;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public double getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}
	public double getAdapt() {
		return adapt;
	}
	public void setAdapt(double adapt) {
		this.adapt = adapt;
	}
	public double getKpCoverage() {
		return kpCoverage;
	}
	public void setKpCoverage(double kpCoverage) {
		this.kpCoverage = kpCoverage;
	}
	public List<Problem> getProblemList() {
		return problemList;
	}
	public void setProblemList(List<Problem> problemList) {
		this.problemList = problemList;
	}
	public String getPaprName() {
		return paprName;
	}
	public void setPaprName(String paprName) {
		this.paprName = paprName;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getPaperSubject() {
		return paperSubject;
	}
	public void setPaperSubject(String paperSubject) {
		this.paperSubject = paperSubject;
	}
	public String getPaperMaker() {
		return paperMaker;
	}
	public void setPaperMaker(String paperMaker) {
		this.paperMaker = paperMaker;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getPaperDuration() {
		return paperDuration;
	}
	public void setPaperDuration(String paperDuration) {
		this.paperDuration = paperDuration;
	}

	
}
