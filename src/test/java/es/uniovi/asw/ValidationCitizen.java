package es.uniovi.asw;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import es.uniovi.asw.parser.CheckCitizen;


/**
 * @author oliver
 *
 */
public class ValidationCitizen {

	CheckCitizen checkCitizen = new CheckCitizen();
	
	@Test
	public void test() throws ParseException {
		
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
		Date date =  d.parse("01-03-1996");
		CitizenDB citizenDBOk  = new CitizenDB("Nombre","Apellidos","email@gmail.com",date,
				"Calle","España","12345678A");
		CitizenDB citizenDBOkFieldEmpty  = new CitizenDB("","Apellidos","email@gmail.com",date,
				"Calle","España","12345678A");
		CitizenDB citizenDBErrorEmail  = new CitizenDB("Nombre","Apellidos","emailgmail.com",date,
				"Calle","España","12345678A");
		CitizenDB citizenDBNullBirtday  = new CitizenDB("Nombre","Apellidos","email@gmail.com",null,
				"Calle","España","12345678A");
		CitizenDB citizenDBErrorDNI  = new CitizenDB("Nombre","Apellidos","email@gmail.com",date,
				"Calle","España","1234578A");
		
		assertTrue(checkCitizen.checkCitizenInformation(citizenDBOk));
		assertFalse(checkCitizen.checkCitizenInformation(citizenDBOkFieldEmpty));
		assertFalse(checkCitizen.checkCitizenInformation(citizenDBErrorEmail));
		assertFalse(checkCitizen.checkCitizenInformation(citizenDBNullBirtday));
		assertFalse(checkCitizen.checkCitizenInformation(citizenDBErrorDNI));
	}

}
