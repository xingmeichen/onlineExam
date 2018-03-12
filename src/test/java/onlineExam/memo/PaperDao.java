package onlineExam.memo;

public class PaperDao {	
	
	

	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		int i=0;
		for(; i<81; i++){
			i += i;
		}
		System.out.println(i);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
