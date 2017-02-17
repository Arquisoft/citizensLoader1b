package es.uniovi.asw.parser;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import es.uniovi.asw.CitizenDB;
import es.uniovi.asw.letters.PDFLetter;
import es.uniovi.asw.letters.WriteLetter;

/**
 * Clase para la carga de un fichero excel mediante la libreria APache Poi
 * @author david
 *
 */
public abstract class RList implements ReadList{
	
	@Override
	public List<CitizenDB> read() {
		try {
			List<CitizenDB> citizens = new InsertR().insert(this.readFile());
			this.generateLetters(citizens);
		} catch (SQLException e) {
			System.err.println("Error al cargar el fichero de ciudadanos");
			e.printStackTrace();
		}
		return null;
	}
	
	protected abstract List<CitizenDB> readFile();
	
	/**
	 * Método que se encarga de generar las cartas (con usuario y pass) para cada
	 * uno de los ciudadanos cargados del fichero de entrada
	 * @param citizens La lista de ciudadanos
	 */
	private void generateLetters(List<CitizenDB> citizens){
		for(CitizenDB user : citizens){
			WriteLetter writer = new PDFLetter(user.getName(),user.getPassword(),user.getMail()); //el mail será el login del usuario en el sistema
			try {
				writer.write("mediante el presente mail le adjuntamos su usuario y contraseña de inicio de sesión"
						+ " para nuestra aplicación");
			} catch (IOException e) {
				System.err.println("Error al crear la carta para el ciudadano con DNI: "+user.getDNI());
			}
		}
	}
	
}
