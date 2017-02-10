package es.uniovi.asw.parser;

import java.util.List;

import es.uniovi.asw.Citizen;

public class InsertR implements Insert{
	
	@Override
	public List<Citizen> insert(List<Citizen> citizen, String path) {
		//Obtenemos el ciudadano que vamos a insertar y lo enviamos al método
		//insertar de la clase InsertarP perteneciente al elemento DBUpdater
		//que se encargará de insertar el ciudadano en la base de datos.
		// (falta por hacer dicho elemento/paquete asi como sus clases)
		return new InsertP().insert(citizen, path);
	}

}
