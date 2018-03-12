package onlineExam.memo;

import java.util.List;
import java.util.ArrayList;

public class Problem {
	
	public int id;     //题目的编号
	public int type;   //题目的类型（1表示单选题，2表示不定项选题，3表示判断题，4表示填空题，5表示问答题）
	public int score;  //题目的分值
	public double difficulty;  //题目的难度系数
	public List<Integer> points;   //题目包含的知识点的编号
	
	//不带参数的构造函数
	Problem(){
		this.id = 0;
		this.type = 0;
		this.score = 0;
		this.difficulty = 0.00;
		this.points = new ArrayList<Integer>();		
	}
	
	//带参数的构造函数
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
