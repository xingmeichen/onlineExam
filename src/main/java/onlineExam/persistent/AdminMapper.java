package onlineExam.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import onlineExam.common.DB;
import onlineExam.domain.po.Admin;

public class AdminMapper {
	
	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;	
	
	public List<Admin> getAdmin(){
		
		List<Admin> list = new ArrayList<Admin>();
		String sql = "select * from admin ";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Admin admin = new Admin();
				admin.setAdminId(rs.getString("adminId"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setAdminPassword(rs.getString("adminPassword"));
				admin.setId(rs.getInt("id"));
				list.add(admin);
			}
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	public Admin getOneAdmin(String adminId) {
		
		Admin admin = new Admin();
		String sql = "select * from admin where adminId=?";		
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, adminId);
			rs = ps.executeQuery();
			if(rs.next()){
				admin.setAdminId(rs.getString("adminId"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setAdminPassword(rs.getString("adminPassword"));
				admin.setId(rs.getInt("id"));
			}
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return admin;
	}
	
	public Admin queryLogin(String adminId,String adminPassword){
		
		Admin admin = new Admin();
		String sql = "select * from admin where adminId=? and adminPassword=?";
		con = DB.getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, adminId);
			ps.setString(2, adminPassword);
			rs = ps.executeQuery();
			if(rs.next()){
				admin.setAdminId(rs.getString("adminId"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setAdminPassword(rs.getString("adminPassword"));
				admin.setId(rs.getInt("id"));
			}
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return admin;

	}
	public static void main(String[] args) {
		
		AdminMapper adao = new AdminMapper();
		Admin admin = new Admin();
		admin = adao.getOneAdmin("a5");
		if(admin != null)
			System.out.println(admin.getAdminPassword());
		else
			System.out.println("admin为空");
	}

}
