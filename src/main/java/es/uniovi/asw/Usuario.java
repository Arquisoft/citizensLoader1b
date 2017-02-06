package es.uniovi.asw;

public class Usuario {

	private String nombre;
	private String apellidos;
	private String mail;
	private String fechaNacimiento;
	private String direccion;
	private String nacionalidad;
	private String DNI;
	
	public Usuario(String nombre,String apellidos,String mail,String fechaNacimiento,
			String direccion,String nacionalidad,String DNI){
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.mail = mail;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.nacionalidad = nacionalidad;
		this.DNI = DNI;
	}
	
	public Usuario(){
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}
	
	public String toString(){
		return nombre+" "+apellidos+" "+mail+" "+" "+this.fechaNacimiento+" "+direccion+" "+nacionalidad+" "+DNI;
	}
}
