package alg71780982Y.p32;

public class Tromino {

	int[][] tablero; // Tablero donde se desarrollara el puzzle
	int x, y; // Coordenadas X e Y de la pieza vacia del tablero

	/**
	 * Constructor del puzzle en el cual se inicializa el tablero y
	 * se colocan las piezas.
	 * 
	 * @param n Tamaño del tablero
	 * @param x Coordenada X de la casilla vacia
	 * @param y Coordenada Y de la casilla vacia
	 */
	public Tromino(int n, int x, int y) {
		if (n % 2 != 0) {
			System.out.println("No es un numero potencia de 2!!");
			return;
		}
		tablero = new int[n][n];
		for (int i =0;i<n;i++) {
			for (int j=0;j<n;j++) {
				tablero[i][j] = 0;				
			}
		}
		
		tablero[x][y] = -1;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Caller del metodo recursivo "trominoRec()" que se encarga de realizar
	 * la primera llamada con el tamaño del tablero, la primera casilla del tablero
	 * y la posicion (X,Y) de la casilla vacia.
	 */
	public void trominoCaller() {
		trominoRec(tablero.length,0,0,this.x,this.y);
	}
	

	int count = 0; // Contador del valor de la pieza que se esta colocando
	
	/**
	 * Metodo recursivo que se encarga de resolver el problema del Tromino
	 * de forma que se implemente el algoritmo Divide y Venceras
	 * 
	 * @param length Tamaño del tablero en el momento dado
	 * @param x Posicion X del tablero
	 * @param y Posicion Y del tablero
	 * @param vacioX Posicion X de la casilla vacia u ocupada
	 * @param vacioY Posicion Y de la casilla vacia u ocupada
	 */
	private void trominoRec(int length, int x, int y, int vacioX, int vacioY) {
		int vacioCuadradoX = vacioX, vaciopCuadradoY = vacioY;
		int n = length;
		
		if (length == 2) {
			// Primer cuadrante (arriba izquierda)
			if ((vacioX <= x+(n/2)-1) && (vacioY <= y+(n/2)-1)) {
				colocarTromino(x+n/2-1, y+n/2, x+n/2, y+n/2-1, x+n/2, y+n/2);
			}
			// Segundo cuadrante (arriba derecha)
			else if ((vacioX > x+(n/2)-1) && (vacioY <= y+(n/2)-1)) {
				colocarTromino(x+n/2-1, y+n/2-1, x+n/2-1, y+n/2, x+n/2, y+n/2);
			}
			// Tercer cuadrante (abajo izquierda)
			else if ((vacioX <= x+(n/2)-1) && (vacioY > y+(n/2)-1)) {
				colocarTromino(x+n/2, y+n/2, x+n/2-1, y+n/2-1, x+n/2, y+n/2-1);
			} 
			// Cuarto cuadrante (abajo derecha)
			else if ((vacioX > x+(n/2)-1) && (vacioY > y+(n/2)-1)) {
				colocarTromino(x+n/2-1, y+n/2-1, x+n/2-1, y+n/2, x+n/2, y+n/2-1);
			}
			return;
		}
		
		
		// Comprobamos si esta en el primer cuadrante de la division (arriba izquierda)
		if (vacioCuadradoX < (x + n/2) && vaciopCuadradoY < (y + n/2)) {
			colocarTromino(x+n/2, y+(n/2)-1, x+n/2, y+n/2, x+n/2-1, y+n/2);
			trominoRec(length/2, x, y, vacioCuadradoX, vaciopCuadradoY); // Primer cuadrante (arriba izquierda)
			trominoRec(length/2, x+length/2, y, x+(length/2-1), y+(length/2)); // Segundo cuadrante (arriba derecha)
			trominoRec(length/2, x, y+length/2, x+(length/2), y+(length/2-1)); // Tercer cuadrante (abajo izquierda)
			trominoRec(length/2, x+length/2, y+length/2, x+(length/2), y+(length/2)); // Cuarto cuadrante (abajo derecha)
		}
		
		// Comprobamos si esta en el segundo cuadrante de la division (arriba derecha)
		else if (vacioCuadradoX >= (x + n/2) && vaciopCuadradoY < (y + n/2)) {
			colocarTromino(x+(n/2) - 1, y+(n/2), x+n/2, y+n/2, x+n/2-1, y+n/2-1);
			trominoRec(length/2, x, y, x+(length/2-1), y+(length/2-1)); // Primer cuadrante (arriba izquierda)
			trominoRec(length/2, x+length/2, y, vacioCuadradoX, vaciopCuadradoY); // Segundo cuadrante (arriba derecha)
			trominoRec(length/2, x, y+length/2, x+(length/2), y+(length/2-1)); // Tercer cuadrante (abajo izquierda)
			trominoRec(length/2, x+length/2, y+length/2, x+(length/2), y+(length/2)); // Cuarto cuadrante (abajo derecha)
		}
		
		// Comprobamos si esta en el tercer cuadrante de la division (abajo izquierda)
		else if (vacioCuadradoX < (x + n/2) && vaciopCuadradoY >= (y + n/2)) {
			colocarTromino(x+n/2, y+(n/2) - 1, x+n/2, y+n/2, x+n/2-1, y+n/2-1);
			trominoRec(length/2, x, y, x+(length/2-1), y+(length/2-1)); // Primer cuadrante (arriba izquierda)
			trominoRec(length/2, x+length/2, y, x+(length/2-1), y+(length/2)); // Segundo cuadrante (arriba derecha)
			trominoRec(length/2, x, y+length/2, vacioCuadradoX, vaciopCuadradoY); // Tercer cuadrante (abajo izquierda)
			trominoRec(length/2, x+length/2, y+length/2, x+(length/2), y+(length/2)); // Cuarto cuadrante (abajo derecha)
		}
		
		// Comprobamos si esta en el cuarto cuadrante de la division (abajo derecha)
		else if (vacioCuadradoX >= (x + n/2) && vaciopCuadradoY >= (y + n/2)) {
			colocarTromino(x+n/2-1, y+(n/2), x+n/2, y+(n/2) - 1, x+(n/2) - 1, y+(n/2) - 1);
			trominoRec(length/2, x, y, x+(length/2-1), y+(length/2-1)); // Primer cuadrante (arriba izquierda)
			trominoRec(length/2, x+length/2, y, x+(length/2-1), y+(length/2)); // Segundo cuadrante (arriba derecha)
			trominoRec(length/2, x, y+length/2, x+(length/2), y+(length/2-1)); // Tercer cuadrante (abajo izquierda)
			trominoRec(length/2, x+length/2, y+length/2, vacioCuadradoX, vaciopCuadradoY); // Cuarto cuadrante (abajo derecha)
		}
		
		return;
	}
	
	/**
	 * Metodo que coloca una pieza dadas las coordenadas del tablero
	 * 
	 * @param x1 Coordenada X (numero 1)
	 * @param y1 Coordenada Y (numero 1)
	 * @param x2 Coordenada X (numero 2)
	 * @param y2 Coordenada Y (numero 2)
	 * @param x3 Coordenada X (numero 3)
	 * @param y3 Coordenada Y (numero 3)
	 */
	private void colocarTromino(int x1, int y1, int x2, int y2, int x3, int y3) {
		count++;
		tablero[x1][y1] = count;
		tablero[x2][y2] = count;
		tablero[x3][y3] = count;
	}
	
	/**
	 * Metodo que imprime por pantalla el tablero en un momento dado
	 */
	public void printBoard() {
		System.out.println();
		System.out.println();
		System.out.println();
		for (int i=0;i<tablero.length;i++) {
			for (int j=0;j<tablero.length;j++) {
				System.out.print(tablero[i][j] + "\t|\t");
			}
			System.out.println();
		}
		
	}
	
	/**
	 * Metodo que lanza la ejecucion del problema para comprobar que funciona para
	 * tamaños pequeños del problema.
	 * @param arg 
	 */
	public static void main(String arg[]) {
		Tromino tromino = new Tromino(8,4,3);
		tromino.trominoCaller();
		tromino.printBoard();
	}
	
}
