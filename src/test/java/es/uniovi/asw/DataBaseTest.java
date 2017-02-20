package es.uniovi.asw;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import org.junit.Test;

import es.uniovi.asw.dbupdate.Insert;
import es.uniovi.asw.dbupdate.InsertP;


/**
 * @author oliver
 *
 */
public class DataBaseTest {

	@Test
	public void test() throws Exception {
		ArrayList<CitizenDB> citizenDBs = new ArrayList<CitizenDB>();
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
		for(int i = 0; i<9 ; i++){
			Date date =  d.parse((1+i)+"-03-1996");
			CitizenDB citizenDB  = new CitizenDB("Nombre"+i,"Apellidos"+i,"email"+i+"@gmail.com",date,
					"Calle"+i,"España", generatorDNI()+"A");
			citizenDBs.add(citizenDB);
		}
			Insert insert = new InsertP();		
			
			insert.insert(citizenDBs);
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

