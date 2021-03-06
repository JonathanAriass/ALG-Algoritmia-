package alg71780972Y.p2;

import alg71780972Y.p2.Vector;

/** Este programa sirve para ordenar n elementos
	con un algoritmo de los "malos" (cuadrático)·
	es la SELECCION
 */
public class Seleccion extends Vector
{
	public Seleccion(int nElementos) {
		super(nElementos);
	}
	

	/**
	 * Ordenación por selección
	 */
	@Override
	public void ordenar() {
		int n = elements.length;
		int auxPos;
		for (int i=0; i<n-1;i++) {
			auxPos = i;
			for (int j=i+1;j<n;j++) {
				if (elements[j] < elements[i]) {
					auxPos = j;
				}
			}
			intercambiar(i, auxPos);
		}
	}  

	@Override
	public String getNombre() {		
		return "Selección";
	}  
} 
