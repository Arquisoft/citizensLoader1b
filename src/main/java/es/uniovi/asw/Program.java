package es.uniovi.asw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



//import es.uniovi.asw.letters.WordLetter;
import es.uniovi.asw.letters.PDFLetter;
import es.uniovi.asw.letters.WriteLetter;
import es.uniovi.asw.parser.RList;
import es.uniovi.asw.parser.RListExcel;

/**
 * Clase para centralizar la funcionalidad de la aplicación
 * (Pendiente de agregar una instancia de la BD, de momento trabajaremos sobre 
 * una lista de usuarios)
 * En esta clase se instancian el cargador y el módulo para escribir las cartas
 * @author david
 */
public class Program {
	
	private List<CitizenDB> citizens = new ArrayList<CitizenDB>();
	private RList loader;
	
	
	public Program(String path) throws IOException{
		this.loader = new RListExcel();
	}
	
	public void execute() throws IOException{
		this.citizens = this.loader.read();
		this.showCitizens();
		this.generateLetters();
	}
	
	public void generateLetters() throws IOException{
		for(CitizenDB user : citizens){
			WriteLetter writer = new PDFLetter(user.getName(),user.getPassword(),user.getMail()); //el mail será el login del usuario en el sistema
			writer.write("mediante el presente mail le adjuntamos su usuario y contraseña de inicio de sesión"
					+ " para nuestra aplicación");
		}
	}
	
	/**
	 * En el constructor por defecto, el loader se carga con el fichero de
	 * prueba test.xlsx
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Program() throws FileNotFoundException, IOException{
		this.loader = new RListExcel();
	}
	
	public void showCitizens(){
		for(CitizenDB c : this.citizens)
			System.out.println(c.toString());
	}
}
