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

	private Loader loader;
	
	public static void main(String... args) {
		final LoadUsers runner = new LoadUsers();
		runner.run(args);
	}


	/** En esta version solo mostramos por pantalla los usuarios leidos
	 * @param args
	 */
	void run(String... args) {
		try {
			loader = new Loader();
			loader.readUsers();
			loader.showUsers();
		} catch (FileNotFoundException e) {
			System.out.println("No se he encontrado el fichero excel");
		} catch (IOException e) {
			System.out.println("Error I/O");
		}
	}
}
