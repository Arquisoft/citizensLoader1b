package es.uniovi.asw;

public class User {

	private String name;
	private String surname;
	private String mail;
	private String bithday;
	private String address;
	private String nacionality;
	private String DNI;
	
	public User(String name,String surname,String mail,String fechaNacimiento,
			String direccion,String nacionalidad,String DNI){
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.bithday = fechaNacimiento;
		this.address = direccion;
		this.nacionality = nacionalidad;
		this.DNI = DNI;
	}
	
	public User(){
		
	}

	public String getNombre() {
		return name;
	}

	public void setNombre(String nombre) {
		this.name = nombre;
	}

	public String getApellidos() {
		return surname;
	}

	public void setApellidos(String apellidos) {
		this.surname = apellidos;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFechaNacimiento() {
		return bithday;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.bithday = fechaNacimiento;
	}

	public String getDireccion() {
		return address;
	}

	public void setDireccion(String direccion) {
		this.address = direccion;
	}

	public String getNacionalidad() {
		return nacionality;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionality = nacionalidad;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}
	
	public String toString(){
		return name+" "+surname+" "+mail+" "+" "+this.bithday+" "+address+" "+nacionality+" "+DNI;
	}
}
