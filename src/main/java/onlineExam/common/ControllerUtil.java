/**
 * 
 */
package onlineExam.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * Description: </br>
 * @author chenxingmei
 * @date 2018年1月21日 
 */
public class ControllerUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerUtil.class);
	
    /*** 
     * 获取 request 中 json 字符串的内容 
     *  
     * @param request 
     * @return : <code>byte[]</code> 
     * @throws IOException 
     */  
    public static String getRequestJsonString(HttpServletRequest request)  
            throws IOException {  
        String submitMehtod = request.getMethod();  
        // GET  
        if (submitMehtod.equals("GET")) {  
            return new String(request.getQueryString().getBytes("iso-8859-1"),"utf-8").replaceAll("%22", "\"");  
        // POST  
        } else {  
            return getRequestPostStr(request);  
        }  
    }  
  
    /**       
     * 描述:获取 post 请求的 byte[] 数组 
     * <pre> 
     * 举例： 
     * </pre> 
     * @param request 
     * @return 
     * @throws IOException       
     */  
    public static byte[] getRequestPostBytes(HttpServletRequest request) {  
        int contentLength = request.getContentLength();  
        if(contentLength<0){  
            return null;  
        }  
        byte buffer[] = new byte[contentLength];  
        for (int i = 0; i < contentLength;) {
            int readlen = 0;
			try {
				readlen = request.getInputStream().read(buffer, i,  
				        contentLength - i);
			} catch (IOException e) {
				e.printStackTrace();
			}  
            if (readlen == -1) {  
                break;  
            }  
            i += readlen;  
        }  
        return buffer;  
    }  
  
    /**       
     * 描述:获取 post 请求内容 
     * <pre> 
     * 举例： 
     * </pre> 
     * @param request 
     * @return 
     * @throws IOException       
     */  
    public static String getRequestPostStr(HttpServletRequest request) {  
        byte buffer[] = getRequestPostBytes(request);  
        String charEncoding = request.getCharacterEncoding();  
        if (charEncoding == null) {  
            charEncoding = "UTF-8";  
        }  
        String result = null;
        try {
        	result = new String(buffer, charEncoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
        return result;
    } 
}
