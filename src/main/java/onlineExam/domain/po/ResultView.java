package onlineExam.domain.po;

/**
 * 鎴愮哗瑙嗗浘
 * 閫氳繃杩欎釜瑙嗗浘鍙互寰楀埌瀛︾敓鐨勭瓟棰樼粨鏋滐紝浠ュ強瀵瑰簲鐨勮瘯鍗凤紝鑰冮 锛屾槸鍚﹀凡缁忕粺璁″垎鏁扮瓑绛�
  */
public class ResultView {
	
	public String studentId;    //鑰冪敓瀛﹀彿
	public String studentName;  //鑰冭瘯濮撳悕
	public String paperId;      //鑰冨嵎缂栧彿
	public int questionNumber;  //棰樺彿
	public String question;   //棰樼洰
	public String answer;	 //绛旈缁撴灉
	public int mark;   //璇曢鎬诲垎
	public int grade; //瀛︾敓寰楀垎
	public String signStatus; //鎵撳垎鐘舵�侊紝濡傛灉宸茬粡鎵撳垎涓篩锛屽皻鏈墦鍒嗕负N
	public String quetionType;  //鑰冮鐨勭被鍨�
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getSignStatus() {
		return signStatus;
	}
	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}
	public String getQuetionType() {
		return quetionType;
	}
	public void setQuetionType(String quetionType) {
		this.quetionType = quetionType;
	}	
	
}
