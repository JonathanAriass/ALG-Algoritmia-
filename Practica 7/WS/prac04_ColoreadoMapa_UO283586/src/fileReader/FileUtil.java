package fileReader;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileUtil {
	
	public static String[] loadColors(String ficheroColores) throws IOException {
		Path filePath = new File("files/" + ficheroColores).toPath();
		Charset charset = Charset.defaultCharset();        
		List<String> stringList = Files.readAllLines(filePath, charset);
		String[] stringArray = stringList.toArray(new String[]{});
		
		return stringArray;
	} 
	
	
	public static HashMap<String, List<String>> loadPaises(String ficheroFronteras) {

		String linea;
		String[] paisesAux = null;
		String paisAux = "";
		String[] fronterasAux = null;

		HashMap<String, List<String>> fronteras = new HashMap<String, List<String>>();
		
		try {
			BufferedReader fichero = new BufferedReader(new FileReader("files/" + ficheroFronteras));
			while (fichero.ready()) {
				linea = fichero.readLine();
				paisesAux = linea.split(":");
				
				paisAux = paisesAux[0];
				fronterasAux = paisesAux[1].split(",");

				List<String> fronterasLista = new ArrayList<String>();
				for (String cadenas : fronterasAux) {
//					System.out.print(cadenas.substring(1) + ", "); // En este for se obtienen las fronteras de cada pais
					fronterasLista.add(cadenas.substring(1));
				}
				fronteras.put(paisAux, fronterasLista);


			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
		
		return fronteras;
		
	}
	
	
	public static final String SOL_DATA = "files/Solution.txt";
	
	public static void saveSolution(HashMap<String, String> sol) throws FileNotFoundException, IOException {
		
		File file = new File(SOL_DATA);
		
		BufferedWriter bf = null;
		
		try {
			  
            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file));
  
            // iterate map entries
            for (Map.Entry<String, String> entry :
                 sol.entrySet()) {
  
                // put key and value separated by a colon
                bf.write(entry.getKey() + "--> "
                         + entry.getValue());
  
                // new line
                bf.newLine();
            }
  
            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
  
            try {
  
                // always close the writer
                bf.close();
            }
            catch (Exception e) {
            }
        }
	}
	
	
}
