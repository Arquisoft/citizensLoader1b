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
			String parameter = this.checkParameter(args[1]);
			if(parameter != null)
				rList = new RListExcel(args[1],ruta);
			else{
				System.err.println(args[1]+" no es una opción válida. Teclee -help para más información");
				return;
			}
		}
		rList.read();
		return;
	}
	
	private String checkParameter(String parameter){
		if(parameter.compareToIgnoreCase("-w") == 0)
			return "word";
		else
			if(parameter.compareToIgnoreCase("-p") == 0)
				return "pdf";
			else
				if(parameter.compareToIgnoreCase("-d") == 0)
					return "default";
				else
					return null;
	}
	
	private void printHelp() {
		
	}
}
