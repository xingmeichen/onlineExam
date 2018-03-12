package onlineExam.domain.po;

import java.util.List;
import java.util.ArrayList;

public class Problem {
	
	public int id;     //棰樼洰鐨勭紪鍙�
	public int type;   //棰樼洰鐨勭被鍨嬶紙1琛ㄧず鍗曢�夐锛�2琛ㄧず涓嶅畾椤归�夐锛�3琛ㄧず鍒ゆ柇棰橈紝4琛ㄧず濉┖棰橈紝5琛ㄧず闂瓟棰橈級
	public int score;  //棰樼洰鐨勫垎鍊�
	public double difficulty;  //棰樼洰鐨勯毦搴︾郴鏁�
	public List<Integer> points;   //棰樼洰鍖呭惈鐨勭煡璇嗙偣鐨勭紪鍙�
	
	//涓嶅甫鍙傛暟鐨勬瀯閫犲嚱鏁�
	Problem(){
		this.id = 0;
		this.type = 0;
		this.score = 0;
		this.difficulty = 0.00;
		this.points = new ArrayList<Integer>();		
	}
	
	//甯﹀弬鏁扮殑鏋勯�犲嚱鏁�
	Problem(Problem p){
		this.id = p.id;
		this.type = p.type;
		this.score = p.score;
		this.difficulty = p.difficulty;
		this.points = p.points;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}

	public List<Integer> getPoints() {
		return points;
	}

	public void setPoints(List<Integer> points) {
		this.points = points;
	}
	
	public static void main(String[] args) {
		
		Problem problem = new Problem();
		System.out.println(problem);				
	}
	
}
