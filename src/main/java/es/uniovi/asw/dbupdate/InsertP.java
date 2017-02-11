package es.uniovi.asw.dbupdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import es.uniovi.asw.Citizen;

/**
 * 
 * @author oliver
 */
public class InsertP implements Insert{

	private Database database;
	
	//Este es el insert que va a meter los ciudadanos a la base de datos.
	@Override
	public List<Citizen> insert(List<Citizen> citizens, String path) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			 con = database.getConection();
			 ps  = con.prepareStatement("insert into persona(name,surname,mail,birthday"
			 		+ ",address,nationality,DNI) values (?,?,?,?,?,?,?)");
			 for(Citizen citizen : citizens){
							ps.setString(2, citizen.getName());
							ps.setString(3, citizen.getSurname());
							ps.setString(4, citizen.getMail());
							ps.setDate(5, new java.sql.Date( citizen.getBirthday().getTime()));
							ps.setString(6, citizen.getAddress());
							ps.setString(7, citizen.getNationality());
							ps.setString(8, citizen.getDNI());
							ps.execute();
						}
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
			con.close();
		}
		return null;
		
	}

}
