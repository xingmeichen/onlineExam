/**
 * 
 */
package onlineExam.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import onlineExam.domain.po.Essay;
import onlineExam.domain.po.FillBlank;
import onlineExam.domain.po.Judge;
import onlineExam.domain.po.Multiple;
import onlineExam.domain.po.Paper;
import onlineExam.domain.po.Single;

/***
 * Description: </br>
 * 
 * @author chenxingmei
 * @date 2018年3月14日
 */
public interface IPaperService {

	/***
	 * Description: 获取试卷列表</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public List<Paper> getListPaper();

	/***
	 * Description: 获取试卷的基本信息</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public Paper getPaperInf(String paperId);

	/**
	 * 判断当前时间是否是考试时间 （1）如果返回-1表示还未到考试时间 （2）如果返回0表示正在考试中 （3）如果返回1表示考试已经结束
	 * 
	 * @throws SQLException
	 * */
	public int ifExamTime(String paperId) throws SQLException;

	/***
	 * Description: 获取正处于考试时间范围的所有考试科目的试卷信息</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public List<Paper> getCurrentExam();

	/***
	 * Description: 查询尚未开始的考试科目的信息</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public List<Paper> getNotStartExam();

	/***
	 * Description: 查询已经结束的考试科目信息</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public List<Paper> getEndExam();

	/***
	 * Description: 查询尚未结束的考试信息</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public List<Paper> getNotEndExam();

	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public List<Single> getListSingle(String paperId);

	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public List<Multiple> getListMultiple(String paperId);

	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public List<Judge> getListJudge(String paperId);

	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public List<FillBlank> getListFillBlank(String paperId);

	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public List<Essay> getListEssay(String paperId);

	/***
	 * Description: 客观题由系统统一打分</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean signMarkBySystem(String studentId);

	/***
	 * Description: 主观题由老师打分</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean signMarkByTeacher(String studentId, String paperId,
			int questionNumber, int mark);

	/***
	 * Description: 老师添加试卷到系统中</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean addPaper(String paperId, String paperName, String courseId,
			String paperSubject, String paperMaker, Timestamp paperStartTime,
			Timestamp paperEndTime, String paperDuration, int mark);

	/***
	 * Description: 向系统中添加单选题</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean addSingle(String paperId, int questionNumber,
			String question, String A, String B, String C, String D,
			String answer, int mark);

	/***
	 * Description: 将多选题添加到系统中</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean addMultiple(String paperId, int questionNumber,
			String question, String A, String B, String C, String D,
			String answer, int mark);

	/***
	 * Description: 判断题添加到系统中</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean addJudge(String paperId, int questionNumber,
			String question, String answer, int mark);

	/***
	 * Description: 将填空题添加到系统中</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean addFillBlank(String paperId, int questionNumber,
			String question, int mark);

	/***
	 * Description:将问答题添加到系统中 </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean addEssay(String paperId, int questionNumber,
			String question, int mark);

	/***
	 * Description: 学生提交试卷</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean doExam(String studentId, String paperId, int questionNumber,
			String answer, String questionType);
}
