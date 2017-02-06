package es.uniovi.asw;

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


/**
 * Clase para la carga de un fichero excel mediante la libreria APache Poi
 * @author david
 *
 */
public class Loader {

	private final static String DEFAULT_PATH = "src/test/resources/test.xlsx";
	
	private List<User> users = new ArrayList<User>(); //contenedor de usuarios
	private XSSFWorkbook workbook;  //referencia al libro de excel
	
	/**
	 * Inicializamos la referencia al libro excel con la ruta recibida como parámetro
	 * @param path Dirección del fichero a cargar
	 * @throws IOException
	 */
	public Loader(String path) throws IOException{
		this.workbook = new XSSFWorkbook(new FileInputStream(new File(path)));
	}

	/**
	 * En el constructor por defecto cargamos la hoja de excel "test.xlsx" de ejemplo
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public Loader() throws FileNotFoundException, IOException{
		this.workbook = new XSSFWorkbook(new FileInputStream(new File(DEFAULT_PATH)));
	}
	
	
	/**
	 * Lee el fichero de excel y hasta la creación de la BD inserta los usuarios leídos en una 
	 * lista
	 */
	public void readUsers(){
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
					User user = new User();
					String value = "";
					while (cellIterator.hasNext()) {
						value = this.getCellValue(cellIterator.next());
						this.insertUserField(user, j++, value);
					}
					this.users.add(user);
				}
				counter++;
			}
		}
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
			return String.valueOf(cell.getNumericCellValue());
		case Cell.CELL_TYPE_BOOLEAN:
			return  String.valueOf(cell.getBooleanCellValue());
		case Cell.CELL_TYPE_STRING:
			return  cell.getStringCellValue();
		}
		return "";
	}

	/**
	 * En función de la columna del excel leída, insertaremos un valor u otro en el ciente
	 * @param user
	 * @param arrow
	 * @param value
	 */
	private void insertUserField(User user, int arrow,String value) {
		switch(arrow){
		case 0:
			user.setName(value);
			return;
		case 1:
			user.setSurname(value);
			return;
		case 2:
			user.setMail(value);
			return;
		case 3:
			user.setBirthday(value);
			return;
		case 4:
			user.setAddress(value);
			return;
		case 5:
			user.setNacionality(value);
			return;
		case 6:
			user.setDNI(value);
			return;
		}
	}
	
	/**
	 * Metodo para probar que los usuarios se cargan correctamente
	 * en el futuro se iran insertando en la BD
	 */
	public void showUsers(){
		for(User user : this.users)
			System.out.println(user.toString());
	}
}
