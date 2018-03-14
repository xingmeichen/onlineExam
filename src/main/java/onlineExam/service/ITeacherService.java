/**
 * 
 */
package onlineExam.service;

import java.util.ArrayList;
import java.util.List;

import onlineExam.domain.po.Paper;
import onlineExam.domain.po.Result;
import onlineExam.domain.po.ResultView;
import onlineExam.domain.po.Teacher;
import onlineExam.persistent.PaperMapper;
import onlineExam.persistent.ResultMapper;
import onlineExam.persistent.TeacherMapper;

/***
 * Description: </br>
 * 
 * @author chenxingmei
 * @date 2018年3月14日
 */
public interface ITeacherService {

	/***
	 * Description: 获取一个学生的信息</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public Teacher getOneTeacher(String teacherId);

	/***
	 * Description: 修改教师编号， 若修改成功返回true 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean updateTeacherId(String teacherId, String newTeacherId);

	/***
	 * Description: 修改教师登录密码， 若修改成功返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean updateTeacherPassword(String teacherId,
			String teacherPassword);

	/***
	 * Description: 修改教师姓名， 若修改成功返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean updateTeacherName(String teacherId, String teacherName);

	/***
	 * Description: 修改教师性别， 若修改成功返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean updateTeacherSex(String teacherId, String teacherSex);

	/***
	 * Description: 修改教师院系， 若修改成功返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean updateTeacherDept(String teacherId, String teacherDept);

	/***
	 * Description: 修改教师职务， 若修改成功返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean updateTeacherPost(String teacherId, String teacherPost);

	/***
	 * Description: 根据出卷人查找考卷信息</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public List<Paper> getListPaperByPaperMaker(String paperMaker);

	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public List<Result> getResultByPaperId(String paperId);

	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public List<ResultView> getResultView(String paperId, String studentId,
			String questionType);

	/***
	 * Description: 将ResultView中的学生得分grade设置为result中的mark</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean signMarkFromResultViewToResult(String studentId,
			String paperId, int questionNumber, int grade);
}
