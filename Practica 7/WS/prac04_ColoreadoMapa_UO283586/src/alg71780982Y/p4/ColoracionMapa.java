package alg71780982Y.p4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import fileReader.FileUtil;

public class ColoracionMapa {

	HashMap<String,List<String>> fronteras = null;
	String[] colores = null;
	
	public ColoracionMapa(String ficheroColores, String ficheroFronteras) throws IOException {
		fronteras = FileUtil.loadPaises(ficheroFronteras);
		colores = FileUtil.loadColors(ficheroColores);
	}
	
	public HashMap<String, String> coloracionMapa() {
		HashMap<String, String> res = inicializarRes(); // Pais con color respectivo
		for (String pais : fronteras.keySet()) {
			if (res.get(pais) == "empty") {
				String colorElegido = elegirColor(fronteras.get(pais), res);
				res.put(pais, colorElegido);
			}
		}	
		
		return res;
	}
	
	
	/**
	 * Metodo que inicializa un HashMap para cada pais con el color vacio.
	 * @return
	 */
	private HashMap<String, String> inicializarRes() {
		HashMap<String, String> aux = new HashMap<String,String>();
		for (String pais : fronteras.keySet()) {
			aux.put(pais, "empty");
		}
		return aux;
	}
	
	 private int getColorIndex(String color) {
		int count = 0;
		for (String c : colores) {
			if (c == color) return count;
			else count++;
		}
		return -1;
	}
	
	private String elegirColor(List<String> frontera, HashMap<String, String> paises) {
		String aux = colores[0];
		if (frontera.size() == 1 && frontera.get(0).trim().equals("NO")) {
			return colores[0];
		}
		ArrayList<String> colAux = new ArrayList<String>();
		for (String f : frontera) {
			String color = paises.get(f.trim());
			if (color != "empty") {
				colAux.add(color);
			}
		}
		if (colAux.size() == 0) {
			aux = colores[0];
		} else {
			aux = searchColor(colAux);			
		}
		return aux;
	}

	private String searchColor(ArrayList<String> colAux) {
		int[] aux = new int[colAux.size()];
		for (int i=0;i<colAux.size();i++) {
			aux[i] = getColorIndex(colAux.get(i));

		}
		Arrays.sort(aux);
		int cont = 0;
		for (int i=0;i<aux.length;i++) {
			if (cont == aux[i]) cont++;
		}

		return colores[cont];
	}
	
}
