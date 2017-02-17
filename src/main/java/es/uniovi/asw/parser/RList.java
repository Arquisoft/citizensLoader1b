package es.uniovi.asw.parser;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import es.uniovi.asw.CitizenDB;


/**
 * Clase para la carga de un fichero excel mediante la libreria APache Poi
 * @author david
 *
 */
public abstract class RList implements ReadList{
	
	private static Insert ins = new InsertR();
	
	@Override
	public List<CitizenDB> read() {
		List<CitizenDB> citizens = readFile(); 
		
		List<CitizenDB> citizensDb = new ArrayList<CitizenDB>();
		try {
			citizensDb = ins.insert(citizens);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return citizensDb;
	}

	
	
	abstract List<CitizenDB> readFile();
	
}
