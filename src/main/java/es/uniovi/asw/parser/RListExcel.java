package es.uniovi.asw.parser;

import java.io.FileNotFoundException;
import java.util.List;

import es.uniovi.asw.CitizenDB;

public class RListExcel extends RList {
	
	private final static String DEFAULT_PATH = "src/test/resources/test.xlsx";
	private AdapterPoi adapter; //adaptador de la librería ApachePoi
	
	/**
	 * Inicializamos la referencia al libro excel con la ruta recibida como
	 * parámetro
	 * @param path Dirección del fichero a cargar
	 * @throws FileNotFoundException 
	 */
	public RListExcel(String letterType,String path){
		super(letterType);
		try {
			this.adapter = new AdapterPoi(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * En el constructor por defecto cargamos la hoja de excel "test.xlsx"
	 * de ejemplo
	 * @throws FileNotFoundException 
	 */
	public RListExcel(String letterType){
		super(letterType);
		try {
			this.adapter = new AdapterPoi(DEFAULT_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<CitizenDB> readFile(){
		return this.adapter.readExcelFile();
	}
}
