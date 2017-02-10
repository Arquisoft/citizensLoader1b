package es.uniovi.asw.parser;

import java.util.List;

import es.uniovi.asw.Citizen;;

public interface ReadList {
	
	/**
	 * Lee el fichero de excel y hasta la creación de la BD inserta los usuarios
	 * leídos en una lista
	 * @return Lista de los ciudadanos obtenidos en la lectura
	 */
	List<Citizen> read();
}
