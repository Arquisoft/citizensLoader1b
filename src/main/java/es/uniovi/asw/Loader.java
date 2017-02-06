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

public class Loader {

	private final static String RUTA_DEFECTO = "test.xlsx";
	
	private List<Usuario> usuarios = new ArrayList<Usuario>(); //contenedor de usuarios
	private XSSFWorkbook workbook;  //referencia al libro de excel
	
	/**
	 * Inicializamos la referencia al libro excel con la ruta recibida como parámetro
	 * @param ruta Dirección del fichero a cargar
	 * @throws IOException
	 */
	public Loader(String ruta) throws IOException{
		this.workbook = new XSSFWorkbook(new FileInputStream(new File(ruta)));
	}

	/**
	 * En el constructor por defecto cargamos la hoja de excel "test.xlsx" de ejemplo
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public Loader() throws FileNotFoundException, IOException{
		this.workbook = new XSSFWorkbook(new FileInputStream(new File(RUTA_DEFECTO)));
	}
	
	@SuppressWarnings("deprecation")
	public void readUsers(){
		// para cada una de las hojas presentes en el documento de excel
		for(int i=0;i < workbook.getNumberOfSheets();i++){
			XSSFSheet sheet = this.workbook.getSheetAt(i);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row;
			int conta = 0;
			while(rowIterator.hasNext()){
				row = rowIterator.next();
				if (conta > 0) {
					Iterator<Cell> cellIterator = row.cellIterator();
					Cell cell;
					int j = 0;
					Usuario usuario = new Usuario();
					String valor = "";
					while (cellIterator.hasNext()) {
						valor = this.getCellValue(cell = cellIterator.next());
						this.insertarCampoUsuario(usuario, j++, valor);
					}
					this.usuarios.add(usuario);
				}
				conta++;
			}
		}
	}
	
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

	private void insertarCampoUsuario(Usuario usuario, int numColumna,String valor) {
		switch(numColumna){
		case 0:
			usuario.setNombre(valor);
			return;
		case 1:
			usuario.setApellidos(valor);
			return;
		case 2:
			usuario.setMail(valor);
			return;
		case 3:
			usuario.setFechaNacimiento(valor);
			return;
		case 4:
			usuario.setDireccion(valor);
			return;
		case 5:
			usuario.setNacionalidad(valor);
			return;
		case 6:
			usuario.setDNI(valor);
			return;
		}
	}
	
	/**
	 * Metodo para probar que los usuarios se cargan correctamente
	 * en el futuro se iran insertando en la BD
	 */
	public void showUsers(){
		for(Usuario usuario : this.usuarios)
			System.out.println(usuario.toString());
	}
}
