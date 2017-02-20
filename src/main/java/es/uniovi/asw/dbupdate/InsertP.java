package es.uniovi.asw.dbupdate;

import java.sql.SQLException;
import java.util.List;
import es.uniovi.asw.CitizenDB;
import es.uniovi.asw.ReportWriter.WriteReport;
import es.uniovi.asw.parser.CheckCitizen;
import es.uniovi.asw.parser.GenerationPassword;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author oliver
 */
public class InsertP implements Insert{

	private CheckCitizen checkCitizen = new CheckCitizen();
	private GenerationPassword generationPassword = new GenerationPassword();	
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<CitizenDB> insert(List<CitizenDB> citizens) throws SQLException {
		
			for(CitizenDB citizen : citizens)
				if(checkCitizen.checkCitizenInformation(citizen)  && checkCitizen(citizen.getDNI())){
					citizen.setPassword(generationPassword.passwordGenerator());
					userRepository.save(citizen);
				}	    
		return null;
	}

	/**Metodo que se encarga de comprobar si un ciudano ya existe en la base de datos
	 * @param citizen: ciudadano que queremos comprobar
	 * @return devuelve true si no esta y false si esta
	 */
	private boolean checkCitizen(String dni) {
		WriteReport report = new WReportR();
		CitizenDB citizen = userRepository.findByDni(dni);
		if(citizen !=null){
			report.log("El usuario ya existe en la base de datos.");
			return false;
		}
		return true;
		
	}
}
