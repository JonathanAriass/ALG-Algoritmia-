package alg71780982Y_UO283586;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		long t1, t2;
		int res = 0;
		for (int n=100;n<1000000000;n*=2) {
			String cadena1 = stringGenerator(n);
			String cadena2 = stringGenerator(n);
			Algoritmo alg = new Algoritmo(cadena1, cadena2);
			t1 = System.currentTimeMillis ();
			
			for (int i=0;i<100;i++) {
				res = alg.distancia();				
			}
			t2 = System.currentTimeMillis ();
			
			System.out.println("n="+n+ "**TIEMPO="+(t2-t1) + "**SOLUCION="+res);
			
		}
//		String cadena1 = stringGenerator(7);
//		String cadena2 = stringGenerator(9);
//		System.out.println("Cadena1: " + cadena1);
//		System.out.println("Cadena2: " + cadena2);
//		Algoritmo alg = new Algoritmo(cadena1, cadena2);
//		System.out.println(alg.distancia());
//		System.out.println("Cadena 1: " + args[0]);
//		System.out.println("Cadena 2: " + args[1]);
//		
//		System.out.println();
//		
//		
//		Algoritmo alg = new Algoritmo(args[0], args[1]);
//		alg.distancia();
	}

	
	protected static String stringGenerator(int n) {
	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    int length = n;
	    for(int i = 0; i < length; i++) {
	      int index = random.nextInt(alphabet.length());
	      char randomChar = alphabet.charAt(index);
	      sb.append(randomChar);
	    }
	    
	    return sb.toString();
	}
	
}
