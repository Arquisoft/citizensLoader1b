package es.uniovi.asw.dbupdate;

import java.sql.SQLException;
import java.util.List;

import es.uniovi.asw.CitizenDB;

public interface Insert {

	/**
	 * Método que inserta el objeto creado previamente en la base de datos
	 * @param citizen El objeto que deseamos insertar en la base de datos
	 * @param path lugar donde insertamos el ciudadano
	 * @return La lista de ciudadanos insertados
	 * @throws SQLException 
	 */
	List<CitizenDB> insert(List<CitizenDB> citizen, String path) throws SQLException;
	
}
