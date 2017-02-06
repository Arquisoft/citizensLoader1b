package es.uniovi.asw.writer;

import java.io.IOException;

/**
 * Interfaz que implementarán los distintos algoritmos para escribir una las diferentes cartas 
 * (patrón de diseño Strategy)
 * @author david
 *
 */
public interface WriteLetter {
	/**
	 * Consideraremos de manera provisional para desarrollar la implementación inicial
	 * que el texto de la carta se recibe como parámetro del método
	 * @param mensaje
	 * @throws IOException TODO
	 */
	void write(String mensaje) throws IOException;  
}
