package es.uniovi.asw.dbupdate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import es.uniovi.asw.Citizen;

 
public class Database implements UpdateDB {

	@Override
	public void insertDB(List<Citizen> citizens) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			 con = getConection();
			 ps  = con.prepareStatement("insert into persona(name,surname,mail,birthday"
			 		+ ",address,nationality,DNI) values (?,?,?,?,?,?,?)");
			 for(Citizen citizen : citizens){
					if(!existCitizens(citizen.getDNI() , con)){

							ps.setString(2, citizen.getName());
							ps.setString(3, citizen.getSurname());
							ps.setString(4, citizen.getMail());
							ps.setDate(5, new java.sql.Date( citizen.getBirthday().getTime()));
							ps.setString(6, citizen.getAddress());
							ps.setString(7, citizen.getNationality());
							ps.setString(8, citizen.getDNI());
							ps.execute();
						}
					}
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
			con.close();
		}
		
	}
	
	public Connection getConection(){
		Connection con = null;     
        try {	
			 Class.forName( "org.hsqldb.jdbcDriver" );
			 con =  DriverManager.getConnection("jdbc:hsqldb:hsql://localhost", "SA", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
        return con;
	}
	
	private static boolean existCitizens(String dni , Connection con) throws SQLException  {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			 ps = con.prepareStatement("select * from Persona where dni = ?");
			 ps.setString(1, dni);
			 rs = ps.executeQuery();
			 return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			rs.close();
			ps.close();
		}
		return false;
	}

	
}
