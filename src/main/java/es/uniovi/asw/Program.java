package es.uniovi.asw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.loader.Loader;
import es.uniovi.asw.writer.DefaultLetter;
import es.uniovi.asw.writer.WriteLetter;

/**
 * Clase para centralizar la funcionalidad de la aplicación
 * (Pendiente de agregar una instancia de la BD, de momento trabajaremos sobre una lista de usuarios)
 * En esta clase se instancian el cargador y el módulo para escribir las cartas
 * @author david
 */
public class Program {
	
	private List<User> users = new ArrayList<User>();
	private Loader loader;
	
	
	public Program(String path) throws IOException{
		this.loader = new Loader(path);
	}
	
	public void execute() throws IOException{
		this.users = this.loader.loadUsers();
		this.generateLetters();
	}
	
	public void generateLetters() throws IOException{
		for(User user : users){
			WriteLetter writer = new DefaultLetter(user.getName(),user.getName()+"123"); // hasta que generemos la contraseña más elaboradamente será el nombre del usuario + 123
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
		this.loader = new Loader();
	}
}
