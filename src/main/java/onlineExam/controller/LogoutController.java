package onlineExam.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		String userType = (String) req.getSession().getAttribute("userType");

		if (null != userType && userType.equals("student")) {
			req.getSession().removeAttribute("student");
			resp.sendRedirect("../login.jsp");
		}

		else if (null != userType && userType.equals("teacher")) {
			req.getSession().removeAttribute("teacher");
			resp.sendRedirect("../login.jsp");
		} else if (null != userType && userType.equals("admin")) {
			req.getSession().removeAttribute("admin");
			resp.sendRedirect("../login.jsp");
		} else
			resp.sendRedirect("../login.jsp");

	}

}
