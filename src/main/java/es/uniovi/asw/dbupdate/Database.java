package es.uniovi.asw.dbupdate;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	private static Database database = new Database();
	
	private Database(){
		startDataBase();
	}
	
	private void startDataBase() {
	if(!existTable()){
		Connection connection = null;
		Statement st = null;
		try {
			connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			st = connection.createStatement();
			st.execute("CREATE TABLE PERSONA(ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY ,NAME VARCHAR (20) NOT NULL, SURNAME VARCHAR(40) NOT NULL , MAIL VARCHAR(40) NOT NULL , BIRTHDAY DATE NOT NULL, ADDRESS VARCHAR(40)NOT NULL , NATIONALITY VARCHAR(20) NOT NULL,  DNI VARCHAR(9) NOT NULL ,PASSWORD VARCHAR(9) NULL)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				st.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	}
	
	public static Database getDatabase(){
		return database;
			
	}
	
	public boolean existTable()  {
		boolean bResult = false;
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		try {
				connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				DatabaseMetaData dbm = connection.getMetaData();
				ResultSet rsTable = dbm.getTables(null, null, "PERSONA", null);
				if(rsTable.next())
					bResult = true;
					else
					bResult = false;
		} catch (SQLException e) {
				e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 
		return bResult;
	}
}
