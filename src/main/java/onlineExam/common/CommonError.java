/**
 * 
 */
package onlineExam.common;

/***
 * Description: 系统通用错误</br>
 * @author chenxingmei
 * @date 2018年1月18日 
 */
public enum CommonError {

	/**
	 * SYSTEM_ERROR *
	 */
	SYSTEM_ERROR(110110, "系统错误"),
	
	/**
	 * LOGIN_ERROR *
	 */
	LOGIN_ERROR(110111, "登录错误"),
	
	/**
	 * PARAM_ERROR *
	 */
	PARAM_ERROR(110112, "参数非法");
	
	/**
	 * errorCode 错误码*
	 */
	public int errorCode;
	
	/**
	 * errorMsg 错误信息*
	 */
	public String errorMsg;
	
	CommonError(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
