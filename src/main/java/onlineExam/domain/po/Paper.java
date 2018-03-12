package onlineExam.domain.po;

import java.sql.Timestamp;

public class Paper {
	
	public int id;                //璇曞嵎id鍙�
	public String paperId;        //璇曞嵎缂栧彿
	public String paperName;      //璇曞嵎鍚嶇О
	public String courseId;       //鑰冭瘯绉戠洰璇剧▼鍙�
	public String paperSubject;   //鑰冨嵎绉戠洰
	public String paperMaker;     //鍑哄嵎浜� 锛屽嵆鑰佸笀鐨勬暀甯堢紪鍙�
	public Timestamp paperStartTime;   //鑰冭瘯鐨勫紑濮嬫椂闂�
	public Timestamp paperEndTime;     //鑰冭瘯鐨勭粨鏉熸椂闂�
	public String paperDuration;    //琛ㄧず鑰冭瘯鏃堕暱
	public int mark;            //棰樼洰鐨勫垎鍊�

	public Paper(){
		
	}
	
	public Paper(String paperId, String paperName, String courseId,
			String paperSubject, String paperMaker, Timestamp paperStartTime,
			Timestamp paperEndTime, String paperDuration, int mark) {
		super();
		this.paperId = paperId;
		this.paperName = paperName;
		this.courseId = courseId;
		this.paperSubject = paperSubject;
		this.paperMaker = paperMaker;
		this.paperStartTime = paperStartTime;
		this.paperEndTime = paperEndTime;
		this.paperDuration = paperDuration;
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
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
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
	public Timestamp getPaperStartTime() {
		return paperStartTime;
	}
	public void setPaperStartTime(Timestamp paperStartTime) {
		this.paperStartTime = paperStartTime;
	}
	public Timestamp getPaperEndTime() {
		return paperEndTime;
	}
	public void setPaperEndTime(Timestamp paperEndTime) {
		this.paperEndTime = paperEndTime;
	}
	public String getPaperDuration() {
		return paperDuration;
	}
	public void setPaperDuration(String paperDuration) {
		this.paperDuration = paperDuration;
	}

	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	
	public Paper setPaper(
			String paperId, 
			String paperName, // 璇曞嵎鍚嶇О
			String courseId, // 鑰冭瘯绉戠洰璇剧▼鍙�
			String paperSubject, // 鑰冨嵎绉戠洰
			String paperMaker, // 鍑哄嵎浜� 锛屽嵆鑰佸笀鐨勬暀甯堢紪鍙�
			Timestamp paperStartTime, // 鑰冭瘯鐨勫紑濮嬫椂闂�   鍏跺湪鏁版嵁搴撲腑鐨勮〃绀虹被鍨嬫槸锛歞atetime
			Timestamp paperEndTime, // 鑰冭瘯鐨勭粨鏉熸椂闂�        鍏跺湪鏁版嵁搴撲腑鐨勮〃绀虹被鍨嬫槸锛歞atetime
			String paperDuration, // 琛ㄧず鑰冭瘯鏃堕暱锛屼互鍒嗛挓涓哄崟浣�
			int mark) {
		Paper paper = new Paper();
		this.paperDuration = paperDuration;
		this.courseId = courseId;		
		this.mark = mark;
		this.paperEndTime = paperEndTime;
		this.paperId = paperId;
		this.paperMaker = paperMaker;
		this.paperName = paperName;
		this.paperStartTime = paperStartTime;
		this.paperSubject = paperSubject;	
		return paper;
	}

}
