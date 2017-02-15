package es.uniovi.asw.dbupdate;

import java.sql.SQLException;
import java.util.List;

import es.uniovi.asw.Citizen;
import es.uniovi.asw.CitizenDB;

public interface Insert {

	/**
	 * Método que inserta el objeto creado previamente en la base de datos
	 * @return La lista de ciudadanos insertados
	 * @throws SQLException 
	 */
	List<CitizenDB> insert(List<Citizen> citizen) throws SQLException;
	
}
