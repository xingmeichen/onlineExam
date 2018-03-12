package onlineExam.domain.po;

public class CSView {

	public int id;
	//学号
	public String studentId; 
	//学生姓名
	public String studentName; 
	//课程号
	public String courseId; 
	//试卷编号
	public String paperId;  
	//试卷名称
	public String paperName; 
	//试卷对应考试科目
	public String paperSubject; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public String getPaperSubject() {
		return paperSubject;
	}
	public void setPaperSubject(String paperSubject) {
		this.paperSubject = paperSubject;
	}
}
