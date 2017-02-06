package es.uniovi.asw.writer;

import java.io.IOException;

public abstract class AbstractWriteLetter implements WriteLetter {
	
	protected String userName;
	protected String userPass;
	
	public AbstractWriteLetter(String userName,String userPass){
		this.userName = userName;
		this.userPass = userPass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	@Override
	public abstract void write(String mensaje) throws IOException;
}
