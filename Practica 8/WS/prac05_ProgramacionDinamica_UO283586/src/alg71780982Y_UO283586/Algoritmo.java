package alg71780982Y_UO283586;

public class Algoritmo {

	private String cadena1;
	private String convertir;
	
	public Algoritmo(String obj1, String obj2) {
		this.cadena1 = obj1;
		this.convertir = obj2;
	}
	
	public int distancia() {
		int[][] array = initialize();
		for (int i=1;i<convertir.length()+1;i++) {
			for (int j=1; j<cadena1.length()+1;j++) {
				if (convertir.charAt(i-1) == cadena1.charAt(j-1)) {
					array[i][j] = array[i-1][j-1];
				} else {
					array[i][j] = 1 + Math.min(Math.min(array[i-1][j-1], array[i-1][j]), array[i][j-1]);
				}
			}
		}
		//print(array);
		return array[convertir.length()][cadena1.length()];
	}
	
	private int[][] initialize() {
		int[][] array = new int[convertir.length()+1][cadena1.length()+1];
		for (int i=0;i<convertir.length()+1;i++) {
			for (int j=0;j<cadena1.length()+1;j++) {
				if (i==0) {
					array[i][j] = j;
				}
				if (j==0) {
					array[i][j] = i;
				}
			}
		}
		return array;
	}
	
	protected void print(int[][] array) {
		for (int i=0;i<convertir.length()+1;i++) {
			for (int j=0; j<cadena1.length()+1;j++) {
				System.out.print("|" + array[i][j] + "|");
			}
			System.out.println();
		}
	}
	
}
