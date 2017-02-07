package es.uniovi.asw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.letters.WordLetter;
//import es.uniovi.asw.letters.PDFLetter;
import es.uniovi.asw.letters.WriteLetter;
import es.uniovi.asw.parser.RList;

/**
 * Clase para centralizar la funcionalidad de la aplicación
 * (Pendiente de agregar una instancia de la BD, de momento trabajaremos sobre una lista de usuarios)
 * En esta clase se instancian el cargador y el módulo para escribir las cartas
 * @author david
 */
public class Program {
	
	private List<Citizen> citizens = new ArrayList<Citizen>();
	private RList loader;
	
	
	public Program(String path) throws IOException{
		this.loader = new RList(path);
	}
	
	public void execute() throws IOException{
		this.citizens = this.loader.read();
		this.showCitizens();
		this.generateLetters();
	}
	
	public void generateLetters() throws IOException{
		for(Citizen user : citizens){
			WriteLetter writer = new WordLetter(user.getName(),user.getName()+"123"); // hasta que generemos la contraseña más elaboradamente será el nombre del usuario + 123
			writer.write("mediante el presente mail le adjuntamos su contraseña de inicio de sesión"
					+ " para nuestra aplicación");
		}
	}
	
	/**
	 * En el constructor por defecto, el loader se carga con el fichero de prueba test.xlsx
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Program() throws FileNotFoundException, IOException{
		this.loader = new RList();
	}
	
	public void showCitizens(){
		for(Citizen c : this.citizens)
			System.out.println(c.toString());
	}
}
