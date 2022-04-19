package clases.profesor;

import java.nio.file.Paths;

public class PromediadorImagenBench {
	
	// Ajustes del banco de pruebas
	private static String REAL_IMG = "img/einstein_1_256.png";
	private static String BAD_IMG = "img/einstein_1_256.png";
	private static String OUT_DIR_G = "img/out_g/";
	private static String OUT_DIR_B = "img/out_bt/";
	private static int N_IMGS = 6; 
	private static double PORCENTAJE_BAD = 25; // %
	private static double S_NOISE = 5.0; // Nivel de ruido - desvici�n est�ndar de una distrubuci�n Gaussiana
		
	public static void main(String[] args) {
		
		int n_real, n_bad;
		PromediadorImagen img_avger;
		
		long t1, t2;
		
		for (int i = 2;i<14;i+=2) {
			n_bad = (int) ((PORCENTAJE_BAD/100.)*i);
			n_real = i - n_bad;
			img_avger = new PromediadorImagen(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);			

			// TESTING VORAZ
			System.out.print("TESTING VORAZ:\n");
			t1 = System.currentTimeMillis ();
			for (int j = 0; j<50; j++)
				img_avger.splitSubsetsGreedy(i);
			t2 = System.currentTimeMillis ();
			System.out.println("n="+i+ "**TIEMPO="+(t2-t1) + "**ZNCC="+img_avger.zncc());
			System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
			System.out.printf("  -Contador: %d\n",  img_avger.getCounter());
			img_avger.saveResults(OUT_DIR_G);
			
			
			
			// TESTING BACKTRACKING SIN PODA
//			System.out.print("TESTING BACKTRACKING SIN BALANCEO:\n");
//			// Toma tiempo inicial
//			t1 = System.currentTimeMillis ();
//			img_avger.splitSubsetsBacktracking();
//			t2 = System.currentTimeMillis ();
//			System.out.println("n="+i+ "**TIEMPO="+(t2-t1) + "**ZNCC="+img_avger.zncc());
//			// Toma tiempo final
//			System.out.println(img_avger.getCounter());
//			System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
//			System.out.printf("  -Contador: %d\n",  img_avger.getCounter());
//			img_avger.saveResults(OUT_DIR_B);
			
			
			
			// TESTING BACKTRACKING CON PODA
//			System.out.print("TESTING BACKTRACKING CON BALANCEO:\n");
//			img_avger.splitSubsetsBacktracking(1);
//			System.out.println(img_avger.getCounter());
//			System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
//			System.out.printf("  -Contador: %d\n",  img_avger.getCounter());
//			img_avger.saveResults(OUT_DIR_B);
		}

	}

}

