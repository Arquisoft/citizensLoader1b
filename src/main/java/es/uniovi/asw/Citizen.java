package es.uniovi.asw;

import java.util.Date;

/**
 * Clase para almacenar los usuarios leidos por Loader
 * @author david
 */
public class Citizen {

	private String name;
	private String surname;
	private String mail;
	private Date birthday;
	private String address;
	private String nationality;
	private String DNI;
	
	public Citizen(String name,String surname,String mail,Date bithday,
			String address,String nationality,String DNI){
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.birthday = bithday;
		this.address = address;
		this.nationality = nationality;
		this.DNI = DNI;
	}
	
	public Citizen(){
		
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String toString(){
		return "[Nombre: " +name+"] [Apellido: "+surname+"] [E-Mail: "+mail
				+"] [Cumpleaños: "+birthday+"] [Direccion: "+address
				+"] [Nacionalidad: "+nationality+"] [DNI: "+DNI+"]";
	}
}
