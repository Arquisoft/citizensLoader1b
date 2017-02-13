package es.uniovi.asw.letters;

import java.io.IOException;

public abstract class AbstractWriteLetter implements WriteLetter {
	
	protected String userName;
	protected String userPass;
	protected String mail;
	
	public AbstractWriteLetter(String name,String userPass, String mail){
		this.userName = name;
		this.userPass = userPass;
		this.mail = mail;
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
	
	public String getMail(){
		return this.mail;
	}
	
	public void setMail(String mail){
		this.mail = mail;
	}

	@Override
	public abstract void write(String mensaje) throws IOException;
}
