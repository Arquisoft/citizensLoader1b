package es.uniovi.asw.letters;

import es.uniovi.asw.CitizenDB;
import java.io.IOException;
import java.util.List;

public abstract class AbstractWriteLetter implements WriteLetter {

	protected List<CitizenDB> citizens;
	protected String message;
	
	public AbstractWriteLetter(List<CitizenDB> citizens,String message){
		this.citizens = citizens;
		this.message = message;
	}

	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	@Override
	public abstract void write(CitizenDB citizenDB) throws IOException;
}
