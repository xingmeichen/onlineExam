package onlineExam.domain.po;

public class Teacher {
	
	public int id;
	public String teacherId;
    public String teacherPassword;
    public String teacherName;
    public String teacherSex;
    public String teacherDept;
    public String teacherPost;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherPassword() {
		return teacherPassword;
	}
	public void setTeacherPassword(String teacherPassword) {
		this.teacherPassword = teacherPassword;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teachrName) {
		this.teacherName = teachrName;
	}
	public String getTeacherSex() {
		return teacherSex;
	}
	public void setTeacherSex(String teacherSex) {
		this.teacherSex = teacherSex;
	}
	public String getTeacherDept() {
		return teacherDept;
	}
	public void setTeacherDept(String teachrDept) {
		this.teacherDept = teachrDept;
	}
	public String getTeacherPost() {
		return teacherPost;
	}
	public void setTeacherPost(String teacherPost) {
		this.teacherPost = teacherPost;
	}
    
    
}
