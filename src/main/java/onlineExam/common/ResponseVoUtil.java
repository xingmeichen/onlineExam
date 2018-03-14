/**
 * 
 */
package onlineExam.common;

import java.util.StringTokenizer;

import ch.qos.logback.core.subst.Tokenizer;

/***
 * Description: </br>
 * @author chenxingmei
 * @date 2017年12月30日 
 */
public class ResponseVoUtil {

	public static ResponseVo getSuccess() {
		ResponseVo responseVo = new ResponseVo();
		responseVo.setSuccess(true);
		return responseVo;
	}
	
	public static ResponseVo getFail() {
		ResponseVo responseVo = new ResponseVo();
		responseVo.setSuccess(false);
		return responseVo;
	}
	
	public static ResponseVo getFail(CommonError error) {
		ResponseVo responseVo = new ResponseVo();
		responseVo.setSuccess(false);
		responseVo.setErrorCode(error.errorCode);
		responseVo.setMessage(error.errorMsg);
		return responseVo;
	}
	
	public void change (StringBuffer ss1, StringBuffer ss2) {
		ss1.append(" World");
		ss2 = ss1;
		System.out.println("ss2的值为：" + ss2);			
	}
	
	public static void main(String[] args) {
		ResponseVoUtil util = new ResponseVoUtil();
		StringTokenizer tokenize = new StringTokenizer("Hi, hello world. How are you?");
		while(tokenize.hasMoreTokens()) {
			System.out.println(tokenize.nextToken());
		}
	}
}
