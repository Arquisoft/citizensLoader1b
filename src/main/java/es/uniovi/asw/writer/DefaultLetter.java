package es.uniovi.asw.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class DefaultLetter extends AbstractWriteLetter{
	
	public DefaultLetter(String userName,String userPass){
		super(userName,userPass);
	}

	@Override
	public void write(String mensaje) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter(
				"src/main/resources/letters/plainText/"+this.userName+".txt"));
				//"/citizensLoader1b/src/main/resources/letters/plainText"
		
		printWriter.println("Estimado "+this.userName);
		printWriter.println(mensaje);
		printWriter.println("Contraseña: "+this.userPass);
		printWriter.close();
	}
}
