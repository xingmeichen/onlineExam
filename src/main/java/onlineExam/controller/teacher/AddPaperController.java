package onlineExam.controller.teacher;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Teacher;
import onlineExam.service.impl.PaperServiceImpl;

public class AddPaperController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {


		//设置编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		PaperServiceImpl paction = new PaperServiceImpl();
		
		String paperId = req.getParameter("paperId");
//		System.out.println(paperId);
		String paperName = req.getParameter("paperName");
//		System.out.println(paperName);
		String courseId = req.getParameter("courseId");
//		System.out.println(courseId);
        String paperSubject = req.getParameter("paperSubject");
//        System.out.println(paperSubject);
        Timestamp paperStartTime = Timestamp.valueOf(req.getParameter("paperStartTime"));
//        System.out.println(paperStartTime);
        Timestamp paperEndTime = Timestamp.valueOf(req.getParameter("paperEndTime"));
//        System.out.println(paperEndTime);
        String paperDuration =  req.getParameter("paperDuration");
//        System.out.println(paperDuration);
        int mark = Integer.parseInt(req.getParameter("mark"));
//        System.out.println(mark);
        
        Teacher teacher = (Teacher) req.getSession().getAttribute("teacher"); //从session中获取老师这个实体
        String paperMaker = teacher.getTeacherId();  
//        System.out.println(paperMaker);
        boolean result = paction.addPaper(paperId, paperName, courseId, paperSubject, paperMaker, paperStartTime, paperEndTime, paperDuration, mark);
        if(result)
        {
        	req.setAttribute("paperId", paperId);
        	req.setAttribute("message", "试卷信息保存成功！");
        	resp.sendRedirect("thome.jsp");
        }
        else {
        	req.setAttribute("message", "试卷信息保存失败");
        	resp.sendRedirect("error.jsp");	
        }
        		
	}	

}
