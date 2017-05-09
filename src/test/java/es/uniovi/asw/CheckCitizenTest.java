package es.uniovi.asw;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import es.uniovi.asw.dbupdate.Jpa;
import es.uniovi.asw.parser.CheckCitizen;


/**
 * @author oliver
 *
 */
public class CheckCitizenTest {

	CheckCitizen checkCitizen = new CheckCitizen();
	
	@Test
	public void checkCitizen() throws ParseException {
		List<CitizenDB> users = new ArrayList<CitizenDB>();
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
		Date date =  d.parse("01-03-1996");
		CitizenDB citizenDBOk  = new CitizenDB("Nombre","Apellidos","email@gmail.com",date,
				"Calle","España","12345678A", "ADMIN");
		users.add(citizenDBOk);
		CitizenDB citizenDBOkFieldEmpty  = new CitizenDB("","Apellidos","email@gmail.com",date,
				"Calle","España","12345678A" ,"ADMIN");
		users.add(citizenDBOkFieldEmpty);
		CitizenDB citizenDBErrorEmail  = new CitizenDB("Nombre","Apellidos","emailgmail.com",date,
				"Calle","España","12345678A","ADMIN");
		users.add(citizenDBErrorEmail);
		CitizenDB citizenDBNullBirtday  = new CitizenDB("Nombre","Apellidos","email@gmail.com",null,
				"Calle","España","12345678A","ADMIN");
		users.add(citizenDBNullBirtday);
		CitizenDB citizenDBErrorDNI  = new CitizenDB("Nombre","Apellidos","email@gmail.com",date,
				"Calle","España","1234578A","ADMIN");
		users.add(citizenDBErrorDNI);
		CitizenDB citizenDBApellidosVacio  = new CitizenDB("Nombre","","email@gmail.com",date,
				"Calle","España","12345678A","ADMIN");
		users.add(citizenDBApellidosVacio);
		CitizenDB citizenDBDireccionVacia  = new CitizenDB("Nombre","Apellidos","email@gmail.com",date,
				"","España","12345678A","ADMIN");
		users.add(citizenDBDireccionVacia);
		CitizenDB citizenDBNacionalidadVacia  = new CitizenDB("Nombre","Apellidos","email@gmail.com",date,
				"Calle","","12345678A","ADMIN");
		users.add(citizenDBNacionalidadVacia);
		
		
		assertTrue(checkCitizen.checkCitizenInformation(citizenDBOk));
		assertFalse(checkCitizen.checkCitizenInformation(citizenDBOkFieldEmpty));
		assertFalse(checkCitizen.checkCitizenInformation(citizenDBErrorEmail));
		assertFalse(checkCitizen.checkCitizenInformation(citizenDBNullBirtday));
		assertFalse(checkCitizen.checkCitizenInformation(citizenDBErrorDNI));
		assertFalse(checkCitizen.checkCitizenInformation(citizenDBApellidosVacio));
		assertFalse(checkCitizen.checkCitizenInformation(citizenDBDireccionVacia));
		assertFalse(checkCitizen.checkCitizenInformation(citizenDBNacionalidadVacia));
		
		borrarUsuarios(users);
	}
	
	@Test
	public void checkDNI() {
		String dniValido = "12345678A";
		String dniVacio = "";
		String dniCorto = "1234567A";
		String dniNumeros = "123456789";
		String dniLetras = "ABCDEFGHI";
		String dniLetraMedio = "1234A5678";
		
		assertTrue(checkCitizen.checkDNI(dniValido));
		assertFalse(checkCitizen.checkDNI(dniVacio));
		assertFalse(checkCitizen.checkDNI(dniCorto));
		assertFalse(checkCitizen.checkDNI(dniNumeros));
		assertFalse(checkCitizen.checkDNI(dniLetras));
		assertFalse(checkCitizen.checkDNI(dniLetraMedio));
	}
	
	@Test
	public void checkBirthday() throws ParseException{
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
		Date dateValida =  d.parse("01-03-1996");
		Date dateNull = null;
		
		assertTrue(checkCitizen.checkBirthday(dateValida));
		assertFalse(checkCitizen.checkBirthday(dateNull));
		
	}
	
	@Test
	public void checkMail(){
		String mailValido = "email@gmail.com";
		String mailVacio = "";
		String mailSinPunto = "email@gmailcom";
		String mailSinArroba = "emailgmail.com";
		String mailCArroba = "@email@gmail.com";
		String mailCPunto = ".email@gmail.com";
		String mailFPunto = "email@gmailcom.";
		String mailFArroba = "emailgmail.com@";
		
		assertTrue(checkCitizen.checkMail(mailValido));
		assertFalse(checkCitizen.checkMail(mailVacio));
		assertFalse(checkCitizen.checkMail(mailSinPunto));
		assertFalse(checkCitizen.checkMail(mailSinArroba));
		assertFalse(checkCitizen.checkMail(mailCArroba));
		assertFalse(checkCitizen.checkMail(mailCPunto));
		assertFalse(checkCitizen.checkMail(mailFPunto));
		assertFalse(checkCitizen.checkMail(mailFArroba));
	}
	
	@Test
	public void checkFieldString(){
		String vacio = "";
		String s = "prueba";
		
		assertFalse(checkCitizen.checkFieldString(vacio, "Prueba"));
		assertTrue(checkCitizen.checkFieldString(s, "Prueba"));
	}
	
	@Test
	public void areDigits(){
		String cadenaDigitos = "123456789";
		String cadenaMixta = "123ABC456";
		String cadenaCaracteres = "ABCDEFGHI";
		String cadenaEspecial = "1-A/t_2:3";
		
		assertTrue(checkCitizen.areDigits(cadenaDigitos));
		assertFalse(checkCitizen.areDigits(cadenaMixta));
		assertFalse(checkCitizen.areDigits(cadenaCaracteres));
		assertFalse(checkCitizen.areDigits(cadenaEspecial));
	}
	
	@Test
	public void toStringTest() throws ParseException{
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
		List<CitizenDB> users = new ArrayList<CitizenDB>();
		Date date =  d.parse("01-03-1996");
		CitizenDB citizenDBOk  = new CitizenDB("Nombre","Apellidos","email@gmail.com",date,
				"Calle","España","12345678A","ADMIN");
		users.add(citizenDBOk);
		String correctToString = "[Nombre: " +"Nombre"+"] [Apellido: "+"Apellidos"+"] [E-Mail: "+"email@gmail.com"
				+"] [Cumpleaños: "+date+"] [Direccion: "+"Calle"
				+"] [Nacionalidad: "+"España"+"] [DNI: "+"12345678A"+"]";
		
		String incorrecto = "{Nombre: " +"Nombre"+"] [Apellido: "+"Apellidos"+"] [E-Mail: "+"email@gmail.com"
				+"] [Cumpleaños: "+date+"] [Direccion: "+"Calle"
				+"] [Nacionalidad: "+"España"+"] [DNI: "+"12345678A"+"}";
		
		assertEquals(citizenDBOk.toString(), correctToString);
		assertFalse(citizenDBOk.toString().equals(incorrecto));
		
		borrarUsuarios(users);
	}
	
	@Test
	public void hashCodeTest() throws ParseException{
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
		List<CitizenDB> users = new ArrayList<CitizenDB>();
		Date date =  d.parse("01-03-1996");
		CitizenDB citizenDBOk  = new CitizenDB("Nombre","Apellidos","email@gmail.com",date,
				"Calle","España","12345678A","ADMIN");
		users.add(citizenDBOk);
		
		assertEquals(-1867378596, citizenDBOk.hashCode());
		
		borrarUsuarios(users);
	}
	
	@Test
	public void equalsTest() throws ParseException{
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
		Date date =  d.parse("01-03-1996");
		List<CitizenDB> users = new ArrayList<CitizenDB>();
		CitizenDB citizenDBOk  = new CitizenDB("Nombre","Apellidos","email@gmail.com",date,
				"Calle","España","12345678A","ADMIN");
		users.add(citizenDBOk);
		CitizenDB citizenDBOk1  = new CitizenDB("Nombre","Apellidos","email@gmail.com",date,
				"Calle","España","12345678A","ADMIN");
		users.add(citizenDBOk1);
		CitizenDB citizenDBOk2  = new CitizenDB("Nombre","Apellidos","email@gmail.com",date,
				"Calle","España","12345679A","ADMIN");
		users.add(citizenDBOk2);
		
		assertTrue(citizenDBOk.equals(citizenDBOk1));
		assertFalse(citizenDBOk.equals(citizenDBOk2));
		
		borrarUsuarios(users);
	}
	
	private void borrarUsuarios(List<CitizenDB> users){
		EntityManager em = Jpa.createEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		try{
			
			for(CitizenDB citizen : users)
					Jpa.getManager().remove(citizen);    
		trx.commit();	    
		}catch(RuntimeException e){
			trx.rollback();
	    	throw e;
		}
		finally{
			em.close();
		}

	}

}
