package alg77777777.p31;

import java.util.Random;

public class TrominoTiempos {

	public static void main(String[] args) {
		Tromino tromino;
		long t1,t2;
		for (int i=16;i<1000000000;i*=2) {
			Random r = new Random();
			int x = r.nextInt(i+1);
			int y = r.nextInt(i+1);
			tromino = new Tromino(i, x, y);
			
			t1 = System.currentTimeMillis ();
			for (int j=0;j<10000;j++) {
				tromino.trominoCaller();				
			}
			t2 = System.currentTimeMillis ();
			
			System.out.println("n="+i+ "**TIEMPO="+(t2-t1));
		}
	}

}
