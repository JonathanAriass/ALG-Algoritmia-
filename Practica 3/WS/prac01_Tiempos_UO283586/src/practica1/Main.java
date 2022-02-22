package practica1;
import java.io.IOException;

/**
 * Clase main que creara un objeto "MatrizOperaciones" y ejecutara alguno de sus
 * metodos para asi mostrar como funciona dicho objeto.
 * 
 * @author Jonathan Arias Busto (UO283586)
 * @version 1
 *
 */
public class Main {

	private static final String FILE_PATH1= "Files/matriz01.txt";
	private static final String FILE_PATH2= "Files/matrizPrueba.txt";
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		MatrizOperaciones mo1 = new MatrizOperaciones(4, 3, 8);
		
		System.out.println("Matriz aleatoria 4x4\n");
		mo1.escribir();
		
		System.out.println("\n(forma 1) La suma de la diagonal principal es : " + mo1.sumarDiagonal1());
		System.out.println("(forma 2) La suma de la diagonal principal es : " + mo1.sumarDiagonal2());
		
		System.out.println("\nSe carga la matriz del fichero \"matriz01.txt\".\n");
		MatrizOperaciones mo2 = new MatrizOperaciones(FILE_PATH1);
		mo2.escribir();
		
		System.out.println("\n(forma 1) La suma de la diagonal principal es : " + mo2.sumarDiagonal1());
		System.out.println("(forma 2) La suma de la diagonal principal es : " + mo2.sumarDiagonal2() + "\n\n");
		
		MatrizOperaciones mo3 = new MatrizOperaciones(FILE_PATH2);
		mo3.escribir();
		System.out.println("\nDespues de ejecutar metodo: recorrerCamino(2, 3)\n");
		mo3.recorrerCamino(2, 3);
		mo3.escribir();
		
		System.out.println("\n\n¡Nueva matriz!\n");
		
		MatrizOperaciones mo4 = new MatrizOperaciones(FILE_PATH2);
		mo4.escribir();
		System.out.println("\nDespues de ejecutar metodo: recorrerCamino(5, 6)\n");
		mo4.recorrerCamino(5, 6);
		mo4.escribir();
	}

}
