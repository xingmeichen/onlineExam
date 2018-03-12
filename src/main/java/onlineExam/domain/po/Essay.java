package onlineExam.domain.po;

public class Essay {
	
	public int id;
	public String paperId;
	public int questionNumber;
	public String question;
	public String answer;
	public int mark;	

	
	public Essay(){
		
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
	
	public Essay (String paperId, int questionNumber,
			String question, int mark) {
	   super();
       this.mark = mark;
       this.paperId = paperId;
       this.question = question;
       this.questionNumber = questionNumber;      
	}	
	
	public static void main(String[] args) {
		

	}

}
