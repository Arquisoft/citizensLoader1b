package es.uniovi.asw;

/**
 * Clase para almacenar los usuarios leidos por Loader
 * @author david
 *
 */
public class User {

	private String name;
	private String surname;
	private String mail;
	private String birthday;
	private String address;
	private String nacionality;
	private String DNI;
	
	public User(String name,String surname,String mail,String bithday,
			String address,String nacionality,String DNI){
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.birthday = bithday;
		this.address = address;
		this.nacionality = nacionality;
		this.DNI = DNI;
	}
	
	public User(){
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNacionality() {
		return nacionality;
	}

	public void setNacionality(String nacionality) {
		this.nacionality = nacionality;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String toString(){
		return name+" "+surname+" "+mail+" "+" "+birthday+" "+address+" "+nacionality+" "+DNI;
	}
}
