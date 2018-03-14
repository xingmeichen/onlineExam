/**
 * 
 */
package onlineExam.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.springframework.util.StringUtils;

/***
 * Description: </br>
 * @author chenxingmei
 * @date 2018年1月5日 
 */
public class FileUtil {
	
	/***
	 * Description: 创建文件</br>
	 * @author chenxingmei
	 * @date 2018年1月5日
	 */
	public static File createFile(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			return file;
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	
	/***
	 * Description: 读文件</br>
	 * @author chenxingmei
	 * @date 2018年1月5日
	 */
	public static void readFile(File file) {
		if (null == file) {
			return;
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String content = reader.readLine();
			System.out.println(content);
			reader.close(); 			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	

	/***
	 * Description: 写文件</br>
	 * @author chenxingmei
	 * @date 2018年1月5日
	 */
	public static void writeFile(String source, File file) {
		if (null == file || StringUtils.isEmpty(source)) {
			return;
		}
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			writer.write(source);		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != writer) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {		
		File file = createFile("E:\\test.txt");
		readFile(file);
		writeFile("World", file);
		readFile(file);
	}

}
