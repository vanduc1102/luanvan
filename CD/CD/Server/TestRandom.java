import java.util.Random;
public class TestRandom{
	public static void main(String []args){
		Random r = new Random();
		for( int i = 0; i < 10; i++){
			int ranInt = r.nextInt();
			if (ranInt < 0)
				ranInt *= -1;
			ranInt = 5  + ranInt % 25;
			System.out.println("Value = "+ranInt);
		}
	}
}