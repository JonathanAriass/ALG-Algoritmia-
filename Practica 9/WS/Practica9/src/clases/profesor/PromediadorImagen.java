package clases.profesor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PromediadorImagen {
	
	private Imagen real_img, bad_img; // para almacenar las imagenes con patron bueno y malo (negativo del malo)
	private Imagen avg_img, half1_img, half2_img; // para almacenar los promedios del subconjunto 1 y 2
	private Imagen[] dataset; // almacena el conjunto de de imagenes generadas (buenas y malas)
	public int[] sol; // array que determina donde poner las im�genes 0->no asignada, 1->primer subconjunto, 2->segundo subconjunto
	private int[] bestSol; // mejor soluci�n
	private int width, height; // ancho y alto de las im�genes
	//backtracking variables
	private int counter; // contador de nodos en el arbol impl�cito
	private double max_zncc; // donde almacenar el ZNCC final
	
	/** Constructor
	* @real_path  ruta del modelo de imagen "buena" (patr�n a encontrar) en disco
	* @bad_path  ruta del modelo de imagen "mala"
	* @n_real  numero de imagenes buenas (>= 1)
	* @n_bad  numero de imagenes "malas" (tiene que ser menor que las buenas) 
	* @s_noise  standard deviation for noise 
	*/
	public PromediadorImagen(String real_path, String bad_path, int n_real, int n_bad, double s_noise) {
		
		assert (n_real >= 1) && (n_bad < n_real); 
		
		// Cargando los patrones de referencia (buena y mala)
		this.real_img = new Imagen(real_path);
		this.bad_img = new Imagen(bad_path);
		this.width = this.real_img.getWidth();
		this.height = this.real_img.getHeight();
		
		// Se crean con conjunto de imagenes con un array ordenado aleatoriamente para posicionar 
		// las imagenes buenas y malas aleatoriamente
		int total_imgs = n_real + n_bad; // numero total de im�genes
		this.dataset = new Imagen[total_imgs]; 
		this.sol = new int[total_imgs]; // d�nde se almacena la soluci�n actual (combinaci�n de asignaciones): 0->no asignada, 1->primer subconjunto, 2->segundo subconjunto 
		this.bestSol = new int[total_imgs]; // d�nde se almacena la mejor soluci�n
		int[] rand_index = this.randomIndexes(total_imgs); // array con las posiciones aleatorias
		Imagen hold_img; // imagen temporal
		int region = 0; // 0-arriba, 1-bajo, 2-izquierda, 3-derecha
		for (int i=0; i<n_real; i++) { // im�genes buenas
			hold_img = new Imagen(this.width, this.height); // creaci�n de la imagen
			hold_img.addSignal(this.real_img); // a�adir los valores de los p�xeles
			hold_img.suppressRegion(region); // suprimir una region
			hold_img.addNoise(s_noise); // a�adir ruido
			this.dataset[rand_index[i]] = hold_img; // incluir la imagne en una posci�n aleatorio de dataset
			if (region == 3) region = 0;
			else region++;
		}
		region = 0;
		for (int i=n_real; i<n_real+n_bad; i++) { // bucle para las im�genes malas
			hold_img = new Imagen(this.width, this.height); 
			hold_img.addSignal(this.bad_img); 
			hold_img.invertSignal(); 
			hold_img.suppressRegion(region); 
			hold_img.addNoise(s_noise);   
			this.dataset[rand_index[i]] = hold_img; 
			if (region == 3) region = 0;
			else region++;
		}
	}
	
	/**
	 * Gener una array con valores de posiciones aleatorios
	 * @param n longitud del array
	 * @return 
	 */
	public int[] randomIndexes(int n) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(i);
		Collections.shuffle(list);
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = list.get(i);
		return array;
	}
	
	/**
	 * Almacena las im�genes generadas seg�n la mejor soluci�n encontrada
	 * @out_dir directorio donde se almacenan las im�genes 
	 */
	public void saveResults(String out_dir) {
		this.avg_img.save(out_dir + "/img_avg.png");
		this.half1_img.save(out_dir + "/img_half1_avg.png");
		this.half2_img.save(out_dir + "/img_half2_avg.png");
		for(int i=0; i<this.dataset.length; i++) {
			this.dataset[i].save(out_dir + "/img_" + i + "_klass_" + this.bestSol[i] + ".png");
		}
	}
	
	/**
	 * @return devuelve el n�mero de pasos requeridos por el algoritmo 
	 */
	public int getCounter() {
		return counter;
	}
	
	/** Calcula el ZNCC entre los promedios de los dos subconjuntos de im�genes
	 * @return el valor de ZNCC
	 */
	public double zncc() {
		return this.half1_img.zncc(this.half2_img);
	}
	
	/**
	 * Greedy algorithm: random instances for each half, the best one is the final solution    
	 * @n_tries numero de intentos aleatorios     
	 */
	public void splitSubsetsGreedy(int n_tries) {	
		Random r = new Random();
		int val; 
		int[] aux = new int[dataset.length];
		for (int j=0;j<n_tries;j++) {
			half1_img = new Imagen(width, height);
			half2_img = new Imagen(width, height);
			for (int i=0; i<this.dataset.length;i++) {
				val = r.nextInt((2-0)+1) + 0;
				switch(val) {
					case 0:
						aux[i] = 0;
						break;
					case 1:
						aux[i] = 1;
						half1_img.addSignal(this.dataset[i]);
						break;
					case 2:
						aux[i] = 2;
						half2_img.addSignal(this.dataset[i]);
						break;
				}		
			}
			if (this.zncc() > this.max_zncc) {
				this.bestSol = aux;
				this.max_zncc = this.zncc();
				half1_img.addSignal(half2_img);
				avg_img = half1_img;
			}
		}

		
	}
	
	/**
	 * Algoritmo backtracking con condici�n balanceo 
	 * @max_unbalancing: (condici�n de poda) determina la diferencia m�xima en el n�mero de im�genes
	 *                   entre los dos subconjuntos              
	 */
	public void splitSubsetsBacktracking(int max_unbalancing) {
		this.max_zncc = 0;
		auxBacktracking(0, 0, max_unbalancing);
		avg_img.addSignal(half1_img);
		avg_img.addSignal(half2_img);
	}
	
	private void auxBacktracking(int nivel, int valor, int max_unbalancing) {
		if (nivel == dataset.length && isValidSolution(max_unbalancing)) {
			avg_img = new Imagen(width, height);
			Imagen half_img1 = new Imagen(width, height);
			Imagen half_img2 = new Imagen(width, height);
			for (int i=0;i<sol.length;i++) {
				if (sol[i] == 1) {
					half_img1.addSignal(this.dataset[i]);
				} else if (sol[i] == 2) {
					half_img2.addSignal(this.dataset[i]);
				}
			}

			if (half_img1.zncc(half_img2) > this.max_zncc) {
				counter++;
				for (int j = 0;j<sol.length;j++) {
					bestSol[j] = sol[j];
				}
				this.max_zncc = half_img1.zncc(half_img2);
				this.half1_img = half_img1;
				this.half2_img = half_img2;

			}
			return;
		} else {
			sol[nivel] = 0;
			auxBacktracking(nivel+1, 0, max_unbalancing);
			sol[nivel] = 1;
			auxBacktracking(nivel+1, 1, max_unbalancing);
			sol[nivel] = 2;
			auxBacktracking(nivel+1, 2, max_unbalancing);
		}
	}

	private boolean isValidSolution(int balance) {
		int n_1 = 0;
		int n_2 = 0;
		for (int el : sol) {
			if (el == 1) n_1++;
			else if (el == 2) n_2++;
		}
		return n_1 - n_2 <= balance;
	}
	
	/**
	 * Algoritmo backtracking sin condici�n de balanceo.           
	 */
	public void splitSubsetsBacktracking() {
		this.max_zncc = 0;
		auxBacktracking(0, 0);
		avg_img.addSignal(half1_img);
		avg_img.addSignal(half2_img);
	}
	
	private void auxBacktracking(int nivel, int valor) {
		if (nivel == dataset.length) {
			avg_img = new Imagen(width, height);
			Imagen half_img1 = new Imagen(width, height);
			Imagen half_img2 = new Imagen(width, height);
			for (int i=0;i<sol.length;i++) {
				if (sol[i] == 1) {
					half_img1.addSignal(this.dataset[i]);
				} else if (sol[i] == 2) {
					half_img2.addSignal(this.dataset[i]);
				}
			}

			if (half_img1.zncc(half_img2) > this.max_zncc) {
				counter++;
				for (int j = 0;j<sol.length;j++) {
					bestSol[j] = sol[j];
				}
				this.max_zncc = half_img1.zncc(half_img2);
				this.half1_img = half_img1;
				this.half2_img = half_img2;

			}
			return;
		} else {
			sol[nivel] = 0;
			auxBacktracking(nivel+1, 0);
			sol[nivel] = 1;
			auxBacktracking(nivel+1, 1);
			sol[nivel] = 2;
			auxBacktracking(nivel+1, 2);
		}
	}
	
	private void printSol() {
		System.out.print("[");
		for (int i : sol) {
			System.out.print(i + ", ");
		}
		System.out.println("]");
	}

}
