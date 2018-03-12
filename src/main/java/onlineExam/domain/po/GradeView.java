package onlineExam.domain.po;

/**
 * 杩欐槸涓�涓垚缁╄鍥撅紝
 * 璁板綍浜嗚�冪敓鐨勮�冭瘯鎴愮哗,浠ュ強瀵瑰簲鐨勮�冨嵎锛岃�冭瘯绉戠洰*/

public class GradeView {
	
	public int id;
	public String studentId;  //鑰冪敓瀛﹀彿	
	public String studentName; //鑰冪敓濮撳悕
	public String paperId;     //鑰冪敓鐨勮�冨嵎缂栧彿
	public String courseId;    //鑰冨嵎瀵瑰簲鐨勮绋嬪彿
	public int mark;          //鑰冪敓鐨勮�冭瘯鎬诲垎
	public String paperSubject;  //鑰冭瘯鐨勭鐩�
	public String paperName;      //鑰冨嵎鍚嶇О
	public String paperMaker;   //鑰冭瘯瀵瑰簲鐨勫嚭鍗�

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPaperMaker() {
		return paperMaker;
	}
	public void setPaperMaker(String paperMaker) {
		this.paperMaker = paperMaker;
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
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int grade) {
		this.mark = grade;
	}
	public String getPaperSubject() {
		return paperSubject;
	}
	public void setPaperSubject(String paperSubject) {
		this.paperSubject = paperSubject;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	
	
	public static void main(String[] args) {
	
	}

}
