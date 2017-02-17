package es.uniovi.asw;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Test;

import es.uniovi.asw.dbupdate.Insert;
import es.uniovi.asw.dbupdate.InsertP;
import es.uniovi.asw.dbupdate.Jpa;


/**
 * @author oliver
 *
 */
public class DateBaseTest {

	@Test
	public void test() throws Exception {
		ArrayList<CitizenDB> citizenDBs = new ArrayList<CitizenDB>();
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
		Long numberCitizenAntiguos = (Long) checkNumbeCitizen();
		for(int i = 0; i<9 ; i++){
			Date date =  d.parse((1+i)+"-03-1996");
			CitizenDB citizenDB  = new CitizenDB("Nombre"+i,"Apellidos"+i,"email"+i+"@gmail.com",date,
					"Calle"+i,"España",generatorDNI()+"A");
			citizenDBs.add(citizenDB);
		}
			//Insert insert = new InsertP();
			
			//insert.insert(citizenDBs);
			
			//Long numberCitizenNuevo = (Long) checkNumbeCitizen();	
		
			//assertTrue(numberCitizenAntiguos < numberCitizenNuevo);
			
			//numberCitizenAntiguos = numberCitizenNuevo;
		
			//insert.insert(citizenDBs);
		
			//numberCitizenNuevo = (Long) checkNumbeCitizen();			
		
			//assertFalse(numberCitizenNuevo < numberCitizenAntiguos);
			//assertTrue(numberCitizenAntiguos == numberCitizenNuevo);
		
	}

	private Object checkNumbeCitizen() {
		EntityManager em = Jpa.createEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		try{
			
			Query query = Jpa.getManager().createQuery("select count(c) from CitizenDB c");
			Long numberOfCitizen = (Long) query.getSingleResult();
			trx.commit();
			return numberOfCitizen;
		}catch(RuntimeException e){
			trx.rollback();
	    	throw e;
		}
		finally{
			em.close();
			
	}
  }
	private Object generatorDNI() {
		Random random = new Random();
		String dni = "";
		for(int i = 0 ; i<8; i++){
			dni = dni + random.nextInt(9);
		}
		return dni;
			
	}
}

