package alg71780982Y.p4;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import fileReader.FileUtil;

public class Main {

	public static void main(String[] args) throws IOException {
		ColoracionMapa cm = new ColoracionMapa(args[0], args[1]);
		HashMap<String, String> res = cm.coloracionMapa();
		for(String pais : res.keySet()) {
			System.out.println(pais + "(" + res.get(pais) +")");
		}
		
		// Se guarda el contenido en un fichero llamado Solution.txt
		FileUtil.saveSolution(res);
	}


	
}
