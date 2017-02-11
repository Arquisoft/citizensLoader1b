package es.uniovi.asw;

import java.util.Date;

/**
 * Clase que adapta los ciudadanos creados para su posterior insercion en la
 * base de datos
 * @author Sergio
 */
@Entity
@Table(name="Persona")
public class CitizenDB {

	@Id
	@GeneratedValue
	private Long id; //Id autogenerado cuando se inserta el ciudadano a la BD
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;
	@Column(unique = true, nullable = false)
	private String mail;
	@Column(nullable = false)
	private Date birthday;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String nationality;
	@Column(unique = true, nullable = false)
	private String DNI;
	@Collumn(nullable = false)
	private String password; //La password que se va a poner al ciudadano
	

	public CitizenDB(String name,String surname,String mail,Date bithday,
			String address,String nationality,String DNI){
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.birthday = bithday;
		this.address = address;
		this.nationality = nationality;
		this.DNI = DNI;
	}
	
	public CitizenDB(){
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getMail() {
		return mail;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getAddress() {
		return address;
	}

	public String getNationality() {
		return nationality;
	}

	public String getDNI() {
		return DNI;
	}

	public String toString(){
		return "[Nombre: " +name+"] [Apellido: "+surname+"] [E-Mail: "+mail
				+"] [Cumpleaños: "+birthday+"] [Direccion: "+address
				+"] [Nacionalidad: "+nationality+"] [DNI: "+DNI+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CitizenDB other = (CitizenDB) obj;
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.DNI))
			return false;
		return true;
	}
	
	
}
