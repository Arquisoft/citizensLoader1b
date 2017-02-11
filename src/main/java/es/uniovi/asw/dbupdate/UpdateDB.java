package es.uniovi.asw.dbupdate;

import java.sql.SQLException;
import java.util.List;

import es.uniovi.asw.Citizen;

public interface UpdateDB {

	public void insertDB(List<Citizen> citizens) throws SQLException;
}
