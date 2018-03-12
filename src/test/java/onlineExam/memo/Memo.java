package onlineExam.memo;

public class Memo {
	
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
	
	}
	
	public static void main(String[] args) {
		
		Memo memo = new Memo();
		memo.ifPhone();
		String m = "";
		m = "hello ";
		m = m+"\n";
		m = m+"world";
		System.out.println(m);
	}

}
