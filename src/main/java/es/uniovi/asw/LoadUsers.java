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
	protected String run(String args[]){
		if(args == null){
			System.err.println("Error irrecuperable, reinicie la aplicacion");
			return "Error irrecuperable, reinicie la aplicacion";
		}
		if(args.length == 0){
			System.err.println("Error en la entrada, teclee -help para ver la ayuda");
			return "Error en la entrada, teclee -help para ver la ayuda";
		}
		else
			return this.runOptions(args);
	}
	
	private String runOptions(String args[]){
		if(args[0].compareToIgnoreCase("-help") == 0){
			this.printHelp();
			return "ayuda";
		}
		
		String ruta = args[0];
		if(args.length == 1){// nos llega sólo la ruta del fichero sin parametros
			
			rList = new RListExcel("-d",ruta);
			rList.read();
			return "por defecto";
		}
		if(args.length == 2){
			try{
				if(args[1].compareToIgnoreCase("-w") != 0 && args[1].compareToIgnoreCase("-p") != 0
						&& args[1].compareToIgnoreCase("-d") != 0){
					System.err.println("Opción no reconocida: "+args[1]+". Teclee -help para ayuda");
					return "error";
				}
				
				rList = new RListExcel(args[1],ruta);
				rList.read();
				return "parametros";
			}
			catch(IllegalArgumentException e){
				System.err.println(e.getMessage());
				return "error";
			}
		}
		return "error";
	}
	
	private void printHelp() {
		
	}
}
