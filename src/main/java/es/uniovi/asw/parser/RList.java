package es.uniovi.asw.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
	 * Inicializamos la referencia al libro excel con la ruta recibida como
	 * parámetro
	 * @param path Dirección del fichero a cargar
	 */
	public RList(String path) {
		try {
			FileInputStream file = new FileInputStream(new File(path));
			this.workbook = new XSSFWorkbook(file);
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error E/S");
			e.printStackTrace();
		}
	}

	/**
	 * En el constructor por defecto cargamos la hoja de excel "test.xlsx"
	 * de ejemplo
	 */
	public RList(){
		try {
			FileInputStream file = new FileInputStream(new File(DEFAULT_PATH));
			this.workbook = new XSSFWorkbook(file);
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error E/S");
			e.printStackTrace();
		}
	}
	
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
					while (cellIterator.hasNext()) 	
						this.insertCitizenField(user, j++, cellIterator.next());	
					user.setPassword(new GenerationPassword().passwordGenerator());
					citizens.add(user);
				}
				counter++;
			}
		}
		return citizens;
	}

	/**
	 * En función de la columna del excel leída, insertaremos un valor u otro
	 * en el cliente
	 * @param citizen
	 * @param col columna en la que se encuentra la celda a tratar
	 * @param cell La celda a tratar en cada momento
	 */
	private void insertCitizenField(Citizen citizen, int col,Cell cell) {
		switch(col){
		case 0:
			citizen.setName(cell.getStringCellValue());
			return;
		case 1:
			citizen.setSurname(cell.getStringCellValue());
			return;
		case 2:
			citizen.setMail(cell.getStringCellValue());
			return;
		case 3:
			citizen.setBirthday(cell.getDateCellValue());
			return;
		case 4:
			citizen.setAddress(cell.getStringCellValue());
			return;
		case 5:
			citizen.setNationality(cell.getStringCellValue());
			return;
		case 6:
			citizen.setDNI(cell.getStringCellValue());
			return;
		default:
			return;
		}
	}
	
}
