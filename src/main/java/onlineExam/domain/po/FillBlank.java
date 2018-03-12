package onlineExam.domain.po;

public class FillBlank {
	
	public int id;
	public int questionNumber;
	public String paperId;
	public String question;
	public String answer;
	public int mark;
	
	public FillBlank(){
		
	}
	
	public FillBlank(int questionNumber, String paperId, String question,int mark) {
		super();
		this.questionNumber = questionNumber;
		this.paperId = paperId;
		this.question = question;
		this.mark = mark;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
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
	
	public FillBlank(String paperId, int questionNumber,String question,int mark) {		
			
		this.question = question;
		this.mark = mark;		
		this.paperId = paperId;
		this.questionNumber = questionNumber;
		
	}	
	
	public FillBlank setFillBlank(String paperId, int questionNumber,String question,int mark) {
		
		FillBlank f = new FillBlank();	
		f.setQuestion(question);
		f.setMark(mark);
		f.setPaperId(paperId);
		f.setQuestionNumber(questionNumber);		
		return f;
	}	
	
	public static void main(String[] args) {
		String paperId = "fe";
		int questionNumber = 1;
		String question = "ejfjoe";		
		int mark = 10;
		FillBlank f = new FillBlank(paperId,questionNumber,question,mark);
		System.out.println(f.getQuestion());
		
	}

}
