package es.uniovi.asw;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import es.uniovi.asw.dbupdate.Insert;
import es.uniovi.asw.dbupdate.InsertP;


/**
 * @author oliver
 *
 */
public class DateBaseTest {

	@Test
	public void test() throws Exception {
		ArrayList<CitizenDB> citizenDBs = new ArrayList<CitizenDB>();
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
		for(int i = 0; i<9 ; i++){
			Date date =  d.parse((1+i)+"-03-1996");
			CitizenDB citizenDB  = new CitizenDB("Nombre"+i,"Apellidos"+i,"email"+i+"@gmail.com",date,
					"Calle"+i,"España","1234567"+i+"A");
			citizenDBs.add(citizenDB);
		}
			Insert insert = new InsertP();
			
			//insert.insert(citizenDBs, " ");
		}
}

