package es.uniovi.asw;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import es.uniovi.asw.dbupdate.WReportR;

public class LogTest {

	WReportR logTest = new WReportR();
	
	@Test
	public void logTest() {
		String prueba = "Test de prueba para el log";
		
		logTest.log(prueba);
		
		try {
			FileReader file = new FileReader("log.txt");
			BufferedReader fileR = new BufferedReader(file);
			
			assertTrue(fileR.readLine().contains("Test de prueba para el log"));
			
			fileR.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}

}
