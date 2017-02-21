package es.uniovi.asw;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
public class DataBaseTest {
	
	
	private void inicioTest() throws SQLException{
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		Statement st = null;
		try {
			connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			st = connection.createStatement();
			st.execute("CREATE TABLE PERSONA(ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY ,NAME VARCHAR (20) NOT NULL, SURNAME VARCHAR(40) NOT NULL , MAIL VARCHAR(40) NOT NULL , BIRTHDAY DATE NOT NULL, ADDRESS VARCHAR(40)NOT NULL , NATIONALITY VARCHAR(20) NOT NULL,  DNI VARCHAR(9) NOT NULL ,PASSWORD VARCHAR(9) NULL)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			st.close();
			connection.close();
		}
	}
	
	private void finTest() throws SQLException{
		Connection connection = null;
		Statement st = null;
		try {
			connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			st = connection.createStatement();
			st.execute("DROP TABLE PERSONA");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void test() throws Exception {
		inicioTest();
		ArrayList<CitizenDB> citizenDBs = new ArrayList<CitizenDB>();
		int numeroAntes  = numberCitizen();
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
		for(int i = 0; i<9 ; i++){
			Date date =  d.parse((1+i)+"-03-1996");
			CitizenDB citizenDB  = new CitizenDB("Nombre"+i,"Apellidos"+i,"email"+i+"@gmail.com",date,
					"Calle"+i,"España", generatorDNI()+"A");
			citizenDBs.add(citizenDB);
		}
			Insert insert = new InsertP();		
			
			insert.insert(citizenDBs);
		int numeroDespues = numberCitizen();
		assertTrue(numeroAntes<numeroDespues);
		finTest();
		
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
}

