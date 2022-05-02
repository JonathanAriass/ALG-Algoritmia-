
public class MainBench {

	private static String REAL_IMG = "img/einstein_1_256.png";
	private static String BAD_IMG = "img/einstein_1_256.png";
	private static String OUT_DIR_BNB = "img/out_bnb/";
	private static String OUT_DIR_B = "img/out_bt/";
	private static int N_IMGS = 3; 
	private static double PORCENTAJE_BAD = 25; // %
	private static double S_NOISE = 5.0; // Nivel de ruido - desvici�n est�ndar de una distrubuci�n Gaussiana
	
	public static void main(String[] args) {
		
		int n_real, n_bad;
		PromediadorImagen img_avger;
		long t1, t2;
		for (int i=2;i<8;i++) {
			n_bad = (int) ((PORCENTAJE_BAD/100.)*i);
			n_real = i - n_bad;
			img_avger = new PromediadorImagen(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
			t1 = System.currentTimeMillis ();
			img_avger.main();
			t2 = System.currentTimeMillis ();
			System.out.println("n="+i+ "**TIEMPO="+(t2-t1) + "**ZNCC="+img_avger.zncc());
			img_avger.saveResults(OUT_DIR_BNB);
			
		}
	}

}
