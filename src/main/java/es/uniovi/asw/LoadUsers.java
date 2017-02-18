package es.uniovi.asw;

import java.sql.SQLException;
import es.uniovi.asw.parser.RList;
import es.uniovi.asw.parser.RListExcel;

/**
 * Main application
 * @author Labra
 *
 */
public class LoadUsers {
	
	private RList rList;
	
	public static void main(String args[]) {
		final LoadUsers runner = new LoadUsers();
		runner.run(args);
	}

	/** En esta version solo mostramos por pantalla los usuarios leidos
	 * @param args
	 * @throws SQLException 
	 */
	private void run(String args[]){
		if(args == null){
			System.err.println("Error irrecuperable, reinicie la aplicacion");
			return;
		}
		if(args.length == 0){
			System.err.println("Error en la entrada, teclee -help para ver la ayuda");
			return;
		}
		else{
			this.runOptions(args);
		}
	}
	
	private void runOptions(String args[]){
		if(args[0].compareToIgnoreCase("-help") == 0){
			this.printHelp();
			return;
		}
		
		String ruta = args[0];
		if(args.length == 1) // nos llega sólo la ruta del fichero sin parametros
			rList = new RListExcel("-d",ruta);
		if(args.length == 2){
			try{
				rList = new RListExcel(args[1],ruta);
			}
			catch(IllegalArgumentException e){
				System.err.println(e.getMessage());
				return;
			}
		}
		rList.read();
		return;
	}
	
	private void printHelp() {
		
	}
}
