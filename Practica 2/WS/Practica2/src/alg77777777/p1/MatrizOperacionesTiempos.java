package alg77777777.p1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Clase que representa una matriz cuadrada que puede realizar varias acciones, como
 * calcular la suma de la diagonal principal de diversas formas, asi como calcular un camino dado
 * una matriz especifica.
 * 
 * @author Jonathan Arias Busto (UO283586)
 * @version 1
 *
 */
public class MatrizOperacionesTiempos {

	
	public static void main(String arg[]) {
		long t1, t2;
		MatrizOperacionesTiempos m1;
		for (int i=Integer.parseInt(arg[0]);i<=Integer.parseInt(arg[1]);i*=Integer.parseInt(arg[2])) {
			m1 = new MatrizOperacionesTiempos(i, 4, 100);
			t1 = System.currentTimeMillis ();
			for (int j=0;j<1000000;j++) {
				m1.sumarDiagonal2();				
			}
			t2 = System.currentTimeMillis ();
			System.out.printf("Tiempo para carga: %d | %d\n", i, (t2-t1));
		}
		
	}
	
	private Integer[][] array; // Matriz principal de la clase
	
	/**
	 * Constructor de la matriz que se le pasa como parametro el tamaño de esta (nxn),
	 * el minimo y el maximo de los valores aleatorios que se introduciran en la matriz
	 * al ser creada.
	 * 
	 * @param n Tamaño de la matriz nxn
	 * @param min Minimo para numero aleatorio
	 * @param max Maximo para numero aleatorio
	 */
	public MatrizOperacionesTiempos(int n, int min, int max) {
		array = new Integer[n][n];
		Random rm = new Random();
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				array[i][j] = rm.nextInt(max + 1 - min) + min;
			}
		}
	}
	
	/**
	 * Constructor de la matriz que se le pasa como parametro en nombre de un fichero que
	 * contiene texto con los valores de una matriz.
	 * 
	 * Este constructor carga en la matriz los valores de este fichero.
	 * 
	 * @param nomFich Nombre del fichero del que cargar los datos.
	 * @throws NumberFormatException Excepcion en caso de que se lea una linea donde no se pueda
	 * 								 hacer el parseo a Integer
	 * @throws IOException Excepcion de entrada/salida al abrir el archivo .txt
	 */
	public MatrizOperacionesTiempos(String nomFich) throws NumberFormatException, IOException {
		// Creacion del tunel de lectura de bytes del fichero .txt
		File file = new File(nomFich);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		// Lectura de la primera linea donde se encuentra el tamaño de la matriz a cargar
		String linea;
		linea = br.readLine();
		int n = Integer.parseInt(linea);
		array = new Integer[n][n];
		
		// Comprobacion en caso de que el array no se haya inicializado
		if (array == null) {
			array = new Integer[n][n];
		}
		
		// Variable que se usara para almacenar los datos de la matriz
		String datos[] = null;
		
		// Variable que representa la fila en la que se esta leyendo del fichero .txt
		int row = 0;
		
		// Bucle principal donde se añade los diferentes valores a la matriz principal
		while ((linea = br.readLine()) != null) {
			datos = linea.split("\t");
			
			for (int col = 0; col < n; col++) {
                array[row][col] = Integer.parseInt(datos[col]);
            }
			
			row++;
		}
		
		br.close();
	}
	
	/**
	 * Metodo que devuelve el tamaño del array
	 * 
	 * @return Tamaño del array
	 */
	public int getTam() {
		return array.length;
	}
	
	public void escribir() {
		String str = "";
		for (int i=0;i<getTam();i++) {
			for (int j=0;j<getTam();j++) {
				str += array[i][j] + "\t";
			}
			str += "\n";
		}
		System.out.println(str);
	}
	
	/**
	 * Metodo que calcula la suma de la diagonal del array de forma que recorre toda
	 * la matriz pero solo se queda con los valores de la diagonal principal y los va
	 * sumando.
	 * 
	 * @return Suma de la diagonal principal
	 */
	public int sumarDiagonal1() {
		int res = 0;
		for (int i=0;i<getTam();i++) {
			for (int j=0;j<getTam();j++) {
				if (i == j) {
					res += array[i][j];
				}
			}
		}
		return res;
	}
	
	/**
	 * Metodo que tambien calcula la suma de la diagonal principal, pero en este
	 * caso solo recorre los valores de la diagonal principal ahorrandose asi un
	 * bucle for.
	 * 
	 * @return Suma de la diagonal principal
	 */
	public int sumarDiagonal2() {
		int res = 0;
		for (int i=0;i<getTam();i++) {
			res += array[i][i];
		}
		return res;
	}
	
	/**
	 * Metodo que recorre un camino desde un nodo pasada su posicion en la matriz.
	 * 
	 * La forma de hacer el recorrido es el siguiente:
	 * 		Cuando se encuentra con un numero se movera hacia una nueva posicion dependiendo
	 * 		del valor de dicho numero. Estos valores son:
	 * 			1 --> Arriba
	 * 			2 --> Derecha
	 * 			3 --> Abajo
	 * 			4 --> Izquierda
	 * 
	 * Siendo el esquema de movimiento:
	 * 			  1
	 * 			  ^
	 * 			  |
	 * 		4 <--  --> 2
	 * 			  |
	 * 			  v
	 * 			  3
	 * 
	 * @param i Componente i de la matriz
	 * @param j Componente j de la matriz
	 */
	public void recorrerCamino(int i, int j) {
		boolean isChecked = false;
		int aux = array[i][j];
		array[i][j] = -1;
		
		int iAux = i;
		int jAux = j;
		
		while ((isChecked == false) && aux != -1) {
			switch(aux) {
				// Arriba
				case 1:
					if (iAux-1 < 0) {
						return;
					}
					aux = array[iAux-1][jAux];
					array[iAux][jAux] = -1;
					iAux--;
					break;
				// Derecha
				case 2:
					if (jAux+1 > getTam()-1) {
						return;
					} 
					aux = array[iAux][jAux+1];
					array[iAux][jAux] = -1;
					jAux++;
					break;
				// Abajo
				case 3:
					if (iAux+1 > getTam()-1) {
						return;
					} 
					aux = array[iAux+1][jAux];
					array[iAux][jAux] = -1;
					iAux++;
					break;
				// Izquierda
				case 4:
					if (jAux-1 < 0) {
						return;
					} 
					aux = array[iAux][jAux-1];
					array[iAux][jAux] = -1;
					jAux--;
					break;
			}
				
		}
	}
	
}