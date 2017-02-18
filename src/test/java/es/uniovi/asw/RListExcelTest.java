package es.uniovi.asw;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import es.uniovi.asw.parser.AdapterPoi;

public class RListExcelTest {

	
	@Test(expected = java.io.FileNotFoundException.class)
	public void AdapterPoiTest() throws FileNotFoundException {
		
		new AdapterPoi("ruta");
		
	}

}
