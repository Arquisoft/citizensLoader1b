package es.uniovi.asw.letters;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class DefaultLetter extends AbstractWriteLetter{
	
	public DefaultLetter(String userName,String userPass,String mail){
		super(userName,userPass, mail);
	}

	@Override
	public void write(String mensaje) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter(
				"src/main/resources/letters/plainText/"+this.userName+".txt"));
				//"/citizensLoader1b/src/main/resources/letters/plainText"
		
		printWriter.println("Estimado "+this.userName);
		printWriter.println(mensaje);
		printWriter.println("Usuario: "+this.mail);
		printWriter.println("Contraseña: "+this.userPass);
		printWriter.close();
	}
}
