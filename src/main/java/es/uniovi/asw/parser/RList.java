package es.uniovi.asw.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.Citizen;
import es.uniovi.asw.CitizenDB;


/**
 * Clase para la carga de un fichero excel mediante la libreria APache Poi
 * @author david
 *
 */
public abstract class RList implements ReadList{
	
	private static Insert ins = new InsertR();
	
	@Override
	public List<CitizenDB> read() {
		List<Citizen> citizens = readFile(); 
		
		List<CitizenDB> citizensDb = new ArrayList<CitizenDB>();
		try {
			citizensDb = ins.insert(citizens);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return citizensDb;
	}

	
	
	abstract List<Citizen> readFile();
	
}
