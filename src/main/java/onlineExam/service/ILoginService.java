/**
 * 
 */
package onlineExam.service;

import onlineExam.domain.po.Student;
import onlineExam.domain.po.Teacher;

/***
 * Description: </br>
 * @author chenxingmei
 * @date 2018年3月14日 
 */
public interface ILoginService {

	/***
	 * Description: </br>
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	Student loginStudent(String userId, String userPassword);

	/***
	 * Description: </br>
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	Teacher loginTeacher(String userId, String userPassword);

}
