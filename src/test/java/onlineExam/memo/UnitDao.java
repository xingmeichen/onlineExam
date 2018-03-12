package onlineExam.memo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 产生初始种群
 * */

public class UnitDao {

	public List<Paper> listUnit = null;  //初始种群所有个体（考卷）列表
	public Paper paper = null;         //初始种群中的考卷
	public Problem problem = null;             //初始群体中的problem类
	public int count = 10;             //初始种群个体数量
	public double kpcoverage = 0.50;    //期望试卷考点覆盖率
	public double difficulty = 0.50;    //期望试卷难度
	
	/**
	 * <summary> 初始种群    // </summary>
	 * <param name="count">个体数量</param>
	 * <param name="paper">期望试卷</param>
	 *  <returns>初始种群</returns>
	*/
	public List<Paper> populationInitialization() {

		listUnit = new ArrayList<Paper>();		
		Integer problemID;

		Random rand = new Random();

		/* 生成count个个体（即生成count套试卷） */
		for (int i = 0; i < count; i++) {
			paper = new Paper();
			problem = new Problem();
			paper.id = i + 1;
			paper.adapt = 0.00;

			/*
			 * 限定了每套试卷中各个题型的数量 
			 * j=0时，eachTypeCount[j]表示单项选择题的数量
			 * j=1时，eachTypeCount[j]表示不定项选择题的数量
			 * j=2时，eachTypeCount[j]表示判断题的数量
			 * j=3时，eachTypeCount[j]表示填空题的数量
			 * j=4时，eachTypeCount[j]表示问答题的数量
			 */
			for (int j = 0; j < paper.eachTypeCount.length; j++) {

				/* 如果j=0，表示选择的是单选题,单选题的题目编号是1到20 */
				if (j == 0) {
					int singleCount = 0;
					problemID = rand.nextInt(20);
					while (singleCount < paper.eachTypeCount[j]) {

						// 如果该题目还没有被抽取到
						if (!isContain(problemID, paper.problemList)) {
							singleCount++; // 则该类题型增1
							problem.setId(problemID); // 修改paper中的题目信息，这里只是修改了题目的id
							paper.problemList.add(problem); // 将题目添加到paper中
						}

					}

				}

				/* 如果j==1,表示抽取的是不定项选择题，不定项选择题的题目编号21到40 */
				else if (j == 1) {
					int multipleCount = 0;
					problemID = 21 + rand.nextInt(40) % (40 - 21 + 1);
					while (multipleCount < paper.eachTypeCount[j]) {

						// 如果该题目还没有被抽取到
						if (!isContain(problemID, paper.problemList)) {

							multipleCount++; // 则该类题型增1
							problem.setId(problemID); // 修改paper中的题目信息，这里只是修改了题目的id
							paper.problemList.add(problem); // 将题目添加到paper中
						}
					}
				}

				/* 如果j==2,表示抽取的是判断题，判断题的题目编号是41到60 */
				else if (j == 2) {

					int judgeCount = 0;
					problemID = 41 + rand.nextInt(60) % (60 - 41 + 1);
					while (judgeCount < paper.eachTypeCount[j]) { // 修改paper中的题目信息，这里只是修改了题目的id

						// 如果该题目还没有被抽取到
						if (!isContain(problemID, paper.problemList)) {
							judgeCount++; // 则该类题型增1
							problem.setId(problemID); // 修改paper中的题目信息，这里只是修改了题目的id
							paper.problemList.add(problem); // 将题目添加到paper中
						}
					}

				}

				/* 如果j==3,表示抽取的是填空题，问答题的题目编号61到80 */
				else if (j == 3) {
					int fillCount = 0;
					problemID = 61 + rand.nextInt(80) % (80 - 61 + 1);
					while (fillCount < paper.eachTypeCount[j]) {

						// 如果该题目还没有被抽取到
						if (!isContain(problemID, paper.problemList)) {

							fillCount++; // 则该类题型增1
							problem.setId(problemID); // 修改paper中的题目信息，这里只是修改了题目的id
							paper.problemList.add(problem); // 将题目添加到paper中
						}
					}
				}

				/* 如果j==4,表示抽取的是不定项选择题，不定项选择题的题目编号21到40 */
				else if (j == 4) {
					int essayCount = 0;
					problemID = 81 + rand.nextInt(100) % (100 - 81 + 1);
					while (essayCount < paper.eachTypeCount[j]) {

						// 如果该题目还没有被抽取到
						if (!isContain(problemID, paper.problemList)) {

							essayCount++; // 则该类题型增1
							problem.setId(problemID); // 修改paper中的题目信息，这里只是修改了题目的id
							paper.problemList.add(problem); // 将题目添加到paper中
						}
					}
				}

			}
			
			listUnit.add(paper);
		}
		

/*		// 计算知识点覆盖率及适应度
		listUnit = getKPCoverage(listUnit, paper);
		listUnit = getAdaptationDegree(listUnit, paper, kpcoverage, difficulty);
*/
		return listUnit;
	}
	
	/**检查i，是否包含在problemList中的id中
	 * 如果包含，则返回true，
	 * 否则返回false
	 * */
	public boolean isContain(Integer x, List<Problem> problemList){		
		
		boolean result = false;
		if(null == problemList)
			return false;
		else 
			for(int i=0; i<problemList.size(); i++){
				if(x == problemList.get(i).id)
					return true;
			}
		return result;
	}
	
	/**
	 * 计算考点的覆盖率
	 * */
	public List<Paper> getKPCoverage(List<Paper> listUnit, Paper paper){		
		
/*		 List<Integer> kp;
         for (int i = 0; i < count; i++)
         {
             kp = new ArrayList<Integer>();
             for(int j=0; j<listUnit.size(); j++){
            	 
             }
             unitList[i].ProblemList.ForEach(delegate(Problem p)
             {
                 kp.AddRange(p.Points);
             });

             //个体所有题目知识点并集跟期望试卷知识点交集
            var common = kp.Intersect(paper.Points);
             unitList[i].KPCoverage = common.Count() * 1.00 / paper.Points.Count;
         }
*/
		return listUnit;
	}
	
	/**
	 * 计算群体的适应度
	 * */
	public List<Paper> getAdaptationDegree(List<Paper>listUnit, Paper paper, double kpcoverage, double difficulty){
		
		return listUnit;
	}
	
	public static void main(String[] args){
		
		UnitDao unitDao = new UnitDao();
		
		long start = System.currentTimeMillis();
		System.out.println("Hello World");
		for(int i=1;i<=1000; i++){
			if(i%10 == 0)
				System.out.println();
			System.out.print(i+",");
		}
		System.out.println("***************");
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
}
