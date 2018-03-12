package onlineExam.domain.po;

public class Multiple {
	
	public int id;
	public String paperId;
	public int questionNumber;
	public String question;
	public String A;
	public String B;
	public String C;
	public String D;
	public String answer;
	public int mark;
	
	public Multiple(){
		
	}	
	
	public Multiple(String paperId, int questionNumber, String question,
			String a, String b, String c, String d, String answer, int mark) {
		super();
		this.paperId = paperId;
		this.questionNumber = questionNumber;
		this.question = question;
		this.A = a;
		this.B = b;
		this.C = c;
		this.D = d;
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
	public int getOptionNumber() {
		return questionNumber;
	}
	public void setOptionNumber(int optionNumber) {
		this.questionNumber = optionNumber;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getA() {
		return A;
	}
	public void setA(String a) {
		A = a;
	}
	public String getB() {
		return B;
	}
	public void setB(String b) {
		B = b;
	}
	public String getC() {
		return C;
	}
	public void setC(String c) {
		C = c;
	}
	public String getD() {
		return D;
	}
	public void setD(String d) {
		D = d;
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
	
	public Multiple setMultiple(String paperId,int questionNumber,String question,
			String a,String b,String c,String d,String answer,int mark){
		
		Multiple multiple = new Multiple();
		multiple.setA(A);
		multiple.setAnswer(answer);
		multiple.setB(b);
		multiple.setC(c);
		multiple.setD(d);
		multiple.setMark(mark);
		multiple.setOptionNumber(questionNumber);
		multiple.setPaperId(paperId);
		multiple.setQuestion(question);
		return multiple;
		
	}
	
}
