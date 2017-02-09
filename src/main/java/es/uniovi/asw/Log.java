package es.uniovi.asw;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * 
 * @author Alejandro
 *
 */

	/**
	 * Metodo que escribe en un fichero log un error ocurrido
	 * 
	 * @param error
	 * @throws IOException
	 */
	public void recordError(String error) throws IOException {
		Date fecha = new Date();
		String mensaje = "(" + fecha + ")\t" + error;
		BufferedWriter file;
		try {
			file = new BufferedWriter(new FileWriter("\\citizensLoader1b"));
			file.write(mensaje);
			file.close();
		} catch (FileNotFoundException f) {
			throw new FileNotFoundException(f.getLocalizedMessage());
		} catch (IOException e) {
			throw new IOException(e.getLocalizedMessage());
		}
	}

	public void printError(Exception e) throws IOException {
		String error = e.getLocalizedMessage();
		recordError(error);
		
	}

}