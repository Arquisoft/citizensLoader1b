package es.uniovi.asw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;



/**
 * Main application
 * 
 * @author Labra
 *
 */
public class LoadUsers {
	
	public static void main() {
		final LoadUsers runner = new LoadUsers();
		runner.run();
	}


	/** En esta version solo mostramos por pantalla los usuarios leidos
	 * @param args
	 */
	void run() {
		try {
			Program p = new Program();
			p.execute();
		} catch (FileNotFoundException e) {
			System.out.println("No se he encontrado el fichero excel");
		} catch (IOException e) {
			System.out.println("Error I/O");
		}
	}
}
