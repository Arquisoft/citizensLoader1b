package es.uniovi.asw.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import es.uniovi.asw.Program;



/**
 * Main application
 * 
 * @author Labra
 *
 */
public class LoadUsers {
	
	public static void main(String... args) {
		final LoadUsers runner = new LoadUsers();
		runner.run(args);
	}


	/** En esta version solo mostramos por pantalla los usuarios leidos
	 * @param args
	 */
	void run(String... args) {
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
