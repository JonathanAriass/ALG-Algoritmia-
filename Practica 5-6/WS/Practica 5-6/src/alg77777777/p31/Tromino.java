package alg77777777.p31;

public class Tromino {

	int[][] tablero;
	int x, y;
	
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
	
	public void trominoCaller() {
		trominoRec(tablero.length,0,0,this.x,this.y);
//		tromino(0,0,matriz.length,x,y);
	}
	

	int count = 0;
	
	private void trominoRec(int length, int x, int y, int vacioX, int vacioY) {
		int vacioCuadradoX = vacioX, vaciopCuadradoY = vacioY;
		// Caso base, si el tamaño es 2 se coloca tromino sin que choque con ninguna otra pieza
//		if (length == 2) {
//			count++;
//			for (i=0;i<length;i++) {
//				for (j=0;j<length;j++) {
//					if (tablero[x+i][y+j]==0) {
//						tablero[x+i][y+j] = count;
//					}
//				}
//			}
//			return;
//		}
	
		int n = length;
		
		if (length == 2) {
			if ((vacioX > x+(n/2)-1) && (vacioY > y+(n/2)-1)) {
				colocarTromino(x+n/2-1, y+n/2-1, x+n/2-1, y+n/2, x+n/2, y+n/2-1);
			} else if ((vacioX > x+(n/2)-1) && (vacioY <= y+(n/2)-1)) {
				colocarTromino(x+n/2-1, y+n/2-1, x+n/2-1, y+n/2, x+n/2, y+n/2);
			} else if ((vacioX <= x+(n/2)-1) && (vacioY > y+(n/2)-1)) {
				colocarTromino(x+n/2, y+n/2, x+n/2-1, y+n/2-1, x+n/2, y+n/2-1);
			} else if ((vacioX <= x+(n/2)-1) && (vacioY <= y+(n/2)-1)) {
				colocarTromino(x+n/2-1, y+n/2, x+n/2, y+n/2-1, x+n/2, y+n/2);
			}
			return;
		}
		
		
		// Buscamos el cuadrado vacio en el tablero (posicion en el tablero)
//		for (i=x;i<length+x;i++) {
//			for (j=y;j<length+y;j++) {
//				if (tablero[i][j] != 0) {
//					vacioCuadradoX = i;
//					vaciopCuadradoY = j;
//				}
//			}
//		}
		
		
		
		// Comprobamos si esta en el primer cuadrante de la division (arriba izquierda)
		if (vacioCuadradoX < (x + n/2) && vaciopCuadradoY < (y + n/2)) {
			colocarTromino(x+n/2, y+(n/2)-1, x+n/2, y+n/2, x+n/2-1, y+n/2);
			trominoRec(length/2, x, y+length/2, x+(length/2), y+(length/2-1)); // Tercer cuadrante (abajo izquierda)
			trominoRec(length/2, x, y, vacioCuadradoX, vaciopCuadradoY); // Primer cuadrante (arriba izquierda)
			trominoRec(length/2, x+length/2, y, x+(length/2-1), y+(length/2)); // Segundo cuadrante (arriba derecha)
			trominoRec(length/2, x+length/2, y+length/2, x+(length/2), y+(length/2)); // Cuarto cuadrante (abajo derecha)
		}
		
		// Comprobamos si esta en el segundo cuadrante de la division (arriba derecha)
		else if (vacioCuadradoX >= (x + n/2) && vaciopCuadradoY < (y + n/2)) {
			colocarTromino(x+(n/2) - 1, y+(n/2), x+n/2, y+n/2, x+n/2-1, y+n/2-1);
			trominoRec(length/2, x, y+length/2, x+(length/2), y+(length/2-1)); // Tercer cuadrante (abajo izquierda)
			trominoRec(length/2, x, y, x+(length/2-1), y+(length/2-1)); // Primer cuadrante (arriba izquierda)
			trominoRec(length/2, x+length/2, y, vacioCuadradoX, vaciopCuadradoY); // Segundo cuadrante (arriba derecha)
			trominoRec(length/2, x+length/2, y+length/2, x+(length/2), y+(length/2)); // Cuarto cuadrante (abajo derecha)
		}
		
		// Comprobamos si esta en el tercer cuadrante de la division (abajo izquierda)
		else if (vacioCuadradoX < (x + n/2) && vaciopCuadradoY >= (y + n/2)) {
			colocarTromino(x+n/2, y+(n/2) - 1, x+n/2, y+n/2, x+n/2-1, y+n/2-1);
			trominoRec(length/2, x, y+length/2, vacioCuadradoX, vaciopCuadradoY); // Tercer cuadrante (abajo izquierda)
			trominoRec(length/2, x, y, x+(length/2-1), y+(length/2-1)); // Primer cuadrante (arriba izquierda)
			trominoRec(length/2, x+length/2, y, x+(length/2-1), y+(length/2)); // Segundo cuadrante (arriba derecha)
			trominoRec(length/2, x+length/2, y+length/2, x+(length/2), y+(length/2)); // Cuarto cuadrante (abajo derecha)
		}
		
		// Comprobamos si esta en el cuarto cuadrante de la division (abajo derecha)
		else if (vacioCuadradoX >= (x + n/2) && vaciopCuadradoY >= (y + n/2)) {
			colocarTromino(x+n/2-1, y+(n/2), x+n/2, y+(n/2) - 1, x+(n/2) - 1, y+(n/2) - 1);
			trominoRec(length/2, x, y+length/2, x+(length/2), y+(length/2-1)); // Tercer cuadrante (abajo izquierda)
			trominoRec(length/2, x, y, x+(length/2-1), y+(length/2-1)); // Primer cuadrante (arriba izquierda)
			trominoRec(length/2, x+length/2, y, x+(length/2-1), y+(length/2)); // Segundo cuadrante (arriba derecha)
			trominoRec(length/2, x+length/2, y+length/2, vacioCuadradoX, vaciopCuadradoY); // Cuarto cuadrante (abajo derecha)
		}
		
//		trominoRec(length/2, x, y+length/2, x+(length/2), y+(length/2-1)); // Tercer cuadrante (abajo izquierda)
//		trominoRec(length/2, x, y, x+(length/2-1), y+(length/2-1)); // Primer cuadrante (arriba izquierda)
//		trominoRec(length/2, x+length/2, y, x+(length/2-1), y+(length/2)); // Segundo cuadrante (arriba derecha)
//		trominoRec(length/2, x+length/2, y+length/2, x+(length/2), y+(length/2)); // Cuarto cuadrante (abajo derecha)
		
		return;
	}
	
	private void colocarTromino(int x1, int y1, int x2, int y2, int x3, int y3) {
		count++;
		tablero[x1][y1] = count;
		tablero[x2][y2] = count;
		tablero[x3][y3] = count;
	}
	
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
	
	public static void main(String arg[]) {
		Tromino tromino = new Tromino(4,0,0);
//		tromino.trominoCaller(6,7);
		tromino.trominoCaller();
		tromino.printBoard();
	}
	
}
