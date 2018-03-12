package onlineExam.memo;

public class SingleDao {

	public static void main(String[] args) {
		int count = 1;
		for(int i=0; i<5000; i++){
			
			if(i % 10 == 0)
				{
				System.out.println();
				count++;
				}
			System.out.print(i+1+",");
		}
		System.out.println(count);
	}
}
