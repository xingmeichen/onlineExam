package onlineExam.memo;

import javax.annotation.Resource;

import onlineExam.service.ITeacherService;

public class Memo {
	
	@Resource
	public ITeacherService teacherServiceImpl;
	
	/**
	 * 正确的电话号码
	 * */
	public void ifPhone(){
		String phone = "1989889";
		boolean result = phone.matches("[0-9]{11}");
		if(result)
			System.out.println("正确的电话号码");
		else
			System.out.println("错误的电话号码");
		System.out.println("----------------");
		System.out.println(teacherServiceImpl == null);
	
	}
	
	public static void main(String[] args) {
		
		Memo memo = new Memo();
		memo.ifPhone();
		System.out.println("*************");
	}
}
