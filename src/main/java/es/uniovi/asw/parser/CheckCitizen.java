package es.uniovi.asw.parser;

import java.util.Date;

import es.uniovi.asw.CitizenDB;
import es.uniovi.asw.dbupdate.WReportR;
import es.uniovi.asw.dbupdate.WriteReport;

/**
 * @author oliver
 *
 */
public class CheckCitizen {
	
	private  WriteReport reporter = new WReportR();

	/**
	 * @param citizen
	 */
	public boolean checkCitizenInformation(CitizenDB citizen){
		return checkFieldString(citizen.getName(),"name") 
				&& checkFieldString(citizen.getSurname(),"surname") 
				&& checkMail(citizen.getMail()) 
				&& checkBirthday(citizen.getBirthday()) 
				&& checkFieldString(citizen.getAddress(),"Address")
				&& checkFieldString(citizen.getNationality(),"Nationality") 
				&& checkDNI(citizen.getDNI());
	}

	
	/**Metodo que se encarga de comprobar si el campo dni esta vacio 
	 * @param dni: campo a comprobar
	 * @return devuelve true en caso de que no este vacio y false en caso de que si, ademas
	 * en este caso escribiria el error en el log
	 */
	public boolean checkDNI(String dni) {
		if(!checkFieldString(dni, "dni"))
			return false;
		else if (dni.length() != 9 || Character.isDigit(dni.toString().charAt(dni.length()-1))
				|| !areDigits(dni.substring(0, 7))){
			//Una vez hecho el logo aqui iria reporter.write(informacion que tenemos que meter en el log);
			return false;
		}
		
		return true;
	}

	/**Metodo que se encarga de comprobar si una cadena son numeros
	 * @param substring: cadena a comprobar
	 * @return devueve true si la cadena son numeros y false si no
	 */
	private boolean areDigits(String substring) {
		for(int i = 0; i<substring.length() ; i++)
			if(Character.isLetter(substring.toString().charAt(i)))
				return false;
		return true;
	}


	/**Metodo que se encarga de comprobar si el campo birthday es nulo
	 * @param birthday: campo a comprobar
	 * @return devuelve true en caso de que no sea null y false en caso de que si, ademas
	 * en este caso escribiria el error en el log
	 */
	public boolean checkBirthday(Date birthday) {
		if(birthday == null){
			//Una vez hecho el logo aqui iria reporter.write(informacion que tenemos que meter en el log);
			return false;
		}
		return true;
	}

	/**Metodo que se encarga de comprobar si el campo Mail esta vacio y si tiene el formato correcto
	 * @param mail: mail que queremos comprobar
	 * @return devuelve true en caso de que el campo no este vacio y tenga el formato correcto y 
	 * falso en el caso de que esto no se cierto, ademas en este caso se escribiria un error en el log
	 */
	public boolean checkMail(String mail) {
		if(!checkFieldString(mail, "mail"))
			return false;
		else if (!mail.toString().contains(".") ||!mail.toString().contains("@")
				|| mail.toString().startsWith("@") ||  mail.toString().startsWith(".") 
				|| mail.toString().charAt(mail.length()-1) == '.' 
				|| mail.toString().charAt(mail.length()-1) == '@'){
			//Una vez hecho el logo aqui iria reporter.write(informacion que tenemos que meter en el log);
			return false;	
		}
			
		return true;
	}

	
	/**Metodo que se encarga de comprobar si algun un campo esta vacio
	 * @param field: campo que queremos comprobar
	 * @param attribute: nombre del campo
	 * @return devuelve true en caso de que el campo no este vacio y falso en el caso de que si
	 * ademas en este caso se escribiria un error en el log
	 */
	public boolean checkFieldString(String field , String attribute) {
		if(field.isEmpty()){
			//Una vez hecho el logo aqui iria reporter.write(informacion que tenemos que meter en el log);
			return false;
		}
		return true;
	}
}
