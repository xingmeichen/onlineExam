package onlineExam.persistent;

public class UserMapper {
	
	public String a="hello world";
	public String b="I am the earth.";
	
	public String getA() {
		return a;
	}
	
	public void setA(String a) {
		this.a = a;
	}
	
	public String getB() {
		return b;
	}
	
	public void setB(String b) {
		this.b = b;
	}
	
	public UserMapper setUser(String a,String b){
		UserMapper user = new UserMapper();
		user.setA(a);
		user.setB(b);
		return user;
	}
	
	public static void main(String[] args) {
		
		System.out.println("hello world");
		UserMapper user = new UserMapper();
		user = user.setUser("78378", "fejoefj");
		System.out.println(user.getA()+user.getB());
	}

}
