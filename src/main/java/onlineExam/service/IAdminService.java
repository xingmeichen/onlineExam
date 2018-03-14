/**
 * 
 */
package onlineExam.service;

import java.util.List;

import onlineExam.domain.po.Student;
import onlineExam.domain.po.Teacher;

/***
 * Description: </br>
 * @author chenxingmei
 * @date 2018年3月14日 
 */
public interface IAdminService {

	/***
	 * Description: 查询教师信息列表</br>
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	List<Teacher> getListTeacher();

	/***
	 * Description: 查询学生信息列表</br>
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	List<Student> getListStudent();

}
