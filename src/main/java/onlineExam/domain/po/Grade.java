package onlineExam.domain.po;

public class Grade {
	
	public int id;
	public int grade;
	public String studentId;
	public String paperId;
	
	/*鏃犲弬鏁扮殑鏋勯�犲嚱鏁�*/
	public Grade(){
		
	}
	
	/*甯﹀舰鍙傜殑鏋勯�犲嚱鏁�*/
	public Grade(int grade, String studentId, String paperId) {
		super();
		this.grade = grade;
		this.studentId = studentId;
		this.paperId = paperId;
	}
	/*甯﹀舰鍙傜殑鏋勯�犲嚱鏁�*/
	public Grade(String studentId, String paperId) {
		super();		
		this.studentId = studentId;
		this.paperId = paperId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

}
