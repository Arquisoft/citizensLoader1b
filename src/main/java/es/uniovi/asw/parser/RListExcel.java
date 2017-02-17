package es.uniovi.asw.parser;


import java.util.List;
import es.uniovi.asw.CitizenDB;

public class RListExcel extends RList {
	
	private final static String DEFAULT_PATH = "src/test/resources/test.xlsx";
	private AdapterPoi adapter; //adaptador de la librería ApachePoi
	
	/**
	 * Inicializamos la referencia al libro excel con la ruta recibida como
	 * parámetro
	 * @param path Dirección del fichero a cargar
	 */
	public RListExcel(String path) {
		this.adapter = new AdapterPoi(path);
	}

	/**
	 * En el constructor por defecto cargamos la hoja de excel "test.xlsx"
	 * de ejemplo
	 */
	public RListExcel(){
		this.adapter = new AdapterPoi(DEFAULT_PATH);
	}
	
	public List<CitizenDB> readFile(){
		return this.adapter.readExcelFile();
	}
}
