package es.uniovi.asw;

import static org.junit.Assert.*;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class DataBaseTest {
	

	@Test
	public void test() throws Exception {
		ArrayList<CitizenDB> citizenDBs = new ArrayList<CitizenDB>();
		int numeroAntes  = numberCitizen();
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
		for(int i = 0; i<9 ; i++){
			Date date =  d.parse((1+i)+"-03-1996");
			CitizenDB citizenDB  = new CitizenDB("Nombre"+i,"Apellidos"+i,"email"+i+"@gmail.com",date,
					"Calle"+i,"España", generatorDNI()+"A", "ADMIN");
			citizenDBs.add(citizenDB);
		}
			Insert insert = new InsertP();		
			insert.insert(citizenDBs);
		int numeroDespues = numberCitizen();
		assertTrue(numeroAntes<numeroDespues);
		
		borrarUsuarios(citizenDBs);
	}

	
	private int numberCitizen(){
		EntityManager em = Jpa.createEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		try{
			
			Query query = Jpa.getManager().createQuery("select c from CitizenDB c");
			return query.getResultList().size();
	        
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
	
	private void borrarUsuarios(List<CitizenDB> users){
		EntityManager em = Jpa.createEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		try{
			for(CitizenDB citizen : users){
					citizen = Jpa.getManager().merge(citizen);
					Jpa.getManager().remove(citizen);  
			}
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

