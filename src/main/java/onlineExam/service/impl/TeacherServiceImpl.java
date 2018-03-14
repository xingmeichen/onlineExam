package onlineExam.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import onlineExam.domain.po.Paper;
import onlineExam.domain.po.Result;
import onlineExam.domain.po.ResultView;
import onlineExam.domain.po.Teacher;
import onlineExam.persistent.PaperMapper;
import onlineExam.persistent.ResultMapper;
import onlineExam.persistent.TeacherMapper;
import onlineExam.service.ITeacherService;

/***
 * Description: </br>
 * 
 * @author chenxingmei
 * @date 2018年3月14日
 */
@Service
public class TeacherServiceImpl implements ITeacherService{

	/***
	 * Description: 获取一个学生的信息</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public Teacher getOneTeacher(String teacherId) {

		Teacher t = new Teacher();
		TeacherMapper tdao = new TeacherMapper();
		t = tdao.getOneTeacher(teacherId);
		return t;
	}

	/***
	 * Description: 修改教师编号， 若修改成功返回true 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean updateTeacherId(String teacherId, String newTeacherId) {
		TeacherMapper tdao = new TeacherMapper();
		return tdao.updateTeacherId(teacherId, newTeacherId);
	}

	/***
	 * Description: 修改教师登录密码， 若修改成功返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean updateTeacherPassword(String teacherId,
			String teacherPassword) {

		TeacherMapper tdao = new TeacherMapper();
		return tdao.updateTeacherPassword(teacherId, teacherPassword);
	}

	/***
	 * Description: 修改教师姓名， 若修改成功返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean updateTeacherName(String teacherId, String teacherName) {

		TeacherMapper tdao = new TeacherMapper();
		return tdao.updateTeacherName(teacherId, teacherName);
	}

	/***
	 * Description: 修改教师性别， 若修改成功返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean updateTeacherSex(String teacherId, String teacherSex) {

		TeacherMapper tdao = new TeacherMapper();
		return tdao.updateTeacherSex(teacherId, teacherSex);
	}

	/***
	 * Description: 修改教师院系， 若修改成功返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean updateTeacherDept(String teacherId, String teacherDept) {

		TeacherMapper tdao = new TeacherMapper();
		return tdao.updateTeacherDept(teacherId, teacherDept);
	}

	/***
	 * Description: 修改教师职务， 若修改成功返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean updateTeacherPost(String teacherId, String teacherPost) {

		TeacherMapper tdao = new TeacherMapper();
		return tdao.updateTeacherPost(teacherId, teacherPost);
	}

	/***
	 * Description: 根据出卷人查找考卷信息</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public List<Paper> getListPaperByPaperMaker(String paperMaker) {

		List<Paper> list = new ArrayList<Paper>();
		PaperMapper pdao = new PaperMapper();
		list = pdao.getPaperByPaperMaker(paperMaker);
		return list;
	}

	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public List<Result> getResultByPaperId(String paperId) {

		List<Result> list = new ArrayList<Result>();
		ResultMapper rdao = new ResultMapper();
		list = rdao.getResultOfPaperMaker(paperId);
		return list;
	}

	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public List<ResultView> getResultView(String paperId, String studentId,
			String questionType) {

		List<ResultView> list = new ArrayList<ResultView>();
		ResultMapper rdao = new ResultMapper();
		list = rdao.getResultView(paperId, studentId, questionType);
		return list;
	}

	/***
	 * Description: 将ResultView中的学生得分grade设置为result中的mark</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	@Override
	public boolean signMarkFromResultViewToResult(String studentId,
			String paperId, int questionNumber, int grade) {

		boolean result = false;
		ResultMapper rdao = new ResultMapper();
		result = rdao.signMarkFromResultViewToResult(studentId, paperId,
				questionNumber, grade);
		return result;
	}
}
