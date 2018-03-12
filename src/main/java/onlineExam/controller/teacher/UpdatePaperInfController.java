package onlineExam.controller.teacher;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineExam.domain.po.Paper;
import onlineExam.domain.po.Teacher;
import onlineExam.persistent.PaperMapper;
import onlineExam.service.impl.PaperServiceImpl;

/**
 * 更改考卷的信息的servlet，但是要注意，只有尚未开始的考试才可以更改考卷信息
 * */

public class UpdatePaperInfController extends HttpServlet {

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
		PaperMapper pdao = new PaperMapper();
		
		String paperId = req.getParameter("paperId");
		System.out.println(paperId);
		
		String paperName = req.getParameter("paperName");
		if(null!=paperName && !paperName.equals(""))
			pdao.updatePaperName(paperId, paperName);			
		System.out.println(paperName);
		
		String courseId = req.getParameter("courseId");
		if(null!=courseId && !courseId.equals(""))
			pdao.updateCourseId(paperId, courseId);
		System.out.println(courseId);
		
        String paperSubject = req.getParameter("paperSubject");
        if(null!=paperSubject && !paperSubject.equals(""))
        	pdao.updatePaperSubject(paperId, paperSubject);
        
        System.out.println(paperSubject);
        Timestamp paperStartTime = null;
        if(null != req.getParameter("paperStartTime") && !req.getParameter("paperStartTime").equals("")){
        	paperStartTime = Timestamp.valueOf(req.getParameter("paperStartTime"));
//        	System.out.println(paperStartTime);
        }
        Timestamp paperEndTime = null;
        if(null != req.getParameter("paperEndTime") && !req.getParameter("paperEndTime").equals("")){
        	paperEndTime = Timestamp.valueOf(req.getParameter("paperEndTime"));
        }
        
        /*如果开考时间需要更改（即从页面上获得的值不为空）
         * 并且考试结束时间也需要更改
         * 而且，开考时间早于考试结束时间，
         * 则修改数据库
         */
        if(null!=paperStartTime && !paperStartTime.equals("") &&
        		null!=paperEndTime && !paperEndTime.equals("") &&
        		paperStartTime.compareTo(paperEndTime)<0){
        	
        	pdao.updatePaperStartTime(paperId, paperStartTime);
        	pdao.updatePaperEndTime(paperId, paperEndTime);
        }
        
        /*
         * 如果开考时间需要更改，而考试结束时间不需要更改，
         * 则新输入的开考时间与原数据库中的考试时间进行比较
         * 如果新开考时间早于考试结束时间，则更新数据库
         */
        else if(null!=paperStartTime && !paperStartTime.equals("") &&
        		null==paperEndTime){
        	Paper p = new Paper();
        	p = pdao.getPaperByPaperId(paperId);
        	paperEndTime = p.getPaperEndTime();
        	if(paperStartTime.compareTo(paperEndTime) < 0)
        		pdao.updatePaperStartTime(paperId, paperStartTime);
        }
        /*
         * 如果需要更改考试结束时间，而同时去不需要更改开考时间
         * 则从新输入的考试结束时间与原数据库中的开考时间进行比较
         * 如果新输入的考试结束时间晚于开考时间
         * 则更新数据库
         */
        else if(null!=paperEndTime && paperEndTime.equals("") && 
        		null==paperStartTime){
        	
        	Paper p = new Paper();
        	p = pdao.getPaperByPaperId(paperId);
        	paperStartTime = p.getPaperStartTime();
        	if(paperStartTime.compareTo(paperEndTime) < 0)
        		pdao.updatePaperEndTime(paperId, paperEndTime);
        }
       
        System.out.println(paperEndTime);
        
        String paperDuration =  req.getParameter("paperDuration");
        if(null!=paperDuration && !paperDuration.equals(""))
        	pdao.updatePaperDuration(paperId, paperDuration);
        System.out.println(paperDuration);
        
        int mark = Integer.parseInt(req.getParameter("mark"));
        if( null != ((Integer)mark) && !((Integer)mark).equals(""))
        	pdao.updateMark(paperId, mark);
        System.out.println(mark);
        
        /**
         * 如果做了上述修改之后，则需要更改session中的考卷信息
         * */        
		/*获取考试中、尚未开始、考试结束等三种状态的考试信息*/
		List<Paper> currentExam = new ArrayList<Paper>();
		List<Paper> notStartExam = new ArrayList<Paper>();
		List<Paper> endExam = new ArrayList<Paper>();
		
		Teacher t = new Teacher();
		t = (Teacher) req.getSession().getAttribute("teacher");
		String teacherId = t.getTeacherId();
		//通过教师编号查询当前考试的考试列表，并将其保存到session中
		currentExam = pdao.getCurrenExamOfTeacher(teacherId);
		req.getSession().setAttribute("currentExam",currentExam);
		
		//通过教师编号查询尚未开始的考试的考试列表，并将其保存到session中
		notStartExam = pdao.getNotStartExamOfTeacher(teacherId);
		req.getSession().setAttribute("notStartExam",notStartExam);
		
		//通过教师编号查询已经结束的考试的考试列表，并将其保存到session中
		endExam = pdao.getEndExamOfTeacher(teacherId);
		req.getSession().setAttribute("endExam",endExam); 
        
        resp.sendRedirect("updatePaperInf.jsp");
        

        		
	}	
}
