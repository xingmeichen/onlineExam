/**
 * 
 */
package onlineExam.service;

import onlineExam.domain.po.Student;

/***
 * Description: </br>
 * 
 * @author chenxingmei
 * @date 2018年3月14日
 */
public interface IStudentService {

	/***
	 * Description: </br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public Student getOneStudentByStudentId(String studentId);

	/***
	 * Description: 修改学号 修改成功返回true 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean updateStudentId(String studentId, String newstudentId);

	/***
	 * Description: 修改学生密码， 修改成功则返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean updateStudentPassword(String studentId,
			String studentPassword);

	/***
	 * Description: 修改学生姓名， 修改成功则返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean updateStudentName(String studentId, String studentName);

	/***
	 * Description: 修改学生性别， 修改成功则返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean updateStudentSex(String studentId, String studentSex);

	/***
	 * Description: 修改学生院系， 修改成功则返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean updateStudentDept(String studentId, String studentDept);

	/***
	 * Description: 修改学生院系， 修改成功则返回true, 否则返回false</br>
	 * 
	 * @author chenxingmei
	 * @date 2018年3月14日
	 */
	public boolean updateStudentMajor(String studentId, String studentMajor);
}
