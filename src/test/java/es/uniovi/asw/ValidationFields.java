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
public class ValidationFields {
	
private CheckCitizen checkCitizen = new CheckCitizen();


@Test
public void ValidationEmail() {
 // Symbol -> @
	
	String mailOk = "email@gmail.com";
	String mailWithoutPoint = "email@gmailcom";
	String mailWithoutSymbol = "emailgmail.com";
	String mailStartWithPoint = ".email@gmail.com";
	String mailStartWithSymbol = "@email@gmail.com";
	String maiFinishWithPoint = "email@gmail.com.";
	String maiFinishWithSymbol = "email@gmail.com@";
	
	assertTrue(checkCitizen.checkMail(mailOk));
	assertFalse(checkCitizen.checkMail(mailWithoutPoint));
	assertFalse(checkCitizen.checkMail(mailWithoutSymbol));
	assertFalse(checkCitizen.checkMail(mailStartWithPoint));
	assertFalse(checkCitizen.checkMail(mailStartWithSymbol));
	assertFalse(checkCitizen.checkMail(maiFinishWithPoint));
	assertFalse(checkCitizen.checkMail(maiFinishWithSymbol));
}

@Test
public void ValidationDNI() {
	
	String DNIShort = "1234567A";
	String DNIWithoutLetter = "12345678";
	String DNIWithMoreLetter = "123F456A";
	String DNIOk= "12345678A";
	
	assertTrue(checkCitizen.checkDNI(DNIOk));
	assertFalse(checkCitizen.checkDNI(DNIShort));
	assertFalse(checkCitizen.checkDNI(DNIWithMoreLetter));
	assertFalse(checkCitizen.checkDNI(DNIWithoutLetter));
}

@Test
public void ValidationBirthday() throws ParseException {
	
	SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
	Date date =  d.parse("01-03-1996");
	
	assertTrue(checkCitizen.checkBirthday(date));
	assertFalse(checkCitizen.checkBirthday(null));
}

@Test
public void ValidationField(){
		
	String fieldEmpty = "";
	String fildOk = "cadena";
	
	assertTrue(checkCitizen.checkFieldString(fildOk, "Campo"));
	assertFalse(checkCitizen.checkFieldString(fieldEmpty, "Campo"));
	}

}
