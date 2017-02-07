package es.uniovi.asw.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.Citizen;


/**
 * Clase para la carga de un fichero excel mediante la libreria APache Poi
 * @author david
 *
 */
public class RList implements ReadList{

	private final static String DEFAULT_PATH = "src/test/resources/test.xlsx";

	private XSSFWorkbook workbook;  //referencia al libro de excel
	
	/**
	 * Inicializamos la referencia al libro excel con la ruta recibida como parámetro
	 * @param path Dirección del fichero a cargar
	 */
	public RList(String path) {
		try {
			this.workbook = new XSSFWorkbook(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error E/S");
			e.printStackTrace();
		}
	}

	/**
	 * En el constructor por defecto cargamos la hoja de excel "test.xlsx" de ejemplo
	 */
	public RList(){
		try {
			this.workbook = new XSSFWorkbook(new FileInputStream(new File(DEFAULT_PATH)));
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error E/S");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Lee el fichero de excel y hasta la creación de la BD inserta los usuarios leídos en una 
	 * lista
	 */
	public List<Citizen> read(){
		List<Citizen> citizens = new ArrayList<Citizen>(); 
		// para cada una de las hojas presentes en el documento de excel
		for(int i=0;i < workbook.getNumberOfSheets();i++){
			XSSFSheet sheet = this.workbook.getSheetAt(i);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row;
			int counter = 0;
			//para cada fila de la hoja
			while(rowIterator.hasNext()){
				row = rowIterator.next();
				if (counter > 0) { //omitimos la cabecera (hay que mirar si hay un metodo de la API)
					Iterator<Cell> cellIterator = row.cellIterator();
					int j = 0;
					Citizen user = new Citizen();
					String value = "";
					while (cellIterator.hasNext()) {
						value = this.getCellValue(cellIterator.next());
						this.insertCitizenField(user, j++, value);
					}
					citizens.add(user);
				}
				counter++;
			}
		}
		return citizens;
	}
	
	/**
	 * Obtiene el tipo de una celda (celda numérica, texto o booleana)
	 * Si no es tipo cadena lo convierte a una cadena (tipo de datos con los que trabajaremos)
	 * @param cell
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private String getCellValue(Cell cell){
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			//Devolvemos la fecha en formato String para a la hora de la insercion
			//insertar todos los campos del mismo modo, no tenemos que distinguir
			//entre fecha, nombre, ...
			return new SimpleDateFormat("dd/MM/yyyy").format(cell.getDateCellValue());
		case Cell.CELL_TYPE_STRING:
			return  cell.getStringCellValue();
		default:
			return"";
		}
	}

	/**
	 * En función de la columna del excel leída, insertaremos un valor u otro en el ciente
	 * @param citizen
	 * @param arrow
	 * @param value
	 */
	private void insertCitizenField(Citizen citizen, int arrow,String value) {
		switch(arrow){
		case 0:
			citizen.setName(value);
			return;
		case 1:
			citizen.setSurname(value);
			return;
		case 2:
			citizen.setMail(value);
			return;
		case 3:
			try {
				citizen.setBirthday(new SimpleDateFormat("dd/MM/yyyy").parse(value));
			} catch (ParseException e) {
				System.out.println("Error al leer la fecha");
				e.printStackTrace();
			}
			return;
		case 4:
			citizen.setAddress(value);
			return;
		case 5:
			citizen.setNationality(value);
			return;
		case 6:
			citizen.setDNI(value);
			return;
		default:
			return;
		}
	}
}
