package es.uniovi.asw.dbupdate;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import es.uniovi.asw.Citizen;

/**
 * 
 * @author oliver
 */
public class InsertP implements Insert{

	@Override
	public List<Citizen> insert(List<Citizen> citizens, String path) throws SQLException {
		EntityManager em = Jpa.createEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		try{
			
			for(Citizen citizen : citizens)
				Jpa.getManager().persist(citizen);
	    
		trx.commit();	    
		}catch(RuntimeException e){
			trx.rollback();
	    	throw e;
		}
		finally{
			em.close();
		}
		return null;
	}
}
