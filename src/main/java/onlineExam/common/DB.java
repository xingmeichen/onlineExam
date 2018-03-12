package onlineExam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	
	public static String driver = "com.mysql.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3306/online_exam?useUnicode=true&characterEncoding=utf-8";
	public static String user = "root";
	public static String password = "921010";
	
	/*获取数据库的连接*/
	public static Connection getConn(){
		
		Connection con = null;
		try {
			//加载驱动
			Class.forName(driver);	
			//建立连接
			con = DriverManager.getConnection(url, user, password);	//寤虹珛杩炴帴	
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}		
		return con;			
	}

}
