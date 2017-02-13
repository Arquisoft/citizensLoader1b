package es.uniovi.asw.parser;

import java.util.Random;

public class GenerationPassword {

	private String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#~€¬!$%&/()=?¿";
	
	private int passwordLengt = 9;
	
	public String passwordGenerator(){
		
		Random random = new Random();
		String password ="";
		while(password.length() != passwordLengt){
			password = password + characters.charAt(random.nextInt(characters.length()-1));
		}
		return password;
	}
}
