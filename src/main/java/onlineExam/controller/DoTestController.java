package onlineExam.controller;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.common.CommonError;
import onlineExam.common.ResponseVo;
import onlineExam.common.ResponseVoUtil;
import onlineExam.domain.po.User;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
public class DoTestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DoTestController.class);
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public @ResponseBody ResponseVo test(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("登录开始");
		if (null == request) {
			LOGGER.info("request is null");
		}
		if (null == response) {
			LOGGER.info("response is null");
		}
		String ip = null;
//		if (request.getHeader("x-forwarded-for") == null) {//如果未通过nginx反向代理
//		    ip = request.getRemoteAddr();
//		} else {
//			//若通过nginx反向代理，获取客户端真实ip
//			ip = request.getHeader("x-forwarded-for");
//		}
		LOGGER.info("用户IP是：{}",ip);
		ResponseVo responseVo = ResponseVoUtil.getSuccess();
		try {
//			String string = ControllerUtil.getRequestJsonString(request);
//			LOGGER.info(string);
			
			
			request.setCharacterEncoding("UTF-8");
			//request.getContentType();
			response.setContentType("text/html");
			ServletInputStream servletInputStream = request.getInputStream();
			String paramString = IOUtils.toString(servletInputStream, "UTF-8");
			LOGGER.info(paramString);
//
//			JSONObject json = (JSONObject) JSON.parse(paramString);
	        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(servletInputStream, "UTF-8"));

			//JSONObject obj = (JSONObject) JSONObject.toJSON(input);
			//User param = JSONObject.toJavaObject(obj, User.class);
			//userServiceImpl.login(obj);
			//userOutServiceImpl.login(obj);

		} catch (Exception e) {
			LOGGER.error(CommonError.LOGIN_ERROR.errorMsg, request, response, e);
		}
		//Map<String, String[]> paramMap = request.getParameterMap();		
		//LOGGER.info("{}", paramMap.get("loginName"));
		responseVo = ResponseVoUtil.getSuccess();
		User user = new User();
		//JSONObject obj = (JSONObject) JSONObject.toJSON(user);
		//LOGGER.info("{}", obj.get("loginName"));
		LOGGER.info("登录结束");
		responseVo.setData(user);
		return responseVo;
	}
}
