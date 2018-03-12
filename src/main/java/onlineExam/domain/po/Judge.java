package onlineExam.domain.po;

public class Judge {
	
	public int id;
	public String paperId;
	public int questionNumber;
	public String question;
	public String answer;
	public int mark;
	
	public Judge(){
		
	}
	
	public Judge(String paperId, int questionNumber, String question,
			String answer, int mark) {
		super();
		this.paperId = paperId;
		this.questionNumber = questionNumber;
		this.question = question;
		this.answer = answer;
		this.mark = mark;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Judge setJudge(String paperId, int questionNumber,
			String question, String answer, int mark) {
		Judge judge = new Judge();
		judge.setAnswer(answer);
		judge.setMark(mark);
		judge.setPaperId(paperId);
		judge.setQuestion(question);
		judge.setQuestionNumber(questionNumber);
		return judge;
		
	}	

	
}
